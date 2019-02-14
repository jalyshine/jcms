<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<%--更新文章点击次数--%>
<c:if test="${ param.content == 'article' }">
    <jfp:SqlUpdateCommand cmd="UPDATE article_data SET hits = hits + 1 WHERE article_id = ${ param.id }" />
</c:if>
<%--更新文档点击次数--%>
<c:if test="${ param.content == 'word' }">
    <jfp:SqlUpdateCommand cmd="UPDATE word SET hits = hits + 1 WHERE id = ${ param.id }" />
</c:if>
<%--更新图片点击次数--%>
<c:if test="${ param.content == 'picture' }">
    <jfp:SqlUpdateCommand cmd="UPDATE picture_data SET hits = hits + 1 WHERE picture_id = ${ param.id }" />
</c:if>
<%--更新视频点击次数--%>
<c:if test="${ param.content == 'video' }">
    <jfp:SqlUpdateCommand cmd="UPDATE video_data SET hits = hits + 1 WHERE video_id = ${ param.id }" />
</c:if>
<%--更新下载点击次数--%>
<c:if test="${ param.content == 'download' }">
    <jfp:SqlUpdateCommand cmd="UPDATE download_data SET hits = hits + 1 WHERE download_id = ${ param.id }" />
</c:if>