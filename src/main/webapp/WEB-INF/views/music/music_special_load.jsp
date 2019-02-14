<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>导入音乐</title>
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
        <span class="card-title">导入音乐</span>
		<c:import url="../menubar.jsp?module=music" />
	</div>
	<div class="layui-card-body">
		<form id="searchForm" action="${ base }music/MusicSpecial/load" method="get" class="layui-form">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 60px; text-align: left">更新时间</label>
					<div class="layui-input-inline" style="width: 100px;">
						<input name="stm" type="text" lay-verify="date"
							   autocomplete="off" class="layui-input" id="stm" readonly="readonly"/>
					</div>
					<div class="layui-form-mid">-</div>
					<div class="layui-input-inline" style="width: 100px;">
						<input name="edm" type="text" lay-verify="date"
							   autocomplete="off" class="layui-input" id="edm" readonly="readonly"/>
					</div>
				</div>
				<div class="layui-inline" style="width: 150px;">
					<div class="layui-input-inline" style="width: 150px;">
						<input name="kwd" type="text" placeholder="搜索关键词" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<input type="hidden" name="menu" value="${ param.menu }">
					<input type="hidden" name="id" value="${ param.id }">
					<input type="submit" class="layui-btn" value="开始搜索"/>
				</div>
			</div>
		</form>
		<form action="${ base }music/MusicSpecial/load" method="post" id="operForm" class="layui-form">
			<input type="hidden" name="id" value="${ param.id }">

			<div class="layui-btn-group item-oper layui-inline">
				<button class="layui-btn layui-btn-danger" data-type="load">批量导入内容</button>
			</div>

			<table lay-filter="items" lay-even class="layui-table" lay-size="sm">
				<thead>
				<tr>
					<th style="text-align: center; width:40px;">
						<input type="checkbox" lay-skin="primary" lay-filter="selAll" id="selAll">
					</th>
					<th>ID</th>
					<th>音乐名称</th>
					<th>所属专辑</th>
					<th>所属歌手</th>
					<th>更新时间</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${ page.list }" var="music">
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" name="ids" value="${ music.id }" lay-skin="primary">
						</td>
						<td>${ music.id }</td>
						<td>${ music.title }</td>
						<td>${ music.musicAlbum.title }</td>
						<td>${ music.musicAlbum.musicSinger.name }</td>
						<td>
							<fmt:formatDate value="${ music.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
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
		</form>
	</div>
</div>

<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
	layui.use(['layer', 'form', 'laypage', 'laydate', 'element'], function () {
		var layer = layui.layer, form = layui.form, laydate = layui.laydate,
            laypage = layui.laypage, $ = layui.$;

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

        // 全选
        form.on("checkbox(selAll)", function(data){
            if(data.elem.checked){
                $(":checkbox[name='ids']").prop("checked", true); // 此处不能用attr，否则第二次点击不渲染
            }else{
                $(":checkbox[name='ids']").removeAttr('checked');
            }
            form.render("checkbox");
        });

		// 渲染日期选择
		laydate.render({elem: '#stm', type: 'date'});
		laydate.render({elem: '#edm', type: 'date'});

		$(".item-oper .layui-btn").on("click", function () {
            layer.confirm('确定要' + $(this).text() + '吗？', {icon: 3, title: '提示'}, function (index) {
                $(":checkbox[name='ids']").each(function(i){
                   if(this.checked != true) {
                       $(this).parent().find(":hidden").remove();
				   }
				});
                $("#operForm").submit();
                layer.close(index);
            });
            return false;
		});
	});
</script>
</body>
</html>