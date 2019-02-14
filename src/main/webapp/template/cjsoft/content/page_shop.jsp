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
    </jcp:Content>

    <jcp:Content contentPlaceHolderId="pageContent">

        <!-- 标题栏 -->
        <section id="page-title" style="background-image: url(${ site.domain }/${ category.banner });">
            <div class="container" style="padding: 80px 0">
            </div>
        </section>

        <!--多终端覆盖-->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="pulse">
                            <h2>PC端+移动端 多终端覆盖</h2>
                            <p>一个平台，多个店铺，多个终端，数据互联互通，多管齐下，实现销量翻番。</p>
                            <img src="${ root }assets/page/shop/shop-1.jpg" style="margin: 30px auto"/>
                        </section>
                    </section>
                </div>
            </div>
        </section>

        <!--运营模式-->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="pulse">
                            <h2>运营模式</h2>
                            <p>既要支持平台自营销售商品，也支持筛选优质商家入驻平台开店<br/>业务模式灵活多样
                                可以通过自营销售利润、店铺交易佣金、店铺等级服务费、广告等多途径获得利润。
                            </p>
                            <img src="${ root }assets/page/shop/shop-2.jpg" style="margin: 30px auto"/>
                        </section>
                    </section>
                </div>
            </div>
        </section>

        <!--系统特点-->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-6 shop-item">
                        <h2>1.标准商家入驻流程</h2>
                        <p>完善的商家入驻环节，在线申请、审核、缴费、开通店铺，<br/>每个环节做到规范化、人性化、流程化、专业化。
                        </p>
                        <img src="${ root }assets/page/shop/shop-3.png">
                    </section>
                    <section class="col-md-6 shop-item">
                        <h2>2.入驻主体多样</h2>
                        <p>支持企业入驻开店，也支持个人入驻开店。资质提交区别处理
                        </p>
                        <img src="${ root }assets/page/shop/shop-4.png">
                    </section>
                </div>
                <div class="row">
                    <section class="col-md-6 shop-item">
                        <h2>3.一个账户 双重身份</h2>
                        <p>
                            用户申请一个账号后，在平台上可以是买家，也可以申请成为卖家，<br/>
                            两种身份还可以随时切换，省去二次注册的烦恼。
                        </p>
                        <img src="${ root }assets/page/shop/shop-5.jpg">
                    </section>
                    <section class="col-md-6 shop-item">
                        <h2>4.独立后台 买卖分离</h2>
                        <p>用户登录买家中心，可满足购物、物流跟踪、售后等多方面需求；
                            商家中心则可满足卖家对商品、交易、促销、店铺、物流、客服、售后、结算等全面功能需求。
                        </p>
                        <img src="${ root }assets/page/shop/shop-6.png">
                    </section>
                </div>
                <div class="row">
                    <section class="col-md-6 shop-item">
                        <h2>5.商家保证金制度</h2>
                        <p>
                            支持入驻商家主营类目保证金制度，以确保平台良好的经营环境
                        </p>
                        <img src="${ root }assets/page/shop/shop-7.png">
                    </section>
                    <section class="col-md-6 shop-item">
                        <h2>6.统一收款 定期结算 快速盈利</h2>
                        <p>支持按平台和分店设置商品目录佣金，并可灵活调整佣金比例。<br/>通过平台统一收款、定期结算的方式，
                            当平台上每笔交易成功后，平台运营方可按照交易金额和佣金比例直接分佣，实现平台快速盈利。
                        </p>
                        <img src="${ root }assets/page/shop/shop-8.jpg">
                    </section>
                </div>
            </div>
        </section>

        <!--技术特点-->
        <section class="page-content parallax parallax-1">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="bounceIn">
                            <h2>技术特点</h2>
                        </section>
                    </section>
                </div>

                <div class="row">
                    <article class="col-md-4 col-sm-12">
                        <section class="shop-item triggerAnimation animated" data-animate="fadeInLeft">
                            <h4>浏览速度快</h4>
                            <p>
                                采用静态页面化技术，页面缓存技术，以及对数据库关键数据进行调优，让商城
                                浏览速度倍增
                            </p>
                        </section>
                    </article>
                    <article class="col-md-4 col-sm-12">
                        <section class="shop-item triggerAnimation animated" data-animate="fadeInLeft">
                            <h4>化繁为简</h4>
                            <p>
                                采用OOP（面向对象）和DDD（领域驱动）的应用，使系统程序实现表现层、业务层
                                、逻辑层的完美分离，对于复杂业务采用时间驱动方式，做到将复杂业务完美拆分。
                            </p>
                        </section>
                    </article>
                    <article class="col-md-4 col-sm-12">
                        <section class="shop-item triggerAnimation animated" data-animate="fadeInLeft">
                            <h4>安全核心</h4>
                            <p>
                                针对常见的安全问题，系统核心构架对商城每个操作都做了统一的安全验证，
                                同时，基于角色的访问和权限控制，以及系统日志等多种手段保障安全运营。
                            </p>
                        </section>
                    </article>
                </div>
            </div>
        </section>

    </jcp:Content>
</jcp:ContentPage>