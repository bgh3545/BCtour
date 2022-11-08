<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
table {
	width: 100%;
	border: 1px solid black;
	border-collapse: collapse;
}

tr, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<table>
		<tr>
			<th>id</th>
			<th>pwd</th>
			<th>이름</th>
			<th>email</th>
			<th>tel</th>
			<th>reg_date</th>
		</tr>
		<c:forEach var="i" items="${bcuser }">
			<tr>
				<td>${i.id }</td>
				<td>${i.pwd }</td>
				<td>${i.name }</td>
				<td>${i.email }</td>
				<td>${i.tel }</td>
				<td>${i.reg_date }</td>
			</tr>
		</c:forEach>
	
		<a href="<c:url value='/BClogin/BClogin'/>" style="font-size: 2em;">로그인</a>
	</table>
</body>
</html>