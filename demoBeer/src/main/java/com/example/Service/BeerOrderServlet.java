package com.example.Service;

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

//  mỗi chức năng mình làm 1 trang jsp riêng
@WebServlet({"/views", "/create", "/edit", "/delete"})
public class BeerOrderServlet extends HttpServlet {
    private final BeerOrderDAO _dao = new BeerOrderImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/create")) {
            createForm(req, resp);
        } else if (uri.contains("/edit")) {
            editForm(req, resp);
        } else if (uri.contains("/delete")) {
            deleteForm(req, resp);
        } else {
            showList(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/create")) {
            handleCreate(req, resp);
        } else if (uri.contains("/edit")) {
            handleEdit(req, resp);
        } else if (uri.contains("/delete")) {
            handleDelete(req, resp);
        } else {
            resp.sendRedirect("views");
        }
    }

    private void createForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
    }

    private void editForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    }

    private void deleteForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("deleteOrder.jsp").forward(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BeerOrder> list = _dao.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        String beerName = req.getParameter("beerName");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int customerId = Integer.parseInt(req.getParameter("customerId"));

        if (customerName == null || customerName.isBlank()) {
            req.setAttribute("error", "Tên khách hàng không hợp lệ");
            req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
            return;
        }
        if (!customerName.matches("^[a-zA-Z ]+$")) {
            req.setAttribute("error", "Tên chỉ được chứa chữ cái và khoảng trắng!");
            req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
            return;
        }
        if (beerName == null || beerName.isBlank()) {
            req.setAttribute("error", "Tên beer không hợp lệ");
            req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
            return;
        }
        if (!beerName.matches("^[a-zA-Z ]+$")) {
            req.setAttribute("error", "Tên chỉ được chứa chữ cái và khoảng trắng!");
            req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
            return;
        }
        if (quantity <= 0) {
            req.setAttribute("error", "Số lượng không hợp lệ");
            req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
            return;
        }

        BeerOrder newOrder = new BeerOrder();
        newOrder.setCustomerId(customerId);
        newOrder.setCustomerName(customerName.trim());
        newOrder.setBeerName(beerName.trim());
        newOrder.setQuantity(quantity);

        _dao.insert(newOrder);
        resp.sendRedirect("views?success=create");
    }

    private void handleEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    }
}