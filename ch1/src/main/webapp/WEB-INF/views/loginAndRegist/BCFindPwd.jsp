<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="${path }/resources/img/상단로고.jpg" />
<title>BC.Tour 비밀번호찾기</title>
<style>
* {
	box-sizing: border-box;
}

header {
	padding: 20px;
	display: flex;
	justify-content: space-around;
}

#vertical {
	margin: 0px auto;
	width: 100%;
	display: flex;
	justify-content: center;
	align-content: center;
	flex-direction: column;
	text-align: center;
}

#top_p {
	height: 95px;
	margin: -18px;
	font-size: 3em;
	font-weight: bold;
	border-bottom: 2px solid rgba(0, 0, 0, 0.3);
	/* border: 1px solid black; */
}

#main_p {
	margin: 30px;
	font-size: 1.5em;
	font-weight: bold;
	line-height: 2em;
}

ul li {
	display: inline-block;
	list-style: none;
}

#container {
	display: flex;
	justify-content: space-around;
	/* border: 1px solid green; */
}

#container>ul>li {
	width: 250px;
	height: 50px;
	text-align: center;
	line-height: 40px;
	font-size: 2em;
	font-weight: bold;
}

a {
	text-decoration: none;
	color: black;
}
</style>
</head>

<body>
	<header>
		<div>
			<a href="<c:url value='/'/>"><img src="${path }/resources/img/로고.jpg"
				width="200px" alt="로고"></a>
		</div>
		<div>
			<a href="<c:url value='/logIn/logIn'/>"><img
				src="${path }/resources/img/로그인.png" width="110px" alt="로그인"></a>
		</div>
	</header>
	<div id="vertical">
		<div>
			<div id="top_p">
				<p>아이디/비밀번호 찾기</p>
			</div>
		</div>
		<div id="main_p">
			<p>고객님의 비밀번호는</p>
			<p>${userfindpwd.pwd}입니다</p>
		</div>
	</div>
</body>

</html>