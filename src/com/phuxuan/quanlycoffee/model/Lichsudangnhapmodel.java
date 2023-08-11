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
public class Lichsudangnhapmodel {

    private int id;
    private String manv;
    private Date ngaydangnhap;

    public Lichsudangnhapmodel() {
    }

    public Lichsudangnhapmodel(int id, String manv, Date ngaydangnhap) {
        this.id = id;
        this.manv = manv;
        this.ngaydangnhap = ngaydangnhap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public Date getNgaydangnhap() {
        return ngaydangnhap;
    }

    public void setNgaydangnhap(Date ngaydangnhap) {
        this.ngaydangnhap = ngaydangnhap;
    }

}
