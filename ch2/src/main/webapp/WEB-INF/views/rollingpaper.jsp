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
* {
	margin: 0;
	box-sizing: border-box;
}

h1, h3, p {
	padding: 20px;
}

p {
	border-bottom: 1px solid black;
}

ul, li {
	list-style: none;
}

textarea, button {
	margin-left: 20px;
}

#msg {
	margin-left: 20px;
}
</style>
</head>
<body>
	<h1>�Ѹ�������</h1>
	<p>������ ���� ���� ���غ�����</p>
	<form method="post">
	<h3>����</h3>
	<ul>
		<li><label for="name">�̸�</label> <input type="text" id="name"
			name="name" placeholder="������� �̸�"></li>
	</ul>
	<h3>���ϰ� ���� ��</h3>
	<label for="textarea">
	<textarea id="textarea" name="comment" cols="60" rows="5"
		placeholder="���� ��"></textarea>
	</label>
	<br>
	<button type="submit">�����ϱ�</button>
	<button type="reset" onclick="location.href='http://localhost:8080/ch1/cmt'">�ٽþ���</button>
	<button type="button" onclick="location.href='http://localhost:8080/ch1/info'">��������</button><br>
	</form>
	<div id="msg">${msg }</div>
</body>
</html>