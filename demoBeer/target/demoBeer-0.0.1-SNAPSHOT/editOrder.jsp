<%--
  Created by IntelliJ IDEA.
  User: Hoang
  Date: 5/23/2026
  Time: 7:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.entity.BeerOrder" %>
<html>
<head><title>Sửa Beer Order</title></head>
<body>
<h2>Sửa Beer Order</h2>
<%
  BeerOrder order = (BeerOrder) request.getAttribute("order");
%>

<form action="edit" method="post">
  <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">

  <label>Customer ID:</label>
  <input type="text" name="customerId" value="<%= order.getCustomerId() %>"><br><br>

  <label>Customer Name:</label>
  <input type="text" name="customerName" value="<%= order.getCustomerName() %>"><br><br>

  <label>Beer Name:</label>
  <input type="text" name="beerName" value="<%= order.getBeerName() %>"><br><br>

  <label>Quantity:</label>
  <input type="number" name="quantity" value="<%= order.getQuantity() %>"><br><br>

  <input type="submit" value="Cập nhật">
  <!-- Nút quay lại -->
  <button type="button" onclick="window.location.href='views'">Quay lại</button>
</form>
</body>
</html>