package com.lqv.models;

import java.sql.Date;

public class LQV_NhaCungCap {
    private int LQVid;
    private String LQV_ten_nha_cung_cap;
    private String LQV_dia_chi;
    private String LQV_so_dien_thoai;
    private String LQV_email;
    private Date LQV_ngay_sua;


    public LQV_NhaCungCap() {}

    public LQV_NhaCungCap(int LQVid, String LQV_ten_nha_cung_cap, String LQV_dia_chi, String LQV_so_dien_thoai, String LQV_email) {
        this.LQVid = LQVid;
        this.LQV_ten_nha_cung_cap = LQV_ten_nha_cung_cap;
        this.LQV_dia_chi = LQV_dia_chi;
        this.LQV_so_dien_thoai = LQV_so_dien_thoai;
        this.LQV_email = LQV_email;
    }

    public int getLQVid() {
        return LQVid;
    }

    public void setLQVid(int LQVid) {
        this.LQVid = LQVid;
    }
    
    public Date getLQV_ngay_sua() {
        return LQV_ngay_sua;
    }

    public void setLQV_ngay_sua(Date LQV_ngay_sua) {
        this.LQV_ngay_sua = LQV_ngay_sua;
    }
    
    public String getLQV_ten_nha_cung_cap() {
        return LQV_ten_nha_cung_cap;
    }

    public void setLQV_ten_nha_cung_cap(String LQV_ten_nha_cung_cap) {
        this.LQV_ten_nha_cung_cap = LQV_ten_nha_cung_cap;
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

    public String getLQV_email() {
        return LQV_email;
    }

    public void setLQV_email(String LQV_email) {
        this.LQV_email = LQV_email;
    }

    // Kiểm tra xem email của nhà cung cấp có hợp lệ không
    public boolean isValidEmail() {
        return this.LQV_email != null && this.LQV_email.contains("@");
    }
}
