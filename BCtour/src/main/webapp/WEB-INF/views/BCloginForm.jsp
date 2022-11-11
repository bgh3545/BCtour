<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${path }/resources/img/상단로고.jpg">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="${path }/resources/css/bcloginform.css">
</head>

<body>
	<header>
        <div><a href="<c:url value='/'/>"><img src="${path }/resources/img/로고.jpg" width="200px" alt="로고"></a></div>
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