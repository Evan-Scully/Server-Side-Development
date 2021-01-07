<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:if test="${created}">
    	<h2 class="alert">We created your account! Now login</h2>
	</c:if>
	
	<h2>Login</h2>
	<form method="post" action="UserController">
		<label>Email:</label>
		<input type="email" name="userEmail" value="sheogorath1997@gmail.com"/><br>
		<label>Password:</label>
		<input type="password" name="userPassword"/><br>
		<input TYPE="SUBMIT" NAME="submit" value="log-in"/>
		<input type="reset" value="reset">
	</form>
	
	<c:if test="${not empty login}">
    	<h2 class="alert">Incorrect user-name/password. Please try again</h2>
	</c:if>
	
	<h2>Register</h2>
	<form method="post" action="UserController">
	 	<label>User name:</label>
	 	<input type="text" name="userName"/> <br>
	 	
		<label>Email:</label>
		<input type="email" name="userEmail"/><br>
		
		<label>Password:</label>
		<input type="text" name="userPassword"/><br>
		
		<input TYPE="SUBMIT" NAME="submit" value="register"/>
		<input type="reset" value="reset">
	</form>
</body>
</html>