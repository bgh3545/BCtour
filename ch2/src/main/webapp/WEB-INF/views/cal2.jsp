<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.net.URLDecoder" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

    <title>Register</title>
</head>
<body>
    <!--  form id="user" action="<c:url value="/register/add"/>" method="post"-->
   	<form:form modelAttribute="num">

   
    <div id="msg" class="msg"><form:errors path="x"/> <br><form:errors path="y"/>  </div> 
     <div id="msg" class="msg"></div>   
    		<label for="">x</label>
    	<input class="input-field" type="text" name="x" >
		<label for="">y</label>
    	<input class="input-field" type="text" name="y" >
    <input id="input-submit" type="submit" value="전송"></input>
    
   </form:form> 
   
   
</body>
</html>