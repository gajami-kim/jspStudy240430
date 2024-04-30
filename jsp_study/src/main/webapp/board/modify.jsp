<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board Modify Page</h1>
	<form action="/brd/update" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>bno</th>
				<td> <input type="text" name="bno" value="${bvo.bno }" > </td>
			</tr>
			<tr>
				<th>title</th>
				<td><input type="text" name="title" value="${bvo.title }"></td>
			</tr>
			<tr>
				<th>writer</th>
				<td>${bvo.writer } </td>
			</tr>
			<tr>
				<th>regdate</th>
				<td>${bvo.regdate }</td>
			</tr>
			<tr>
				<th>moddate</th>
				<td>${bvo.moddate }</td>
			</tr>
			<tr>
				<th>content</th>
				<td> <textarea rows="10" cols="30" name="content">${bvo.content} </textarea> </td>
			</tr>
			<tr>
				<th></th>
				<td><img alt="" src="/_fileUpload/${bvo.imageFile }"> <br>
				<input type="hidden" name="imageFile" value=${bvo.imageFile }>
				<input type="file" name="newFile"></td>
			</tr>
		</table>
		<br>
		<button type="submit">수정</button>
	</form>
	<br>
	<a href="/brd/list"><button>리스트로 돌아가기</button></a>
</body>
</html>