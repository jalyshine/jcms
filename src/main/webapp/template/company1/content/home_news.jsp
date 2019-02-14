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
<jfp:SqlDataSource id="DB1" cmd="SELECT * FROM site LEFT JOIN site_info ON site_id = id
                                 WHERE id = ${ category.site_id }"/>
<jfp:SingleItem dataSourceID="DB1" var="site"/>

<%--向母版页传参--%>
<jcp:Param name="root" value="../"/>
<jcp:Param name="siteId" value="${ site.id }" />
<jcp:Param name="menuItemName" value="${ category.name }" />

<%--单页条数，总页数，当前页码--%>
<c:set var="pageSize" value="10"/>
<jfp:SqlDataSource id="db_count"
                   cmd="SELECT CEIL(COUNT(*)/${ pageSize }) FROM article LEFT JOIN category c ON category_id = c.id
                        WHERE category_id = ${ category.id } OR parent_id = ${ category.id }"/>
<jfp:SingleData dataSourceID="db_count" var="pageCnt"/>
<jcp:Error urlError="${ param.auto != null && param.pn > pageCnt }" />
<c:set var="pageNum" value="${ (param.pn==null || param.pn < 1)?1:(param.pn > pageCnt?pageCnt:param.pn) }"/>

<jcp:ContentPage masterPagePath="/template/company1/master.jsp">

    <%--网页头部--%>
    <jcp:Content contentPlaceHolderId="head">
        <title>${ meta.title }</title>
        <meta name="keywords" content="${ meta.keyword }">
        <meta name="description" content="${ meta.description }"/>
    </jcp:Content>

    <%--网页主体--%>
    <jcp:Content contentPlaceHolderId="pageContent">

        <div class="newsPg">
            <div class="banner">
                <img src="${ site.domain }/${ category.image }"/>
            </div>
            <div class="newsList container padT80 padB80">
                <section class="title">
                    <h2>${ category.name }</h2>
                </section>
                <jfp:SqlDataSource id="DB1"
                                   cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d %H:%i') publishTime
                                        , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                        FROM (article a INNER JOIN article_data ON a.id = article_id)
                                        LEFT JOIN category c ON category_id = c.id
                                        WHERE category_id = ${ category.id } OR parent_id = ${ category.id }
                                        ORDER BY publish_time DESC limit ${ (pageNum-1) * pageSize }, ${ pageSize }"/>
                <div class="row padT80">
                    <ul>
                        <jfp:Repeater dataSourceID="DB1" var="item">
                            <li>
                                <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                  html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ item.id }}" />'>
                                    <div class="newsTime">
                                        <p>${ item.date }</p>
                                        <span>${ item.year }</span>
                                    </div>
                                    <div class="newsTitle">
                                        <h3>${ item.title }</h3>
                                        <p>${ item.description }</p>
                                    </div>
                                </a>
                            </li>
                        </jfp:Repeater>
                    </ul>
                </div>
            </div>
            <div class="text-center">
                <c:import url="content_page.jsp">
                    <c:param name="root" value="${ root }" />
                    <c:param name="id" value="${ category.id }" />
                    <c:param name="auto" value="${ param.auto }" />
                    <c:param name="pn" value="${ pageNum }" />
                    <c:param name="cnt" value="${ pageCnt }" />
                </c:import>
            </div>
        </div>

    </jcp:Content>
</jcp:ContentPage>