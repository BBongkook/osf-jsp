<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성일</th>
		<th>작성시간</th>
	</tr>
	<tr>
		<td>${pBoard.pb_num}</td>
		<td><img title ="${pBoard.pb_real_path}"
		width="30"src="${pBoard.pb_file_path}"
		alt="${pBoard.pb_real_path}">
		${pBoard.pb_title}</td>
		<td>${pBoard.pb_credat}</td>
		<td>${pBoard.pb_cretim}</td>
	</tr>
</table>
</body>
</html>