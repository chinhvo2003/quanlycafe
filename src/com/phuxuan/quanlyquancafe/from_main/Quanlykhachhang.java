/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.phuxuan.quanlyquancafe.from_main;

import Dialogchek.sheardatta;
import com.phuxuan.quanlycoffee.dao.Khachhangdao;
import com.phuxuan.quanlycoffee.dao.Thucdondao;
import com.phuxuan.quanlycoffee.model.Khachhangmodel;
import com.phuxuan.quanlycoffee.model.Thucdonmodel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author chinh
 */
public class Quanlykhachhang extends javax.swing.JFrame {

    ArrayList<Khachhangmodel> khachhangmodels;
    private DefaultTableModel tableModel;
    private TableRowSorter sorter;
    private byte[] resonalImage;

    /**
     * Creates new form Quanlykhachhang
     */
    public Quanlykhachhang() throws SQLException, ClassNotFoundException {
        initComponents();
        initTable();
        LoadProductTable();
        hh();
        chuotphaijtable();
        pro();
    }

    private void pro() {
        
        if (sheardatta.nguoiDangNhap.getVaitro().equals("Quản lý")) {

        } else if (sheardatta.nguoiDangNhap.getVaitro().equals("Nhân viên")) {
        }
    }

    private void initTable() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã khách hàng", "Tên khách hàng", "Địa chỉ", "SĐT"});
        tablentd.setModel(tableModel);
    }

    private void LoadProductTable() throws SQLException, ClassNotFoundException {
        khachhangmodels = Khachhangdao.getAll();
        sorter = new TableRowSorter(tableModel);
        tablentd.setRowSorter(sorter);
        tableModel.setRowCount(0);
        for (Khachhangmodel m : khachhangmodels) {
            Object[] object = new Object[]{m.getMakh(), m.getHotenkh(), m.getDiachi(), m.getSdt()};
            tableModel.addRow(object);
        }
        tableModel.fireTableDataChanged();
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
        tablentd.addMouseListener(new MouseAdapter() {
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
                                Khachhangmodel bnv = new Khachhangmodel();
                                bnv.setMakh(Integer.parseInt(txtmakh.getText()));
                                Khachhangdao dao = new Khachhangdao();
                                dao.delete(bnv);
                                LoadProductTable();
                                JOptionPane.showMessageDialog(rootPane, "Xoá khách hàng thành công !");
                            } catch (SQLException ex) {
                                Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    xoa.setForeground(Color.RED);
                    popup.add(xoa);
                    popup.show(tablentd, e.getX(), e.getY());
                }
            }
        });
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
        jPanel9 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtmakh1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txthtkh2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtdaichi2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdienthoai2 = new javax.swing.JTextField();
        jFrame2 = new javax.swing.JFrame();
        jPanel12 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtmakh = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txthtkh = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtdiachi = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
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
        tablentd = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jFrame1.setTitle("Thêm thông tin nhân viên");
        jFrame1.setLocation(new java.awt.Point(300, 100));
        jFrame1.setMinimumSize(new java.awt.Dimension(705, 548));

        jPanel4.setLayout(new java.awt.CardLayout(10, 10));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.CardLayout());

        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
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

        jPanel9.setLayout(new java.awt.CardLayout(10, 5));

        jPanel18.setLayout(new java.awt.GridLayout(5, 0, 0, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Mã khách hàng:");
        jPanel18.add(jLabel6);

        txtmakh1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel18.add(txtmakh1);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Họ tên khách hàng:");
        jPanel18.add(jLabel7);

        txthtkh2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel18.add(txthtkh2);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ:");
        jPanel18.add(jLabel9);
        jPanel18.add(txtdaichi2);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Điện thoại:");
        jPanel18.add(jLabel5);

        txtdienthoai2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel18.add(txtdienthoai2);

        jPanel9.add(jPanel18, "card2");

        jPanel7.add(jPanel9);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5, "card2");

        jFrame1.getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        jFrame2.setTitle("Cập nhật thông tin nhân viên");
        jFrame2.setLocation(new java.awt.Point(300, 100));
        jFrame2.setMinimumSize(new java.awt.Dimension(705, 548));

        jPanel12.setLayout(new java.awt.CardLayout(10, 10));

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cập nhât thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel36.setLayout(new java.awt.CardLayout());

        jPanel37.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        jPanel37.add(jPanel38);

        jPanel39.setLayout(new java.awt.CardLayout(50, 35));

        jPanel40.setLayout(new java.awt.GridLayout(1, 20, 20, 10));

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setText("Huỷ");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel40.add(jButton8);

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setText("Xác nhận");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel40.add(jButton9);

        jPanel39.add(jPanel40, "card2");

        jPanel37.add(jPanel39);

        jPanel36.add(jPanel37, "card2");

        jPanel19.add(jPanel36, java.awt.BorderLayout.PAGE_END);

        jPanel41.setLayout(new java.awt.GridLayout(1, 0));

        jPanel46.setLayout(new java.awt.CardLayout(10, 5));

        jPanel47.setLayout(new java.awt.GridLayout(5, 0, 0, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Mã khách hàng:");
        jPanel47.add(jLabel8);

        txtmakh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel47.add(txtmakh);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Họ tên khách hàng:");
        jPanel47.add(jLabel10);

        txthtkh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel47.add(txthtkh);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Địa chỉ:");
        jPanel47.add(jLabel11);
        jPanel47.add(txtdiachi);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Điện thoại:");
        jPanel47.add(jLabel12);

        txtsdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel47.add(txtsdt);

        jPanel46.add(jPanel47, "card2");

        jPanel41.add(jPanel46);

        jPanel19.add(jPanel41, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel19, "card2");

        jFrame2.getContentPane().add(jPanel12, java.awt.BorderLayout.CENTER);

        setTitle("Quản lý khách hàng");

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

        tablentd.setModel(new javax.swing.table.DefaultTableModel(
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
        tablentd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablentdMouseClicked(evt);
            }
        });
        tablentd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablentdKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablentd);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(171, 104, 50));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quản lý khách hàng");
        jPanel8.add(jLabel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel8, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jFrame1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablentdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablentdMouseClicked
        try {
            int row = tablentd.getSelectedRow();
            if (row >= 0) {
                int makh = (int) tablentd.getValueAt(row, 0);
                Khachhangdao nvd = new Khachhangdao();
                Khachhangmodel b = nvd.FindManv(makh);
                if (b != null) {
                    txtmakh.setText(Integer.toString(b.getMakh()));
                    txthtkh.setText(b.getHotenkh());
                    txtdiachi.setText(b.getDiachi());
                    txtsdt.setText(b.getSdt());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi !");
        }
    }//GEN-LAST:event_tablentdMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Khachhangmodel bnv = new Khachhangmodel();
            //            bnv.setMatd(Integer.parseInt(txtmatd.getText()));
            bnv.setHotenkh(txthtkh2.getText());
            bnv.setDiachi(txtdaichi2.getText());
            bnv.setSdt(txtdienthoai2.getText());
            Khachhangdao dao = new Khachhangdao();
            dao.insert(bnv);
            LoadProductTable();
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công !");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrame1.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn cập nhật thông tin khách hàng này ?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            Khachhangmodel bnv = new Khachhangmodel();
            bnv.setMakh(Integer.parseInt(txtmakh.getText()));
            bnv.setHotenkh(txthtkh.getText());
            bnv.setDiachi(txtdaichi2.getText());
            bnv.setSdt(txtsdt.getText());
            Khachhangdao dao = new Khachhangdao();
            dao.update(bnv);
            LoadProductTable();
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công !");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrame2.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tablentdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablentdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablentdKeyPressed

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
            java.util.logging.Logger.getLogger(Quanlykhachhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quanlykhachhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quanlykhachhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quanlykhachhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dangnhap h = new Dangnhap();
                    h.setVisible(true);
                    if (sheardatta.nguoiDangNhap.getVaitro().equals("Quản lý")) {
                        new Quanlykhachhang().setVisible(true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Quanlykhachhang.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Quanlykhachhang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tablentd;
    private javax.swing.JTextField txtTimHoTen;
    private javax.swing.JTextField txtdaichi2;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtdienthoai2;
    private javax.swing.JTextField txthtkh;
    private javax.swing.JTextField txthtkh2;
    private javax.swing.JTextField txtmakh;
    private javax.swing.JTextField txtmakh1;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}
