<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Member List Page</h1>
	
	<table border="1">
		<tr>
			<th>id</th>
			<th>email</th>
			<th>age</th>
			<th>phone</th>
			<th>regdate</th>
			<th>lastLogin</th>
		</tr>
		
		<c:forEach items="${list }" var="mvo">
			<tr>
			<td>${mvo.id }</td>
			<td>${mvo.email }</td>
			<td>${mvo.age }</td>
			<td>${mvo.phone }</td>
			<td>${mvo.regdate }</td>
			<td>${mvo.lastlogin }</td>
			</tr>
		</c:forEach>
	</table> <br>
	<a href="../index.jsp"> <button type="button">index</button> </a>
</body>
</html>