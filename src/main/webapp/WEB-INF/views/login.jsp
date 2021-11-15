<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Login</title>
</head>
<body>
	<form action="/login" method="post">
		ID : <input type="text" name="id"><br> PW : <input
			type="password" name="pw"><br>
		<br>
		<button type="submit">전송</button>
	</form>
	<button onclick="location.href='/join'">회원가입</button>
	<c:if test="${param.error eq true}">
		<p>${loginFailMsg}</p>
	</c:if>
</body>
</html>