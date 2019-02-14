<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>添加/修改后台菜单</title>
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

<jfp:JsonDataSource value="{'false':'不显示','true':'显示'}" id="DB1"/>
<c:if test="${ backMenu.id == null }">
	<c:set value="${ base }admin/BackMenu/add" var="save"/>
</c:if>
<c:if test="${ backMenu.id != null }">
	<c:set value="${ base }admin/BackMenu/edit" var="save"/>
</c:if>

<div class="layui-card">
	<div class="layui-card-header">
		<span class="card-title">添加 & 编辑后台菜单</span>
		<c:import url="../menubar.jsp?module=admin" />
	</div>
	<div class="layui-card-body">
		<form:form action="${ save }" method="post" modelAttribute="backMenu" cssClass="layui-form">
			<!-- 防止表单重复提交 -->
			<jfp:token />
			<form:hidden path="id"/>

			<div class="layui-form-item">
				<label class="layui-form-label">上级菜单</label>

				<div class="layui-input-inline">
					<form:select path="parentId">
						<option value="0">= 作为一级菜单 =</option>
						<form:options itemValue="id" itemLabel="name" items="${ backMenus }"/>
					</form:select>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">菜单名称</label>

				<div class="layui-input-block">
					<form:input path="name" autocomplete="off" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">菜单图标</label>

				<div class="layui-input-inline">
					<form:input path="icon" autocomplete="off" class="layui-input"/>
				</div>
				<div class="layui-form-mid layui-word-aux">layui图标对应的 unicode 字符</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">模块名称</label>

				<div class="layui-input-block">
					<form:input path="module" autocomplete="off" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">实体类</label>

				<div class="layui-input-block">
					<form:input path="entity" autocomplete="off" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">方法名</label>

				<div class="layui-input-block">
					<form:input path="action" autocomplete="off" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">附加参数</label>

				<div class="layui-input-block">
					<form:input path="data" autocomplete="off" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">是否显示</label>

				<div class="layui-input-block">
					<form:checkbox path="display" title="显示"/>
				</div>
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
	layui.use(['form', 'element'], function () { });
</script>
</body>
</html>