<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<%--查询当前栏目，获取静态设置--%>
<jfp:SqlDataSource id="DB0" cmd="SELECT * FROM category WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="DB0" var="category"/>
<jfp:JsonDataSource id="meta" value="${ category.meta }"/>
<jfp:JsonDataSource id="style" value="${ category.style }"/>
<%--查询当前站点，设置模板样式的基本路径，和主机站点的基本路径 --%>
<jfp:SqlDataSource id="DB1" cmd="SELECT * FROM site WHERE id = ${ category.site_id }"/>
<jfp:SingleItem dataSourceID="DB1" var="site"/>
<%--向母版页传参--%>
<jcp:Param name="root" value="../"/>
<jcp:Param name="siteId" value="${ site.id }" />
<jcp:Param name="menuItemName" value="${ category.name }" />

<jcp:ContentPage masterPagePath="/template/cjsoft/master.jsp">

    <jcp:Content contentPlaceHolderId="head">
        <title>${ meta.title }</title>
        <meta name="keywords" content="${ meta.keyword }">
        <meta name="description" content="${ meta.description }"/>
        <!-- 轮播图 -->
        <link rel="stylesheet" href="${ root }assets/rs-plugin/css/settings.css"/>
        <link rel="stylesheet" href="${ root }assets/rs-plugin/css/kreator.css"/>
    </jcp:Content>

    <jcp:Content contentPlaceHolderId="pageContent">

        <!-- 标题栏 -->
        <section id="page-title" style="background-image: url(${ site.domain }/${ category.banner });">
            <div class="container" style="padding: 150px 0">
            </div>
        </section>

        <!--手机站-->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="pulse">
                            <h2>手机网站 · <span>SOLUTION PROPOSAL</span></h2>
                            <p>创嘉科技为客户提供各种类型的最优互联网整体解决方案 </p>
                        </section>
                    </section>
                </div>

                <div class="row">
                    <article class="col-md-4 col-xs-12-6">
                        <section class="phone-item triggerAnimation animated" data-animate="fadeInLeft">
                            <img src="${ root }assets/page/phone/phone-1.png">
                            <h4>定制设计版面<br/>让你手机网站与众不同 </h4>
                            <p>
                                资深设计师与你一对一沟通，根据你企业文化与行业特点，创意设计出风格独一无二的手机网站
                            </p>
                        </section>
                    </article>

                    <article class="col-md-4 col-xs-12-6">
                        <section class="phone-item triggerAnimation animated" data-animate="fadeInLeft">
                            <img src="${ root }assets/page/phone/phone-2.png">
                            <h4>PC网站和手机网站共享数据<br/>同步更新，管理方便 </h4>
                            <p>
                                手机端和PC端共用数据库PC网站后台统一管理，操作方便
                            </p>
                        </section>
                    </article>

                    <article class="col-md-4 col-xs-12-6">
                        <section class="phone-item triggerAnimation animated" data-animate="fadeInLeft">
                            <img src="${ root }assets/page/phone/phone-3.png">
                            <h4>网站打开速度快<br/>用最少的代码完成所需功能 </h4>
                            <p>
                                我们不是吝啬我们的代码而是为了追求最完美的作品
                            </p>
                        </section>
                    </article>
                </div>

            </div>
        </section>

        <!--微信网站和APP-->
        <section class="tp-wrapper">
            <!--轻松打造微信站-->
            <div class="tp-banner-container">
                <div class="tp-banner">
                    <ul>
                        <li data-transition=" " data-slotamount="13" data-masterspeed="10">
                            <img src="${ root }assets/page/phone/wexin.jpg" alt="slide-bkg" data-bgfit="cover" data-bgposition="center"
                                 data-bg-repeat="no-repeat"/>

                            <div class="tp-caption sfb"
                                 data-x="680"
                                 data-y="60"
                                 data-speed="600"
                                 data-start="1000"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300"><img src="${ root }assets/page/phone/phone-4.png" alt="image"/>
                            </div>

                            <div class="tp-caption regular lfl"
                                 data-x="0"
                                 data-y="120"
                                 data-speed="600"
                                 data-start="1000"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300">
                                <h2 class="phone-desc-title">轻松打造微信网站</h2>
                            </div>

                            <div class="tp-caption lfl"
                                 data-x="0"
                                 data-y="260"
                                 data-speed="600"
                                 data-start="1500"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300">
                                <p class="phone-desc-desc2">通过微信公众号或服务号与手机网站进行对接</p>
                            </div>

                            <div class="tp-caption lfl"
                                 data-x="0"
                                 data-y="295"
                                 data-speed="600"
                                 data-start="1800"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300">
                                <p class="phone-desc-desc2">轻松打造和手机网站一样的微信网站</p>
                            </div>

                            <div class="tp-caption lfl"
                                 data-x="0"
                                 data-y="330"
                                 data-speed="600"
                                 data-start="2100"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300">
                                <p class="phone-desc-desc2">数据和手机网站同步，轻松实现微信营销</p>
                            </div>

                            <div class="tp-caption caption-button lfl"
                                 data-x="0"
                                 data-y="430"
                                 data-speed="600"
                                 data-start="2400"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300">
                                <a href="#">我要定制</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <!--app定制-->
            <div class="tp-banner-container">
                <div class="tp-banner">
                    <ul>
                        <li data-transition=" " data-slotamount="13" data-masterspeed="10">
                            <div class="tp-caption sfb"
                                 data-x="0"
                                 data-y="140"
                                 data-speed="900"
                                 data-start="1500"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300"><img src="${ root }assets/page/phone/app.png" alt="image"/>
                            </div>

                            <div class="tp-caption randomrotate"
                                 data-x="600"
                                 data-y="225"
                                 data-speed="600"
                                 data-start="2000"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300">
                                <h3 class="phone-desc-title">APP定制</h3>
                            </div>

                            <div class="tp-caption randomrotate"
                                 data-x="600"
                                 data-y="300"
                                 data-speed="600"
                                 data-start="2300"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300">
                                <p class="phone-desc-desc1">IOS、Android移动终端定制</p>
                            </div>

                            <div class="tp-caption randomrotate"
                                 data-x="600"
                                 data-y="360"
                                 data-speed="600"
                                 data-start="2300"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300">
                                <p class="phone-desc-desc2">
                                    适合商城、运营平台等，运用APP独有的功能，最大化移动终端的作用，
                                    <br/>全方位覆盖PC端和移动端，数据和PC端同步。
                                </p>
                            </div>

                            <div class="tp-caption caption-button lfl"
                                 data-x="600"
                                 data-y="460"
                                 data-speed="600"
                                 data-start="2400"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300">
                                <a href="#">马上定制</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
    </jcp:Content>

    <jcp:Content contentPlaceHolderId="jsContent">
        <script src="${ root }assets/rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
        <script src="${ root }assets/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
        <script src="${ root }assets/js/jquery.isotope.min.js"></script>
        <script src="${ root }assets/js/portfolio.js"></script>
        <script src="${ root }assets/rs-plugin/js/slider.js"></script>
    </jcp:Content>
</jcp:ContentPage>