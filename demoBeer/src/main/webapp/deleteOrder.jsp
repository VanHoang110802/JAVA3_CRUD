<%--
  Created by IntelliJ IDEA.
  User: Hoang
  Date: 5/23/2026
  Time: 7:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Xóa Beer Order</title>
  <meta charset="UTF-8">
</head>
<body>
<h2>Xóa Beer Order</h2>

<c:if test="${not empty error}">
  <div style="color:red;">${error}</div>
</c:if>

<form action="delete" method="post">
  <label>Order ID cần xóa:</label>
  <input type="text" name="orderId" required><br><br>

  <input type="submit" value="Xóa Order">
  <button type="button" onclick="window.location.href='views'">Quay lại</button>
</form>
</body>
</html>