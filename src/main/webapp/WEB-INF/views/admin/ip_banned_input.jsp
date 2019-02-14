<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>添加/修改禁止IP</title>
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

<c:if test="${ ipBanned.id == null }">
	<c:set value="${ base }admin/IpBanned/add" var="save"/>
</c:if>
<c:if test="${ ipBanned.id != null }">
	<c:set value="${ base }admin/IpBanned/edit" var="save"/>
</c:if>
<div class="layui-card">
	<div class="layui-card-header">
		<span class="card-title">添加 & 编辑禁止IP</span>
		<c:import url="../menubar.jsp?module=admin" />
	</div>
	<div class="layui-card-body">
		<form:form action="${ save }" method="post" modelAttribute="ipBanned"  cssClass="layui-form">

			<!-- 防止表单重复提交 -->
			<jfp:token />
			<form:hidden path="id"/>

			<div class="layui-form-item">
				<label class="layui-form-label">IP地址</label>

				<div class="layui-input-block">
					<form:input path="ip" lay-verify="ip" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">解封时间</label>

				<div class="layui-input-block">
					<form:input path="expireStr" autocomplete="off" class="layui-input"  lay-verify="datetime" readonly="readonly"/>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button lay-submit="" id="submit"
							<c:if test="${ ipBanned.id == null }">
								class="layui-btn layui-btn-disabled" disabled="disabled"
							</c:if>
							<c:if test="${ ipBanned.id != null }">
								class="layui-btn"
							</c:if>
					>提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>

		</form:form>
	</div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
	layui.use(['form', 'laydate', 'element'], function () {
		var $ = layui.jquery, form = layui.form, laydate = layui.laydate;

		laydate.render({elem: '#expireStr', type: 'datetime'});

		form.verify({
			ip: function (value) {
				var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
				var reg = value.match(exp);
				if(reg==null) {
					return "请输入正确的IP地址！";
				}
			}
		});

        $("#ip").change(function () {
            if($.trim(this.value) == ""){
                $("#submit").attr("class", "layui-btn layui-btn-disabled");
                $("#submit").attr("disabled", true);
            } else {
                var id = $("#id").val();
                $.post("${ base }admin/IpBanned/check", {"ip": this.value, "time": new Date()}, function (data) {
                    if (data == "0" || data == id) {
                        $("#submit").attr("class", "layui-btn");
                        $("#submit").removeAttr("disabled");
                    } else {
                        $("#submit").attr("class", "layui-btn layui-btn-disabled");
                        $("#submit").attr("disabled", true);
                        layer.msg('该ip已经被禁用！', {icon: 2});
                    }
                });
            }
        });
	});
</script>
</body>
</html>