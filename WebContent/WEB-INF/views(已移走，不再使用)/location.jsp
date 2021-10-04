<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Location details</title>
<style>
	input[type=text] {
		padding: 10px;
		margin: 10px;
		font-size: 20px;
	}
	
	input[type=submit] {
		border: none;
		background-olor: #4CAF50;
		color: white;
		padding: 16px;
		margin: 16px;
		cursor: pointer;
		font-size: 20px;
	}
	
	h2 {
		color: #08298A;
		text-align: center;
	}
</style>
</head>
<body>
	<h2 style="color: #DF0101">
		<c:out value="${greeting}"></c:out>
	</h2>
	<hr/>
	<h2>Submit location for a list of orgs</h2>
	
	<div style="text-align:center">
		<form action="listOrgs">
			<input type="test" name="locationName"><br/>
			<input type="submit" value="Get Orgs"><br/>
		</form>
	</div>

</body>
</html>