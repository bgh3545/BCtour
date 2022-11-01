<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>error.jsp</title>
</head>
<body>
	<h1 style="color:red;">!!! WARNING 예외 발생 !!!</h1>
	발생한 예외 : ${pageContext.exception }<br>
	예외 메시지 : ${pageContext.exception.message }<br>
	<ol>
		<c:forEach items="${pageContext.exception.stackTrace }" var= "i">
			<li>${i.toString() }</li>
		</c:forEach>
	</ol>
</body>
</html>