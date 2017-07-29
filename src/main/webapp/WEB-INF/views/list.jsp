<%--
  Created by IntelliJ IDEA.
  User: zouyuanfeng
  Date: 2017/7/29
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="include/taglibs.jsp" %>
<html>
<head>
    <title>列表测试接口</title>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="${res}/layer/layer.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <style>
        pre {
            outline: 1px solid #ccc;
            padding: 5px;
            margin: 5px;
        }

        .string {
            color: green;
        }

        .number {
            color: darkorange;
        }

        .boolean {
            color: blue;
        }

        .null {
            color: magenta;
        }

        .key {
            color: red;
        }
    </style>
</head>
<body style="width:80%; padding-top: 10px; margin: 0 auto">
<h1 style="text-align: center">列表测试接口</h1>
<div>
    地址：http://IP:端口号/getList --> <a href="/getList">测试</a><br>
    请求参数：<br>
    参数一：currentPage，当前页码，从0开始，默认0<br>
    参数二：pageSize，分页大小，默认10<br>
    参数三：total，总共的条数<br>
    返回示例：<br>
</div>
<pre id="result">

</pre>

<script>
  function syntaxHighlight(json) {
    if (typeof json != 'string') {
      json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
    return json.replace(
        /("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g,
        function (match) {
          var cls = 'number';
          if (/^"/.test(match)) {
            if (/:$/.test(match)) {
              cls = 'key';
            } else {
              cls = 'string';
            }
          } else if (/true|false/.test(match)) {
            cls = 'boolean';
          } else if (/null/.test(match)) {
            cls = 'null';
          }
          return '<span class="' + cls + '">' + match + '</span>';
        });
  }

  $.get("getList", function(result){
    $('#result').html(syntaxHighlight(result));
  });

</script>
</body>
</html>
