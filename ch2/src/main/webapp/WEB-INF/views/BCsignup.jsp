<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/ch1/resources/img/상단로고.jpg">
    <title>회원가입</title>
    <style>
        * {
        	box-sizing: border-box;
            <%-- margin: 0;
            padding: 0; --%>
        }
        
        form {
            width:700px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:49%;
            left:50%;
            transform: translate(-50%, -50%) ;
        }

        input {
            width: 400px;
            font-size: 20px;
            margin-top: 30px;
            margin-left: 30px;
            padding: 10px;
            border: 1px solid black;
            <%-- border-radius: 10px; --%>

        }

        .box {
            width: 700px;
            height: 80px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .center {
            display: flex;
            justify-content: center;
            align-items: center;
        }
        #title {
            font-size : 3em;
            margin: 30px 0 30px 0;
            <!-- font-family: 'establishRoomNo703OTF'; -->
        }

        #submit {
            width: 400px;
            height: 45px;
            padding: 10px;
            margin-top: 30px;
            margin-left: 30px;
            background-color: rgb(145, 210, 57);
            border: 1px solid rgb(145, 210, 57);
            border-radius: 10px;
            font-size: 20px;
            font-weight: bold;
            color: white;
        }

        #submit:hover {
            background-color: rgb(72, 202, 96, 0.9);
            color: black;
            font-weight: bold;
            cursor: pointer;
        }
        
        header {
            padding: 20px;
            border-bottom: 1px solid #111;
            display: flex;
            justify-content: space-around;
        }
        
    </style>
</head>

<body>
	<header>
        <div><a href="<c:url value='/'/>"><img src="/ch1/resources/img/로고.jpg" width="200px" alt="로고"></a></div>
        <div><a href="<c:url value='/BClogin/BClogin'/>"><img src="/ch1/resources/img/로그인.png" width="110px" alt="로그인"></a></div>
    </header>
    <form:form modelAttribute="user">
        <div class="center"><h1 id="title">회 원 가 입</h1></div>
        <div class="box">
            <input type="text" name="id" placeholder="아이디" required="required">
        </div>
        <div class="box">
            <input type="password" name="pwd" id="pwd" placeholder="비밀번호" required="required">
        </div>
        <div class="box">
            <input type="password" name="pwd_check" id="pwd_check" placeholder="비밀번호 확인">
        </div>
        <div class="box">
            <input type="text" name="name" placeholder="이름">
        </div>
        <div class="box">
            <input type="email" name="email" placeholder="이메일">
        </div>
        <div class="box">
            <input type="tel" name="tel" placeholder="휴대번호">
        </div>
        <div class="box">
            <button id="submit" type="submit" onclick="signAlert()">가입하기</button> <!-- 기본타입 submit -->
        </div>
    </form:form>
    <script>
    function signAlert() {
		var pwd = document.getElementById('pwd').value;
		var pwd_check = document.getElementById('pwd_check').value;
		console.log("pwd = " + pwd);
		console.log("pwd_check = " + pwd_check);
		if(pwd != pwd_check) {
			alert('비밀번호 불일치!');
			return false;
		} else {
			alert('가입성공!');
			return true;
		}
	}
    
	/* 	function check(){
	   	      var p1 = document.getElementById('pwd').value;
	   	      var p2 = document.getElementById('pwd_check').value;
	   	      var form = document.getElementById('user');
	   	      if( p1 != p2 ) {
	   	        alert("비밀번호가 일치 하지 않습니다");
	   	        return false;
	   	      } else{
	   	        alert("비밀번호가 일치합니다");
	   	        form.submit();
	   	        return true;
	   	      }
	   		} */
    	
    	
    </script>
</body>

</html>