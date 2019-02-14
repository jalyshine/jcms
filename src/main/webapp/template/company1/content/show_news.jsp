<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!--查找文章-->
<jfp:SqlDataSource id="db_article"
                   cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d %h:%i') publishTime
                        FROM article LEFT JOIN article_data ON id = article_id WHERE id = ${ param.id }"/>
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

<jcp:ContentPage masterPagePath="/template/company1/master.jsp">

    <%--网页头部--%>
    <jcp:Content contentPlaceHolderId="head">
        <title>${ article.title }</title>
        <meta name="keywords" content="${ article.keywords }">
        <meta name="description" content="${ article.description }"/>
    </jcp:Content>

    <%--网页主体--%>
    <jcp:Content contentPlaceHolderId="pageContent">
        <div class="newsPg">
            <div class="banner">
                <img src="${ site.domain }/${ category.image }"/>
            </div>
            <div class="newsDetail padT80 padB80 container">
                <div class="newdetitle">
                    <h2>${ article.title }</h2>
                    <p class="newdetime">
                        <span class="glyphicon glyphicon-time"></span>${ article.publishTime }
                    </p>
                </div>
                <div class="newdetcon padT80">${ article.content }</div>
                <nav class="padT80">
                    <!-- 相关文章 -->
                    <jfp:SqlDataSource id="db_last"
                                       cmd="SELECT *, DATE_FORMAT(publish_time,'%Y') year
                                                , DATE_FORMAT(publish_time,'%m%d') date
                                   FROM article a LEFT JOIN category c ON a.category_id = c.id
                                   WHERE category_id = ${ category.id }
                                   AND unix_timestamp(publish_time) < unix_timestamp('${ article.publish_time }')
                                   ORDER BY publish_time DESC limit 0, 1"/>
                    <jfp:SingleItem dataSourceID="db_last" var="last"/>
                    <jfp:SqlDataSource id="db_next"
                                       cmd="SELECT *, DATE_FORMAT(publish_time,'%Y') year
                                                , DATE_FORMAT(publish_time,'%m%d') date
                                    FROM article a LEFT JOIN category c ON a.category_id = c.id
                                    WHERE category_id = ${ category.id }
                                    AND unix_timestamp(publish_time) > unix_timestamp('${ article.publish_time }')
                                    ORDER BY publish_time DESC limit 0, 1"/>
                    <jfp:SingleItem dataSourceID="db_next" var="next"/>
                    <ul class="pager">
                        <li class="previous">
                            <c:if test="${ last == null }">
                                <a href="javascript:;">
                                <span aria-hidden="true">&larr;</span> 上一篇：已经没有了
                                </a>
                            </c:if>
                            <c:if test="${ last != null }">
                                <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                              html="${ root }${ last.dir_name }/${ last.year }/${ last.date }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ last.id }}" />'>
                                    <span aria-hidden="true">&larr;</span> 上一篇：${ last.title }
                                </a>
                            </c:if>
                        </li>
                        <li class="next">
                            <c:if test="${ next == null }">
                                <a href="javascript:;">
                                下一篇：已经没有了 <span aria-hidden="true">&rarr;</span>
                                </a>
                            </c:if>
                            <c:if test="${ next != null }">
                                <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                      html="${ root }${ next.dir_name }/${ next.year }/${ next.date }/{1}.html"
                                                      isAuto="${ param.auto != null }"
                                                      data="{${ next.id }}" />'>
                                    下一篇：${ next.title } <span aria-hidden="true">&rarr;</span>
                                </a>
                            </c:if>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <script>
            $(function () {
                $("div.newdetcon img").each(function () {
                    var src = $(this).attr("src");
                    if (src.indexOf("http://") == -1 && src.indexOf("https://") == -1) {
                        $(this).attr("src", '${ site.domain }/' + src);
                    }
                });
            })
        </script>
    </jcp:Content>
</jcp:ContentPage>