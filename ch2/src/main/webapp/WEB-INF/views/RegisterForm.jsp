<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.net.URLDecoder"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
#container {
	margin: auto;
	width: 400px;
	height: 500px;
}

h1 {
	text-align: center;
}

ul, li {
	list-style: none;
}

input {
	margin-bottom: 15px;
}

input[type="date"] {
	width: 278px;
}

#submit {
	width: 300px;
	height: 50px;
	border: 1px solid blue;
	background-color: blue;
	color: white;
	border-radius: 5px;
	font-size: 1.2em;
	margin-left: 40px;
}

.msg {
	height: 30px;
	text-align: center;
	font-size: 16px;
	color: red;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<!-- <form id="container" action="<c:url value="/register/save"/>"
		method="post" onsubmit="return formCheck(this)">  -->
	<form:form id="container" modelAttribute="user" onsubmit="return formCheck(this)">
		
		<fieldset>
			<h1>Register</h1>
	        <div id="msg" class="msg"><form:errors path="id"/></div>
			<ul>
				<li><label for="user_id">아이디</label><br> <input
					type="text" id="user_id" name="id" size="35" autofocus required="required"
					placeholder="아이디를 입력해주세요."></li>
				<li><label for="user_pwd">비밀번호</label><br> <input
					type="password" id="user_pwd" name="pwd" size="35" required="required" 
					placeholder="비밀번호를 입력해주세요."></li>
				<li><label for="user_name">이름</label><br> <input
					type="text" id="user_name" name="name" size="35"></li>
				<li><label for="user_email">이메일</label><br> <input
					type="email" id="user_email" name="email" size="35"></li>
				<li><label for="user_birth">생일</label><br>
					<input type="text" id="user_birth" name="birth" placeholder="2022/12/15"></li>
				<li><label for="face"> <input type="checkbox" id="face"
						name="sns" value="facebook" checked>페이스북
				</label> <label for="kaka"> <input type="checkbox" id="kaka"
						name="sns" value="kakaotalk">카카오톡
				</label> <label for="insta"> <input type="checkbox" id="insta"
						name="sns" value="instagram">인스타그램
				</label></li>
			</ul>
			<input id="submit" type="submit" value="회원가입">
		</fieldset>
	</form:form>
	<script src="https://kit.fontawesome.com/52519a6971.js"
		crossorigin="anonymous"></script>
	<script>
		function formCheck(frm) {
			var msg = '';

			if (frm.id.value.length < 3) {
				setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
				return false;
			} <%-- else if(frm.pwd.value.length < 3) {
				setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);
				return false;
			} else  { --%>
				return true;
			}
			
		function setMessage(msg, element) {
			document.getElementById("msg").innerHTML = `<i class="fa-solid fa-circle-exclamation">${'${msg}'}</i>`;

			if (element) {
				element.select();
			}
		}
	</script>
</body>
</html>