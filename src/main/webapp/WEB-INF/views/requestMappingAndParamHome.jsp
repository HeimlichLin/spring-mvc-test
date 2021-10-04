<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>@RequestMapping and @ RequestParam test bed</title>
	<style>
		input[type-text] {
			padding: 10px;
			margin: 10px;
			font-size: 20px;
		}
		
		input[type-submit] {
			border: none;
			backgroud-color: #4CAF50;
			color: white;
			padding: 16px;
			margin: 16px;
			cursor: pointer;
			font-size: 20px;
		}
		
		h2 {
			color: #08298A;
			text-align: center
		}
	</style>
</head>
<body>
	<div align="left">
		<h2 align="center">Hello</h2>
		<hr/>
		<form action="test1">
			<h3>test1</h3>
			<label id="org">org</label>
			<input type="text" name="orgname" placeholder="Enter org name" size="40">
			<input type="submit" value="Submit"/>
		</form>
		<br/><br/>
		<form action="test2">
			<h3>test2</h3>
			<label id="org">org method</label>
			<input type="text" name="orgname" placeholder="Enter org name" size="40">
			<input type="submit" value="Submit"/>
		</form>
		<br/><br/>
		<form action="test3">
			<h3>test3</h3>
			<input type="submit" value="Submit"/>
		</form>
		<br/><br/>		
		<form action="test4">
			<h3>test4</h3>
			<label id="org">org defaultValue</label>
			<input type="text" name="orgname" placeholder="Enter org name" size="40">
			<input type="submit" value="Submit"/>
		</form>
		<br/><br/>		
		<form action="test5">
			<h3>test5</h3>
			<label id="org">org without 'name' or 'value' attributes</label>
			<input type="text" name="orgname" placeholder="Enter org name" size="40">
			<input type="submit" value="Submit"/>
		</form>
		
		<form action="test6">
			<h3>test6</h3>
			<label id="upload">Upload</label>
			<input type="file" name="orgname" placeholder="Enter org name">
			<input type="submit" value="Submit"/>
		</form>
		
		<form action="upload" enctype="multipart/form-data" method = "POST">
			<h3>test</h3>
			<label id="upload">Upload</label>
			<input type="file" name="fileName" placeholder="Enter org name">
			<input type="submit" value="Submit"/>
		</form>
	</div>
</body>
</html>