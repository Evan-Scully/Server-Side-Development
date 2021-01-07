[<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>]

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 
<c:forEach items="${houses}" var="house">
	Owner: <c:out value="${house.name}"/><br>
	Address: <c:out value="${house.address}"/><br>
	Numbers of Bedrooms: <c:out value="${house.bedrooms}"/> 
	<br>
<br>
</c:forEach> 
</body>
</html>