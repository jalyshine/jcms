<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>

<jcp:MasterPage id="master">
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" type="text/css" href="${ root }assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${ root }assets/css/main.css"/>
        <script src="${ root }assets/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
        <jcp:ContentPlaceHolder id="head"/>
    </head>
    <body>
    <%--查询站点信息--%>
    <jfp:SqlDataSource id="DB0" cmd="SELECT * FROM site_info LEFT JOIN site ON id = site_id WHERE id = ${ siteId }" />
    <jfp:SingleItem dataSourceID="DB0" var="site" />

    <%--导航栏--%>
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand logo" href="index.html">
                    <img src="${ site.domain }/${ site.logo }" alt=""/>
                    <h1>${ site.title }</h1>
                </a>
            </div>
            <jfp:SqlDataSource id="DB1" cmd="SELECT * FROM category WHERE parent_id is null AND site_id = ${ siteId }"/>
            <div class="collapse navbar-collapse " id="bs-example-navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="navBg"></li>
                    <li class="active">
                        <a href='<jcp:Url jsp="${ root }index.jsp?id=${ site.id }"
                                  html="${ root }index.html"
                                  isAuto="${ param.auto != null }"/>'>网站首页</a>
                    </li>
                    <jfp:Repeater dataSourceID="DB1" var="item">
                        <jfp:JsonDataSource id="style" value="${ item.style }"/>
                        <li>
                            <c:if test="${ item.type == 1 }">
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
                        </li>
                    </jfp:Repeater>
                </ul>
            </div>
        </div>
    </nav>
    <!--space-->
    <div class="space"></div>

    <!--模板内容-->
    <jcp:ContentPlaceHolder id="pageContent"/>

    <!--版权信息-->
    <footer class="footer">
        <div class="container">
            <div class="bottom">
                <strong>${ site.name }</strong>  <br/>
                电话 : ${ site.telephone } &nbsp;&nbsp;
                Email : ${ site.email } <br/>
                地址: ${ site.address } &nbsp; &nbsp; ICP备案号：${ site.icp }
            </div>
        </div>
    </footer>
    <script src="${ root }assets/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ root }assets/js/main.js" type="text/javascript" charset="utf-8"></script>
    </body>
    </html>
</jcp:MasterPage>