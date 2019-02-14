<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${ pageContext.request.contextPath }/admin/" var="base"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
	src="${ pageContext.request.contextPath }/statics/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){ 
		
	});
</script>
</head>
<body>
<jfp:JsonDataSource value="{'true':'是','false':'否'}" id="DB1"/>

<form:form action="${ base }saveModule" method="post" modelAttribute="module">
		
	<!-- 防止表单重复提交 -->
	<jfp:token /> 
	
	<form:hidden path="name" value="${ module.name }"/>
	模块目录：${ module.name }
	<br><br>
	
	名称：<form:input path="title"/>
	<br><br>

	版本：<form:input path="version"/>
	<br><br>

	描述：<form:textarea path="description"/>
	<br><br>
	
	地址：<form:input path="url"/>
	<br><br>
	
	是否内置：<form:radiobuttons path="isCore" items="${ DB1 }"/>
	<br><br>
	
	<input type="submit" value="提交">
	  
</form:form>

</body>
</html>