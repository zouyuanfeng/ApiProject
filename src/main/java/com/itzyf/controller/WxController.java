package com.itzyf.controller;

import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import com.itzyf.bean.wx.AccessTokenBean;
import com.itzyf.bean.wx.UserInfoBean;
import com.itzyf.service.WxService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 依风听雨
 * @version 创建时间：2017/7/10 10:02
 */
@RequestMapping("wx")
@Controller
public class WxController {

    private final WxService wxService;

    @Autowired
    public WxController(WxService wxService) {
        this.wxService = wxService;
    }

    @RequestMapping("/index")
    public String index() {
        return "wx/index";
    }

    /**
     * 获取授权CODE
     */
    @RequestMapping({"/", ""})
    public String authorize() {
        return "redirect:" + wxService
                .authorize(
                        "http://itzyf.tunnel.whsz100.com/wx/index"); //授权完成之后回调该网址，并添加参数code和state
    }

    /**
     * 根据前面获得的CODE获取access_token
     */
    @ResponseBody
    @RequestMapping("access_token")
    public AccessTokenBean getAccessToken(HttpSession httpSession,
            @RequestParam String code) {
        String accessTokenJson = (String) httpSession.getAttribute("access_token");
        if (StringUtils.isEmpty(accessTokenJson) || "null".equals(accessTokenJson)) {
            AccessTokenBean accessToken = wxService.getAccessToken(code);
            if (!StringUtils.isEmpty(accessToken.getAccess_token())) {
                httpSession.setAttribute("access_token", new Gson().toJson(accessToken));
            }
            return accessToken;
        } else {
            return new Gson().fromJson(accessTokenJson, AccessTokenBean.class);
        }
    }

    @ResponseBody
    @RequestMapping("userInfo")
    public UserInfoBean getUserInfo(HttpSession httpSession) {
        String accessTokenJson = (String) httpSession.getAttribute("access_token");
        AccessTokenBean accessToken = new Gson()
                .fromJson(accessTokenJson, AccessTokenBean.class);
        return wxService.getUserInfo(accessToken.getAccess_token(), accessToken.getOpenid());
    }

    @RequestMapping("interface")
    public void interfaceWx() {

    }

//    @RequestMapping("redPackage")
//    public ModelAndView redPackage() {
//        ModelAndView mav = new ModelAndView("wx/redPackage");
//        mav.addObject("moneys", getRedPackage(10, 100));
//        return mav;
//    }

//    /**
//     * 微信红包
//     */
//    @ResponseBody
//    @RequestMapping("getRedPackage")
//    public List<Double> getRedPackage(@RequestParam int remainSize,
//            @RequestParam double remainMoney) {
//        if (remainMoney < 0 || remainMoney < 0) {
//            return new ArrayList<>();
//        }
//        LeftMoneyPackage redPackage = new LeftMoneyPackage();
//        redPackage.remainMoney = remainMoney;
//        redPackage.remainSize = remainSize;
//        List<Double> money = new ArrayList<>();
//        while (redPackage.remainSize > 0) {
//            money.add(wxService.getRandomMoney(redPackage));
//        }
//        return money;
//    }
}
