<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BC.Tour - 계정관리</title>
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

        #vertical>div>p {
            margin: 30px 0;
            padding-bottom: 30px;
            font-size: 3em;
            font-weight: bold;
            border-bottom: 1px solid black;
            /* border: 1px solid black; */
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
        }

        #container>ul>li:hover {
            border-bottom: 1px solid black;
            font-weight: bold;
        }

        a {
            text-decoration: none;
            color: black;
        }
        .h2_box {
        	margin: 0 auto;
    		width: 400px;
    		text-align: left;
        }

        #form_container_id {
            margin: 50px auto;
            width: 500px;
            height: 280px;
            /* border: 1px solid black; */
        }

        .form_container_id {
            margin: 25px;
            display: flex;
            justify-content: center;
            align-content: center;
        }

        .form_container_id>input {
            width: 400px;
            padding: 10px;
            font-size: 1.5em;
        }

        .form_container_id>div>input[type="tel"] {
            width: 230px;
            font-size: 1.5em;
            padding: 10px;
            margin-right: 20px;
        }

        .form_container_id>div>button {
            width: 150px;
            height: 52px;
            font-weight: bold;
            font-size: 1.2em;
            padding: 10px;
        }

        .form_container_id>input[type="submit"] {
            font-weight: bold;
        }
        
        .form_container_id>input[type="submit"]:active {
			margin-left: 1.5px;
			margin-top: 1.5px;
			box-shadow: -1.5px -1.5px -1.5px -1.5px;
			border: 2px solid #767676;
			background-color: #e5e5e5;
		}
    </style>
</head>

<body>
    <header>
        <div><a href="<c:url value='/'/>"><img src="${path }/resources/img/로고.jpg" width="200px" alt="로고"></a></div>
        <div><a href="<c:url value='/BClogin/BClogin'/>"><img src="${path }/resources/img/로그인.png" width="110px" alt="로그인"></a></div>
    </header>
    <div id="vertical">
        <div>
            <p>아이디/비밀번호 찾기</p>
            <div id="container">
                <ul>
                    <li><a href="<c:url value='/BCFind/BCFindingId'/>">아이디</a></li>
                    <li><a href="<c:url value='/BCFind/BCFindingPwd'/>">비밀번호</a></li>
                </ul>
            </div>
            <div class="h2_box"><h2>등록한 이메일로 아이디 찾기</h2></div>
        </div>
    </div>
    <!-- 아이디 찾기 -->
    <form action="<c:url value='/BCFind/BCFindingId'/>" method="post">
        <div id="form_container_id">
            <!-- <p>이름으로 아이디 찾기</p> -->
            <div class="form_container_id">
                <input type="text" name="name" placeholder="이름" required="required">
            </div>
            <div class="form_container_id">
                <div>
                    <input type="email" name="email" placeholder="이메일" required="required">
                </div>
                <div>
                    <button type="button" onclick="alert('인증번호가 발송되었습니다.')">인증번호 발송</button>
                </div>
            </div>
            <div class="form_container_id">
                <input type="submit" value="확인">
            </div>
        </div>
    </form>
</body>

</html>