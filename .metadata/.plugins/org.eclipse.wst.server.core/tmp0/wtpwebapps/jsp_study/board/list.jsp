<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-sm">
	<h1>Board List Page</h1>
	
	<!-- Search Line -->
	<div>
		<form action="/brd/list" method="get">
			<div>
				<select name="type"> <!-- select : 여러개 중에 선택 -->
					<option ${ph.pgvo.type eq null ? 'selected':'' }>Choose...</option>
					<option value="t" ${ph.pgvo.type eq 't' ? 'selected':'' }>title</option>
					<option value="w" ${ph.pgvo.type eq 'w' ? 'selected':'' }>writer</option>
					<option value="c" ${ph.pgvo.type eq 'c' ? 'selected':'' }>content</option>
				</select>
				
				<input type="text" name="keyword" placeholder="Search" value=${ph.pgvo.keyword }>
				<input type="hidden" name="pageNum" value="1" }>
				<input type="hidden" name="qty" value="${ph.pgvo.qty }" }>
				<button type="submit">검색</button>
				<span>totalCount : ${ph.totalCount }</span>
			</div>	
		</form>
	</div>
	
	<table class="table table-striped-columns">
		<tr>
			<th>bno</th>
			<th>title</th>
			<th>writer</th>
			<th>regdate</th>
			<th>readcount</th>
		</tr>
		<!-- DB에서 가져온 리스트를 c:forEach를 통해서 반복 출력 -->
		<c:forEach items="${list }" var="bvo">
			<tr>
				<td>${bvo.bno }</td>
				<td> <a href="/brd/detail?bno=${bvo.bno }">${bvo.title } <img alt="" src="/_fileUpload/_th_${bvo.imageFile }"></a> </td>
				<td>${bvo.writer }</td>
				<td>${bvo.regdate }</td>
				<td>${bvo.readCount }</td>
			</tr>
		</c:forEach>
	</table> <br>
	<!-- Paging Line => ph -->
	<div>
	<!-- prev -->
		<c:if test="${ph.prev }">
			<a href="/brd/list?pageNum=${ph.startPage-1 }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> « </a>
		</c:if>
		
		<!-- paging -->
		<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
			<a href="/brd/list?pageNum=${i }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> ${i } </a>
		</c:forEach>
		
		<!-- next -->
		<c:if test="${ph.next }">
			<a href="/brd/list?pageNum=${ph.endPage+1 }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> » </a>
		</c:if>
	</div>
	<br>
	<a href="../index.jsp"><button type="button" class="btn btn-secondary">index</button></a>
</div>
</body>
</html>