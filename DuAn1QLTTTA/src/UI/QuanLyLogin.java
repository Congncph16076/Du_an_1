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
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author congc
 */
public class QuanLyLogin extends javax.swing.JFrame {
    
    DangNhapDAO dnDAO = new DangNhapDAO();
    List<NguoiDung> listND = new ArrayList<>();
    Connection conn = null;
//    String tk = null;
//    String password = null;
//
//    public QuanLyLogin(String tk, String password) {
//        initComponents();
//        this.tk = tk;
//        this.password = password;
//        tk= txt_TaiKhoanQuanLy.getText();
//        password= txt_TaiKhoanQuanLy.getText();
//    }

    public QuanLyLogin() {
        initComponents();
        this.setTitle("Đăng nhập");
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        this.setLocationRelativeTo(null);
    }
    Border border = BorderFactory.createLineBorder(Color.red);
    Border border1 = BorderFactory.createLineBorder(Color.black);

    boolean checknull() {
        
        if (txt_TaiKhoanQuanLy.getText().equals("") && txt_PassQuanLy.getText().equals("")) {
            lbl_loiTKQL.setText("Tên đăng nhập đang bị trống vui lòng nhập lại!");
            lbl_loiPassQuanLy.setText("Mật khẩu đang bị trống vui lòng nhập lại!");
            txt_TaiKhoanQuanLy.setBorder(border);
            txt_PassQuanLy.setBorder(border);
            lbl_loiPassQuanLy.setForeground(Color.red);
            lbl_loiTKQL.setForeground(Color.red);
            txt_TaiKhoanQuanLy.requestFocus();
            return false;
        }
        if (txt_TaiKhoanQuanLy.getText().equals("")) {
            lbl_loiTKQL.setText("Tên đăng nhập đang bị trống vui lòng nhập lại!");
            txt_TaiKhoanQuanLy.setBorder(border);
            lbl_loiTKQL.setForeground(Color.red);
            txt_TaiKhoanQuanLy.requestFocus();
            return false;
        }
        
        if (txt_PassQuanLy.getText().equals("")) {
            lbl_loiPassQuanLy.setText("Mật khẩu đang bị trống vui lòng nhập lại!");
            txt_PassQuanLy.setBorder(border);
            lbl_loiPassQuanLy.setForeground(Color.red);
            txt_PassQuanLy.requestFocus();
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
        jLabel4 = new javax.swing.JLabel();
        txt_TaiKhoanQuanLy = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_PassQuanLy = new javax.swing.JPasswordField();
        btn_LoginQuanLy = new javax.swing.JButton();
        btn_ThoatQuanLy = new javax.swing.JButton();
        lbl_loiPassQuanLy = new javax.swing.JLabel();
        lbl_loiTKQL = new javax.swing.JLabel();
        lbl_notTrung = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/FORUM-ICON-USER.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Hệ thống quản lý trung tâm tiếng anh Laguage school");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Quản Lý");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tài Khoản:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Mật Khẩu:");

        txt_PassQuanLy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_PassQuanLyKeyPressed(evt);
            }
        });

        btn_LoginQuanLy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/dangnhap.png"))); // NOI18N
        btn_LoginQuanLy.setText("Đăng Nhập");
        btn_LoginQuanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LoginQuanLyActionPerformed(evt);
            }
        });
        btn_LoginQuanLy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_LoginQuanLyKeyPressed(evt);
            }
        });

        btn_ThoatQuanLy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/exitot.png"))); // NOI18N
        btn_ThoatQuanLy.setText("Thoát");
        btn_ThoatQuanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThoatQuanLyActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("Đổi mật khẩu?");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_loiPassQuanLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_PassQuanLy, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_TaiKhoanQuanLy))
                            .addComponent(lbl_notTrung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_loiTKQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btn_LoginQuanLy)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_ThoatQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_notTrung, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TaiKhoanQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(lbl_loiTKQL, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_PassQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiPassQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_LoginQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThoatQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThoatQuanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThoatQuanLyActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn thoát chứ?", "Thoát đăng nhập quản lý", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.NO_OPTION) {
            return;
        } else {
            Man_Hinh_Chao manhinh = new Man_Hinh_Chao();
            manhinh.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_ThoatQuanLyActionPerformed

    private void btn_LoginQuanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LoginQuanLyActionPerformed
        
        if (checknull() == true) {
            
            txt_PassQuanLy.setBorder(border1);
            txt_TaiKhoanQuanLy.setBorder(border1); 
            lbl_loiPassQuanLy.setText("");
            lbl_notTrung.setText("");
            lbl_loiTKQL.setText("");
            NguoiDung nd = new NguoiDung();
            nd.setTenDangNhap(txt_TaiKhoanQuanLy.getText());
            nd.setMatKhau(txt_PassQuanLy.getText());
            String tk = txt_TaiKhoanQuanLy.getText();
            int vaiTro =0;
            listND = dnDAO.dangNhapQuanLy(nd, conn);
            if (listND.size() > 0) {
               ManHinh manHinh = new ManHinh(txt_TaiKhoanQuanLy.getText(), vaiTro);
                manHinh.setVisible(true);
                this.dispose();
                
                System.out.println("tài khoản: " + nd.getTenDangNhap() + "\n" + "mật khẩu: " + nd.getMatKhau());
                System.out.println(listND);
            } else {
                
                lbl_notTrung.setText("Tên đăng nhập hoặc mật khẩu không đúng");
                txt_TaiKhoanQuanLy.setBorder(border);
                txt_PassQuanLy.setBorder(border);
                lbl_notTrung.setForeground(Color.red);
                txt_TaiKhoanQuanLy.setText("");
                txt_PassQuanLy.setText("");
                txt_TaiKhoanQuanLy.requestFocus();
            }
             System.out.println("Tk:" + nd.getTenDangNhap() + "\n" + "MK: " + nd.getMatKhau() + "\n" + "vai trò "+nd.getVaiTro());
        }
        

    }//GEN-LAST:event_btn_LoginQuanLyActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        QuenMkQuanLy quenmk = new QuenMkQuanLy();
        quenmk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void txt_PassQuanLyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PassQuanLyKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            java.awt.event.ActionEvent evt1 = null;
            btn_LoginQuanLyActionPerformed(evt1);
        }
    }//GEN-LAST:event_txt_PassQuanLyKeyPressed

    private void btn_LoginQuanLyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_LoginQuanLyKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            java.awt.event.ActionEvent evt1 = null;
            btn_LoginQuanLyActionPerformed(evt1);
        }
    }//GEN-LAST:event_btn_LoginQuanLyKeyPressed

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
            java.util.logging.Logger.getLogger(QuanLyLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_LoginQuanLy;
    private javax.swing.JButton btn_ThoatQuanLy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_loiPassQuanLy;
    private javax.swing.JLabel lbl_loiTKQL;
    private javax.swing.JLabel lbl_notTrung;
    private javax.swing.JPasswordField txt_PassQuanLy;
    private javax.swing.JTextField txt_TaiKhoanQuanLy;
    // End of variables declaration//GEN-END:variables
}
