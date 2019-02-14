<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>工作流管理</title>
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
        <span class="card-title">工作流管理</span>
        <c:import url="../menubar.jsp?module=content" />
    </div>
    <div class="layui-card-body">
        <table class="layui-table" lay-even lay-size="sm">
            <thead>
            <tr>
                <th>ID</th>
                <th>工作流名称</th>
                <th>审核级数</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ workFlows }" var="workFlow">
                <tr>
                    <th>${ workFlow.id }</th>
                    <th>${ workFlow.name }</th>
                    <th>
                        <c:choose>
                            <c:when test="${ workFlow.steps==1 }">一级审核</c:when>
                            <c:when test="${ workFlow.steps==2 }">二级审核</c:when>
                            <c:when test="${ workFlow.steps==3 }">三级审核</c:when>
                            <c:when test="${ workFlow.steps==4 }">四级审核</c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                    </th>
                    <th>${ workFlow.description }</th>
                    <th>
                        <a href="${ base }content/WorkFlow/delete?id=${ workFlow.id }" name="delete"
                           class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
                        <a href="${ base }content/WorkFlow/edit?id=${ workFlow.id }"
                           class="layui-btn layui-btn-xs">编辑</a>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['layer', 'element'], function () {
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