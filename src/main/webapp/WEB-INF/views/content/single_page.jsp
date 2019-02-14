<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>单个网页</title>
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
		<span class="card-title">${ category.name }</span>
	</div>
	<div class="layui-card-body">
		<form:form action="${ base }content/SinglePage/edit" method="post" modelAttribute="singlePage" cssClass="layui-form">
			<!-- 防止表单重复提交 -->
			<jfp:token />
			<form:hidden path="categoryId"/>
			<form:hidden path="id"/>
			<!-- 用于栏目权限控制 -->
			<input type="hidden" name="cid" value="${ category.id }">

			<div class="layui-form-item">
				<label class="layui-form-label">网页标题</label>

				<div class="layui-input-block">
					<form:input path="title" autocomplete="off" lay-verify="title" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">关键词</label>

				<div class="layui-input-block">
					<form:input path="keywords" placeholder="多个关键词用英文,隔开" autocomplete="off" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">描述</label>

				<div class="layui-input-block">
					<form:textarea path="description" autocomplete="off" class="layui-textarea"/>
				</div>
			</div>
			<c:if test="${ singlePage.updateTime != null }">
				<div class="layui-form-item">
					<label class="layui-form-label">更新时间</label>

					<div class="layui-input-inline">
						<label class="layui-form-label" style="background-color: #E6E6E6; text-align: center; width: 200px;">
							<fmt:formatDate value="${ singlePage.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</label>
					</div>
				</div>
			</c:if>
			<div class="layui-form-item">
				<label class="layui-form-label">焦点图</label>

				<div class="layui-input-block layui-upload">
					<input type="checkbox" lay-filter="upload" title="上传原图">
					<form:hidden path="thumb"/>
					<div class="layui-upload-list">
						<img id="thumb-img" style="height: 100px"
						<c:if test="${ empty singlePage.thumb }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty singlePage.thumb }">
							src="${ host }/${ singlePage.thumb }">
						</c:if>
					</div>
				</div>
			</div>
            <div class="layui-form-item">
                <label class="layui-form-label">网页内容</label>

                <div class="layui-input-block">
                        <%--存放编辑器附件地址--%>
                    <input type="hidden" name="editorAttachment"/>
                    <form:textarea path="content" cssClass="ckeditor"/>
                </div>
            </div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button lay-submit id="saveBtn" class="layui-btn" >提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
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

    function CK_update() {
        for (var instance in CKEDITOR.instances)
            CKEDITOR.instances[instance].updateElement();
    }

    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'layPost', 'form'], function () {
        var $ = layui.$, layPost = layui.layPost, form = layui.form;

        var uploadInst = layPost.image('thumb', 'thumb', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');

        form.on('checkbox(upload)', function(data){
            if(data.elem.checked){
                uploadInst.config.url = '${ host }/upload?type=customer';
            } else {
                uploadInst.config.url = '${ host }/upload?type=thumb';
            }
        });

        $("#saveBtn").on("click", function () {
            CK_update();
            var $content = $("<div></div>").html(editor.document.getBody().getHtml());
            var filePath = "";
            $content.find("[src]").each(function (index) {
                var src = $(this).attr("src");
                if (src.indexOf("http://") == -1 && src.indexOf("https://") == -1) {
                    filePath += $(this).attr("src") + ",";
                }
            });
            $(":hidden[name='editorAttachment']").val(filePath.substring(0, filePath.length - 1));
            var url = $("form").attr("action");
            var data = $("form").serialize();
            layPost.post(url, data, function (data) {
                return "编辑成功！";
            });
            return false;
        });
    });
</script>
</body>
</html>