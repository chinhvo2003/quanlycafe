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
public class Datbanmodel {

    private int madb, maban, mahk;
    private String manv, trangthai;
    private Date ngaydat;

    public Datbanmodel() {
    }

    public Datbanmodel(int madb, int maban, int mahk, String manv, String trangthai, Date ngaydat) {
        this.madb = madb;
        this.maban = maban;
        this.mahk = mahk;
        this.manv = manv;
        this.trangthai = trangthai;
        this.ngaydat = ngaydat;
    }

    public int getMadb() {
        return madb;
    }

    public void setMadb(int madb) {
        this.madb = madb;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public int getMahk() {
        return mahk;
    }

    public void setMahk(int mahk) {
        this.mahk = mahk;
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

    public Date getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(Date ngaydat) {
        this.ngaydat = ngaydat;
    }

    @Override
    public String toString() {
        return "Datbanmodel{" + "madb=" + madb + ", maban=" + maban + ", mahk=" + mahk + ", manv=" + manv + ", trangthai=" + trangthai + ", ngaydat=" + ngaydat + '}';
    }
}
