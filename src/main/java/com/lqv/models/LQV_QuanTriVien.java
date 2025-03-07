package com.lqv.models;

import java.util.Date;

public class LQV_QuanTriVien {
    private int LQVid;
    private String LQV_tai_khoan;
    private String LQV_mat_khau;
    private boolean LQV_trang_thai;
    private Date LQV_ngay_tao;
    private Date LQV_ngay_sua;

    public LQV_QuanTriVien() {}

    public LQV_QuanTriVien(int LQVid, String LQV_tai_khoan, String LQV_mat_khau, boolean LQV_trang_thai, Date LQV_ngay_tao, Date LQV_ngay_sua) {
        this.LQVid = LQVid;
        this.LQV_tai_khoan = LQV_tai_khoan;
        this.LQV_mat_khau = LQV_mat_khau;
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

    @Override
    public String toString() {
        return "LQV_QuanTriVien{" +
                "LQVid=" + LQVid +
                ", LQV_tai_khoan='" + LQV_tai_khoan + '\'' +
                ", LQV_mat_khau='" + LQV_mat_khau + '\'' +
                ", LQV_trang_thai=" + LQV_trang_thai +
                ", LQV_ngay_tao=" + LQV_ngay_tao +
                ", LQV_ngay_sua=" + LQV_ngay_sua +
                '}';
    }
}
