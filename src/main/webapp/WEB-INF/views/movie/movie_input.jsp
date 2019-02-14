<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加/修改影片</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <style type="text/css">
		.desc-box{ width: 100%; border: 0; color: gray; font-size: 10pt; background-color: transparent; }
	</style>
</head>
<body>
<jfp:JsonDataSource value="{'1':'高清','2':'标清','3':'流畅'}" id="qualitys" />

<c:if test="${ movie.id == null }">
	<c:set value="${ base }movie/Movie/add" var="save"/>
</c:if>
<c:if test="${ movie.id != null }">
	<c:set value="${ base }movie/Movie/edit" var="save"/>
</c:if>
<div class="layui-card">
	<div class="layui-card-header">
        <span class="card-title">添加 & 编辑影片</span>
		<c:import url="../menubar.jsp?module=movie" />
	</div>
	<div class="layui-card-body">
		<form:form action="${ save }" method="post" modelAttribute="movie" cssClass="layui-form">
			<!-- 防止表单重复提交 -->
			<jfp:token/>
			<form:hidden path="id" value="${ movie.id }" />

			<div class="layui-form-item">
				<label class="layui-form-label">类别</label>

				<div class="layui-input-inline">
					<form:select path="typeId" items="${ types }" itemLabel="attrValue" itemValue="id" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">影片名称</label>

				<div class="layui-input-block">
					<form:input path="title" lay-verify="required" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">影片画质</label>

				<div class="layui-input-inline">
					<form:select path="quality" items="${ qualitys }" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">上映日期</label>

				<div class="layui-input-inline">
					<form:input path="showDateStr" autocomplete="off" class="layui-input" readonly="readonly" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">公共路径</label>

				<div class="layui-input-inline">
					<form:select path="movieData.commonPath" items="${ paths }" itemValue="attrValue" itemLabel="attrValue"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">影片管理</label>

				<div class="layui-input-block">
					<div class="layui-upload">
						<button type="button" class="layui-btn layui-btn-normal" id="addItem">添加条目</button>
						<button type="button" class="layui-btn layui-btn-danger" id="clearItem">清空</button>
						<div class="layui-input-inline" style="width: 60px;">
							<input type="text" autocomplete="off" class="layui-input" value="3" id="itemCount"/>
						</div>
						<div class="layui-upload-list">
							<table lay-filter="picTab" lay-size="sm" class="layui-table">
								<thead><tr>
									<th>文件名</th>
									<th>地址一</th>
									<th>地址二</th>
									<th>操作</th>
								</tr></thead>
								<tbody id="itemList">
								<c:if test="${ movie.id != null }">
									<c:forEach items="${ movie.movieData.movieItems }" var="item">
										<tr>
											<td><input type="text" name="name" value="${ item.name }" class="desc-box"></td>
											<td><input type="text" name="url1" value="${ item.url1 }" class="desc-box"></td>
											<td><input type="text" name="url2" value="${ item.url2 }" class="desc-box"></td>
											<td><button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button></td>
										</tr>
									</c:forEach>
								</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">所属地区</label>

				<div class="layui-input-inline">
					<form:select path="areaId" items="${ areas }" itemLabel="attrValue" itemValue="id"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">导演</label>

				<div class="layui-input-block">
					<form:input path="director" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">主要演员</label>

				<div class="layui-input-block">
					<form:input path="actor" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">影片简介</label>

				<div class="layui-input-block">
					<form:textarea path="description" autocomplete="off" class="layui-textarea"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">影片时长</label>

				<div class="layui-input-inline">
					<form:input path="timeLength" lay-verify="number" autocomplete="off" class="layui-input"/>
				</div>
				<div class="layui-form-mid layui-word-aux">分钟</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">焦点图</label>

				<div class="layui-input-block layui-upload">
					<form:hidden path="thumb"/>
					<div class="layui-upload-list">
						<img id="thumb-img" style="height: 100px"
						<c:if test="${ empty movie.thumb }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty movie.thumb }">
							src="${ base }${ movie.thumb }">
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
						<c:if test="${ empty movie.banner }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty movie.banner }">
							src="${ base }${ movie.banner }">
						</c:if>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">允许评论</label>

				<div class="layui-input-inline">
					<form:checkbox path="movieData.allowComment" title="允许"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">观看收费</label>

				<div class="layui-input-inline">
					<form:input path="movieData.readPoint" autocomplete="off" lay-verify="number" class="layui-input"/>
				</div>
				<div class="layui-form-mid layui-word-aux">点/元</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button lay-submit="" id="submit"
							<c:if test="${ movie.id == null }">
								class="layui-btn layui-btn-disabled" disabled="disabled"
							</c:if>
							<c:if test="${ movie.id != null }">
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
		var layer = layui.layer, $ = layui.$, layPost = layui.layPost,
			laydate = layui.laydate;

		laydate.render({elem: '#showDateStr', type: 'date'});

        layPost.image('thumb', 'thumb', '${ base }', '${ base }', '', '');
        layPost.image('banner', 'banner', '${ base }', '${ base }', '', '');

		$("button.delete").on("click", function(){
			$(this).parent().parent().remove();
			return false;
		});

		// 添加条目
		$("#addItem").on("click", function(){
			var count = parseInt($("#itemCount").val());
			for(var i=0; i<count; i++){
				var tr = $(['<tr><td><input type="text" name="name" class="desc-box" value=" "></td>'
					,'<td><input type="text" name="url1" class="desc-box" value=" "></td>'
					,'<td><input type="text" name="url2" class="desc-box" value=" "></td>'
					,'<td><button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button></td>'
					,'</tr>'].join(''));
				//删除
				tr.find('.delete').on('click', function(){
					tr.remove();
				});
				$('#itemList').append(tr);
			}
		});

		// 清空条目
		$("#clearItem").on("click", function(){
			$('#itemList tr').remove();
		});

		$("#title").change(function () {
			if ($.trim(this.value) == "") {
				$("#submit").attr("class", "layui-btn layui-btn-disabled");
				$("#submit").attr("disabled", true);
			} else {
				var id = $("#id").val();
				$.post("${ base }movie/Movie/check", {"ttl": this.value, "time": new Date()}, function (data) {
					if (data == "0" || data == id) {
						$("#submit").attr("class", "layui-btn");
						$("#submit").removeAttr("disabled");
					} else {
						$("#submit").attr("class", "layui-btn layui-btn-disabled");
						$("#submit").attr("disabled", true);
						layer.msg('该影片已存在！', {icon: 2});
					}
				});
			}
		});
	});
</script>
</body>
</html>