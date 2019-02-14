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

<jcp:ContentPage masterPagePath="/template/company1/master.jsp">

    <%--网页头部--%>
    <jcp:Content contentPlaceHolderId="head">
        <title>${ site.title }</title>
        <meta name="keywords" content="${ site.keywords }">
        <meta name="description" content="${ site.description }"/>
    </jcp:Content>

    <%--网页主体--%>
    <jcp:Content contentPlaceHolderId="pageContent">
        <!--巨幕-->
        <div class="banner">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                </ol>
                <jfp:SqlDataSource id="DB1"
                                   cmd="SELECT * FROM tip p LEFT JOIN tip_type t ON p.type_id = t.id
                                        WHERE t.site_id = ${ siteId } AND name='首页巨幕'"/>
                <div class="carousel-inner" role="listbox">
                    <jfp:Repeater dataSourceID="DB1" var="item" index="i">
                        <c:if test="${ i==0 }">
                            <div class="item active">
                                <img src="${ site.domain }/${ item.banner }">
                            </div>
                        </c:if>
                        <c:if test="${ i>0 }">
                            <div class="item">
                                <img src="${ site.domain }/${ item.banner }">
                            </div>
                        </c:if>
                    </jfp:Repeater>
                </div>
                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

        <!--公司介绍-->
        <div class="honor padT80 padB80 greyBg">
            <div class="container">
                <section class="title">
                    <h2>公司介绍</h2>
                </section>
                <div class="row padT80">
                    <jfp:SqlDataSource id="DB2"
                                       cmd="SELECT * FROM tip t LEFT JOIN tip_type y ON type_id = y.id
                                            WHERE y.name = '公司介绍' AND y.site_id = ${ site.id }
                                            ORDER BY update_time DESC limit 0, 3" />
                    <jfp:Repeater dataSourceID="DB2" var="item" index="i">
                        <div class="col-sm-4">
                            <div class="honty">
                                <div>
                                    <div class="ty">
                                        <c:choose>
                                            <c:when test="${ i==0 }"><span>A</span></c:when>
                                            <c:when test="${ i==1 }"><span>B</span></c:when>
                                            <c:when test="${ i==2 }"><span>C</span></c:when>
                                        </c:choose>
                                    </div>
                                    <div class="tycon">
                                        <h3>${ item.title }</h3>
                                        <p>${ item.description }</p>
                                    </div>
                                </div>
                                <p>${ item.content }</p>
                            </div>
                        </div>
                    </jfp:Repeater>
                </div>
            </div>
        </div>

        <!--公司优势-->
        <div class="charactic padT80 padB80">
            <jfp:SqlDataSource id="DB3"
                               cmd="SELECT * FROM tip t LEFT JOIN tip_type y ON type_id = y.id
                                    WHERE y.name = '公司优势' AND y.site_id = ${ site.id }
                                    ORDER BY update_time DESC limit 0, 4" />
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <div class="charList">
                            <h3>全面服务助企业迈向辉煌</h3>
                            <ul>
                                <jfp:Repeater dataSourceID="DB3" var="item" index="i">
                                    <li class="clearfix">
                                        <div>
                                            <c:choose>
                                                <c:when test="${ i==0 }"><span>A</span></c:when>
                                                <c:when test="${ i==1 }"><span>B</span></c:when>
                                                <c:when test="${ i==2 }"><span>C</span></c:when>
                                                <c:when test="${ i==3 }"><span>D</span></c:when>
                                            </c:choose>
                                        </div>
                                        <p>${ item.title }</p>
                                    </li>
                                </jfp:Repeater>
                            </ul>
                            <jfp:SqlDataSource id="DBx"
                                               cmd="SELECT * FROM category WHERE site_id = ${ site.id } AND
                                                name = '联系我们' limit 0, 1" />
                            <jfp:SingleItem dataSourceID="DBx" var="contact" />
                            <jfp:JsonDataSource id="contact_style" value="${ contact.style }"/>
                            <p><a href='<jcp:Url jsp="${ root }content/${ contact_style.page }?id={1}"
                                          html="${ root }${ contact.dir_name }/{1}.html"
                                          isAuto="${ param.auto != null }"
                                          data="{${ contact.id }}" />'>立即联系我们</a></p>
                        </div>
                    </div>
                    <div class="col-md-6 col-md-offset-2 col-sm-6  col-xs-12">
                        <section class="title">
                            <h2>我们的实力</h2>
                        </section>
                        <jfp:SqlDataSource id="DB4"
                                           cmd="SELECT * FROM single_page s LEFT JOIN category c ON s.category_id = c.id
                                                WHERE site_id = ${ site.id } AND c.name = '关于我们'" />
                        <jfp:SingleItem dataSourceID="DB4" var="about" />
                        <jfp:JsonDataSource id="about_style" value="${ about.style }"/>
                        <div class="charIntr">
                            <p>${ about.description }</p>
                            <a href='<jcp:Url jsp="${ root }content/${ about_style.page }?id={1}"
                                          html="${ root }${ about.dir_name }/{1}.html"
                                          isAuto="${ param.auto != null }"
                                          data="{${ about.category_id }}" />'>查看更多</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--产品与服务-->
        <div class="service padT80 padB80 greyBg">
            <div class="container">
                <section class="title">
                    <h2>产品与服务</h2>
                </section>
                <jfp:SqlDataSource id="DB5"
                                   cmd="SELECT *, DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                        FROM word w LEFT JOIN category c ON w.category_id = c.id
                                        WHERE c.name = '产品服务' AND site_id = ${ site.id }
                                        ORDER BY publish_time DESC limit 0, 6" />
                <ul class="row padT80">
                    <jfp:Repeater dataSourceID="DB5" var="item" index="i">
                        <jfp:JsonDataSource id="style" value="${ item.style }"/>
                        <li class="col-sm-4 col-xs-12">
                            <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                              html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ item.id }}" />'>
                            <span>0${ i+1 }</span>
                            <div>
                                <h3>${ item.title }</h3>
                                <p>${ item.description }</p>
                            </div>
                            </a>
                        </li>
                    </jfp:Repeater>
                </ul>
            </div>
        </div>

        <!--成功案例-->
        <div class="case padT80 padB80">
            <div class="container">
                <section class="title">
                    <h2>成功案例</h2>
                </section>
                <jfp:SqlDataSource id="DB6"
                                   cmd="SELECT *, DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                        FROM article LEFT JOIN category c ON category_id = c.id
                                        WHERE c.name = '成功案例' AND site_id = ${ site.id }
                                        ORDER BY publish_time DESC limit 0, 6" />
                <ul class="row padT80">
                    <jfp:Repeater dataSourceID="DB6" var="item">
                        <jfp:JsonDataSource id="style" value="${ item.style }"/>
                        <li class="col-sm-4 col-xs-6">
                            <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                              html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ item.id }}" />'>
                                <img src="${ site.domain }/${ item.thumb }"/>
                            </a>
                        </li>
                    </jfp:Repeater>
                </ul>
            </div>
        </div>

        <!--新闻资讯-->
        <div class="news padT80 padB80 greyBg">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-xs-12">
                        <section class="title">
                            <h2>新闻资讯</h2>
                        </section>
                        <jfp:SqlDataSource id="DB7"
                                           cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d') time
                                           , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                            FROM article a LEFT JOIN category c ON category_id = c.id
                                            WHERE c.name = '新闻头条' AND site_id = ${ site.id }
                                            ORDER BY publish_time DESC limit 0, 1"/>
                        <jfp:SingleItem dataSourceID="DB7" var="news" />
                        <jfp:JsonDataSource id="style" value="${ news.style }"/>
                        <div class="row padT80">
                            <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                              html="${ root }${ news.dir_name }/${ news.year }/${ news.date }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ news.id }}" />'>
                                <img src="${ site.domain }/${ news.thumb }" class="col-sm-6 col-xs-12"/>
                            </a>
                            <div class="col-sm-6 col-xs-12 newRec">
                                <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                              html="${ root }${ news.dir_name }/${ news.year }/${ news.date }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ news.id }}" />'>
                                    <h3>${ news.title }</h3>
                                </a>
                                <p class="newtim">
                                    <span class="glyphicon glyphicon-time"></span>${ news.time }
                                </p>
                                <p class="newcon">${ news.description }</p>
                            </div>
                        </div>
                    </div>
                    <jfp:SqlDataSource id="DB8"
                                       cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d') time
                                            , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                            FROM article a LEFT JOIN category c ON category_id = c.id
                                            WHERE c.name = '行业新闻' AND site_id = ${ site.id }
                                            ORDER BY publish_time DESC limit 0, 4"/>
                    <ul class="col-sm-6 col-xs-12">
                        <jfp:Repeater dataSourceID="DB8" var="item">
                            <jfp:JsonDataSource id="style" value="${ item.style }"/>
                            <li>
                                <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                              html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ item.id }}" />'>
                                    <div>
                                        <span>${ item.time }</span>
                                        <h3>${ item.title }</h3>
                                    </div>
                                    <p>${ item.description }</p>
                                </a>
                            </li>
                        </jfp:Repeater>
                    </ul>
                </div>
            </div>
        </div>
    </jcp:Content>

</jcp:ContentPage>