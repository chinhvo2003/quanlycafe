/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.dao;

import com.phuxuan.quanlycoffee.model.Khachhangmodel;
import com.phuxuan.quanlycoffee.model.Thucdonmodel;
import com.phuxuan.quanlyquancafe.connectJDBC.Databaseee;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author chinh
 */
public class Khachhangdao {

    public static ArrayList<Khachhangmodel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Khachhangmodel> lst = new ArrayList<>();
        Connection conn = Databaseee.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from KHACH_HANG ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Khachhangmodel t = new Khachhangmodel();
                t.setMakh(rs.getInt(1));
                t.setHotenkh(rs.getString(2));
                t.setDiachi(rs.getString(3));
                t.setSdt(rs.getString(4));
                lst.add(t);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lst;
    }

    public Khachhangmodel FindManv(int makh) throws SQLException, ClassNotFoundException {
        String sql = "select * from KHACH_HANG where makh=?";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setInt(1, makh);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    Khachhangmodel t = new Khachhangmodel();
                    t.setMakh(rs.getInt("makh"));
                    t.setHotenkh(rs.getString("hotenkh"));
                    t.setDiachi(rs.getString("diachi"));
                    t.setSdt(rs.getString("sdt"));
                    return t;
                }
            }
            return null;
        }
    }

    public boolean insert(Khachhangmodel bnv) throws SQLException, ClassNotFoundException {
        String sql = "insert into KHACH_HANG (hotenkh,diachi,sdt) values(?,?,?)";
        Connection conn = Databaseee.getConnection();
        PreparedStatement prst = conn.prepareStatement(sql);

        {
            prst.setString(1, bnv.getHotenkh());
            prst.setString(2, bnv.getDiachi());
            prst.setString(3, bnv.getSdt());
            return prst.executeUpdate() > 0;
        }
    }

    public boolean update(Khachhangmodel bnv) throws SQLException, ClassNotFoundException {
        String sql = "insert into KHACH_HANG hotenkh=?,diachi=?,sdt=? "
                + " where makh=?";
        Connection conn = Databaseee.getConnection();
        PreparedStatement prst = conn.prepareStatement(sql);

        {
            prst.setString(1, bnv.getHotenkh());
            prst.setString(2, bnv.getDiachi());
            prst.setString(3, bnv.getSdt());
            prst.setInt(4, bnv.getMakh());
            return prst.executeUpdate() > 0;
        }
    }

    public boolean delete(Khachhangmodel bnv) throws SQLException, ClassNotFoundException {
        String sql = "delete from KHACH_HANG  "
                + " where makh=?";
        Connection conn = Databaseee.getConnection();
        PreparedStatement prst = conn.prepareStatement(sql);

        {
            prst.setInt(1, bnv.getMakh());
            return prst.executeUpdate() > 0;
        }
    }
}
