<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加/修改音乐专题</title>
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
<c:if test="${ musicSpecial.id == null }">
	<c:set value="${ base }music/MusicSpecial/add" var="save"/>
</c:if>
<c:if test="${ musicSpecial.id != null }">
	<c:set value="${ base }music/MusicSpecial/edit" var="save"/>
</c:if>
<div class="layui-card">
	<div class="layui-card-header">
        <span class="card-title">添加 & 编辑音乐专题</span>
		<c:import url="../menubar.jsp?module=music" />
	</div>
	<div class="layui-card-body">
		<form:form action="${ save }" method="post" modelAttribute="musicSpecial" cssClass="layui-form">

			<!-- 防止表单重复提交 -->
			<jfp:token/>
			<form:hidden path="id" value="${ musicSpecial.id }"/>

			<div class="layui-form-item">
				<label class="layui-form-label">类别</label>

				<div class="layui-input-inline">
					<form:select path="typeId" items="${ types }" itemLabel="attrValue" itemValue="id" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">专题名称</label>

				<div class="layui-input-block">
					<form:input path="title" lay-verify="required" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">专题简介</label>

				<div class="layui-input-block">
					<form:textarea path="description" cssClass="layui-textarea"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">焦点图</label>

				<div class="layui-input-block layui-upload">
					<form:hidden path="thumb"/>
					<div class="layui-upload-list">
						<img id="thumb-img" style="height: 100px"
						<c:if test="${ empty musicSpecial.thumb }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty musicSpecial.thumb }">
							src="${ base }${ musicSpecial.thumb }">
						</c:if>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">大海报</label>

				<div class="layui-input-block layui-upload">
					<form:hidden path="banner"/>
					<div class="layui-upload-list">
						<img id="banner-img" style="height: 100px"
						<c:if test="${ empty musicSpecial.banner }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty musicSpecial.banner }">
							src="${ base }${ musicSpecial.banner }">
						</c:if>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">音乐管理</label>

				<div class="layui-input-block">
					<div class="layui-upload">
						<table lay-filter="picTab" lay-size="sm" class="layui-table">
							<thead><tr>
								<th>音乐名称</th>
								<th>所属专辑</th>
								<th>所属歌手</th>
								<th>状态</th>
								<th>操作</th>
							</tr></thead>
							<tbody id="itemList">
							<c:if test="${ musics != null }">
								<c:forEach items="${ musics }" var="item">
									<tr>
										<td>${ item.title }</td>
										<td>${ item.musicAlbum.title }</td>
										<td>${ item.musicAlbum.musicSinger.name }</td>
										<td>已存储</td>
										<td>
											<input type="hidden" name="mid" value="${ item.id }">
											<button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">是否推荐</label>

				<div class="layui-input-block">
					<form:checkbox path="elite" title="推荐"/>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button lay-submit="" id="submit"
							<c:if test="${ musicSpecial.id == null }">
								class="layui-btn layui-btn-disabled" disabled="disabled"
							</c:if>
							<c:if test="${ musicSpecial.id != null }">
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
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ base }assets/js/'
    }).use(['layer', 'form', 'layPost', 'element'], function () {
		var layer = layui.layer, $ = layui.$, layPost = layui.layPost;

        layPost.image('thumb', 'thumb', '${ base }', '${ base }', '', '');
        layPost.image('banner', 'banner', '${ base }', '${ base }', '', '');

		$("button.delete").on("click", function(){
			$(this).parent().parent().remove();
			return false;
		});

		$("#title").change(function () {
			if ($.trim(this.value) == "") {
				$("#submit").attr("class", "layui-btn layui-btn-disabled");
				$("#submit").attr("disabled", true);
			} else {
				var id = $("#id").val();
				$.post("${ base }music/MusicSpecial/check", {"ttl": this.value, "time": new Date()}, function (data) {
					if (data == "0" || data == id) {
						$("#submit").attr("class", "layui-btn");
						$("#submit").removeAttr("disabled");
					} else {
						$("#submit").attr("class", "layui-btn layui-btn-disabled");
						$("#submit").attr("disabled", true);
						layer.msg('该专题名称已被使用！', {icon: 2});
					}
				});
			}
		});
	});
</script>
</body>
</html>