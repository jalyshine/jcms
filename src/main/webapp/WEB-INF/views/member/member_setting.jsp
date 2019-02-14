<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员模块设置</title>
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
        <span class="card-title">会员模块设置</span>
    </div>
    <div class="layui-card-body">
        <form:form action="${ base }member/MemberSetting/manage" modelAttribute="memberSetting" method="post" cssClass="layui-form">

            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">基本设置</label>

                <div class="layui-input-block">
                    <form:checkbox path="allowReg" title="允许新人注册" />
                    <form:checkbox path="selectModel" title="注册选择模型" />
                    <form:checkbox path="needEmail" title="需要邮件验证" />
                    <form:checkbox path="needCode" title="开启验证码" />
                    <form:checkbox path="needPhone" title="手机验证方式" />
                    <form:checkbox path="needAdmin" title="注册需要审核" />
                    <form:checkbox path="integralModel" title="启用积分兑换" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">1元可购买积分数</label>

                <div class="layui-input-inline">
                    <form:input path="integralPrice" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">新会员默认点数</label>

                <div class="layui-input-inline">
                    <form:input path="initPoint" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">注册默认赠送资金</label>

                <div class="layui-input-inline">
                    <form:input path="initMoney" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">是否显示注册协议</label>

                <div class="layui-input-inline">
                    <form:checkbox path="showAgree" title="显示注册协议" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">会员注册协议</label>

                <div class="layui-input-inline" style="width: 620px;">
                    <form:textarea path="regAgree" class="layui-textarea"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">邮件认证内容</label>

                <div class="layui-input-inline" style="width: 620px;">
                    <form:textarea path="emailVerify" class="layui-textarea"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 120px;">密码找回邮件内容</label>

                <div class="layui-input-inline" style="width: 620px;">
                    <form:textarea path="emailPassword" class="layui-textarea"/>
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
    layui.config({
        base: '${ base }assets/js/'
    }).use(['layPost','form', 'element'], function () {
        var layer = layui.layer, $ = layui.$, layPost = layui.layPost;

        $("#saveBtn").on("click", function () {
            var url = $("form").attr("action");
            var data = $("form").serialize();
            layPost.post(url, data);
            return false;
        });
    });
</script>
</body>
</html>