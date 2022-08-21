<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="2">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>가격</th>
			<th>카테고리</th>
		</tr>
		<c:forEach var="v" items="${datas}">
		<tr>
			<td>${v.getPid()}</td>
			<td>${v.getPname()}</td>
			<td>${v.getPprice()}</td>
			<td>${v.getPcategory()}</td>
		</tr>
		</c:forEach>
	</table>
		<c:forEach var="i" begin="1" end="${pcnt}">
			<a href='ctrl.jsp?action=main&pageNum=${i}'>${i}</a>
		</c:forEach>
</body>
</html>