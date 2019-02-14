<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>附件管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <style type="text/css">
        .icon-span{ width: 25px; float: left; }
        .icon-span i{ font-size: 10pt; color: black }
    </style>
</head>
<body>
<jfp:JsonDataSource id="types" value="{'icon':'图标头像','thumb':'缩略图',
                    'banner':'大型横幅', 'pic':'图片集', 'ill':'文章插图', 'file':'其他文件'}"/>
<div class="layui-card">
	<div class="layui-card-header">
		<span class="card-title">附件管理</span>
	</div>
	<div class="layui-card-body">
        <%--搜索附件--%>
		<form id="searchForm" action="${ base }content/Attachment/list" method="get" class="layui-form">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 60px; text-align: left">上传时间</label>
					<div class="layui-input-inline" style="width: 100px;">
						<input name="stm" type="text" value="${ param.stm }" lay-verify="date"
							   autocomplete="off" class="layui-input" id="stm" readonly="readonly"/>
					</div>
					<div class="layui-form-mid">-</div>
					<div class="layui-input-inline" style="width: 100px;">
						<input name="edm" type="text" value="${ param.edm }" lay-verify="date"
							   autocomplete="off" class="layui-input" id="edm" readonly="readonly"/>
					</div>
				</div>
                <div class="layui-inline" style="width: 150px;">
                    <div class="layui-input-inline" style="width: 150px;">
                        <select name="type">
                            <option value="">文件类型</option>
                            <c:forEach items="${ types }" var="t">
                                <option value="${ t.key }"
                                    <c:if test="${ param.type == t.key }">selected</c:if>
                                >${ t.value }</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
				<div class="layui-inline" style="width: 150px;">
					<div class="layui-input-inline" style="width: 150px;">
						<input name="kwd" type="text" placeholder="搜索关键词" class="layui-input" value="${ param.kwd }"/>
					</div>
				</div>
				<div class="layui-inline" style="width: 150px;">
					<select name="odr">
						<option value="">排序规则</option>
						<option value="upload_time desc">最近上传</option>
						<option value="upload_time asc">最早上传</option>
					</select>
				</div>
				<div class="layui-inline">
					<input type="hidden" name="ps" value="${ param.ps }">
					<input type="hidden" name="pn" value="${ param.pn }">
					<input type="submit" class="layui-btn" value="开始搜索"/>
				</div>
			</div>
		</form>
        <%--批量操作--%>
		<div class="layui-btn-group item-oper">
			<button class="layui-btn layui-btn-danger" data-type="delete">批量删除附件</button>
		</div>
        <%--附件管理--%>
		<form action="" method="post" id="operForm" class="layui-form">
			<input type="hidden" name="odr" value="${ param.odr }">
			<table lay-filter="items" lay-even class="layui-table" lay-size="sm">
				<thead>
				<tr>
					<th style="text-align: center; width:40px;">
						<input type="checkbox" lay-skin="primary" lay-filter="selAll" id="selAll">
					</th>
					<th style="width: 50px;">ID</th>
					<th style="width: 80px;">所属模块</th>
					<th style="width: 80px;">所属栏目</th>
					<th>文件名称</th>
					<th style="width: 50px;">附件大小</th>
					<th style="width: 150px;">上传时间</th>
					<th style="width: 120px;">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${ page.list }" var="attach">
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" name="ids" value="${ attach.id }" lay-skin="primary">
						</td>
						<td>${ attach.id }</td>
						<td>
                            <c:if test="${ attach.attachIndex != null }">
                                ${ attach.attachIndex.module }
                            </c:if>
                        </td>
						<td>
                            <c:if test="${ attach.attachIndex != null }">
                                ${ attach.attachIndex.category.name }
                            </c:if>
                        </td>
						<td>
                            <span class="icon-span">
                                <c:if test="${ attach.type != null && attach.type != 'explorer' }">
                                    <i class="layui-icon">&#xe64a;</i>
                                </c:if>
                            </span>
                            <span class="icon-span">
                                <c:if test="${ attach.attachIndex != null }">
                                    <i class="layui-icon">&#xe64c;</i>
                                </c:if>
                            </span>
                                ${ attach.name }
                        </td>
						<td>
                            ${ attach.size }KB
                        </td>
						<td>
							<fmt:formatDate value="${ attach.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a href="${ base }content/Attachment/delete?id=${ attach.id }" name="delete"
							   class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
                            <c:if test="${ attach.type != 'explorer' }">
                                <a href="javascript:;" data-url="${ attach.path }" data-type="${ attach.type }"
                                   name="view" class="layui-btn layui-btn-xs">预览</a>
                            </c:if>
                            <c:if test="${ attach.type == 'explorer' }">
                                <a href="javascript:;"
                                   name="view" class="layui-btn layui-btn-xs layui-btn-disabled">预览</a>
                            </c:if>
						</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
				<tr>
					<td colspan="9" id="pageLine"></td>
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

		// 渲染日期选择
		laydate.render({elem: '#stm', type: 'date'});
		laydate.render({elem: '#edm', type: 'date'});
        
        $("a[name='view']").click(function () {
            var url = '${ host }/' + $(this).attr("data-url");
            var style = $(this).attr("data-type")=="icon"?'style="margin-top: 80px;"':'style="height: 100%;"';
            layer.open({
                type: 1,
                maxWidth: 500,
                content: '<div style="width: 500px; height: 320px; overflow: hidden; text-align: center">' +
                    '<img src="' + url + '" alt="" ' + style + '/></div>'
            });
        });

        // 删除单个附件
        $("a[name='delete']").click(function () {
            var url = this.href;
            layer.confirm('删除数据不可恢复，是否确定？', {icon: 3, title: '提示'}, function (index) {
                $('<form method="post"></form>').attr("action", url)
                    .append('<input type="hidden" name="order" value="${ param.odr }">')
                    .appendTo($(document.body)).submit();
                layer.close(index);
            });
            return false;
        });

        // 批量删除附件
		$(".item-oper .layui-btn").on("click", function () {
            var action = "${ base }content/Attachment/batch-" + $(this).data('type');
            layer.confirm('确定要' + $(this).text() + '吗？', {icon: 3, title: '提示'}, function (index) {
                $("#operForm").attr("action",action).submit();
                layer.close(index);
            });
		});
	});
</script>
</body>
</html>