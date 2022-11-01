<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <style>
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
            top:30%;
            left:50%;
            transform: translate(-50%, -50%) ;
            <%-- border: 3px solid rgb(145, 210, 57);   
            border-radius: 10px;
            box-shadow: 2px 2px 2px 2px rgba(145, 210, 57, 0.6);
            background-color: white; --%>
        }
        input[type='text'], input[type='password'] {
            width: 300px;
            height: 40px;
            border:2px solid rgb(102, 192, 80);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
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
            margin : 20px 0 30px 0;
        }
        button:hover {
            background-color: rgba(21, 124, 40, 0.9);
            color: black;
            font-weight: bold;
        }
        #title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }
        #msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        #psize {
        	width: 300px;
        	border-top: 0.5px solid black;
        	margin-top: 10px;
        	text-align: center;
        	font-size: 10px
        }
    </style>
</head>
<body>
    <form id="container" action="<c:url value='/login1/login1'/>" method="post" onsubmit="return formCheck(this);">
       <h3 id="title"><a href="<c:url value='/'/>">Login</a></h3>
       <div id="msg">
       <c:if test="${not empty param.msg }">
       <i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg) }</i>
       </c:if>
    </div>
       <input type="text" name="id" value="${cookie.id.value}" placeholder="이메일 입력" autofocus>
       <input type="password" name="pwd" placeholder="비밀번호">
       <input type="hidden" name="toURL" value="${param.toURL }">
       <button>로그인</button>
       <div>
       		<label><input type="checkbox" name="rememberId" ${empty cookie.id.value ? "" : "checked"}>아이디기억</label>
       		<a href="">비밀번호 찾기</a>
       		<a href="<c:url value='/signup'/>">회원가입</a>
       </div>
       <div id="psize">
       		<p>ⓑBC.Tour All rights reserved.</p>
       </div>
    <script>
    	function formCheck(frm) {
    		let msg ='';
    		
    		if(frm.id.value.length==0) {
    			setMessage('id를 입력해주세요.', frm.id);
    			return false;
    		}
    		
    		if(frm.pwd.value.length==0) {
    			setMessage('password를 입력해주세요.', frm.pwd);
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