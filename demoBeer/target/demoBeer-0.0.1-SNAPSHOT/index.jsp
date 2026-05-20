<%--
  Created by IntelliJ IDEA.
  User: Hoang
  Date: 5/19/2026
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Beer Orders</title>
    <meta charset="UTF-8">
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #333;
            padding: 8px 12px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        h2 {
            text-align: center;
        }

        .success {
            color: green;
            text-align: center;
        }
    </style>
</head>
<body>
<!-- Thông báo thành công -->
<c:if test="${param.success == 'create'}">
    <p class="success">Thêm order thành công!</p>
</c:if>
<c:if test="${param.success == 'update'}">
    <p class="success">Cập nhật order thành công!</p>
</c:if>
<c:if test="${param.success == 'delete'}">
    <p class="success">Xóa order thành công!</p>
</c:if>

<h2>Danh sách Beer Orders</h2>
<table>
    <thead>
    <tr>
        <th>Order ID</th>
        <th>Customer ID</th>
        <th>Customer Name</th>
        <th>Beer Name</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${list}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.customerId}</td>
            <td>${order.customerName}</td>
            <td>${order.beerName}</td>
            <td>${order.quantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
