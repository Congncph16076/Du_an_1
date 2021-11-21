/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.DangNhapDAO;
import Entity.NguoiDung;
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
public class GiangVienLogin extends javax.swing.JFrame {

    DangNhapDAO dnDAO = new DangNhapDAO();
    List<NguoiDung> listND = new ArrayList<>();
    Connection conn = null;
    Border border = BorderFactory.createLineBorder(Color.red);
    Border border1 = BorderFactory.createLineBorder(Color.black);

    public GiangVienLogin() {
        initComponents();
        this.setTitle("Đăng nhập");
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        this.setLocationRelativeTo(null);
    }

    boolean checkNull() {
        if (txt_Tai_Khoan_Giang_Vien.getText().equals("") && txt_PassGiangVien.getText().equals("")) {
            lbl_loiTKGV.setText("Tài khoản của giảng viên bị trống vui lòng nhập lại!");
            txt_Tai_Khoan_Giang_Vien.setBorder(border);
            lbl_loiTKGV.setForeground(Color.red);
            lbl_loiPassGV.setText("Mật khẩu của giảng viên bị trống vui lòng nhập lại!");
            txt_PassGiangVien.setBorder(border);
            lbl_loiPassGV.setForeground(Color.red);
            txt_Tai_Khoan_Giang_Vien.requestFocus();
            return false;
        }

        if (txt_Tai_Khoan_Giang_Vien.getText().equals("")) {
            lbl_loiTKGV.setText("Tài khoản của giảng viên bị trống vui lòng nhập lại!");
            txt_Tai_Khoan_Giang_Vien.setBorder(border);
            lbl_loiTKGV.setForeground(Color.red);
            txt_Tai_Khoan_Giang_Vien.requestFocus();
            return false;
        }

        if (txt_PassGiangVien.getText().equals("")) {
            lbl_loiPassGV.setText("Mật khẩu của giảng viên bị trống vui lòng nhập lại!");
            txt_PassGiangVien.setBorder(border);
            lbl_loiPassGV.setForeground(Color.red);
            txt_PassGiangVien.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_Tai_Khoan_Giang_Vien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_PassGiangVien = new javax.swing.JPasswordField();
        btn_LoginGiangVien = new javax.swing.JButton();
        btn_ExitGiangVien = new javax.swing.JButton();
        lbl_loiGV = new javax.swing.JLabel();
        lbl_loiTKGV = new javax.swing.JLabel();
        lbl_loiPassGV = new javax.swing.JLabel();
        lbl_doimk = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/FORUM-ICON-USER.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Hệ thống quản lý trung tâm tiếng anh Laguage school");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Quản Lý");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tài Khoản:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Mật Khẩu:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/dangnhap.png"))); // NOI18N
        jButton1.setText("Đăng Nhập");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/exitot.png"))); // NOI18N
        jButton2.setText("Thoát");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/FORUM-ICON-USER.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Hệ thống quản lý trung tâm tiếng anh Laguage school");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Giảng Viên");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Tài Khoản:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Mật Khẩu:");

        btn_LoginGiangVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/dangnhap.png"))); // NOI18N
        btn_LoginGiangVien.setText("Đăng Nhập");
        btn_LoginGiangVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LoginGiangVienActionPerformed(evt);
            }
        });

        btn_ExitGiangVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/exitot.png"))); // NOI18N
        btn_ExitGiangVien.setText("Thoát");
        btn_ExitGiangVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExitGiangVienActionPerformed(evt);
            }
        });

        lbl_doimk.setForeground(new java.awt.Color(255, 51, 51));
        lbl_doimk.setText("Đổi mật khẩu?");
        lbl_doimk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_doimkMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbl_loiGV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_Tai_Khoan_Giang_Vien, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_PassGiangVien))
                                .addComponent(lbl_loiTKGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_loiPassGV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel8)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_doimk, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_LoginGiangVien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ExitGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Tai_Khoan_Giang_Vien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_loiTKGV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_PassGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiPassGV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_LoginGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ExitGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_doimk)
                .addGap(7, 7, 7))
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

    private void btn_ExitGiangVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExitGiangVienActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chứ?", "Thoát đăng nhập giảng viên", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.NO_OPTION) {
            return;
        } else {
            Man_Hinh_Chao manhinh = new Man_Hinh_Chao();
            manhinh.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_ExitGiangVienActionPerformed

    private void btn_LoginGiangVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LoginGiangVienActionPerformed
       
            if (checkNull()) {
                txt_Tai_Khoan_Giang_Vien.setBorder(border1);
                txt_PassGiangVien.setBorder(border1);
                lbl_loiGV.setText("");
                lbl_loiTKGV.setText("");
                lbl_loiPassGV.setText("");
                NguoiDung nd = new NguoiDung();
                nd.setTenDangNhap(txt_Tai_Khoan_Giang_Vien.getText());
                nd.setMatKhau(txt_PassGiangVien.getText());
                listND = dnDAO.dangNhapGiangVien(nd, conn);
                if (listND.size() > 0) {
                    ManHinhLamViecGV manHinh = new ManHinhLamViecGV(txt_Tai_Khoan_Giang_Vien.getText());
                    manHinh.setVisible(true);
                    this.dispose();
                    
                System.out.println("tài khoản: " + nd.getTenDangNhap() + "\n" + "mật khẩu: " + nd.getMatKhau());
                System.out.println(listND);
                }else{
                     lbl_loiGV.setText("Tên đăng nhập hoặc mật khẩu không đúng");
                txt_Tai_Khoan_Giang_Vien.setBorder(border);
                txt_PassGiangVien.setBorder(border);
                lbl_loiGV.setForeground(Color.red);
                txt_Tai_Khoan_Giang_Vien.setText("");
                txt_PassGiangVien.setText("");
                txt_Tai_Khoan_Giang_Vien.requestFocus();
                }
            }
        
    }//GEN-LAST:event_btn_LoginGiangVienActionPerformed

    private void lbl_doimkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_doimkMouseClicked
        QuenMKGV quen = new QuenMKGV();
        quen.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_doimkMouseClicked

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
            java.util.logging.Logger.getLogger(GiangVienLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiangVienLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiangVienLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiangVienLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiangVienLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ExitGiangVien;
    private javax.swing.JButton btn_LoginGiangVien;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_doimk;
    private javax.swing.JLabel lbl_loiGV;
    private javax.swing.JLabel lbl_loiPassGV;
    private javax.swing.JLabel lbl_loiTKGV;
    private javax.swing.JPasswordField txt_PassGiangVien;
    private javax.swing.JTextField txt_Tai_Khoan_Giang_Vien;
    // End of variables declaration//GEN-END:variables
}
