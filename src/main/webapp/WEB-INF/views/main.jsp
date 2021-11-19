<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>
	<h2>${sessionScope.id}님 안녕하세요!</h2>
	<p>
		<a href="/list">목록</a>
	</p>
	<p>
		<a href="/logout">로그아웃</a>
	</p>
	<p>
		<a href="/logout">로그아웃</a>
	</p>
	
</body>
</html>
