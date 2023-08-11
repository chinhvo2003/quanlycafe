/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.dao;

import com.phuxuan.quanlycoffee.model.Khachhangmodel;
import com.phuxuan.quanlycoffee.model.Lichsudangnhapmodel;
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
public class Lichsudangnhapdao {

    public static ArrayList<Lichsudangnhapmodel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Lichsudangnhapmodel> lst = new ArrayList<>();
        Connection conn = Databaseee.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select hotennv,ngaydangnhap from lichsu_dn left join nhan_vien on lichsu_dn.manv = nhan_vien.manv";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Lichsudangnhapmodel t = new Lichsudangnhapmodel();
//                t.setId(rs.getInt(1));
                t.setManv(rs.getString("hotennv"));
                t.setNgaydangnhap(rs.getDate("ngaydangnhap"));
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

    public boolean insert(Lichsudangnhapmodel bnv) throws SQLException, ClassNotFoundException {
        String sql = "insert into LICHSU_DN (manv,ngaydangnhap) values(?,?)";
        Connection conn = Databaseee.getConnection();
        PreparedStatement prst = conn.prepareStatement(sql);
        {
            prst.setString(1, bnv.getManv());
            prst.setDate(2, bnv.getNgaydangnhap());
            return prst.executeUpdate() > 0;
        }
    }
}
