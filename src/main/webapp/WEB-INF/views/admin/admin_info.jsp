<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改个人信息</title>
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
        <span class="card-title">修改信息</span>
        <span class="layui-breadcrumb">
            <a href="${ base }admin/Admin/info">修改信息</a>
            <a href="${ base }admin/Admin/password">修改密码</a>
            <a href="${ base }admin/Admin/home">返回主页</a>
        </span>
    </div>
    <div class="layui-card-body">
        <form:form action="${ base }admin/Admin/info" method="post" modelAttribute="admin" cssClass="layui-form">
            <form:hidden path="id" />
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>

                <div class="layui-input-block" style="line-height: 40px; color:gray;">${ admin.userName }</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">最后登录</label>

                <div class="layui-input-block" style="line-height: 40px; color:gray;">
                    IP：${ admin.lastLoginIp } &nbsp;&nbsp;&nbsp;&nbsp;
                    时间：<fmt:formatDate value="${ admin.lastLoginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上传头像</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="face"/>
                    <div class="layui-upload-list">
                        <img id="face-img" style="height: 100px"
                        <c:if test="${ empty admin.face }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty admin.face }">
                            src="${ base }${ admin.face }">
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>

                <div class="layui-input-block">
                    <form:input path="realName" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">Email</label>

                <div class="layui-input-block">
                    <form:input path="email" lay-verify="email" autocomplete="off" class="layui-input"/>
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
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'element', 'layPost'], function () {
        var $ = layui.jquery, layPost = layui.layPost;

        layPost.image('face', 'icon', '${ base }', '${ base }', '', '');

        $(":submit").on("click", function () {
            layPost.post($("form").attr("action"), $("form").serialize());
            return false;
        });
    });
</script>
</body>
</html>