<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<span class="layui-breadcrumb">
<a href="${ base }music/${ param.entity }/add?sid=${ param.sid }">添加${ param.name }</a>
<a href="${ base }music/${ param.entity }/list?sid=${ param.sid }">${ param.name }管理</a>
<a href="${ base }music/MusicSinger/list">返回歌手管理</a>
<a href="${ base }music/${ param.entity }/list">返回${ param.name }列表</a>
</span>
