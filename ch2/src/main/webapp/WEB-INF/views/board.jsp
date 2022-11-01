<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>greenart</title>
    <link rel="icon" href="/ch1/resources/img/상단로고.jpg">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    
	<style>
		* { 
	    box-sizing: border-box; 
	    margin : 0;
	    padding: 0;
	}
	
	a { text-decoration: none;  }
	
	ul {
	    list-style-type: none;
	    height: 48px;
	    width: 100%;
	    background-color: #93CC8D;
	    display: flex;
	}
	
	ul > li {
	    color: lightgray;
	    height : 100%;
	    width:90px;
	    display:flex;
	    align-items: center;
	}
	
	ul > li > a {
	    color: lightgray;
	    margin:auto;
	    padding: 10px;
	    font-size:20px;
	    align-items: center;
	}
	
	ul > li > a:hover {
	    color :white;
	    border-bottom: 3px solid rgb(209, 209, 209);
	}
	
	#logo {
		color:white;
	    font-size: 18px;
	    padding-left:40px; 
	    margin-right:auto;
	    display: flex;
	}
	</style>
</head>
<body>
<c:set var="loginOutlink" value="${sessionScope.id == null? '/BClogin/BClogin' : '/BClogin/BClogout' }" />
<c:set var="loginOut" value="${sessionScope.id==null? 'Login' : 'Logout' }" />
<div id="menu">
	<ul>
	    <li id="logo">greenart</li>
	    <li><a href="<c:url value='/'/>">Home</a></li>
	    <li><a href="<c:url value='/board/list'/>">Board</a></li>
	    <li><a href="<c:url value='${loginOutlink }'/>">${loginOut }</a></li>    
	    <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
	    <li><a href=""><i class="fas fa-search small"></i></a></li>
	</ul> 
</div>
<div style="text-align:center">
	<h1>게시판 목록입니다.</h1>
</div>