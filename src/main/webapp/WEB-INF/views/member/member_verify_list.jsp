<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>审核会员</title>
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
<form action="${ base }member/MemberVerigy/list" method="get" class="layui-form" id="searchForm">
    <input type="hidden" name="ps" value="${ param.ps }">
    <input type="hidden" name="pn" value="${ param.pn }">
    <input type="hidden" name="odr" value="reg_time desc">
</form>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">审核会员</span>
        <c:import url="../menubar.jsp?module=member"/>
        <span class="layui-breadcrumb" style="margin-right: 10px;">
            <a href="${ base }member/MemberVerify/list?tts=1">通过</a>
            <a href="${ base }member/MemberVerify/list?tts=2">拒绝</a>
            <a href="${ base }member/MemberVerify/list?tts=3">忽略</a>
            <a href="${ base }member/MemberVerify/list?tts=4">无法通过</a>
        </span>
    </div>
    <div class="layui-card-body">
        <div class="layui-btn-group member-verify-oper">
            <button class="layui-btn" data-type="pass">通过</button>
            <button class="layui-btn" data-type="refuse">拒绝</button>
            <button class="layui-btn" data-type="ignore">忽略</button>
            <button class="layui-btn layui-btn-danger" data-type="delete">删除</button>
        </div>
        <form action="" method="post" id="operForm" class="layui-form">
            <table lay-filter="items" lay-even class="layui-table" lay-size="sm">
                <thead>
                <tr>
                    <th style="text-align: center; width:40px;">
                        <input type="checkbox" lay-skin="primary" lay-filter="selAll" id="selAll">
                    </th>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>Email</th>
                    <th>注册时间</th>
                    <th>审核状态</th>
                    <th>审核信息</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ page.list }" var="memberVerify">
                    <tr>
                        <td style="text-align: center;">
                            <input type="checkbox" name="ids" value="${ memberVerify.id }" lay-skin="primary">
                        </td>
                        <td>${ memberVerify.id }</td>
                        <td>
                            <a href="${ base }member/MemberVerify/input?id=${memberVerify.id}">
                                    ${ memberVerify.userName }
                            </a>
                        </td>
                        <td>${ memberVerify.email }</td>
                        <td>
                            <fmt:formatDate value="${ memberVerify.regTime }" type="both"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${ memberVerify.status == 1  }">通过</c:when>
                                <c:when test="${ memberVerify.status == 2  }">拒绝</c:when>
                                <c:when test="${ memberVerify.status == 3  }">忽略</c:when>
                                <c:when test="${ memberVerify.status == 4  }">无法通过</c:when>
                                <c:otherwise>未审核</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${ memberVerify.message }</td>
                        <td>
                            <a href="${ base }member/MemberVerify/edit?id=${ memberVerify.id }"
                               class="layui-btn layui-btn-xs">编辑</a>
                            <a href="${ base }member/MemberVerify/delete?id=${ memberVerify.id }" name="delete"
                               class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="8" id="pageLine"></td>
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

        $(".member-verify-oper .layui-btn").on("click", function () {
            var action = "${ base }member/MemberVerify/batch-" + $(this).data('type');
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