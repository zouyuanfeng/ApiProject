<%--
  Created by IntelliJ IDEA.
  User: zouyuanfeng
  Date: 2017/6/30
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="include/taglibs.jsp" %>
<html>
<head>
    <title>接口</title>
    <script src="${res}/js/jquery.min.js"></script>
    <script src="${res}/layer/layer.js"></script>
    <script src="${res}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${res}/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <style>
        .table > tbody > tr > td {
            vertical-align: middle;
        }
    </style>
</head>

<body>
<h1 style="text-align: center">接口列表</h1>
<div style="width: 80%;margin: 0 auto">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Single button -->
            <c:if test="${search ne true}">
                <div class="btn-group" style="margin:8px ">
                    <button type="button" class="btn btn-default dropdown-toggle"
                            data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                            ${groupName} <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="${ctx}/index">All</a></li>
                        <c:forEach items="${groupNames}" var="name">
                            <li><a href="${ctx}/index?groupName=${name}">${name}</a></li>
                        </c:forEach>
                    </ul>
                    <button class="btn btn-default" style="margin-left: 8px;"
                            onclick="deleteCheck()">
                        删除选中
                    </button>
                </div>
            </c:if>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <button class="btn btn-default" style="margin:8px "
                            onclick="javascript:location.href ='index'">
                        重置
                    </button>
                </li>
                <li>
                    <button class="btn btn-default" style="margin:8px "
                            onclick="javascript:location.href ='add'">
                        新增
                    </button>
                </li>
                <li>
                    <button class="btn btn-default" style="margin:8px "
                            onclick="javascript:location.href ='list'">
                        列表
                    </button>
                </li>
                <li>
                    <button class="btn btn-default" style="margin:8px "
                            onclick="javascript:location.href ='upload'">
                        上传
                    </button>
                </li>
                <li>
                    <button class="btn btn-default" style="margin:8px "
                            onclick="javascript:location.href ='button'">
                        按钮颜色生成
                    </button>
                </li>
            </ul>
            <form class="navbar-form navbar-right" action="search">
                <div class="form-group">
                    <input type="text" class="form-control" name="keyword" placeholder="请输入方法名">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
        </div>
    </nav>

    <table align="center" class="table table-hover">
        <thead>
        <tr>
            <th><input type="checkbox" onclick="onClickHander(this)">序号</th>
            <th>组名</th>
            <th>方法名</th>
            <th>返回值</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${apis}" var="api" varStatus="status">
            <tr>
                <td><input type="checkbox" value="${api.id}">${ status.index + 1}
                </td>
                <td>${api.groupname}</td>
                <td style="width: 10%">${api.method}</td>
                <td style="width: 50%">${api.response}</td>
                <td style="width: 10%">${api.description}</td>
                <td>
                    <button type="button" class="btn btn-primary btn-sm"
                            onclick="toApi('${api.groupname}','${api.method}')">请求
                    </button>
                    <button type="button" class="btn btn-primary btn-sm"
                            onclick="toPage(${api.id})">修改
                    </button>
                    <button type="button" class="btn btn-primary btn-sm"
                            onclick="deleteApi(${api.id})">删除
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <c:if test="${pages>1}">
        <nav aria-label="Page navigation" style="text-align: center">
            <ul class="pagination">
                <li <c:if test="${index+1 eq 1}">class="disabled"</c:if>>
                    <a href="${ctx}/index?index=${index-1}&groupName=${groupName}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pages}" var="page">
                    <c:if test="${page eq index+1}">
                        <li class="active"><a
                                href="${ctx}/index?index=${page-1}&groupName=${groupName}">${page}<span
                                class="sr-only">(current)</span></a>
                        </li>
                    </c:if>
                    <c:if test="${page ne index+1}">
                        <li>
                            <a href="${ctx}/index?index=${page-1}&groupName=${groupName}">${page}</a>
                        </li>
                    </c:if>

                </c:forEach>
                <li <c:if test="${index+1 eq pages}">class="disabled"</c:if>>
                    <a href="${ctx}/index?index=${index+1}&groupName=${groupName}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </c:if>
</div>


</body>
<script>
  function deleteApi(id) {
    console.log($(this));
    var msg = "您真的确定要删除吗？\n\n请确认！";
    layer.confirm(msg, {
      btn: ['确定', '取消'] //按钮
    }, function () {
      $.get("/delete/" + id, function (result, status) {
        if (result.code == 0) {
          location.reload()
        } else {
          layer.msg(result.msg)
        }
      });
    });
  }

  function toPage(id) {
    location.href = "update/" + id;
  }

  function toApi(group, method) {
    location.href = "api/" + group + "/" + method;
  }

  //    function getUrlParam(name) {
  //        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
  //        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
  //        if (r != null) return unescape(r[2]);
  //        return null; //返回参数值
  //    }

  function onClickHander(obj) {
    if (obj.checked) {
      $("td>:checkbox").prop("checked", 'true');
    } else {
      $("td>:checkbox").removeAttr("checked");
    }
  }

  function deleteCheck() {
    var ids = [];
    $("td>:checkbox").each(function (index, obj) {
      if (obj.checked) {
        ids.push(obj.value);
      }
    });
    console.log(ids);
    if (ids.length == 0) {
      layer.msg("未选中");
      return;
    }
    layer.confirm('是否确认删除选中', {
      btn: ['确定', '取消'] //按钮
    }, function () {
//            $.post("/deleteMuti", JSON.stringify(ids), function (result, status) {
//                if (result.code == 0) {
//                    location.reload()
//                } else {
//                    layer.msg(result.msg)
//                }
//            });

      $.ajax({
        type: "POST",
        url: "deleteMuti",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(ids),
        success: function (data) {
          if (data.code == 0) {
            layer.msg("删除成功");
            setTimeout(function () {
              location.reload()
            }, 500)
          }
        },
        error: function (e) {
          alert("出错：" + e);
        }
      });

    });
  }

</script>
</html>
