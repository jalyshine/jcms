<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>

<jcp:MasterPage id="master">
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="initial-scale=1, width=device-width">

        <%--基本样式--%>
        <link rel="stylesheet" href="${ root }assets/css/animate.css"/>
        <link rel="stylesheet" href="${ root }assets/css/bootstrap.css"/>
        <link rel="stylesheet" href="${ root }assets/css/style.css"/>
        <link rel="stylesheet" href="${ root }assets/css/default-color.css"/>
        <link rel="stylesheet" href="${ root }assets/css/responsive.css"/>

        <script src="${ root }assets/js/jquery-1.11.0.min.js"></script>

        <%--字体图标--%>
        <link rel="stylesheet" href="${ root }assets/fonts/sfont.css"/>

        <jcp:ContentPlaceHolder id="head"/>
    </head>
    <body class="home">
    <%--查询站点信息--%>
    <jfp:SqlDataSource id="DB0" cmd="SELECT * FROM site_info LEFT JOIN site ON id = site_id WHERE id = ${ siteId }" />
    <jfp:SingleItem dataSourceID="DB0" var="site" />

    <!-- 导航联系栏 -->
    <div id="header-wrapper">
        <jfp:SqlDataSource id="DBx" cmd="SELECT * FROM category WHERE name = '联系我们' AND site_id = ${ site.id }"/>
        <jfp:SingleItem dataSourceID="DBx" var="category_contact" />
        <jfp:JsonDataSource id="contact_style" value="${ category_contact.style }"/>
        <!--联系栏-->
        <div id="top-bar">
            <div class="container">
                <ul class="contact-info">
                    <li>
                        <i class="i_tel"></i>
                        <span>${ site.telephone }</span>
                    </li>
                    <li>
                        <a href='<jcp:Url jsp="${ root }content/${ contact_style.page }?id={1}"
                                              html="${ root }${ category_contact.dir_name }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ category_contact.id }}" />'>
                            <i class="i_contact_us"></i> ${ category_contact.name }
                        </a>
                    </li>
                </ul>

                <ul class="social-links">
                    <li><a href="http://wpa.qq.com/msgrd?v=3&uin=390722605&site=qq&menu=yes" target="_blank" class="i_qq"></a></li>
                    <li><a href="mailto:${ site.email }" class="i_email"></a></li>
                    <li><a href="#" class="i_wechat" id="wc_btn"></a></li>
                </ul>
            </div>
            <!--微信扫一扫弹窗-->
            <div id="popDiv" class="wc-div" style="display: none;">
                <img alt="微信扫一扫" src="${ site.domain }/${ site.qr_code }" alt="微信扫一扫"/>
                <div>
                    <p>微信扫描二维码<br/> 关注创嘉科技公众号<br/></p>
                    <a href="#" class="btn btn-default" id="wc_close_btn">关闭</a>
                </div>
            </div>
        </div>
        <!-- 导航栏 -->
        <jfp:SqlDataSource id="DB1" cmd="SELECT * FROM category WHERE parent_id is null AND site_id = ${ siteId }"/>
        <header id="header">
            <div class="container">
                <div id="logo">
                    <a href="index.html">
                        <img src="${ site.domain }/${ site.logo }" alt="${ site.name }"/>
                    </a>
                </div>
                <nav class="navbar navbar-default" role="navigation">
                    <div class="navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li
                                    <c:if test="${ menuItemName == '首页' }">class="current-menu-item"</c:if>
                            >
                                <a href='<jcp:Url jsp="${ root }index.jsp?id=${ site.id }"
                                  html="${ root }index.html"
                                  isAuto="${ param.auto != null }"/>' class="navbar-toggle">首页</a>
                            </li>
                            <jfp:Repeater dataSourceID="DB1" var="item" index="i">
                                <jfp:JsonDataSource id="style" value="${ item.style }"/>
                                <li
                                        <c:if test="${ menuItemName == item.name }">class="current-menu-item"</c:if>
                                >
                                    <c:if test="${ item.type == 1 }">
                                        <a href='<jcp:Url jsp="${ root }content/${ style.home }?id={1}"
                                                  html="${ root }${ item.dir_name }/{1}_1.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ item.id }}" />' class="navbar-toggle">${ item.name }</a>
                                    </c:if>
                                    <c:if test="${ item.type == 2 && item.name != '联系我们' }">
                                        <a href='<jcp:Url jsp="${ root }content/${ style.page }?id={1}"
                                              html="${ root }${ item.dir_name }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ item.id }}" />' class="navbar-toggle">${ item.name }</a>
                                    </c:if>
                                    <c:if test="${ item.type == 3}">
                                        <a href="${ item.url }" class="navbar-toggle">${ item.name }</a>
                                    </c:if>
                                </li>
                            </jfp:Repeater>
                        </ul>
                    </div>
                </nav>
                <!-- 响应式导航栏 -->
                <div id="dl-menu" class="dl-menuwrapper">
                    <button class="dl-trigger">Open Menu</button>
                    <ul class="dl-menu">
                        <li>
                            <a href='<jcp:Url jsp="${ root }index.jsp?id=${ site.id }"
                                  html="${ root }index.html"
                                  isAuto="${ param.auto != null }"/>' class="navbar-toggle">首页</a>
                        </li>
                        <jfp:Repeater dataSourceID="DB1" var="item" index="i">
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
        </header>
    </div>

    <!--模板内容-->
    <jcp:ContentPlaceHolder id="pageContent"/>

    <!-- 版权信息栏 -->
    <section id="footer-wrapper">
        <footer id="footer">
            <div class="container">
                <div class="row">
                    <ul class="col-md-3 footer-widget-container">
                        <li class="widget">
                            <h4>关于我们</h4>
                            <jfp:SqlDataSource id="DBz"
                                               cmd="SELECT * FROM single_page p LEFT JOIN category c ON category_id = c.id
                                               WHERE c.name = '关于' AND c.site_id = ${ site.id }" />
                            <jfp:SingleItem dataSourceID="DBz" var="about_item" />
                            <div class="article-content">${ about_item.content }</div>
                        </li>
                    </ul>
                    <ul class="col-md-3 footer-widget-container">
                        <li class="widget" id="tweetscroll-wrapper">
                            <h4>创嘉观点</h4>
                            <jfp:SqlDataSource id="DB10"
                                               cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d') time
                                                    , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                                    FROM word w LEFT JOIN category c ON category_id = c.id
                                                    WHERE c.name = '创嘉观点' AND site_id = ${ site.id }
                                                    ORDER BY update_time DESC limit 0, 5"/>
                            <ul class="nav-list">
                                <jfp:Repeater dataSourceID="DB10" var="item">
                                    <jfp:JsonDataSource id="style" value="${ item.style }"/>
                                    <li><a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                  html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ item.id }}" />'>
                                            ${ item.title }
                                    </a></li>
                                </jfp:Repeater>
                            </ul>
                        </li>
                    </ul>
                    <ul class="col-md-3 footer-widget-container">
                        <li class="widget focus-us">
                            <h4>关注我们</h4>
                            <img src="${ site.domain }/${ site.qr_code }">
                        </li>
                    </ul>
                    <ul class="col-md-3 footer-widget-container">
                        <li class="widget widget_text widget_contact">
                            <h4>联系方式</h4>
                            <address>
                                <ul>
                                    <li>
                                        <i class="i_address fg-color"></i> ${ site.address }
                                    </li>
                                    <li>
                                        <i class="i_tel fg-color"></i> ${ site.telephone }
                                    </li>
                                    <li>
                                        <i class="i_mobile fg-color"></i> ${ site.phone }
                                    </li>
                                    <li>
                                        <i class="i_email fg-color"></i> ${ site.email }
                                    </li>
                                </ul>
                            </address>
                        </li>
                    </ul>
                </div>
            </div>
        </footer>

        <section id="copyright-container">
            <div class="container">
                &copy; 版权所有 ${ site.copy_right }.
                备案号 <a href="http://www.miibeian.gov.cn/">${ site.icp }</a>
            </div>
        </section>

        <a href="#" class="scroll-up" onfocus="this.blur();">
            <i class="i_arrow_10"></i>
        </a>
    </section>

    <script src="${ root }assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${ root }assets/js/jquery.bootstrap.min.js"></script>
    <script src="${ root }assets/js/jquery.scripts.min.js"></script>
    <script src="${ root }assets/js/jquery.tweetscroll.js"></script>
    <script src="${ root }assets/js/include.js"></script>

    <!--页面JS-->
    <jcp:ContentPlaceHolder id="jsContent"/>

    <script>
        $(function () {
            $("div.article-content img").each(function () {
                var src = $(this).attr("src");
                if (src.indexOf("http://") == -1 && src.indexOf("https://") == -1) {
                    $(this).attr("src", '${ site.domain }/' + src);
                }
            });
        })
    </script>
    </body>
    </html>
</jcp:MasterPage>