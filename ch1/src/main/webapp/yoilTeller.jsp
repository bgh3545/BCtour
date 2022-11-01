<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import = "java.util.Calendar" %>
<%
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String day = request.getParameter("day");
	
	int yyyy = Integer.parseInt(year);
	int mm 	 = Integer.parseInt(month);
	int dd	 = Integer.parseInt(day);
	
	Calendar cal = Calendar.getInstance();
	cal.set(yyyy,mm-1,dd);
	
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	char yoil = " 일월화수목금토".charAt(dayOfWeek);
%>
<html>
<head>
	<title>yoilTeller.jsp</title>
</head>
<body>
	<h1><%=yyyy%>년 <%=mm%>월 <%=dd%>일은 <%=yoil%>요일 입니다. </h1>
</body>
</html>