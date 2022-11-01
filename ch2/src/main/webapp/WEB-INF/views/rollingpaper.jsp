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
	<h1>롤링페이퍼</h1>
	<p>전하지 못한 말을 전해보세요</p>
	<form method="post">
	<h3>정보</h3>
	<ul>
		<li><label for="name">이름</label> <input type="text" id="name"
			name="name" placeholder="공백없이 이름"></li>
	</ul>
	<h3>전하고 싶은 말</h3>
	<label for="textarea">
	<textarea id="textarea" name="comment" cols="60" rows="5"
		placeholder="전할 말"></textarea>
	</label>
	<br>
	<button type="submit">접수하기</button>
	<button type="reset" onclick="location.href='http://localhost:8080/ch1/cmt'">다시쓰기</button>
	<button type="button" onclick="location.href='http://localhost:8080/ch1/info'">보러가기</button><br>
	</form>
	<div id="msg">${msg }</div>
</body>
</html>