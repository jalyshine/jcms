<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>模板风格</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <style type="text/css">
        .item{ display: inline-block; width: 260px; height: 280px; margin: 20px; border: 1px solid #eee; }
        .item .img-border{ width: 100%; height: 150px; text-align: center; overflow: hidden; }
        .item .img-border img { height: 100%; }
        .item p{ width: 90%; margin: 0 auto; }
        .item .title{ line-height: 50px; font-size: 13pt; }
        .item .name{ line-height: 30px; color: gray; text-align: right; }
        .item .name span{ float: left; }
        .item .name a{ border: 1px solid #0f74a8; color: #0f74a8; padding: 2px 5px;}
        .item .name a:hover{ color: white; background-color: #0f74a8; }
        .item .operation{ line-height: 50px; }
        .item .operation span{ display: inline-block; width: 25px; height: 10px; }
    </style>
</head>
<body>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">模板风格</span>
        <c:import url="../menubar.jsp?module=template" />
    </div>
    <div class="layui-card-body">
        <form action="${ base }template/Template/list" method="get" class="layui-form" id="searchForm">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input name="kwd" type="text" placeholder="搜索关键词"
                               class="layui-input" value="${ param.kwd }" autocomplete="off"/>
                    </div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <select name="cid">
                            <option value="">色系</option>
                            <c:forEach items="${ colors }" var="color">
                                <option value="${ color.id }">${ color.attrValue }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <select name="tid">
                            <option value="">分类</option>
                            <c:forEach items="${ types }" var="type">
                                <option value="${ type.id }">${ type.attrValue }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="hidden" name="ps" value="${ param.ps }">
                        <input type="hidden" name="pn" value="${ param.pn }">
                        <input type="hidden" name="odr" value="update_time desc">
                        <input type="submit" class="layui-btn" value="开始搜索"/>
                    </div>
                </div>
            </div>
        </form>
        <c:forEach items="${ page.list }" var="item">
        <div class="item">
            <div class="img-border">
                <img src="${ base }${ item.thumb }" />
            </div>
            <p class="title">${ item.title }</p>
            <p class="name">
                <span>${ item.name }</span>
                <a href="${ base }template/Template/list?tid=${ item.type.id }">${ item.type.attrValue }</a>
            </p>
            <p class="operation">
                <a href="${ base }template/Template/edit?id=${ item.id }"
                   class="layui-btn layui-btn-xs">编 辑</a>
                <a href="${ base }template/${ item.name }/preview/index.html" target="_blank"
                   class="layui-btn layui-btn-xs layui-btn-primary">预 览</a>
                <a href="${ base }template/Template/delete?id=${ item.id }" name="delete"
                   class="layui-btn layui-btn-xs layui-btn-danger">删 除</a>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <c:choose>
                    <c:when test="${ item.color.attrValue == '红色系' }"><span style="background-color: red"></span></c:when>
                    <c:when test="${ item.color.attrValue == '橙色系' }"><span style="background-color: orange"></span></c:when>
                    <c:when test="${ item.color.attrValue == '黄色系' }"><span style="background-color: yellow"></span></c:when>
                    <c:when test="${ item.color.attrValue == '绿色系' }"><span style="background-color: green"></span></c:when>
                    <c:when test="${ item.color.attrValue == '蓝色系' }"><span style="background-color: blue"></span></c:when>
                    <c:when test="${ item.color.attrValue == '紫色系' }"><span style="background-color: purple"></span></c:when>
                    <c:when test="${ item.color.attrValue == '浅灰系' }"><span style="background-color: lightgray"></span></c:when>
                    <c:when test="${ item.color.attrValue == '深灰系' }"><span style="background-color: darkgray"></span></c:when>
                    <c:when test="${ item.color.attrValue == '白色系' }"><span style="border: 1px solid gray;"></span></c:when>
                    <c:when test="${ item.color.attrValue == '黑色系' }"><span style="background-color: black"></span></c:when>
                </c:choose>
            </p>
        </div>
        </c:forEach>
        <div id="pageLine"></div>
    </div>
</div>

<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['layer', 'laypage', 'element', 'form'], function () {
        var layer = layui.layer, $ = layui.$, laypage = layui.laypage;

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