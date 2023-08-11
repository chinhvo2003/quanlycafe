/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.dao;

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
public class Thucdondao {

    public static ArrayList<Thucdonmodel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Thucdonmodel> lst = new ArrayList<>();
        Connection conn = Databaseee.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from THUC_DON ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Thucdonmodel t = new Thucdonmodel();
                t.setMatd(rs.getInt(1));
                t.setTenmb(rs.getString(2));
                t.setDtv(rs.getString(3));
                t.setDongia(rs.getFloat(4));
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

    public Thucdonmodel FindManv(int matd) throws SQLException, ClassNotFoundException {
        String sql = "select * from THUC_DON where matd=?";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setInt(1, matd);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    Thucdonmodel t = new Thucdonmodel();
                    t.setMatd(rs.getInt("matd"));
                    t.setTenmb(rs.getString("tentd"));
                    t.setDtv(rs.getString("dvt"));
                    t.setDongia(rs.getFloat("dongia"));
                    Blob blob = rs.getBlob("anhtd");
                    if (blob != null) {
                        t.setAnhtd(blob.getBytes(1, (int) blob.length()));
                    }
                    return t;
                }
            }
            return null;
        }
    }

    public boolean insert(Thucdonmodel bnv) throws SQLException, ClassNotFoundException {
        String sql = "insert into THUC_DON (tentd,dvt,dongia,anhtd) values(?,?,?,?)";
        Connection conn = Databaseee.getConnection();
        PreparedStatement prst = conn.prepareStatement(sql);

        {
//            prst.setInt(1, bnv.getMatd());
            prst.setString(1, bnv.getTenmb());
            prst.setString(2, bnv.getDtv());
            prst.setFloat(3, bnv.getDongia());
            if (bnv.getAnhtd() != null) {
                Blob hinh = new SerialBlob(bnv.getAnhtd());
                prst.setBlob(4, hinh);
            } else {
                Blob hinh = null;
                prst.setBlob(4, hinh);
            }
            return prst.executeUpdate() > 0;
        }
    }

    public boolean update(Thucdonmodel bnv) throws SQLException, ClassNotFoundException {
        String sql = "update THUC_DON set tentd=?,dvt=?,dongia=?,anhtd=? "
                + " where matd=?";
        Connection conn = Databaseee.getConnection();
        PreparedStatement prst = conn.prepareStatement(sql);

        {
            prst.setInt(5, bnv.getMatd());
            prst.setString(1, bnv.getTenmb());
            prst.setString(2, bnv.getDtv());
            prst.setFloat(3, bnv.getDongia());
            if (bnv.getAnhtd() != null) {
                Blob hinh = new SerialBlob(bnv.getAnhtd());
                prst.setBlob(4, hinh);
            } else {
                Blob hinh = null;
                prst.setBlob(4, hinh);
            }
            return prst.executeUpdate() > 0;
        }
    }

    public boolean delete(Thucdonmodel bnv) throws SQLException, ClassNotFoundException {
        String sql = "delete from THUC_DON "
                + " where matd=?";
        Connection conn = Databaseee.getConnection();
        PreparedStatement prst = conn.prepareStatement(sql);

        {
            prst.setInt(1, bnv.getMatd());
            return prst.executeUpdate() > 0;
        }
    }
}
