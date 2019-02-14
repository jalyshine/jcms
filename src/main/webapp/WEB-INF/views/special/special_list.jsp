<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>专题管理</title>
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

<form action="${ base }special/Special/list" method="get" class="layui-form" id="searchForm">
	<input type="hidden" name="ps" value="${ param.ps }">
	<input type="hidden" name="pn" value="${ param.pn }">
	<input type="hidden" name="odr" value="update_time desc"> 
</form>

<div class="layui-card">
	<div class="layui-card-header">
		<span class="card-title">专题管理</span>
		<c:import url="../menubar.jsp?module=special" />
	</div>
	<div class="layui-card-body">
		<table lay-filter="items" lay-even class="layui-table" lay-size="sm">
			<thead>
			<tr>
				<th>ID</th>
				<th>专题名称</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ page.list }" var="special">
				<tr>
					<td>${ special.id }</td>
					<td>${ special.title }</td>
					<td>
						<fmt:formatDate value="${ special.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<a href="${ base }special/SpecialComment/list?id=${ special.id }"
						   class="layui-btn layui-btn-xs">评论</a>
						<a href="${ base }special/Special/delete?id=${ special.id }"
						   name="delete" class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
						<a href="${ base }special/Special/edit?id=${ special.id }"
						   class="layui-btn layui-btn-xs">编辑</a>
						<a href="${ base }special/SpecialContent/add?sid=${ special.id }"
						   class="layui-btn layui-btn-xs">添加内容</a>
						<a href="${ base }special/SpecialContent/list?sid=${ special.id }"
						   class="layui-btn layui-btn-xs">管理内容</a>
						<a href="${ base }special/SpecialContent/load?sid=${ special.id }"
						   class="layui-btn layui-btn-xs">导入内容</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
			<tr>
				<td colspan="4" id="pageLine"></td>
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