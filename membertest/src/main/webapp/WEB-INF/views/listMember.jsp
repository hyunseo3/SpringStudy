<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td>아이디</td>
		<td>이름</td>
		<td>액션</td>
	</tr>
<c:forEach var="m" items="${list}">
    <tr>
        <td>${m.id}</td>
        <td>${m.name}</td>
        <td>
        	<a href="/delete">삭제</a>
        </td>
    </tr>
</c:forEach>

</table>
</body>
</html>