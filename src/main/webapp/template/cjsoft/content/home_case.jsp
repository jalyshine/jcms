<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="jfP" uri="http://www.springframework.org/tags/form-plus" %>

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
<jcp:Param name="menuItemName" value="案例" />

<%--单页条数，总页数，当前页码--%>
<c:set var="pageSize" value="12"/>
<jfp:SqlDataSource id="db_count"
                   cmd="SELECT CEIL(COUNT(*)/${ pageSize }) FROM word LEFT JOIN category c ON category_id = c.id
                        WHERE category_id = ${ category.id } OR parent_id = ${ category.id }"/>
<jfp:SingleData dataSourceID="db_count" var="pageCnt"/>
<jcp:Error urlError="${ param.auto != null && param.pn > pageCnt }" />
<c:set var="pageNum" value="${ (param.pn==null || param.pn < 1)?1:(param.pn > pageCnt?pageCnt:param.pn) }"/>

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
                    <span>你的位置: </span>
                    <ul class="breadcrumb">
                        <li><a href='<jcp:Url jsp="${ root }index.jsp?id=${ site.id }"
                                              html="${ root }index.html"
                                              isAuto="${ param.auto != null }"/>'>首页</a></li>
                        <li><a href="javascript:;">${ category.name }</a></li>
                    </ul>
                </div>
            </div>
        </section>

        <!-- 内容列表 -->
        <section class="page-content">
            <div class="container">
                <div class="row portfolio-filters triggerAnimation animated" data-animate="fadeInDown">
                    <section class="col-md-12">
                        <ul id="filters">
                            <c:if test="${ category.parent_id == null }">
                                <li class="active"><a href="javascript:;">显示全部</a></li>
                                <jfp:SqlDataSource id="DB2" cmd="SELECT * FROM category WHERE parent_id = ${ category.id }"/>
                                <jfp:Repeater dataSourceID="DB2" var="item">
                                    <li
                                            <c:if test="${ param.id == item.id }">class="active"</c:if>
                                    >
                                        <a href='<jcp:Url jsp="${ root }content/${ style.home }?id={1}"
                                                          html="${ root }${ item.dir_name }/{1}_1.html"
                                                          isAuto="${ param.auto != null }"
                                                          data="{${ item.id }}" />'>${ item.name }</a>
                                    </li>
                                </jfp:Repeater>
                            </c:if>
                            <c:if test="${ category.parent_id != null }">
                                <jfp:SqlDataSource id="DBx" cmd="SELECT * FROM category WHERE id = ${ category.parent_id }" />
                                <jfp:SingleItem dataSourceID="DBx" var="parent" />
                                <jfp:JsonDataSource id="parent_style" value="${ parent.style }"/>

                                <li><a href='<jcp:Url jsp="${ root }content/${ parent_style.home }?id={1}"
                                                      html="${ root }${ parent.dir_name }/{1}_1.html"
                                                      isAuto="${ param.auto != null }"
                                                      data="{${ parent.id }}" />'>显示全部</a></li>
                                <jfp:SqlDataSource id="DBy" cmd="SELECT * FROM category WHERE parent_id = ${ parent.id }" />
                                <jfp:Repeater dataSourceID="DBy" var="item">
                                    <li
                                            <c:if test="${ param.id == item.id }">class="active"</c:if>
                                    >
                                    <a href='<jcp:Url jsp="${ root }content/${ style.home }?id={1}"
                                                      html="${ root }${ item.dir_name }/{1}_1.html"
                                                      isAuto="${ param.auto != null }"
                                                      data="{${ item.id }}" />'>${ item.name }</a>
                                    </li>
                                </jfp:Repeater>
                            </c:if>
                        </ul>
                    </section>
                </div>
                <div class="row portfolio-items-holder triggerAnimation animated" data-animate="fadeInUp">
                    <jfp:SqlDataSource id="DB3"
                                       cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d %H:%i') publishTime
                                        , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                        FROM word LEFT JOIN category c ON category_id = c.id
                                        WHERE category_id = ${ category.id } OR parent_id = ${ category.id }
                                        ORDER BY publish_time DESC limit ${ (pageNum-1) * pageSize }, ${ pageSize }"/>
                    <ul id="portfolioitems" class="isotope">
                        <jfp:Repeater dataSourceID="DB3" var="item">
                            <li class="col-md-4 isotope-item">
                                <figure class="portfolio-img-container">
                                    <img src="${ site.domain }/${ item.thumb }" alt="${ item.title }"/>

                                    <div class="portfolio-hover">
                                        <ul class="portfolio-links">
                                            <li class="portfolio-zoom">
                                                <a href="${ site.domain }/${ item.thumb }"
                                                   class="gallery-item i_ic_setting_zoom"></a>
                                            </li>

                                            <li class="show_case">
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
                                                          data="{${ item.id }}" />'><h4>${ item.title }</h4></a>
                                        <span class='categories'>${ item.description }</span>
                                    </figcaption>
                                </figure>
                            </li>
                        </jfp:Repeater>
                    </ul>
                </div>
                <div class="row">
                    <c:import url="content_page.jsp">
                        <c:param name="root" value="${ root }" />
                        <c:param name="id" value="${ category.id }" />
                        <c:param name="auto" value="${ param.auto }" />
                        <c:param name="pn" value="${ pageNum }" />
                        <c:param name="cnt" value="${ pageCnt }" />
                    </c:import>
                </div>
            </div>
        </section>
    </jcp:Content>
</jcp:ContentPage>