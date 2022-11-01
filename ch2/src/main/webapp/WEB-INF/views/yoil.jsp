<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<!-- 모델 객체가 해당되는 값을 꺼내서 출력해줌 -->
	<h1>
		year=<%=request.getParameter("year") %></h1>
	<h1>getYoilMVC4</h1>
	<p>${myDate.year }년 ${myDate.month }월 ${myDate.day }일 ${yoil }요일입니다.
	</p>
</body>
</html>
