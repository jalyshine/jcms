<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/修改专题内容</title>
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
<jfp:JsonDataSource value="{'0':'不分页','1':'自动分页','2':'手动分页'}" id="DB1"/>

<c:if test="${ specialContent.id == null }">
    <c:set value="${ base }special/SpecialContent/add" var="save"/>
</c:if>
<c:if test="${ specialContent.id != null }">
    <c:set value="${ base }special/SpecialContent/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑专题内容</span>
        <c:import url="special_content_menubar.jsp?sid=${ param.sid }"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="specialContent" cssClass="layui-form">

            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="isData"/>
            <!-- 所属专题id -->
            <form:hidden path="specialId" value="${ param.sid }"/>

            <div class="layui-form-item">
                <label class="layui-form-label">所属类别</label>

                <div class="layui-input-inline">
                    <form:select path="specialTypeId" items="${ specialTypes }" itemLabel="name" itemValue="id"/>
                </div>
            </div>

            <c:if test="${ specialContent.isData }">
                <div class="layui-form-item">
                    <label class="layui-form-label">肩标题</label>

                    <div class="layui-input-block">
                        <form:input path="specialContentData.title1" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
            </c:if>

            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>

                <div class="layui-input-block">
                    <form:input path="title" autocomplete="off" lay-verify="title" class="layui-input"/>
                </div>
            </div>

            <c:if test="${ specialContent.isData }">
                <div class="layui-form-item">
                    <label class="layui-form-label">副标题</label>

                    <div class="layui-input-block">
                        <form:input path="specialContentData.title2" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
            </c:if>

            <div class="layui-form-item">
                <label class="layui-form-label">关键词</label>

                <div class="layui-input-inline">
                    <form:input path="keywords" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">多关键词之间用空格或“,”隔开</div>
            </div>

            <c:if test="${ specialContent.isData }">
                <div class="layui-form-item">
                    <label class="layui-form-label">作者</label>

                    <div class="layui-input-inline">
                        <form:input path="specialContentData.author" autocomplete="off" class="layui-input"/>
                    </div>
                    <div class="layui-form-mid layui-word-aux">多作者之间用空格或“,”隔开</div>
                </div>
            </c:if>

            <c:if test="${ specialContent.isData }">
                <div class="layui-form-item">
                    <label class="layui-form-label">作者单位</label>

                    <div class="layui-input-block">
                        <form:input path="specialContentData.authorUnit" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
            </c:if>

            <div class="layui-form-item">
                <label class="layui-form-label">内容摘要</label>

                <div class="layui-input-block">
                    <form:textarea path="description" class="layui-textarea"/>
                </div>
            </div>

            <c:if test="${ specialContent.isData }">
                <div class="layui-form-item">
                    <label class="layui-form-label">文章内容</label>

                    <div class="layui-input-block">
                        <%--存放编辑器附件地址--%>
                        <input type="hidden" name="editorAttachment"/>
                        <form:textarea path="specialContentData.content" cssClass="ckeditor"/>
                    </div>
                </div>
            </c:if>

            <c:if test="${ specialContent.isData }">
                <div class="layui-form-item">
                    <label class="layui-form-label">分页方式</label>

                    <div class="layui-input-inline">
                        <form:select items="${ DB1 }" path="specialContentData.pageType"/>
                    </div>
                </div>
            </c:if>

            <div class="layui-form-item">
                <label class="layui-form-label">缩略图</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="thumb"/>
                    <div class="layui-upload-list">
                        <img id="thumb-img" style="height: 100px"
                        <c:if test="${ empty specialContent.thumb }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty specialContent.thumb }">
                            src="${ host }/${ specialContent.thumb }">
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">发布时间</label>

                <div class="layui-input-inline">
                    <form:input path="publishTimeStr" autocomplete="off" class="layui-input" readonly="readonly"/>
                </div>
            </div>

            <c:if test="${ specialContent.isData }">
                <div class="layui-form-item">
                    <label class="layui-form-label">链接地址</label>

                    <div class="layui-input-inline">
                        <form:input path="url" autocomplete="off" class="layui-input"/>
                    </div>
                    <div class="layui-input-inline">
                        <form:checkbox path="isLink" title="转向链接"/>
                    </div>
                </div>
            </c:if>

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
<script src="${ base }assets/plugins/ckeditor/ckeditor.js"></script>
<script>
    <c:if test="${ specialContent.isData }">
    var editor = CKEDITOR.replace('specialContentData.content', {
        adminUrl: '${ base }',
        imageUploadUrl: '${ host }/upload?type=ill',
        fileUploadUrl: '${ host }/upload',
        account: '${ host_account }',
        password: '${ host_password }',
        baseHref: '${ host }/'
    });
    </c:if>

    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'laydate', 'layPost', 'element'], function () {
        var $ = layui.$, form = layui.form,
            laydate = layui.laydate, layPost = layui.layPost;

        laydate.render({elem: '#publishTimeStr', type: 'datetime'});

        layPost.image('thumb', 'thumb', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');

        form.verify({
            title: function (value, item) {
                if ($.trim(value) == "") {
                    return "文章标题不能为空！";
                }
                var flag = false;
                $.ajaxSettings.async = false; // 异步提交
                $.post("${ base }special/SpecialContent/check", {'time': new Date(), 'ttl': value}, function (data) {
                    flag = data != 0 && data != $("#id").val();
                });
                $.ajaxSettings.async = true; // 恢复同步提交
                if (flag) {
                    return "该标题已经被占用！";
                }
            }
        });

        <c:if test="${ specialContent.isData }">
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
        </c:if>
    });
</script>
</body>
</html>