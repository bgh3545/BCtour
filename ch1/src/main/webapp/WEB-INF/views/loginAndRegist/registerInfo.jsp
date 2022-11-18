<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>register</title>
	<style>
	table, th, td {
  		border:1px solid black;
 	 	border-collapse: collapse;
	}
	</style>	
</head>
<body><table>
	<c:forEach var="i" items="${list}">
		<tr>
			<td>${i.id}</td>
			<td>${i.pwd}</td>
			<td>${i.name}</td>
			<td>${i.email}</td>
			<td>${i.birth}</td>
			<td>${i.sns}</td>
			<td>${i.reg_date}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>
