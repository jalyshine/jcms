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

<%--单页条数，总页数，当前页码--%>
<c:set var="pageSize" value="12"/>
<jfp:SqlDataSource id="db_count" cmd="SELECT CEIL(COUNT(*)/${ pageSize }) FROM article WHERE category_id = ${ category.id }"/>
<jfp:SingleData dataSourceID="db_count" var="pageCnt"/>
<jcp:Error urlError="${ param.auto != null && param.pn > pageCnt }" />
<c:set var="pageNum" value="${ (param.pn==null || param.pn < 1)?1:(param.pn > pageCnt?pageCnt:param.pn) }"/>

<jcp:ContentPage masterPagePath="/template/company/master.jsp">

    <jcp:Content contentPlaceHolderId="head">
        <title>${ meta.title }</title>
        <meta name="keywords" content="${ meta.keyword }">
        <meta name="description" content="${ meta.description }"/>
        <link href="${ root }assets/css/nei.css" rel="stylesheet"/>
    </jcp:Content>

    <jcp:Content contentPlaceHolderId="pageContent"> 
        <div class="n_Main">
            <c:import url="content_side.jsp">
                <c:param name="id" value="${ category.id }" />
                <c:param name="root" value="${ root }" />
                <c:param name="auto" value="${ param.auto }" />
            </c:import>

            <div class="n_right">
                <c:import url="content_nav.jsp?id=${ category.id }" />
                <div class="n_centent">
                    <jfp:SqlDataSource id="DB2"
                                       cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d %h:%i') publishTime
                                        , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                        FROM (article a INNER JOIN article_data ON a.id = article_id)
                                        LEFT JOIN category c ON category_id = c.id
                                        WHERE category_id = ${ category.id } OR parent_id = ${ category.id }
                                        ORDER BY publish_time DESC limit ${ (pageNum-1) * pageSize }, ${ pageSize }"/>
                    <ul class="alMain">
                        <jfp:Repeater dataSourceID="DB2" var="item">
                            <li>
                                <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                  html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ item.id }}" />'>
                                <img src="${ site.domain }/${ item.thumb }" width="216" height="146"/>
                                <p>${ item.title }</p>
                                </a>
                            </li>
                        </jfp:Repeater>
                    </ul>
                    <div class="page_list">
                        <c:import url="content_page.jsp">
                            <c:param name="root" value="${ root }" />
                            <c:param name="id" value="${ category.id }" />
                            <c:param name="auto" value="${ param.auto }" />
                            <c:param name="pn" value="${ pageNum }" />
                            <c:param name="cnt" value="${ pageCnt }" />
                        </c:import>
                    </div>
                </div>
            </div>
        </div>
    </jcp:Content>
</jcp:ContentPage>