<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>logInForm</title>
<style type="text/css">
		*{
		box-sizing:border-box;
		background-color:rgb(243,255,213);
		}
		form{
		width:400px;
		height:550px;
		display:flex;
		flex-direction:column;
		align-items:center;
		position: absolute;
		top:50%;
		left:50%;
		transform:translate(-50%,-50%);
		border:3px solid rgb(145,210,57);
		border-radius:10px;
		
		}
		.title{
		color: rgb(145,210,57);
		font-size: 50px;
		margin: 60px 0 50px 0;
		font-weight: bolder;
		}
		.input-field{
		width:300px;
		height:40px;
		border:1px solid rgb(145,210,57);
		border-radius:5px;
		padding: 0 10px;
		margin-bottom:10px;
		background-color:white;
		}
		#log-in{
		background-color:rgb(145,210,57);
		color:white;
		width:300px;
		height:50px;
		font-size:20px;
		border:none;
		border-radius:5px;
		margin:20px 0 30px 0;
		font-weight:bolder;
		}
		.last{
			float: left;
		}
		a[href="#"]{
			text-decoration-line:none;
			color:black;
		}
		#msg{
			padding:50px;
			color:red;
			font-size:15px;
			padding-top:20px;
		}
		#log-in:hover{
		background-color:rgb(122, 180, 39);
		}
	</style>
</head>
<body>
	<form action="<c:url value='/logIn1/logIn1'/>" Method="Post" onsubmit="return formCheck(this);">
		<div class="title">LogIn</div>
		<div id="msg" class="msg">
		<c:if test="${not empty param.msg }">
		<i class="fa-solid fa-triangle-exclamation">${URLDecoder.decode(param.msg)}</i>
		</c:if>
		</div>
		<input class="input-field" type="text" name="id" placeholder="아이디 입력" ${empty cookie.id.value? "autofocus":""} value="${cookie.id.value}">
		<input class="input-field" type="password" name="pwd" ${empty cookie.id.value? "":"autofocus"} placeholder="비밀번호">
		<input class="input-field" type="hidden" name="toURL" value="${param.toURL}">
		<input id="log-in" type="submit" value="로그인"></input>
		<div class="last">
		<label><input type="checkbox" name="rememberId" ${empty cookie.id.value? "":"checked"}>아이디 기억</label>
		<a href="#">비밀번호 찾기</a>
		<a href="#" onclick="location.href='http://localhost/ch1/register/add'">회원가입</a>
		</div>
	<script src="https://kit.fontawesome.com/9eda133edb.js" crossorigin="anonymous"></script>
	<script>
		function formCheck(frm){
			let msg='';
			
			if(frm.id.value.length==0){
				setMessage('아이디를 입력해주세요',frm.id);
				return false;
			}
			
			if(frm.pwd.value.length==0){
				setMessage('비밀번호를 입력해주세요',frm.id);
				return false;
			}
			return true	
		}
		function setMessage(msg,element){
			document.getElementById("msg").innerHTML = `<i class="fa-solid fa-triangle-exclamation">${'${msg}'}</i>`;
			
			if(element){
				element.select();
			}
		}
	</script>
	</form>
</body>
</html>