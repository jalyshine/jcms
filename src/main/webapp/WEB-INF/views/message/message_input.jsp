<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>发送短消息</title>
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
<c:if test="${ message.id == null }">
	<c:set value="${ base }message/Message/add" var="save"/>
</c:if>
<c:if test="${ message.id != null }">
	<c:set value="${ base }message/Message/edit" var="save"/>
</c:if>
<div class="layui-card">
	<div class="layui-card-header">
		<span class="card-title">发送短消息</span>
		<c:import url="../menubar.jsp?module=message" />
	</div>
	<div class="layui-card-body">
		<form:form action="${ save }" method="post" modelAttribute="message" cssClass="layui-form">

			<!-- 防止表单重复提交 -->
			<jfp:token />
			<form:hidden path="id"/>
			<form:hidden path="siteId"/>

			<div class="layui-form-item">
				<label class="layui-form-label">标题</label>

				<div class="layui-input-block">
					<form:input path="title" lay-verify="required" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">收件人</label>

				<div class="layui-input-block">
					<form:input path="recipient" autocomplete="off" class="layui-input" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">内容</label>

				<div class="layui-input-block">
					<form:textarea path="content" class="layui-textarea"/>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button lay-submit="" id="submit"
							<c:if test="${ message.id == null }">
								class="layui-btn layui-btn-disabled" disabled="disabled"
							</c:if>
							<c:if test="${ message.id != null }">
								class="layui-btn"
							</c:if>
					>提交
					</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>

		</form:form>
	</div>
</div>
<script type="text/javascript" src="${ base }assets/plugins/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['form', 'element'], function () {
		var form = layui.form, $ = layui.$;

		$("#recipient").change(function () {
			if ($.trim(this.value) == "") {
				$("#submit").attr("class", "layui-btn layui-btn-disabled");
				$("#submit").attr("disabled", true);
			} else {
				$.post("${ base }member/Member/check", {"name": this.value, "time": new Date()}, function (data) {
					if (data == "0") {
						$("#submit").attr("class", "layui-btn layui-btn-disabled");
						$("#submit").attr("disabled", true);
						layer.msg('接收人不存在！', {icon: 2});
					} else {
						$("#submit").attr("class", "layui-btn");
						$("#submit").removeAttr("disabled");
					}
				});
			}
		});
	});
</script>
</body>
</html>