<%--
  Created by IntelliJ IDEA.
  User: Hoang
  Date: 5/23/2026
  Time: 7:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Cập nhật Beer Order</title>
  <meta charset="UTF-8">
</head>
<body>
<h2>Cập nhật Beer Order</h2>

<c:if test="${not empty error}">
  <div style="color:red;">${error}</div>
</c:if>

<form action="edit" method="post">
  <!-- hidden để biết order nào cần cập nhật -->
  <input type="hidden" name="orderId" value="${order.orderId}">

  <label>Customer ID:</label>
  <input type="text" name="customerId" value="${order.customerId}" required><br><br>

  <label>Customer Name:</label>
  <input type="text" name="customerName" value="${order.customerName}" required><br><br>

  <label>Beer Name:</label>
  <input type="text" name="beerName" value="${order.beerName}" required><br><br>

  <label>Quantity:</label>
  <input type="number" name="quantity" value="${order.quantity}" min="1" required><br><br>

  <input type="submit" value="Cập nhật Order">
  <button type="button" onclick="window.location.href='views'">Quay lại</button>
</form>
</body>
</html>