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
<jcp:Param name="menuItemName" value="关于" />

<jcp:ContentPage masterPagePath="/template/cjsoft/master.jsp">

    <jcp:Content contentPlaceHolderId="head">
        <title>${ meta.title }</title>
        <meta name="keywords" content="${ meta.keyword }">
        <meta name="description" content="${ meta.description }"/>
    </jcp:Content>

    <jcp:Content contentPlaceHolderId="pageContent">

        <!-- 标题栏 -->
        <section id="page-title" style="background-image: url(${ site.domain }/${ category.banner });">
            <div class="container">
                <h1>${ category.name }</h1>
                <div class="breadcrumb-container">
                    <span>您的位置: </span>
                    <ul class="breadcrumb">
                        <li><a href='<jcp:Url jsp="${ root }index.jsp?id=${ site.id }"
                                              html="${ root }index.html"
                                              isAuto="${ param.auto != null }"/>'>首页</a></li>
                        <li><a href="javascript:;">${ category.name }</a></li>
                    </ul>
                </div>
            </div>
        </section>

        <!-- 公司概况 -->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="bounceIn">
                            <h2>公司概况 · <span>GENERAL SITUATION</span></h2>
                            <p>懂您所需、做您所想</p>
                        </section>
                    </section>
                </div>

                <jfp:SqlDataSource id="DB2"
                                   cmd="SELECT * FROM single_page s LEFT JOIN category c ON s.category_id = c.id
                                        WHERE c.name = '关于' AND site_id = ${ site.id }" />
                <jfp:SingleItem dataSourceID="DB2" var="about" />
                <div class="row">
                    <div class="about-image col-sm-6 col-sm-12">
                        <img src="${ site.domain }/${ about.thumb }" />
                    </div>
                    <div class="col-sm-6 col-sm-12">
                        <div class="about-title">
                            <h3>我们是谁</h3>
                        </div>
                        <p class="about-content">${ about.description }</p>
                        <div class="about-title">
                            <h3>我们能做什么</h3>
                        </div>
                        <jfp:SqlDataSource id="DB3"
                                           cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d') time
                                                , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                                FROM word w LEFT JOIN category c ON category_id = c.id
                                                WHERE c.name = '创嘉观点' AND site_id = ${ site.id }
                                                ORDER BY update_time DESC limit 0, 5"/>
                        <ul class="about-list">
                            <jfp:Repeater dataSourceID="DB3" var="item">
                                <jfp:JsonDataSource id="style" value="${ item.style }"/>
                                <li><a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                  html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ item.id }}" />'>
                                        ${ item.title }
                                </a></li>
                            </jfp:Repeater>
                        </ul>
                    </div>
                </div>
            </div>
        </section>

        <!--团队文化-->
        <section class="page-content parallax parallax-7" data-stellar-background-ratio="0.4">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="bounceIn">
                            <h2>团队文化 · <span>TEAM CULTURE</span></h2>
                            <p>一起创新的思考方式，我们没有英雄式的设计师，团队协作才能爆发更多的能量。</p>
                        </section>
                    </section>
                </div>
                <div class="row">
                    <jfp:SqlDataSource id="DB4"
                                       cmd="SELECT * FROM tip t LEFT JOIN tip_type y ON type_id = y.id
                                            WHERE y.name = '公司文化' AND y.site_id = ${ site.id }" />
                    <jfp:SingleItem dataSourceID="DB4" var="culture" />
                    <div class="col-sm-6 col-sm-12">
                        <div class="about-title">
                            <h3>${ culture.title }</h3>
                            <p>${ culture.description }</p>
                        </div>
                        <p class="about-content">${ culture.content }</p>
                    </div>

                    <div class="about-image col-sm-6 col-sm-12">
                        <img src="${ site.domain }/${ culture.icon }" alt="${ culture.title }"/>
                    </div>
                </div>
            </div>
        </section>

        <!--合作伙伴-->
        <section class="page-content partner">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="pulse">
                            <h2>合作伙伴 · <span>COOPERATION PARTNERS</span></h2>
                            <p>我们最大的使命就是让他们的选择变得坚定和正确，为客户创造最大的价值从而实现自己的价值。</p>
                        </section>
                    </section>
                </div>
                <div class="row">
                    <jfp:SqlDataSource id="DB5"
                                       cmd="SELECT * FROM friendly_link l LEFT JOIN friendly_link_type y ON l.type_id = y.id
                                            WHERE y.name = '合作伙伴' AND y.site_id = ${ site.id }" />
                    <jfp:Repeater dataSourceID="DB5" var="item">
                        <article class="col-md-2 col-sm-3 col-xs-6">
                            <div class="triggerAnimation animated link-item" data-animate='fadeInLeft'>
                                <img src="${ site.domain }/${ item.logo }">
                            </div>
                        </article>
                    </jfp:Repeater>
                </div>
            </div>
        </section>
    </jcp:Content>
</jcp:ContentPage>