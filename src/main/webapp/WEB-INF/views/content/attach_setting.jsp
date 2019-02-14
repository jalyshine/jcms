<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>附件配置</title>
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
<jfp:JsonDataSource value='{"0":"随机位置","1":"顶部靠左","2":"顶部居中","3":"顶部靠右","4":"中间靠左",
                            "5":"中间居中","6":"中间靠右","7":"底部靠左","8":"底部居中","9":"底部靠右" }' id="DB_POS"/>

<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">附件配置</span>
    </div>
    <div class="layui-card-body">
        <form:form action="${ base }admin/AttachSetting/manage" method="post" modelAttribute="attachSetting" cssClass="layui-form">

            <form:hidden path="siteId" />

            <div class="layui-form-item">
                <label class="layui-form-label">附件大小</label>

                <div class="layui-input-inline">
                    <form:input path="limitSize" lay-verify="number" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">允许上传附件的最大值（KB）</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">附件类型</label>

                <div class="layui-input-inline">
                    <form:input path="allowExtension" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">格式为：jpg|jpeg|gif|bmp|png</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">开启水印</label>

                <div class="layui-input-block">
                    <form:checkbox path="enableMark" title="开启"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">水印条件</label>

                <div class="layui-input-block">
                    <label class="layui-form-label" style="width: 20px;">宽</label>
                    <div class="layui-input-inline">
                        <form:input path="markWidth" lay-verify="number" autocomplete="off" cssClass="layui-input"/>
                    </div>
                    <label class="layui-form-label" style="width: 20px;">高</label>
                    <div class="layui-input-inline">
                        <form:input path="markHeight" lay-verify="number" autocomplete="off" cssClass="layui-input"/>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">水印图片</label>

                <div class="layui-input-inline">
                    <form:hidden path="mark"/>
                    <div class="layui-upload-list">
                        <img id="mark-img" style="height: 100px"
                        <c:if test="${ empty attachSetting.mark }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty attachSetting.mark }">
                            src="${ host }/${ attachSetting.mark }">
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">水印透明度</label>

                <div class="layui-input-inline">
                    <form:input path="markOpacity" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">请设置为0-100之间的数字，0代表完全透明，100代表不透明</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">水印质量</label>

                <div class="layui-input-inline">
                    <form:input path="markQuality" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">水印质量请设置为0-100之间的数字,决定 jpg 格式图片的质量</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">水印位置</label>

                <div class="layui-input-inline">
                    <form:select path="markPosition" items="${ DB_POS }"/>
                </div>
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
        var $ = layui.$, layPost = layui.layPost;

        layPost.image('mark', 'customer', '${ base }', '${ host }', '${ host_account }', '${ host_password }');

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