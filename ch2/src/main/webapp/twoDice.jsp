<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.Random"%>
<%!
	int getRandomInt(int range) {
		return new Random().nextInt(range) + 1;
	}
%>
<%
	int idx1 = getRandomInt(6);
	int idx2 = getRandomInt(6);
%>
<html>
<head>
<title>twoDice.jsp</title>
</head>
<body>
	<img src='resources/img/dice<%=idx1%>.png'>
	<img src='resources/img/dice<%=idx2%>.png'>
</body>
</html>