<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath }" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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