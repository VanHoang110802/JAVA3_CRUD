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

    @Override
    public BeerOrder findById(int id) {
        String sql = "SELECT orderId, customerId, customerName, beerName, quantity FROM BeerOrder WHERE orderId=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    BeerOrder o = new BeerOrder();
                    o.setOrderId(rs.getInt("orderId"));
                    o.setCustomerId(rs.getInt("customerId"));
                    o.setCustomerName(rs.getString("customerName"));
                    o.setBeerName(rs.getString("beerName"));
                    o.setQuantity(rs.getInt("quantity"));
                    return o;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(BeerOrder order) {
        String sql = "INSERT INTO BeerOrder(customerId, customerName, beerName, quantity) VALUES(?,?,?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getCustomerId());
            ps.setString(2, order.getCustomerName());
            ps.setString(3, order.getBeerName());
            ps.setInt(4, order.getQuantity());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(BeerOrder order) {
        String sql = "UPDATE BeerOrder SET customerId=?, customerName=?, beerName=?, quantity=? WHERE orderId=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getCustomerId());
            ps.setString(2, order.getCustomerName());
            ps.setString(3, order.getBeerName());
            ps.setInt(4, order.getQuantity());
            ps.setInt(5, order.getOrderId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM BeerOrder WHERE orderId=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
