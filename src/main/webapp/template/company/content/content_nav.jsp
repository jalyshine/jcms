<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<%--需要参数：
    id    栏目ID
--%>

<%--查询栏目信息--%>
<jfp:SqlDataSource id="DB0" cmd="SELECT * FROM category WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="DB0" var="category"/>

<%--查询站点信息--%>
<jfp:SqlDataSource id="DB1" cmd="SELECT * FROM site LEFT JOIN site_info ON site_id = id
                                 WHERE id = ${ category.site_id }"/>
<jfp:SingleItem dataSourceID="DB1" var="site"/>

<div class="location">
    当前所在位置：首页
    <c:if test="${ category.parent_id == null }">
        &nbsp; &gt;&gt; &nbsp;${ category.name }
    </c:if>
    <c:if test="${ category.parent_id != null }">
        <jfp:SqlDataSource id="DB2"
                           cmd="SELECT * FROM category WHERE id = ${ category.parent_id }"/>
        <jfp:SingleItem dataSourceID="DB2" var="parent"/>
        &nbsp; &gt;&gt; &nbsp; ${ parent.name }
        &nbsp; &gt;&gt; &nbsp; ${ category.name }
    </c:if>
    <div class="n_phone">联系电话:${ site.telephone }</div>
</div>
