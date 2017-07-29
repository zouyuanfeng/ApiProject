<%--
  Created by IntelliJ IDEA.
  User: zouyuanfeng
  Date: 2017/7/29
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>列表测试接口</title>
</head>
<body style="width:80%; padding-top: 10px; margin: 0 auto">
<h1 style="text-align: center">列表测试接口</h1>
<div >
    地址：http://IP:端口号/getList --> <a href="/getList">测试</a><br>
    请求参数：<br>
    参数一：currentPage，当前页码，从0开始，默认0<br>
    参数二：pageSize，分页大小，默认10<br>
    参数三：total，总共的条数<br>
    返回示例：<br>
</div>
<pre>

    {
        "msg": "成功",
        "code": 0,
        "result": {
            "total": 5,
            "currentPage": 0,
            "pageSize": 10,
            "hasNext": false,
            "dataList": [
                {
                    "name": "测试数据-->0",
                    "desc": "这是测试数据的描述。。。嗯，就这样吧"
                },
                {
                    "name": "测试数据-->1",
                    "desc": "这是测试数据的描述。。。嗯，就这样吧"
                },
                {
                    "name": "测试数据-->2",
                    "desc": "这是测试数据的描述。。。嗯，就这样吧"
                },
                {
                    "name": "测试数据-->3",
                    "desc": "这是测试数据的描述。。。嗯，就这样吧"
                },
                {
                    "name": "测试数据-->4",
                    "desc": "这是测试数据的描述。。。嗯，就这样吧"
                }
            ]
        }
    }
</pre>
</body>
</html>
