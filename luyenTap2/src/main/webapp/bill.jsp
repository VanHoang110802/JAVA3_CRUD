<%--
  Created by IntelliJ IDEA.
  User: Hoang
  Date: 6/15/2026
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.entity.Bill" %>
<html>
<head>
    <title>Đặt đơn hàng trà sữa PolyBar</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h2, h3 { color: #333; }
        label { display: inline-block; width: 150px; margin-bottom: 5px; }
        input, select { margin-bottom: 10px; }
        table { border-collapse: collapse; width: 100%; margin-top: 15px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
        .btn { padding: 5px 10px; margin-right: 5px; }
    </style>
</head>
<body>

<h2><%= request.getAttribute("bill") != null ? "Sửa đơn hàng" : "Đặt đơn hàng trà sữa PolyBar" %></h2>
<form method="post" action="<%=request.getContextPath()%>/BillServlet/<%= request.getAttribute("bill") != null ? "update" : "create" %>">
    <%
        com.example.entity.Bill bill = (com.example.entity.Bill) request.getAttribute("bill");
    %>

    <label>Mã đơn hàng:</label>
    <input type="text" name="maDonHang"
           value="<%= bill != null ? bill.getMaDonHang() : "" %>"
        <%= bill != null ? "readonly" : "required" %>><br>

    <label>Email nhận hóa đơn:</label>
    <input type="email" name="emailNhanHoaDon"
           value="<%= bill != null ? bill.getEmailNhanHoaDon() : "" %>"
           required><br>

    <label>Ngày đặt hàng:</label>
    <input type="datetime-local" name="ngayDatHang"
           value="<%= bill != null ? bill.getNgayDatHang().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) : "" %>"
           required><br>

    <label>Loại đồ uống:</label>
    <select name="loaiDoUong">
        <option value="Truyền thống" <%= bill != null && "Truyền thống".equals(bill.getLoaiDoUong()) ? "selected" : "" %>>Truyền thống</option>
        <option value="Matcha" <%= bill != null && "Matcha".equals(bill.getLoaiDoUong()) ? "selected" : "" %>>Matcha</option>
        <option value="Cam sả" <%= bill != null && "Cam sả".equals(bill.getLoaiDoUong()) ? "selected" : "" %>>Cam sả</option>
        <option value="Sữa đặc" <%= bill != null && "Sữa đặc".equals(bill.getLoaiDoUong()) ? "selected" : "" %>>Sữa đặc</option>
    </select><br>

    <label>Size:</label>
    <input type="radio" name="sizeDoUong" value="M" <%= bill != null && "M".equals(bill.getSizeDoUong()) ? "checked" : "" %>> M
    <input type="radio" name="sizeDoUong" value="L" <%= bill != null && "L".equals(bill.getSizeDoUong()) ? "checked" : "" %>> L
    <input type="radio" name="sizeDoUong" value="XL" <%= bill != null && "XL".equals(bill.getSizeDoUong()) ? "checked" : "" %>> XL<br>

    <label>Số cốc đặt:</label>
    <input type="number" name="soCocDat" min="1"
           value="<%= bill != null ? bill.getSoCocDat() : "" %>"
           required><br>

    <button type="submit" class="btn"><%= bill != null ? "Cập nhật" : "Đăng ký" %></button>
    <% if (bill != null) { %>
    <a href="<%=request.getContextPath()%>/BillServlet/list" class="btn">Thoát</a>
    <% } %>
</form>

<p>Xem danh sách đơn hàng đã đặt tại đây:
    <a href="<%=request.getContextPath()%>/BillServlet/list">Xem danh sách</a>
</p>

<hr>

<h3>Lọc đơn hàng</h3>
<form method="get" action="<%=request.getContextPath()%>/BillServlet/filter">
    <label>Mã đơn hàng:</label>
    <input type="text" name="maDonHang" placeholder="Nhập mã đơn hàng">

    <label>Loại đồ uống:</label>
    <select name="loaiDoUong">
        <option value="" selected>-- Tất cả --</option>
        <option value="Truyền thống">Truyền thống</option>
        <option value="matcha">Matcha</option>
        <option value="cam sả">Cam sả</option>
        <option value="sữa đặc">Sữa đặc</option>
    </select>

    <label>Size:</label>
    <select name="sizeDoUong">
        <option value="" selected>-- Tất cả --</option>
        <option value="M">M</option>
        <option value="L">L</option>
        <option value="XL">XL</option>
    </select>

    <button type="submit" class="btn">Lọc</button>
    <!-- Đặt lại: quay về danh sách đầy đủ -->
    <button type="button" class="btn" onclick="window.location='<%=request.getContextPath()%>/BillServlet/list'">Đặt lại</button>
</form>

<hr>

<h3>Danh sách đơn hàng</h3>
<%
    List<Bill> bills = (List<Bill>) request.getAttribute("bills");
    if (bills != null && !bills.isEmpty()) {
%>
<table>
    <tr>
        <th>STT</th>
        <th>Mã đơn hàng</th>
        <th>Email</th>
        <th>Ngày đặt hàng</th>
        <th>Loại đồ uống</th>
        <th>Size</th>
        <th>Số cốc</th>
        <th>Thành tiền (VND)</th>
        <th>Hành động</th>
    </tr>
    <%
        int i = 1;
        for (Bill b : bills) {
    %>
    <tr>
        <td><%= i++ %></td>
        <td><%= b.getMaDonHang() %></td>
        <td><%= b.getEmailNhanHoaDon() %></td>
        <td><%= b.getNgayDatHang() %></td>
        <td><%= b.getLoaiDoUong() %></td>
        <td><%= b.getSizeDoUong() %></td>
        <td><%= b.getSoCocDat() %></td>
        <td><%= b.getSoCocDat() * 10000 %></td>
        <td>
            <form method="get" action="<%=request.getContextPath()%>/BillServlet/edit" style="display:inline;">
                <input type="hidden" name="maDonHang" value="<%= b.getMaDonHang() %>">
                <button type="submit" class="btn">Xem</button>
            </form>
            <form method="post" action="<%=request.getContextPath()%>/BillServlet/delete" style="display:inline;">
                <input type="hidden" name="maDonHang" value="<%= b.getMaDonHang() %>">
                <button type="submit" class="btn">Xóa</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<p><strong>Tổng tiền:</strong> <%= bills.stream().mapToInt(b -> b.getSoCocDat() * 10000).sum() %> VND</p>
<%
} else {
%>
<p></p>
<%
    }
%>

</body>
</html>
