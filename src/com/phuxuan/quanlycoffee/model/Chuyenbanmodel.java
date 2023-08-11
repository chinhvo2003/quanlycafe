/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.model;

import java.sql.Date;

/**
 *
 * @author chinh
 */
public class Chuyenbanmodel {
    private int macb,mabanmoi,mabancu,mahd;
    private String manv,lydo;
    private Date ngaychuyen;

    public Chuyenbanmodel() {
    }

    public Chuyenbanmodel(int macb, int mabanmoi, int mabancu, int mahd, String manv, String lydo, Date ngaychuyen) {
        this.macb = macb;
        this.mabanmoi = mabanmoi;
        this.mabancu = mabancu;
        this.mahd = mahd;
        this.manv = manv;
        this.lydo = lydo;
        this.ngaychuyen = ngaychuyen;
    }

    public int getMacb() {
        return macb;
    }

    public void setMacb(int macb) {
        this.macb = macb;
    }

    public int getMabanmoi() {
        return mabanmoi;
    }

    public void setMabanmoi(int mabanmoi) {
        this.mabanmoi = mabanmoi;
    }

    public int getMabancu() {
        return mabancu;
    }

    public void setMabancu(int mabancu) {
        this.mabancu = mabancu;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public Date getNgaychuyen() {
        return ngaychuyen;
    }

    public void setNgaychuyen(Date ngaychuyen) {
        this.ngaychuyen = ngaychuyen;
    }

    @Override
    public String toString() {
        return "Chuyenbanmodel{" + "macb=" + macb + ", mabanmoi=" + mabanmoi + ", mabancu=" + mabancu + ", mahd=" + mahd + ", manv=" + manv + ", lydo=" + lydo + ", ngaychuyen=" + ngaychuyen + '}';
    }
    
    
}
