<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${ pageContext.request.contextPath }/admin/" var="base"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ pageContext.request.contextPath }/statics/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){ 
		
	});
</script>
<style type="text/css">
	#searchArea{ width: 98%; line-height: 32px; background-color: #F2E2E2;
	 	border: 1px solid orange; margin: 0 auto; padding: 10px;}
	.pageNo,.pageSize{ width: 30px; text-align: center; }
</style>
</head>
<body>
<form action="" id="_deleteForm" method="post">
	<input type="hidden" name="_method" value="DELETE" >
</form>
<table border="1" cellpadding="10" cellspacing="0">
	<thead>
		<tr>
			<td>模块名称</td>
			<td>模块目录</td>
			<td>版本号</td>
			<td>安装日期</td>
			<td>更新日期</td>
			<td>管理操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ page.content }" var="module">
			<tr>
				<td>${ module.title }</td>
				<td>${ module.name }</td>
				<td>${ module.version }</td>
				<td>
					<fmt:formatDate value="${ module.installDate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${ module.updateDate }" pattern="yyyy-MM-dd"/>
				</td>					
				<td>
					<a href="${ base }">安装/卸载</a> &nbsp; &nbsp;
					<a href="${ base }editModule/${ module.name }">编辑</a>
				</td> 
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
			<tr>
				<td colspan="7">
					总共${ page.totalPages }页 &nbsp; &nbsp; 
					每页<input type="text" class="pageSize" value="${ page.size }">条记录 &nbsp; &nbsp;
					当前第<input type="text" class="pageNo" value="${ page.number + 1 }">页 &nbsp; &nbsp; 
					<input type="button" value="GO" > &nbsp; &nbsp; &nbsp; &nbsp;
					<a title="${ page.number }" href="javascript:;">上一页</a> | 
					<a title="${ page.number + 2 }" href="javascript:;">下一页</a>
				</td>
			</tr>
		</tfoot>
</table> 

</body>
</html>