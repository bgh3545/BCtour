<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>register</title>	
</head>
<body>
	<h1>id=${user.id}</h1>
	<h1>pwd=${user.pwd}</h1>
	<h1>name=${user.name}</h1>
	<h1>email=${user.email}</h1>
	<h1>birth=${user.birth}</h1>
	<h1><c:forEach var="i" items="${user.hobby}">hobby=${i}<br></c:forEach></h1>
	<h1><c:forEach var="i" items="${user.sns}">sns=${i}<br></c:forEach></h1>
</body>
</html>
