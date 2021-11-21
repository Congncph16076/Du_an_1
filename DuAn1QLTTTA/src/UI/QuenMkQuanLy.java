/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.DangNhapDAO;
import Entity.NguoiDung;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author congc
 */
public class QuenMkQuanLy extends javax.swing.JFrame {

    Connection conn = null;
    DangNhapDAO dnDAO = new DangNhapDAO();
    List<NguoiDung> listnguoiDung = new ArrayList<>();
    Border border = BorderFactory.createLineBorder(Color.red);
    Border border1 = BorderFactory.createLineBorder(Color.black);

    public QuenMkQuanLy() {
        initComponents();
        this.setTitle("Quên mật khẩu");
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        this.setLocationRelativeTo(null);
    }

    boolean checknull() {

        if (txt_quenMKQL.getText().equals("") && txt_MKCu.getText().equals("") && txt_MKMoi.getText().equals("")) {
            lbl_loiTK.setText("Tên đăng nhập bị trống vui lòng nhập lại!");
            lbl_loiTK.setForeground(Color.red);
            txt_quenMKQL.setBorder(border);
            lbl_loiMKCu.setText("Mật khẩu cũ bị trống vui lòng nhập lại!");
            lbl_loiMKCu.setForeground(Color.red);
            txt_MKCu.setBorder(border);
            lbl_loiMKMoi.setText("mật khẩu mới bị trống vui lòng nhập lại!");
            lbl_loiMKMoi.setForeground(Color.red);
            txt_MKMoi.setBorder(border);
            txt_quenMKQL.requestFocus();
            return false;
        }

        if (txt_quenMKQL.getText().equals("")) {
            lbl_loiTK.setText("Tên đăng nhập bị trống vui lòng nhập lại!");
            lbl_loiTK.setForeground(Color.red);
            txt_quenMKQL.setBorder(border);
            txt_quenMKQL.requestFocus();
            return false;
        }
        if (txt_MKCu.getText().equals("")) {
            lbl_loiMKCu.setText("Mật khẩu cũ bị trống vui lòng nhập lại!");
            lbl_loiMKCu.setForeground(Color.red);
            txt_MKCu.setBorder(border);
            txt_MKCu.requestFocus();
            return false;
        }
        if (txt_MKMoi.getText().equals("")) {
            lbl_loiMKMoi.setText("mật khẩu mới bị trống vui lòng nhập lại!");
            lbl_loiMKMoi.setForeground(Color.red);
            txt_MKMoi.setBorder(border);
            txt_MKMoi.requestFocus();
            return false;
        }
        return true;
    }

    boolean checkKhop() {
        NguoiDung nd = new NguoiDung();
        listnguoiDung = dnDAO.listQuanLy(nd, conn);
        if (!(txt_quenMKQL.getText().equals(nd.getTenDangNhap())
                || txt_MKCu.getText().equals(nd.getMatKhauCu()))) {
            lbl_loi.setText("Tên đăng nhập hoặc mật khẩu cũ không đúng");
            lbl_loi.setForeground(Color.red);
            txt_quenMKQL.setBorder(border);
            txt_MKCu.setBorder(border);
            txt_MKMoi.setBorder(border);
            lbl_loiMKCu.setText("");
            lbl_loiMKMoi.setText("");
            lbl_loiTK.setText("");
            return false;

        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_loi = new javax.swing.JLabel();
        txt_quenMKQL = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lbl_loiTK = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_MKCu = new javax.swing.JPasswordField();
        lbl_loiMKCu = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_MKMoi = new javax.swing.JPasswordField();
        lbl_loiMKMoi = new javax.swing.JLabel();
        btn_doiMK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/FORUM-ICON-USER.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Hệ thống quản lý trung tâm tiếng anh Laguage School");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Đổi mật khẩu quản lý");

        lbl_loi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Exit.png"))); // NOI18N
        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tên Đăng Nhập");

        lbl_loiTK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Mật Khẩu cũ");

        lbl_loiMKCu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Mật Khẩu Mới");

        lbl_loiMKMoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_doiMK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Accept.png"))); // NOI18N
        btn_doiMK.setText("Đổi mật khẩu");
        btn_doiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doiMKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(jLabel3)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btn_doiMK)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbl_loiMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_quenMKQL, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbl_loi, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_MKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbl_loiMKCu, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_loiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_MKCu, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel1)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_quenMKQL, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MKCu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiMKCu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_doiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn thoát chứ?", "Thoát quên mật khẩu quản lý", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.NO_OPTION) {
            return;
        } else {
            QuanLyLogin ql = new QuanLyLogin();
            ql.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_doiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doiMKActionPerformed
        try {
            if (checknull()) {
                if (checkKhop()) {
                    txt_quenMKQL.setBorder(border1);
                    txt_MKCu.setBorder(border1);
                    txt_MKMoi.setBorder(border1);
                    lbl_loiMKCu.setText("");
                    lbl_loiMKMoi.setText("");
                    lbl_loiTK.setText("");
                    NguoiDung nd = new NguoiDung();
                    nd.setMatKhauCu(txt_MKCu.getText());
                    nd.setTenDangNhap(txt_quenMKQL.getText());
                    nd.setMatKhau(txt_MKMoi.getText());
                    dnDAO.updateMK(nd, conn);

                    System.out.println("Tk:" + nd.getTenDangNhap() + "\n" + "MK: " + nd.getMatKhau() + "\n" + "MK cũ: " + nd.getMatKhauCu());
                }
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btn_doiMKActionPerformed

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
            java.util.logging.Logger.getLogger(QuenMkQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMkQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMkQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMkQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMkQuanLy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_doiMK;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_loi;
    private javax.swing.JLabel lbl_loiMKCu;
    private javax.swing.JLabel lbl_loiMKMoi;
    private javax.swing.JLabel lbl_loiTK;
    private javax.swing.JPasswordField txt_MKCu;
    private javax.swing.JPasswordField txt_MKMoi;
    private javax.swing.JTextField txt_quenMKQL;
    // End of variables declaration//GEN-END:variables
}
