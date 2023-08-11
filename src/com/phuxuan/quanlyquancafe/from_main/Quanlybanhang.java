/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.phuxuan.quanlyquancafe.from_main;

import Dialogchek.sheardatta;
import com.phuxuan.quanlycoffee.model.Banmodel;
import com.phuxuan.quanlycoffee.model.CThoadonmodel;
import com.phuxuan.quanlycoffee.model.Hoadonmodel;
import com.phuxuan.quanlycoffee.model.Thucdonmodel;
import com.phuxuan.quanlycoffee.dao.Bandao;
import com.phuxuan.quanlycoffee.dao.Chuyenbandao;
import com.phuxuan.quanlycoffee.dao.Cthoadondao;
import com.phuxuan.quanlycoffee.dao.Hoadondao;
import com.phuxuan.quanlycoffee.dao.Khachhangdao;
import com.phuxuan.quanlycoffee.model.Chuyenbanmodel;
import com.phuxuan.quanlycoffee.model.Khachhangmodel;
import com.phuxuan.quanlyquancafe.connectJDBC.Databaseee;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chinh
 */
public class Quanlybanhang extends javax.swing.JFrame implements ActionListener, MouseListener {

    private JPanel jPanel100;

    /**
     * Creates new form Hoatdongg
     */
    public Quanlybanhang() throws SQLException, ClassNotFoundException {
        initComponents();
        veban();
        showdate();
        showTime();
        vemonan();
//        pro();
        laymaban();
    }

    private void laymaban() throws SQLException, ClassNotFoundException {
        Connection conn = Databaseee.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from BAN";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                jComboBoban.addItem(rs.getString(1));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pro() {
        Dangnhap h = new Dangnhap();
        h.setVisible(true);
        h.dispose();
        labletennv.setText(sheardatta.nguoiDangNhap.getHotennv());
        lablevaitro.setText(sheardatta.nguoiDangNhap.getVaitro());
        if (sheardatta.nguoiDangNhap.getVaitro().equals("Quản lý")) {

        } else if (sheardatta.nguoiDangNhap.getVaitro().equals("Nhân viên")) {
            jbuttonnv.setEnabled(false);
            jbuttonkhachhang.setEnabled(false);
            jbuttontd.setEnabled(false);
            jbuttonhd.setEnabled(false);
            jButton6.setEnabled(false);
        }
    }

    private void showdate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String dat = s.format(d);
        lbtDate.setText(dat);

    }

    private void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh : mm : ss");
                String tim = s.format(d);
                lblTime.setText(tim);
            }
        }).start();
    }

    private double conv(String s) {
        String number = "";
        String[] array = s.replace(",", " ").split("\\s");
        for (String i : array) {
            number = number.concat(i);
        }
        return Float.parseFloat(number);
    }

    private void thongke() {
        try {
            Connection connection = Databaseee.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from CT_HOADON where mahd =" + txtmahd.getText() + "";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                float thanhtien = rs.getFloat("thanhtien");
                String[] s2 = txttongtien.getText().split("\\s");
                float totalMoney = (float) (thanhtien + conv(s2[0]));
                txttongtien.setText(Float.toString(totalMoney));
                ttxtienphaitra.setText(Float.toString(totalMoney));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean chekhuydatban() {
        try {
            Connection connection = Databaseee.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from BAN where maban=" + ttxmaban.getText() + "";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet.getString("trangthai").equals("ĐÃ ĐẶT")) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean checkchuyenban() {
        try {
            Connection connection = Databaseee.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from BAN where maban=" + jComboBoban.getSelectedItem() + "";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet.getString("trangthai").equals("CÒN TRỐNG")) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void veban() {
        try {
            Connection connection = Databaseee.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from BAN";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int maban = resultSet.getInt("maban");
                JButton jButton = new JButton(String.valueOf(maban));
                jButton.setBounds(100, 100, 100, 100);
                jButton.setFont(new Font("", Font.BOLD, 14));
                jButton.setCursor(new Cursor(HAND_CURSOR) {
                });
                switch (resultSet.getString("trangthai")) {
                    case "ĐÃ CÓ KHÁCH": {
                        ImageIcon icon = new ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/bann3.png"));
                        jButton.setIcon(icon);
                        break;
                    }
                    case "ĐÃ ĐẶT": {
                        ImageIcon icon = new ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/bann1.png"));
                        jButton.setIcon(icon);
                        break;
                    }
                    default: {
                        ImageIcon icon = new ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/bann2.png"));
                        jButton.setIcon(icon);
                        break;
                    }
                }
                jPanel2.add(jButton);
                jButton.addActionListener(this);
                jButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            JPopupMenu popup = new JPopupMenu();
                            JMenuItem datban = new JMenuItem("Đặt bàn");
                            datban.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        Banmodel b = new Banmodel();
                                        b.setMaban(Integer.parseInt(ttxmaban.getText()));
                                        b.setTrangthai("ĐÃ ĐẶT");
                                        Bandao daoo = new Bandao();
                                        daoo.update(b);
                                        po();
                                        Quanlybanhang main = new Quanlybanhang();
                                        main.setVisible(true);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            popup.add(datban);
                            JMenuItem huydatban = new JMenuItem("Huỷ đặt bàn");
                            huydatban.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        Banmodel b = new Banmodel();
                                        b.setMaban(Integer.parseInt(ttxmaban.getText()));
                                        b.setTrangthai("CÒN TRỐNG");
                                        Bandao daoo = new Bandao();
                                        daoo.update(b);
                                        po();
                                        Quanlybanhang main = new Quanlybanhang();
                                        main.setVisible(true);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            popup.add(huydatban);
                            JMenuItem taohd = new JMenuItem("Tạo hoá đơn");
                            taohd.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    if (JOptionPane.showConfirmDialog(rootPane, "Bạn có tạo hoá đơn cho bàn này ?") == JOptionPane.NO_OPTION) {
                                        return;
                                    }
                                    try {
                                        try {
                                            StringBuilder sb = new StringBuilder();
                                            if (sb.length() > 0) {
                                                JOptionPane.showMessageDialog(rootPane, sb);
                                                return;
                                            }
                                            /// thêm khách hàng
                                            Khachhangmodel k = new Khachhangmodel();
                                            k.setHotenkh("Khách hàng");
                                            k.setDiachi("Không");
                                            k.setSdt("0");
                                            Khachhangdao d = new Khachhangdao();
                                            d.insert(k);
                                            /// tạo hoá đơn
                                            String sql = "select * from NHAN_VIEN where hotennv=N'" + labletennv.getText() + "'";
                                            Connection conn = Databaseee.getConnection();
                                            Statement stmt = conn.createStatement();
                                            ResultSet rs = stmt.executeQuery(sql);
                                            while (rs.next()) {
                                                Hoadonmodel h = new Hoadonmodel();
                                                h.setMaban(Integer.parseInt(ttxmaban.getText()));
                                                h.setManv(rs.getString("manv"));
                                                h.setMakh(31);
                                                h.setNgayban(java.sql.Date.valueOf(lbtDate.getText()));
                                                h.setTongtien(Double.valueOf(0));
                                                h.setTrangthai("CHƯA THANH TOÁN");
                                                Hoadondao dao = new Hoadondao();
                                                dao.insertin(h);
                                            }
                                            /// cập nhật trạng thái bàn
                                            Banmodel b = new Banmodel();
                                            b.setMaban(Integer.parseInt(ttxmaban.getText()));
                                            b.setTrangthai("ĐÃ CÓ KHÁCH");
                                            Bandao daoo = new Bandao();
                                            daoo.update(b);
                                            JOptionPane.showMessageDialog(rootPane, "Tạo hoá đơn thành công !!!");
                                        } catch (SQLException ex) {
                                            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        po();
                                        Quanlybanhang main = new Quanlybanhang();
                                        main.setVisible(true);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            );
                            popup.add(taohd);
                            JMenuItem huyhd = new JMenuItem("Huỷ đơn");
                            huyhd.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        try {
                                            /// cập nhật trạng thái hoá đơn
                                            String sql = "select * from NHAN_VIEN where hotennv=N'" + labletennv.getText() + "'";
                                            Connection conn = Databaseee.getConnection();
                                            Statement stmt = conn.createStatement();
                                            ResultSet rs = stmt.executeQuery(sql);
                                            while (rs.next()) {
                                                Hoadonmodel h = new Hoadonmodel();
                                                h.setMahd(Integer.parseInt(txtmahd.getText()));
                                                h.setManv(rs.getString("manv"));
                                                h.setTrangthai("ĐÃ HUỶ");
                                                Hoadondao dao = new Hoadondao();
                                                dao.updatehuydon(h);
                                            }
                                            /// cập nhật trạng thái bàn
                                            Banmodel b = new Banmodel();
                                            b.setMaban(Integer.parseInt(ttxmaban.getText()));
                                            b.setTrangthai("CÒN TRỐNG");
                                            Bandao daoo = new Bandao();
                                            daoo.update(b);
                                            // huỷ món
                                            CThoadonmodel c = new CThoadonmodel();
                                            c.setMahd(Integer.parseInt(txtmahd.getText()));
                                            Cthoadondao daooo = new Cthoadondao();
                                            daooo.xoahuydon(c);
                                            layCTHOADON();
                                            thongke();
                                            JOptionPane.showMessageDialog(rootPane, "Huỷ hoá đơn thành công !!!");
                                        } catch (SQLException ex) {
                                            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        po();
                                        Quanlybanhang main = new Quanlybanhang();
                                        main.setVisible(true);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            );
                            popup.add(huyhd);
                            JMenuItem XEMHDD = new JMenuItem("Gọi nước");
                            XEMHDD.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        jFrame1.setVisible(true);
                                        layCTHOADON();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            popup.add(XEMHDD);
                            JMenuItem xemhd = new JMenuItem("Xoá bàn");
                            xemhd.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        if (JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xoá bàn này !") == JOptionPane.NO_OPTION) {
                                            return;
                                        }
                                        Banmodel b = new Banmodel();
                                        b.setMaban(Integer.parseInt(ttxmaban.getText()));
                                        Bandao dao = new Bandao();
                                        dao.xoaban(b);
                                        po();
                                        Quanlybanhang main = new Quanlybanhang();
                                        main.setVisible(true);
                                        JOptionPane.showMessageDialog(rootPane, "Xoá bàn thành công !!!");
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            popup.add(xemhd);
                            JMenuItem chuyenban = new JMenuItem("Chuyển bàn");
                            chuyenban.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    jFrame3.setVisible(true);
                                }
                            });
                            popup.add(chuyenban);
                            if (checktaohd()) {
                                taohd.setVisible(false);
                                xemhd.setVisible(false);
                                datban.setVisible(false);
                                huydatban.setVisible(false);
                            } else {
                                taohd.setVisible(true);
                                xemhd.setVisible(true);
                                chuyenban.setVisible(false);
                                XEMHDD.setVisible(false);
                                huyhd.setVisible(false);
                            }
                            if (chekhuydatban()) {
                                datban.setVisible(false);
                                xemhd.setVisible(false);
                            } else {
                                huydatban.setVisible(false);
                            }
                            taohd.setCursor(new Cursor(HAND_CURSOR) {
                            });
                            XEMHDD.setCursor(new Cursor(HAND_CURSOR) {
                            });
                            xemhd.setCursor(new Cursor(HAND_CURSOR) {
                            });
                            chuyenban.setCursor(new Cursor(HAND_CURSOR) {
                            });
                            datban.setCursor(new Cursor(HAND_CURSOR) {
                            });
                            huydatban.setCursor(new Cursor(HAND_CURSOR) {
                            });
                            popup.show(jButton, e.getX(), e.getY());
                        }
                    }
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void vemonan() {
        try {
            Connection connection = Databaseee.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from THUC_DON";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String tentd = resultSet.getString("tentd");
                float dongia = resultSet.getInt("dongia");
                jPanel100 = new JPanel();
                JLabel jLabel99 = new JLabel("");
                JLabel jLabel3 = new JLabel(tentd);
                JLabel jLabel4 = new JLabel(dongia + "(VNĐ)");
                JLabel jLabel999 = new JLabel("");
                jLabel3.setCursor(new Cursor(HAND_CURSOR) {
                });
                jPanel100.add(jLabel99);
                jPanel100.add(jLabel3);
                jPanel100.add(jLabel4);
                jPanel100.add(jLabel999);
                jPanel100.setBackground(new Color(230, 237, 183));
                jPanel100.setLayout(new GridLayout(4, 1, 2, 10));
                jLabel3.setFont(new Font("", Font.BOLD, 13));
                jLabel4.setFont(new Font("", Font.BOLD, 13));
                jLabel3.setForeground(Color.BLACK);
                jLabel4.setForeground(Color.RED);
                jLabel3.setHorizontalAlignment((int) CENTER_ALIGNMENT);
                jLabel3.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
                jLabel4.setHorizontalAlignment((int) CENTER_ALIGNMENT);
                jLabel4.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
                jPanel100.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                Border blackline = BorderFactory.createLineBorder(Color.BLACK);
                jPanel100.setBorder(blackline);
                jpane.add(jPanel100);
                jLabel3.addMouseListener(this);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private boolean checktaohd() {
        try {
            Connection connection = Databaseee.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from BAN where maban=" + ttxmaban.getText() + "";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("trangthai").equals("ĐÃ CÓ KHÁCH")) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void po() {
        this.dispose();
    }

    private void inhoadon() {
        // Tạo chuỗi định dạng văn bản để lưu các giá trị trong bảng
        Random rand = new Random();
        int randomNumber = rand.nextInt(1000) + 1;
        StringBuilder sb = new StringBuilder();
        sb.append("\t\tNHỚ KAFÉ\n");
        sb.append("\t\tHOÁ ĐƠN IN LẠI\n");
        sb.append("Số HĐ:\t\t\t0000").append(randomNumber);
        sb.append("\n");
        sb.append("Bàn: \t\t\t\t").append(ttxmaban.getText());
        sb.append("\n");
        sb.append("-----------------------------------------\n");
        sb.append("Tên nước\tSL\tGía bán\tThành tiền\n");
        sb.append("-----------------------------------------\n");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                sb.append(tableModel.getValueAt(i, j)).append("\t");
            }
            sb.append("\n");
            sb.append("-----------------------------------------\n");
        }
        sb.append("Tổng tiền: \t\t\t\t").append(txttongtien.getText());
        sb.append("\n");
        sb.append("Thành tiền: \t\t\t").append(txttongtien.getText());
        sb.append("\n");
        sb.append("\n");
        sb.append("\tCảm ơn Quý khách. Hẹn gặp lại.!\n");
        // Lưu chuỗi định dạng văn bản thành tệp văn bản
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu tệp hóa đơn");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileWriter fileWriter = new FileWriter(fileToSave + ".pdf")) {
                fileWriter.write(sb.toString());
                JOptionPane.showMessageDialog(this, "In hoá đơn thành công !!!", "Xuất hóa đơn", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi lưu tệp: " + e.getMessage(), "Xuất hóa đơn", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ttxmaban = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtmahd = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtmakh = new javax.swing.JLabel();
        datengayban = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableModel = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jpane = new javax.swing.JPanel();
        jFrame2 = new javax.swing.JFrame();
        jPanel19 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        tennuoc = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        txtsoluong = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jFrame3 = new javax.swing.JFrame();
        jPanel22 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        banso = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoban = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jFrame4 = new javax.swing.JFrame();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtban = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        ttxtienphaitra = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txttienkhachdua = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txttienthuaa = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jbuttonnv = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jbuttonkhachhang = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jbuttontd = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jbuttonhd = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labletennv = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbtDate = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lablevaitro = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        jPanel2 = new java.awt.Panel();
        jPanel3 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();

        jFrame1.setTitle("Gọi nước");
        jFrame1.setLocation(new java.awt.Point(270, 140));
        jFrame1.setMinimumSize(new java.awt.Dimension(1000, 537));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(204, 0, 204));
        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Bàn: ");
        jPanel8.add(jLabel3);

        ttxmaban.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ttxmaban.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.add(ttxmaban);

        jPanel4.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel13.setLayout(new java.awt.CardLayout(15, 10));

        jPanel15.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Hoá đơn:");
        jPanel15.add(jLabel9);

        txtmahd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtmahd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel15.add(txtmahd);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Khách hàng:");
        jPanel15.add(jLabel11);

        txtmakh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtmakh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel15.add(txtmakh);

        datengayban.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        datengayban.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel15.add(datengayban);

        jPanel13.add(jPanel15, "card2");

        jPanel12.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel16.setLayout(new java.awt.CardLayout(10, 10));

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tableModel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableModel.setInheritsPopupMenu(true);
        tableModel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableModelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableModel);

        jPanel16.add(jScrollPane1, "card2");

        jPanel14.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel12);

        jPanel9.add(jPanel10);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(472, 150));
        jPanel7.setLayout(new java.awt.CardLayout(10, 10));

        jPanel20.setLayout(new java.awt.GridLayout(2, 0));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Tổng tiền:");
        jPanel20.add(jLabel12);

        txttongtien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel20.add(txttongtien);
        jPanel20.add(jLabel15);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/money.png"))); // NOI18N
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton5);

        jPanel7.add(jPanel20, "card2");

        jPanel11.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel17.setLayout(new java.awt.CardLayout(10, 20));

        jPanel18.setLayout(new java.awt.BorderLayout());

        jpane.setLayout(new java.awt.GridLayout(4, 5, 4, 3));
        jScrollPane2.setViewportView(jpane);

        jPanel18.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel17.add(jPanel18, "card2");

        jPanel11.add(jPanel17, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel11);

        jPanel4.add(jPanel9, java.awt.BorderLayout.CENTER);

        jFrame1.getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        jFrame2.setTitle("Nhập số lượng");
        jFrame2.setLocation(new java.awt.Point(600, 300));
        jFrame2.setMinimumSize(new java.awt.Dimension(390, 240));
        jFrame2.setUndecorated(true);

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(171, 104, 50)));
        jPanel19.setLayout(new java.awt.CardLayout(10, 10));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Tên nước:");

        tennuoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tennuoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Số lượng mua:");

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/addtocard.png"))); // NOI18N
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("+");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtsoluong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtsoluong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsoluong.setText("1");

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setText("-");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tennuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tennuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel19.add(jPanel21, "card2");

        jFrame2.getContentPane().add(jPanel19, java.awt.BorderLayout.CENTER);

        jFrame3.setLocation(new java.awt.Point(600, 300));
        jFrame3.setMinimumSize(new java.awt.Dimension(322, 151));
        jFrame3.setUndecorated(true);

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(171, 104, 50)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(171, 104, 50));
        jLabel4.setText("Bàn:");

        banso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        banso.setForeground(new java.awt.Color(171, 104, 50));
        banso.setText("jLabel6");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(171, 104, 50));
        jLabel10.setText("Chuyển sang");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(171, 104, 50));
        jButton2.setText("Chuyển");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(171, 104, 50));
        jButton3.setText("Huỷ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(banso, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jComboBoban, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(banso)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(29, 29, 29))
        );

        jFrame3.getContentPane().add(jPanel22, java.awt.BorderLayout.CENTER);

        jFrame4.setLocation(new java.awt.Point(600, 300));
        jFrame4.setMinimumSize(new java.awt.Dimension(400, 300));
        jFrame4.setUndecorated(true);

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(171, 104, 50)));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jPanel24.setLayout(new java.awt.CardLayout(20, 10));

        jPanel26.setLayout(new java.awt.GridLayout(1, 0));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Thanh toán Bàn: ");
        jPanel26.add(jLabel6);

        txtban.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtban.setForeground(new java.awt.Color(0, 102, 0));
        txtban.setText("jLabel12");
        jPanel26.add(txtban);

        jPanel24.add(jPanel26, "card2");

        jPanel23.add(jPanel24, java.awt.BorderLayout.PAGE_START);

        jPanel25.setLayout(new java.awt.CardLayout(20, 10));

        jPanel27.setLayout(new java.awt.GridLayout(3, 0));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Số tiền cần trả:");
        jPanel27.add(jLabel16);

        ttxtienphaitra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ttxtienphaitra.setForeground(new java.awt.Color(255, 0, 0));
        jPanel27.add(ttxtienphaitra);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Số tiền khách đưa:");
        jPanel27.add(jLabel22);

        txttienkhachdua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txttienkhachdua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttienkhachduaKeyReleased(evt);
            }
        });
        jPanel27.add(txttienkhachdua);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Tiền thừa:");
        jPanel27.add(jLabel24);

        txttienthuaa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txttienthuaa.setForeground(new java.awt.Color(255, 0, 0));
        jPanel27.add(txttienthuaa);

        jPanel25.add(jPanel27, "card2");

        jPanel23.add(jPanel25, java.awt.BorderLayout.CENTER);

        jPanel28.setLayout(new java.awt.CardLayout(20, 10));

        jPanel29.setLayout(new java.awt.GridLayout(1, 0, 80, 0));

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setText("Xác nhận");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setPreferredSize(new java.awt.Dimension(75, 40));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel29.add(jButton9);

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setText("Huỷ bỏ");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel29.add(jButton10);

        jPanel28.add(jPanel29, "card2");

        jPanel23.add(jPanel28, java.awt.BorderLayout.PAGE_END);

        jFrame4.getContentPane().add(jPanel23, java.awt.BorderLayout.CENTER);

        setTitle("Bán hàng");

        jToolBar1.setRollover(true);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/ban2.png"))); // NOI18N
        jButton1.setText("Bàn");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator2);

        jbuttonnv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbuttonnv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/nhanvien.png"))); // NOI18N
        jbuttonnv.setText("Nhân viên");
        jbuttonnv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbuttonnv.setFocusable(false);
        jbuttonnv.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jbuttonnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonnvActionPerformed(evt);
            }
        });
        jToolBar1.add(jbuttonnv);
        jToolBar1.add(jSeparator3);

        jbuttonkhachhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbuttonkhachhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/khachhang.png"))); // NOI18N
        jbuttonkhachhang.setText("Khách hàng");
        jbuttonkhachhang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbuttonkhachhang.setFocusable(false);
        jbuttonkhachhang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jbuttonkhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonkhachhangActionPerformed(evt);
            }
        });
        jToolBar1.add(jbuttonkhachhang);
        jToolBar1.add(jSeparator4);

        jbuttontd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbuttontd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/cafe1.png"))); // NOI18N
        jbuttontd.setText("Thực đơn");
        jbuttontd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbuttontd.setFocusable(false);
        jbuttontd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jbuttontd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttontdActionPerformed(evt);
            }
        });
        jToolBar1.add(jbuttontd);
        jToolBar1.add(jSeparator5);

        jbuttonhd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbuttonhd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/hoadon.png"))); // NOI18N
        jbuttonhd.setText("Hoá đơn");
        jbuttonhd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbuttonhd.setFocusable(false);
        jbuttonhd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jbuttonhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonhdActionPerformed(evt);
            }
        });
        jToolBar1.add(jbuttonhd);
        jToolBar1.add(jSeparator7);

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/thongke.png"))); // NOI18N
        jButton6.setText("Thống kê");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);
        jToolBar1.add(jSeparator8);

        jPanel5.setLayout(new java.awt.CardLayout());

        jPanel6.setLayout(new java.awt.GridLayout(2, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tên nhân viên:  ");
        jPanel6.add(jLabel2);

        labletennv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel6.add(labletennv);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Ngày:  ");
        jPanel6.add(jLabel5);

        lbtDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbtDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel6.add(lbtDate);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Vai trò:  ");
        jPanel6.add(jLabel7);

        lablevaitro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel6.add(lablevaitro);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Giờ:  ");
        jPanel6.add(jLabel8);

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel6.add(lblTime);

        jPanel5.add(jPanel6, "card2");

        jToolBar1.add(jPanel5);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new java.awt.GridLayout(4, 5, 9, 9));
        scrollPane1.add(jPanel2);

        jPanel3.setLayout(new java.awt.CardLayout());

        jPanel30.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/bann3.png"))); // NOI18N
        jLabel1.setText("Có khách");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel30.add(jLabel1);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/bann1.png"))); // NOI18N
        jLabel13.setText("Đã đặt");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel30.add(jLabel13);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/bann2.png"))); // NOI18N
        jLabel17.setText("Còn trống");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel30.add(jLabel17);

        jPanel3.add(jPanel30, "card2");

        jPanel31.setLayout(new java.awt.BorderLayout());

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/cofee.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(103, Short.MAX_VALUE))))
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Hệ thống");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/setingmk.png"))); // NOI18N
        jMenuItem1.setText("Đổi mật khẩu");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/lgout.png"))); // NOI18N
        jMenuItem2.setText("Đăng xuất");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator6);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/exit.png"))); // NOI18N
        jMenuItem3.setText("Thoát");
        jMenuItem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator9);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phuxuan/quanlycoffee/image/fix.png"))); // NOI18N
        jMenuItem4.setText("Rest");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            Doimatkhau h = new Doimatkhau();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Dangnhap h = new Dangnhap();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Quanlyban h = new Quanlyban();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbuttonnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonnvActionPerformed
        try {
            Quanlynhanvien h = new Quanlynhanvien();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbuttonnvActionPerformed

    private void jbuttonkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonkhachhangActionPerformed
        try {
            Quanlykhachhang h = new Quanlykhachhang();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbuttonkhachhangActionPerformed

    private void jbuttontdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttontdActionPerformed
        try {
            Quanlythucdon h = new Quanlythucdon();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbuttontdActionPerformed

    private void jbuttonhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonhdActionPerformed
        try {
            Quanlyhoadon h = new Quanlyhoadon();
            h.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbuttonhdActionPerformed
    private String cutChar(String arry) {
        return arry.replaceAll("\\D+", "");
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
//        if (cheksl()) {
        StringBuilder sb = new StringBuilder();
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(rootPane, sb);
            return;
        }
        String sql = "select * from THUC_DON where tentd=N'" + tennuoc.getText() + "'";
        try {
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CThoadonmodel c = new CThoadonmodel();
                c.setMahd(Integer.parseInt(txtmahd.getText()));
                c.setMatd(rs.getInt("matd"));
                c.setSoluongban(Integer.parseInt(txtsoluong.getText()));
                c.setGiaban(rs.getFloat("dongia"));
                c.setThanhtien(c.getGiaban() * c.getSoluongban());
                Cthoadondao dao = new Cthoadondao();
                dao.update(c);
                layCTHOADON();
                thongke();
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException ex) {
//            }
        }
        txtsoluong.setText("1");
        jFrame2.dispose();
//        jFrame1.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed
    private boolean cheksl() {
        return Integer.parseInt(txtsoluong.getText()) > 0;
    }
    private void tableModelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableModelMouseClicked

        try {
            int row = tableModel.getSelectedRow();
            if (row >= 0) {
                String tentd = (String) tableModel.getValueAt(row, 0);
                Cthoadondao dao = new Cthoadondao();
                CThoadonmodel b = dao.FindManv(tentd);
                if (dao != null) {
                    tennuoc.setText(b.getTentd());
                    txttongtien.setText("0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "looix");
        }
        tableModel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem xoaphong = new JMenuItem("Xóa thực đơn");
                    xoaphong.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String sql = "select * from THUC_DON where tentd=N'" + tennuoc.getText() + "'";
                                Connection conn = Databaseee.getConnection();
                                Statement stmt = conn.createStatement();
                                ResultSet rs = stmt.executeQuery(sql);
                                while (rs.next()) {
                                    try {
                                        CThoadonmodel c = new CThoadonmodel();
                                        c.setMatd(rs.getInt("matd"));
                                        c.setMahd(Integer.parseInt(txtmahd.getText()));
                                        c.setSoluongban(Integer.parseInt(txtsoluong.getText()));
                                        Cthoadondao dao = new Cthoadondao();
                                        dao.xoa(c);
                                        layCTHOADON();
                                        thongke();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    );
                    popup.add(xoaphong);
                    JMenuItem XEMHDD = new JMenuItem("Cập nhật số lượng");
                    XEMHDD.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jFrame2.setVisible(true);
                        }
                    });
                    popup.add(XEMHDD);
                    xoaphong.setForeground(Color.red);
                    XEMHDD.setForeground(Color.ORANGE);
                    popup.show(tableModel, e.getX(), e.getY());
                }
            }
        }
        );
    }//GEN-LAST:event_tableModelMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jFrame3.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (checkchuyenban()) {
            try {
                /// cập nhật trạng thái bàn cũ
                Banmodel b = new Banmodel();
                b.setMaban(Integer.parseInt(banso.getText()));
                b.setTrangthai("CÒN TRỐNG");
                Bandao daoo = new Bandao();
                daoo.update(b);
                /// cập nhật trạng thái bàn mới
                b.setMaban(Integer.parseInt(jComboBoban.getSelectedItem().toString()));
                b.setTrangthai("ĐÃ CÓ KHÁCH");
                Bandao daooo = new Bandao();
                daooo.update(b);
                /// cập nhật số bàn của hoá đơn
                Hoadonmodel h = new Hoadonmodel();
                h.setMahd(Integer.parseInt(txtmahd.getText()));
                h.setMaban(Integer.parseInt(jComboBoban.getSelectedItem().toString()));
                Hoadondao dao = new Hoadondao();
                dao.updatesoban(h);
                /// thêm vào hoá đơn chuyển bàn
                StringBuilder sb = new StringBuilder();
                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(rootPane, sb);
                    return;
                }
                String sql = "select * from NHAN_VIEN where hotennv=N'" + labletennv.getText() + "'";
                Connection conn = Databaseee.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Chuyenbanmodel c = new Chuyenbanmodel();
                    c.setMabanmoi(Integer.parseInt(jComboBoban.getSelectedItem().toString()));
                    c.setMabancu(Integer.parseInt(banso.getText()));
                    c.setMahd(Integer.parseInt(txtmahd.getText()));
                    c.setManv(rs.getString("manv"));
                    c.setLydo("");
                    c.setNgaychuyen(java.sql.Date.valueOf(lbtDate.getText()));
                    Chuyenbandao chuyendao = new Chuyenbandao();
                    chuyendao.insertin(c);
                }
                JOptionPane.showMessageDialog(rootPane, "Chuyển bàn thành công !");
                this.dispose();
                Quanlybanhang main = new Quanlybanhang();
                main.setVisible(true);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jFrame3.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            this.dispose();
            Quanlybanhang main = new Quanlybanhang();
            main.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int a = Integer.parseInt(txtsoluong.getText().trim());
        int b = 1;
        int kq = a + b;
        txtsoluong.setText(String.valueOf(kq));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (cheksl()) {
            int td = 0;
            int a = Integer.parseInt(txtsoluong.getText().trim());
            int b = 1;
            int kq = a - b;
            txtsoluong.setText(String.valueOf(kq));
        }
        if (chekve0()) {
            txtsoluong.setText("1");
            jFrame2.dispose();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            Quanlythongke h = new Quanlythongke();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed
    private boolean chekve0() {
        return Integer.parseInt(txtsoluong.getText()) == 0;
    }
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            Hoadonmodel h = new Hoadonmodel();
            h.setMahd(Integer.parseInt(txtmahd.getText()));
            h.setTrangthai("ĐÃ THANH TOÁN");
            h.setTongtien(Double.valueOf(txttongtien.getText()));
            Hoadondao dao = new Hoadondao();
            dao.update(h);
            /// cập nhật trạng thái bàn
            Banmodel b = new Banmodel();
            b.setMaban(Integer.parseInt(ttxmaban.getText()));
            b.setTrangthai("CÒN TRỐNG");
            Bandao daoo = new Bandao();
            daoo.update(b);
            inhoadon();
            po();
            Quanlybanhang main = new Quanlybanhang();
            main.setVisible(true);
            JOptionPane.showMessageDialog(rootPane, "Thanh toán thành công !!!");
        } catch (SQLException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrame1.dispose();
        jFrame4.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txttienkhachduaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttienkhachduaKeyReleased
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        txttienkhachdua.setText(cutChar(txttienkhachdua.getText()));
        if (txttienkhachdua.getText().equals("")) {
            String[] s = ttxtienphaitra.getText().split("\\s");
            txttienthuaa.setText("0");
        } else {
            txttienkhachdua.setText(formatter.format(conv(txttienkhachdua.getText())));
            String s1 = txttienkhachdua.getText();
            String[] s2 = ttxtienphaitra.getText().split("\\s");
            if ((conv(s1) - conv(s2[0])) >= 0) {
                txttienthuaa.setText(formatter.format((conv(s1) - conv(s2[0]))));
                jButton9.setEnabled(true);
            } else {
                txttienthuaa.setText(formatter.format((conv(s1) - conv(s2[0]))));
                jButton9.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txttienkhachduaKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jFrame4.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        jFrame4.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Quanlybanhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quanlybanhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quanlybanhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quanlybanhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dangnhap h = new Dangnhap();
                    h.setVisible(true);
                    if (sheardatta.nguoiDangNhap.getVaitro().equals("Quản lý")) {
                        new Quanlybanhang().setVisible(true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banso;
    private javax.swing.JLabel datengayban;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBoban;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private java.awt.Panel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbuttonhd;
    private javax.swing.JButton jbuttonkhachhang;
    private javax.swing.JButton jbuttonnv;
    private javax.swing.JButton jbuttontd;
    private javax.swing.JPanel jpane;
    private javax.swing.JLabel labletennv;
    private javax.swing.JLabel lablevaitro;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lbtDate;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JTable tableModel;
    private javax.swing.JLabel tennuoc;
    private javax.swing.JLabel ttxmaban;
    private javax.swing.JLabel ttxtienphaitra;
    private javax.swing.JLabel txtban;
    private javax.swing.JLabel txtmahd;
    private javax.swing.JLabel txtmakh;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttienkhachdua;
    private javax.swing.JLabel txttienthuaa;
    private javax.swing.JLabel txttongtien;
    // End of variables declaration//GEN-END:variables

    private void layhoadon() {
        String sql = "select mahd,hotenkh from hoa_don left join khach_hang on hoa_don.makh = khach_hang.makh where trangthai=N'CHƯA THANH TOÁN' and  maban=" + ttxmaban.getText() + "";
        try {
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                txtmahd.setText(String.valueOf(rs.getInt("mahd")));
                txtmakh.setText(rs.getString("hotenkh"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void layCTHOADON() throws SQLException, ClassNotFoundException {
        Connection conn = Databaseee.getConnection();
        try {
            String sqll = "select mact,tentd,soluongban,giaban,thanhtien from ct_hoadon left join thuc_don on ct_hoadon.matd = thuc_don.matd where mahd =" + txtmahd.getText() + "";
            String[] aray = {"Tên nước", "Số lượng", "Gía bán", "Thành tiền"};
            DefaultTableModel model = new DefaultTableModel(aray, 0);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqll);
            while (rs.next()) {
                Vector vector = new Vector();
//                vector.add(rs.getInt("mact"));
                vector.add(rs.getString("tentd"));
                vector.add(rs.getInt("soluongban"));
                vector.add(rs.getFloat("giaban"));
                vector.add(rs.getFloat("thanhtien"));
                model.addRow(vector);
            }
            tableModel.setModel(model);
        } catch (SQLException e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ttxmaban.setText(((JButton) e.getSource()).getText());
        banso.setText(((JButton) e.getSource()).getText());
        txtban.setText(((JButton) e.getSource()).getText());
        txtmahd.setText("0");
        txttongtien.setText("0");
        ttxtienphaitra.setText("0");
        layhoadon();
        checktaohd();
        thongke();
        txtsoluong.setEnabled(false);
        jButton9.setEnabled(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        tennuoc.setText(((JLabel) e.getSource()).getText());
        jFrame2.setVisible(true);
        txttongtien.setText("0");
        ttxtienphaitra.setText("0");
        if (cheksl()) {
            StringBuilder sb = new StringBuilder();
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(rootPane, sb);
                return;
            }
            String sql = "select * from THUC_DON where tentd=N'" + tennuoc.getText() + "'";
            try {
                Connection conn = Databaseee.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    CThoadonmodel c = new CThoadonmodel();
                    c.setMahd(Integer.parseInt(txtmahd.getText()));
                    c.setMatd(rs.getInt("matd"));
                    c.setSoluongban(1);
                    c.setGiaban(rs.getFloat("dongia"));
                    c.setThanhtien(c.getGiaban() * c.getSoluongban());
                    Cthoadondao dao = new Cthoadondao();
                    dao.insertin(c);
                    layCTHOADON();
                    thongke();
                }
            } catch (ClassNotFoundException | NumberFormatException | SQLException ex) {
            }
        }
        txtsoluong.setText("1");
        jFrame2.dispose();
        jFrame1.setVisible(true);

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
