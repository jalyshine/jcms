<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加/修改MV</title>
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
<c:if test="${ musicVideo.id == null }">
	<c:set value="${ base }music/MusicVideo/add" var="save"/>
</c:if>
<c:if test="${ musicVideo.id != null }">
	<c:set value="${ base }music/MusicVideo/edit" var="save"/>
</c:if>
<div class="layui-card">
	<div class="layui-card-header">
        <span class="card-title">添加 & 编辑MV</span>
		<c:import url="music_singer_menubar.jsp?sid=${ param.sid }&entity=MusicVideo&name=MV" />
	</div>
	<div class="layui-card-body">
		<form:form action="${ save }" method="post" modelAttribute="musicVideo" cssClass="layui-form">
			<!-- 防止表单重复提交 -->
			<jfp:token/>
			<form:hidden path="id" value="${ musicVideo.id }" />
			<!-- 所属歌手  -->
			<form:hidden path="musicSingerId" value="${ musicSinger.id }"/>
			<input type="hidden" name="menu" value="${ param.menu }">

			<div class="layui-form-item">
				<label class="layui-form-label">所属歌手</label>

				<div class="layui-input-inline">
					<label class="layui-form-label" style="background-color: #E6E6E6; text-align: center;">
							${ musicSinger.name }
					</label>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">MV名称</label>

				<div class="layui-input-block">
					<form:input path="title" lay-verify="required" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">时长（秒）</label>

				<div class="layui-input-inline">
					<form:input path="timeLength" lay-verify="number" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">MV地址</label>

				<div class="layui-input-block layui-upload">
					<button type="button" class="layui-btn" id="mv-btn">上传MV</button>
					<div class="layui-input-inline">
						<form:input path="url" autocomplete="off" class="layui-input"/>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">MV简介</label>

				<div class="layui-input-block">
					<form:textarea path="description" autocomplete="off" class="layui-textarea"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">发行日期</label>

				<div class="layui-input-inline">
					<form:input path="showDateStr" autocomplete="off" class="layui-input" readonly="readonly" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">焦点图</label>

				<div class="layui-input-block layui-upload">
					<form:hidden path="thumb"/>
					<div class="layui-upload-list">
						<img id="thumb-img" style="height: 100px"
						<c:if test="${ empty musicVideo.thumb }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty musicVideo.thumb }">
							src="${ base }${ musicVideo.thumb }">
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
						<c:if test="${ empty musicVideo.banner }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty musicVideo.banner }">
							src="${ base }${ musicVideo.banner }">
						</c:if>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">是否推荐</label>

				<div class="layui-input-inline">
					<form:checkbox path="elite" title="推荐"/>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button lay-submit="" id="submit"
							<c:if test="${ musicVideo.id == null }">
								class="layui-btn layui-btn-disabled" disabled="disabled"
							</c:if>
							<c:if test="${ musicVideo.id != null }">
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
    }).use(['layer', 'form',  'laydate', 'layPost', 'element'], function () {
		var layer = layui.layer, $ = layui.$, laydate = layui.laydate,
            upload = layui.upload, layPost = layui.layPost;

		laydate.render({elem: '#showDateStr', type: 'date'});

        layPost.image('thumb', 'thumb', '${ base }', '${ base }', '', '');
        layPost.image('banner', 'banner', '${ base }', '${ base }', '', '');

		upload.render({
			elem: '#mv-btn'
			, url: '${ base }upload'
            , accept: 'video'
			, done: function (res) {
				if (res.code != 0) {
					return layer.msg('上传失败');
				} else {
					$("#url").val(res.data.src);
					return layer.msg('上传成功');
				}
			} , error: function () {
				layer.alert("上传失败，请检查网络！");
			}
		});


		$("#title").change(function () {
			if ($.trim(this.value) == "") {
				$("#submit").attr("class", "layui-btn layui-btn-disabled");
				$("#submit").attr("disabled", true);
			} else {
				var id = $("#id").val();
				$.post("${ base }music/MusicVideo/check", {"ttl": this.value, "time": new Date()}, function (data) {
					if (data == "0" || data == id) {
						$("#submit").attr("class", "layui-btn");
						$("#submit").removeAttr("disabled");
					} else {
						$("#submit").attr("class", "layui-btn layui-btn-disabled");
						$("#submit").attr("disabled", true);
						layer.msg('该MV已存在！', {icon: 2});
					}
				});
			}
		});
	});
</script>
</body>
</html>