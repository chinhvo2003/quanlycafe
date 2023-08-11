/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.dao;

import com.phuxuan.quanlycoffee.model.Hoadonmodel;
import com.phuxuan.quanlycoffee.model.Khachhangmodel;
import com.phuxuan.quanlyquancafe.connectJDBC.Databaseee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author chinh
 */
public class Hoadondao {

    public static ArrayList<Hoadonmodel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Hoadonmodel> lst = new ArrayList<>();
        Connection conn = Databaseee.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select mahd,maban,nhan_vien.hotennv,makh,ngayban,tongtien,trangthai from hoa_don left join nhan_vien on hoa_don.manv = nhan_vien.manv";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Hoadonmodel t = new Hoadonmodel();
                t.setMahd(rs.getInt(1));
                t.setMaban(rs.getInt(2));
                t.setManv(rs.getString(3));
                t.setMakh(rs.getInt(4));
                t.setNgayban(rs.getDate(5));
                t.setTongtien(rs.getDouble(6));
                t.setTrangthai(rs.getString(7));
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

    public Hoadonmodel FindManv(int mahd) throws SQLException, ClassNotFoundException {
        String sql = "select * from HOA_DON where mahd=?";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setInt(1, mahd);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    Hoadonmodel t = new Hoadonmodel();
                    t.setMahd(rs.getInt("mahd"));
                    return t;
                }
            }
            return null;
        }
    }

    public boolean insertin(Hoadonmodel m) throws SQLException {
        String sql = "INSERT INTO HOA_DON (maban,manv,makh,ngayban,tongtien,trangthai) VALUES ( ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, m.getMaban());
            prstt.setString(2, m.getManv());
            prstt.setInt(3, m.getMakh());
            prstt.setDate(4, m.getNgayban());
            prstt.setDouble(5, m.getTongtien());
            prstt.setString(6, m.getTrangthai());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean update(Hoadonmodel m) throws SQLException {
        String sql = "update HOA_DON set trangthai=?, tongtien=?"
                + " where mahd=?";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(3, m.getMahd());
            prstt.setDouble(2, m.getTongtien());
            prstt.setString(1, m.getTrangthai());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean updatehuydon(Hoadonmodel m) throws SQLException {
        String sql = "update HOA_DON set trangthai=?, manv=?"
                + " where mahd=?";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(3, m.getMahd());
            prstt.setString(2, m.getManv());
            prstt.setString(1, m.getTrangthai());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean updatesoban(Hoadonmodel m) throws SQLException {
        String sql = "update HOA_DON set maban=?"
                + " where mahd=?";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(2, m.getMahd());
            prstt.setInt(1, m.getMaban());
            return prstt.executeUpdate() > 0;
        }
    }
}
