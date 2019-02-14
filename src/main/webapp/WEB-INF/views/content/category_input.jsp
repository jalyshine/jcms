<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/修改栏目</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <style type="text/css">
        .layui-form-label {
            font-size: 10pt;
        }
    </style>
</head>
<body>
<c:if test="${ category.id == null }">
    <c:set value="${ base }content/Category/add" var="save"/>
</c:if>
<c:if test="${ category.id != null }">
    <c:set value="${ base }content/Category/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑栏目</span>
        <c:import url="../menubar.jsp?module=content"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="category" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>
            <form:hidden path="type"/>

            <div class="layui-form-item">
                <label class="layui-form-label">上级栏目</label>

                <div class="layui-input-inline">
                    <form:select path="parentId">
                        <option value="">= 作为一级栏目 =</option>
                        <form:options items="${ categoryTree }" itemLabel="name" itemValue="id"/>
                    </form:select>
                </div>
            </div>
            <c:if test="${ category.type == 1 }">
                <div class="layui-form-item">
                    <label class="layui-form-label">请选择模型</label>

                    <div class="layui-input-inline">
                        <form:select path="modelId">
                            <option value="">请选择模型</option>
                            <form:options items="${ models }" itemLabel="name" itemValue="id"/>
                        </form:select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">工作流</label>

                    <div class="layui-input-inline">
                        <form:select path="workFlowId">
                            <option value="">不需要审核</option>
                            <form:options items="${ workFlows }" itemLabel="name" itemValue="id"/>
                        </form:select>
                    </div>
                </div>
            </c:if>
            <div class="layui-form-item">
                <label class="layui-form-label">栏目名称</label>

                <div class="layui-input-block">
                    <form:input path="name" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">英文目录</label>

                <div class="layui-input-block">
                    <form:input path="dirName" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">栏目描述</label>

                <div class="layui-input-block">
                    <form:textarea path="description" autocomplete="off" class="layui-textarea"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">横幅</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="banner"/>
                    <div class="layui-upload-list">
                        <img id="banner-img" style="height: 100px"
                        <c:if test="${ empty category.banner }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty category.banner }">
                            src="${ host }/${ category.banner }">
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">缩略图</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="thumb"/>
                    <div class="layui-upload-list">
                        <img id="thumb-img" style="height: 100px"
                        <c:if test="${ empty category.thumb }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty category.thumb }">
                            src="${ host }/${ category.thumb }">
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
                        <c:if test="${ empty category.icon }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty category.icon }">
                            src="${ host }/${ category.icon }">
                        </c:if>
                    </div>
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; color: gray;">
                <legend>模板设置</legend>
            </fieldset>

            <c:if test="${ category.type == 1 }">
                <div class="layui-form-item">
                    <label class="layui-form-label">栏目页模板</label>

                    <div class="layui-input-inline">
                        <form:select path="styleOption.home">
                            <form:option value="">请选择</form:option>
                            <form:options items="${ template_home }" />
                        </form:select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">详情页模板</label>

                    <div class="layui-input-inline">
                        <form:select path="styleOption.show">
                            <form:option value="">请选择</form:option>
                            <form:options items="${ template_show }" />
                        </form:select>
                    </div>
                </div>
            </c:if>

            <c:if test="${ category.type == 2 }">
                <div class="layui-form-item">
                    <label class="layui-form-label">单网页模板</label>

                    <div class="layui-input-inline">
                        <form:select path="styleOption.page">
                            <form:option value="">请选择</form:option>
                            <form:options items="${ template_page }" />
                        </form:select>
                    </div>
                </div>
            </c:if>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; color: gray;">
                <legend>SEO设置</legend>
            </fieldset>

            <div class="layui-form-item">
                <label class="layui-form-label">栏目页标题</label>

                <div class="layui-input-block">
                    <form:input path="metaOption.title" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">栏目页关键词</label>

                <div class="layui-input-block">
                    <form:input path="metaOption.keyword" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">栏目页描述</label>

                <div class="layui-input-block">
                    <form:textarea path="metaOption.description" autocomplete="off" class="layui-textarea"/>
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
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'element', 'layPost'], function () {
        var layPost = layui.layPost;
        layPost.image('banner', 'customer', '${ base }', '${ host }', '${ host_account }', '${ host_password }');
        layPost.image('thumb', 'customer', '${ base }', '${ host }', '${ host_account }', '${ host_password }');
        layPost.image('icon', 'customer', '${ base }', '${ host }', '${ host_account }', '${ host_password }');
    });
</script>
</body>
</html>