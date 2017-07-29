package com.itzyf.service;

import com.itzyf.bean.wx.AccessTokenBean;
import com.itzyf.bean.wx.UserInfoBean;
import com.itzyf.utils.GlobalConfig;
import com.itzyf.utils.HttpUtils;
import java.io.IOException;
import java.net.URLEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 依风听雨
 * @version 创建时间：2017/7/10 10:34
 */
@Service("wxService")
public class WxService {

    /**
     * 微信授权，获取CODE
     *
     * <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0e81c3bee622d60&
     * redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php&response_type=
     * code&scope=snsapi_userinfo&state=STATE#wechat_redirect">测试链接，需要公众号中打开</a>
     */
    public String authorize(String redirect_uri) {

        try {
            return GlobalConfig.getConfig().getConfigWxUrlValue("wx_auth_url")
                    .replace("${redirect_uri}", URLEncoder.encode(redirect_uri, "UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 请求获取accessToken
     */
    public AccessTokenBean getAccessToken(String code) {
        String url = GlobalConfig.getConfig().getConfigWxUrlValue("wx_access_token")
                .replace("${code}", code);
        try {
            return HttpUtils.request(url, AccessTokenBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户信息
     */
    public UserInfoBean getUserInfo(String accessToken, String openId) {
        String url = GlobalConfig.getConfig().getConfigWxUrlValue("wx_userinfo")
                .replace("${accessToken}", accessToken).replace("${openid}", openId);
        try {
            return HttpUtils.request(url, UserInfoBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public double getRandomMoney(LeftMoneyPackage _leftMoneyPackage) {
//        // remainSize 剩余的红包数量
//        // remainMoney 剩余的钱
//        if (_leftMoneyPackage.remainSize == 1) {
//            _leftMoneyPackage.remainSize--;
//            return (double) Math.round(_leftMoneyPackage.remainMoney * 100) / 100;
//        }
//        Random r = new Random();
//        double min = 0.01; //
//        double max = _leftMoneyPackage.remainMoney / _leftMoneyPackage.remainSize * 2;
//        double money = r.nextDouble() * max;
//        money = money <= min ? 0.01 : money;
//        money = Math.floor(money * 100) / 100;
//        _leftMoneyPackage.remainSize--;
//        _leftMoneyPackage.remainMoney -= money;
//        return money;
//    }


}
