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
public class Nhanvienmodel {

    private String manv, hotennv, gioitinh, sdt, diachi, tendangnhap, matkhau, vaitro;
    private Date ngaysinh;
    private Float luong;
    private byte[] anhcancuoc;

    public Nhanvienmodel() {
    }

    public Nhanvienmodel(String manv, String hotennv, String gioitinh, String sdt, String diachi, String tendangnhap, String matkhau, String vaitro, Date ngaysinh, Float luong, byte[] anhcancuoc) {
        this.manv = manv;
        this.hotennv = hotennv;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
        this.ngaysinh = ngaysinh;
        this.luong = luong;
        this.anhcancuoc = anhcancuoc;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHotennv() {
        return hotennv;
    }

    public void setHotennv(String hotennv) {
        this.hotennv = hotennv;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public Float getLuong() {
        return luong;
    }

    public void setLuong(Float luong) {
        this.luong = luong;
    }

    public byte[] getAnhcancuoc() {
        return anhcancuoc;
    }

    public void setAnhcancuoc(byte[] anhcancuoc) {
        this.anhcancuoc = anhcancuoc;
    }

    @Override
    public String toString() {
        return "Nhanvienmodel{" + "manv=" + manv + ", hotennv=" + hotennv + ", gioitinh=" + gioitinh + ", sdt=" + sdt + ", diachi=" + diachi + ", tendangnhap=" + tendangnhap + ", matkhau=" + matkhau + ", vaitro=" + vaitro + ", ngaysinh=" + ngaysinh + ", luong=" + luong + ", anhcancuoc=" + anhcancuoc + '}';
    }

}
