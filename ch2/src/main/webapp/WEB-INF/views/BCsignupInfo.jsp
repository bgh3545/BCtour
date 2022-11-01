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
	<h1>id=${signuser.id }</h1>
	<h1>pwd=${signuser.pwd }</h1>
	<h1>name=${signuser.name }</h1>
	<h1>email=${signuser.email }</h1>
	<h1>tel=${signuser.tel }</h1>

	<a href="<c:url value='/BCinput'/>" style="font-size: 2em;">목록</a>
</body>
</html>