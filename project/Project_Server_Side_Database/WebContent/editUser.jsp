<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="UserController">
		<input type="hidden" name="id" value="${user_me.id}"/>
	 	User-name: <input type="text" name="userName" value="${user_me.username}"/> <br>
		Email: <input type="text" name="userEmail" value="${user_me.email}"/><br>
		Password: <input type="text" name="userPassword" value="${user_me.password}"/><br>
		<INPUT TYPE="SUBMIT" NAME="submit" VALUE="Save">
	</form>
</body>
</html>