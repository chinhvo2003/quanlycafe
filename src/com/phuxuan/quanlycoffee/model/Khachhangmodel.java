/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.model;

/**
 *
 * @author chinh
 */
public class Khachhangmodel {
    private int makh;
    private String hotenkh,diachi,sdt;

    public Khachhangmodel() {
    }

    public Khachhangmodel(int makh, String hotenkh, String diachi, String sdt) {
        this.makh = makh;
        this.hotenkh = hotenkh;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getHotenkh() {
        return hotenkh;
    }

    public void setHotenkh(String hotenkh) {
        this.hotenkh = hotenkh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "Khachhangmodel{" + "makh=" + makh + ", hotenkh=" + hotenkh + ", diachi=" + diachi + ", sdt=" + sdt + '}';
    }
    
    
}
