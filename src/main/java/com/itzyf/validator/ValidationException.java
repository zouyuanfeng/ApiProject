/**
 * www.tydic.com Inc.
 * Copyright (c) 2010-2016 All Rights Reserved.
 */
package com.itzyf.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 后台校验异常定义
 * @author tianshuo
 * @version Id: ValidationException.java, v 0.1 2016/7/28 0028 9:47 tianshuo Exp $$
 */
public class ValidationException extends Exception{
    private List<String> errors = null;

    public ValidationException(String msg) {
        super(msg);
        this.errors = new ArrayList();
        this.errors.add(msg);
    }

    public ValidationException(List<String> msgList) {
        this.errors = msgList;
    }

    public String getMessage() {
        return this.listToString(this.errors);
    }

    public List<String> getMessageList() {
        return this.errors;
    }

    private String listToString(List<String> list) {
        StringBuffer sb = new StringBuffer();
        Iterator i$ = list.iterator();

        while(i$.hasNext()) {
            String str = (String)i$.next();
            sb.append(str);
            sb.append("\r\n");
        }

        return sb.toString().trim();
    }
}