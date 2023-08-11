/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.model;

/**
 *
 * @author chinh
 */
public class Thongkesoluongbanmodel {
    private String tentd;
    private int slban;
    private Float thanhtoen;

    public Thongkesoluongbanmodel() {
    }

    

    public Thongkesoluongbanmodel(String tentd, int slban, Float thanhtoen) {
        this.tentd = tentd;
        this.slban = slban;
        this.thanhtoen = thanhtoen;
    }

    public String getTentd() {
        return tentd;
    }

    public void setTentd(String tentd) {
        this.tentd = tentd;
    }

    public int getSlban() {
        return slban;
    }

    public void setSlban(int slban) {
        this.slban = slban;
    }

    public Float getThanhtoen() {
        return thanhtoen;
    }

    public void setThanhtoen(Float thanhtoen) {
        this.thanhtoen = thanhtoen;
    }

   
    
    
}
