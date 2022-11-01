<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.greenart.ch12.*"%>
<%
	Person person = new Person();
	request.setAttribute("person", person);
	request.setAttribute("name", "춘식이");
	pageContext.setAttribute("name", "춘삼이");
	request.setAttribute("list", new java.util.ArrayList());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>EL</title>
</head>
<body>
	person.getCar().getColor() =
	<%=person.getCar().getColor() %>
	<br> person.getCar().getColor() = ${person.getCar().getColor() }
	<br> person.getCar().getColor() = ${person.car.color }
	<br> name =
	<%=request.getAttribute("name") %>
	<br> name = ${requestScope.name}
	<br> name = ${name }
	<br> id =
	<%=request.getParameter("id") %>
	<br> id = ${param.id }
	<br> "1"+1 = ${"1" + 1 }
	<br> "1" += "1" = ${"1" += "1" }
	<br> "2" > 1 = ${"2">1 }
	<br> null = ${null }
	<br> null+1 = ${null + 1 }
	<br> null+null = ${null+null }
	<br> "" + null = ${""+null }
	<br> ""-1 = ${""-1 }
	<br> empty space = ${empty "" }
	<br> empty null = ${empty null }
	<br> empty list = ${empty list }
	<br> null == 0 = ${null == 0 }
	<br> null eq 0 = ${null eq 0 }
	<br> name == "춘식이" = ${name=="춘식이" }
	<br> name != "춘식이" = ${name != "춘식이" }
	<br> name eq "춘식이" = ${name eq "춘식이" }
	<br> name ne "춘식이" = ${name ne "춘식이" }
	<br> name.equals("춘식이") = ${name.equals("춘식이") }
	<br>
</body>
</html>