<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加/编辑贴士</title>
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
<c:if test="${ tip.id == null }">
    <c:set value="${ base }tip/Tip/add" var="save"/>
</c:if>
<c:if test="${ tip.id != null }">
    <c:set value="${ base }tip/Tip/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑贴士</span>
        <c:import url="../menubar.jsp?module=tip"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="tip" cssClass="layui-form">

            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">所属分类</label>

                <div class="layui-input-inline">
                    <form:select path="typeId" lay-verify="required">
                        <option value="">请选择分类</option>
                        <form:options items="${ tipTypes }" itemLabel="name" itemValue="id"/>
                    </form:select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>

                <div class="layui-input-block">
                    <form:input path="title" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">链接地址</label>

                <div class="layui-input-block">
                    <form:input path="url" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>

                <div class="layui-input-block">
                    <form:input path="description" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">图标</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="icon"/>
                    <div class="layui-upload-list">
                        <img id="icon-img" style="height: 100px"
                        <c:if test="${ empty tip.icon }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty tip.icon }">
                            src="${ host }/${ tip.icon }">
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">横幅</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="banner"/>
                    <div class="layui-upload-list">
                        <img id="banner-img" style="height: 100px"
                        <c:if test="${ empty tip.banner }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty tip.banner }">
                            src="${ host }/${ tip.banner }">
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">内容</label>

                <div class="layui-input-block">
                        <%--存放编辑器附件地址--%>
                    <input type="hidden" name="editorAttachment"/>
                    <form:textarea path="content" autocomplete="off" class="ckeditor"/>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit="ok" class="layui-btn">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>

        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ base }assets/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${ base }assets/js/fixed.js"></script>
<script src="${ base }assets/plugins/ckeditor/ckeditor.js"></script>
<script>
    var editor = CKEDITOR.replace('content', {
        adminUrl: '${ base }',
        imageUploadUrl: '${ host }/upload?type=ill',
        fileUploadUrl: '${ host }/upload',
        account: '${ host_account }',
        password: '${ host_password }',
        baseHref: '${ host }/'
    });

    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'layPost', 'element'], function () {
        var form = layui.form, layPost = layui.layPost, $ = layui.$;

        layPost.image('banner', 'customer', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');
        layPost.image('icon', 'customer', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');

        form.on("submit(ok)", function () {
            var $content = $("<div></div>").html(editor.document.getBody().getHtml());
            var filePath = "";
            $content.find("[src]").each(function (index) {
                var src = $(this).attr("src");
                if (src.indexOf("http://") == -1 && src.indexOf("https://") == -1) {
                    filePath += $(this).attr("src") + ",";
                }
            });
            $(":hidden[name='editorAttachment']").val(filePath.substring(0, filePath.length - 1));
            return false;
        });
    });
</script>
</body>
</html>