<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>会员组管理</title>
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
<form id="searchForm" action="${ m }MemberGroup/list" method="get">
    <input type="hidden" name="ps" value="${ param.ps }">
    <input type="hidden" name="pn" value="${ param.pn }">
</form>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">会员组管理</span>
        <c:import url="../menubar.jsp?module=member" />
    </div>
    <div class="layui-card-body">
        <table lay-filter="items" lay-even class="layui-table" lay-size="sm">
            <thead>
            <tr>
                <th>ID</th>
                <th>会员组名</th>
                <th>是否系统组</th>
                <th>会员数</th>
                <th>星星数</th>
                <th>积分小于</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ page.list }" var="group">
                <tr>
                    <td>${ group.id }</td>
                    <td>
                        <c:if test="${ group.userNameColor != null }">
                            <span style="color:${ group.userNameColor }">${ group.name }</span>
                        </c:if>
                        <c:if test="${ group.userNameColor == null }">
                            ${ group.name }
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${ group.isCore }">
                            <b style='color: green;'>√</b>
                        </c:if>
                        <c:if test="${ !group.isCore }">
                            <b style='color: red;'>×</b>
                        </c:if>
                    </td>
                    <td>${ group.memberCount }</td>
                    <td>${ group.starNum }</td>
                    <td>${ group.maxPoint }</td>
                    <td>
                        <a href="${ base }member/MemberGroup/edit?id=${ group.id }"
                           class="layui-btn layui-btn-xs">编辑</a>
                        <c:if test="${ group.isCore }">
                            <a href='javascript:;' class="layui-btn layui-btn-xs layui-btn-disabled">删除</a>
                        </c:if>
                        <c:if test="${ !group.isCore }">
                            <a href="${ base }member/MemberGroup/delete?id=${ group.id }" name="delete"
                               class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
                        </c:if>
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
    </div>
</div>
<script type="text/javascript" src="${ base }assets/plugins/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['layer', 'form', 'laypage', 'element'], function () {
        var layer = layui.layer, form = layui.form,
            laypage = layui.laypage, $ = layui.$;

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