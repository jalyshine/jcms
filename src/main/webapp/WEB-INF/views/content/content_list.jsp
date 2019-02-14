<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>内容管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <style type="text/css">
        .content-admin .layui-side {
            top: 0;
            bottom: 0;
            border-right: 1px solid #ccc
        }

        .content-admin .layui-body {
            top: 0;
            bottom: 0;
            overflow: hidden;
        }

        iframe {
            width: 100%;
            height: 100%;
            border: 0;
        }

        .layui-nav {
            background-color: #eee;
        }

        .layui-nav li i {
            float: left;
            font-size: 12pt;
            margin-right: 10px;
        }

        .layui-nav dd i {
            margin-left: 20px;
        }

        .layui-nav .layui-nav-item a {
            height: 32px;
            line-height: 32px;
            color: #444
        }

        .layui-nav .layui-nav-item a:hover {
            background-color: #aaa
        }

        .layui-nav .layui-nav-more {
            border-color: #444 transparent transparent;
        }

        .layui-nav-itemed > a .layui-nav-more {
            border-color: transparent transparent #444
        }

        .layui-nav-itemed > a {
            color: #444 !important;
        }

        .layui-nav .layui-this a {
            color: #444;
            background-color: #ddd;
        }

        .layui-nav .layui-nav-child dd a {
            background-color: #f4f4f4;
            color: #444
        }

        .layui-nav .layui-nav-child dd.layui-this a {
            background-color: #ddd;
            color: #444
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin content-admin">
    <div class="layui-side">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree">
                <c:forEach items="${ contentMenu }" var="menu" varStatus="status">
                    <c:if test="${ status.index == 0 }">
                        <li class="layui-nav-item layui-nav-itemed">
                    </c:if>
                    <c:if test="${ status.index > 0 }">
                        <li class="layui-nav-item">
                    </c:if>

                    <c:if test="${ !empty menu.children }">
                        <a href='javascript:;'>
                            <i class="layui-icon layui-icon-read"></i>
                            <cite>${ menu.name }</cite>
                        </a>
                        <dl class='layui-nav-child'>
                            <c:forEach items="${ menu.children }" var="child">
                                <dd>
                                    <a class="contentLink"
                                            <c:if test="${ menu.type == 1 }">
                                                href='${ base }content/${ child.tableName }/list?cid=${ child.id }'
                                            </c:if>
                                            <c:if test="${ menu.type == 2 }">
                                                href='${ base }content/SinglePage/edit?cid=${ menu.id }'
                                            </c:if>
                                    >
                                        <i class="layui-icon layui-icon-right"></i>
                                        <cite>${ child.name }</cite>
                                    </a>
                                </dd>
                            </c:forEach>
                        </dl>
                    </c:if>

                    <c:if test="${ empty menu.children }">
                        <a class="contentLink"
                                <c:if test="${ menu.type == 1 }">
                                    href='${ base }content/${ menu.tableName }/list?cid=${ menu.id }'
                                </c:if>
                                <c:if test="${ menu.type == 2 }">
                                    href='${ base }content/SinglePage/edit?cid=${ menu.id }'
                                </c:if>
                        >
                            <i class="layui-icon layui-icon-note"></i>
                            <cite>${ menu.name }</cite>
                        </a>
                    </c:if>

                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="layui-body" id="content-body">
        <iframe src="${ base }content/Category/search?site=${ param.site }"></iframe>
    </div>
</div>

<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['element'], function () {
        var $ = layui.$;

        $(".layui-nav-item").on("click", function () {
            $(this).siblings().removeClass('layui-nav-itemed');
        });

        $(".layui-nav a.contentLink").on("click", function () {
            if (parent.admin_side_spread) {
                parent.toggleAdminSite($, false, true);
            }
            $("iframe").attr("src", this.href);
            return false;
        });

        // 父页面的左侧菜单自动收缩
        $(window).on("load", function () {
            if (parent.admin_side_spread) {
                parent.toggleAdminSite($, false, true);
            }
        });

    });
</script>
</body>
</html>