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
<jcp:Param name="menuItemName" value="套餐" />

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

        <!-- 套餐报价 -->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="bounceIn">
                            <h2>套餐价格 · <span>PACKAGE PRICE</span></h2>
                            <p>
                                针对不同的产品与服务推广需求，我们为您精细准备了一系列套餐计划，相信总有一款适合你
                            </p>
                        </section>
                    </section>
                </div>
                <div class="row">
                    <section class="col-md-12">
                        <div class="triggerAnimation animated" data-animate="fadeInUp">
                            <div class="pricing-table-col">
                                <ul>
                                    <li class="head">
                                        <div class="title-container">
                                            <div class="title">
                                                <i class="i_five_pointed_st"></i>
                                                <h3>基础版</h3>
                                            </div>
                                        </div>

                                        <p class="price">
                                            <span class="currency">￥</span>
                                            <span class="big">3000</span>
                                            /年
                                        </p>
                                    </li>

                                    <li class="odd">
                                        <p>电脑站</p>
                                    </li>

                                    <li>
                                        <p>百度SEO优化</p>
                                    </li>

                                    <li class="odd">
                                        <p>
                                            关键词3-6个
                                        </p>
                                    </li>

                                    <li>
                                        <p>
                                            做2年赠送1年
                                        </p>
                                    </li>

                                    <li class="odd">
                                        <p>
                                            续费次年8折
                                        </p>
                                    </li>

                                    <li class="pricing-footer">
                                        <a href="#" class="btn btn-default btn-empty">我要定制</a>
                                    </li>
                                </ul>
                            </div>

                            <div class="pricing-table-col">
                                <ul>
                                    <li class="head">
                                        <div class="title-container">
                                            <div class="title">
                                                <i class="i_pentagram"></i>
                                                <h3>标准版</h3>
                                            </div>
                                        </div>

                                        <p class="price">
                                            <span class="currency">￥</span>
                                            <span class="big">3880</span>
                                            /年
                                        </p>
                                    </li>

                                    <li class="odd">
                                        <p>电脑站+手机站</p>
                                    </li>

                                    <li>
                                        <p>百度+搜狗SEO优化</p>
                                    </li>

                                    <li class="odd">
                                        <p>
                                            关键词8-12个
                                        </p>
                                    </li>

                                    <li>
                                        <p>
                                            做2年送1年
                                        </p>
                                    </li>

                                    <li class="odd">
                                        <p>
                                            续费次年8折
                                        </p>
                                    </li>

                                    <li class="pricing-footer">
                                        <a href="#" class="btn btn-default btn-empty">我要定制</a>
                                    </li>
                                </ul>
                            </div>

                            <div class="pricing-table-col selected">
                                <ul>
                                    <li class="head">
                                        <div class="title-container">
                                            <div class="title">
                                                <i class="i_zan"></i>
                                                <h3>精品版</h3>
                                            </div>
                                        </div>

                                        <p class="price">
                                            <span class="currency">￥</span>
                                            <span class="big">5880</span>
                                            /年
                                        </p>
                                    </li>

                                    <li class="odd">
                                        <p>电脑站+手机站+小程序</p>
                                    </li>

                                    <li>
                                        <p>百度+搜狗+360SEO优化</p>
                                    </li>

                                    <li class="odd">
                                        <p>
                                            12-17个关键词
                                        </p>
                                    </li>

                                    <li>
                                        <p>
                                            做2年送1年
                                        </p>
                                    </li>

                                    <li class="odd">
                                        <p>
                                            续费次年8折
                                        </p>
                                    </li>

                                    <li class="pricing-footer">
                                        <a href="#" class="btn btn-default btn-empty">我要定制</a>
                                    </li>

                                </ul>
                            </div>

                            <div class="pricing-table-col">
                                <ul>
                                    <li class="head">
                                        <div class="title-container">
                                            <div class="title">
                                                <i class="i_diamond"></i>
                                                <h3>商务版</h3>
                                            </div>
                                        </div>

                                        <p class="price">
                                            <span class="currency">￥</span>
                                            <span class="big">8000</span>
                                            /年
                                        </p>
                                    </li>

                                    <li class="odd">
                                        <p>电脑站+手机站+小程序+公众号</p>
                                    </li>

                                    <li>
                                        <p>百度+搜狗+360S+小程序SEO优化</p>
                                    </li>

                                    <li class="odd">
                                        <p>
                                            关键词30-40个
                                        </p>
                                    </li>

                                    <li>
                                        <p>
                                            &nbsp;&nbsp;&nbsp;
                                        </p>
                                    </li>

                                    <li class="odd">
                                        <p>
                                            续费次年7折
                                        </p>
                                    </li>

                                    <li class="pricing-footer">
                                        <a href="#" class="btn btn-default btn-empty">我要定制</a>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </section>
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
                                    若标准套餐无法满足您的需求，我们将提供更加高级的定制服务。
                                    <jfp:SqlDataSource id="DBx" cmd="SELECT * FROM category WHERE name = '联系我们' AND site_id = ${ site.id }"/>
                                    <jfp:SingleItem dataSourceID="DBx" var="category_contact" />
                                    <jfp:JsonDataSource id="contact_style" value="${ category_contact.style }"/>
                                    <a class="btn btn-default btn-empty triggerAnimation animated"
                                       href='<jcp:Url jsp="${ root }content/${ contact_style.page }?id={1}"
                                              html="${ root }${ category_contact.dir_name }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ category_contact.id }}" />' data-animate="fadeInUp">发布需求</a>
                                </h2>
                            </article>
                        </div>
                    </article>
                </div>
            </div>
        </section>

        <!--支付方式-->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="bounceIn">
                            <h2>支付方式 · <span>PAYMENT METHOD</span></h2>
                        </section>
                    </section>
                </div>

                <div class="row card-area">
                    <jfp:SqlDataSource id="DB2" cmd="SELECT * FROM tip t LEFT JOIN tip_type y ON type_id = y.id
                                                     WHERE y.name = '支付方式' AND y.site_id = ${ site.id }" />
                    <jfp:Repeater dataSourceID="DB2" var="item">
                        <div class="col-sm-6 col-xs-12">
                            <div class="card-img">
                                <img src="${ site.domain }/${ item.icon }" alt="${ item.title }"/>
                            </div>
                            <div class="card-text">
                                <span class="name">银行</span>
                                <span class="text">${ item.description }</span>
                            </div>
                            <div class="card-text">
                                <span class="name">账户</span>
                                <span class="text">${ item.title }</span>
                            </div>
                            <div class="card-text">
                                <span class="name">卡号</span>
                                <span class="text">${ item.url }</span>
                            </div>
                        </div>
                    </jfp:Repeater>
                </div>
            </div>
        </section>

    </jcp:Content>
</jcp:ContentPage>