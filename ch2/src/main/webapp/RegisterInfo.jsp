<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>id=${param.id }</h1>
	<h1>pw=${param.pw }</h1>
	<h1>name=${param.name }</h1>
	<h1>email=${param.email }</h1>
	<h1>birth=${param.birth }</h1>
	<h1>sns=${paramValues.sns[0] }</h1>
	<h1>sns=${paramValues.sns[1] }</h1>
	<h1>sns=${paramValues.sns[2] }</h1>

	<h1>sns=<%=request.getParameterValues("sns")[0] %></h1>
	<h1>sns=<%=request.getParameterValues("sns")[1] %></h1>
	<h1>sns=<%=request.getParameterValues("sns")[2] %></h1>  
</body>
</html>