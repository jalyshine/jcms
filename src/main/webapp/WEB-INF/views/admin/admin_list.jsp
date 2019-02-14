<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成员管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
</head>
<body>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">成员管理</span>
        <c:import url="../menubar.jsp?module=admin" />
    </div>
    <div class="layui-card-body">
        <table class="layui-table" lay-even lay-size="sm">
            <thead>
            <tr>
                <th>ID</th>
                <th>用户名</th>
                <th>所属角色</th>
                <th>最后登录IP</th>
                <th>最后登录时间</th>
                <th>email</th>
                <th>真实姓名</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ admins }" var="admin">
                <tr>
                    <td>${ admin.id }</td>
                    <td>${ admin.userName }</td>
                    <td>
                        <c:if test="${ !admin.adminRole.disabled }">
                            ${ admin.adminRole.name }
                        </c:if>
                    </td>
                    <td>${ admin.lastLoginIp }</td>
                    <td>
                        <fmt:formatDate value="${ admin.lastLoginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${ admin.email }</td>
                    <td>${ admin.realName }</td>
                    <td>
                        <c:if test="${ admin.id == 1 }">
                            <a href="javascript:;" class="layui-btn layui-btn-xs layui-btn-disabled">删除</a>
                        </c:if>
                        <c:if test="${ admin.id != 1 }">
                            <a href="${ base }admin/Admin/delete?id=${ admin.id }" name="delete"
                               class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
                        </c:if>
                        <a href="${ base }admin/Admin/edit?id=${ admin.id }" class="layui-btn layui-btn-xs">修改</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['layer','element'], function () {
        var layer = layui.layer, $ = layui.$;

        $("a[name='delete']").click(function () {
            var url = this.href;
            layer.confirm('删除数据不可恢复，是否确定？', {icon: 3, title: '提示'}, function (index) {
                $('<form method="post"></form>').attr("action", url).appendTo($(document.body)).submit();
                layer.close(index);
            });
            return false;
        });
    });
</script>
</body>
</html>