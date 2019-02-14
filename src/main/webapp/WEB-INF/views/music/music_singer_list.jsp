<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>歌手管理</title>
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
<div class="layui-card">
	<div class="layui-card-header">
        <span class="card-title">歌手管理</span>
		<c:import url="../menubar.jsp?module=music" />
	</div>
	<div class="layui-card-body">
		<form id="searchForm" action="${ base }music/MusicSinger/list" method="post" class="layui-form">
			<div class="layui-form-item">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<select name="aid">
							<option value="">所属地区</option>
							<c:forEach items="${ areas }" var="area">
								<option value="${ area.id }">${ area.attrValue }</option>
							</c:forEach>
						</select>
					</div>
					<div class="layui-input-inline" style="width: 100px;">
						<select name="sex">
							<option value="">性别</option>
							<c:forEach items="${ sexes }" var="v_sex">
								<option value="${ v_sex.key }">${ v_sex.value }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline" style="width: 150px;">
						<input name="kwd" type="text" placeholder="搜索关键词" class="layui-input" value="${ param.kwd }"/>
					</div>
				</div>
				<div class="layui-inline">
					<input type="hidden" name="ps" value="${ param.ps }">
					<input type="hidden" name="pn" value="${ param.pn }">
					<input type="hidden" name="odr" value="update_time desc">
					<input type="submit" class="layui-btn" value="开始搜索"/>
				</div>
			</div>
		</form>
		<table lay-filter="items" lay-even class="layui-table" lay-size="sm">
			<thead>
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>性别</th>
				<th>地区</th>
				<th>点击量</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ page.list }" var="singer">
				<tr>
					<td>${ singer.id }</td>
					<td>${ singer.name }</td>
					<td>
						<c:choose>
							<c:when test="${ singer.sex == 1 }">男</c:when>
							<c:when test="${ singer.sex == 2 }">女</c:when>
							<c:otherwise>组合</c:otherwise>
						</c:choose>
					</td>
					<td>${ singer.area.attrValue }</td>
					<td>${ singer.hits }</td>
					<td>
						<a href="${ base }music/MusicSinger/edit?id=${ singer.id }"
						   class="layui-btn layui-btn-xs">编辑</a>
						<a href="${ base }music/MusicSinger/delete?id=${ singer.id }" name="delete"
						   class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
						<a href="${ base }music/MusicAlbum/add?sid=${ singer.id }"
						   class="layui-btn layui-btn-xs">添加专辑</a>
						<a href="${ base }music/MusicAlbum/list?sid=${ singer.id }"
						   class="layui-btn layui-btn-xs">管理专辑</a>
						<a href="${ base }music/MusicVideo/add?sid=${ singer.id }"
						   class="layui-btn layui-btn-xs">添加MV</a>
						<a href="${ base }music/MusicVideo/list?sid=${ singer.id }"
						   class="layui-btn layui-btn-xs">管理MV</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
			<tr>
				<td colspan="6" id="pageLine"></td>
			</tr>
			</tfoot>
		</table>
	</div>
</div>

<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
	layui.use(['layer', 'laypage', 'form', 'element'], function () {
		var layer = layui.layer, $ = layui.$, laypage = layui.laypage;

        // 分页
        laypage.render({
            elem: 'pageLine'
            ,count: ${ page.total }
            ,curr: ${ page.pageNum }
            ,limit: 20
            ,layout: ['count', 'prev', 'page', 'next', 'skip']
            ,jump: function(obj, first){
                if(!first){
                    $(":hidden[name='ps']").val(obj.limit);
                    $(":hidden[name='pn']").val(obj.curr);
                    $("#searchForm").submit();
                }
            }
        });

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
