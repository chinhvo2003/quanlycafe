/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.model;

/**
 *
 * @author chinh
 */
public class Banmodel {
    private int maban,soghe;
    private String trangthai;

    public Banmodel() {
    }

    public Banmodel(int maban, int soghe, String trangthai) {
        this.maban = maban;
        this.soghe = soghe;
        this.trangthai = trangthai;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public int getSoghe() {
        return soghe;
    }

    public void setSoghe(int soghe) {
        this.soghe = soghe;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "Banmodel{" + "maban=" + maban + ", soghe=" + soghe + ", trangthai=" + trangthai + '}';
    }
    
    
}
