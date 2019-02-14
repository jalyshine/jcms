<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>后台操作日志</title>
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
<form action="${ base }admin/Logs/list" method="get" class="layui-form" id="searchForm">
	<input type="hidden" name="ps" value="${ param.ps }">
	<input type="hidden" name="pn" value="${ param.pn }">
</form>

<div class="layui-card">
	<div class="layui-card-header">
		<span class="card-title">后台操作日志</span>
	</div>
	<div class="layui-card-body">
		<div class="layui-btn-group logs-oper">
			<button class="layui-btn layui-btn-danger" data-type="delete">批量删除</button>
		</div>
		<form action="${ base }admin/Logs/delete" method="post" id="operForm" class="layui-form">
			<table lay-filter="items" lay-even class="layui-table" lay-size="sm">
				<thead>
				<tr>
					<th style="text-align: center; width:40px;">
						<input type="checkbox" lay-skin="primary" lay-filter="selAll" id="selAll">
					</th>
					<th>地址</th>
					<th>参数</th>
					<th>站点</th>
					<th>IP</th>
					<th>管理员</th>
					<th>时间</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${ page.list }" var="log">
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" name="ids" value="${ log.id }" lay-skin="primary">
						</td>
						<td>${ log.uri }</td>
						<td>${ log.data }</td>
						<td>${ log.site.name }</td>
						<td>${ log.ip }</td>
						<td>${ log.admin.userName }</td>
						<td><fmt:formatDate value="${ log.time }" type="both" /></td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
				<tr>
					<td colspan="7" id="pageLine"></td>
				</tr>
				</tfoot>
			</table>
		</form>
	</div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['layer', 'form', 'laypage', 'element'], function () {
        var layer = layui.layer, form = layui.form, laypage = layui.laypage, $ = layui.$;

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

        $(".logs-oper .layui-btn").on("click", function () {
            layer.confirm('确定要删除所选日志吗？', {icon: 3, title: '提示'}, function (index) {
                $("#operForm").submit();
                layer.close(index);
            });
        });
    });
</script>
</body>
</html>