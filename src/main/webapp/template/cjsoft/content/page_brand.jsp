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

        <!--网页设计-->
        <section class="tp-wrapper">
            <div class="tp-banner-container">
                <div class="tp-banner">
                    <ul>
                        <li data-transition=" " data-slotamount="0" data-masterspeed="1" class="bg-color">

                            <div class="tp-caption sfb"
                                 data-x="500"
                                 data-y="50"
                                 data-speed="900"
                                 data-start="100"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300"><img src="${ root }assets/page/brand/wd_1_text.png" alt="image"/>
                            </div>

                            <div class="tp-caption sfb"
                                 data-x="260"
                                 data-y="200"
                                 data-speed="900"
                                 data-start="500"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300"><img src="${ root }assets/page/brand/wd_1_text2.png" alt="image"/>
                            </div>

                            <div class="tp-caption sft"
                                 data-x="380"
                                 data-y="400"
                                 data-speed="900"
                                 data-start="900"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300"><img src="${ root }assets/page/brand/wd_1_2.png" alt="image"/>
                            </div>

                            <div class="tp-caption lfl"
                                 data-x="100"
                                 data-y="400"
                                 data-speed="800"
                                 data-start="1300"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300"><img src="${ root }assets/page/brand/wd_1_left.png" alt="slider image"/>
                            </div>

                            <div class="tp-caption lfr"
                                 data-x="900"
                                 data-y="20"
                                 data-speed="800"
                                 data-start="1300"
                                 data-easing="Back.easeOut"
                                 data-endspeed="300"><img src="${ root }assets/page/brand/wd_1_right.png" alt="slider image"/>
                            </div>

                        </li>
                    </ul>
                </div>
            </div>
        </section>

        <!--H5-->
        <section class="page-content">
            <div class="container">
                <div class="brand-item">
                    <h2 class="fg-color">采用HTML5领先技术</h2>
                    <div>页面代码采用最新HTML5标准，兼容各大浏览器，对搜索引擎优化良好，让你的网站赢在起跑线上！</div>
                    <p>
                        Page code using the latest HTML5 standard, compatible with all major browsers <br/>
                        the search engine optimization is good ,let your site win on the starting line!
                    </p>
                    <img src="${ root }assets/page/brand/html+css.png">
                </div>
            </div>
        </section>

        <!--系统开发-->
        <section class="page-content parallax bg-color">
            <div class="container">
                <div class="brand-item fg-white">
                    <h2>系统开发，管理内容是如此简单</h2>
                    <div>独立开发，安全、高效、容易扩展。后台完全根据前台内容定制，傻瓜式管理，操作方便简单</div>
                    <p>
                        Independent development, safe, efficient and easy to expand. Backstage is completely customized<br/>
                        according to the content of the front desk, foolish management, easy to operate!
                    </p>
                    <img src="${ root }assets/page/brand/cms.png">
                </div>
            </div>
        </section>

        <!--省心的网站-->
        <section class="page-content">
            <div class="container">
                <div class="brand-item">
                    <h2 class="fg-color">省心的网站绝对超值</h2>
                    <div>国内顶级空间商，安全高效的代码，稳定的系统让你网站稳如泰山</div>
                    <p>
                        Domestic top space, stable, safe and efficient code, the system allows you site stability
                    </p>
                    <img src="${ root }assets/page/brand/safe.png">
                </div>
            </div>
        </section>

        <!--制作流程-->
        <section class="page-content parallax bg-color">
            <div class="container">
                <div class="brand-item fg-white">
                    <h2>标准化制作流程</h2>
                    <div>网站前期策划、首页设计稿、内页设计稿、程序制作都需通过你确认，设计到你满意为止</div>
                    <p>
                        Prophase planning, website homepage design draft, the inside pages design draft <br/>
                        application shall be confirmed by you, the design to you satisfaction!
                    </p>
                    <img src="${ root }assets/page/brand/process.png">
                </div>
            </div>
        </section>

        <!--服务流程-->
        <section class="page-content">
            <div class="container">
                <div class="brand-item">
                    <h2>完善服务流程，对项目了如指掌</h2>
                    <div>每一个客户可通过客服询问系统实时查看项目进度，在线提交问题，对你的项目了如指掌</div>
                    <p>
                        Every customer can pass Fang Wei ERP system real-time view the project schedule, <br/>
                        submit questions online, knew all about your project
                    </p>
                    <img src="${ root }assets/page/brand/light.png">
                </div>
            </div>
        </section>

        <!--制作流程-->
        <section class="page-content parallax parallax-1">
            <div class="container">
                <div class="container">
                    <div class="brand-item">
                        <h2>贴心售后服务</h2>
                        <div>赠送一年免费空间域名和售后服务，还有系统BUG终身修复</div>
                        <p>
                            One year free space domain and after-sales service, and the system BUG fix for life
                        </p>
                        <img src="${ root }assets/page/brand/sale-after.png">
                    </div>
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