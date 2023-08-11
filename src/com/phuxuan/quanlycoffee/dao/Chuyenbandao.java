/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.dao;

import com.phuxuan.quanlycoffee.model.Chuyenbanmodel;
import com.phuxuan.quanlycoffee.model.Hoadonmodel;
import com.phuxuan.quanlyquancafe.connectJDBC.Databaseee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author chinh
 */
public class Chuyenbandao {

    public boolean insertin(Chuyenbanmodel c) throws SQLException {
        String sql = "INSERT INTO CHUYEN_BAN (mabanmoi,mabancu,manv,mahd,ngaychuyen,lydo) VALUES ( ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, c.getMabanmoi());
            prstt.setInt(2, c.getMabancu());
            prstt.setString(3, c.getManv());
            prstt.setInt(4, c.getMahd()                                                                                                                                                                                                                         );
            prstt.setDate(5, c.getNgaychuyen());
            prstt.setString(6, c.getLydo());
            return prstt.executeUpdate() > 0;
        }
    }
}
