/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.dao;

import com.phuxuan.quanlycoffee.model.Banmodel;
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
public class Bandao {

    public static ArrayList<Banmodel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Banmodel> lst = new ArrayList<>();
        Connection conn = Databaseee.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from BAN ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Banmodel t = new Banmodel();
                t.setMaban(rs.getInt(1));
                t.setTrangthai(rs.getString(2));
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

    public boolean update(Banmodel m) throws SQLException {
        String sql = "update ban set trangthai =?"
                + " where maban=? ";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, m.getTrangthai());
            prstt.setInt(2, m.getMaban());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean insert(Banmodel m) throws SQLException {
        String sql = "insert into BAN (maban,trangthai) values(?,?)";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, m.getMaban());
            prstt.setString(2, m.getTrangthai());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean xoaban(Banmodel m) throws SQLException {
        String sql = "delete from ban "
                + " where maban=? ";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, m.getMaban());
            return prstt.executeUpdate() > 0;
        }
    }
}
