/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.bieudo;

import com.phuxuan.quanlycoffee.model.Thongkemodel;
import com.phuxuan.quanlycoffee.model.Thongkesoluongbanmodel;
import com.phuxuan.quanlyquancafe.connectJDBC.Databaseee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chinh
 */
public class ThongkeDaoImpl implements ThongkeDao {

    @Override
    public List<Thongkemodel> getListByHoadon() {
        try {
            Connection conn = Databaseee.getConnection();
            String sql = "select sum(tongtien) as mahdd, ngayban  from hoa_don group by ngayban";
            List<Thongkemodel> list = new ArrayList<>();
            PreparedStatement prstt = conn.prepareStatement(sql);
            ResultSet rs = prstt.executeQuery();
            while (rs.next()) {
                Thongkemodel tm = new Thongkemodel();
                tm.setNgayban(rs.getDate("ngayban"));
                tm.setTongtien(rs.getFloat("mahdd"));
                list.add(tm);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThongkeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public List<Thongkesoluongbanmodel> getListByteCThoadon() {
        try {
            Connection conn = Databaseee.getConnection();
            String sql = "select tentd,sum(ct_hoadon.thanhtien) as slban from ct_hoadon left join thuc_don on ct_hoadon.matd = thuc_don.matd group by thuc_don.tentd";
            List<Thongkesoluongbanmodel> list = new ArrayList<>();
            PreparedStatement prstt = conn.prepareStatement(sql);
            ResultSet rs = prstt.executeQuery();
            while (rs.next()) {
                Thongkesoluongbanmodel tm = new Thongkesoluongbanmodel();
                tm.setTentd(rs.getString("tentd"));
                tm.setSlban(rs.getInt("slban"));
                list.add(tm);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ThongkeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
