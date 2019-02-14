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
<jcp:Param name="siteId" value="${ site.id }"/>
<jcp:Param name="menuItemName" value="方案"/>

<%--单页条数，总页数，当前页码--%>
<c:set var="pageSize" value="10"/>
<jfp:SqlDataSource id="db_count"
                   cmd="SELECT CEIL(COUNT(*)/${ pageSize }) FROM word LEFT JOIN category c ON category_id = c.id
                        WHERE category_id = ${ category.id } OR parent_id = ${ category.id }"/>
<jfp:SingleData dataSourceID="db_count" var="pageCnt"/>
<jcp:Error urlError="${ param.auto != null && param.pn > pageCnt }"/>
<c:set var="pageNum" value="${ (param.pn==null || param.pn < 1)?1:(param.pn > pageCnt?pageCnt:param.pn) }"/>

<jcp:ContentPage masterPagePath="/template/cjsoft/master.jsp">

    <%--网页头部--%>
    <jcp:Content contentPlaceHolderId="head">
        <title>${ meta.title }</title>
        <meta name="keywords" content="${ meta.keyword }">
        <meta name="description" content="${ meta.description }"/>
    </jcp:Content>

    <%--网页主体--%>
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
                <jfp:SqlDataSource id="DB3"
                                   cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d %H:%i') publishTime
                                        , DATE_FORMAT(publish_time,'%Y') year, DATE_FORMAT(publish_time,'%m%d') date
                                        FROM word LEFT JOIN category c ON category_id = c.id
                                        WHERE category_id = ${ category.id } OR parent_id = ${ category.id }
                                        ORDER BY publish_time DESC limit ${ (pageNum-1) * pageSize }, ${ pageSize }"/>

                <div class="row triggerAnimation animated" data-animate="fadeInUp">
                    <jfp:Repeater dataSourceID="DB3" var="item">
                        <div class="solution-item col-md-6 col-xs-12">
                            <div class="solution-img">
                                <img src="${ site.domain }/${ item.thumb }" alt="${ item.title }">
                                <div class="solution-bk"></div>
                                <i class="icon-apple"></i>
                            </div>
                            <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                              html="${ root }${ item.dir_name }/${ item.year }/${ item.date }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ item.id }}" />'>${ item.title }</a>
                            <p>${ item.description }</p>
                        </div>
                    </jfp:Repeater>
                </div>
            </div>
        </section>
    </jcp:Content>
</jcp:ContentPage>