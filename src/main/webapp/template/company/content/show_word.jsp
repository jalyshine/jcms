<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!--查找文档-->
<jfp:SqlDataSource id="db_word"
                   cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d %h:%i') publishTime
                        FROM word WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="db_word" var="word"/>
<!--查询栏目-->
<jfp:SqlDataSource id="DB0" cmd="SELECT * FROM category WHERE id = ${ word.category_id }"/>
<jfp:SingleItem dataSourceID="DB0" var="category"/>
<jfp:JsonDataSource id="style" value="${ category.style }"/>
<!--查询站点-->
<jfp:SqlDataSource id="DB1" cmd="SELECT * FROM site WHERE id = ${ category.site_id }"/>
<jfp:SingleItem dataSourceID="DB1" var="site"/>
<!--向母版页传参-->
<jcp:Param name="root" value="${ param.auto == null?'../':'../../../' }"/>
<jcp:Param name="siteId" value="${ category.site_id }"/>
<jcp:Param name="menuItemName" value="${ category.name }"/>

<jcp:ContentPage masterPagePath="/template/company/master.jsp">

    <%--网页头部--%>
    <jcp:Content contentPlaceHolderId="head">
        <title>${ word.title }</title>
        <meta name="keywords" content="${ word.keywords }">
        <meta name="description" content="${ word.description }"/>
        <script src="hit.jsp?content=word&id=${ word.id }" type="text/javascript"></script>
        <link href="${ root }assets/css/nei.css" rel="stylesheet"/>
        <script>
            $(function () {
                $("#textarea img").each(function () {
                    var src = $(this).attr("src");
                    if (src.indexOf("http://") == -1 && src.indexOf("https://") == -1) {
                        $(this).attr("src", '${ site.domain }/' + src);
                    }
                });
            })
        </script>
    </jcp:Content>

    <%--网页主体--%>
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
                    <div class="listConts">
                        <!-- 标题区域 -->
                        <h1 class="title_1">${ word.title }</h1>
                        <div class="info">
                            <small>更新时间：</small>${ word.publishTime }
                            <small>点击次数：</small>${ word.hits }次
                        </div>
                        <!-- 摘要区域 -->
                        <div class="desc">${ word.description }</div>
                        <!-- 内容区域开始 -->
                        <div id="textarea">
                            ${ word.content }
                        </div>
                        <div class="author"> (编辑：admin)</div>
                        <!-- 相关文章 -->
                        <jfp:SqlDataSource id="db_last"
                                           cmd="SELECT *, DATE_FORMAT(publish_time,'%Y') year
                                                , DATE_FORMAT(publish_time,'%m%d') date
                                   FROM word a LEFT JOIN category c ON a.category_id = c.id
                                   WHERE category_id = ${ category.id }
                                   AND unix_timestamp(publish_time) < unix_timestamp('${ word.publish_time }')
                                   ORDER BY publish_time DESC limit 0, 1"/>
                        <jfp:SingleItem dataSourceID="db_last" var="last"/>
                        <jfp:SqlDataSource id="db_next"
                                           cmd="SELECT *, DATE_FORMAT(publish_time,'%Y') year
                                                , DATE_FORMAT(publish_time,'%m%d') date
                                    FROM word a LEFT JOIN category c ON a.category_id = c.id
                                    WHERE category_id = ${ category.id }
                                    AND unix_timestamp(publish_time) > unix_timestamp('${ word.publish_time }')
                                    ORDER BY publish_time DESC limit 0, 1"/>
                        <jfp:SingleItem dataSourceID="db_next" var="next"/>
                        <div class="preNext">
                            <div class="line"><strong></strong></div>
                            <ul class="text">
                                <li>上一篇：
                                    <c:if test="${ last == null }">已经没有了</c:if>
                                    <c:if test="${ last != null }">
                                        <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                      html="${ root }${ last.dir_name }/${ last.year }/${ last.date }/{1}.html"
                                                      isAuto="${ param.auto != null }"
                                                      data="{${ last.id }}" />'>
                                            ${ last.title }
                                        </a>
                                    </c:if>
                                </li>
                                <li>下一篇：
                                    <c:if test="${ next == null }">已经没有了</c:if>
                                    <c:if test="${ next != null }">
                                        <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                      html="${ root }${ next.dir_name }/${ next.year }/${ next.date }/{1}.html"
                                                      isAuto="${ param.auto != null }"
                                                      data="{${ next.id }}" />'>
                                                ${ next.title }
                                        </a>
                                    </c:if>
                                </li>
                            </ul>
                            <ul class="actBox">
                                <li id="act-pus"><a href="javascript:;" onClick="AddFavorite();">收藏</a></li>
                                <li id="act-pnt"><a href="javascript:;" onClick="window.print();">打印</a></li>
                            </ul>
                            <input type="hidden" name="aid" id="aid" value="19" />
                            <input type="hidden" name="molds" id="molds" value="1" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jcp:Content>
</jcp:ContentPage>