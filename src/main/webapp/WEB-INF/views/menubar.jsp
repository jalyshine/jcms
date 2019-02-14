<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<c:set var="menu_id" value="${ param.menu }"/>
<c:if test="${ menu_id == null || menu_id == '' }">
    <jfp:SqlDataSource id="menu_id"
                       cmd="SELECT IF(depth=3, id, parent_id) FROM back_menu WHERE concat('${ base }', module, '/', entity, '/', action)
                       = '${requestScope['javax.servlet.forward.request_uri']}' AND
                       IF(data is null or data = '', 1=1 , locate(data, '${ pageContext.request.queryString }') > 0)"/>
</c:if>
<jfp:SqlDataSource id="menu_count"
                   cmd="SELECT COUNT(*) FROM back_menu WHERE id = ${ menu_id } OR (parent_id = ${ menu_id } AND display = 1)"/>
<c:if test="${ menu_count != '1' }">
    <jfp:SqlDataSource id="menu_items"
                       cmd="SELECT * FROM back_menu WHERE id = ${ menu_id } OR (parent_id = ${ menu_id } AND display = 1)"/>
    <span class="layui-breadcrumb">
        <jfp:Repeater dataSourceID="menu_items" var="m">
            <a href="${ base }${ param.module }/${ m.entity }/${m.action}?menu=${ menu_id }<c:if test="${ !empty m.data }">&${m.data}</c:if>">
                ${ m.name }
            </a>
        </jfp:Repeater>
    </span>
</c:if>