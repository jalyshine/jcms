<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>设置栏目权限</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <style type="text/css">
        .lev1{ background-color: #ccc; }
        .lev2{ background-color: #f2f2f2; text-indent: 4em;}
        .lev3{ text-indent: 8em;}
        .lev4{ text-indent: 12em; }
        .check{ text-align: center; width:40px; }
    </style>
</head>
<body>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">设置栏目权限</span>
         <span class="layui-breadcrumb">
            <a href="${ base }admin/AdminRole/list">返回角色管理</a>
        </span>
    </div>
    <div class="layui-card-body">
        <form action="${ base }content/CategoryPrivacy/manage" method="post" class="layui-form">
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
                            <option value="${ base }content/CategoryPrivacy/manage?role=${ param.role }&site=${ item.id }"
                                    <c:if test="${ param.site == item.id }">selected</c:if>
                            >${ item.name }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">栏目权限</label>

                <div class="layui-input-block">
                    <table class="layui-table" lay-size="sm">
                        <thead>
                        <tr>
                            <th class="check">全选</th>
                            <th class="check">ID</th>
                            <th>栏目名称</th>
                            <th class="check">查看</th>
                            <th class="check">添加</th>
                            <th class="check">修改</th>
                            <th class="check">删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ categories }" var="category">
                            <tr>
                                <td class="check">
                                    <input type="hidden" name="cid" value="${ category.id }">
                                    <input type="checkbox" lay-skin="primary" lay-filter="selAll">
                                    <input type="hidden" name="action" value="">
                                </td>
                                <td class="check">${ category.id }</td>
                                <c:if test="${ category.type==1 }">
                                <td class="lev${ category.level }">
                                    </c:if>
                                    <c:if test="${ category.type!=1 }">
                                <td>
                                    </c:if>
                                    <c:if test="${ category.level > 1 }" >∟ </c:if>
                                        ${ category.name }
                                </td>
                                <td class="check">
                                    <input type="checkbox" lay-skin="primary" value="list" lay-filter="selAction"
                                           <c:if test="${ category.type!=1 }">disabled</c:if>
                                           <c:if test="${ fn:contains(category.privacyString,'list,') }">checked</c:if>>
                                </td>
                                <td class="check">
                                    <input type="checkbox" lay-skin="primary" value="add" lay-filter="selAction"
                                           <c:if test="${ category.type!=1 }">disabled</c:if>
                                           <c:if test="${ fn:contains(category.privacyString,'add,') }">checked</c:if>>
                                </td>
                                <td class="check">
                                    <input type="checkbox" lay-skin="primary" value="edit" lay-filter="selAction"
                                           <c:if test="${ fn:contains(category.privacyString,'edit,') }">checked</c:if>>
                                </td>
                                <td class="check">
                                    <input type="checkbox" lay-skin="primary" value="delete" lay-filter="selAction"
                                           <c:if test="${ category.type!=1 }">disabled</c:if>
                                           <c:if test="${ fn:contains(category.privacyString,'delete,') }">checked</c:if>>
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
            var $boxes = $(this).parent().parent().find(":checkbox").not(':disabled');
            var value = "";
            if (data.elem.checked) {
                $boxes.prop("checked", true);
                $boxes.each(function (index) {
                    if(index > 0){
                        value += this.value + ",";
                    }
                });
            } else {
                $boxes.removeAttr('checked');
            }
            $(this).nextAll(":hidden:first").val(value);
            form.render("checkbox");
        });
        // 单选
        form.on("checkbox(selAction)", function (data) {
            var action = this.value + ",";
            var actionInput = $(this).parent().parent().find(":hidden[name='action']");
            var value = actionInput.val();
            if (data.elem.checked) {
                actionInput.val(value + action);
            } else {
                actionInput.val(value.replace(action,""));
            }
        });
        // 切换站点
        form.on("select(site)", function () {
            var url = $("#change-site").val();
            if (url.trim() != "") {
                window.location.href = url;
            }
        });
    });
</script>
</body>
</html>