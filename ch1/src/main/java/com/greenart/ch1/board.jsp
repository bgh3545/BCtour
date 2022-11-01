<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loginoutlink" value="${sessionScope.id==null? '/logIn1/logIn1':'/logIn1/logOut1'}"/>
<c:set var="loginout" value="${sessionScope.id==null? 'LogIn':'LogOut' }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style>
    .menu{
        font-size: 30px;
        background-color: aqua;
        width: 100%;
        height: 80px;
        text-align: right;
    }
    a[href]:hover{
        font-weight: bolder;
        color: black;
    }
    a[href]{
        text-decoration: none;
        color: gray;
        margin-right: 20px;
    }
    h1{
    	text-align:center;
    }
</style>
</head>
<body>

    <div class="menu">
    	<a href="<c:url value='/'/>">Home</a>
    	<a href="<c:url value='/board/list'/>">Board</a>
        <a href="<c:url value='${loginoutlink }'/>">${loginout}</a>
        <a href="<c:url value='/register/add'/>">SignIn</a>
    </div>
    <h1>게시판 페이지</h1>
</body>
</html>