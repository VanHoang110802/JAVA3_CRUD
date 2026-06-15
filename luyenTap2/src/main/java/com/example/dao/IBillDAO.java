package com.example.dao;

import com.example.entity.Bill;

import java.util.ArrayList;

public interface IBillDAO {
    ArrayList<Bill> getAllDao();
    void insertDao(Bill bill);
    void updateDao(Bill bill);
    void deleteDao(String maDonHang);
    ArrayList<Bill> filterDao(String maDonHang, String loaiDoUong, String sizeDoUong);
}
