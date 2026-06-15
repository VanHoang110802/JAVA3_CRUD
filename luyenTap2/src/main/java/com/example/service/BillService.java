package com.example.service;

import com.example.dao.BillDAO;
import com.example.entity.Bill;

import java.util.ArrayList;

public class BillService {
    private BillDAO billDAO;

    public BillService() {
        this.billDAO = new BillDAO();
    }

    public ArrayList<Bill> getAllBills() {
        return billDAO.getAllDao();
    }

    public void addBill(Bill bill) {
        billDAO.insertDao(bill);
    }

    public void updateBill(Bill bill) {
        billDAO.updateDao(bill);
    }

    public void deleteBill(String maDonHang) {
        billDAO.deleteDao(maDonHang);
    }

    public ArrayList<Bill> filterBills(String maDonHang, String loaiDoUong, String sizeDoUong) {
        return billDAO.filterDao(maDonHang, loaiDoUong, sizeDoUong);
    }
}
