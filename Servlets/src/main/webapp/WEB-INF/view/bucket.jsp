<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Good List</title>
</head>
<body>
<h2>Menu:</h2>
<div><a href="AndersenTrainee_war">Main page</a></div>
<div><a href="goodlist">Good list</a></div>
<div>Your bucket contains:</div>
<table border="2">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Count</td>
        <td>Delete</td>
    </tr>
    <c:forEach items="${bucket}" var="bucket">
        <tr>
            <td>${bucket.key.getId()}</td>
            <td>${bucket.key.getName()}</td>
            <td>${bucket.key.getPrice()}</td>
            <td>${bucket.value}</td>
            <td>
                <form action="bucket" method="post">
                    <input type="hidden" name="id" value="${bucket.key.getId()}">
                    <input type="submit" value="Delete" style="float:right">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>