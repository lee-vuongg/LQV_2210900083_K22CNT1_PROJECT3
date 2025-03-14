package com.lqv.models;

import java.util.Date;

public class LQV_DonDatHang {
    private int LQVid;
    private Date LQV_ngay_dat_hang;
    private int LQVid_khach_hang;
    private int LQVid_nha_cung_cap;
    private double LQV_tong_tien;
    private String LQV_trang_thai;

    public LQV_DonDatHang() {
        this.LQV_ngay_dat_hang = new Date();
        this.LQV_trang_thai = "Chờ xử lý";
    }

    public LQV_DonDatHang(int LQVid, Date LQV_ngay_dat_hang, int LQVid_khach_hang, int LQVid_nha_cung_cap, double LQV_tong_tien, String LQV_trang_thai) {
        this.LQVid = LQVid;
        this.LQV_ngay_dat_hang = LQV_ngay_dat_hang;
        this.LQVid_khach_hang = LQVid_khach_hang;
        this.LQVid_nha_cung_cap = LQVid_nha_cung_cap;
        this.LQV_tong_tien = LQV_tong_tien;
        this.LQV_trang_thai = LQV_trang_thai;
    }

    public int getLQVid() {
        return LQVid;
    }

    public void setLQVid(int LQVid) {
        this.LQVid = LQVid;
    }

    public Date getLQV_ngay_dat_hang() {
        return LQV_ngay_dat_hang;
    }

    public void setLQV_ngay_dat_hang(Date LQV_ngay_dat_hang) {
        this.LQV_ngay_dat_hang = LQV_ngay_dat_hang;
    }

    public int getLQVid_khach_hang() {
        return LQVid_khach_hang;
    }

    public void setLQVid_khach_hang(int LQVid_khach_hang) {
        this.LQVid_khach_hang = LQVid_khach_hang;
    }

    public int getLQVid_nha_cung_cap() {
        return LQVid_nha_cung_cap;
    }

    public void setLQVid_nha_cung_cap(int LQVid_nha_cung_cap) {
        this.LQVid_nha_cung_cap = LQVid_nha_cung_cap;
    }

    public double getLQV_tong_tien() {
        return LQV_tong_tien;
    }

    public void setLQV_tong_tien(double LQV_tong_tien) {
        this.LQV_tong_tien = LQV_tong_tien;
    }

    public String getLQV_trang_thai() {
        return LQV_trang_thai;
    }

    public void setLQV_trang_thai(String LQV_trang_thai) {
        this.LQV_trang_thai = LQV_trang_thai;
    }

    // Xử lý logic: Kiểm tra xem đơn hàng đã được xử lý chưa
    public boolean isProcessed() {
        return this.LQV_trang_thai.equals("Đã xử lý") || this.LQV_trang_thai.equals("Đã giao");
    }

    // Xử lý logic: Cập nhật trạng thái đơn hàng
    public void updateStatus(String newStatus) {
        this.LQV_trang_thai = newStatus;
    }
}
