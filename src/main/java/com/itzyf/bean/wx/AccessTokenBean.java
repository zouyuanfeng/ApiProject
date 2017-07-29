package com.itzyf.bean.wx;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 依风听雨
 * @version 创建时间：2017/7/10 11:41
 */
@Getter
@Setter
public class AccessTokenBean extends ErrorBean{

    /**
     * access_token : ACCESS_TOKEN
     * expires_in : 7200
     * refresh_token : REFRESH_TOKEN
     * openid : OPENID
     * scope : SCOPE
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;

}
