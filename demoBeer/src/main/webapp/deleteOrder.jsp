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
<h2>Nhập ID Order cần xóa</h2>
<form action="delete" method="get">
  <label>Order ID:</label>
  <input type="number" name="id" required>
  <input type="submit" value="Xóa Order">
</form>

<!-- Hiển thị thông báo lỗi nếu servlet set attribute "error" -->
<c:if test="${not empty error}">
  <p style="color:red;">${error}</p>
</c:if>

<!-- Nút quay lại danh sách -->
<button type="button" onclick="window.location.href='views'">Quay lại danh sách</button>
</body>
</html>