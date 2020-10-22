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

 
<c:forEach items="${userList}" var="user">
		User name is: <c:out value="${user.name}"/><br>
		User address is: <c:out value="${user.address}"/><br>
		User age is: <c:out value="${user.age}"/> 
		<br>
	</c:forEach>

<!-- 
 <c:choose>
		<c:when test='${user.name=="Martina"}'>
			<c:out value="Hi M!"/>
		</c:when>
		<c:when test='${user.name=="Ms Curran"}'>
			<c:out value="Hi Ms!"/>
		</c:when>
		<c:otherwise>
			<c:out value="Hello....."/>
		</c:otherwise>
	</c:choose>
-->
		<!-- 
Name: <c:out value="${user.name}" default="this name is unknown"/>
Address: <c:out value="${user.address}" default="this address is unknown"/>
Age * 2: <c:out value="${user.age * 2}"/>
-->
</body>
</html>