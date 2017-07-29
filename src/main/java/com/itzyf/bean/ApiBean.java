package com.itzyf.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 依风听雨
 * @version 创建时间：2017/6/30 14:20
 */
@Getter
@Setter
@ToString
public class ApiBean {
    private int id;
    private String method;
    private String response;
    private String description;
    private String groupname;

}
