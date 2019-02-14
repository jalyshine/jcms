<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>设置角色权限</title>
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
        .layui-icon { cursor: pointer; color: #888; }
    </style>
</head>
<body>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">设置角色权限</span>
        <span class="layui-breadcrumb">
            <a href="${ base }admin/AdminRole/list">返回角色管理</a>
        </span>
    </div>
    <div class="layui-card-body">
        <form action="${ base }admin/AdminRolePrivacy/manage" method="post" class="layui-form">
            <input type="hidden" name="role" value="${ param.role }">
            <input type="hidden" name="site" value="${ param.site }">

            <div class="layui-form-item">
                <label class="layui-form-label">当前角色</label>

                <div class="layui-input-inline">
                    <label class="layui-form-label" style="background-color: #E6E6E6; text-align: center;">${ roleName }</label>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">当前站点</label>

                <div class="layui-input-inline">
                    <select id="change-site" lay-filter="site" lay-verify="required">
                        <option value="">请选择站点</option>
                        <c:forEach items="${ sites }" var="item">
                            <option value="${ base }admin/AdminRolePrivacy/manage?role=${ param.role }&site=${ item.id }"
                                    <c:if test="${ param.site == item.id }">selected</c:if>
                            >${ item.name }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">设置权限</label>

                <div class="layui-input-block">
                    <table class="layui-table" lay-size="sm">
                        <thead>
                        <tr>
                            <th style="text-align: center; width:40px;">
                                <input type="checkbox" lay-skin="primary" lay-filter="selAll" id="selAll">
                            </th>
                            <th style="width:40px;">ID</th>
                            <th>菜单名称</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ menuList }" var="menu">
                            <tr data-pid="${ menu.parentId == null?0:menu.parentId }" data-id="${ menu.id }" data-extend>
                                <td style="text-align: center;">
                                    <input type="checkbox" value="${ menu.id }" lay-skin="primary"
                                           <c:if test="${ menu.display }">checked</c:if>
                                           <c:if test="${ menu.depth > 2 }">name="mids"</c:if>
                                           lay-filter="selMenu" data-pid="${ menu.parentId }">
                                </td>
                                <td>${ menu.id }</td>
                                <td class="lev${ menu.depth }">
                                    <i class="layui-icon layui-icon-triangle-d" style="font-size: 12pt;"></i>
                                    &nbsp;${ menu.name }
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit="" id="submit" class="layui-btn">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['form', 'element'], function () {
        var $ = layui.$, form = layui.form;
        // 全选
        form.on("checkbox(selAll)", function (data) {
            if (data.elem.checked) {
                $("tbody :checkbox").prop("checked", true); // 此处不能用attr，否则第二次点击不渲染
            } else {
                $("tbody :checkbox").removeAttr('checked');
            }
            form.render("checkbox");
        });
        // 单选
        form.on("checkbox(selMenu)", function (data) {
            treeGrid.check($(this), data.elem.checked);
            form.render("checkbox");
        });

        form.on("select(site)", function () {
            var url = $("#change-site").val();
            if (url.trim() != "") {
                window.location.href = url;
            }
        });

        $(".layui-icon").on("click", function () {
            $tr = $(this).parent().parent();
            treeGrid.extend($tr);
        });

        var treeGrid = {
            check: function ($checkbox, isChecked) {
                var $children = $("tbody :checkbox[data-pid='" + $checkbox.val() + "']");
                if($children.length > 0){
                    if (isChecked) {
                        $children.prop("checked", true);
                    } else {
                        $children.removeAttr('checked');
                    }
                    $children.each(function (index) {
                        treeGrid.check($(this), isChecked);
                    });
                }
            },
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