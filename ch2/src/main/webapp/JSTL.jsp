<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 코어태그 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 포매팅 사용 -->
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
	<c:if test="${param.msg == null }">메시지가 없습니다.<br>
	</c:if>
	<c:set var="age" value="${param.age }" />
	<c:choose>
		<c:when test="${age >= 19 }">성인입니다.</c:when>
		<c:when test="${0 <= age && age < 19 }">성인이 아닙니다.</c:when>
		<c:otherwise>값이 유효하지 않습니다.</c:otherwise>
	</c:choose>
	<br>
	<c:set var="now" value="<%=new java.util.Date() %>" />
	Server time is
	<fmt:formatDate value="${now }" type="date" pattern="yyyy년MM월dd일" />
</body>
</html>