<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<span class="layui-breadcrumb">
    <a href="${ base }special/SpecialContent/add?sid=${ param.sid }">添加信息</a>
    <a href="${ base }special/SpecialContent/list?sid=${ param.sid }">管理信息</a>
    <a href="${ base }special/SpecialContent/load?sid=${ param.sid }">导入信息</a>
    <a href="${ base }special/Special/list">返回专题管理</a>
</span>