<%@ page contentType="text/html; charset=utf-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error.jsp</title>
</head>
<body>
	<h1></h1>
	발생한 예외 : ${pageContext.exception}<br>
	예외 메세지 : ${pageContext.exception.message}<br>
	<ol>
		<c:forEach items="${pageContext.exception.stackTrace}" var="i">
		<li>${i.toString()}</li>
		</c:forEach>
	</ol>
</body>
</html>