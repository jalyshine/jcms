<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>评论模块设置</title>
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
        <span class="card-title">评论模块设置</span>
    </div>
    <div class="layui-card-body">
        <form:form action="${ base }comment/CommentSetting/manage" modelAttribute="commentSetting" method="post" cssClass="layui-form">

            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">基本设置</label>

                <div class="layui-input-block">
                    <form:checkbox path="allowGuest" title="允许游客评论" />
                    <form:checkbox path="needVerify" title="是否需要审核" />
                    <form:checkbox path="needCode" title="开启验证码" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">评论奖励积分</label>

                <div class="layui-input-inline">
                    <form:input path="addPoint" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">评论被删扣除积分</label>

                <div class="layui-input-inline">
                    <form:input path="delPoint" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;"> </label>
                <div class="layui-input-block">
                    <form:hidden path="siteId"/>
                    <button lay-submit id="saveBtn" class="layui-btn">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script src="${ base }assets/js/fixed.js"></script>
<script>
    layui.use(['layer','form', 'element'], function () {
        var layer = layui.layer, $ = layui.$;

        $("#saveBtn").on("click", function () {
            var url = $("form").attr("action");
            var data = $("form").serialize();
            $.post(url, data, function (data) {
                if(data == "1"){
                    layer.msg("提交成功！", {icon: 1});
                }
            });
            return false;
        });
    });
</script>
</body>
</html>