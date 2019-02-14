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

<jcp:ContentPage masterPagePath="/template/company1/master.jsp">

    <jcp:Content contentPlaceHolderId="head">
        <title>${ setting.metaTitle }</title>
        <meta name="keywords" content="${ setting.metaKeywords }">
        <meta name="description" content="${ setting.metaDescription }"/>
    </jcp:Content>

    <jcp:Content contentPlaceHolderId="pageContent">
        <div class="abPg">
            <div class="banner">
                <img src="${ site.domain }/${ category.image }"/>
            </div>
            <div class="abIntr container padT80 padB80">
                <section class="title padB80">
                    <h2>公司介绍</h2>
                </section>
                <jfp:SqlDataSource id="DB4"
                                   cmd="SELECT * FROM single_page s LEFT JOIN category c ON s.category_id = c.id
                                        WHERE c.name = '关于我们'" />
                <jfp:SingleItem dataSourceID="DB4" var="about" />
                <div class="row ">
                    <div class="col-sm-6 col-xs-12" >
                        <img src="${ site.domain }/${ about.thumb }"/>
                    </div>
                    <div class="col-sm-6 col-xs-12" >
                        <h3>${ about.title }</h3>
                        <p>${ about.content }</p>
                    </div>
                </div>
            </div>
            <div class="container padB80">
                <div class="row padT80">
                    <jfp:SqlDataSource id="DB2"
                                       cmd="SELECT * FROM tip t LEFT JOIN tip_type y ON type_id = y.id
                                            WHERE y.name = '公司介绍' ORDER BY update_time DESC limit 0, 3" />
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
    </jcp:Content>
</jcp:ContentPage>