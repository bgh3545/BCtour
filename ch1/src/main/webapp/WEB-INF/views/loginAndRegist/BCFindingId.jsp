<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${path }/resources/img/상단로고.jpg">
    <link rel="stylesheet" href="${path }/resources/CSS/bcfindingid.css?a">
    <title>BC.Tour 아이디찾기</title>
</head>

<body>
    <header>
        <div><a href="<c:url value='/'/>"><img src="${path }/resources/img/로고.jpg" width="200px" alt="로고"></a></div>
        <div><a href="<c:url value='/logIn/logIn'/>"><img src="${path }/resources/img/로그인.png" width="110px" alt="로그인"></a></div>
    </header>
    <div id="vertical">
        <div>
            <p>아이디/비밀번호 찾기</p>
            <div id="container">
                <ul>
                    <li id="findid"><a href="<c:url value='/BCFind/BCFindingId'/>">아이디</a></li>
                    <li><a href="<c:url value='/BCFind/BCFindingPwd'/>">비밀번호</a></li>
                </ul>
            </div>
            <div class="h2_box"><h2>등록한 이메일로 아이디 찾기</h2></div>
        </div>
    </div>
    <!-- 아이디 찾기 -->
    <form action="<c:url value='/BCFind/BCFindingId'/>" id="form" method="post" onsubmit="return ConfirmBtn();">
        <div id="form_container">
            <div class="form_container_id">
                <input type="text" name="name" value="${param.name2 }" placeholder="이름" required="required">
            </div>
        <div class="form_container_id">
            <div>
            	<input type="email" name="email" id="email" value="${param.email2 }" placeholder="이메일" required="required">
            </div>
            <div>
            <c:choose>
            	<c:when test="${param.email2 != null }">
            		<button type="button" id="btn" disabled>인증확인</button>
            	</c:when>
             	<c:otherwise>
             		<button type="button" id="btn">인증확인</button>                		
             	</c:otherwise>
            </c:choose>
            </div>
         </div>
            <div class="form_container_id">
                <input type="submit" value="확인">
            </div>
        </div>
    </form>
    <script>
    function ConfirmBtn() {
    	let btn = document.getElementById("btn").disabled;
    	if (btn == true ) {
    		return true;
    	} else {
    		alert("이메일 인증확인을 해주세요.");
    		return false;
    	}
    }
    
    /* 인증확인 클릭 시 주소에 맞는 메일페이지로 이동 */
    document.getElementById('btn').addEventListener('click',e=>{
    	
    	let email = document.getElementById("email").value;
    	let valid = new RegExp('^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$');
		if (valid.test(email)==false) {
 			alert("이메일 형식이 올바르지 않습니다.\n다시 입력해주세요.");
 			return false;
		} else {
			var mailPage = confirm("입력하신 이메일로 인증확인하시겠습니까?\n확인을 누르시면 메일페이지로 이동됩니다.");
			if( mailPage ) {
				var form = document.getElementById('form');
				form.action = "<c:url value='/BCFind/emailGetId'/>";
				form.method = "POST";
				form.submit();
				
				setTimeout(() => {
		    		let emailAddr = document.getElementById('email').value;
		    		if ( emailAddr.match("naver") ) {
		    			window.location.href = "http://mail.naver.com";
		    		}
		    		if ( emailAddr.match("gmail") ) {
		    			window.location.href = "https://mail.google.com";
		    		}
				}, 1000);
				
			} else {
				alert("발송이 취소되었습니다.");
			}
		}
	});
    </script>
</body>

</html>