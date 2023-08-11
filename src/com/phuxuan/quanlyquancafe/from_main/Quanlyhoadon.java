/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.phuxuan.quanlyquancafe.from_main;

import Dialogchek.sheardatta;
import com.phuxuan.quanlycoffee.dao.Hoadondao;
import com.phuxuan.quanlycoffee.dao.Khachhangdao;
import com.phuxuan.quanlycoffee.model.Hoadonmodel;
import com.phuxuan.quanlycoffee.model.Khachhangmodel;
import com.phuxuan.quanlyquancafe.connectJDBC.Databaseee;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
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
public class Quanlyhoadon extends javax.swing.JFrame {

    ArrayList<Hoadonmodel> hoadonmodels;
    private DefaultTableModel tableModel;
    private TableRowSorter sorter;

    /**
     * Creates new form Quanlyhoadon
     */
    public Quanlyhoadon() throws ClassNotFoundException, SQLException {
        initComponents();
//        initTable();
//        LoadProductTable();
        chuotphaijtable();
        showdate("", "");
        pro();
    }

    private void initTable() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã hoá đơn", "Mã bàn", "Nhân viên", "Ngày bán", "Tổng tiền", "Trạng thái"});
        tablentd.setModel(tableModel);
    }

    private void pro() {
        if (sheardatta.nguoiDangNhap.getVaitro().equals("Quản lý")) {

        } else if (sheardatta.nguoiDangNhap.getVaitro().equals("Nhân viên")) {
        }
    }

    private void LoadProductTable() throws SQLException, ClassNotFoundException {
        hoadonmodels = Hoadondao.getAll();
        sorter = new TableRowSorter(tableModel);
        tablentd.setRowSorter(sorter);
        tableModel.setRowCount(0);
        for (Hoadonmodel m : hoadonmodels) {
            Object[] object = new Object[]{m.getMahd(), m.getMaban(), m.getManv(), m.getNgayban(), m.getTongtien(), m.getTrangthai()};
            tableModel.addRow(object);
        }
        tableModel.fireTableDataChanged();
    }

    public void showdate(String d1, String d2) {
        try {
            Connection conn = Databaseee.getConnection();
            PreparedStatement st;
            ResultSet rs;
            try {
                if (d1.equals("") || d2.equals("")) {
                    st = conn.prepareStatement("select mahd,maban,nhan_vien.hotennv,ngayban,tongtien,trangthai from hoa_don left join nhan_vien on hoa_don.manv = nhan_vien.manv");
                } else {
                    st = conn.prepareStatement("select mahd,maban,nhan_vien.hotennv,ngayban,tongtien,trangthai from hoa_don left join nhan_vien on hoa_don.manv = nhan_vien.manv where ngayban between ? and ?");
                    st.setString(1, d1);
                    st.setString(2, d2);
                }
                rs = st.executeQuery();
                DefaultTableModel model = (DefaultTableModel) tablentd.getModel();
                Object[] row;

                while (rs.next()) {
                    row = new Object[6];
                    row[0] = rs.getInt(1);
                    row[1] = rs.getInt(2);
                    row[2] = rs.getString(3);
                    row[3] = rs.getDate(4);
                    row[4] = rs.getDouble(5);
                    row[5] = rs.getString(6);
                    model.addRow(row);
                }
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(Quanlyhoadon.class.getName()).log(Level.SEVERE, null, ex);
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
//                            jFrame2.setVisible(true);
                        }
                    });
                    capnhat.setForeground(Color.ORANGE);
                    popup.add(capnhat);
                    JMenuItem xoa = new JMenuItem("Xoá");
                    xoa.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
//                            try {
//                                Khachhangmodel bnv = new Khachhangmodel();
//                                bnv.setMakh(Integer.parseInt(txtmakh.getText()));
//                                Khachhangdao dao = new Khachhangdao();
//                                dao.delete(bnv);
//                                LoadProductTable();
//                                JOptionPane.showMessageDialog(rootPane, "Xoá khách hàng thành công !");
//                            } catch (SQLException ex) {
//                                Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
//                            } catch (ClassNotFoundException ex) {
//                                Logger.getLogger(Quanlynhanvien.class.getName()).log(Level.SEVERE, null, ex);
//                            }
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
        lablehoadon = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablentd = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jFrame1.setTitle("Chi tiết hoá đơn");
        jFrame1.setLocation(new java.awt.Point(420, 200));
        jFrame1.setMinimumSize(new java.awt.Dimension(686, 313));

        jPanel4.setLayout(new java.awt.BorderLayout());

        lablehoadon.setFont(new java.awt.Font("Segoe UI", 1, 3)); // NOI18N
        lablehoadon.setForeground(new java.awt.Color(255, 255, 255));
        lablehoadon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lablehoadon.setText(" cd");
        jPanel4.add(lablehoadon, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new java.awt.CardLayout(20, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        jPanel6.add(jScrollPane2, "card2");

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jFrame1.getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        setTitle("Quản lý hoá đơn");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm theo ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel2.setLayout(new java.awt.CardLayout(10, 10));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Tìm kiếm");
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
                .addGap(72, 72, 72)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jButton1)
                .addContainerGap(341, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel2.add(jPanel3, "card2");

        jToolBar1.add(jPanel2);

        jPanel1.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        tablentd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hoá đơn", "Mã bàn", "Nhân viên", "Ngày bán", "Tổng tiền", "Trạng thái"
            }
        ));
        tablentd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablentdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablentd);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel7.setBackground(new java.awt.Color(171, 104, 50));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quản lý hoá đơn");
        jPanel7.add(jLabel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel7, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablentdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablentdMouseClicked
        try {
            try {
                int row = tablentd.getSelectedRow();
                if (row >= 0) {
                    int mahd = (int) tablentd.getValueAt(row, 0);
                    Hoadondao nvd = new Hoadondao();
                    Hoadonmodel b = nvd.FindManv(mahd);
                    if (nvd != null) {
                        lablehoadon.setText(Integer.valueOf(b.getMahd()).toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "Lỗi !");
            }
            layCTHOADON();
            jFrame1.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Quanlyhoadon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quanlyhoadon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tablentdMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            tablentd.setModel(new DefaultTableModel(null, new Object[]{"Mã hoá đơn", "Mã bàn", "Tên nhân viên", "Ngày bán", "Tổng tiền", "Trạng thái"}));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = df.format(jDateChooser1.getDate());
            String date2 = df.format(jDateChooser2.getDate());
            showdate(date1, date2);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Quanlyhoadon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quanlyhoadon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quanlyhoadon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quanlyhoadon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dangnhap h = new Dangnhap();
                    h.setVisible(true);
                    if (sheardatta.nguoiDangNhap.getVaitro().equals("Quản lý")) {
                        new Quanlyhoadon().setVisible(true);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Quanlyhoadon.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Quanlyhoadon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lablehoadon;
    private javax.swing.JTable tablentd;
    // End of variables declaration//GEN-END:variables

    private void layCTHOADON() throws SQLException, ClassNotFoundException {
        Connection conn = Databaseee.getConnection();
        try {
            String sqll = "select mact,tentd,soluongban,giaban,thanhtien from ct_hoadon left join thuc_don on ct_hoadon.matd = thuc_don.matd where mahd =" + lablehoadon.getText() + "";
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
            jTable1.setModel(model);
        } catch (SQLException e) {
        }
    }
}
