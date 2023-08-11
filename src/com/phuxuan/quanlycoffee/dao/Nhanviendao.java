/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.dao;

import com.phuxuan.quanlycoffee.model.Nhanvienmodel;
import com.phuxuan.quanlyquancafe.connectJDBC.Databaseee;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author chinh
 */
public class Nhanviendao {

    public Nhanvienmodel checkLogin(String tendangnhap, String matkhau) throws SQLException, ClassNotFoundException {
        String sql = "select hotennv,tendangnhap,matkhau,vaitro from NHAN_VIEN"
                + " where tendangnhap=? and matkhau=?";
        try (
                Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, tendangnhap);
            prstt.setString(2, matkhau);
            try (ResultSet rs = prstt.executeQuery();) {
                if (rs.next()) {
                    Nhanvienmodel nd = new Nhanvienmodel();
                    nd.setTendangnhap(tendangnhap);
                    nd.setVaitro(rs.getString("vaitro"));
                    nd.setHotennv(rs.getString("hotennv"));
                    return nd;
                }
            }
        }
        return null;
    }

    public static ArrayList<Nhanvienmodel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Nhanvienmodel> lst = new ArrayList<>();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Connection conn = Databaseee.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from NHAN_VIEN ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Nhanvienmodel s = new Nhanvienmodel();
                s.setManv(rs.getString(1));
                s.setHotennv(rs.getString(2));
                s.setNgaysinh(rs.getDate(3));
                s.setGioitinh(rs.getString(4));
                s.setLuong(rs.getFloat(5));
                s.setSdt(rs.getString(6));
                s.setDiachi(rs.getString(7));
                s.setTendangnhap(rs.getString(8));
                s.setMatkhau(rs.getString(9));
                s.setVaitro(rs.getString(10));
                lst.add(s);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lst;
    }

    public boolean insert(Nhanvienmodel bnv) throws SQLException, ClassNotFoundException {
        String sql = "insert into NHAN_VIEN (manv, hotennv, ngaysinh, gioitinh, luong, sdt, diachi,tendangnhap,matkhau,vaitro,anhcancuoc) values(?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = Databaseee.getConnection();
        PreparedStatement prst = conn.prepareStatement(sql);

        {
            prst.setString(1, bnv.getManv());
            prst.setString(2, bnv.getHotennv());
            prst.setDate(3, bnv.getNgaysinh());
            prst.setString(4, bnv.getGioitinh());
            prst.setFloat(5, bnv.getLuong());
            prst.setString(6, bnv.getSdt());
            prst.setString(7, bnv.getDiachi());
            prst.setString(8, bnv.getTendangnhap());
            prst.setString(9, bnv.getMatkhau());
            prst.setString(10, bnv.getVaitro());
            if (bnv.getAnhcancuoc() != null) {
                Blob hinh = new SerialBlob(bnv.getAnhcancuoc());
                prst.setBlob(11, hinh);
            } else {
                Blob hinh = null;
                prst.setBlob(11, hinh);
            }
            return prst.executeUpdate() > 0;
        }
    }

    public boolean update(Nhanvienmodel bnv) throws SQLException, ClassNotFoundException {
        String sql = "update NHAN_VIEN set hotennv=?, ngaysinh=?, gioitinh=?, luong=?, sdt=?, diachi=?,tendangnhap=?,vaitro=?,anhcancuoc=?"
                + " where manv=?";
        Connection conn = Databaseee.getConnection();
        PreparedStatement prst = conn.prepareStatement(sql);

        {
            prst.setString(10, bnv.getManv());
            prst.setString(1, bnv.getHotennv());
            prst.setDate(2, bnv.getNgaysinh());
            prst.setString(3, bnv.getGioitinh());
            prst.setFloat(4, bnv.getLuong());
            prst.setString(5, bnv.getSdt());
            prst.setString(6, bnv.getDiachi());
            prst.setString(7, bnv.getTendangnhap());
            prst.setString(8, bnv.getVaitro());
            if (bnv.getAnhcancuoc() != null) {
                Blob hinh = new SerialBlob(bnv.getAnhcancuoc());
                prst.setBlob(9, hinh);
            } else {
                Blob hinh = null;
                prst.setBlob(9, hinh);
            }
            return prst.executeUpdate() > 0;
        }
    }

    public boolean delete(Nhanvienmodel bnv) throws SQLException, ClassNotFoundException {
        String sql = "delete from NHAN_VIEN"
                + " where manv=?";
        Connection conn = Databaseee.getConnection();
        PreparedStatement prst = conn.prepareStatement(sql);

        {
            prst.setString(1, bnv.getManv());
            return prst.executeUpdate() > 0;
        }
    }

    public Nhanvienmodel FindManv(String mamn) throws SQLException, ClassNotFoundException {
        String sql = "select * from NHAN_VIEN where manv=?";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setString(1, mamn);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    Nhanvienmodel bk = new Nhanvienmodel();
                    bk.setManv(rs.getString("manv"));
                    bk.setHotennv(rs.getString("hotennv"));
                    bk.setNgaysinh(rs.getDate("ngaysinh"));
                    bk.setGioitinh(rs.getString("gioitinh"));
                    bk.setLuong(rs.getFloat("luong"));
                    bk.setSdt(rs.getString("sdt"));
                    bk.setDiachi(rs.getString("diachi"));
                    bk.setTendangnhap(rs.getString("tendangnhap"));
                    bk.setVaitro(rs.getString("vaitro"));
                    Blob blob = rs.getBlob("anhcancuoc");
                    if (blob != null) {
                        bk.setAnhcancuoc(blob.getBytes(1, (int) blob.length()));
                    }
                    return bk;
                }
            }
            return null;
        }
    }
}
