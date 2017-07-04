<%--
  Created by IntelliJ IDEA.
  User: zouyuanfeng
  Date: 2017/7/4
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="include/taglibs.jsp" %>
<html>
<head>
    <title>上传接口</title>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="${res}/layer/layer.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body style="margin: 10px">
<code>
    地址：http://IP:8080/fileUpload
    参数: file
</code>
<form action="fileUpload" method="post" enctype="multipart/form-data">

    <div class="form-group">
        <label for="exampleInputFile">文件上传</label>
        <input type="file" id="exampleInputFile" accept="image/png,image/gif" name="file">
    </div>
    <button class="btn btn-default">提交</button>
</form>
</body>
</html>
