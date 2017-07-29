package com.itzyf.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 依风听雨
 * @version 创建时间：2017/6/1 11:32
 */
@Getter
@Setter
@ToString
public class Result<T> {
    private String msg;
    private int code;
    private T result;
}
