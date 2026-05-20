<%--
  Created by IntelliJ IDEA.
  User: Hoang
  Date: 5/20/2026
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Tìm Order để sửa</title></head>
<body>
<h2>Nhập ID Order cần sửa</h2>
<form action="edit" method="get">
    <label>Order ID:</label>
    <input type="number" name="id" required>
    <input type="submit" value="Tìm">
    <!-- Nút quay lại -->
    <button type="button" onclick="window.location.href='orders'">Quay lại</button>
</form>
</body>
</html>
