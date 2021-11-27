/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author congc
 */
public class ManHinh extends javax.swing.JFrame {

    private String str;
    private int vaiTro;

    public ManHinh(String str, int vaiTro) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.str = str;
        this.vaiTro = vaiTro;
        lbl_tenDangNhap.setText(str);
        this.setTitle("Không gian làm việc");
        if (vaiTro == 0) {

        }
        if (vaiTro == 1) {
            btn_dotThi.setEnabled(false);
            btn_QLNV.setEnabled(false);
            btn_DD.setEnabled(false);
            Mnu_dotThi.setEnabled(false);
            Mnu_diemDanh.setEnabled(false);
            Mnu_dotThi.setEnabled(false);
            // btn_DangKiHocVien.setEnabled(false);

        }
        if (vaiTro == 2) {
            btn_DangKiHocVien.setEnabled(false);
            btn_bienLai.setEnabled(false);
            btn_QLNV.setEnabled(false);
            Mnu_dangKi.setEnabled(false);
            Mnu_bienLai.setEnabled(false);
            Mnu_nhanVien.setEnabled(false);
            Mnu_thongKe.setEnabled(false);

        }
    }

    public ManHinh() {
        initComponents();
        System.out.println(desktopMain.size());
        this.setLocationRelativeTo(null);
    }

    void close() {
        for (JInternalFrame jif : desktopMain.getAllFrames()) {
            jif.dispose();

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
        lbl_tenDangNhap = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_Lop = new javax.swing.JButton();
        btn_dotThi = new javax.swing.JButton();
        btn_hocVien = new javax.swing.JButton();
        btn_DangKiHocVien = new javax.swing.JButton();
        btn_bienLai = new javax.swing.JButton();
        btn_DD = new javax.swing.JButton();
        btn_QLNV = new javax.swing.JButton();
        desktopMain = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Mnu_dangXuat = new javax.swing.JMenuItem();
        Mnu_Thoat = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Mnu_lop = new javax.swing.JMenuItem();
        Mnu_dotThi = new javax.swing.JMenuItem();
        Mnu_dangKi = new javax.swing.JMenuItem();
        Mnu_bienLai = new javax.swing.JMenuItem();
        Mnu_hocVien = new javax.swing.JMenuItem();
        Mnu_diemDanh = new javax.swing.JMenuItem();
        Mnu_nhanVien = new javax.swing.JMenuItem();
        Mnu_thongKe = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl_tenDangNhap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Xin chào:");

        btn_Lop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/lop.png"))); // NOI18N
        btn_Lop.setText("Quản lý lớp");
        btn_Lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LopActionPerformed(evt);
            }
        });

        btn_dotThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/lịch thi.png"))); // NOI18N
        btn_dotThi.setText("Quản lý đợt thi");
        btn_dotThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dotThiActionPerformed(evt);
            }
        });

        btn_hocVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/hocVien.png"))); // NOI18N
        btn_hocVien.setText("Quản lý học viên");

        btn_DangKiHocVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/diemthi.png"))); // NOI18N
        btn_DangKiHocVien.setText("Quản lý đăng kí học viên");
        btn_DangKiHocVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangKiHocVienActionPerformed(evt);
            }
        });

        btn_bienLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/biên lai.png"))); // NOI18N
        btn_bienLai.setText("Quản lý biên lai");
        btn_bienLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bienLaiActionPerformed(evt);
            }
        });

        btn_DD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/lịch thi.png"))); // NOI18N
        btn_DD.setText("Quản lý điểm danh");

        btn_QLNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/nhânvien.png"))); // NOI18N
        btn_QLNV.setText("Quản lý Nhân viên");
        btn_QLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_tenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_QLNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_DD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_bienLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_DangKiHocVien, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(btn_hocVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_dotThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Lop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_tenDangNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btn_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_dotThi, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_hocVien, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_DangKiHocVien, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btn_bienLai, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_DD, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_QLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        desktopMain.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopMainLayout = new javax.swing.GroupLayout(desktopMain);
        desktopMain.setLayout(desktopMainLayout);
        desktopMainLayout.setHorizontalGroup(
            desktopMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1198, Short.MAX_VALUE)
        );
        desktopMainLayout.setVerticalGroup(
            desktopMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jMenu1.setText("File");

        Mnu_dangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Exit.png"))); // NOI18N
        Mnu_dangXuat.setText("Đăng Xuất");
        Mnu_dangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mnu_dangXuatActionPerformed(evt);
            }
        });
        jMenu1.add(Mnu_dangXuat);

        Mnu_Thoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/exitot.png"))); // NOI18N
        Mnu_Thoat.setText("Thoát");
        Mnu_Thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mnu_ThoatActionPerformed(evt);
            }
        });
        jMenu1.add(Mnu_Thoat);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản lý");

        Mnu_lop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/giaoduccongdan.png"))); // NOI18N
        Mnu_lop.setText("Quản lý  lớp");
        jMenu2.add(Mnu_lop);

        Mnu_dotThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/tk_dethi.png"))); // NOI18N
        Mnu_dotThi.setText("Quản lý đợt thi");
        jMenu2.add(Mnu_dotThi);

        Mnu_dangKi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/dangKy.png"))); // NOI18N
        Mnu_dangKi.setText("Quản lý đăng kí ");
        jMenu2.add(Mnu_dangKi);

        Mnu_bienLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/List.png"))); // NOI18N
        Mnu_bienLai.setText("Quản lý biên lai");
        jMenu2.add(Mnu_bienLai);

        Mnu_hocVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Users.png"))); // NOI18N
        Mnu_hocVien.setText("Quản lý học viên");
        jMenu2.add(Mnu_hocVien);

        Mnu_diemDanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Notes.png"))); // NOI18N
        Mnu_diemDanh.setText("Quản lý điểm danh");
        jMenu2.add(Mnu_diemDanh);

        Mnu_nhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/User.png"))); // NOI18N
        Mnu_nhanVien.setText("Quản lý nhân viên");
        jMenu2.add(Mnu_nhanVien);

        jMenuBar1.add(jMenu2);

        Mnu_thongKe.setText("Thống kê");

        jMenuItem1.setText("jMenuItem1");
        Mnu_thongKe.add(jMenuItem1);

        jMenuBar1.add(Mnu_thongKe);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopMain))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktopMain, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LopActionPerformed
        close();
        QLLop ql = new QLLop();
        //ql.setPreferredSize(new Dimension(150, 100));
        desktopMain.add(ql);
        ql.setLocation((desktopMain.getWidth() - ql.getWidth()) / 2,
                (desktopMain.getHeight() - ql.getHeight()) / 2);
        ql.setVisible(true);
    }//GEN-LAST:event_btn_LopActionPerformed

    private void btn_QLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QLNVActionPerformed
        close();
        QLNV ql = new QLNV();
        desktopMain.add(ql);
        ql.setLocation((desktopMain.getWidth() - ql.getWidth()) / 2,
                (desktopMain.getHeight() - ql.getHeight()) / 2);
        ql.setVisible(true);
    }//GEN-LAST:event_btn_QLNVActionPerformed

    private void btn_dotThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dotThiActionPerformed
        close();
        QuanLiDotThi ql = new QuanLiDotThi();
        desktopMain.add(ql);
        ql.setLocation((desktopMain.getWidth() - ql.getWidth()) / 2,
                (desktopMain.getHeight() - ql.getHeight()) / 2);
        ql.setVisible(true);
    }//GEN-LAST:event_btn_dotThiActionPerformed

    private void Mnu_ThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mnu_ThoatActionPerformed
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát?");
        if (a != JOptionPane.YES_OPTION) {
            return;
        }
        this.dispose();
    }//GEN-LAST:event_Mnu_ThoatActionPerformed

    private void Mnu_dangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mnu_dangXuatActionPerformed
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất?");
        if (a != JOptionPane.YES_OPTION) {
            return;
        }
        Man_Hinh_Chao manHinh = new Man_Hinh_Chao();
        manHinh.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Mnu_dangXuatActionPerformed

    private void btn_bienLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bienLaiActionPerformed
        close();
        QuanLy_BienLai ql = new QuanLy_BienLai();
        desktopMain.add(ql);
        ql.setLocation((desktopMain.getWidth() - ql.getWidth()) / 2,
                (desktopMain.getHeight() - ql.getHeight()) / 2);
        ql.setVisible(true);
    }//GEN-LAST:event_btn_bienLaiActionPerformed

    private void btn_DangKiHocVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangKiHocVienActionPerformed
        // TODO add your handling code here:
        close();
        QuanLyDangKi ql = new QuanLyDangKi();
        desktopMain.add(ql);
        ql.setLocation((desktopMain.getWidth() - ql.getWidth()) / 2,
                (desktopMain.getHeight() - ql.getHeight()) / 2);
        ql.setVisible(true);
    }//GEN-LAST:event_btn_DangKiHocVienActionPerformed

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
            java.util.logging.Logger.getLogger(ManHinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManHinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManHinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManHinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManHinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Mnu_Thoat;
    private javax.swing.JMenuItem Mnu_bienLai;
    private javax.swing.JMenuItem Mnu_dangKi;
    private javax.swing.JMenuItem Mnu_dangXuat;
    private javax.swing.JMenuItem Mnu_diemDanh;
    private javax.swing.JMenuItem Mnu_dotThi;
    private javax.swing.JMenuItem Mnu_hocVien;
    private javax.swing.JMenuItem Mnu_lop;
    private javax.swing.JMenuItem Mnu_nhanVien;
    private javax.swing.JMenu Mnu_thongKe;
    private javax.swing.JButton btn_DD;
    private javax.swing.JButton btn_DangKiHocVien;
    private javax.swing.JButton btn_Lop;
    private javax.swing.JButton btn_QLNV;
    private javax.swing.JButton btn_bienLai;
    private javax.swing.JButton btn_dotThi;
    private javax.swing.JButton btn_hocVien;
    private javax.swing.JDesktopPane desktopMain;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_tenDangNhap;
    // End of variables declaration//GEN-END:variables
}
