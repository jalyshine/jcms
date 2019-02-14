<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>后台登录</title>
    <link href="${ base }assets/plugins/layui/css/layui.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${ base }assets/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="login-box">
    <div class="logo"></div>
    <form action="${ base }admin/Admin/login" class="layui-form" method="post" id="login-form">
        <div class="layui-form-item">
            <label class="login-icon"><i class="layui-icon">&#xe66f;</i></label>
            <input type="text" name="userName" lay-verify="userName" placeholder="请输入账号" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-form-item">
            <label class="login-icon"><i class="layui-icon">&#xe673;</i></label>
            <input type="password" name="password" lay-verify="password" placeholder="请输入密码" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-form-item">
            <div class="pull-left">
                <label class="login-icon"><i class="layui-icon">&#xe679;</i></label>
                <input type="text" id="code" placeholder="请输入验证码" class="layui-input">
            </div>
            <div class="pull-right">
                <canvas id="canvas"></canvas>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="pull-left login-remember">
                <label>记住帐号？</label>
                <input type="checkbox" value="true" lay-skin="switch" checked title="记住帐号">
            </div>
            <div class="pull-right">
                <button class="layui-btn" lay-submit lay-filter="login">
                    <i class="layui-icon">&#xe650;</i> 登录
                </button>
            </div>
        </div>
    </form>
</div>
<div class="footer">风鸟云建站系统 2019-2024 &copy; www.tjcj-soft.com</div>

<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ base }assets/js/'
    }).use(['layer', 'form', 'identify', 'layPost'], function () {
        var layer = layui.layer, $ = layui.jquery, form = layui.form,
            identify = layui.identify, layPost = layui.layPost;

        $(":text[name='userName']").val(layui.data("admin_login").username);
        var code = identify.draw($("#canvas"), 6);

        form.on('submit(login)', function (data) {
            var identifyCode = code; //$("#code").val().toLowerCase();
            if(identifyCode == code){
                if ($(":checkbox").prop("checked")) {
                    layui.data('admin_login', {
                        key: 'username', value: $(":text").val()
                    });
                } else {
                    layui.data('admin_login', null);
                }
                layPost.post($("form").attr("action"), $("form").serialize(), function (data) {
                    window.location = "${ base }index?site=" + data;
                }, function (data) {
                    var num = parseInt(data);
                    if(num > 0){
                        return "请于" + num + "分钟后登录！";
                    } else {
                        return "您还有" + (0-num) + "次登录机会！";
                    }
                });
            } else {
                layer.msg('验证码输入错误，请重新输入', {icon:2});
                code = identify.draw($("#canvas"), 6);
            }
            return false;
        });
    });
</script>
</body>
</html>