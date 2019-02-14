<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!--查找文章-->
<jfp:SqlDataSource id="db_article"
                   cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d %h:%i') publishTime
                        FROM word WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="db_article" var="article"/>
<!--查询栏目-->
<jfp:SqlDataSource id="DB0" cmd="SELECT * FROM category WHERE id = ${ article.category_id }"/>
<jfp:SingleItem dataSourceID="DB0" var="category"/>
<jfp:JsonDataSource id="style" value="${ category.style }"/>
<!--查询站点-->
<jfp:SqlDataSource id="DB1" cmd="SELECT * FROM site WHERE id = ${ category.site_id }"/>
<jfp:SingleItem dataSourceID="DB1" var="site"/>
<!--向母版页传参-->
<jcp:Param name="root" value="${ param.auto == null?'../':'../../../' }"/>
<jcp:Param name="siteId" value="${ category.site_id }"/>
<jcp:Param name="menuItemName" value="${ category.name }"/>

<jcp:ContentPage masterPagePath="/template/cjsoft/master.jsp">

    <%--网页头部--%>
    <jcp:Content contentPlaceHolderId="head">
        <title>${ article.title }</title>
        <meta name="keywords" content="${ article.keywords }">
        <meta name="description" content="${ article.description }"/>
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

        <!-- 内容详情 -->
        <section class="page-content">
            <div class="container">
                <section class="col-md-12">
                    <ul id="filters">
                        <c:if test="${ category.parent_id == null }">
                            <li class="active"><a href="javascript:;">显示全部</a></li>
                            <jfp:SqlDataSource id="DB2" cmd="SELECT * FROM category WHERE parent_id = ${ category.id }"/>
                            <jfp:Repeater dataSourceID="DB2" var="item">
                                <li
                                        <c:if test="${ category.id == item.id }">class="active"</c:if>
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
                                        <c:if test="${ category.id == item.id }">class="active"</c:if>
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
                <div class="article-title">
                    <h3>${ article.title }</h3>
                    <ul>
                        <li><i class="i_author fg-color"></i> &nbsp; Admin</li>
                        <li><i class="i_date fg-color"></i> &nbsp; ${ article.publishTime }</li>
                        <li><i class="i_click fg-color"></i> &nbsp; ${ article.hits }</li>
                        <li><i class="i_category fg-color"></i> &nbsp; ${ category.name }</li>
                    </ul>
                </div>
                <div class="article-content">${ article.content }</div>

                <!-- 相关文章 -->
                <jfp:SqlDataSource id="db_last"
                                   cmd="SELECT *, DATE_FORMAT(publish_time,'%Y') year
                                                , DATE_FORMAT(publish_time,'%m%d') date
                                   FROM word a LEFT JOIN category c ON a.category_id = c.id
                                   WHERE category_id = ${ category.id }
                                   AND unix_timestamp(publish_time) < unix_timestamp('${ article.publish_time }')
                                   ORDER BY publish_time DESC limit 0, 1"/>
                <jfp:SingleItem dataSourceID="db_last" var="last"/>
                <jfp:SqlDataSource id="db_next"
                                   cmd="SELECT *, DATE_FORMAT(publish_time,'%Y') year
                                                , DATE_FORMAT(publish_time,'%m%d') date
                                    FROM word a LEFT JOIN category c ON a.category_id = c.id
                                    WHERE category_id = ${ category.id }
                                    AND unix_timestamp(publish_time) > unix_timestamp('${ article.publish_time }')
                                    ORDER BY publish_time DESC limit 0, 1"/>
                <jfp:SingleItem dataSourceID="db_next" var="next"/>
                <div class="article-relevance">
                    <p>
                        上一篇：
                        <c:if test="${ last == null }">
                            <a href="javascript:;">已经没有了</a>
                        </c:if>
                        <c:if test="${ last != null }">
                            <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                              html="${ root }${ last.dir_name }/${ last.year }/${ last.date }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ last.id }}" />'>${ last.title }</a>
                        </c:if>
                    </p>
                    <p>
                        下一篇：
                        <c:if test="${ next == null }">
                            <a href="javascript:;">已经没有了</a>
                        </c:if>
                        <c:if test="${ next != null }">
                            <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                              html="${ root }${ next.dir_name }/${ next.year }/${ next.date }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ next.id }}" />'>${ next.title }</a>
                        </c:if>
                    </p>
                </div>
            </div>
        </section>
        <script>
            $(function () {
                $("div.article-content img").each(function () {
                    var src = $(this).attr("src");
                    if (src.indexOf("http://") == -1 && src.indexOf("https://") == -1) {
                        $(this).attr("src", '${ site.domain }/' + src);
                    }
                });
            })
        </script>
    </jcp:Content>
</jcp:ContentPage>