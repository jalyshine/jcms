<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>

<%--传给母版--%>
<jcp:Param name="root" value=""/>
<jcp:Param name="siteId" value="${ param.id }"/>
<jcp:Param name="menuItemName" value="首页"/>

<%--查找站点信息--%>
<jfp:SqlDataSource id="DB0"
                   cmd="SELECT * FROM site LEFT JOIN site_info ON id = site_id
                        WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="DB0" var="site"/>

<jcp:ContentPage masterPagePath="/template/cjsoft/master.jsp">

    <%--网页头部--%>
    <jcp:Content contentPlaceHolderId="head">
        <title>${ site.title }</title>
        <meta name="keywords" content="${ site.keywords }">
        <meta name="description" content="${ site.description }"/>

        <!-- 查看案例 -->
        <link rel="stylesheet" href="${ root }assets/css/magnific-popup.css"/>
        <!-- 轮播图 -->
        <link rel="stylesheet" href="${ root }assets/rs-plugin/css/settings.css"/>
        <link rel="stylesheet" href="${ root }assets/rs-plugin/css/kreator.css"/>
    </jcp:Content>

    <%--网页主体--%>
    <jcp:Content contentPlaceHolderId="pageContent">
        <!--巨幕-->
        <section class="tp-wrapper">
            <div class="tp-banner-container">
                <div class="tp-banner">
                    <jfp:SqlDataSource id="DB1"
                                       cmd="SELECT * FROM tip t LEFT JOIN tip_type y ON type_id = y.id
                                            WHERE y.name = '首页巨幕' AND t.site_id = ${ site.id } " />
                    <ul>
                        <jfp:Repeater dataSourceID="DB1" var="item">
                            <li data-transition=" " data-slotamount="13" data-masterspeed="10">
                                <img src="${ site.domain }/${ item.banner }" alt="${ item.title }"
                                     data-bgfit="cover" data-bgposition="center" data-bg-repeat="no-repeat"/>
                            </li>
                        </jfp:Repeater>
                    </ul>
                </div>
            </div>
        </section>

        <!--服务范围-->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="bounceIn">
                            <h2>服务范围 · <span>SERVICE SCOPE</span></h2>
                            <p>从PC到移动互联网，创嘉科技为您打造一个全方位的互联网营销体系</p>
                        </section>
                    </section>
                </div>

                <div class="row">
                    <jfp:SqlDataSource id="DBx" cmd="SELECT * FROM category WHERE name = '联系我们' AND site_id = ${ site.id }"/>
                    <jfp:SingleItem dataSourceID="DBx" var="category_contact" />
                    <jfp:JsonDataSource id="contact_style" value="${ category_contact.style }"/>

                    <jfp:SqlDataSource id="DB2"
                                       cmd="SELECT c.icon c_icon, c.name c_name, c.description c_desc, c.style c_style,
                                            c.dir_name c_dir_name, c.id c_id, c.type c_type, c.url c_url
                                            FROM category c LEFT JOIN category p ON c.parent_id = p.id
                                            WHERE p.name = '服务' AND p.site_id = ${ site.id }" />
                    <jfp:Repeater dataSourceID="DB2" var="item">
                        <jfp:JsonDataSource id="style" value="${ item.c_style }"/>
                        <div class="col-md-3 col-sm-6 col-xs-12 service-scope-item">
                            <div class="service-scope-border">
                                <img src="${ site.domain }/${ item.c_icon }">
                                <h3>${ item.c_name }</h3>
                                <p>${ item.c_desc }</p>
                                <div class="service-scope-link">
                                    <c:if test="${ item.c_type == 2 }">
                                        <a href='<jcp:Url jsp="${ root }content/${ style.page }?id={1}"
                                                  html="${ root }${ item.c_dir_name }/{1}.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ item.c_id }}" />'>查看详情</a>
                                    </c:if>
                                    <c:if test="${ item.c_type == 3 }">
                                        <a href='${ item.c_url }'>查看详情</a>
                                    </c:if>
                                    <a href='<jcp:Url jsp="${ root }content/${ contact_style.page }?id={1}"
                                              html="${ root }${ category_contact.dir_name }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ category_contact.id }}" />'>立即咨询</a>
                                </div>
                            </div>
                        </div>
                    </jfp:Repeater>
                </div>
            </div>
        </section>

        <!--分界线-->
        <section class="page-content parallax parallax-8">
            <div class="container">
                <div class="row">
                    <article class="col-md-12">
                        <div class="triggerAnimation animated" data-animate="fadeInUp">
                            <article class="note-rotator">
                                <h2>
                                    站在用户的角度思考问题，与客户深入沟通，找到
                                    <span class="fg-color">网站设计与推广</span> 的最佳解决方案
                                </h2>
                            </article>
                        </div>
                    </article>
                </div>
            </div>
        </section>

        <!--成功案例-->
        <section class="page-content">
            <div class="container-full">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="pulse">
                            <h2>成功案例 · <span>SUCCESSFUL CASES</span></h2>
                            <p>一个人能走多远，取决于与谁同行，TI团队是一个富有理想和激情的团队，是一个蓬勃向上并富有朝气的团队，
                                <br/>也是一个技术专业化、管理人性化、创新性和学习型的优秀团队。 </p>
                        </section>
                    </section>
                </div>

                <div class="row">
                    <section class="col-md-12">
                        <jfp:SqlDataSource id="DB3"
                                           cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d') time
                                                , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                                FROM word w LEFT JOIN (category c LEFT JOIN category p ON c.parent_id = p.id)
                                                ON w.category_id = c.id WHERE p.name = '案例' AND p.site_id = ${ site.id }
                                                ORDER BY update_time DESC limit 0, 8" />

                        <ul class="portfolio-projects-list triggerAnimation animated clearfix" data-animate="fadeInUp">
                            <jfp:Repeater dataSourceID="DB3" var="item">
                                <jfp:JsonDataSource id="style" value="${ item.style }"/>
                                <li>
                                    <figure class="portfolio-img-container">
                                        <img src="${ site.domain }/${ item.thumb }" alt="${ item.title }"/>

                                        <div class="portfolio-hover">
                                            <ul class="portfolio-links">
                                                <li class="portfolio-zoom">
                                                    <a href="${ site.domain }/${ item.thumb }"
                                                       class="gallery-item i_ic_setting_zoom"></a>
                                                </li>

                                                <li class="portfolio-single">
                                                    <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                          html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                                          isAuto="${ param.auto != null }"
                                                          data="{${ item.id }}" />' class="i_get_into"></a>
                                                </li>
                                            </ul>
                                        </div>

                                        <figcaption class="portfolio-title">
                                            <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                  html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ item.id }}" />'>
                                                <h4>${ item.title }</h4>
                                            </a>
                                            <span class='categories'>${ item.author }</span>
                                        </figcaption>
                                    </figure>
                                </li>
                            </jfp:Repeater>
                        </ul>

                        <jfp:SqlDataSource id="DBy" cmd="SELECT * FROM category WHERE name = '案例'"/>
                        <jfp:SingleItem dataSourceID="DBy" var="category_case" />
                        <jfp:JsonDataSource id="case_style" value="${ category_case.style }"/>
                        <a href='<jcp:Url jsp="${ root }content/${ case_style.home }?id={1}"
                                                  html="${ root }${ category_case.dir_name }/{1}_1.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ category_case.id }}" />'
                           class="btn btn-default btn-empty triggerAnimation animated">查看更多</a>
                    </section>
                </div>
            </div>
        </section>

        <!--解决方案-->
        <section class="page-content parallax parallax-11">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="pulse">
                            <h2>解决方案 · <span>SOLUTION PROPOSAL</span></h2>
                            <p>创嘉科技为客户提供各种类型的最优互联网整体解决方案 </p>
                        </section>
                    </section>
                </div>

                <div class="row">
                    <jfp:SqlDataSource id="DB4"
                                       cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d') time
                                        , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                        FROM word w LEFT JOIN category c ON category_id = c.id
                                        WHERE c.name = '方案' AND site_id = ${ site.id }
                                        ORDER BY publish_time ASC limit 0, 4"/>
                    <jfp:Repeater dataSourceID="DB4" var="item" index="i">
                        <jfp:JsonDataSource id="style" value="${ item.style }"/>
                        <article class="col-md-3 col-sm-6">
                            <section class="solution solution_${i+1} triggerAnimation animated" data-animate="fadeInLeft">
                                <div class="solution-icon" style="background-image: url(${ site.domain }/${ item.icon })"></div>
                                <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                  html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ item.id }}" />'>
                                    <h4>${ item.title }</h4>
                                </a>
                                <p>${ item.description }</p>
                                <div class="bg"></div>
                            </section>
                        </article>
                    </jfp:Repeater>

                    <section class="col-md-12">
                        <jfp:SqlDataSource id="DBz" cmd="SELECT * FROM category WHERE name = '方案'"/>
                        <jfp:SingleItem dataSourceID="DBz" var="category_solution" />
                        <jfp:JsonDataSource id="solution_style" value="${ category_solution.style }"/>
                        <a href='<jcp:Url jsp="${ root }content/${ solution_style.home }?id={1}"
                                                  html="${ root }${ category_solution.dir_name }/{1}_1.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ category_solution.id }}" />'
                           class="btn btn-default btn-empty triggerAnimation animated">查看更多</a>
                    </section>
                </div>

            </div>
        </section>

        <!--新闻资讯-->
        <section class='page-content home-news'>
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="pulse">
                            <h2>新闻资讯 · <span>NEWS & INFORMATION</span></h2>
                            <p>提供网站建设相关资讯、互联网行业资讯、网站设计知识、空间域名邮箱、网站解决方案、常见问题、签约新闻等</p>
                        </section>
                    </section>
                </div>
                <div class="row">
                    <jfp:SqlDataSource id="DB5"
                                       cmd="SELECT c.thumb c_thumb, c.name c_name, c.style c_style, c.id c_id
                                            FROM category c LEFT JOIN category p ON c.parent_id = p.id
                                            WHERE p.name = '资讯' AND p.site_id = ${ site.id } limit 0, 3" />
                    <jfp:Repeater dataSourceID="DB5" var="item">
                        <jfp:JsonDataSource id="style" value="${ item.style }"/>
                        <article class="col-md-4">
                            <div class="triggerAnimation animated" data-animate="fadeInUp">
                                <div class="news-banner">
                                    <img src="${ site.domain }/${ item.c_thumb }"/>
                                </div>
                                <h3>${ item.c_name }</h3>
                                <jfp:SqlDataSource id="DB_temp"
                                                   cmd="SELECT *, DATE_FORMAT(publish_time,'%m-%d') time
                                                    , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                                    FROM word WHERE category_id = ${ item.c_id }
                                                    ORDER BY publish_time DESC limit 0, 5" />
                                <ul class="news-list">
                                    <jfp:Repeater dataSourceID="DB_temp" var="word">
                                        <li>
                                            <i class="i_direction_21"></i>
                                            <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                  html="${ root }${ word.dir_name }/${ word.year }/${ word.date }/{1}.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ word.id }}" />'>${ word.title }</a> ${ word.time }
                                        </li>
                                    </jfp:Repeater>
                                </ul>
                            </div>
                        </article>
                    </jfp:Repeater>
                </div>
            </div>
        </section>

        <!--数字创嘉-->
        <section class="page-content parallax parallax-2 dark">
            <div class="container">
                <div class="row">
                    <article class="col-md-12 clearfix">
                        <div class="triggerAnimation animated" data-animate="fadeInUp">
                            <ul class="numbers-counter">
                                <li>
                                    <span class="timer number" data-to="7" data-speed="2000">0</span>
                                    <span class="unit"> 年</span>
                                    <p>专注网站建设</p>
                                </li>

                                <li>
                                    <span class="timer number" data-to="100" data-speed="2000">0</span>
                                    <span class="unit"> +人</span>
                                    <p>专业的服务团队</p>
                                </li>

                                <li>
                                    <span class="timer number" data-to="164" data-speed="2000">0</span>
                                    <span class="unit"> 家</span>
                                    <p>企业成功合作</p>
                                </li>

                                <li>
                                    <span class="timer number" data-to="1200" data-speed="2000">0</span>
                                    <span class="unit"> +个</span>
                                    <p>成功案例证明实力</p>
                                </li>
                            </ul>
                        </div>
                    </article>
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

    <jcp:Content contentPlaceHolderId="jsContent">
        <script src="${ root }assets/rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
        <script src="${ root }assets/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
        <script src="${ root }assets/js/jquery.isotope.min.js"></script>
        <script src="${ root }assets/js/portfolio.js"></script>
        <script src="${ root }assets/js/jquery.magnific-popup.min.js"></script>
        <script src="${ root }assets/js/jquery.countTo.js"></script>
        <script src="${ root }assets/rs-plugin/js/slider.js"></script>
    </jcp:Content>

</jcp:ContentPage>