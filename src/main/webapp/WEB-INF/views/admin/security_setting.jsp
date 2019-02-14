<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>安全配置</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <style type="text/css">
        .form-title { width: 150px; }
    </style>
</head>
<body>

<jfp:JsonDataSource id="DB1" value="{'info':'info','warn':'warn','error':'error','fatal':'fatal'}" />

<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">安全配置</span>
    </div>
    <div class="layui-card-body">
        <form:form action="${ base }admin/SecuritySetting/manage" method="post" modelAttribute="securitySetting" cssClass="layui-form">

            <div class="layui-form-item">
                <label class="layui-form-label form-title">后台管理操作日志</label>

                <div class="layui-input-inline">
                    <form:checkbox path="allowOperLogger" title="启用"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label form-title">后台最大登陆失败次数</label>

                <div class="layui-input-inline">
                    <form:input path="maxLoginTimes" autocomplete="off" lay-verify="number" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">建议设为3-10之间</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label form-title">后台访问域名</label>

                <div class="layui-input-inline">
                    <form:input path="backDomain" autocomplete="off" placeholder="http://" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">例如：admin.domain.com，绑定后，只能通过该域名登陆。</div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; color: gray;">
                <legend>错误日志设置</legend>
            </fieldset>

            <div class="layui-form-item">
                <label class="layui-form-label form-title">日志级别</label>

                <div class="layui-input-inline">
                    <form:select path="errorLogSetting.status" items="${ DB1 }"/>
                </div>
                <div class="layui-form-mid layui-word-aux">建议设为error级</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label form-title">配置生效时间</label>

                <div class="layui-input-inline">
                    <form:input path="errorLogSetting.monitorInterval" autocomplete="off" lay-verify="number" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">分钟</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label form-title">单个日志最大体积</label>

                <div class="layui-input-inline">
                    <form:input path="errorLogSetting.maxSize" autocomplete="off" lay-verify="number" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">MB</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label form-title">保留日志文件个数</label>

                <div class="layui-input-inline">
                    <form:input path="errorLogSetting.maxFiles" autocomplete="off" lay-verify="number" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label form-title">创建新日志周期</label>

                <div class="layui-input-inline">
                    <form:input path="errorLogSetting.rollingInterval" autocomplete="off" lay-verify="number" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">天</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label form-title"> </label>

                <div class="layui-input-block">
                    <button lay-submit="" id="submit" class="layui-btn">提交</button>
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
    }).use(['form', 'layPost'], function () {
        var layer = layui.layer, $ = layui.$, layPost = layui.layPost;

        $("#maxLoginTimes").on("change", function () {
            var times = $(this).val();
            if(times < 3 || times > 10){
                layer.alert("建议设为3-10之间");
                $(this).val("5");
            }
        });

        $("#submit").on("click", function () {
            var url = $("form").attr("action");
            var arg = $("form").serialize();
            layPost.post(url, arg);
            return false;
        });
    });
</script>
</body>
</html>