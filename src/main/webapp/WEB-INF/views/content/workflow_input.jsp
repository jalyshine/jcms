<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>添加/修改工作流</title>
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
<jfp:JsonDataSource value="{'1':'一级审核','2':'二级审核','3':'三级审核','4':'四级审核'}" id="DB1"/>
<c:if test="${ workFlow.id == null }">
	<c:set value="${ base }content/WorkFlow/add" var="save"/>
</c:if>
<c:if test="${ workFlow.id != null }">
	<c:set value="${ base }content/WorkFlow/edit" var="save"/>
</c:if>
<div class="layui-card">
	<div class="layui-card-header">
		<span class="card-title">添加 & 编辑工作流</span>
		<c:import url="../menubar.jsp?module=content" />
	</div>
	<div class="layui-card-body">
		<form:form action="${ save }" method="post" modelAttribute="workFlow" cssClass="layui-form">

			<!-- 防止表单重复提交 -->
			<jfp:token />
			<form:hidden path="id"/>
			<form:hidden path="siteId"/>
			<div class="layui-form-item">
				<label class="layui-form-label">审核级数</label>

				<div class="layui-input-inline">
					<form:select path="steps" items="${ DB1 }" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">工作流名称</label>

				<div class="layui-input-block">
					<form:input path="name" autocomplete="off" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">工作流描述</label>

				<div class="layui-input-block">
					<form:textarea path="description" autocomplete="off" class="layui-textarea"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">允许修改</label>

				<div class="layui-input-inline">
					<form:checkbox path="allowModify" title="允许" />
				</div>
				<div class="layui-form-mid layui-word-aux">审核状态下，是否允许修改</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button lay-submit="" id="submit" class="layui-btn" >提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
	layui.use(['form', 'element'], function () {	});
</script>
</body>
</html>