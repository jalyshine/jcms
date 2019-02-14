<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>模型管理</title>
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
		<span class="card-title">模型管理</span>
		<c:import url="../menubar.jsp?module=content" />
	</div>
	<div class="layui-card-body">
		<table class="layui-table" lay-even lay-size="sm">
			<thead>
			<tr>
				<th>ID</th>
				<th>模型名称</th>
				<th>数据表名</th>
				<th>模型描述</th>
				<th>模型状态</th>
				<th>数据量</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ models }" var="model">
				<tr>
					<td>${ model.id }</td>
					<td>${ model.name }</td>
					<td>${ model.tableName }</td>
					<td>${ model.description }</td>
					<td>${ model.disabled=="true"?"禁用":"可用" }</td>
					<td>${ model.itemCount }</td>
					<td><fmt:formatDate value="${ model.updateTime }" pattern="yyyy/MM/dd hh:mm" /></td>
					<td>
						<a href="${ base }content/Model/delete?id=${ model.id }" name="delete"
						   class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
						<a href="${ base }content/Model/edit?id=${ model.id }"
						   class="layui-btn layui-btn-xs">编辑</a>
					</td>
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