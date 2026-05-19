package com.example.dao;

import com.example.entity.BeerOrder;
import com.example.jdbc.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeerOrderImpl implements BeerOrderDAO {
    @Override
    public List<BeerOrder> getAll() {
        List<BeerOrder> list = new ArrayList<>();
        String sql = "SELECT orderId, customerId, customerName, beerName, quantity FROM BeerOrder";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BeerOrder o = new BeerOrder();
                o.setOrderId(rs.getInt("orderId"));
                o.setCustomerId(rs.getInt("customerId"));
                o.setCustomerName(rs.getString("customerName"));
                o.setBeerName(rs.getString("beerName"));
                o.setQuantity(rs.getInt("quantity"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
