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
public class QuenMKKT extends javax.swing.JFrame {

    Connection conn = null;
    DangNhapDAO dnDAO = new DangNhapDAO();
    List<NguoiDung> listnguoiDung = new ArrayList<>();
    Border border = BorderFactory.createLineBorder(Color.red);
    Border border1 = BorderFactory.createLineBorder(Color.black);

    public QuenMKKT() {
        initComponents();
        this.setTitle("Quên mật khẩu");
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        this.setLocationRelativeTo(null);
    }

    boolean checknull() {

        if (txt_quenMKKT.getText().equals("") && txt_MKCuKT.getText().equals("") && txt_MKMoiKT.getText().equals("")) {
            lbl_loiTK.setText("Tên đăng nhập bị trống vui lòng nhập lại!");
            lbl_loiTK.setForeground(Color.red);
            txt_quenMKKT.setBorder(border);
            lbl_loiMKCuKT.setText("Mật khẩu cũ bị trống vui lòng nhập lại!");
            lbl_loiMKCuKT.setForeground(Color.red);
            txt_MKCuKT.setBorder(border);
            lbl_loiMKMoiKT.setText("mật khẩu mới bị trống vui lòng nhập lại!");
            lbl_loiMKMoiKT.setForeground(Color.red);
            txt_MKMoiKT.setBorder(border);
            txt_quenMKKT.requestFocus();
            return false;
        }

        if (txt_quenMKKT.getText().equals("")) {
            lbl_loiTK.setText("Tên đăng nhập bị trống vui lòng nhập lại!");
            lbl_loiTK.setForeground(Color.red);
            txt_quenMKKT.setBorder(border);
            txt_quenMKKT.requestFocus();
            return false;
        }
        if (txt_MKCuKT.getText().equals("")) {
            lbl_loiMKCuKT.setText("Mật khẩu cũ bị trống vui lòng nhập lại!");
            lbl_loiMKCuKT.setForeground(Color.red);
            txt_MKCuKT.setBorder(border);
            txt_MKCuKT.requestFocus();
            return false;
        }
        if (txt_MKMoiKT.getText().equals("")) {
            lbl_loiMKMoiKT.setText("mật khẩu mới bị trống vui lòng nhập lại!");
            lbl_loiMKMoiKT.setForeground(Color.red);
            txt_MKMoiKT.setBorder(border);
            txt_MKMoiKT.requestFocus();
            return false;
        }
        return true;
    }

    boolean checkKhop() {
        NguoiDung nd = new NguoiDung();
        listnguoiDung = dnDAO.listKeToan(nd, conn);
        if (!(txt_quenMKKT.getText().equals(nd.getTenDangNhap()) || txt_MKCuKT.getText().equals(nd.getMatKhauCu()))) {
            lbl_loi.setText("Tên đăng nhập hoặc mật khẩu cũ không đúng");
            lbl_loi.setForeground(Color.red);
            txt_quenMKKT.setBorder(border);
            txt_MKCuKT.setBorder(border);
            txt_MKMoiKT.setBorder(border);
            lbl_loiMKCuKT.setText("");
            lbl_loiMKMoiKT.setText("");
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
        jLabel5 = new javax.swing.JLabel();
        txt_quenMKKT = new javax.swing.JTextField();
        lbl_loi = new javax.swing.JLabel();
        lbl_loiTK = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_MKCuKT = new javax.swing.JPasswordField();
        lbl_loiMKCuKT = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_MKMoiKT = new javax.swing.JPasswordField();
        btn_doiMKKT = new javax.swing.JButton();
        btn_thoatKT = new javax.swing.JButton();
        lbl_loiMKMoiKT = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/FORUM-ICON-USER.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Hệ thống quản lý trung tâm tiếng anh Laguage School");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Đổi mật khẩu kế toán");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tên Đăng Nhập");

        lbl_loi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbl_loiTK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Mật Khẩu cũ");

        lbl_loiMKCuKT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Mật Khẩu Mới");

        btn_doiMKKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Accept.png"))); // NOI18N
        btn_doiMKKT.setText("Đổi mật khẩu");
        btn_doiMKKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doiMKKTActionPerformed(evt);
            }
        });

        btn_thoatKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Exit.png"))); // NOI18N
        btn_thoatKT.setText("Thoát");
        btn_thoatKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatKTActionPerformed(evt);
            }
        });

        lbl_loiMKMoiKT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(lbl_loiMKMoiKT, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(90, 90, 90)
                                    .addComponent(jLabel3)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_doiMKKT)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_thoatKT, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_quenMKKT, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbl_loi, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_MKMoiKT, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbl_loiMKCuKT, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_loiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_MKCuKT, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addComponent(jLabel1)))
                    .addContainerGap(43, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(436, Short.MAX_VALUE)
                .addComponent(lbl_loiMKMoiKT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbl_loi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_quenMKKT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbl_loiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_MKCuKT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbl_loiMKCuKT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_MKMoiKT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(32, 32, 32)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_thoatKT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_doiMKKT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void btn_doiMKKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doiMKKTActionPerformed

        System.out.println(txt_quenMKKT.getText()+"\n"+txt_MKCuKT.getText()+"\n"+txt_MKMoiKT.getText());
        try {
            NguoiDung nd = new NguoiDung();
            
            if (checknull()) {

                if (checkKhop()) {
                    txt_quenMKKT.setBorder(border1);
                    txt_MKCuKT.setBorder(border1);
                    txt_MKMoiKT.setBorder(border1);

                    lbl_loiMKCuKT.setText("");
                    lbl_loiMKMoiKT.setText("");
                    lbl_loiTK.setText("");
                    
                    nd.setMatKhauCu(txt_MKCuKT.getText());
                    nd.setTenDangNhap(txt_quenMKKT.getText());
                    nd.setMatKhau(txt_MKMoiKT.getText());
                    
                    dnDAO.updateMKKT(nd, conn);

                    Dialog.alert(null, "Đổi mật khẩu thành công vui lòng đăng nhập lại");
                    KeToanLogin kt = new KeToanLogin();
                    kt.setVisible(true);

                    this.dispose();
                }
                System.out.println("Tk:" + nd.getTenDangNhap() + "\n" + "MK: " + nd.getMatKhau() + "\n" + "MK cũ: " + nd.getMatKhauCu() + nd.getVaiTro());
            }

        } catch (Exception e) {
            Dialog.alert(null, "sai");
        }
    }//GEN-LAST:event_btn_doiMKKTActionPerformed

    private void btn_thoatKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatKTActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn thoát chứ?", "Thoát quên mật khẩu quản lý", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.NO_OPTION) {
            return;
        } else {
            KeToanLogin kt = new KeToanLogin();
            kt.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_thoatKTActionPerformed

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
            java.util.logging.Logger.getLogger(QuenMKKT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMKKT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMKKT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMKKT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMKKT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_doiMKKT;
    private javax.swing.JButton btn_thoatKT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_loi;
    private javax.swing.JLabel lbl_loiMKCuKT;
    private javax.swing.JLabel lbl_loiMKMoiKT;
    private javax.swing.JLabel lbl_loiTK;
    private javax.swing.JPasswordField txt_MKCuKT;
    private javax.swing.JPasswordField txt_MKMoiKT;
    private javax.swing.JTextField txt_quenMKKT;
    // End of variables declaration//GEN-END:variables
}
