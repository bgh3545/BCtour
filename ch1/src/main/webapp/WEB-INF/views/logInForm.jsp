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
		*{box-sizing:border-box;
		}
		form{
		width:400px;
		height:600px;
		display:flex;
		flex-direction:column;
		align-items:center;
		position: absolute;
		top:50%;
		left:50%;
		transform:translate(-50%,-50%);
		border:1px solid rgb(89,117,196);
		border-radius:10px;
		}
		.title{
		font-size: 50px;
		margin: 60px 0 50px 0;
		font-weight: bolder;
		}
		.input-field{
		width:300px;
		height:40px;
		border:1px solid rgb(89,117,196);
		border-radius:5px;
		padding: 0 10px;
		margin-bottom:10px;
		}
		#log-in{
		background-color:rgb(89,117,196);
		color:white;
		width:300px;
		height:50px;
		font-size:17px;
		border:none;
		border-radius:5px;
		margin:20px 0 30px 0;
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
		}
	</style>
</head>
<body>
	<form action="<c:url value='/'/>" Method="Post" onsubmit="return formCheck(this);">
		<div class="title">LogIn</div>
		<div id="msg" class="msg"><c:if test="${not empty param.msg }">
		<i class="fa fa-exclamation-circle">${URLDecoder.decode(param.msg)}</i>
		</c:if>
		</div>
		<input class="input-field" type="text" name="id" placeholder="아이디 입력" autofocus>
		<input class="input-field" type="password" name="pwd" placeholder="비밀번호">
		<input id="log-in" type="submit" value="로그인"></input>
		<div class="last">
		<label><input type="checkbox" name="rememberId">아이디 기억</label>
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