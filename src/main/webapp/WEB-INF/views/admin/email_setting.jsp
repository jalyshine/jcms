<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${ pageContext.request.contextPath }/admin/" var="a"/>
<c:set value="${ pageContext.request.contextPath }/assets/" var="s"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>邮箱配置</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ s }plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ s }css/page.css"/>
</head>
<body>

<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">邮箱配置</span>
    </div>
    <div class="layui-card-body">
        <form:form action="${ a }EmailSetting/manage" method="post" modelAttribute="emailSetting" cssClass="layui-form">

            <div class="layui-form-item">
                <label class="layui-form-label">邮件服务器</label>

                <div class="layui-input-block">
                    <form:input path="server" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">发件人地址</label>

                <div class="layui-input-block">
                    <form:input path="sender" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">AUTH验证</label>

                <div class="layui-input-inline">
                    <form:checkbox path="needAuth" title="开启"/>
                </div>
                <div class="layui-form-mid layui-word-aux">qq邮箱必须开启POP3/SMTP服务，并使用授权码登录</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">验证用户名</label>

                <div class="layui-input-block">
                    <form:input path="userName" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">验证密码</label>

                <div class="layui-input-block">
                    <form:input path="password" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"> </label>

                <div class="layui-input-block">
                    <button lay-submit="" id="submit" class="layui-btn">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">邮件配置测试</span>
    </div>
    <div class="layui-card-body">
        <div class="layui-input-inline">
            <input id="content" type="text" class="layui-input"/>
        </div>
        <div class="layui-inline">
            <input type="button" class="layui-btn" value="测试发送" id="test"/>
        </div>
    </div>
</div>
<script src="${ s }plugins/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'layPost'], function () {
        var $ = layui.$, layPost = layui.layPost;

        $("#submit").on("click", function () {
            var url = $("form").attr("action");
            var arg = $("form").serialize();
            layPost.post(url, arg);
            return false;
        });

        $("#test").on("click", function () {
            var args = {
                "host": $("#server").val(),
                "from": $("#sender").val(),
                "needAuth": $("#needAuth").attr("checked"),
                "userName": $("#userName").val(),
                "password": $("#password").val(),
                "content": $("#content").val(),
                "time": new Date()
            };
            layPost.post("${ a }EmailSetting/test", args, function (data) {
                return "发送成功！";
            }, function (data) {
                return "发送失败！账号或密码错误...";
            }, true);
        });
    });
</script>
</body>
</html>