<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>模板属性</title>
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
        <span class="card-title">模板属性</span>
		<c:import url="../menubar.jsp?module=template" />
	</div>
	<div class="layui-card-body">
		<table class="layui-table" lay-size="sm" lay-even>
			<thead>
			<tr>
				<td style="width: 30px; text-align: center;">ID</td>
				<td>属性名称</td>
				<td>属性值</td>
				<td>操作</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ templateAttributes }" var="attr">
				<tr>
					<td style="text-align: center;">${ attr.id }</td>
					<td>${ attr.attrName }</td>
					<td>${ attr.attrValue }</td>
					<td>
						<a href="${ base }template/TemplateAttribute/edit?name=${ attr.attrName }&id=${ attr.id }"
						   class="layui-btn layui-btn-xs">编辑</a>
						<a href="${ base }template/TemplateAttribute/delete?name=${ attr.attrName }&id=${ attr.id }" name="delete"
						   class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
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
