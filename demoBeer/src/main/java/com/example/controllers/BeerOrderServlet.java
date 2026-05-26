package com.example.controllers;

import com.example.dao.BeerOrderDAO;
import com.example.dao.BeerOrderImpl;
import com.example.entity.BeerOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/views", "/create", "/edit", "/delete"})
public class BeerOrderServlet extends HttpServlet {
    private final BeerOrderDAO _dao = new BeerOrderImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/create":
                // hiển thị form thêm mới
                req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
                break;

            case "/edit":
                // hiển thị form sửa
                String idStr = req.getParameter("id");
                if (idStr == null || idStr.isEmpty()) {
                    req.getRequestDispatcher("findOrder.jsp").forward(req, resp);
                } else {
                    try {
                        int id = Integer.parseInt(idStr);
                        BeerOrder order = _dao.findById(id);
                        if (order != null) {
                            req.setAttribute("order", order);
                            req.getRequestDispatcher("editOrder.jsp").forward(req, resp);
                        } else {
                            req.setAttribute("error", "Không tìm thấy order với ID này!");
                            req.getRequestDispatcher("findOrder.jsp").forward(req, resp);
                        }
                    } catch (NumberFormatException e) {
                        req.setAttribute("error", "ID không hợp lệ!");
                        req.getRequestDispatcher("findOrder.jsp").forward(req, resp);
                    }
                }
                break;

            case "/delete":
                // hiển thị form xóa
                req.getRequestDispatcher("deleteOrder.jsp").forward(req, resp);
                break;

            default:
                // hiển thị danh sách
                List<BeerOrder> list = _dao.getAll();
                req.setAttribute("list", list);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/create":
                // xử lý thêm mới
                String customerName = req.getParameter("customerName");
                String beerName = req.getParameter("beerName");
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                int customerId = Integer.parseInt(req.getParameter("customerId")); // nếu form có trường này

                BeerOrder newOrder = new BeerOrder();
                newOrder.setCustomerId(customerId);
                newOrder.setCustomerName(customerName);
                newOrder.setBeerName(beerName);
                newOrder.setQuantity(quantity);

                _dao.insert(newOrder);
                resp.sendRedirect("views?success=create");
                break;

            case "/edit":
                // xử lý cập nhật
                int id = Integer.parseInt(req.getParameter("orderId"));
                int editCustomerId = Integer.parseInt(req.getParameter("customerId"));
                String editCustomerName = req.getParameter("customerName");
                String editBeerName = req.getParameter("beerName");
                int editQuantity = Integer.parseInt(req.getParameter("quantity"));

                BeerOrder editOrder = new BeerOrder();
                editOrder.setOrderId(id);
                editOrder.setCustomerId(editCustomerId);
                editOrder.setCustomerName(editCustomerName);
                editOrder.setBeerName(editBeerName);
                editOrder.setQuantity(editQuantity);

                _dao.update(editOrder);
                resp.sendRedirect("views?success=edit");
                break;

            case "/delete":
                // xử lý xóa
                try {
                    int delId = Integer.parseInt(req.getParameter("orderId"));
                    BeerOrder order = _dao.findById(delId);
                    if (order != null) {
                        _dao.delete(delId);
                        resp.sendRedirect("views?success=delete");
                    } else {
                        req.setAttribute("error", "Không tìm thấy order với ID này!");
                        req.getRequestDispatcher("deleteOrder.jsp").forward(req, resp);
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "ID không hợp lệ!");
                    req.getRequestDispatcher("deleteOrder.jsp").forward(req, resp);
                }
                break;

            default:
                resp.sendRedirect("views");
                break;
        }
    }
}