/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.dao;

import com.phuxuan.quanlycoffee.model.CThoadonmodel;
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
public class Cthoadondao {

    public static ArrayList<CThoadonmodel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CThoadonmodel> lst = new ArrayList<>();
        Connection conn = Databaseee.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select tentd,sum(ct_hoadon.soluongban) as slban,sum(ct_hoadon.thanhtien) as thanhtien from thuc_don left join ct_hoadon on thuc_don.matd = ct_hoadon.matd group by thuc_don.tentd";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CThoadonmodel t = new CThoadonmodel();
                t.setTentd(rs.getString("tentd"));
                t.setSoluongban(rs.getInt("slban"));
                t.setThanhtien(rs.getFloat("thanhtien"));
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

    public boolean insertin(CThoadonmodel m) throws SQLException {
        String sql = "INSERT INTO CT_HOADON (mahd,matd,soluongban,giaban,thanhtien) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, m.getMahd());
            prstt.setInt(2, m.getMatd());
            prstt.setInt(3, m.getSoluongban());
            prstt.setFloat(4, m.getGiaban());
            prstt.setFloat(5, m.getThanhtien());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean update(CThoadonmodel m) throws SQLException {
        String sql = "update CT_HOADON set soluongban=?, giaban=?,thanhtien=?"
                + " where matd=? and mahd=?";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, m.getSoluongban());
            prstt.setFloat(2, m.getGiaban());
            prstt.setFloat(3, m.getThanhtien());
            prstt.setInt(4, m.getMatd());
            prstt.setInt(5, m.getMahd());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean xoa(CThoadonmodel m) throws SQLException {
        String sql = "delete from CT_HOADON "
                + " where mahd=? and matd=?";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, m.getMahd());
            prstt.setInt(2, m.getMatd());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean xoahuydon(CThoadonmodel m) throws SQLException {
        String sql = "delete from ct_hoadon where mahd = ?";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, m.getMahd());
            return prstt.executeUpdate() > 0;
        }
    }

    public CThoadonmodel FindManv(String tentd) throws SQLException, ClassNotFoundException {
        String sql = "select tentd,soluongban from ct_hoadon left join thuc_don on ct_hoadon.matd = thuc_don.matd"
                + " where tentd =?";
        try (
                Connection conn = Databaseee.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setString(1, tentd);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    CThoadonmodel k = new CThoadonmodel();
                    k.setTentd(rs.getString("tentd"));
                    k.setSoluongban(rs.getInt("soluongban"));
                    return k;
                }
            }
            return null;
        }
    }
}
