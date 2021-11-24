/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.DangNhapDAO;
import Entity.NguoiDung;
import TienIchHoTro.Dialog;
import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author congc
 */
public class QuenMKGV extends javax.swing.JFrame {

    Connection conn = null;
    DangNhapDAO dnDAO = new DangNhapDAO();
    List<NguoiDung> listnguoiDung = new ArrayList<>();
    Border border = BorderFactory.createLineBorder(Color.red);
    Border border1 = BorderFactory.createLineBorder(Color.black);

    public QuenMKGV() {
        initComponents();
        this.setTitle("Quên mật khẩu");
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        this.setLocationRelativeTo(null);
    }

    boolean checknull() {

        if (txt_quenMKGV.getText().equals("") && txt_MKCuGV.getText().equals("") && txt_MKMoiGV.getText().equals("")) {
            lbl_loiTK.setText("Tên đăng nhập bị trống vui lòng nhập lại!");
            lbl_loiTK.setForeground(Color.red);
            txt_quenMKGV.setBorder(border);
            lbl_loiMKCuGV.setText("Mật khẩu cũ bị trống vui lòng nhập lại!");
            lbl_loiMKCuGV.setForeground(Color.red);
            txt_MKCuGV.setBorder(border);
            lbl_loiMKMoi.setText("mật khẩu mới bị trống vui lòng nhập lại!");
            lbl_loiMKMoi.setForeground(Color.red);
            txt_MKMoiGV.setBorder(border);
            txt_quenMKGV.requestFocus();
            return false;
        }

        if (txt_quenMKGV.getText().equals("")) {
            lbl_loiTK.setText("Tên đăng nhập bị trống vui lòng nhập lại!");
            lbl_loiTK.setForeground(Color.red);
            txt_quenMKGV.setBorder(border);
            txt_quenMKGV.requestFocus();
            return false;
        }
        if (txt_MKCuGV.getText().equals("")) {
            lbl_loiMKCuGV.setText("Mật khẩu cũ bị trống vui lòng nhập lại!");
            lbl_loiMKCuGV.setForeground(Color.red);
            txt_MKCuGV.setBorder(border);
            txt_MKCuGV.requestFocus();
            return false;
        }
        if (txt_MKMoiGV.getText().equals("")) {
            lbl_loiMKMoi.setText("mật khẩu mới bị trống vui lòng nhập lại!");
            lbl_loiMKMoi.setForeground(Color.red);
            txt_MKMoiGV.setBorder(border);
            txt_MKMoiGV.requestFocus();
            return false;
        }
        return true;
    }

    boolean checkKhop() {
        NguoiDung nd = new NguoiDung();
        listnguoiDung = dnDAO.listGiangVien(nd, conn);
        if (!(txt_quenMKGV.getText().equals(nd.getTenDangNhap()) || txt_MKCuGV.getText().equals(nd.getMatKhauCu()))) {
            lbl_loi.setText("Tên đăng nhập hoặc mật khẩu cũ không đúng");
            lbl_loi.setForeground(Color.red);
            txt_quenMKGV.setBorder(border);
            txt_MKCuGV.setBorder(border);
            txt_MKMoiGV.setBorder(border);
            lbl_loiMKCuGV.setText("");
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
        jLabel5 = new javax.swing.JLabel();
        txt_quenMKGV = new javax.swing.JTextField();
        lbl_loiTK = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_MKCuGV = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        txt_MKMoiGV = new javax.swing.JPasswordField();
        lbl_loiMKCuGV = new javax.swing.JLabel();
        lbl_loiMKMoi = new javax.swing.JLabel();
        btn_doiMKGV = new javax.swing.JButton();
        btn_thoatGV = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/FORUM-ICON-USER.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Hệ thống quản lý trung tâm tiếng anh Laguage School");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Đổi mật khẩu giảng viên");

        lbl_loi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tên Đăng Nhập");

        lbl_loiTK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Mật Khẩu cũ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Mật Khẩu Mới");

        lbl_loiMKCuGV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbl_loiMKMoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_doiMKGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Accept.png"))); // NOI18N
        btn_doiMKGV.setText("Đổi mật khẩu");
        btn_doiMKGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doiMKGVActionPerformed(evt);
            }
        });

        btn_thoatGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Exit.png"))); // NOI18N
        btn_thoatGV.setText("Thoát");
        btn_thoatGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatGVActionPerformed(evt);
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
                                    .addComponent(btn_doiMKGV)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_thoatGV, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbl_loiMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_quenMKGV, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbl_loi, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_MKMoiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbl_loiMKCuGV, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_loiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_MKCuGV, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel1)))
                .addContainerGap(59, Short.MAX_VALUE))
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
                    .addComponent(txt_quenMKGV, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MKCuGV, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiMKCuGV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MKMoiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_thoatGV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_doiMKGV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btn_doiMKGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doiMKGVActionPerformed
        try {
            if (checknull()) {
NguoiDung nd = new NguoiDung();
                if (checkKhop()) {
                    txt_quenMKGV.setBorder(border1);
                    txt_MKCuGV.setBorder(border1);
                    txt_MKMoiGV.setBorder(border1);

                    lbl_loiMKCuGV.setText("");
                    lbl_loiMKMoi.setText("");
                    lbl_loiTK.setText("");

                    
                    nd.setMatKhauCu(txt_MKCuGV.getText());
                    nd.setTenDangNhap(txt_quenMKGV.getText());
                    nd.setMatKhau(txt_MKMoiGV.getText());
                    dnDAO.updateMKGV(nd, conn);
                    Dialog.alert(null, "Đổi mật khẩu thành công vui lòng đăng nhập lại");
                    GiangVienLogin gv = new GiangVienLogin();
                    gv.setVisible(true);
                    this.dispose();
                    
                }
System.out.println("Tk:" + nd.getTenDangNhap() + "\n" + "MK: " + nd.getMatKhau() + "\n" + "MK cũ: " + nd.getMatKhauCu());
            }
        } catch (Exception e) {
            Dialog.alert(null, "sai");
        }
    }//GEN-LAST:event_btn_doiMKGVActionPerformed

    private void btn_thoatGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatGVActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn thoát chứ?", "Thoát quên mật khẩu quản lý", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.NO_OPTION) {
            return;
        } else {
            GiangVienLogin gv = new GiangVienLogin();
            gv.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_thoatGVActionPerformed

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
            java.util.logging.Logger.getLogger(QuenMKGV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMKGV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMKGV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMKGV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMKGV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_doiMKGV;
    private javax.swing.JButton btn_thoatGV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_loi;
    private javax.swing.JLabel lbl_loiMKCuGV;
    private javax.swing.JLabel lbl_loiMKMoi;
    private javax.swing.JLabel lbl_loiTK;
    private javax.swing.JPasswordField txt_MKCuGV;
    private javax.swing.JPasswordField txt_MKMoiGV;
    private javax.swing.JTextField txt_quenMKGV;
    // End of variables declaration//GEN-END:variables
}
