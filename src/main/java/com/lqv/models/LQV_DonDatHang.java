package com.lqv.models;

import java.sql.Timestamp;

public class LQV_DonDatHang {
    private int LQVid;
    private Timestamp LQV_ngay_dat_hang; // Sửa từ Date -> Timestamp
    private int LQVid_nha_cung_cap;
    private double LQV_tong_tien;
    private String LQV_tinh_trang;

    public LQV_DonDatHang() {
        this.LQV_ngay_dat_hang = new Timestamp(System.currentTimeMillis()); // Lấy thời gian hiện tại
        this.LQV_tinh_trang = "Chờ xử lý";
    }

    public LQV_DonDatHang(int LQVid, Timestamp LQV_ngay_dat_hang, int LQVid_nha_cung_cap, double LQV_tong_tien, String LQV_tinh_trang) {
        this.LQVid = LQVid;
        this.LQV_ngay_dat_hang = LQV_ngay_dat_hang;
        this.LQVid_nha_cung_cap = LQVid_nha_cung_cap;
        this.LQV_tong_tien = LQV_tong_tien;
        this.LQV_tinh_trang = LQV_tinh_trang;
    }

    public int getLQVid() {
        return LQVid;
    }

    public void setLQVid(int LQVid) {
        this.LQVid = LQVid;
    }

    public Timestamp getLQV_ngay_dat_hang() { // Sửa kiểu dữ liệu trả về
        return LQV_ngay_dat_hang;
    }

    public void setLQV_ngay_dat_hang(Timestamp LQV_ngay_dat_hang) {
        this.LQV_ngay_dat_hang = LQV_ngay_dat_hang;
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

    public String getLQV_tinh_trang() {
        return LQV_tinh_trang;
    }

    public void setLQV_tinh_trang(String LQV_tinh_trang) {
        this.LQV_tinh_trang = LQV_tinh_trang;
    }

    // Kiểm tra đơn hàng đã duyệt chưa
    public boolean isProcessed() {
        return this.LQV_tinh_trang.equals("Đã duyệt") || this.LQV_tinh_trang.equals("Đã giao");
    }

    // Cập nhật trạng thái đơn hàng
    public void updateStatus(String newStatus) {
        this.LQV_tinh_trang = newStatus;
    }
}
