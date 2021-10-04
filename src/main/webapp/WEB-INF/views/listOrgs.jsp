<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listing org using a Service</title>
<style>
table {
	border-collapse: collapse;
}

table, th, td {
	padding: 5px;
	margin: 10px;
	font-size: 20px;
	border: 1px solid black;
}
</style>
</head>
<body>
	<h2 align="center" style="color: #DF0101">
		<c:out value="${greeting}"></c:out>
	</h2>
	<hr />
	<div align="center">
		<table>
			<caption style="font-size: 20px; margin-bottom: 10px">
				<c:out value="${locationName}"/> Org Directory
			</caption>
			<tr>
				<th>Name</th>
				<th>Year</th>
				<th>Code</th>
			</tr>
			<c:forEach var="row" items="${orgList}">
				<tr>
					<td><c:out value="${row.name}"/></td>
					<td><c:out value="${row.year}"/></td>
					<td><c:out value="${row.code}"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>