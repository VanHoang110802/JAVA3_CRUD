package com.example.entity;

import java.time.LocalDateTime;

public class Bill {
    private String maDonHang;
    private String emailNhanHoaDon;
    private LocalDateTime ngayDatHang;
    private String loaiDoUong;
    private String sizeDoUong;
    private int soCocDat;

    public Bill() {
    }

    public Bill(String maDonHang, String emailNhanHoaDon, LocalDateTime ngayDatHang, String loaiDoUong, String sizeDoUong, int soCocDat) {
        this.maDonHang = maDonHang;
        this.emailNhanHoaDon = emailNhanHoaDon;
        this.ngayDatHang = ngayDatHang;
        this.loaiDoUong = loaiDoUong;
        this.sizeDoUong = sizeDoUong;
        this.soCocDat = soCocDat;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getEmailNhanHoaDon() {
        return emailNhanHoaDon;
    }

    public void setEmailNhanHoaDon(String emailNhanHoaDon) {
        this.emailNhanHoaDon = emailNhanHoaDon;
    }

    public LocalDateTime getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(LocalDateTime ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public String getLoaiDoUong() {
        return loaiDoUong;
    }

    public void setLoaiDoUong(String loaiDoUong) {
        this.loaiDoUong = loaiDoUong;
    }

    public String getSizeDoUong() {
        return sizeDoUong;
    }

    public void setSizeDoUong(String sizeDoUong) {
        this.sizeDoUong = sizeDoUong;
    }

    public int getSoCocDat() {
        return soCocDat;
    }

    public void setSoCocDat(int soCocDat) {
        this.soCocDat = soCocDat;
    }
}
