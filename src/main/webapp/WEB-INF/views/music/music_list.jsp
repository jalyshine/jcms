<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>音乐管理</title>
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
        <span class="card-title">音乐管理</span>
    </div>
    <div class="layui-card-body">
        <form id="searchForm" action="${ base }music/Music/list" method="get" class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 60px; text-align: left;">更新时间</label>

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
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 150px;">
                        <input name="kwd" type="text" placeholder="搜索关键词" class="layui-input" value="${ param.kwd }"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <input type="hidden" name="ps" value="${ param.ps }">
                    <input type="hidden" name="pn" value="${ param.pn }">
                    <input type="submit" class="layui-btn" value="开始搜索"/>
                </div>
            </div>
        </form>
        <div class="layui-btn-group music-oper">
            <button class="layui-btn layui-btn-danger" data-type="delete">删除选中音乐</button>
        </div>
        <form action="" method="post" id="operForm" class="layui-form">
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
                    <th>操作</th>
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
                        <td>
                            <a href="${ base }music/Music/delete?id=${ music.id }" name="delete"
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
    layui.use(['layer', 'laypage', 'laydate', 'form', 'element'], function () {
        var layer = layui.layer, laypage = layui.laypage, laydate = layui.laydate,
            form = layui.form, $ = layui.$;

        // 渲染日期选择
        laydate.render({elem: '#stm', type: 'date'});
        laydate.render({elem: '#edm', type: 'date'});

        // 全选
        form.on("checkbox(selAll)", function (data) {
            if (data.elem.checked) {
                $(":checkbox[name='ids']").prop("checked", true); // 此处不能用attr，否则第二次点击不渲染
            } else {
                $(":checkbox[name='ids']").removeAttr('checked');
            }
            form.render("checkbox");
        })

        // 分页
        laypage.render({
            elem: 'pageLine'
            , count: ${ page.total }
            , curr: ${ page.pageNum }
            , limit: 20
            , layout: ['count', 'prev', 'page', 'next', 'skip']
            , jump: function (obj, first) {
                if (!first) {
                    $(":hidden[name='ps']").val(obj.limit);
                    $(":hidden[name='pn']").val(obj.curr);
                    $("#searchForm").submit();
                }
            }
        });

        $(".music-oper .layui-btn").on("click", function () {
            var action = "${ base }music/Music/batch-" + $(this).data('type');
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
