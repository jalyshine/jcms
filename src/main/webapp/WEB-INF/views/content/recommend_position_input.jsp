<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/修改推荐位</title>
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
<c:if test="${ recommendPosition.id == null }">
    <c:set value="${ base }content/RecommendPosition/add" var="save"/>
</c:if>
<c:if test="${ recommendPosition.id != null }">
    <c:set value="${ base }content/RecommendPosition/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑推荐位</span>
        <c:import url="../menubar.jsp?module=content" />
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="recommendPosition" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">推荐位名称</label>

                <div class="layui-input-block">
                    <form:input path="name" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">所属模型</label>

                <div class="layui-input-inline">
                    <form:select path="modelId" lay-filter="model">
                        <option value="">===请选择模型===</option>
                        <form:options items="${ models }" itemLabel="name" itemValue="id"/>
                    </form:select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">所属栏目</label>

                <div class="layui-input-inline">
                    <form:select path="categoryId">
                        <option  value="">===请选择栏目===</option>
                    </form:select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">最大条数</label>

                <div class="layui-input-inline">
                    <form:input path="maxItems" autocomplete="off" class="layui-input"/>
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
    layui.use(['form', 'element'], function () {
        var $ = layui.$, form = layui.form;
        var map = eval('(${ categories })');

        form.on("select(model)", function (data) {
            var id = data.value;
            if(id != '0'){
                var list = map[id];
                var html = "<option value=''>== 请选择栏目 ==</option>";
                for (var i = 0; i < list.length; i++) {
                    html += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
                $("select[name='categoryId']").html(html);
                form.render("select");
            }
        });
    });
</script>

</body>
</html>