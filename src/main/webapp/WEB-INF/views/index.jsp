<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>后台管理页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/index.css"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">风鸟云建站系统</div>
        <div class="admin-oper">
            <ul class="layui-nav admin-nav-bar">
                <li class="layui-nav-item">
                    <a href="javascript:;" id="admin-side-toggle">
                        <i class="layui-icon layui-icon-shrink-right"></i>
                    </a>
                </li>
                <c:forEach items="${ navs }" var="nav" varStatus="tts">
                    <li class="layui-nav-item">
                        <a href="${ base }admin/BackMenu/list?parent=${ nav.id }" class="nav_lnk"
                           data-index="${ tts.index }">${ nav.name }</a>
                    </li>
                </c:forEach>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="${ base }template/${ uiStyle }/index.jsp?id=${ param.site }" target="_blank" title="站点主页">
                        <i class="layui-icon" style="font-size: 15pt">&#xe66c;</i>
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="${ base }disk" title="资源管理器" id="file-explorer">
                        <i class="layui-icon" style="font-size: 15pt">&#xe632;</i>
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" class="admin-header-user">
                        <img src="${ base }${ admin.face }" class="layui-nav-img"/>
                        <span>${ admin.userName }</span>
                    </a>
                    <dl class="layui-nav-child">
                        <dd id="lock">
                            <a href="javascript:;">
                                <i class="layui-icon">&#xe673;</i>
                                &nbsp;&nbsp;<cite>锁屏</cite>
                            </a>
                        </dd>
                        <dd>
                            <a href="${ base }admin/Admin/logout">
                                <i class="layui-icon">&#xe66f;</i>
                                &nbsp;&nbsp;<cite>注销</cite>
                            </a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-side">
        <div class="layui-side-scroll">
            <div id="admin-navbar-side" lay-filter="side"></div>
        </div>
    </div>
    <div class="layui-body">
        <div class="layui-tab layui-tab-brief admin-nav-card" lay-filter="admin-tab">
            <ul class="layui-tab-title">
                <li class="admin-tab-box">
                    <i class="layui-icon">&#xe61a;</i>
                </li>
                <li class="layui-this admin-tab-home">
                    <i class="layui-icon" aria-hidden="true">&#xe68e;</i>
                </li>
            </ul>
            <div class="layui-tab-content admin-tab-content">
                <div class="layui-tab-item layui-hide" id="page-box">
                    <dl class="layui-nav-child layui-show">
                        <dd><a href="#" class="page-refresh">刷新本页</a></dd>
                        <dd><a href="#" class="page-closeAll">关闭所有</a></dd>
                        <dd><a href="#" class="page-closeOther">关闭其他</a></dd>
                    </dl>
                </div>
                <div class="layui-tab-item layui-show">
                    <iframe src="${ base }admin/Admin/home"></iframe>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-footer">
        <img src="${ base }assets/css/img/logo-foot.png" /> &nbsp;风鸟云建站系统 2019-2024 &copy; www.tjcj-soft.com
    </div>
</div>

<!--锁屏模板 start-->
<script type="text/template" id="lock-temp">
    <div class="admin-header-lock">
        <div class="admin-face">
            <img src="${ base }${ admin.face }"/>
            <span>${ admin.userName }</span>
        </div>
        <div class="layui-input-inline">
            <input type="hidden" value="${ admin.userName }" id="lockUserName">
            <input type="password" class="layui-input" placeholder="请输入密码" id="lockPwd"/>
        </div>
        <button class="layui-btn" id="unlock">解锁</button>
    </div>
</script>

<!-- 传入上下文路径 -->
<input type="hidden" value="${ base }" id="baseUrl">

<!--侧栏菜单 -->
<c:forEach items="${ navs }" var="x" varStatus="status_x">
    <script type="text/template" id="siteMenu${ status_x.index }">
        <ul class="layui-nav layui-nav-tree">
            <c:forEach items="${ x.childs }" var="y" varStatus="status_y">
                <c:if test="${ status_y.index == 0 }">
                    <li class="layui-nav-item layui-nav-itemed">
                </c:if>
                <c:if test="${ status_y.index != 0 }">
                    <li class="layui-nav-item">
                </c:if>
                <a href='javascript:;'>
                    <i class="layui-icon" aria-hidden="true">${ y.icon }</i>
                    <cite>${ y.name }</cite>
                </a>
                <dl class='layui-nav-child'>
                    <c:forEach items="${ y.childs }" var="z">
                        <dd title="${ z.name }">
                            <a class="tabUrlLink"
                                    <c:if test="${ fn:endsWith(z.action, ':ajax') }">
                                        href="${ base }${ z.module }/${ z.entity }/${z.action}" data-param="${ z.data }"
                                    </c:if>
                                    <c:if test="${ !fn:endsWith(z.action, ':ajax') }">
                                        href="${ base }${ z.module }/${ z.entity }/${z.action}?menu=${ z.id }<c:if
                                            test="${ !empty z.data }">&${z.data}</c:if>"
                                    </c:if>
                            >
                                <i class="layui-icon" aria-hidden="true">&#xe602;</i>
                                <cite>${ z.name }</cite>
                            </a>
                        </dd>
                    </c:forEach>
                </dl>
                </li>
            </c:forEach>
        </ul>
    </script>
</c:forEach>

<script src="${ base }assets/plugins/layui/layui.js"></script>
<script src="${ base }assets/js/index.js"></script>
</body>
</html>