<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加/编辑友情链接</title>
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
<jfp:JsonDataSource value="{'1':'LOGO链接','2':'文字链接'}" id="DB1"/>

<c:if test="${ friendlyLink.id == null }">
    <c:set value="${ base }link/FriendlyLink/add" var="save"/>
</c:if>
<c:if test="${ friendlyLink.id != null }">
    <c:set value="${ base }link/FriendlyLink/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑友情链接</span>
        <c:import url="../menubar.jsp?module=link"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="friendlyLink" cssClass="layui-form">

            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">所属分类</label>

                <div class="layui-input-inline">
                    <form:select path="typeId" lay-verify="required">
                        <option value="">请选择分类</option>
                        <form:options items="${ friendlyLinkTypes }" itemLabel="name" itemValue="id"/>
                    </form:select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">链接类型</label>

                <div class="layui-input-block">
                    <form:radiobuttons path="linkFrom" items="${ DB1 }"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">网站名称</label>

                <div class="layui-input-block">
                    <form:input path="name" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">网站地址</label>

                <div class="layui-input-block">
                    <form:input path="url" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">图片logo</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="logo"/>
                    <div class="layui-upload-list">
                        <img id="logo-img" style="height: 100px"
                        <c:if test="${ empty friendlyLink.logo }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty friendlyLink.logo }">
                            src="${ host }/${ friendlyLink.logo }">
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">网站联系人</label>

                <div class="layui-input-block">
                    <form:input path="userName" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">联系电话</label>

                <div class="layui-input-block">
                    <form:input path="phone" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">网站介绍</label>

                <div class="layui-input-block">
                    <form:textarea path="description" autocomplete="off" class="layui-textarea"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">是否推荐</label>

                <div class="layui-input-block">
                    <form:checkbox path="elite" title="是"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">是否通过</label>

                <div class="layui-input-block">
                    <form:checkbox path="passed" title="是"/>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit="" id="submit" class="layui-btn">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>

        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ base }assets/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${ base }assets/js/fixed.js"></script>
<script type="text/javascript">
    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'layPost', 'element'], function () {
        var layPost = layui.layPost;
        layPost.image('logo', 'customer', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');
    });
</script>
</body>
</html>