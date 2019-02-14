<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/修改来源</title>
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
<c:if test="${ copyFrom.id == null }">
    <c:set value="${ base }content/CopyFrom/add" var="save"/>
</c:if>
<c:if test="${ copyFrom.id != null }">
    <c:set value="${ base }content/CopyFrom/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑来源</span>
        <c:import url="../menubar.jsp?module=content"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="copyFrom" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">网站名称</label>

                <div class="layui-input-block">
                    <form:input path="name" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">网站地址</label>

                <div class="layui-input-block">
                    <form:input path="url" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">缩略图</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="thumb"/>
                    <div class="layui-upload-list">
                        <img id="thumb-img" style="height: 100px"
                        <c:if test="${ empty copyFrom.thumb }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty copyFrom.thumb }">
                            src="${ host }/${ copyFrom.thumb }">
                        </c:if>
                    </div>
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
<script type="text/javascript" src="${ base }assets/plugins/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'layPost', 'element'], function () {
        var layPost = layui.layPost;
        layPost.image('thumb', 'icon', '${ base }', '${ host }', '${ host_account }', '${ host_password }');
    });
</script>
</body>
</html>