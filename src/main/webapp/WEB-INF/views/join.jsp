<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
</head>
<body>
<form class = "join_form" method="post">
	ID : <input type="text" class = "id" name="id">
	PW : <input type="password" class = "pw" name="pw">
	<button type="button" onclick="return join()">가입하기</button>
</form>
<script type="text/javascript" src="/resources/js/join.js"
		charset="utf-8"></script>
</body>
</html>