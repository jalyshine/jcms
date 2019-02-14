<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>警告</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <style type="text/css">
        body{ background-color: #eee; }
        .icon { width: 500px; text-align: center; padding-top: 20px;
            margin: 0 auto; border-bottom: 5px solid #1AA094; }
        .icon i{ font-size: 260pt; }
        .text{width: 100%; text-align: center; font-size: 15pt; margin-top: 20px; }
    </style>
</head>
<body>
<div class="icon">
    <i class="layui-icon layui-icon-face-surprised"></i>
</div>
<div class="text">
    <c:choose>
        <c:when test="${ type == 'banned' }">此IP被禁止访问...</c:when>
        <c:when test="${ type == 'repeat' }">请勿重复提交表单...</c:when>
    </c:choose>
</div>
</body>
</html>
