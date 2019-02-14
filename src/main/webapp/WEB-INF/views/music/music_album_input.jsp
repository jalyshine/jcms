<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>音乐专辑</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="${ base }assets/plugins/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <style type="text/css">
		.desc-box{ width: 100%; border: 0; color: gray; font-size: 10pt; background-color: transparent; }
	</style>
</head>
<body>
<c:if test="${ musicAlbum.id == null }">
	<c:set value="${ base }music/MusicAlbum/add" var="save"/>
</c:if>
<c:if test="${ musicAlbum.id != null }">
	<c:set value="${ base }music/MusicAlbum/edit" var="save"/>
</c:if>
<div class="layui-card">
	<div class="layui-card-header">
        <span class="card-title">添加 & 编辑音乐专辑</span>
		<c:import url="music_singer_menubar.jsp?sid=${ param.sid }&entity=MusicAlbum&name=专辑" />
	</div>
	<div class="layui-card-body">
		<form:form action="${ save }" method="post" modelAttribute="musicAlbum" cssClass="layui-form">

			<!-- 防止表单重复提交 -->
			<jfp:token/>
			<form:hidden path="id" value="${ musicAlbum.id }" />
			<!-- 所属歌手  -->
			<form:hidden path="musicSingerId" value="${ musicSinger.id }"/>

			<div class="layui-form-item">
				<label class="layui-form-label">所属歌手</label>

				<div class="layui-input-inline">
					<label class="layui-form-label" style="background-color: #E6E6E6; text-align: center;">
							${ musicSinger.name }
					</label>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">专辑名称</label>

				<div class="layui-input-block">
					<form:input path="title" lay-verify="required" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">专辑简介</label>

				<div class="layui-input-block">
					<form:textarea path="description" cssClass="layui-textarea"/>
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
						<c:if test="${ empty musicAlbum.thumb }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty musicAlbum.thumb }">
							src="${ base }${ musicAlbum.thumb }">
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
						<c:if test="${ empty musicAlbum.banner }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty musicAlbum.banner }">
							src="${ base }${ musicAlbum.banner }">
						</c:if>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">音乐管理</label>

				<div class="layui-input-block">
					<div class="layui-upload">
						<button type="button" class="layui-btn layui-btn-normal" id="addItem">添加音乐</button>
						<button type="button" class="layui-btn" id="uploadItem">开始上传</button>
						<div class="layui-upload-list">
							<table lay-filter="picTab" lay-size="sm" class="layui-table">
								<thead><tr>
									<th>名称</th>
									<th>大小</th>
									<th>状态</th>
									<th>操作</th>
								</tr></thead>
								<tbody id="itemList">
								<c:if test="${ musicAlbum.id != null }">
									<c:forEach items="${ musics }" var="item">
										<tr>
											<td>
												<input type="text" name="ttl" value="${ item.title }" autocomplete="off" class="desc-box" />
												<input type="hidden" name="adr" value="${ item.url }">
												<input type="hidden" name="mid" value="${ item.id }">
											</td>
											<td>
												<fmt:formatNumber value="${ item.size/(1024*1024) }" pattern="#,#00.0#"/>MB
												<input type="hidden" name="size" value="${ item.size }">
											</td>
											<td>已存储</td>
											<td>
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
							<c:if test="${ musicAlbum.id == null }">
								class="layui-btn layui-btn-disabled" disabled="disabled"
							</c:if>
							<c:if test="${ musicAlbum.id != null }">
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
    }).use(['layer', 'form', 'laydate', 'layPost', 'element'], function () {
		var layer = layui.layer, $ = layui.$, layPost = layui.layPost,
            laydate = layui.laydate, upload = layui.upload;

		laydate.render({elem: '#showDateStr', type: 'date'});

        layPost.image('thumb', 'thumb', '${ base }', '${ base }', '', '');
        layPost.image('banner', 'banner', '${ base }', '${ base }', '', '');

		$("button.delete").on("click", function(){
			var $tr = $(this).parent().parent().remove();
			return false;
		});

		var itemListView = $('#itemList')
				,uploadListIns = upload.render({
					elem: '#addItem'
					,url: '${ base }upload'
					,accept: 'file'
					,multiple: true
					,auto: false
					,bindAction: '#uploadItem'
					,choose: function(obj){
						var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
						//读取本地文件
						obj.preview(function(index, file, result){
							var tr = $(['<tr id="upload-'+ index +'">'
								,'<td><input type="text" name="ttl" class="desc-box" value="' + file.name + '">'
								,'<input type="hidden" name="adr"><input type="hidden" name="mid" value="0" /></td>'
								,'<td>'+ (file.size/(1024*1024)).toFixed(1) +'MB'
								,'<input type="hidden" name="size" value="'+ file.size +'"></td>'
								,'<td>等待上传</td>'
								,'<td><button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button>'
								,'<button class="layui-btn layui-btn-xs reload layui-hide">重传</button></td>'
								,'</tr>'].join(''));

							//单个重传
							tr.find('.reload').on('click', function(){
								obj.upload(index, file);
							});

							//删除
							tr.find('.delete').on('click', function(){
								delete files[index]; //删除对应的文件
								tr.remove();
								uploadListIns.config.elem.next()[0].value = ''; //清空 input explorer 值，以免删除后出现同名文件不可选
							});

							itemListView.append(tr);
						});
					}
					,done: function(res, index, upload){
						if(res.code == 0){ //上传成功
							var tr = itemListView.find('tr#upload-'+ index)
									,tds = tr.children();
							tds.eq(0).find(":hidden[name='adr']").val(res.data.src);
							tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
							tds.eq(3).html(''); //清空操作
							return delete this.files[index]; //删除文件队列已经上传成功的文件
						}
						this.error(index, upload);
					}
					,error: function(index, upload){
						var tr = itemListView.find('tr#upload-'+ index)
								,tds = tr.children();
						tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
						tds.eq(3).find('.reload').removeClass('layui-hide'); //显示重传
					}
				});

		$("#title").change(function () {
			if ($.trim(this.value) == "") {
				$("#submit").attr("class", "layui-btn layui-btn-disabled");
				$("#submit").attr("disabled", true);
			} else {
				var id = $("#id").val();
				$.post("${ base }music/MusicAlbum/check", {"ttl": this.value, "time": new Date()}, function (data) {
					if (data == "0" || data == id) {
						$("#submit").attr("class", "layui-btn");
						$("#submit").removeAttr("disabled");
					} else {
						$("#submit").attr("class", "layui-btn layui-btn-disabled");
						$("#submit").attr("disabled", true);
						layer.msg('该专辑已存在！', {icon: 2});
					}
				});
			}
		});
	});
</script>
</body>
</html>