package com.itzyf.bean.wx;

/**
 * @author 依风听雨
 * @version 创建时间：2017/7/10 11:46
 */
public class ErrorBean {

    /**
     * errcode : 40029 errmsg : invalid code
     */

    private int errcode;
    private String errmsg;


    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
