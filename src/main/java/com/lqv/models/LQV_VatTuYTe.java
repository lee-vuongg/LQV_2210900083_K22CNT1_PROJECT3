package com.lqv.models;

import java.util.Date;

public class LQV_VatTuYTe {
    private int LQVid;
    private String LQV_ten_vat_tu;
    private String LQV_ma_vat_tu;
    private int LQV_so_luong;
    private double LQV_don_gia;
    private Date LQV_ngay_het_han;
    private String LQV_mo_ta;
    private Date LQV_ngay_tao;  // Thêm ngày tạo
    private Date LQV_ngay_sua;  // Thêm ngày sửa

    public LQV_VatTuYTe() {}

    public LQV_VatTuYTe(int LQVid, String LQV_ten_vat_tu, String LQV_ma_vat_tu, int LQV_so_luong, double LQV_don_gia, Date LQV_ngay_het_han, String LQV_mo_ta, Date LQV_ngay_tao) {
        this.LQVid = LQVid;
        this.LQV_ten_vat_tu = LQV_ten_vat_tu;
        this.LQV_ma_vat_tu = LQV_ma_vat_tu;
        this.LQV_so_luong = LQV_so_luong;
        this.LQV_don_gia = LQV_don_gia;
        this.LQV_ngay_het_han = LQV_ngay_het_han;
        this.LQV_mo_ta = LQV_mo_ta;
        this.LQV_ngay_tao = LQV_ngay_tao;
        this.LQV_ngay_sua = null; // Mặc định chưa có ngày sửa
    }

    public int getLQVid() {
        return LQVid;
    }

    public void setLQVid(int LQVid) {
        this.LQVid = LQVid;
    }

    public String getLQV_ten_vat_tu() {
        return LQV_ten_vat_tu;
    }

    public void setLQV_ten_vat_tu(String LQV_ten_vat_tu) {
        this.LQV_ten_vat_tu = LQV_ten_vat_tu;
    }

    public String getLQV_ma_vat_tu() {
        return LQV_ma_vat_tu;
    }

    public void setLQV_ma_vat_tu(String LQV_ma_vat_tu) {
        this.LQV_ma_vat_tu = LQV_ma_vat_tu;
    }

    public int getLQV_so_luong() {
        return LQV_so_luong;
    }

    public void setLQV_so_luong(int LQV_so_luong) {
        this.LQV_so_luong = LQV_so_luong;
    }

    public double getLQV_don_gia() {
        return LQV_don_gia;
    }

    public void setLQV_don_gia(double LQV_don_gia) {
        this.LQV_don_gia = LQV_don_gia;
    }

    public Date getLQV_ngay_het_han() {
        return LQV_ngay_het_han;
    }

    public void setLQV_ngay_het_han(Date LQV_ngay_het_han) {
        this.LQV_ngay_het_han = LQV_ngay_het_han;
    }

    public String getLQV_mo_ta() {
        return LQV_mo_ta;
    }

    public void setLQV_mo_ta(String LQV_mo_ta) {
        this.LQV_mo_ta = LQV_mo_ta;
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

    // Cập nhật ngày sửa
    public void updateNgaySua() {
        this.LQV_ngay_sua = new Date();
    }

    // Kiểm tra xem vật tư đã hết hạn chưa
    public boolean isExpired() {
        return this.LQV_ngay_het_han.before(new Date());
    }

    // Tính tổng giá trị của vật tư (số lượng * đơn giá)
    public double getTotalValue() {
        return this.LQV_so_luong * this.LQV_don_gia;
    }
}
