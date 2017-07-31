<%--
  Created by IntelliJ IDEA.
  User: zouyuanfeng
  Date: 2017/7/3
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="include/taglibs.jsp" %>
<html>
<head>
    <title>API</title>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="${res}/layer/layer.js"></script>
    <script src="https://cdn.bootcss.com/jquery.serializeJSON/2.8.1/jquery.serializejson.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body style="margin: 10px">
<h1 align="center">操作</h1>
<form id="form" style="width:50%;margin:0 auto;" onsubmit="return onSubmit()">
    <div class="form-group">
        <label for="method">方法名</label>
        <input maxlength="30" class="form-control" id="method" name="method" placeholder="请输入方法名称"<c:if
                test="${bean ne null}">
               value="${bean.method}"</c:if>>
    </div>
    <div class="form-group">
        <label for="groupname">分组名称</label>
        <input class="form-control" id="groupname" name="groupname" placeholder="请输入分组名称" <c:if
                test="${bean ne null}">
               value="${bean.groupname}"</c:if>>
    </div>
    <div class="form-group">
        <label for="description">接口描述</label>
        <input class="form-control" id="description" name="description" placeholder="请输入接口描述" <c:if
                test="${bean ne null}">
               value="${bean.description}"</c:if>>
    </div>
    <div class="form-group">
        <label for="response">返回报文--><a href="http://www.bejson.com/jsoneditoronline/" target="_blank">JSON校验工具</a></label>
        <textarea class="form-control" id="response" name="response" placeholder="请输入接口返回报文"
                  style="resize: vertical"> <c:if
                test="${bean ne null}">
            ${bean.response}</c:if></textarea>
    </div>
    <input type="hidden" name="id"
    <c:if test="${bean ne null}">
           value="${bean.id}"</c:if> >
    <button type="submit" class="btn btn-default" style="float: right" id="button_submit">提交</button>
</form>
<script type="text/javascript">

    $(document).ready(function () {
        $("#button_submit").click(function () {

//            var reg = /[^u4e00-u9fa5]/;
//            if (reg.test($("#method").val())) {
//                alert("禁止输入中文");
//                return;
//            }
            $.ajax({
                type: "POST",
                url: "/operation",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify($('form').serializeJSON()),
                success: function (data) {
                    layer.msg(data.msg);
                    if (data.code == 0) {
                        setTimeout(function () {
                            location.href = "/index"
                        }, 1500)
                    }
                },
                error: function (e) {
                    alert("出错：" + e);
                }
            });
        });
    });

    function onSubmit() {
        return false
    }
</script>
</body>
</html>
