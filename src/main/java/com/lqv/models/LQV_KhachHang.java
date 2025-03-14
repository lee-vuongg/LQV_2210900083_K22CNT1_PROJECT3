package com.lqv.models;

import java.util.Date;

public class LQV_KhachHang {
    private int LQVid;
    private String LQV_tai_khoan;
    private String LQV_mat_khau;
    private String LQV_ho_ten;
    private String LQV_email;
    private String LQV_dia_chi;
    private String LQV_so_dien_thoai;
    private boolean LQV_trang_thai;
    private Date LQV_ngay_tao;
    private Date LQV_ngay_sua;

    public LQV_KhachHang() {
        this.LQV_ngay_tao = new Date();
        this.LQV_trang_thai = true;
    }

    public LQV_KhachHang(int LQVid, String LQV_tai_khoan, String LQV_mat_khau, String LQV_ho_ten, String LQV_email, String LQV_dia_chi, String LQV_so_dien_thoai, boolean LQV_trang_thai, Date LQV_ngay_tao, Date LQV_ngay_sua) {
        this.LQVid = LQVid;
        this.LQV_tai_khoan = LQV_tai_khoan;
        this.LQV_mat_khau = LQV_mat_khau;
        this.LQV_ho_ten = LQV_ho_ten;
        this.LQV_email = LQV_email;
        this.LQV_dia_chi = LQV_dia_chi;
        this.LQV_so_dien_thoai = LQV_so_dien_thoai;
        this.LQV_trang_thai = LQV_trang_thai;
        this.LQV_ngay_tao = LQV_ngay_tao;
        this.LQV_ngay_sua = LQV_ngay_sua;
    }

    public int getLQVid() {
        return LQVid;
    }

    public void setLQVid(int LQVid) {
        this.LQVid = LQVid;
    }

    public String getLQV_tai_khoan() {
        return LQV_tai_khoan;
    }

    public void setLQV_tai_khoan(String LQV_tai_khoan) {
        this.LQV_tai_khoan = LQV_tai_khoan;
    }

    public String getLQV_mat_khau() {
        return LQV_mat_khau;
    }

    public void setLQV_mat_khau(String LQV_mat_khau) {
        this.LQV_mat_khau = LQV_mat_khau;
    }

    public String getLQV_ho_ten() {
        return LQV_ho_ten;
    }

    public void setLQV_ho_ten(String LQV_ho_ten) {
        this.LQV_ho_ten = LQV_ho_ten;
    }

    public String getLQV_email() {
        return LQV_email;
    }

    public void setLQV_email(String LQV_email) {
        this.LQV_email = LQV_email;
    }

    public String getLQV_dia_chi() {
        return LQV_dia_chi;
    }

    public void setLQV_dia_chi(String LQV_dia_chi) {
        this.LQV_dia_chi = LQV_dia_chi;
    }

    public String getLQV_so_dien_thoai() {
        return LQV_so_dien_thoai;
    }

    public void setLQV_so_dien_thoai(String LQV_so_dien_thoai) {
        this.LQV_so_dien_thoai = LQV_so_dien_thoai;
    }

    public boolean isLQV_trang_thai() {
        return LQV_trang_thai;
    }

    public void setLQV_trang_thai(boolean LQV_trang_thai) {
        this.LQV_trang_thai = LQV_trang_thai;
    }

    public Date getLQV_ngay_tao() {
        return LQV_ngay_tao;
    }

    public void setLQV_ngay_tao(Date LQV_ngay_tao) {
        this.LQV_ngay_tao = LQV_ngay_tao;
    }

    public Date getLQV_ngay_sua() {
        return LQV_ngay_sua;
    }

    public void setLQV_ngay_sua(Date LQV_ngay_sua) {
        this.LQV_ngay_sua = LQV_ngay_sua;
    }

    // Kiểm tra email hợp lệ
    public boolean isValidEmail() {
        return this.LQV_email != null && this.LQV_email.contains("@");
    }

    // Kiểm tra số điện thoại hợp lệ
    public boolean isValidPhone() {
        return this.LQV_so_dien_thoai != null && this.LQV_so_dien_thoai.matches("\\d{10}");
    }
}
