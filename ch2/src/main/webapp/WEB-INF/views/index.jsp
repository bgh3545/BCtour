<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비씨투어</title>
<style>
@font-face {
    font-family: 'establishRoomNo703OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2112@1.0/establishRoomNo703OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

* {
	box-sizing: border-box;
	font-family: 'establishRoomNo703OTF';
	font-weight: lighter;
}

.main {
	width: 100%;
	height: 934px;
	margin: 0px auto;
	position: absolute;
}

input {
	width: 600px;
	height: 55px;
	margin-left: 85px;
	margin-right: 85px;
	border-radius: 8px;
	border-color: rgb(145, 210, 57);
}

.icon {
	width: 50px;
	height: 50px;
}

.search {
	width: 1350px;
	border-bottom: 3px solid rgba(0, 0, 0, 0.3);
	height: 106px;
	margin: 0px auto;
	display: flex;
}

.nav {
	width: 1350px;
	height: 780px;
	margin: 0px auto;
	display: flex;
	justify-content: flex-end
}

.icon {
	width: 200px;
	height: 150px;
	border-right: 5px solid black;
	margin-right: 0px;
}

.logo {
	color: rgb(145, 210, 57);
	margin: 0px auto;
	font-size: 40px;
}

.imgContainer {
	width: 765px;
	height: 175px;
	border: 5px solid black;
	text-align: center;
	margin-top: 20px;
	margin-right: 5px;
}

.column {
	margin-right: -50px;
	width: 410px;
	height: 650px;
	text-align: center;
}

.column>h2:hover {
	color: rgb(145, 210, 57);
}

.column>h2 {
	color: gray;
	font-size: 25px;
	margin-top: 40px;
}

ul {
	list-style: none;
	display: inline;
}

.header {
	width: 100%;
	margin-bottom: 30px;
	font-size: 5px;
	display: flex;
	margin-left: 80vw;
}

.header>ul>li {
	color: rgba(0, 0, 0, 0.5);
	padding-right: 20px;
}

li {
	display: inline;
}

.mypage {
	margin-left: 10px;
}

.mypage>li {
	padding-right: 10px;
}

.imgContainer>img {
	width: 300px;
	height: 751px;
}

.column2 {
	width: 900px;
	height: 650px;
	text-align: center;
}
</style>
</head>
<body>
	<c:set var="loginOutlink" value="${sessionScope.id == null? '/login1/login1' : '/login1/logout' }" />
	<c:set var="loginOut" value="${sessionScope.id==null? '로그인' : '로그아웃' }" />
	<div class="main">
		<div class="header">
			<ul>
				<li><a href="<c:url value='/board/list'/>">게시판</a></li>
				<li><a href="<c:url value='${loginOutlink }'/>">${loginOut }</a></li>
				<li><a href="<c:url value='/signup'/>">회원가입</a></li>
			</ul>
		</div>
		<div class="search" style="text-align: center;">
			<h1 class="logo">
				<a href="<c:url value='/'/>">비씨투어</a>
			</h1>
			<input type="text" id="id" name="id" value="" placeholder="검색">
			<ul class="mypage">
				<li>마이 페이지</li>
				<li>여행일지</li>
			</ul>
		</div>
		<div class="nav">
			<div class="column">
				<h2>수도권</h2>
				<h2>충청북도</h2>
				<h2>충청남도</h2>
				<h2>전라남도</h2>
				<h2>전라북도</h2>
				<h2>경상남도</h2>
				<h2>경상북도</h2>
				<h2>제주도</h2>
			</div>
			<div class="nav">
				<div class="column2">
					<div class="imgContainer"></div>
					<div class="imgContainer"></div>
					<div class="imgContainer"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>