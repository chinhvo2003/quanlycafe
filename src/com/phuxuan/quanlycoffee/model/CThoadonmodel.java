/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.model;

/**
 *
 * @author chinh
 */
public class CThoadonmodel {

    private int mact, mahd, matd, soluongban;
    private Float thanhtien, giaban;
    private String tentd;

    public CThoadonmodel() {
    }

    public CThoadonmodel(int mact, int mahd, int matd, int soluongban, Float thanhtien, Float giaban) {
        this.mact = mact;
        this.mahd = mahd;
        this.matd = matd;
        this.soluongban = soluongban;
        this.thanhtien = thanhtien;
        this.giaban = giaban;
    }

    public CThoadonmodel(String tentd) {
        this.tentd = tentd;
    }

    public int getMact() {
        return mact;
    }

    public void setMact(int mact) {
        this.mact = mact;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getMatd() {
        return matd;
    }

    public void setMatd(int matd) {
        this.matd = matd;
    }

    public int getSoluongban() {
        return soluongban;
    }

    public void setSoluongban(int soluongban) {
        this.soluongban = soluongban;
    }

    public Float getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Float thanhtien) {
        this.thanhtien = thanhtien;
    }

    public Float getGiaban() {
        return giaban;
    }

    public void setGiaban(Float giaban) {
        this.giaban = giaban;
    }

    public String getTentd() {
        return tentd;
    }

    public void setTentd(String tentd) {
        this.tentd = tentd;
    }

    @Override
    public String toString() {
        return "CThoadonmodel{" + "mact=" + mact + ", mahd=" + mahd + ", matd=" + matd + ", soluongban=" + soluongban + ", thanhtien=" + thanhtien + ", giaban=" + giaban + '}';
    }

}
