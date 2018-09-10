<%--
  author: 依风听雨
  创建时间：2018/04/10 09:34
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="include/taglibs.jsp" %>
<html>
<head>
    <title>按钮颜色生成</title>
    <script src="${res}/js/jquery.min.js"></script>
    <script src="${res}/layer/layer.js"></script>
    <script src="${res}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${res}/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body>


<div style="width: 600px;margin: 0 auto">
    <div class="page-header">
        <h1>按钮颜色生成</h1>
    </div>
    <div class="form-inline">
        <div class="form-group">
            <label class="sr-only" for="color">颜色值</label>
            <div class="input-group">
                <div class="input-group-addon">#</div>
                <input type="text" class="form-control" id="color" placeholder="color">
            </div>
        </div>
        <button type="submit" class="btn btn-primary" onclick="onSubmit()">生成</button>
    </div>

    <div>
        <h3>结果：</h3>
        <pre id="result">

        </pre>
    </div>
</div>
<script>

    function onSubmit() {
        var data = {};
        data['color'] = '#' + $("#color").val();
        $.ajax({
            type: "POST",
            url: "getColor",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (data) {
                if (data.code == 0) {
                    $("#result").html(JSON.stringify(data.result, null, "\t"));
                } else {
                    $("#result").html("");
                    layer.msg(data.msg);
                }
            },
            error: function (e) {
                alert("出错：" + e);
            }
        });
    }

</script>
</body>
</html>
