<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>添加审核会员</title>
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
<c:if test="${ memberVerify.id == null }">
    <c:set value="${ base }member/MemberVerify/add" var="save"/>
</c:if>
<c:if test="${ memberVerify.id != null }">
    <c:set value="${ base }member/MemberVerify/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑审核会员</span>
        <c:import url="../menubar.jsp?module=member" />
        <span class="layui-breadcrumb" style="margin-right: 10px;">
            <a href="${ base }member/MemberVerify/list?tts=1">通过</a>
            <a href="${ base }member/MemberVerify/list?tts=2">拒绝</a>
            <a href="${ base }member/MemberVerify/list?tts=3">忽略</a>
            <a href="${ base }member/MemberVerify/list?tts=4">无法通过</a>
        </span>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="memberVerify" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>

                <div class="layui-input-block">
                    <form:input path="userName" lay-verify="name" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>

                <div class="layui-input-block">
                    <form:password path="password" lay-verify="psd1" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>

                <div class="layui-input-block">
                    <input type="password" name="password2" lay-verify="psd2" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">Email</label>

                <div class="layui-input-block">
                    <form:input path="email" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号</label>

                <div class="layui-input-block">
                    <form:input path="phone" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit="" id="submit"
                            <c:if test="${ memberVerify.id == null }">
                                class="layui-btn layui-btn-disabled" disabled="disabled"
                            </c:if>
                            <c:if test="${ memberVerify.id != null }">
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
<script type="text/javascript" src="${ base }assets/plugins/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['layer', 'form', 'element'], function () {
        var layer = layui.layer, $ = layui.$, form = layui.form;

        form.verify({
            name: function (value) {
                if ($.trim(value).length < 8) {
                    return "用户名不能少于8位！";
                }
            },
            psd1: function (value) {
                if ($.trim(value).length < 8) {
                    return "密码不能少于8位！";
                }
            },
            psd2: function (value) {
                if (value != $("[name='password']").val()) {
                    return "确认密码不一致！";
                }
            }
        });

        $(":text[name='userName']").change(function () {
            if ($.trim(this.value) == "") {
                $("#submit").attr("class", "layui-btn layui-btn-disabled");
                $("#submit").attr("disabled", true);
            } else {
                var id = $(":hidden[name='id']").val();
                $.post("${ base }member/Member/check", {"name": this.value, "time": new Date()}, function (data) {
                    if (data == "0" || data == id) {
                        $("#submit").attr("class", "layui-btn");
                        $("#submit").removeAttr("disabled");
                    } else {
                        $("#submit").attr("class", "layui-btn layui-btn-disabled");
                        $("#submit").attr("disabled", true);
                        layer.msg('该用户名已经被占用！', {icon: 2});
                    }
                });
            }
        });
    });
</script>
</body>
</html>