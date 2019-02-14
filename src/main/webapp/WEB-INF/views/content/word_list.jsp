<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>文档管理</title>
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
		<span class="card-title">文档管理</span>
		<c:import url="content_menubar.jsp">
			<c:param name="et" value="Word" />
			<c:param name="etn" value="文档" />
			<c:param name="cid" value="${ category.id }" />
			<c:param name="odr" value="${ param.odr }" />
		</c:import>
	</div>
	<div class="layui-card-body">
		<form id="searchForm" action="${ base }content/Word/list" method="get" class="layui-form">
			<div class="layui-form-item">
				<div class="layui-input-inline">
					<input name="kwd" type="text" placeholder="搜索关键词" class="layui-input" value="${ param.kwd }"/>
				</div>
				<div class="layui-inline">
					<input type="hidden" name="ps" value="${ param.ps }">
					<input type="hidden" name="pn" value="${ param.pn }">
					<input type="hidden" name="cid" value="${ category.id }">
					<input type="submit" class="layui-btn" value="开始搜索"/>
				</div>
			</div>
		</form>
		<div class="layui-btn-group item-oper">
			<button class="layui-btn layui-btn-danger" data-type="delete">批量删除文档</button>
		</div>
		<form action="" method="post" id="operForm" class="layui-form">
			<input type="hidden" name="odr" value="${ param.odr }">
			<input type="hidden" name="cid" value="${ category.id }">
			<table lay-filter="items" lay-even class="layui-table" lay-size="sm">
				<thead>
				<tr>
					<th style="text-align: center; width:40px;">
						<input type="checkbox" lay-skin="primary" lay-filter="selAll" id="selAll">
					</th>
					<th style="width: 50px;">ID</th>
					<th>标题</th>
					<th style="width: 150px;">发布时间</th>
					<th style="width: 150px;">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${ page.list }" var="word">
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" name="ids" value="${ word.id }" lay-skin="primary">
						</td>
						<td>${ word.id }</td>
						<td>${ word.title }</td>
						<td>${ word.publishTimeStr }</td>
						<td>
							<a href="${ base }content/Word/edit?id=${ word.id }&cid=${ word.categoryId }"
							   class="layui-btn layui-btn-xs">编辑</a>
							<a href="javascript:;" lay-data="${ word.id }" name="delete"
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
		</form>
	</div>
</div>
<form action="${ base }content/Word/delete" method="post" id="delForm">
	<input type="hidden" name="odr" value="${ param.odr }">
	<input type="hidden" name="cid" value="${ category.id }">
	<input type="hidden" name="id">
</form>

<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
	layui.use(['layer', 'form', 'laypage', 'element'], function () {
		var layer = layui.layer, form = layui.form,
            laypage = layui.laypage, $ = layui.$;

        // 全选
        form.on("checkbox(selAll)", function(data){
            if(data.elem.checked){
                $(":checkbox[name='ids']").prop("checked", true); // 此处不能用attr，否则第二次点击不渲染
            }else{
                $(":checkbox[name='ids']").removeAttr('checked');
            }
            form.render("checkbox");
        })

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
            var id = $(this).attr("lay-data");
            layer.confirm('删除数据不可恢复，是否确定？', {icon: 3, title: '提示'}, function (index) {
                $("#delForm :hidden[name='id']").val(id);
                $("#delForm").submit();
            });
		});

		$(".item-oper .layui-btn").on("click", function () {
            var action = "${ base }content/Word/batch-" + $(this).data('type');
            layer.confirm('确定要' + $(this).text() + '吗？', {icon: 3, title: '提示'}, function (index) {
                $("#operForm").attr("action",action).submit();
                layer.close(index);
            });
		});
	});
</script>
</body>
</html>