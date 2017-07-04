package com.itzyf.bean;

/**
 * @author 依风听雨
 * @version 创建时间：2017/6/30 14:20
 */
public class ApiBean {
    private int id;
    private String method;
    private String response;
    private String description;
    private String groupname;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

}
