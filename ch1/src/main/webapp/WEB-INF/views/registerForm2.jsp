<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLDecoder" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<style>
		*{
		box-sizing:border-box;
		}
		form{
		width:600px;
		height:800px;
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
		.input-field{
		width:400px;
		height:40px;
		border:1px solid rgb(145,210,57);
		border-radius:5px;
		padding: 0 10px;
		margin-bottom:10px;
		}
		label{
		width:400px;
		height:30px;
		margin-top:4px;
		}
		#input-submit{
		background-color:rgb(89,117,196);
		color:white;
		width:300px;
		height:50px;
		font-size:17px;
		border:none;
		border-radius:5px;
		margin:20px 0 30px 0;
		}
		.title{
		font-size: 50px;
		margin: 40px 0 5px 0;
		}
		.msg{
		border: 1px solid black;
		width:400px;
		height:60px;
		text-align:center;
		font-size:10%;
		color:red;
		margin-bottom:10px;
		padding:0;
		}
		span{
		width:400px;
		height:60px;
		}
		.sns-chk{
		margin-top:5px;
		}
		form:errors{
		width:400px;
		}
	</style>
	<title>register</title>
</head>
<body>
	<!--form id="page" action="<c:url value ="/register/save2"/>" method="post" onsubmit="return formCheck(this)"-->
	<form:form modelAttribute="user">
		<div class="title">Register</div>
		<div id="msg" class="msg"><form:errors path="id"/><br><form:errors path="pwd"/></div>
		<label for="">아이디</label>
		<input class="input-field" type="text" name="id" placeholder="8~12자리의 영대소문자와 숫자 조합" autofocus >
		<label for="">비밀번호</label>
		<input class="input-field" type="password" name="pwd" placeholder="8~12자리의 영대소문자와 숫자 조합" >
		<label for="">이름</label>
		<input class="input-field" type="text" name="name" placeholder="홍길동" required>
		<label for="">이메일</label>
		<input class="input-field" type="email" name="email" placeholder="example@greenart.co.kr" required>
		<label for="">생일</label>
		<input class="input-field" type="text" name="birth" placeholder="생년월일 8자리 입력" required>
		<label for="">취미</label>
		<input class="input-field" type="text" name="hobby" placeholder="피아노#베이킹">
		<div class="sns-chk">
			<label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
			<label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
			<label><input type="checkbox" name="sns" value="instargram"/>인스타그램</label>
		</div>
		<input id="input-submit" type="submit" value="회원가입"></input>
	</form:form>
	<script src="https://kit.fontawesome.com/9eda133edb.js" crossorigin="anonymous"></script>
	<script>
		function formCheck(frm){
			var msg='';
			
			if(frm.id.value.length<3){
				setMessage('아이디의 길이는 3이상이어야 합니다',frm.id);
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
</body>
</html>