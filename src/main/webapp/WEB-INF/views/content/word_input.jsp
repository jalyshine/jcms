<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/修改文档</title>
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
<c:if test="${ word.id == null }">
    <c:set value="${ base }content/Word/add" var="save"/>
</c:if>
<c:if test="${ word.id != null }">
    <c:set value="${ base }content/Word/edit" var="save"/>
</c:if>

<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑文档</span>
        <c:import url="content_menubar.jsp">
            <c:param name="et" value="Word"/>
            <c:param name="etn" value="文档"/>
            <c:param name="cid" value="${ category.id }"/>
        </c:import>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="word" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <!-- 所属栏目 -->
            <form:hidden path="categoryId" value="${ category.id }"/>
            <!-- 用于栏目权限控制 -->
            <input type="hidden" name="cid" value="${ category.id }">
            <input type="hidden" name="status" value="99">

            <div class="layui-form-item">
                <label class="layui-form-label">所属栏目</label>

                <div class="layui-input-inline">
                    <label class="layui-form-label"
                           style="background-color: #E6E6E6; text-align: center;">${ category.name }</label>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>

                <div class="layui-input-block">
                    <form:input path="title" autocomplete="off" lay-verify="title" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">摘要</label>

                <div class="layui-input-block">
                    <form:textarea path="description" class="layui-textarea"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">发布时间</label>

                <div class="layui-input-inline">
                    <form:input path="publishTimeStr" autocomplete="off" class="layui-input" readonly="readonly"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">缩略图</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="thumb"/>
                    <div class="layui-upload-list">
                        <img id="thumb-img" style="height: 100px"
                        <c:if test="${ empty word.thumb }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty word.thumb }">
                            src="${ host }/${ word.thumb }">
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
                        <c:if test="${ empty word.banner }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty word.banner }">
                            src="${ host }/${ word.banner }">
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图标</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="icon"/>
                    <div class="layui-upload-list">
                        <img id="icon-img" style="height: 100px"
                        <c:if test="${ empty word.icon }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty word.icon }">
                            src="${ host }/${ word.icon }">
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">内容</label>

                <div class="layui-input-block">
                    <%--存放编辑器附件地址--%>
                    <input type="hidden" name="editorAttachment"/>
                    <form:textarea path="content" cssClass="ckeditor"/>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit id="saveBtn" class="layui-btn">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script src="${ base }assets/js/fixed.js"></script>
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
    }).use(['form', 'laydate', 'layPost', 'element'], function () {
        var $ = layui.$, form = layui.form,
            laydate = layui.laydate, layPost = layui.layPost;

        laydate.render({elem: '#publishTimeStr', type: 'datetime'});

        layPost.image('thumb', 'customer', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');
        layPost.image('banner', 'customer', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');
        layPost.image('icon', 'customer', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');

        form.verify({
            title: function (value, item) {
                if ($.trim(value) == "") {
                    return "标题不能为空！";
                }
                var flag = false;
                $.ajaxSettings.async = false; // 异步提交
                $.post("${ base }content/Word/check", {'time': new Date(), 'ttl': value}, function (data) {
                    flag = data != 0 && data != $("#id").val();
                });
                $.ajaxSettings.async = true; // 恢复同步提交
                if (flag) {
                    return "该标题已经被占用！";
                }
            }
        });

        $("#saveBtn").on("click", function () {
            var $content = $("<div></div>").html(editor.document.getBody().getHtml());
            var filePath = "";
            $content.find("[src]").each(function (index) {
                var src = $(this).attr("src");
                if (src.indexOf("http://") == -1 && src.indexOf("https://") == -1) {
                    filePath += $(this).attr("src") + ",";
                }
            });
            $(":hidden[name='editorAttachment']").val(filePath.substring(0, filePath.length - 1));
            $("form").submit();
            return false;
        });
    });
</script>
</body>
</html>