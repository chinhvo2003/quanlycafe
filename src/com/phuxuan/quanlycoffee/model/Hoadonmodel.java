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
public class Hoadonmodel {
    private int mahd,maban,makh;
    private String manv,trangthai;
    private Date ngayban;
    private Double tongtien;

  

    public Hoadonmodel() {
    }

    public Hoadonmodel(int mahd, int maban, int makh, String manv, String trangthai, Date ngayban, Double tongtien) {
        this.mahd = mahd;
        this.maban = maban;
        this.makh = makh;
        this.manv = manv;
        this.trangthai = trangthai;
        this.ngayban = ngayban;
        this.tongtien = tongtien;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public Date getNgayban() {
        return ngayban;
    }

    public void setNgayban(Date ngayban) {
        this.ngayban = ngayban;
    }

    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }

    
    @Override
    public String toString() {
        return "Hoadonmodel{" + "mahd=" + mahd + ", maban=" + maban + ", makh=" + makh + ", manv=" + manv + ", trangthai=" + trangthai + ", ngayban=" + ngayban + ", tongtien=" + tongtien + '}';
    }

   
    
    
}
