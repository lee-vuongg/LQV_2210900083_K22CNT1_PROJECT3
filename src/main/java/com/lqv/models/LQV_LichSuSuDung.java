package com.lqv.models;

import java.util.Date;

public class LQV_LichSuSuDung {
    private int LQVid;
    private int LQVid_vat_tu;
    private int LQVid_khach_hang;
    private Date LQV_ngay_su_dung;
    private int LQV_so_luong_su_dung; // Cập nhật tên trường
    private String LQV_nguoi_su_dung;
    private String LQV_ghi_chu;

    public LQV_LichSuSuDung() {
        this.LQV_ngay_su_dung = new Date();
        this.LQV_so_luong_su_dung = 1; // Đổi tên biến
    }

    public LQV_LichSuSuDung(int LQVid, int LQVid_vat_tu, int LQVid_khach_hang, String LQV_nguoi_su_dung, Date LQV_ngay_su_dung, int LQV_so_luong_su_dung, String LQV_ghi_chu) {
        this.LQVid = LQVid;
        this.LQVid_vat_tu = LQVid_vat_tu;
        this.LQVid_khach_hang = LQVid_khach_hang;
        this.LQV_nguoi_su_dung = LQV_nguoi_su_dung;
        this.LQV_ngay_su_dung = LQV_ngay_su_dung;
        this.LQV_so_luong_su_dung = LQV_so_luong_su_dung; // Đổi tên biến
        this.LQV_ghi_chu = LQV_ghi_chu;
    }

    public int getLQVid() {
        return LQVid;
    }

    public void setLQVid(int LQVid) {
        this.LQVid = LQVid;
    }

    public int getLQVid_vat_tu() {
        return LQVid_vat_tu;
    }

    public void setLQVid_vat_tu(int LQVid_vat_tu) {
        this.LQVid_vat_tu = LQVid_vat_tu;
    }

    public int getLQVid_khach_hang() {
        return LQVid_khach_hang;
    }

    public void setLQVid_khach_hang(int LQVid_khach_hang) {
        this.LQVid_khach_hang = LQVid_khach_hang;
    }

    public Date getLQV_ngay_su_dung() {
        return LQV_ngay_su_dung;
    }

    public void setLQV_ngay_su_dung(Date LQV_ngay_su_dung) {
        this.LQV_ngay_su_dung = LQV_ngay_su_dung;
    }

    public int getLQV_so_luong_su_dung() { // Cập nhật tên phương thức
        return LQV_so_luong_su_dung;
    }

    public void setLQV_so_luong_su_dung(int LQV_so_luong_su_dung) { // Cập nhật tên phương thức
        this.LQV_so_luong_su_dung = LQV_so_luong_su_dung;
    }

    public String getLQV_nguoi_su_dung() {
        return LQV_nguoi_su_dung;
    }

    public void setLQV_nguoi_su_dung(String LQV_nguoi_su_dung) {
        this.LQV_nguoi_su_dung = LQV_nguoi_su_dung;
    }

    public String getLQV_ghi_chu() {
        return LQV_ghi_chu;
    }

    public void setLQV_ghi_chu(String LQV_ghi_chu) {
        this.LQV_ghi_chu = LQV_ghi_chu;
    }

    // Kiểm tra xem số lượng sử dụng có hợp lệ không
    public boolean isValidUsage() {
        return this.LQV_so_luong_su_dung > 0; // Đổi tên biến
    }
}
