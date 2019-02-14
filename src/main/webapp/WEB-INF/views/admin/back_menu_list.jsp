<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台菜单管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <style type="text/css">
        .lev1 { color: white; background-color: #aaa; }
        .lev2 { color: black; background-color: #f2f2f2; text-indent: 2em; }
        .lev3 { color: black; text-indent: 4em; }
        .lev4 { text-indent: 6em; }
        .sort-text { width: 30px; text-align: center; border: 1px dotted #888; }
        .layui-icon { cursor: pointer; color: #888; }
    </style>
</head>
<body>

<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">后台菜单管理</span>
        <c:import url="../menubar.jsp?module=admin" />
    </div>
    <div class="layui-card-body">
        <div class="layui-btn-group menu-oper">
            <button class="layui-btn" data-type="sort">重新排序</button>
        </div>
        <form action="" method="post" id="operForm" class="layui-form">
            <table class="layui-table" lay-size="sm">
                <thead>
                <tr>
                    <th style="width: 40px;">ID</th>
                    <th>菜单名称</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ backMenus }" var="menu">
                    <tr data-pid="${ menu.parentId == null?0:menu.parentId }" data-id="${ menu.id }" data-extend>
                        <td>${ menu.id }</td>
                        <td class="lev${ menu.depth }">
                            <i class="layui-icon layui-icon-triangle-d"></i>&nbsp;
                            <input type="hidden" name="id" value="${ menu.id }">
                            <input type="text" name="order" value="${ menu.listOrder }" class="sort-text">
                            &nbsp; ${ menu.name }
                        </td>
                        <td>
                            <a href="${ base }admin/BackMenu/edit?id=${ menu.id }"
                               class="layui-btn layui-btn-xs">编辑</a>
                            <a href="${ base }admin/BackMenu/delete?id=${ menu.id }" name="delete"
                               class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
                            <c:if test="${ menu.depth < 4 }">
                                <a href="${ base }admin/BackMenu/add?pid=${ menu.id }"
                                   class="layui-btn layui-btn-xs">添加子菜单</a>
                            </c:if>
                            <c:if test="${ menu.depth >= 4 }">
                                <a href="javascript:;"
                                   class="layui-btn layui-btn-xs layui-btn-disabled">添加子菜单</a>
                            </c:if>
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
    layui.use(['layer', 'form', 'element'], function () {
        var layer = layui.layer, $ = layui.$, form = layui.form;

        $(".menu-oper .layui-btn").on("click", function () {
            var action = "${ base }admin/BackMenu/batch-" + $(this).data('type');
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

        $(".layui-icon").on("click", function () {
            $tr = $(this).parent().parent();
            treeGrid.extend($tr);
        });

        var treeGrid = {
            extend: function ($tr) {
                // 节点的原始展开状态：true展开，false折叠
                var isOpen = $tr.attr("data-extend") != undefined;
                var id = $tr.attr("data-id");
                if (isOpen) {  // 如果展开
                    $tr.removeAttr("data-extend");
                    $tr.find("td i").attr("class", "layui-icon layui-icon-triangle-r");
                    treeGrid.childs($tr).each(function (index) {
                        treeGrid.close($(this));
                    });
                } else {       // 如果已经折叠
                    $tr.attr("data-extend", "");
                    $tr.find("td i").attr("class", "layui-icon layui-icon-triangle-d");
                    treeGrid.childs($tr).css("display", "").each(function (index) {
                        treeGrid.open($(this));
                    });
                }
                form.render("checkbox");
            },
            childs: function ($tr) {
                var id = $tr.attr("data-id");
                return $("tbody tr[data-pid='" + id + "']");
            },
            close: function ($tr) {
                treeGrid.childs($tr).each(function (index) {
                    treeGrid.close($(this));
                });
                $tr.css("display", "none");
            },
            open: function ($tr) {
                var isOpen = $tr.attr("data-extend") != undefined;
                if (isOpen) {
                    treeGrid.childs($tr).css("display", "");
                }
            }
        }
    });
</script>
</body>
</html>