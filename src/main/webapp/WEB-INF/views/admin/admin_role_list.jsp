<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
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
        <span class="card-title">角色管理</span>
        <c:import url="../menubar.jsp?module=admin" />
    </div>
    <div class="layui-card-body">
        <table class="layui-table" lay-even lay-size="sm">
            <thead>
            <tr>
                <th>ID</th>
                <th>角色名称</th>
                <th>角色描述</th>
                <th>角色状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ adminRoles }" var="role">
                <tr>
                    <td>${ role.id }</td>
                    <td>${ role.name }</td>
                    <td>${ role.description }</td>
                    <td>${ role.disabled=="true"?"禁用":"可用" }</td>
                    <td>
                        <c:if test="${ role.id != 1 }">
                            <a href="${ base }admin/AdminRolePrivacy/manage?role=${ role.id }"
                               class="layui-btn layui-btn-xs">权限设置</a>
                            <a href="${ base }content/CategoryPrivacy/manage?role=${ role.id }"
                               class="layui-btn layui-btn-xs">栏目权限</a>
                            <a href="${ base }admin/Admin/list?role=${ role.id }"
                               class="layui-btn layui-btn-xs">成员管理</a>
                            <a href="${ base }admin/AdminRole/delete?id=${ role.id }" name="delete"
                               class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
                            <a href="${ base }admin/AdminRole/edit?id=${ role.id }"
                               class="layui-btn layui-btn-xs">编辑</a>
                        </c:if>
                        <c:if test="${ role.id == 1 }">
                            <a href="javascript:;" class="layui-btn layui-btn-xs layui-btn-disabled">权限设置</a>
                            <a href="javascript:;" class="layui-btn layui-btn-xs layui-btn-disabled">栏目权限</a>
                            <a href="${ base }admin/Admin/list?role=${ role.id }&menu=${ param.menu }"
                               class="layui-btn layui-btn-xs">成员管理</a>
                            <a href="javascript:;" class="layui-btn layui-btn-xs layui-btn-disabled">删除</a>
                            <a href="javascript:;" class="layui-btn layui-btn-xs layui-btn-disabled">编辑</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript" src="${ base }assets/plugins/layui/layui.js"></script>
<script type="text/javascript">
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