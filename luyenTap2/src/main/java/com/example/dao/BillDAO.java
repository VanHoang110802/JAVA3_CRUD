package com.example.dao;

import com.example.entity.Bill;
import com.example.jdbc.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO implements IBillDAO{
    @Override
    public ArrayList<Bill> getAllDao(){
        ArrayList<Bill> listBill = new ArrayList<>();
        String sql = "SELECT maDonHang, emailNhanHoaDon, ngayDatHang, loaiDoUong, sizeDoUong, soCocDat FROM Bill";
        try(Connection conn = DBConnect.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();){

            while(rs.next()){
                Bill bill = new Bill();
                bill.setMaDonHang(rs.getString("maDonHang"));
                bill.setEmailNhanHoaDon(rs.getString("emailNhanHoaDon"));
                bill.setNgayDatHang(rs.getTimestamp("ngayDatHang").toLocalDateTime()); // Timestamp -> LocalDateTime
                bill.setLoaiDoUong(rs.getString("loaiDoUong"));
                bill.setSizeDoUong(rs.getString("sizeDoUong"));
                bill.setSoCocDat(rs.getInt("soCocDat"));
                listBill.add(bill);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listBill;
    }

    @Override
    public void insertDao(Bill bill){
        String sql = "INSERT INTO Bill(maDonHang, emailNhanHoaDon, ngayDatHang, loaiDoUong, sizeDoUong, soCocDat) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bill.getMaDonHang());
            ps.setString(2, bill.getEmailNhanHoaDon());
            ps.setTimestamp(3, Timestamp.valueOf(bill.getNgayDatHang())); // LocalDateTime -> Timestamp
            ps.setString(4, bill.getLoaiDoUong());
            ps.setString(5, bill.getSizeDoUong());
            ps.setInt(6, bill.getSoCocDat());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDao(Bill bill){
        String sql = "UPDATE Bill SET emailNhanHoaDon=?, ngayDatHang=?, loaiDoUong=?, sizeDoUong=?, soCocDat=? WHERE maDonHang=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bill.getEmailNhanHoaDon());
            ps.setTimestamp(2, Timestamp.valueOf(bill.getNgayDatHang()));
            ps.setString(3, bill.getLoaiDoUong());
            ps.setString(4, bill.getSizeDoUong());
            ps.setInt(5, bill.getSoCocDat());
            ps.setString(6, bill.getMaDonHang());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDao(String maDonHang){
        String sql = "DELETE FROM Bill WHERE maDonHang = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maDonHang);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bill findByIdDao(String maDonHang) {
        Bill b = null;
        String sql = "SELECT maDonHang, emailNhanHoaDon, ngayDatHang, loaiDoUong, sizeDoUong, soCocDat FROM Bill WHERE maDonHang = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maDonHang);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    b = new Bill();
                    b.setMaDonHang(rs.getString("maDonHang"));
                    b.setEmailNhanHoaDon(rs.getString("emailNhanHoaDon"));
                    b.setNgayDatHang(rs.getTimestamp("ngayDatHang").toLocalDateTime());
                    b.setLoaiDoUong(rs.getString("loaiDoUong"));
                    b.setSizeDoUong(rs.getString("sizeDoUong"));
                    b.setSoCocDat(rs.getInt("soCocDat"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public ArrayList<Bill> filterDao(String maDonHang, String loaiDoUong, String sizeDoUong){
        ArrayList<Bill> listBill = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT maDonHang, emailNhanHoaDon, ngayDatHang, loaiDoUong, sizeDoUong, soCocDat FROM Bill WHERE 1=1");

        if (maDonHang != null && !maDonHang.isEmpty()) {
            sql.append(" AND maDonHang = ?");
        }
        if (loaiDoUong != null && !loaiDoUong.isEmpty()) {
            sql.append(" AND loaiDoUong = ?");
        }
        if (sizeDoUong != null && !sizeDoUong.isEmpty()) {
            sql.append(" AND sizeDoUong = ?");
        }

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (maDonHang != null && !maDonHang.isEmpty()) {
                ps.setString(index++, maDonHang.trim());
            }
            if (loaiDoUong != null && !loaiDoUong.isEmpty()) {
                ps.setString(index++, loaiDoUong.trim());
            }
            if (sizeDoUong != null && !sizeDoUong.isEmpty()) {
                ps.setString(index++, sizeDoUong.trim());
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Bill b = new Bill();
                    b.setMaDonHang(rs.getString("maDonHang"));
                    b.setEmailNhanHoaDon(rs.getString("emailNhanHoaDon"));
                    b.setNgayDatHang(rs.getTimestamp("ngayDatHang").toLocalDateTime());
                    b.setLoaiDoUong(rs.getString("loaiDoUong"));
                    b.setSizeDoUong(rs.getString("sizeDoUong"));
                    b.setSoCocDat(rs.getInt("soCocDat"));
                    listBill.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBill;
    }
}
