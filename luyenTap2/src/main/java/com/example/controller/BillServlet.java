package com.example.controller;

import com.example.dao.BillDAO;
import com.example.entity.Bill;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/BillServlet/*")
public class BillServlet extends HttpServlet {
    private BillDAO dao = new BillDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo(); // lấy phần sau /BillServlet
        //System.out.println("duong dan hien dang chay la: " + path);
        if ("/list".equals(path)) {
            List<Bill> bills = dao.getAllDao();
            req.setAttribute("bills", bills);
           // System.out.println("Forwarding to /bill.jsp với số lượng bill: " + bills.size());
            req.getRequestDispatcher("/bill.jsp").forward(req, resp);

        } else if ("/filter".equals(path)) {
            String maDonHang = req.getParameter("maDonHang");
            String loaiDoUong = req.getParameter("loaiDoUong");
            String sizeDoUong = req.getParameter("sizeDoUong");

            List<Bill> bills = dao.filterDao(maDonHang, loaiDoUong, sizeDoUong);
            req.setAttribute("bills", bills);
            System.out.println("Filter params: " + maDonHang + ", " + loaiDoUong + ", " + sizeDoUong);
            //System.out.println("SQL: " + sql);
            System.out.println("Kết quả lọc: " + bills.size());
            req.getRequestDispatcher("/bill.jsp").forward(req, resp);

        } else if ("/edit".equals(path)) {
            String maDonHang = req.getParameter("maDonHang");
            Bill bill = dao.findByIdDao(maDonHang);
            req.setAttribute("bill", bill);
            req.getRequestDispatcher("/bill.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        System.out.println("duong dan hien dang chay la: " + path);
        if ("/create".equals(path)) {
            Bill b = new Bill();
            b.setMaDonHang(req.getParameter("maDonHang"));
            b.setEmailNhanHoaDon(req.getParameter("emailNhanHoaDon"));
            b.setNgayDatHang(LocalDateTime.parse(req.getParameter("ngayDatHang")));
            b.setLoaiDoUong(req.getParameter("loaiDoUong"));
            b.setSizeDoUong(req.getParameter("sizeDoUong"));
            b.setSoCocDat(Integer.parseInt(req.getParameter("soCocDat")));
            dao.insertDao(b);
            resp.sendRedirect(req.getContextPath() + "/BillServlet/list");
        } else if ("/update".equals(path)) {
            String maDonHang = req.getParameter("maDonHang");
            String email = req.getParameter("emailNhanHoaDon");
            String ngayDatHang = req.getParameter("ngayDatHang");
            String loaiDoUong = req.getParameter("loaiDoUong");
            String sizeDoUong = req.getParameter("sizeDoUong");
            int soCocDat = Integer.parseInt(req.getParameter("soCocDat"));

            Bill bill = new Bill();
            bill.setMaDonHang(maDonHang);
            bill.setEmailNhanHoaDon(email);
            bill.setNgayDatHang(LocalDateTime.parse(ngayDatHang.replace(" ", "T"))); // chuyển từ datetime-local
            bill.setLoaiDoUong(loaiDoUong);
            bill.setSizeDoUong(sizeDoUong);
            bill.setSoCocDat(soCocDat);

            dao.updateDao(bill);

            resp.sendRedirect(req.getContextPath() + "/BillServlet/list");
        } else if ("/delete".equals(path)) {
            String maDonHang = req.getParameter("maDonHang");
            dao.deleteDao(maDonHang);
            resp.sendRedirect(req.getContextPath() + "/BillServlet/list");
        }
    }
}
