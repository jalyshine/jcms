<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>影片管理</title>
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
        <span class="card-title">影片管理</span>
        <c:import url="../menubar.jsp?module=movie"/>
    </div>
    <div class="layui-card-body">
        <form id="searchForm" action="${ base }movie/Movie/list" method="get" class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <select name="tid">
                        <option value="">影片类别</option>
                        <c:forEach items="${ types }" var="type">
                            <option value="${ type.id }">${ type.attrValue }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="layui-inline" style="width: 100px;">
                    <select name="aid">
                        <option value="">影片地区</option>
                        <c:forEach items="${ areas }" var="area">
                            <option value="${ area.id }">${ area.attrValue }</option>
                        </c:forEach>
                    </select>
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
        <div class="layui-btn-group item-oper">
            <button class="layui-btn layui-btn-danger" data-type="deletes">批量删除影片</button>
        </div>
        <form action="${ base }movie/Article/oper" method="post" id="operForm" class="layui-form">
            <input type="hidden" name="odr" value="${ param.odr }">
            <input type="hidden" name="type" value="">
            <table lay-filter="items" lay-even class="layui-table" lay-size="sm">
                <thead>
                <tr>
                    <th style="text-align: center; width:40px;">
                        <input type="checkbox" lay-skin="primary" lay-filter="selAll" id="selAll">
                    </th>
                    <th>ID</th>
                    <th>影片名称</th>
                    <th>所属类别</th>
                    <th>所属地区</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ page.list }" var="movie">
                    <tr>
                        <td style="text-align: center;">
                            <input type="checkbox" name="ids" value="${ article.id }" lay-skin="primary">
                        </td>
                        <td>${ movie.id }</td>
                        <td>${ movie.title }</td>
                        <td>${ movie.type.attrValue }</td>
                        <td>${ movie.area.attrValue }</td>
                        <td>
                            <fmt:formatDate value="${ movie.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <a href="${ base }movie/MovieComment/list?id=${ movie.id }"
                               class="layui-btn layui-btn-xs">评论</a>
                            <a href="${ base }movie/Movie/edit?id=${ movie.id }"
                               class="layui-btn layui-btn-xs">编辑</a>
                            <a href="${ base }movie/Movie/delete?id=${ movie.id }" name="delete"
                               class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
                        </td>
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

        $(".item-oper .layui-btn").on("click", function () {
            var type = $(this).data('type');
            $("#operForm :hidden[name='type']").val(type);
            layer.confirm('确定要' + $(this).text() + '吗？', {icon: 3, title: '提示'}, function (index) {
                $("#operForm").submit();
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
