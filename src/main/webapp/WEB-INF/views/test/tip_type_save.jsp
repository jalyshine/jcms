<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<%--<jfp:SqlCommand method="insert"--%>
				<%--cmd="INSERT INTO tip_type (site_id, name, description)--%>
				<%--VALUES (${ siteId }, '${ name }', '${ description }')" />--%>

<jfp:JsonDataSource value="{'title':'高清'}" id="qualitys" />
<%
	Gson gson = new Gson();
	Map map = (Map) pageContext.getAttribute("qualitys");
	out.write(gson.toJson(map));
%>