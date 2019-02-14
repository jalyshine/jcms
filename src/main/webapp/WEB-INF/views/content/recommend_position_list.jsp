<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>推荐位管理</title>
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
		<span class="card-title">推荐位管理</span>
		<c:import url="../menubar.jsp?module=content" />
	</div>
	<div class="layui-card-body">
		<form action="${ base }content/RecommendPosition/list" method="get" class="layui-form" id="searchForm">
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
		<table class="layui-table" lay-even lay-size="sm">
			<thead>
			<tr>
				<th>ID</th>
				<th>推荐位名称</th>
				<th>所属栏目</th>
				<th>所属模型</th>
				<th>最大条数</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ page.list }" var="position">
				<tr>
					<td>${ position.id }</td>
					<td>${ position.name }</td>
					<td>
						<c:if test="${ position.categoryId == null }">
							全部
						</c:if>
						<c:if test="${ position.categoryId != null }">
							${ position.category.name }
						</c:if>
					</td>
					<td>
						<c:if test="${ position.modelId == null }">
							全部
						</c:if>
						<c:if test="${ position.modelId != null }">
							${ position.model.name }
						</c:if>
					</td>
					<td>${ position.maxItems }</td>
					<td>
						<a href="${ base }content/RecommendPosition/delete?id=${ position.id }" name="delete"
						   class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
						<a href="${ base }content/RecommendPosition/edit?id=${ position.id }"
						   class="layui-btn layui-btn-xs">编辑</a>
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
	layui.use(['layer','laypage', 'element'], function () {
		var layer = layui.layer, laypage = layui.laypage, $ = layui.$;
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