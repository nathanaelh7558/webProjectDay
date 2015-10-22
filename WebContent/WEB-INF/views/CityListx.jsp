<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<tr><th>City</th><th>District</th><th>Population</th></tr>
<c:forEach var="row" items="${rows}" >
<tr>
<td><c:out value="${row[0] }"/></td>
<td><c:out value="${row[1] }"/></td>
<td><c:out value="${row[2] }"/></td>
</tr>
</c:forEach>
</table>
</body>
</html>