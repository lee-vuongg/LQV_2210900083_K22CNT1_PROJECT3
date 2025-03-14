package com.lqv.models;

public class LQV_Kho {
    private int LQVid;
    private String LQV_ten_kho;
    private String LQV_dia_chi;
    private String LQV_nguoi_quan_ly;
    private int LQV_suc_chua;

    public LQV_Kho() {}

    public LQV_Kho(int LQVid, String LQV_ten_kho, String LQV_dia_chi, String LQV_nguoi_quan_ly, int LQV_suc_chua) {
        this.LQVid = LQVid;
        this.LQV_ten_kho = LQV_ten_kho;
        this.LQV_dia_chi = LQV_dia_chi;
        this.LQV_nguoi_quan_ly = LQV_nguoi_quan_ly;
        this.LQV_suc_chua = LQV_suc_chua;
    }

    public int getLQVid() {
        return LQVid;
    }

    public void setLQVid(int LQVid) {
        this.LQVid = LQVid;
    }

    public String getLQV_ten_kho() {
        return LQV_ten_kho;
    }

    public void setLQV_ten_kho(String LQV_ten_kho) {
        this.LQV_ten_kho = LQV_ten_kho;
    }

    public String getLQV_dia_chi() {
        return LQV_dia_chi;
    }

    public void setLQV_dia_chi(String LQV_dia_chi) {
        this.LQV_dia_chi = LQV_dia_chi;
    }

    public String getLQV_nguoi_quan_ly() {
        return LQV_nguoi_quan_ly;
    }

    public void setLQV_nguoi_quan_ly(String LQV_nguoi_quan_ly) {
        this.LQV_nguoi_quan_ly = LQV_nguoi_quan_ly;
    }

    public int getLQV_suc_chua() {
        return LQV_suc_chua;
    }

    public void setLQV_suc_chua(int LQV_suc_chua) {
        this.LQV_suc_chua = LQV_suc_chua;
    }

    // Kiểm tra xem kho có đầy chưa
    public boolean isFull(int currentStock) {
        return currentStock >= this.LQV_suc_chua;
    }
}
