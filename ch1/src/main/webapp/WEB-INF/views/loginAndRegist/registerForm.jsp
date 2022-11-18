<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<style>
		*{
		box-sizing:border-box;
		background-color:rgb(243,255,213);
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
		border:3px solid rgb(145,210,57);
		border-radius:10px;
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
		label{
		width:300px;
		height:30px;
		margin-top:4px;
		}
		#input-submit{
		background-color:rgb(145,210,57);
		color:white;
		width:300px;
		height:50px;
		font-size:17px;
		border:none;
		border-radius:5px;
		margin:20px 0 30px 0;
		}
		.title{
		color:rgb(5, 90, 33);
		font-weight:bold;
		font-size: 40px;
		margin: 40px 0 30px 0;
		}
		.msg{
		height:30px;
		text-align:center;
		font-size:16px;
		color:red;
		margin-bottom:20px;
		}
		#input-submit:hover{
		background-color:rgb(122, 180, 39);
		}
		.sns-chk{
		margin-top:5px;
		}
	</style>
	<title>register</title>
</head>
<body>
	<form id="page" action="<c:url value ="/register/save"/>" method="post" onsubmit="return formCheck(this)">
		<div class="title">회원가입</div>
		<div id="msg" class="msg">${URLDecoder.decode(param.msg,"utf-8")} </div>
		<label for="">아이디</label>
		<input class="input-field" type="text" name="id" autocomplete="on" placeholder="8~12자리의 영대소문자와 숫자 조합" ${empty cookie.id.value? "autofocus":""} required value=${user.id}>
		<label for="">비밀번호</label>
		<input class="input-field" type="password" name="pwd" placeholder="8~12자리의 영대소문자와 숫자 조합" ${empty cookie.id.value? "":"autofocus"} required>
		<label for="">이름</label>
		<input class="input-field" type="text" name="name" autocomplete="off" placeholder="홍길동" required value=${user.name}>
		<label for="">이메일</label>
		<input class="input-field" type="email" name="email" autocomplete="off" placeholder="example@greenart.co.kr" required value=${user.email}>
		<label for="">생일</label>
		<input class="input-field" type="text" name="birth" autocomplete="off" placeholder="생년월일 8자리 입력 ex)20010213">
		<div class="sns-chk">
			<label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
			<label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
			<label><input type="checkbox" name="sns" value="instargram"/>인스타그램</label>
		</div>
		<input id="input-submit" type="submit" value="회원가입"></input>
	</form>
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