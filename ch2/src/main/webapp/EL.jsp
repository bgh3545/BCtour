<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.greenart.ch12.*"%>
<%
	Person person = new Person();
	request.setAttribute("person", person);
	request.setAttribute("name", "�����");
	pageContext.setAttribute("name", "�����");
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
	<br> name == "�����" = ${name=="�����" }
	<br> name != "�����" = ${name != "�����" }
	<br> name eq "�����" = ${name eq "�����" }
	<br> name ne "�����" = ${name ne "�����" }
	<br> name.equals("�����") = ${name.equals("�����") }
	<br>
</body>
</html>