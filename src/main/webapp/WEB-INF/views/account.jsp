<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<c:if test="${ current_site != 1 }">
    <jfp:SqlDataSource id="DB0" cmd="SELECT domain, user_name, password FROM site WHERE id = ${ current_site }"/>
    <jfp:SingleItem dataSourceID="DB0" var="as" />
    <c:set var="host" value="${ as.domain }" scope = "request"/>
    <c:set var="host_account" value="${ as.user_name }" scope = "request"/>
    <c:set var="host_password" value="${ as.password }" scope = "request"/>
</c:if>
<c:if test="${ current_site == 1 }">
    <c:set var="host" value="${ pageContext.request.contextPath }" scope = "request"/>
</c:if>