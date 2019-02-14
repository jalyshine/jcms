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

<jcp:ContentPage masterPagePath="/template/company1/master.jsp">

    <%--网页头部--%>
    <jcp:Content contentPlaceHolderId="head">
        <title>${ word.title }</title>
        <meta name="keywords" content="${ word.keywords }">
        <meta name="description" content="${ word.description }"/>
        <script src="hit.jsp?content=word&id=${ word.id }" type="text/javascript"></script>
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
        <div class="newsPg">
            <div class="banner">
                <img src="${ site.domain }/${ category.image }"/>
            </div>
            <div class="newsDetail padT80 padB80 container">
                <div class="newdetitle">
                    <h2>${ word.title }</h2>
                    <p class="newdetime">
                        <span class="glyphicon glyphicon-time"></span>${ word.publishTime }
                    </p>
                </div>
                <div class="newdetcon padT80">${ word.content }</div>
            </div>
        </div>
    </jcp:Content>
</jcp:ContentPage>