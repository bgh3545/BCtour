<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${path }/resources/img/상단로고.jpg" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="${path }/resources/css/bcsignup.css">
    <title>회원가입</title>
</head>

<body>
	<header>
        <div><a href="<c:url value='/'/>"><img src="${path }/resources/img/로고.jpg" width="200px" alt="로고"></a></div>
        <div><a href="<c:url value='/BClogin/BClogin'/>"><img src="${path }/resources/img/로그인.png" width="110px" alt="로그인"></a></div>
    </header>
    <form:form modelAttribute="user" onsubmit="return signAlert();">
        <div class="center"><p id="title">회 원 가 입</p></div>
        <div class="box">
            <input type="text" name="id" id="id" placeholder="아이디" required="required">
            <button type="button" id="id_check">중복체크</button>
        </div>
        
        <div id="id_msg"></div>
        
        <div class="box">
            <input type="password" name="pwd" id="pwd" onkeyup="Password()" placeholder="비밀번호" required="required">
        </div>
        
        <div class="box">
            <input type="password" name="pwd_check" id="pwd_check" onkeyup="Password()" placeholder="비밀번호 확인">
        </div>
        
        <div id="pwd_msg"></div>
        
        <div class="box">
            <input type="text" name="name" id="name" placeholder="이름">
        </div>
        <div class="box">
            <input type="email" name="email" id="email" placeholder="이메일" required="required">
        </div>
        <div class="box">
            <input type="tel" name="tel" id="tel" placeholder="휴대번호">
        </div>
        <div class="checkbox">
        	<input type="checkbox" id="identify" name="identify">
            <label for="identify">(필수) 개인정보 수집/이용에 동의합니다.</label>
            <input type="checkbox" id="privacy" name="privacy">
            <label for="privacy">(필수) 고유식별 수집/이용에 동의합니다.</label>
        </div>
        <div class="box">
            <button id="submit" type="submit">가입하기</button> <!-- 기본타입 submit -->
        </div>
    </form:form>
    <script src="http://code.jquery.com/jquery-latest.js" charset="UTF-8"></script>
    <script>
    /* ------------ 아이디 체크 ---------------- */
    
    	let id;
		$("#id_check").click(function(){
			let id =$("input[name=id]").val();
	        $.ajax({
	            type:'GET',       // 요청 메서드
	            url: '/bctour/checkId?id='+id ,  // 요청 URI
	            success : function(result){
				if( id.length < 5 || id.length == 0) {
					$("#id_msg").css("display" , "block");
					$("#id_msg").text("아이디를 5자 이상 입력해주세요.");
					$("#id_msg").css("color", "rgb(20, 180, 20)");
				} else {
					if(result=="true"){
						$("#id_msg").css("display" , "block");
						$("#id_msg").text("아이디가 중복되었습니다.");
						$("#id_msg").css("color", "red");
					}
					else{
						if(confirm("사용 가능한 아이디 입니다.\n사용하시겠습니까?")) {
						id = $("#id").val();
						$("#id_msg").css("display" , "block");
						$("#id_msg").text("사용 가능한 아이디입니다.");
						$("#id_msg").css("color", "blue");
						$("#id_check").attr("disabled","disabled");
						$("#id").attr("readOnly","readOnly");
						$("#id").css("background-color", "rgb(238,238,238)");
						$("#id").css("border", "1px solid rgba(118, 118, 118, 0.3)");
						$("#id").css("color", "rgba(16, 16, 16, 0.3)");
						
						console.log(id)
						}
						}
					}
	            },
	            error: function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
	        }); // $.ajax()
		});
		
        // 가입하기 
        function signAlert() {
        	var id_check = document.getElementById("id_check").disabled;
        	
        	var identity = document.getElementById("identify").checked;
        	var privacy = document.getElementById("privacy").checked;
        	
        	var pwd = document.getElementById("pwd").value;
        	var pwd_check = document.getElementById("pwd_check").value;
        	
        	var email = document.getElementById("email").value;
        	var valid = new RegExp('^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$');
     			
        	if(id_check != true) {
        		alert("중복체크를 눌러주세요.");
        		return false;
        	} else if(pwd != pwd_check) {
        		alert("비밀번호가 일치하지 않습니다.");
        		return false;
        	} else if (valid.test(email)==false) {
         		alert("이메일 형식이 올바르지 않습니다.\n다시 입력해주세요.");
         		return false;
        	} else if ( identity != true || privacy != true ) {
        		alert("개인정보 및 고유식별 수집/이용에 체크해주세요.");
        		return false;
        	} else {
        		alert("성공적으로 가입되었습니다.");
        		return true;
        	}
        }
    
 // 실시간 비밀번호 검사
    function Password() {

    var pwd = document.getElementById("pwd").value;
    var pwd_check = document.getElementById("pwd_check").value;
    var pwd_msg = "";
    var color = "";

// 	   입력값이 있을경우에만 실행
    if(pwd.length || pwd_check.length ) {
    	
        // 최대 입력 글자수를 제한한다.
        if(pwd.length < 5 || pwd.length > 16) {
            pwd_msg = "최소 5자 이상, 최대 16자 이하";
            color = "#A23E48";
        } else {
        	pwd_msg = "사용 가능한 비밀번호 입니다.";
        	color = "#A23E48";
        }
        // 비밀번호 확인
    	if ( pwd == pwd_check && pwd_check.length != 0) {
    		pwd_msg = "비밀번호가 일치합니다.";
    		color = "blue";
    	}
       	if ( pwd != pwd_check && pwd_check.length != 0) {
       	pwd_msg = "비밀번호가 일치하지 않습니다.";
       		color = "#A23E48";
    	}

    } else {
        pwd_msg = "비밀번호를 입력해 주세요";
        color = "#000000";
    }

    document.getElementById("pwd_msg").innerHTML = pwd_msg;
    document.getElementById("pwd_msg").style.color = color;
    document.getElementById("pwd_msg").style.display = "block";
}
    
    </script>
</body>

</html>