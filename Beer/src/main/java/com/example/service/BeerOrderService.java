package com.example.service;

import com.example.dao.BeerOrderDAO;
import com.example.dao.IBeerOrderDAO;
import com.example.entity.BeerOrder;

import java.util.List;

public class BeerOrderService {
    private final BeerOrderDAO dao = new BeerOrderDAO();

    public List<BeerOrder> getAllOrders() {
        return dao.getAll();
    }

    public BeerOrder findOrderById(int id) {
        return dao.findById(id);
    }

    public String createOrder(BeerOrder order) {
        if (order.getCustomerName() == null || order.getCustomerName().isBlank()) {
            return "Tên khách hàng không hợp lệ";
        }
        if (!order.getCustomerName().matches("^[a-zA-Z ]+$")) {
            return "Tên khách hàng chỉ được chứa chữ cái và khoảng trắng";
        }
        if (order.getBeerName() == null || order.getBeerName().isBlank()) {
            return "Tên beer không hợp lệ";
        }
        if (!order.getBeerName().matches("^[a-zA-Z ]+$")) {
            return "Tên beer chỉ được chứa chữ cái và khoảng trắng";
        }
        if (order.getQuantity() <= 0) {
            return "Số lượng không hợp lệ";
        }
        dao.insert(order);
        return null;
    }

    public void updateOrder(BeerOrder order) {
        dao.update(order);
    }

    public String deleteOrder(int id) {
        BeerOrder order = dao.findById(id);
        if (order == null) return "Không tìm thấy order với ID này";
        dao.delete(id);
        return null;
    }
}
