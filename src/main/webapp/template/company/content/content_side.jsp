<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<%--需要参数：
    id    栏目ID
    root  站点根路径
    auto  是否自动访问
--%>

<%--查询栏目信息--%>
<jfp:SqlDataSource id="DB0" cmd="SELECT * FROM category WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="DB0" var="category"/>
<jfp:JsonDataSource id="style" value="${ category.style }"/>

<%--查询站点信息--%>
<jfp:SqlDataSource id="DB1" cmd="SELECT * FROM site LEFT JOIN site_info ON site_id = id
                                 WHERE id = ${ category.site_id }"/>
<jfp:SingleItem dataSourceID="DB1" var="site"/>

<div class="n_left">
    <div class="n_title">
        <h3>${ category.name }</h3>
        <p>CATEGORY TITLE</p>
    </div>
    <c:if test="${ category.parent_id == null }">
        <jfp:SqlDataSource id="DB2" cmd="SELECT * FROM category WHERE parent_id = ${ category.id }" />
    </c:if>
    <c:if test="${ category.parent_id != null }">
        <jfp:SqlDataSource id="DB2" cmd="SELECT * FROM category WHERE parent_id = ${ category.parent_id }" />
    </c:if>
    <ul>
        <jfp:Repeater dataSourceID="DB2" var="item">
            <jfp:JsonDataSource id="style" value="${ item.style }"/>
            <li><a href='<jcp:Url jsp="${ param.root }content/${ style.home }?id={1}"
                                  html="${ param.root }${ item.dir_name }/{1}_1.html"
                                  isAuto="${ param.auto != '' }"
                                  data="{${ item.id }}" />'>
                    ${ item.name }</a></li>
        </jfp:Repeater>
    </ul>
    <div class="QQline">
        <a href="javascript:;">
            <img src="${ param.root }assets/css/img/online.jpg" />
        </a>
    </div>
    <div class="map">
        <img src="${ site.domain }/${ site.image }" />
    </div>
    <div class="n_contact">
        <div></div>
        联系电话：	${ site.telephone }<br/>
        　　传真：	${ site.fax }<br/>
        电子邮箱：	${ site.email }<br/>
        　　邮编：	${ site.post_code }<br/>
        公司地址：	${ site.address }<br/>
        企业网址：	${ site.url }<br/>
    </div>
</div>