<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form class="form">
	<p>제목 : <input type="text" name="title"></p>
    <p>카테고리 : 
    <select class="category" name="categoryId">
    </select></p>
    <p>가격 : <input type="text" name="price"></p>
    <p><button class="btn">저장</button>
    <p>
		<a href="/list">목록</a>
	</p>
</form>
<script type="text/javascript" src="resources/js/create.js"
		charset="utf-8"></script>
</body>
</html>