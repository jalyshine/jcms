<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/编辑管理员角色</title>
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

<jfp:JsonDataSource id="DB1" value="{'true':'禁用','false':'启用'}"/>
<c:if test="${ adminRole.id == null }">
    <c:set value="${ base }admin/AdminRole/add" var="save"/>
</c:if>
<c:if test="${ adminRole.id != null }">
    <c:set value="${ base }admin/AdminRole/edit" var="save"/>
</c:if>

<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑管理员角色</span>
        <c:import url="../menubar.jsp?module=admin"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="adminRole" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>

            <div class="layui-form-item">
                <label class="layui-form-label">角色名称</label>
                <div class="layui-input-block">
                    <form:input path="name" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色描述</label>
                <div class="layui-input-block">
                    <form:textarea path="description" class="layui-textarea"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">是否禁用</label>
                <div class="layui-input-block">
                    <form:checkbox path="disabled" title="禁用"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit="" id="submit"
                            <c:if test="${ adminRole.id == null }">
                                class="layui-btn layui-btn-disabled" disabled="disabled"
                            </c:if>
                            <c:if test="${ adminRole.id != null }">
                                class="layui-btn"
                            </c:if>
                    >提交
                    </button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['layer', 'form', 'element'], function () {
        var layer = layui.layer, $ = layui.jquery;

        $(":text[name='name']").change(function () {
            if ($.trim(this.value) == "") {
                $("#submit").attr("class", "layui-btn layui-btn-disabled");
                $("#submit").attr("disabled", true);
            } else {
                var id = $(":hidden[name='id']").val();
                $.post("${ base }admin/AdminRole/check", {"name": this.value, "time": new Date()}, function (data) {
                    if (data == "0" || data == id) {
                        $("#submit").attr("class", "layui-btn");
                        $("#submit").removeAttr("disabled");
                    } else {
                        $("#submit").attr("class", "layui-btn layui-btn-disabled");
                        $("#submit").attr("disabled", true);
                        layer.msg('该角色名已经被占用！', {icon: 2});
                    }
                });
            }
        });
    });

</script>
</body>
</html>