<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>id=${user.id }</h1>
	<h1>pwd=${user.pwd }</h1>
	<h1>name=${user.name }</h1>
	<h1>email=${user.email }</h1>
	<h1>birth=${user.birth }</h1>
	<h1>sns=${user.sns }</h1>
	<h1>reg_date=${user.reg_date }</h1>
	<h1>list=${user1 }</h1>

	<a href="<c:url value='/DBinput'/>" style="font-size: 2em;">목록</a>
</body>
</html>