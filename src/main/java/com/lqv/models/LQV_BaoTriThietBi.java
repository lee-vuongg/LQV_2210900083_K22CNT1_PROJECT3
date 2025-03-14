package com.lqv.models;

import java.util.Date;

public class LQV_BaoTriThietBi {
    private int LQVid;
    private int LQVid_vat_tu;
    private Date LQV_ngay_bao_tri;
    private String LQV_mo_ta;
    private double LQV_chi_phi;

    public LQV_BaoTriThietBi() {
        this.LQV_ngay_bao_tri = new Date();
        this.LQV_chi_phi = 0.0;
    }

    public LQV_BaoTriThietBi(int LQVid, int LQVid_vat_tu, Date LQV_ngay_bao_tri, String LQV_mo_ta, double LQV_chi_phi) {
        this.LQVid = LQVid;
        this.LQVid_vat_tu = LQVid_vat_tu;
        this.LQV_ngay_bao_tri = LQV_ngay_bao_tri;
        this.LQV_mo_ta = LQV_mo_ta;
        this.LQV_chi_phi = LQV_chi_phi;
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

    public Date getLQV_ngay_bao_tri() {
        return LQV_ngay_bao_tri;
    }

    public void setLQV_ngay_bao_tri(Date LQV_ngay_bao_tri) {
        this.LQV_ngay_bao_tri = LQV_ngay_bao_tri;
    }

    public String getLQV_mo_ta() {
        return LQV_mo_ta;
    }

    public void setLQV_mo_ta(String LQV_mo_ta) {
        this.LQV_mo_ta = LQV_mo_ta;
    }

    public double getLQV_chi_phi() {
        return LQV_chi_phi;
    }

    public void setLQV_chi_phi(double LQV_chi_phi) {
        this.LQV_chi_phi = LQV_chi_phi;
    }

    // Xử lý logic: Kiểm tra xem bảo trì có tốn chi phí không
    public boolean isFreeMaintenance() {
        return this.LQV_chi_phi == 0.0;
    }
}
