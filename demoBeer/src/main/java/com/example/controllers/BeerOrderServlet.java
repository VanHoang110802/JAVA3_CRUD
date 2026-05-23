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
                String delIdStr = req.getParameter("id");
                if (delIdStr == null || delIdStr.isEmpty()) {
                    // chưa nhập ID
                    req.setAttribute("error", "Bạn chưa nhập ID để xóa!");
                    req.getRequestDispatcher("deleteOrder.jsp").forward(req, resp);
                } else {
                    try {
                        int delId = Integer.parseInt(delIdStr);
                        BeerOrder order = _dao.findById(delId);
                        if (order != null) {
                            _dao.delete(delId);
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
            default:
                List<BeerOrder> list = _dao.getAll();
                req.setAttribute("list", list);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
        }
    }
}
