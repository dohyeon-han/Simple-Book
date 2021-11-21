<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>list</title>
</head>
<body>
	<h1>책 목록</h1>
	<table>
		<thead>
			<tr>
				<td>제목</td>
				<td>카테고리</td>
				<td>가격</td>
			</tr>
		</thead>
		<tbody class="list">
		</tbody>
	</table>
	<p>
		<a href="/">메인</a>
	</p>
	<p>
		<a href="/create">추가하기</a>
	</p>
	<script type="text/javascript" src="/resources/js/list.js"
		charset="utf-8"></script>
</body>
</html>