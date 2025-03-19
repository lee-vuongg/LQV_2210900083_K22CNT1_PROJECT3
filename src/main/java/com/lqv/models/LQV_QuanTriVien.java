package com.lqv.models;

import java.util.Date;

public class LQV_QuanTriVien {
    private int LQVid;
    private String LQV_tai_khoan;
    private String LQV_mat_khau;
    private boolean LQV_trang_thai;
    private Date LQV_ngay_tao;
    private Date LQV_ngay_sua;
    private String role;  // "admin" hoặc "khach"
	private String LQV_role;


    public LQV_QuanTriVien() {
        this.LQV_ngay_tao = new Date();
        this.LQV_trang_thai = true;
    }

    public LQV_QuanTriVien(int LQVid, String LQV_tai_khoan, String LQV_mat_khau, boolean LQV_trang_thai, Date LQV_ngay_tao, Date LQV_ngay_sua) {
        this.LQVid = LQVid;
        this.LQV_tai_khoan = LQV_tai_khoan;
        this.LQV_mat_khau = LQV_mat_khau;
        this.LQV_trang_thai = LQV_trang_thai;
        this.LQV_ngay_tao = LQV_ngay_tao;
        this.LQV_ngay_sua = LQV_ngay_sua;
    }

    // ✅ Getter và Setter cho role
    public String getLQV_role() {
        return LQV_role;
    }

    public void setLQV_role(String LQV_role) {
        this.LQV_role = LQV_role;
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
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    // Xử lý logic: Hash mật khẩu trước khi lưu
    public void hashPassword() {
        this.LQV_mat_khau = Integer.toHexString(this.LQV_mat_khau.hashCode());
    }

    // Xử lý logic: Kiểm tra tài khoản hợp lệ
    public boolean isValidAccount() {
        return this.LQV_tai_khoan != null && !this.LQV_tai_khoan.trim().isEmpty();
    }
}
