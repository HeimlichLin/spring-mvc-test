<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Org home page</title>
</head>
<body>
	<h2 align="center" style="color:#DF0101">Welcome! <c:out value="${greeting}"></c:out></h2>
	<hr/>
	<h1 align="center">Org Details</h1>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<div style="text-align:center">
		<a href="${contextPath}/location" style="font-size: 30px">Click here to get org details for location.</a>
	</div>
</body>
</html>