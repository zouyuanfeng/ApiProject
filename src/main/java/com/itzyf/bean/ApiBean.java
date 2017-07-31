package com.itzyf.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author 依风听雨
 * @version 创建时间：2017/6/30 14:20
 */
@Getter
@Setter
@ToString
public class ApiBean {

    private int id;
    @NotBlank(message = "方法名称不能为空")
    private String method;
    @NotBlank(message = "分组名称不能为空")
    private String groupname;
    @NotBlank(message = "响应数据不能为空")
    private String response;
    @NotBlank(message = "接口描述不能为空")
    private String description;


}
