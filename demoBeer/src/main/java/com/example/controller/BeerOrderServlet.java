package com.example.controller;

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

@WebServlet(urlPatterns = {"/orders", "/create", "/edit", "/delete"})
public class BeerOrderServlet extends HttpServlet {
    private final BeerOrderDAO dao = new BeerOrderImpl();

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
                        BeerOrder order = dao.findById(id);
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
                String delIdStr = req.getParameter("id");
                if (delIdStr == null || delIdStr.isEmpty()) {
                    // chưa nhập ID
                    req.setAttribute("error", "Bạn chưa nhập ID để xóa!");
                    req.getRequestDispatcher("deleteOrder.jsp").forward(req, resp);
                } else {
                    try {
                        int delId = Integer.parseInt(delIdStr);
                        BeerOrder order = dao.findById(delId);
                        if (order != null) {
                            dao.delete(delId);
                            resp.sendRedirect("orders?success=delete");
                        } else {
                            // không tìm thấy order
                            req.setAttribute("error", "Không tìm thấy order với ID này!");
                            req.getRequestDispatcher("deleteOrder.jsp").forward(req, resp);
                        }
                    } catch (NumberFormatException e) {
                        // nhập sai định dạng ID
                        req.setAttribute("error", "ID không hợp lệ!");
                        req.getRequestDispatcher("deleteOrder.jsp").forward(req, resp);
                    }
                }
                break;

            default: // /orders
                List<BeerOrder> list = dao.getAll();
                req.setAttribute("list", list);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            try {
                BeerOrder order = new BeerOrder();
                order.setCustomerId(Integer.parseInt(req.getParameter("customerId")));
                order.setCustomerName(req.getParameter("customerName"));
                order.setBeerName(req.getParameter("beerName"));
                order.setQuantity(Integer.parseInt(req.getParameter("quantity")));
                dao.insert(order);
                resp.sendRedirect("orders?success=create");
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Dữ liệu nhập không hợp lệ!");
                req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
            }

        } else if ("update".equals(action)) {
            try {
                BeerOrder order = new BeerOrder();
                order.setOrderId(Integer.parseInt(req.getParameter("orderId")));
                order.setCustomerId(Integer.parseInt(req.getParameter("customerId")));
                order.setCustomerName(req.getParameter("customerName"));
                order.setBeerName(req.getParameter("beerName"));
                order.setQuantity(Integer.parseInt(req.getParameter("quantity")));
                dao.update(order);
                resp.sendRedirect("orders?success=update");
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Dữ liệu nhập không hợp lệ!");
                req.getRequestDispatcher("editOrder.jsp").forward(req, resp);
            }
        }
    }
}