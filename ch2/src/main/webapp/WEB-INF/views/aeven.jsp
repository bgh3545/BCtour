<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.net.URLDecoder"%>
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
	<form:form modelAttribute="aeven">
	<div style="width:50px; height:50px; color:blue;"><form:errors path='x'/></div>
	<div><form:errors path='y'/></div>
	<label for="x"> x </label>
	<input type="text" id="x" name="x">
	<label for="y"> y </label> 
	<input type="text" id="y" name="y"> <br>
	<input type="submit" value="전송">
	</form:form>
</body>
</html>