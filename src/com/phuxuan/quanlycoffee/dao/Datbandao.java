/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.dao;

import com.phuxuan.quanlycoffee.model.Datbanmodel;
import com.phuxuan.quanlyquancafe.connectJDBC.Databaseee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author chinh
 */
public class Datbandao {

    public boolean insertin(Datbanmodel m) throws SQLException {
        String sql = "INSERT INTO DAT_BAN (maban,manv,makh,ngaydat,trangthai) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, m.getMaban());
            prstt.setString(2, m.getManv());
            prstt.setInt(3, m.getMahk());
            prstt.setDate(4, m.getNgaydat());
            prstt.setString(5, m.getTrangthai());
            return prstt.executeUpdate() > 0;
        }
    }

}
