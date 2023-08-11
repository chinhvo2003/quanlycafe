/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.model;

/**
 *
 * @author chinh
 */
public class Thucdonmodel {
    private int matd;
    private String tenmb,dtv;
    private Float dongia;
    private byte[] anhtd;
    public Thucdonmodel() {
    }

    public Thucdonmodel(int matd, String tenmb, String dtv, Float dongia, byte[] anhtd) {
        this.matd = matd;
        this.tenmb = tenmb;
        this.dtv = dtv;
        this.dongia = dongia;
        this.anhtd = anhtd;
    }

    public int getMatd() {
        return matd;
    }

    public void setMatd(int matd) {
        this.matd = matd;
    }

    public String getTenmb() {
        return tenmb;
    }

    public void setTenmb(String tenmb) {
        this.tenmb = tenmb;
    }

    public String getDtv() {
        return dtv;
    }

    public void setDtv(String dtv) {
        this.dtv = dtv;
    }

    public Float getDongia() {
        return dongia;
    }

    public void setDongia(Float dongia) {
        this.dongia = dongia;
    }

    public byte[] getAnhtd() {
        return anhtd;
    }

    public void setAnhtd(byte[] anhtd) {
        this.anhtd = anhtd;
    }

    @Override
    public String toString() {
        return "Thucdonmodel{" + "matd=" + matd + ", tenmb=" + tenmb + ", dtv=" + dtv + ", dongia=" + dongia + ", anhtd=" + anhtd + '}';
    }

    
}
