<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MV管理</title>
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
<div class="layui-card">
	<div class="layui-card-header">
        <span class="card-title">MV管理</span>
		<c:if test="${ param.sid != null }">
			<c:import url="music_singer_menubar.jsp?sid=${ param.sid }&entity=MusicVideo&name=MV" />
		</c:if>
		<c:if test="${ param.sid == null }">
            <span class="layui-breadcrumb">
                <a href="javascript:;"><cite>所有MV</cite></a>
            </span>
		</c:if>
	</div>
	<div class="layui-card-body">
		<form id="searchForm" action="${ base }music/MusicVideo/list" method="post" class="layui-form">
			<div class="layui-form-item">
				<div class="layui-inline">
					<div class="layui-input-inline">
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
				<th>MV名称</th>
				<th>所属歌手</th>
				<th>更新时间</th>
				<th>点击量</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ page.list }" var="video">
				<tr>
					<td>${ video.id }</td>
					<td>${ video.title }</td>
					<td>${ video.musicSinger.name }</td>
					<td>
						<fmt:formatDate value="${ video.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>${ video.hits }</td>
					<td>
						<a href="${ base }music/MusicVideo/edit?id=${ video.id }&sid=${ video.musicSinger.id }"
						   class="layui-btn layui-btn-xs">编辑</a>
						<a href="${ base }music/MusicVideo/delete?id=${ video.id }&sid=${ video.musicSinger.id }"
						   name="delete" class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
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
    layui.use(['layer', 'laypage', 'element'], function () {
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
