<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>发布/修改通告</title>
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
<c:if test="${ announce.id == null }">
    <c:set value="${ base }announce/Announce/add" var="save"/>
</c:if>
<c:if test="${ announce.id != null }">
    <c:set value="${ base }announce/Announce/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑通告</span>
        <c:import url="../menubar.jsp?module=announce"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="announce" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>

                <div class="layui-input-block">
                    <form:input path="title" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">起始时间</label>

                <div class="layui-input-inline">
                    <form:input path="startTimeStr" autocomplete="off" class="layui-input" readonly="readonly"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">截止时间</label>

                <div class="layui-input-inline">
                    <form:input path="endTimeStr" autocomplete="off" class="layui-input" readonly="readonly"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">内容</label>

                <div class="layui-input-block">
                    <form:textarea path="content" autocomplete="off" class="layui-textarea"/>
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
<script type="text/javascript">
    layui.use(['form', 'laydate', 'element'], function () {
        var laydate = layui.laydate;
        laydate.render({elem: '#startTimeStr', type: 'datetime'});
        laydate.render({elem: '#endTimeStr', type: 'datetime'});
    });
</script>
</body>
</html>