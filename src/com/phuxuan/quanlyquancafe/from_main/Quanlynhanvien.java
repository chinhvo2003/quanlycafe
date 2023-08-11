/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.phuxuan.quanlyquancafe.from_main;

import Dialogchek.ImageHelperR;
import Dialogchek.sheardatta;
import com.phuxuan.quanlycoffee.connectJDBC.Databaseee;
import com.phuxuan.quanlycoffee.dao.Nhanviendao;
import com.phuxuan.quanlycoffee.model.Nhanvienmodel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author chinh
 */
public class Quanlynhanvien extends javax.swing.JFrame {

    private byte[] resonalImage;
    private DefaultTableModel tblModel;
    ArrayList<Nhanvienmodel> nhanvienmodels;
    private TableRowSorter sorter;

    /**
     * Creates new form Nhanvien
     */
    public Quanlynhanvien() throws SQLException, ClassNotFoundException {
        initComponents();
        hh();
        initTable();
        LoadProductTable();
        chuotphaijtable();
        pro();
    }

    private void pro() {
//       s
        if (sheardatta.nguoiDangNhap.getVaitro().equals("Quản lý")) {

        } else if (sheardatta.nguoiDangNhap.getVaitro().equals("Nhân viên")) {
        }
    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{"MaNV", "Tên NV", "Ngày sinh", "Giois tính", "Lương", "Điện thoại", "Địa chỉ", "Tên đăng nhập", "Vai trò"});
        tablenhanvien.setModel(tblModel);
    }

    private void LoadProductTable() throws SQLException, ClassNotFoundException {
        nhanvienmodels = Nhanviendao.getAll();
        sorter = new TableRowSorter(tblModel);
        tablenhanvien.setRowSorter(sorter);
        tblModel.setRowCount(0);
        for (Nhanvienmodel m : nhanvienmodels) {
            Object[] object = new Object[]{m.getManv(), m.getHotennv(), m.getNgaysinh(), m.getGioitinh(), m.getLuong(), m.getSdt(), m.getDiachi(), m.getTendangnhap(), m.getVaitro()};
            tblModel.addRow(object);
        }
        tblModel.fireTableDataChanged();
    }

    private void hh() {
        txtTimHoTen.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txtTimHoTen.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txtTimHoTen.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txtTimHoTen.getText());
            }
        });
    }
    //Ham tim kiem theo chuoi str

    private void search(String str) {
        if (str.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(str));
        }
    }

    private void chuotphaijtable() {
        tablenhanvien.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem capnhat = new JMenuItem("Cập nhật");
                    capnhat.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jFrame2.setVisible(true);
                        }
                    });
                    capnhat.setForeground(Color.ORANGE);
                    popup.add(capnhat);
                    JMenuItem xoa = new JMenuItem("Xoá");
                    xoa.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Nhanvienmodel bnv = new Nhanvienmodel();
                                bnv.setManv(txtmanv1.getText());
                                Nhanviendao dao = new Nhanviendao();
                                dao.delete(bnv);
                                LoadProductTable();
                                JOptionPane.showMessageDialog(rootPane, "Xoá nhân viên thành công !");
                            } catch (SQLException ex) {
                                Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    xoa.setForeground(Color.RED);
                    popup.add(xoa);
                    popup.show(tablenhanvien, e.getX(), e.getY());
                }
            }
        });
    }

    private boolean checkmanv() {
        try {
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select * from NHAN_VIEN";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("manv").toString().trim().equals(txtmanv.getText())) {
                    JOptionPane.showMessageDialog(rootPane, "Mã nhân viên đã tồn tại !!!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
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
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jlableanh = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtmanv = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttennv = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        datengaysinh = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jcombogt = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtluong = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        ttxsdt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtdiachi = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txttendangnhap = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jpasss = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        jcombovaitro = new javax.swing.JComboBox<>();
        jFrame2 = new javax.swing.JFrame();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jlableanh1 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtmanv1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txttennv1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        datengaysinh1 = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jcombogt1 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtluong1 = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        ttxsdt1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtdiachi1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txttendangnhap1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jpasss1 = new javax.swing.JPasswordField();
        jLabel24 = new javax.swing.JLabel();
        jcombovaitro1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtTimHoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablenhanvien = new javax.swing.JTable();
        jPanel36 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jFrame1.setTitle("Thêm thông tin nhân viên");
        jFrame1.setLocation(new java.awt.Point(300, 100));
        jFrame1.setMinimumSize(new java.awt.Dimension(924, 560));

        jPanel4.setLayout(new java.awt.CardLayout(10, 10));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.CardLayout());

        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel15);

        jPanel16.setLayout(new java.awt.CardLayout(50, 35));

        jPanel17.setLayout(new java.awt.GridLayout(1, 20, 20, 10));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Huỷ");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel17.add(jButton3);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Xác nhận");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton4);

        jPanel16.add(jPanel17, "card2");

        jPanel14.add(jPanel16);

        jPanel6.add(jPanel14, "card2");

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel10.setLayout(new java.awt.CardLayout());

        jPanel13.setLayout(new java.awt.BorderLayout());

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Ảnh");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton2, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel13, "card2");

        jPanel8.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jPanel11.setLayout(new java.awt.BorderLayout());
        jPanel11.add(jlableanh, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel8);

        jPanel9.setLayout(new java.awt.CardLayout());

        jPanel18.setLayout(new java.awt.GridLayout(5, 0, 0, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Mã nhân viên:");
        jPanel18.add(jLabel6);

        txtmanv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel18.add(txtmanv);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tên nhân viên:");
        jPanel18.add(jLabel7);

        txttennv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel18.add(txttennv);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Ngày sinh:");
        jPanel18.add(jLabel8);

        datengaysinh.setToolTipText("");
        jPanel18.add(datengaysinh);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Gioi tính:");
        jPanel18.add(jLabel9);

        jcombogt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcombogt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", " " }));
        jPanel18.add(jcombogt);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Lương:");
        jPanel18.add(jLabel5);

        txtluong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel18.add(txtluong);

        jPanel9.add(jPanel18, "card2");

        jPanel7.add(jPanel9);

        jPanel12.setLayout(new java.awt.CardLayout());

        jPanel19.setLayout(new java.awt.GridLayout(5, 0, 0, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("SĐT:");
        jPanel19.add(jLabel10);

        ttxsdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel19.add(ttxsdt);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Địa chỉ:");
        jPanel19.add(jLabel11);

        txtdiachi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel19.add(txtdiachi);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Tên đăng nhập:");
        jPanel19.add(jLabel12);

        txttendangnhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel19.add(txttendangnhap);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Mật khẩu:");
        jPanel19.add(jLabel13);

        jpasss.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel19.add(jpasss);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Vai trò:");
        jPanel19.add(jLabel14);

        jcombovaitro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcombovaitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lý", " " }));
        jPanel19.add(jcombovaitro);

        jPanel12.add(jPanel19, "card2");

        jPanel7.add(jPanel12);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5, "card2");

        jFrame1.getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        jFrame2.setTitle("Cập nhật thông tin nhân viên");
        jFrame2.setLocation(new java.awt.Point(300, 100));
        jFrame2.setMinimumSize(new java.awt.Dimension(924, 560));

        jPanel20.setLayout(new java.awt.CardLayout(10, 10));

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cập nhật thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel21.setLayout(new java.awt.BorderLayout());

        jPanel22.setLayout(new java.awt.CardLayout());

        jPanel23.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        jPanel23.add(jPanel24);

        jPanel25.setLayout(new java.awt.CardLayout(50, 35));

        jPanel26.setLayout(new java.awt.GridLayout(1, 20, 20, 10));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Huỷ");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel26.add(jButton5);

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Xác nhận");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton6);

        jPanel25.add(jPanel26, "card2");

        jPanel23.add(jPanel25);

        jPanel22.add(jPanel23, "card2");

        jPanel21.add(jPanel22, java.awt.BorderLayout.PAGE_END);

        jPanel27.setLayout(new java.awt.GridLayout(1, 0));

        jPanel28.setLayout(new java.awt.BorderLayout());

        jPanel29.setLayout(new java.awt.CardLayout());

        jPanel30.setLayout(new java.awt.BorderLayout());

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Ảnh");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel30.add(jButton7, java.awt.BorderLayout.CENTER);

        jPanel29.add(jPanel30, "card2");

        jPanel28.add(jPanel29, java.awt.BorderLayout.PAGE_END);

        jPanel31.setLayout(new java.awt.BorderLayout());
        jPanel31.add(jlableanh1, java.awt.BorderLayout.CENTER);

        jPanel28.add(jPanel31, java.awt.BorderLayout.CENTER);

        jPanel27.add(jPanel28);

        jPanel32.setLayout(new java.awt.CardLayout());

        jPanel33.setLayout(new java.awt.GridLayout(5, 0, 0, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Mã nhân viên:");
        jPanel33.add(jLabel15);

        txtmanv1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel33.add(txtmanv1);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Tên nhân viên:");
        jPanel33.add(jLabel16);

        txttennv1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel33.add(txttennv1);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Ngày sinh:");
        jPanel33.add(jLabel17);

        datengaysinh1.setToolTipText("");
        jPanel33.add(datengaysinh1);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Gioi tính:");
        jPanel33.add(jLabel18);

        jcombogt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcombogt1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", " " }));
        jPanel33.add(jcombogt1);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Lương:");
        jPanel33.add(jLabel19);

        txtluong1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel33.add(txtluong1);

        jPanel32.add(jPanel33, "card2");

        jPanel27.add(jPanel32);

        jPanel34.setLayout(new java.awt.CardLayout());

        jPanel35.setLayout(new java.awt.GridLayout(5, 0, 0, 40));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("SĐT:");
        jPanel35.add(jLabel20);

        ttxsdt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel35.add(ttxsdt1);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Địa chỉ:");
        jPanel35.add(jLabel21);

        txtdiachi1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel35.add(txtdiachi1);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Tên đăng nhập:");
        jPanel35.add(jLabel22);

        txttendangnhap1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel35.add(txttendangnhap1);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Mật khẩu:");
        jPanel35.add(jLabel23);

        jpasss1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel35.add(jpasss1);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Vai trò:");
        jPanel35.add(jLabel24);

        jcombovaitro1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcombovaitro1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lý", " " }));
        jPanel35.add(jcombovaitro1);

        jPanel34.add(jPanel35, "card2");

        jPanel27.add(jPanel34);

        jPanel21.add(jPanel27, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel21, "card2");

        jFrame2.getContentPane().add(jPanel20, java.awt.BorderLayout.CENTER);

        setTitle("Thông tin nhân viên");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        jPanel2.setLayout(new java.awt.CardLayout(10, 10));

        txtTimHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tìm kiếm");

        jButton1.setText("Thêm");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtTimHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, "card2");

        jToolBar1.add(jPanel2);

        jPanel1.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        tablenhanvien.setModel(new javax.swing.table.DefaultTableModel(
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
        tablenhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablenhanvienMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablenhanvienMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tablenhanvien);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel36.setBackground(new java.awt.Color(171, 104, 50));
        jPanel36.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quản lý nhân viên");
        jPanel36.add(jLabel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel36, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jFrame1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".jpg");
                }
            }

            @Override
            public String getDescription() {
                return "Image File (*.jpg)";
            }
        });
        if (chooser.showOpenDialog(rootPane) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File file = chooser.getSelectedFile();
        try {
            ImageIcon icon = new ImageIcon(file.getPath());
            Image img = ImageHelperR.resize(icon.getImage(), 313, 271);
            ImageIcon resizeIcon = new ImageIcon(img);
            jlableanh.setIcon(resizeIcon);
            resonalImage = ImageHelperR.toByteArray(img, "jpg");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane, "looix");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (checkmanv()) {
            try {
                Nhanvienmodel bnv = new Nhanvienmodel();
                bnv.setManv(txtmanv.getText());
                bnv.setHotennv(txttennv.getText());
                SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                String ngaysinh = date.format(datengaysinh.getDate());
                bnv.setNgaysinh(Date.valueOf(ngaysinh));
                bnv.setGioitinh(jcombogt.getSelectedItem().toString());
                bnv.setLuong(Float.valueOf(txtluong.getText()));
                bnv.setSdt(ttxsdt.getText());
                bnv.setDiachi(txtdiachi.getText());
                bnv.setTendangnhap(txttendangnhap.getText());
                bnv.setVaitro(jcombovaitro.getSelectedItem().toString());
                bnv.setAnhcancuoc(resonalImage);
                bnv.setMatkhau(String.valueOf(jpasss.getPassword()));
                Nhanviendao dao = new Nhanviendao();
                dao.insert(bnv);
                LoadProductTable();
                JOptionPane.showMessageDialog(rootPane, "Thêm nhân viên thành công !");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jFrame1.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tablenhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablenhanvienMouseClicked
        try {
            int row = tablenhanvien.getSelectedRow();
            if (row >= 0) {
                String manv = (String) tablenhanvien.getValueAt(row, 0);
                Nhanviendao nvd = new Nhanviendao();
                Nhanvienmodel b = nvd.FindManv(manv);
                if (b != null) {
                    txtmanv1.setText(b.getManv());
                    ttxsdt1.setText(b.getSdt());
                    datengaysinh1.setDate(b.getNgaysinh());
                    txtluong1.setText(b.getLuong().toString());
                    jcombogt1.setSelectedItem(b.getGioitinh());
                    txtdiachi1.setText(b.getDiachi());
                    txttennv1.setText(b.getHotennv());
                    txtdiachi1.setText(b.getDiachi());
                    txttendangnhap1.setText(b.getTendangnhap());
                    jcombovaitro1.setSelectedItem(b.getVaitro());
                    if (b.getAnhcancuoc() != null) {
                        Image img = ImageHelperR.createImageFromByteArray(b.getAnhcancuoc(), "jpg");
                        jlableanh1.setIcon(new ImageIcon(img));
                        resonalImage = b.getAnhcancuoc();
                    } else {
//                        resonalImage = b.getAnhcancuoc();
//                        ImageIcon icon = new ImageIcon(getClass().getResource("/com/teamvietdev/qlhv/images/cute.jpg"));
//                        jlableanh1.setIcon(icon);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi !");
        }
    }//GEN-LAST:event_tablenhanvienMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn cập nhật thông tin nhân viên này ?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            Nhanvienmodel bnv = new Nhanvienmodel();
            bnv.setManv(txtmanv1.getText());
            bnv.setHotennv(txttennv1.getText());
            SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
            String ngaysinh = date.format(datengaysinh1.getDate());
            bnv.setNgaysinh(Date.valueOf(ngaysinh));
            bnv.setGioitinh(jcombogt1.getSelectedItem().toString());
            bnv.setLuong(Float.valueOf(txtluong1.getText()));
            bnv.setSdt(ttxsdt1.getText());
            bnv.setDiachi(txtdiachi1.getText());
            bnv.setTendangnhap(txttendangnhap1.getText());
            bnv.setVaitro(jcombovaitro1.getSelectedItem().toString());
            bnv.setAnhcancuoc(resonalImage);
            Nhanviendao dao = new Nhanviendao();
            dao.update(bnv);
            LoadProductTable();
            JOptionPane.showMessageDialog(rootPane, "Cập nhật nhân viên thành công !");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrame2.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".jpg");
                }
            }

            @Override
            public String getDescription() {
                return "Image File (*.jpg)";
            }
        });
        if (chooser.showOpenDialog(rootPane) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File file = chooser.getSelectedFile();
        try {
            ImageIcon icon = new ImageIcon(file.getPath());
            Image img = ImageHelperR.resize(icon.getImage(), 313, 271);
            ImageIcon resizeIcon = new ImageIcon(img);
            jlableanh1.setIcon(resizeIcon);
            resonalImage = ImageHelperR.toByteArray(img, "jpg");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane, "looix");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tablenhanvienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablenhanvienMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tablenhanvienMouseEntered

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
            java.util.logging.Logger.getLogger(Quanlynhanvien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quanlynhanvien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quanlynhanvien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quanlynhanvien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                        new Quanlynhanvien().setVisible(true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser datengaysinh;
    private com.toedter.calendar.JDateChooser datengaysinh1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox<String> jcombogt;
    private javax.swing.JComboBox<String> jcombogt1;
    private javax.swing.JComboBox<String> jcombovaitro;
    private javax.swing.JComboBox<String> jcombovaitro1;
    private javax.swing.JLabel jlableanh;
    private javax.swing.JLabel jlableanh1;
    private javax.swing.JPasswordField jpasss;
    private javax.swing.JPasswordField jpasss1;
    private javax.swing.JTable tablenhanvien;
    private javax.swing.JTextField ttxsdt;
    private javax.swing.JTextField ttxsdt1;
    private javax.swing.JTextField txtTimHoTen;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtdiachi1;
    private javax.swing.JTextField txtluong;
    private javax.swing.JTextField txtluong1;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtmanv1;
    private javax.swing.JTextField txttendangnhap;
    private javax.swing.JTextField txttendangnhap1;
    private javax.swing.JTextField txttennv;
    private javax.swing.JTextField txttennv1;
    // End of variables declaration//GEN-END:variables
}
