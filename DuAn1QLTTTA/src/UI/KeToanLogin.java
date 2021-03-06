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
public class KeToanLogin extends javax.swing.JFrame {

    DangNhapDAO dnDAO = new DangNhapDAO();
    List<NguoiDung> listND = new ArrayList<>();
    Connection conn = null;
    Border border = BorderFactory.createLineBorder(Color.red);
    Border border1 = BorderFactory.createLineBorder(Color.black);

    boolean checkNull() {
        if (txt_LoginKeToan.getText().equals("") && txt_PassKeToan.getText().equals("")) {
            lbl_loiTKKT.setText("Tài khoản bị trống vui lòng nhập lại!");
            txt_LoginKeToan.setBorder(border);
            lbl_loiTKKT.setForeground(Color.red);
            lbl_loiPassKT.setText("Mật khẩu bị trống vui lòng nhập lại!");
            txt_PassKeToan.setBorder(border);
            lbl_loiPassKT.setForeground(Color.red);
            txt_LoginKeToan.requestFocus();
            return false;
        }

        if (txt_LoginKeToan.getText().equals("")) {
            lbl_loiTKKT.setText("Tài khoản bị trống vui lòng nhập lại!");
            txt_LoginKeToan.setBorder(border);
            lbl_loiTKKT.setForeground(Color.red);
            txt_LoginKeToan.requestFocus();
            return false;
        }

        if (txt_PassKeToan.getText().equals("")) {
            lbl_loiPassKT.setText("Mật khẩu bị trống vui lòng nhập lại!");
            txt_PassKeToan.setBorder(border);
            lbl_loiPassKT.setForeground(Color.red);
            txt_PassKeToan.requestFocus();
            return false;
        }
        return true;
    }

    public KeToanLogin() {
        initComponents();
        this.setTitle("Đăng nhập");
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        this.setLocationRelativeTo(null);
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_LoginKeToan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_PassKeToan = new javax.swing.JPasswordField();
        btn_DangNhapKeToan = new javax.swing.JButton();
        btn_Thoat_KeToan = new javax.swing.JButton();
        lbl_loiPassKT = new javax.swing.JLabel();
        lbl_loiTKKT = new javax.swing.JLabel();
        lbl_loi = new javax.swing.JLabel();
        lbl_doiMK = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/FORUM-ICON-USER.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Hệ thống quản lý trung tâm tiếng anh Laguage school");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Kế Toán");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Tài Khoản:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Mật Khẩu:");

        txt_PassKeToan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_PassKeToanKeyPressed(evt);
            }
        });

        btn_DangNhapKeToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/dangnhap.png"))); // NOI18N
        btn_DangNhapKeToan.setText("Đăng Nhập");
        btn_DangNhapKeToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangNhapKeToanActionPerformed(evt);
            }
        });
        btn_DangNhapKeToan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_DangNhapKeToanKeyPressed(evt);
            }
        });

        btn_Thoat_KeToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/exitot.png"))); // NOI18N
        btn_Thoat_KeToan.setText("Thoát");
        btn_Thoat_KeToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Thoat_KeToanActionPerformed(evt);
            }
        });

        lbl_loiPassKT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbl_loiTKKT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbl_loi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbl_doiMK.setBackground(new java.awt.Color(255, 255, 255));
        lbl_doiMK.setForeground(new java.awt.Color(255, 0, 0));
        lbl_doiMK.setText("Đổi mật khẩu?");
        lbl_doiMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_doiMKMouseClicked(evt);
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
                                .addComponent(lbl_loiPassKT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_LoginKeToan, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_PassKeToan))
                                .addComponent(lbl_loiTKKT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_loi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_doiMK)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_DangNhapKeToan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_Thoat_KeToan, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jLabel8)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(96, 96, 96))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_loi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_LoginKeToan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTKKT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_PassKeToan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiPassKT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_DangNhapKeToan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Thoat_KeToan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_doiMK)
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

    private void btn_Thoat_KeToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Thoat_KeToanActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chứ?", "Thoát đăng nhập nhân viên kế toán", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.NO_OPTION) {
            return;
        } else {
            Man_Hinh_Chao manhinh = new Man_Hinh_Chao();
            manhinh.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_Thoat_KeToanActionPerformed

    private void btn_DangNhapKeToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangNhapKeToanActionPerformed

        if (checkNull()) {
            txt_LoginKeToan.setBorder(border1);
            txt_PassKeToan.setBorder(border1);
            lbl_loi.setText("");
            lbl_loiPassKT.setText("");
            lbl_loiTKKT.setText("");
            NguoiDung nd = new NguoiDung();
            nd.setTenDangNhap(txt_LoginKeToan.getText());
            nd.setMatKhau(txt_PassKeToan.getText());
            listND = dnDAO.dangNhapKeToan(nd, conn);
            if (listND.size() >0) {
                int vaiTro =1;
                ManHinh manHinh = new ManHinh(txt_LoginKeToan.getText(), vaiTro);
                manHinh.setVisible(true);
                this.dispose();
                System.out.println("tài khoản: " + nd.getTenDangNhap() + "\n" + "mật khẩu: " + nd.getMatKhau());
                System.out.println(listND);
            }else{
                lbl_loi.setText("Tên đăng nhập hoặc mật khẩu không đúng");
                txt_LoginKeToan.setBorder(border);
                txt_PassKeToan.setBorder(border);
                lbl_loi.setForeground(Color.red);
                txt_LoginKeToan.setText("");
                txt_PassKeToan.setText("");
                txt_LoginKeToan.requestFocus();
            }
            
            System.out.println("Tk:" + nd.getTenDangNhap() + "\n" + "MK: " + nd.getMatKhau() + "\n" + "vai trò " + nd.getVaiTro());
        }

    }//GEN-LAST:event_btn_DangNhapKeToanActionPerformed

    private void lbl_doiMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_doiMKMouseClicked
        QuenMKKT quen = new QuenMKKT();
        quen.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_doiMKMouseClicked

    private void txt_PassKeToanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PassKeToanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            java.awt.event.ActionEvent evt1 = null;
            btn_DangNhapKeToanActionPerformed(evt1);
        }
    }//GEN-LAST:event_txt_PassKeToanKeyPressed

    private void btn_DangNhapKeToanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_DangNhapKeToanKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            java.awt.event.ActionEvent evt1 = null;
            btn_DangNhapKeToanActionPerformed(evt1);
        }
    }//GEN-LAST:event_btn_DangNhapKeToanKeyPressed

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
            java.util.logging.Logger.getLogger(KeToanLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KeToanLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KeToanLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KeToanLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KeToanLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DangNhapKeToan;
    private javax.swing.JButton btn_Thoat_KeToan;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_doiMK;
    private javax.swing.JLabel lbl_loi;
    private javax.swing.JLabel lbl_loiPassKT;
    private javax.swing.JLabel lbl_loiTKKT;
    private javax.swing.JTextField txt_LoginKeToan;
    private javax.swing.JPasswordField txt_PassKeToan;
    // End of variables declaration//GEN-END:variables
}
