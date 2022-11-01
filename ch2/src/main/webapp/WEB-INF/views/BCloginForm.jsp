<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/ch1/resources/img/상단로고.jpg">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <style>
    	@font-face {
   			 font-family: 'establishRoomNo703OTF';
    		 src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2112@1.0/establishRoomNo703OTF.woff') format('woff');
   			 font-weight: normal;
   			 font-style: normal;
		}
    
    
       * { box-sizing:border-box; }
       a {
            text-decoration: none;
            color: black;
            margin-left: 10px;
        }
        form {
            width:400px;
            height:500px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:40%;
            left:50%;
            transform: translate(-50%, -50%) ;
        }
        input[type='text'], input[type='password'] {
            width: 300px;
            height: 40px;
            padding: 0 10px;
            margin-bottom: 10px;
            font-size: 15px;
        }
        button {
            background-color: rgb(152, 218, 59);
            color : white;
            width:300px;
            height:50px;
            font-size: 20px;
            font-weight: bold;
            border : none;
            border-radius: 10px;
            margin : 10px 0 10px 0;
        }
        button:hover {
            background-color: rgba(21, 124, 40, 0.9);
            color: black;
            font-weight: bold;
            cursor: pointer;
        }
        #title {
            font-size : 3em;
            margin: 30px 0 30px 0;
            <!-- font-family: 'establishRoomNo703OTF'; -->
        }
        #msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        header {
            padding: 20px;
            border-bottom: 1px solid #111;
            display: flex;
            justify-content: space-around;
        }
        #psize {
        	width: 300px;
        	border-top: 0.5px solid black;
        	margin-top: 10px;
            text-align:center;
        	font-size: 12px
        }
        .margintop20 {
        	margin-top: 20px;
        }
        .topbox {
        	width: 140px;
        	height: 56.02px;
        	border: 0;
        }
    </style>
</head>
<body>
	<header>
        <div><a href="<c:url value='/'/>"><img src="/ch1/resources/img/로고.jpg" width="200px" alt="로고"></a></div>
        <div class="topbox"></div>
    </header>
    <form action="<c:url value='/BClogin/BClogin'/>" method="post" onsubmit="return formCheck(this);">
       <h1 id="title">로그인</h1>
       <div id="msg">
       <c:if test="${not empty param.msg }">
       <i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg) }</i>
       </c:if>
    	</div>
       <input type="text" name="id" value="${cookie.id.value}" placeholder="아이디" autofocus>
       <input type="password" name="pwd" placeholder="비밀번호">
       <input type="hidden" name="toURL" value="${param.toURL }">
       <button type="submit">로그인</button>
       <button type="button" onclick="location.href='<c:url value="/BCsignup"/>'">회원가입</button>
       
       <div class="margintop20">
       		<label><input type="checkbox" name="rememberId" ${empty cookie.id.value ? "" : "checked"}>아이디기억</label>
       		<a href="<c:url value='/BCFind/BCFindingId'/>">아이디/비밀번호 찾기</a>
       </div>
       <div id="psize">
       		<p>ⓑBC.Tour All rights reserved.</p>
       </div>
    <script>
    	function formCheck(frm) {
    		let msg ='';
    		
    		if(frm.id.value.length==0) {
    			setMessage('id를 입력해주세요.', frm.id);
    			alert("id를 입력해주세요");
    			return false;
    		}
    		
    		if(frm.pwd.value.length==0) {
    			setMessage('password를 입력해주세요.', frm.pwd);
    			alert("password를 입력해주세요");
    			return false;
    		}
    		return true;
    	}
    	
    	function setMessage(msg, element) {
    		document.getElementById("msg").innerHTML = `${'${msg}'}`;
    		
    		if(element) {
    			element.select();
    		}
    	}
    </script>
    </form>
</body>
</html>