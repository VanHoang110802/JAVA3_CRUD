package com.example.controller;

import com.example.entity.BeerOrder;
import com.example.service.BeerOrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//  mỗi chức năng mình làm 1 trang jsp riêng
@WebServlet({"/views", "/create", "/edit", "/delete"})
public class BeerOrderServlet extends HttpServlet {
    private final BeerOrderService service = new BeerOrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/create")) {
            hienThiFromCreate(req, resp);
        } else if (path.equals("/edit")) {
            hienThiFromEdit(req, resp);
        } else if (path.equals("/delete")) {
            hienThiFromDelete(req, resp);
        } else {
            hienThiFromGetAll(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/create")) {
            xuLyPhanCreate(req, resp);
        } else if (path.equals("/edit")) {
            xuLyPhanEdit(req, resp);
        } else if (path.equals("/delete")) {
            xuLyPhanDelete(req, resp);
        } else {
            resp.sendRedirect("views");
        }
    }

    // Các hàm riêng
    private void hienThiFromCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
    }

    private void hienThiFromEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                BeerOrder order = service.findOrderById(id);
                req.setAttribute("order", order);
            } catch (NumberFormatException e) {
                req.setAttribute("error", "ID không hợp lệ!");
            }
        }
        req.getRequestDispatcher("editOrder.jsp").forward(req, resp);
    }

    private void hienThiFromDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("deleteOrder.jsp").forward(req, resp);
    }

    private void hienThiFromGetAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BeerOrder> list = service.getAllOrders();
        req.setAttribute("list", list);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private void xuLyPhanCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BeerOrder order = new BeerOrder();
        order.setCustomerId(Integer.parseInt(req.getParameter("customerId")));
        order.setCustomerName(req.getParameter("customerName"));
        order.setBeerName(req.getParameter("beerName"));
        order.setQuantity(Integer.parseInt(req.getParameter("quantity")));

        String error = service.createOrder(order);
        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("views?success=create");
        }
    }

    private void xuLyPhanEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BeerOrder order = new BeerOrder();
        order.setOrderId(Integer.parseInt(req.getParameter("orderId")));
        order.setCustomerId(Integer.parseInt(req.getParameter("customerId")));
        order.setCustomerName(req.getParameter("customerName"));
        order.setBeerName(req.getParameter("beerName"));
        order.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        service.updateOrder(order);
        resp.sendRedirect("views?success=edit");
    }

    private void xuLyPhanDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("orderId"));
        String error = service.deleteOrder(id);
        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("deleteOrder.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("views?success=delete");
        }
    }
}
