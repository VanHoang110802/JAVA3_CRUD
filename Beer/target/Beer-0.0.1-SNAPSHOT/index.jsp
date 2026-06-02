<%--
  Created by IntelliJ IDEA.
  User: Hoang
  Date: 6/2/2026
  Time: 11:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    .actions {
      text-align: center;
      margin-bottom: 20px;
    }

    .actions button {
      margin: 0 5px;
    }
  </style>
</head>
<body>
<h2>Danh sách Beer Orders</h2>

<c:if test="${not empty param.success}">
  <div class="success">
    <c:choose>
      <c:when test="${param.success eq 'create'}">Thêm order thành công!</c:when>
      <c:when test="${param.success eq 'edit'}">Cập nhật order thành công!</c:when>
      <c:when test="${param.success eq 'delete'}">Xóa order thành công!</c:when>
    </c:choose>
  </div>
</c:if>

<div class="actions">
  <button type="button" onclick="window.location.href='create'">Thêm Order</button>
  <button type="button" onclick="window.location.href='edit'">Cập nhật Order</button>
  <button type="button" onclick="window.location.href='delete'">Xóa Order</button>
  <button type="button" onclick="window.location.href='views'">Xem Orders</button>
</div>

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