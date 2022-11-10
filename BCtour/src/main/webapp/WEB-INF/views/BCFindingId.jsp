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
    <title>BC.Tour - 계정관리</title>
    <style>
        * {
            box-sizing: border-box;
        }

        header {
            padding: 20px;
            display: flex;
            justify-content: space-around;
        }

        #vertical {
            margin: 0px auto;
            width: 100%;
            display: flex;
            justify-content: center;
            align-content: center;
            flex-direction: column;
            text-align: center;
        }

        #vertical>div>p {
            margin: 30px 0;
            padding-bottom: 30px;
            font-size: 3em;
            font-weight: bold;
            border-bottom: 1px solid black;
            /* border: 1px solid black; */
        }

        ul li {
            display: inline-block;
            list-style: none;
        }

        #container {
            display: flex;
            justify-content: space-around;
            /* border: 1px solid green; */
        }

        #container>ul>li {
            width: 250px;
            height: 50px;
            text-align: center;
            line-height: 40px;
            font-size: 2em;
        }

        #container>ul>li:hover {
            border-bottom: 1px solid black;
            font-weight: bold;
        }

        a {
            text-decoration: none;
            color: black;
        }
        .h2_box {
        	margin: 0 auto;
    		width: 400px;
    		text-align: left;
        }

        #form_container_id {
            margin: 50px auto;
            width: 500px;
            height: 280px;
            /* border: 1px solid black; */
        }

        .form_container_id {
            margin: 25px;
            display: flex;
            justify-content: center;
            align-content: center;
        }

        .form_container_id>input {
            width: 400px;
            padding: 10px;
            font-size: 1.5em;
        }

        .form_container_id>div>input[type="email"] {
            width: 230px;
            font-size: 1.5em;
            padding: 10px;
            margin-right: 20px;
        }

        .form_container_id>div>button {
            width: 150px;
            height: 52px;
            font-weight: bold;
            font-size: 1.2em;
            padding: 10px;
        }

        .form_container_id>input[type="submit"] {
            font-weight: bold;
        }
        
        .form_container_id>input[type="submit"]:active {
			margin-left: 1.5px;
			margin-top: 1.5px;
			box-shadow: -1.5px -1.5px -1.5px -1.5px;
			border: 2px solid #767676;
			background-color: #e5e5e5;
		}
    </style>
</head>

<body>
    <header>
        <div><a href="<c:url value='/'/>"><img src="${path }/resources/img/로고.jpg" width="200px" alt="로고"></a></div>
        <div><a href="<c:url value='/BClogin/BClogin'/>"><img src="${path }/resources/img/로그인.png" width="110px" alt="로그인"></a></div>
    </header>
    <div id="vertical">
        <div>
            <p>아이디/비밀번호 찾기</p>
            <div id="container">
                <ul>
                    <li><a href="<c:url value='/BCFind/BCFindingId'/>">아이디</a></li>
                    <li><a href="<c:url value='/BCFind/BCFindingPwd'/>">비밀번호</a></li>
                </ul>
            </div>
            <div class="h2_box"><h2>등록한 이메일로 아이디 찾기</h2></div>
        </div>
    </div>
    <!-- 아이디 찾기 -->
    <form action="<c:url value='/BCFind/BCFindingId'/>" id="form" method="post" onsubmit="return ConfirmBtn();">
        <div id="form_container_id">
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
    	setTimeout(() => {
    		let emailAddr = document.getElementById('email').value;
    		if ( emailAddr.match("naver") ) {
    			window.location.href = "http://mail.naver.com";
    		} else if ( emailAddr.match("gmail") ) {
    			window.location.href = "https://mail.google.com";
    		} else {
    			window.location.href = "http://localhost:8080/bctour/BClogin/BClogin";
    		}
		}, 4300);
    	
    	let email = document.getElementById("email").value;
    	let valid = new RegExp('^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$');
		if (valid.test(email)==false) {
 			alert("이메일 형식이 올바르지 않습니다.\n다시 입력해주세요.");
 			return false;
		} else {
			alert("입력하신 이메일로 인증확인이 발송되었습니다.\n메일페이지로 이동됩니다.");
			var form = document.getElementById('form');
			form.action = "<c:url value='/BCFind/emailGetId'/>";
			form.method = "POST";
			form.submit();
		}
	});
    </script>
</body>

</html>