<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BC.Tour - ��������</title>
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
	border-bottom: 1px solid black;
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
			<a href="<c:url value='/'/>"><img src="/ch1/resources/img/�ΰ�.jpg"
				width="200px" alt="�ΰ�"></a>
		</div>
		<div>
			<a href="<c:url value='/BClogin/BClogin'/>"><img
				src="/ch1/resources/img/�α���.png" width="110px" alt="�α���"></a>
		</div>
	</header>
	<div id="vertical">
		<div>
			<div id="top_p">
				<p>���̵�/��й�ȣ ã��</p>
			</div>
		</div>
		<div id="main_p">
			<p>������ ��й�ȣ��</p>
			<p>${userfindpwd.pwd}�Դϴ�</p>
		</div>
	</div>
</body>

</html>