<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
span {
	font-weight: bold;
}

</style>
</head>
<body>
	<button type="button" onclick="location.href='http://localhost:8080/ch1/del'">����</button><br>
	<button type="button" onclick="location.href='http://localhost:8080/ch1/cmt'">�Է�</button><br>
	<h1>���� ��ϵ� �޽��� �� : ${cnt }</h1>
	<div>
	<c:forEach var="i" items="${cmts }">
		<div>
			<span>�̸� : ${i.name }</span><br>
			���� : ${i.comment }
		</div>
	</c:forEach>	
	</div>
</body>
</html>