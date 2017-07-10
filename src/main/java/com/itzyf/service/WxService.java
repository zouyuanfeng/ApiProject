package com.itzyf.service;

import com.google.gson.Gson;
import com.itzyf.bean.wx.AccessTokenBean;
import com.itzyf.bean.wx.UserInfoBean;
import com.itzyf.intercept.HttpLoggingInterceptor;
import com.itzyf.utils.GlobalConfig;
import java.io.IOException;
import java.net.URLEncoder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

/**
 * @author 依风听雨
 * @version 创建时间：2017/7/10 10:34
 */
@Service("wxService")
public class WxService {

    private final String appid = GlobalConfig.getConfig().getConfigValue("wx_appId");
    private final String appSecret = GlobalConfig.getConfig().getConfigValue("wx_appSecret");
    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor())
            .build();

    /**
     * 微信授权，获取CODE
     *
     * <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0e81c3bee622d60&
     * redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php&response_type=
     * code&scope=snsapi_userinfo&state=STATE#wechat_redirect">测试链接，需要公众号中打开</a>
     */
    public String authorize(String redirect_uri) {

        try {
            return
                    "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid
                            + "&redirect_uri=" + URLEncoder.encode(redirect_uri, "UTF-8")
                            + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect ";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 请求获取accessToken
     */
    public AccessTokenBean getAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid
                + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code ";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.code() == 200) {
                return new Gson().fromJson(response.body().charStream(), AccessTokenBean.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户信息
     */
    public UserInfoBean getUserInfo(String accessToken, String openId) {
        String url =
                "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid="
                        + openId + "&lang=zh_CN";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.code() == 200) {
                return new Gson().fromJson(response.body().charStream(), UserInfoBean.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
