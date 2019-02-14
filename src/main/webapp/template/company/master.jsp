<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>

<jcp:MasterPage id="master">
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" id="fusion-style-css" href="${ root }assets/css/style.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="${ root }assets/css/global.css" type="text/css"/>
        <script type="text/javascript" src="${ root }assets/js/jquery.min.js"></script>
        <jcp:ContentPlaceHolder id="head"/>
    </head>
    <body class="home blog et_frontend_customizer gecko et_includes_sidebar">
    <%--查询站点信息--%>
    <jfp:SqlDataSource id="DB0" cmd="SELECT * FROM site_info LEFT JOIN site ON id = site_id WHERE id = ${ siteId }" />
    <jfp:SingleItem dataSourceID="DB0" var="site" />

    <!--巨幕-->
    <jfp:SqlDataSource id="DB1" cmd="SELECT * FROM tip p LEFT JOIN tip_type t ON p.type_id = t.id
                                     WHERE t.site_id = ${ siteId } AND name='首页巨幕'"/>
    <div id="et-slider-wrapper" class="et_slider_auto et_slider_speed_7000">
        <div id="et-slides">
            <jfp:Repeater dataSourceID="DB1" var="item">
                <div class="et-slide" style="background-image: url(${ site.domain }/${ item.banner });">
                    <a href="${ item.url }">
                    <div class="container clearfix">
                        <div class="featured-image">
                            <img src="${ site.domain }/${ item.icon }" width="250" height="90" />
                        </div>
                        <div class="description">
                            <h2><a href="javascript:;">${ item.title }</a></h2>
                            <p>${ item.description }</p>
                        </div>
                    </div>
                    </a>
                </div>
            </jfp:Repeater>
        </div>
    </div>
    <!--栏目导航-->
    <jfp:SqlDataSource id="DB2" cmd="SELECT * FROM category WHERE parent_id is null AND site_id = ${ siteId }"/>
    <div class="Maintop">
        <div class="top_1200">
            <div class="logo">
                <img src="${ site.domain }/${ site.logo }" />
            </div>
            <div class="nav_list">
                <a href='<jcp:Url jsp="${ root }index.jsp?id=${ site.id }"
                                  html="${ root }index.html"
                                  isAuto="${ param.auto != null }"/>'>首页</a>
                <jfp:Repeater dataSourceID="DB2" var="item" index="i">
                    <jfp:JsonDataSource id="style" value="${ item.style }"/>
                    | <c:if test="${ item.type == 1 }">
                        <a href='<jcp:Url jsp="${ root }content/${ style.home }?id={1}"
                                          html="${ root }${ item.dir_name }/{1}_1.html"
                                          isAuto="${ param.auto != null }"
                                          data="{${ item.id }}" />'>${ item.name }</a>
                    </c:if>
                    <c:if test="${ item.type == 2 }">
                        <a href='<jcp:Url jsp="${ root }content/${ style.page }?id={1}"
                                          html="${ root }${ item.dir_name }/{1}.html"
                                          isAuto="${ param.auto != null }"
                                          data="{${ item.id }}" />'>${ item.name }</a>
                    </c:if>
                    <c:if test="${ item.type == 3 }">
                        <a href="${ item.url }">${ item.name }</a>
                    </c:if>
                </jfp:Repeater>
            </div>
        </div>
    </div>
    <div class="topbj"></div>

    <!--模板内容-->
    <jcp:ContentPlaceHolder id="pageContent"/>

    <!--版权信息-->
    <div class="footer">
        <p>
            ${ site.copy_right } <br/>
            ${ site.address } ${ site.telephone } ICP：${ site.icp }
            <a href="http://www.tjcj-soft.com/jcms">后台管理</a>
        </p>
    </div>

    <script type="text/javascript" src="${ root }assets/js/superfish.js"></script>
    <script type="text/javascript" src="${ root }assets/js/jquery.fitvids.js"></script>
    <script type="text/javascript" src="${ root }assets/js/custom.js"></script>
    <script type="text/javascript" src="${ root }assets/js/modernizr.js"></script>
    </body>
    </html>
</jcp:MasterPage>