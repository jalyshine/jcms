<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<%--查询当前栏目，获取静态设置--%>
<jfp:SqlDataSource id="DB0" cmd="SELECT * FROM category WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="DB0" var="category"/>
<jfp:JsonDataSource id="setting" value="${ category.setting }"/>
<%--查询当前站点，设置模板样式的基本路径，和主机站点的基本路径 --%>
<jfp:SqlDataSource id="DB1" cmd="SELECT * FROM site WHERE id = ${ category.site_id }"/>
<jfp:SingleItem dataSourceID="DB1" var="site"/>
<%--向母版页传参--%>
<jcp:Param name="root" value="../"/>
<jcp:Param name="siteId" value="${ site.id }" />
<jcp:Param name="menuItemName" value="${ category.name }" />

<jcp:ContentPage masterPagePath="/template/company/master.jsp">

    <jcp:Content contentPlaceHolderId="head">
        <title>${ setting.metaTitle }</title>
        <meta name="keywords" content="${ setting.metaKeywords }">
        <meta name="description" content="${ setting.metaDescription }"/>
        <link href="${ root }assets/css/nei.css" rel="stylesheet"/>
        <script>
            $(function () {
                $(".n_centent img").each(function () {
                    var src = $(this).attr("src");
                    if (src.indexOf("http://") == -1 && src.indexOf("https://") == -1) {
                        $(this).attr("src", '${ site.domain }/' + src);
                    }
                });
            })
        </script>
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
                <jfp:SqlDataSource id="DB1" cmd="SELECT * FROM single_page WHERE category_id = ${ category.id }" />
                <jfp:SingleItem dataSourceID="DB1" var="item" />
                <div class="n_centent">${ item.content }</div>
            </div>
        </div>
    </jcp:Content>
</jcp:ContentPage>