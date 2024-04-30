<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Board Register Page</h1>

<!-- enctype="multipart/form-data" : 이미지 파일을 가져갈 때 사용하는 타입 -->
<!-- 파일 : 종류/확장자  image/jpg  image/png  -->
<!-- accept : 첨부할 파일 확장자를 완전히 걸러주지는 않음. 처음 파일을 띄울때 내가 원하는 확장자를 먼저 보여줄 수 있게 만들어줌 -->
<form action="/brd/insert" method="post" enctype="multipart/form-data">
	title : <input type="text" name="title"> <br>
	writer : <input type="text" name="writer" value="${ses.id }" readonly="readonly"> <br>
	content : <textarea rows="10" cols="30" name="content"></textarea> <br>
	첨부파일 : <input type="file" name="imageFile" accept="image/png, image/png, image/gif, image/jpeg"> <br> <br>
	<button type="submit">등록</button>
</form>
<a href="/brd/list"><button>list로 돌아가기</button></a>
</body>
</html>