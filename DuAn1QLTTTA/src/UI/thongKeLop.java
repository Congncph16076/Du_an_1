/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entity.Lop;
import com.sun.source.tree.ContinueTree;
import java.awt.Desktop;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author congc
 */
public class thongKeLop extends javax.swing.JInternalFrame {

    Connection conn = null;

    /**
     * Creates new form thongKeDangki
     */
    public thongKeLop() {
        initComponents();
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        initCBCthang();
    }
    
    void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void initCBCthang() {
        this.cbc_thang.removeAllItems();
        String[] caThi = new String[]{
            "Tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
        };
        for (int i = 0; i < caThi.length; i++) {
            cbc_thang.addItem(caThi[i]);
        }
    }

    protected void fillTab1() {

        String sql = "SELECT TENLOP,COUNT(TENLOP)[soLuong],MONTH(NGAYNHAPHOC) [thang],YEAR(NGAYNHAPHOC) [nam] FROM dbo.LOP\n"
                + "GROUP BY TENLOP,NGAYNHAPHOC\n"
                + "HAVING YEAR(NGAYNHAPHOC) = " + Year_lop.getYear();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            DefaultTableModel tbl1 = (DefaultTableModel) tbl_soLuong.getModel();
            tbl1.setRowCount(0);
            while (rs.next()) {

                String lop = rs.getString("TENLOP");
                int soLuong = rs.getInt("soLuong");
                int thang = rs.getInt("thang");
                int nam = rs.getInt("nam");
                tbl1.addRow(new Object[]{
                    lop, soLuong, thang, nam
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void filltabYear() {
        String sql = "SELECT DAY(NGAYDANGKI) [ngay],MONTH(NGAYDANGKI) [thang],COUNT(madangki)[dangKi] FROM dbo.DANGKI\n"
                + "GROUP BY NGAYDANGKI\n"
                + "HAVING YEAR(NGAYDANGKI) = " + Year_dangKi.getYear();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            DefaultTableModel tbl1 = (DefaultTableModel) tbl_dangKi.getModel();
            tbl1.setRowCount(0);
            while (rs.next()) {
                int ngay = rs.getInt("ngay");
                int thang = rs.getInt("thang");
                int soLuong = rs.getInt("dangKi");

                tbl1.addRow(new Object[]{
                    ngay, thang, soLuong
                });

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void fillTabYearMonth() {
        String sql = "SELECT DAY(NGAYDANGKI) [ngay],MONTH(NGAYDANGKI) [thang],COUNT(madangki)[dangKi] FROM dbo.DANGKI\n"
                + "GROUP BY NGAYDANGKI\n"
                + "HAVING YEAR(NGAYDANGKI) = " + Year_dangKi.getYear() + "AND MONTH(NGAYDANGKI) = " + cbc_thang.getSelectedIndex();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            DefaultTableModel tbl1 = (DefaultTableModel) tbl_dangKi.getModel();
            tbl1.setRowCount(0);
            while (rs.next()) {
                int ngay = rs.getInt("ngay");
                int thang = rs.getInt("thang");
                int soLuong = rs.getInt("dangKi");
                tbl1.addRow(new Object[]{
                    ngay, thang, soLuong
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_soLuong = new javax.swing.JTable();
        Year_lop = new com.toedter.calendar.JYearChooser();
        jButton1 = new javax.swing.JButton();
        btn_xuatExcel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Year_dangKi = new com.toedter.calendar.JYearChooser();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_dangKi = new javax.swing.JTable();
        cbc_thang = new javax.swing.JComboBox<>();
        btn_dangKi = new javax.swing.JButton();

        setClosable(true);
        setTitle("Thống kê");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel2.setBackground(new java.awt.Color(51, 255, 204));

        tbl_soLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên lớp", "Số lượng", "Tháng", "Năm"
            }
        ));
        jScrollPane1.setViewportView(tbl_soLuong);
        if (tbl_soLuong.getColumnModel().getColumnCount() > 0) {
            tbl_soLuong.getColumnModel().getColumn(1).setResizable(false);
            tbl_soLuong.getColumnModel().getColumn(1).setHeaderValue("Số lượng");
            tbl_soLuong.getColumnModel().getColumn(2).setHeaderValue("Tháng");
            tbl_soLuong.getColumnModel().getColumn(3).setHeaderValue("Năm");
        }

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("Hiển thị ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_xuatExcel.setText("Xuất Excel");
        btn_xuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Year_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_xuatExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Year_lop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_xuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("thống kê lớp theo năm", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 255, 204));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setText("Hiển thị ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tbl_dangKi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ngày", "Tháng", "Số lượng"
            }
        ));
        jScrollPane2.setViewportView(tbl_dangKi);

        cbc_thang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_dangKi.setText("Xuất File Excel");
        btn_dangKi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangKiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Year_dangKi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbc_thang, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_dangKi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Year_dangKi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(cbc_thang)
                    .addComponent(btn_dangKi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Thống kê số lượng học viên đăng kí ", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (Year_dangKi.getYear() == Year_dangKi.getYear()) {
            filltabYear();
            if (Year_dangKi.getYear() == Year_dangKi.getYear() && cbc_thang.getSelectedIndex() > 0) {
                fillTabYearMonth();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fillTab1();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_xuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatExcelActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Thống kê lớp theo năm");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tbl_soLuong.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tbl_soLuong.getColumnName(i));
                }

                for (int i = 0; i < tbl_soLuong.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < tbl_soLuong.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        if (tbl_soLuong.getValueAt(i, j) != null) {
                            cell.setCellValue(tbl_soLuong.getValueAt(i, j).toString());
                        }
                    }
                }
                FileOutputStream fos = new FileOutputStream(new File(saveFile.toString()));
                wb.write(fos);
                wb.close();
                fos.close();
                openFile(saveFile.toString());
            }else{
                JOptionPane.showMessageDialog(null,"Error");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_xuatExcelActionPerformed

    private void btn_dangKiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangKiActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Thống kê Đăng kí");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tbl_dangKi.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tbl_dangKi.getColumnName(i));
                }

                for (int i = 0; i < tbl_dangKi.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < tbl_dangKi.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        if (tbl_dangKi.getValueAt(i, j) != null) {
                            cell.setCellValue(tbl_dangKi.getValueAt(i, j).toString());
                        }
                    }
                }
                FileOutputStream fos = new FileOutputStream(new File(saveFile.toString()));
                wb.write(fos);
                wb.close();
                fos.close();
                openFile(saveFile.toString());
            }else{
                JOptionPane.showMessageDialog(null,"Error");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_dangKiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser Year_dangKi;
    private com.toedter.calendar.JYearChooser Year_lop;
    private javax.swing.JButton btn_dangKi;
    private javax.swing.JButton btn_xuatExcel;
    private javax.swing.JComboBox<String> cbc_thang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbl_dangKi;
    private javax.swing.JTable tbl_soLuong;
    // End of variables declaration//GEN-END:variables
}
