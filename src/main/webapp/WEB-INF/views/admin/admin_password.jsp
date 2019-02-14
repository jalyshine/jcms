<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改管理员密码</title>
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
        <span class="card-title">修改密码</span>
        <span class="layui-breadcrumb">
            <a href="${ base }admin/Admin/info">修改信息</a>
            <a href="${ base }admin/Admin/password">修改密码</a>
            <a href="${ base }admin/Admin/home">返回主页</a>
        </span>
    </div>
    <div class="layui-card-body">
        <form:form action="${ base }admin/Admin/password" method="post" modelAttribute="admin" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="userName"/>
            <form:hidden path="realName"/>
            <form:hidden path="email"/>
            <form:hidden path="encrypt"/>

            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>

                <div class="layui-input-block" style="line-height: 40px; color:gray;">${ admin.userName }</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>

                <div class="layui-input-block" style="line-height: 40px; color:gray;">${ admin.realName }</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">Email</label>

                <div class="layui-input-block" style="line-height: 40px; color:gray;">${ admin.email }</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">旧密码</label>

                <div class="layui-input-block">
                    <input type="password" name="oldPassword" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">新密码</label>

                <div class="layui-input-block">
                    <input type="password" name="newPassword" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>

                <div class="layui-input-block">
                    <input type="password" name="password2" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ base }assets/plugins/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'element', 'layPost'], function () {
        var form = layui.form, $ = layui.jquery, layPost = layui.layPost;

        form.verify({
            psd: function (value) {
                if ($.trim(value).length < 8) {
                    return "密码不能少于8位！";
                }
            }
        });

        $(":submit").on("click", function () {
            var psd1 = $.trim($(":password[name='newPassword']").val());
            var psd2 = $.trim($(":password[name='password2']").val());
            if(psd1.length < 8){
                layer.msg("密码不能少于8位！");
            } else if(psd1 != psd2){
                layer.msg("确认密码不一致！");
            } else {
                layPost.post($("form").attr("action"), $("form").serialize(), null, function (data) {
                    return "原密码输入错误！！！";
                });
            }
            return false;
        });
    });
</script>
</body>
</html>