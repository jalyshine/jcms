<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<span class="layui-breadcrumb">
    <a href="${ base }content/${ param.et }/add?cid=${ param.cid }">添加${ param.etn }</a>
    <a href="${ base }content/${ param.et }/list?cid=${ param.cid }&odr=${ param.odr }">全部${ param.etn }</a>
    <c:if test="${ param.wfs != null && param.wfs > 0 }">
        <a href="${ base }content/${ param.et }/list?cid=${ param.cid }&tts=0&odr=${ param.odr }">退稿</a>
        <c:forEach var="i" begin="1" end="${ param.wfs }" step="1">
            <a href="${ base }content/${ param.et }/list?cid=${ param.cid }&tts=${ i }&odr=${ param.odr }">${ i }审${ param.etn }</a>
        </c:forEach>
        <a href="${ base }content/${ param.et }/list?cid=${ param.cid }&tts=99&odr=${ param.odr }">通过</a>
    </c:if>
</span>