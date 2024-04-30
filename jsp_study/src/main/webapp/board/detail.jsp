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
	<h1>Board Detail Page</h1>
	
	<table border="1">
		<tr>
			<th>bno</th>
			<td>${bvo.bno }</td>
		</tr>
		<tr>
			<th>title</th>
			<td>${bvo.title }</td>
		</tr>
		<tr>
			<th>writer</th>
			<td>${bvo.writer }</td>
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
			<td>${bvo.content }	
			</td>
		</tr>
		<c:if test="${bvo.imageFile ne null }">
		<tr>
			<th></th>
			<td><img alt="" src="/_fileUpload/${bvo.imageFile }"></td>
		</tr>
		</c:if>
	</table>
	<br>
	
	<c:if test="${bvo.writer eq ses.id}">	
		<a href="/brd/modify?bno=${bvo.bno }"><button>수정</button></a>
		<a href="/brd/remove?bno=${bvo.bno }"><button>삭제</button></a>
	</c:if>
	<a href="../index.jsp"><button>index</button></a>
	<a href="/brd/list"><button>리스트로 돌아가기</button></a>
	
	<!-- comment line -->
	<hr>
	<!-- comment 등록 -->
	<div>
		comment line <br>
		<input type="text" id="cmtWriter" value="${ses.id }" readonly="readonly"> <br>
		<input type="text" id="cmtText" placeholder="Add Comment...">
		<button type="button" id="cmtAddBtn">comment post</button>
	</div>
	<br>
	<hr>
	<!-- comment 출력 -->
	<div id="commentLine">
		<div>
			<div>cno, bno, writer, regdate</div>
			<div>
				<button>수정</button> <button>삭제</button> <br>
				<input type="text" value="content">
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}" />`;
		const id = `<c:out value="${ses.id}" />`;
		console.log(bnoVal);
	</script>
	<script type="text/javascript" src="/resources/board_detail.js"></script>

	<!-- 댓글이 없어도 뿌릴 수 있게 따로 한번 더 호출(위에 bnoVal을 사용) -->	
	<!-- board_detail.js 가 선언된 후에 불러와야함 -->
	<script type="text/javascript">
		printCommentList(bnoVal);
	</script>
	
</body>
</html>




















