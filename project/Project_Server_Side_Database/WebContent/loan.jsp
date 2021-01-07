<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your loaned books</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<style type="text/css"> 
body { 
 width:100%;
} 
.loans {
	width:100%;
	color:red;
	display: flex;
}
.books {
	width: 250px;
	color: black;
}
.alert{
	color:red;
}
.title {
	color:blue;
}
</style> 
</head>
<body>
	<h1 class="title"><c:out value="${user_me.username}"/>'s Library Account</h1>
	
	<c:if test="${empty books}">
    	<h2 class="alert">You currently have no loaned books</h2>
	</c:if>
	<c:if test="${not empty books}">
    	<h2>Your loaned books</h2>
	</c:if>
	
	<div class="loans">
		<c:forEach items="${books}" var="book">
			<div class="books">
				<form method="post" action="LoanController">
					<br>
					<input type="hidden" name="id" value="${book.id }"/>
					Title: <c:out value="${book.name }"/><br>
					Author: <c:out value="${book.author}"/><br>
					Series: <c:out value="${book.series}"/><br>
					ISBN: <c:out value="${book.ISBN}"/><br>
					<INPUT TYPE="SUBMIT" NAME="submit" VALUE="Return">
					<INPUT TYPE="SUBMIT" NAME="submit" VALUE="Edit">
					<br>
				</form>
			</div>	
		</c:forEach>
	</div>	
	
	<h2>Books in the Library</h2>
	
	<c:if test="${empty list_of_books}">
    	You currently have all the books rented
	</c:if>
	<c:if test="${not empty list_of_books}">
    	Books currently in stock
	</c:if>
	
	<div class="loans">
		<c:forEach items="${list_of_books}" var="book">
			<div class="books">
				<form method="post" action="LoanController">
					<br>
					<input type="hidden" name="id" value="${book.id }"/>
					Title: <c:out value="${book.name }"/><br>
					Author: <c:out value="${book.author}"/><br>
					Series: <c:out value="${book.series}"/><br>
					ISBN: <c:out value="${book.ISBN}"/><br>
					<INPUT TYPE="SUBMIT" NAME="submit" VALUE="Loan">
					<br>
				</form>
			</div>
		</c:forEach>
	</div>
	
	<h2>Create Book</h2>
	
	<form method="post" action="BookController">
		Title: <input type="text" name="name"/><br>
		Author: <input type="text" name="author"/><br>
		Series: <input type="text" name="series"/><br>
		ISBN: <input type="text" name="isbn"/><br>
	<input type="submit" NAME="submit" value="Create"/>
	</form>
	
	<h2>My Account</h2>
	
	<form method="post" action="UserController">
		<input type="hidden" name="id" value="${user_me.id }"/>
		User-name: <c:out value="${user_me.username }"/><br>
		Email: <c:out value="${user_me.email}"/><br>
		<INPUT TYPE="SUBMIT" NAME="submit" VALUE="change">
		<INPUT TYPE="SUBMIT" NAME="submit" VALUE="logout">
	</form>
	
</body>
</html>