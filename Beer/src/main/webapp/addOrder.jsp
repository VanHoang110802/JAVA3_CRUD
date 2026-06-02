<%--
  Created by IntelliJ IDEA.
  User: Hoang
  Date: 6/2/2026
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Thêm Beer Order</title>
  <meta charset="UTF-8">
</head>
<body>
<h2>Thêm Beer Order mới</h2>
<form action="create" method="post">
  <label>Customer ID:</label>
  <input type="text" name="customerId" required><br><br>

  <label>Customer Name:</label>
  <input type="text" name="customerName" pattern="^[a-zA-Z ]+$" required><br><br>

  <label>Beer Name:</label>
  <input type="text" name="beerName" pattern="^[a-zA-Z ]+$" required><br><br>

  <label>Quantity:</label>
  <input type="number" name="quantity" min="1" step="1" required><br><br>

  <input type="submit" value="Thêm Order">
  <button type="button" onclick="window.location.href='views'">Quay lại</button>
</form>
</body>
</html>