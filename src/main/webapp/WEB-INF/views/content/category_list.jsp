<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>栏目管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
	<style type="text/css">
		.lev1{ background-color: #ccc; }
		.lev2{ background-color: #f2f2f2; text-indent: 4em;}
		.lev3{ text-indent: 8em;}
		.lev4{ text-indent: 12em; }
		.sort-text { width: 30px; text-align: center; border: 1px dotted gray; color: gray;}
	</style>
</head>
<body>
<div class="layui-card">
	<div class="layui-card-header">
		<span class="card-title">栏目管理</span>
		<c:import url="../menubar.jsp?module=content" />
	</div>
	<div class="layui-card-body">
		<div class="layui-btn-group category-oper">
			<button class="layui-btn" data-type="sort">重新排序</button>
		</div>
		<form id="operForm" action="" method="post">
			<table class="layui-table" lay-size="sm">
				<thead>
				<tr>
					<th>ID</th>
					<th>栏目名称</th>
					<th>栏目类型</th>
					<th>所属模型</th>
					<th>数据量</th>
					<th>描述</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${ categoryTree }" var="category">
					<tr>
						<td>${ category.id }
						</td>
						<c:if test="${ category.level!=null && category.level > 0 }">
						<td class="lev${ category.level }">
							</c:if>
							<c:if test="${ category.level==null }">
						<td>
							</c:if>
							<c:if test="${ category.level > 1 }" >∟ </c:if>
							<input type="hidden" name="id" value="${ category.id }">
							<input type="text" name="order" value="${ category.listOrder }" class="sort-text">
								${ category.name }
						</td>
						<td>
							<c:choose>
								<c:when test="${ category.type==1 }">内部栏目</c:when>
								<c:when test="${ category.type==2 }">单网页</c:when>
								<c:when test="${ category.type==3 }">外部链接</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:if test="${ category.model != null }">
								${ category.model.name }
							</c:if>
						</td>
						<td></td>
						<td>${ category.description }</td>
						<td>
							<a href="${ base }content/Category/delete?id=${ category.id }" name="delete"
							   class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
							<a href="${ base }content/Category/edit?type=${ category.type }&id=${ category.id }"
							   class="layui-btn layui-btn-xs">编辑</a>
							<a href="${ base }content/Category/add?type=${ category.type }&pid=${ category.id }"
							   class="layui-btn layui-btn-xs">添加子栏目</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
	layui.use(['layer', 'element'], function () {
		var layer = layui.layer, $ = layui.$;

        $(".category-oper .layui-btn").on("click", function () {
            var action = "${ base }content/Category/batch-" + $(this).data('type');
            layer.confirm('确定要' + $(this).text() + '吗？', {icon: 3, title: '提示'}, function (index) {
                $("#operForm").attr("action", action).submit();
                layer.close(index);
            });
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