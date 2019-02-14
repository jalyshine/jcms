<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加/修改歌手</title>
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
<jfp:JsonDataSource value="{'1':'男','2':'女','3':'组合'}" id="sexes"/>

<c:if test="${ musicSinger.id == null }">
	<c:set value="${ base }music/MusicSinger/add" var="save"/>
</c:if>
<c:if test="${ musicSinger.id != null }">
	<c:set value="${ base }music/MusicSinger/edit" var="save"/>
</c:if>
<div class="layui-card">
	<div class="layui-card-header">
        <span class="card-title">添加 & 编辑歌手</span>
		<c:import url="../menubar.jsp?module=music" />
	</div>
	<div class="layui-card-body">
		<form:form action="${ save }" method="post" modelAttribute="musicSinger" cssClass="layui-form">

			<!-- 防止表单重复提交 -->
			<jfp:token/>
			<form:hidden path="id" value="${ musicSinger.id }" />

			<div class="layui-form-item">
				<label class="layui-form-label">所属地区</label>

				<div class="layui-input-inline">
					<form:select path="areaId" items="${ areas }" itemLabel="attrValue" itemValue="id" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">歌手姓名</label>

				<div class="layui-input-block">
					<form:input path="name" lay-verify="required" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">性别</label>

				<div class="layui-input-inline">
					<form:select items="${ sexes }" path="sex" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">歌手简介</label>

				<div class="layui-input-block">
					<form:textarea path="description" cssClass="layui-textarea"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">是否推荐</label>

				<div class="layui-input-block">
					<form:checkbox path="elite" title="推荐"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">歌手头像</label>

				<div class="layui-input-block layui-upload">
					<form:hidden path="face"/>
					<div class="layui-upload-list">
						<img id="face-img" style="height: 100px"
						<c:if test="${ empty musicSinger.face }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty musicSinger.face }">
							src="${ base }${ musicSinger.face }">
						</c:if>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button lay-submit="" id="submit"
							<c:if test="${ musicSinger.id == null }">
								class="layui-btn layui-btn-disabled" disabled="disabled"
							</c:if>
							<c:if test="${ musicSinger.id != null }">
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
    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'layPost', 'element'], function () {
		var layPost = layui.layPost, $ = layui.$;

        layPost.image('face', 'icon', '${ base }', '${ base }', '', '');

        $("#name").change(function () {
			if ($.trim(this.value) == "") {
				$("#submit").attr("class", "layui-btn layui-btn-disabled");
				$("#submit").attr("disabled", true);
			} else {
				var id = $("#id").val();
				$.post("${ base }music/MusicSinger/check", {"ttl": this.value, "time": new Date()}, function (data) {
					if (data == "0" || data == id) {
						$("#submit").attr("class", "layui-btn");
						$("#submit").removeAttr("disabled");
					} else {
						$("#submit").attr("class", "layui-btn layui-btn-disabled");
						$("#submit").attr("disabled", true);
						layer.msg('该歌手已存在！', {icon: 2});
					}
				});
			}
		});
	});
</script>
</body>
</html>