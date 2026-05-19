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

/**
 * Servlet hiển thị danh sách BeerOrder từ database
 */
@WebServlet("/orders")
public class BeerOrderServlet extends HttpServlet {
    private BeerOrderDAO dao = new BeerOrderImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Lấy dữ liệu từ DB qua DAO
        List<BeerOrder> list = dao.getAll();

        // Debug log để kiểm tra dữ liệu
        System.out.println("BeerOrderServlet: lấy được " + list.size() + " bản ghi từ DB");

        // Gắn dữ liệu vào request
        req.setAttribute("list", list);

        // Forward sang index.jsp để hiển thị
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}