<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th colspan="2">Действие</th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr bgcolor=${meal.excess ? "red" : "green"}>
            <td>${meal.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <a href="/topjava/meals?action=edit&mealId=${meal.id}">Edit</a>
                <a href="/topjava/meals?action=delete&mealId=${meal.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="/topjava/meals?action=new">Add Meal</a></p>

</body>
</html>
