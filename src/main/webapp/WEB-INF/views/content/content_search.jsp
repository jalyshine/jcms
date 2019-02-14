<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>搜索栏目</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <style type="text/css">
        body { background-color: #f2f2f2; }
        .center{ width: 50%; margin: 15% auto 0 auto; }
    </style>
</head>
<body>

<form action="" method="post" class="layui-form">
    <div class="center">
        <select lay-search lay-filter="category">
            <option>请输入要查找的栏目名称</option>
            <c:forEach items="${ categoryTree }" var="category">
                <c:if test="${ category.modelId != null }">
                    <option value="${ base }content/${ category.model.tableName }/list?cid=${category.id}&sta=99">${ category.name }</option>
                </c:if>
                <c:if test="${ category.type == 2 && category.modelId == null }">
                    <option value="${ base }content/SinglePage/edit?cid=${category.id}">${ category.name }</option>
                </c:if>
            </c:forEach>
        </select>
    </div>
</form>

<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form;

        form.on("select(category)", function(data){
            window.location.href = data.value;
        });

    });
</script>
</body>
</html>