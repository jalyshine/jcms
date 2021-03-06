<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../account.jsp" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>来源管理</title>
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
		<span class="card-title">来源管理</span>
		<c:import url="../menubar.jsp?module=content" />
	</div>
	<div class="layui-card-body">
		<form action="${ base }content/CopyFrom/list" method="get" class="layui-form" id="searchForm">
			<div class="layui-form-item">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input name="kwd" type="text" placeholder="搜索关键词" class="layui-input" value="${ param.kwd }" autocomplete="off"/>
					</div>
					<div class="layui-input-inline" style="width: 100px;">
						<input type="hidden" name="ps" value="${ param.ps }">
						<input type="hidden" name="pn" value="${ param.pn }">
						<input type="submit" class="layui-btn" value="开始搜索"/>
					</div>
				</div>
			</div>
		</form>
		<table lay-filter="items" lay-even class="layui-table" lay-size="sm">
			<thead>
			<tr>
				<th>ID</th>
				<th>来源名称</th>
				<th>来源链接</th>
				<th>缩略图</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ page.list }" var="copyFrom">
				<tr>
					<td>${ copyFrom.id }</td>
					<td>${ copyFrom.name }</td>
					<td>${ copyFrom.url }</td>
					<td>
						<c:if test="${ copyFrom.thumb == '' }">-</c:if>
						<c:if test="${ copyFrom.thumb != '' }">
							<img id="thumb-img" style="width: 80px;" src="${ host }/${ copyFrom.thumb }">
						</c:if>
					</td>
					<td>
						<a href="${ base }content/CopyFrom/edit?id=${ copyFrom.id }"
						   class="layui-btn layui-btn-xs">编辑</a>
						<a href="${ base }content/CopyFrom/delete?id=${ copyFrom.id }" name="delete"
						   class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
			<tr>
				<td colspan="5" id="pageLine"></td>
			</tr>
			</tfoot>
		</table>
	</div>
</div>

<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['layer','laypage', 'element'], function () {
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