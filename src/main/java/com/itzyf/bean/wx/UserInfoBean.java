package com.itzyf.bean.wx;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 依风听雨
 * @version 创建时间：2017/7/10 14:27
 */
@Getter
@Setter
public class UserInfoBean extends ErrorBean{

    /**
     * openid :  OPENID nickname : NICKNAME sex : 1 province : PROVINCE city : CITY country :
     * COUNTRY headimgurl : http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ
     * 4eMsv84eavHiaiceqxibJxCfHe/46 privilege : ["PRIVILEGE1","PRIVILEGE2"] unionid :
     * o6_bmasdasdsad6_2sgVt7hMZOPfL
     */

    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private String unionid;
    private java.util.List<String> privilege;

}
