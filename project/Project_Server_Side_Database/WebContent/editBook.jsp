<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="BookController">
		<input type="hidden" name="id" value="${book.id}"/>
	 	Title: <input type="text" name="name" value="${book.name}"/> <br>
		Author: <input type="text" name="author" value="${book.author}"/><br>
		Series: <input type="text" name="series" value="${book.series}"/><br>
		ISBN: <input type="text" name="isbn" value="${book.ISBN}"/><br>
		<INPUT TYPE="SUBMIT" NAME="submit" VALUE="Save">
	</form>
</body>
</html>