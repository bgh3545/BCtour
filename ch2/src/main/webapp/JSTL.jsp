<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- �ھ��±� ��� -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- ������ ��� -->
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>JSTL</title>
</head>
<body>
	<c:set var="to" value="10" />
	<c:set var="arr" value="10,20,30,40,50,60,70" />
	<c:forEach var="i" begin="1" end="${to }">
	${i*i }
</c:forEach>
	<br>
	<c:if test="${not empty arr}">
		<c:forEach var="elem" items="${arr }" varStatus="status">
		${status.count }. arr[${status.index }] = ${elem*2 }<br>
		</c:forEach>
	</c:if>
	<c:if test="${param.msg != null}">
	msg = <b>${param.msg }</b>
		<br>
	msg = <c:out value="<b>${param.msg }</b>" />
	</c:if>
	<br>
	<c:if test="${param.msg == null }">�޽����� �����ϴ�.<br>
	</c:if>
	<c:set var="age" value="${param.age }" />
	<c:choose>
		<c:when test="${age >= 19 }">�����Դϴ�.</c:when>
		<c:when test="${0 <= age && age < 19 }">������ �ƴմϴ�.</c:when>
		<c:otherwise>���� ��ȿ���� �ʽ��ϴ�.</c:otherwise>
	</c:choose>
	<br>
	<c:set var="now" value="<%=new java.util.Date() %>" />
	Server time is
	<fmt:formatDate value="${now }" type="date" pattern="yyyy��MM��dd��" />
</body>
</html>