/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.HocVienDao;
import DAO.LopDAO;
import DAO.QuanlyHocVienDao;
import Entity.HocVien;
import Entity.Lop;
import Entity.NguoiDung;
import TienIchHoTro.Dialog;
import java.awt.Color;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vinhn
 */
public class QuanLyHocVien extends javax.swing.JFrame {

    private DefaultTableModel model = new DefaultTableModel();
    private List<HocVien> lstHocVien = new ArrayList<>();
    HocVienDao hvDAO = new HocVienDao();
    QuanlyHocVienDao qlDao = new QuanlyHocVienDao();
    Connection conn = null;
    private DefaultComboBoxModel<String> cbox = new DefaultComboBoxModel();
    public QuanLyHocVien() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Quản lý học viên");
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        fillTable();
       
    }
    
     void fillTable() {
        model = (DefaultTableModel) tblHocVien.getModel();
        model.setRowCount(0);
        lstHocVien = hvDAO.listHV(conn);
        for (HocVien l : lstHocVien) {
            Object[] obj = new Object[]{
                l.getMaHocVien(), l.getTenHocVien(), l.getMaLop(), l.getTenLop(), l.getGioiTinh(),
                 l.getNgaySinh(), l.getSdt(), l.getEmail(), l.getDiaChi(), l.getHocPhiNo()};
            model.addRow(obj);
        }
    }

    boolean checkNull() {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Border border = BorderFactory.createLineBorder(Color.red);
        if (txtTenHV.getText().equals("") && txtMaLop.getText().equals("") && txtTenLop.getText().equals("")
                && txtDiaChi.getText().equals("") && txtSDT.getText().equals("") && txtEmail.getText().equals("")
                && dateNgaySinh.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập thông tin nào mời nhập lại");
            txtTenHV.requestFocus();
            return false;
        }

        if (txtTenHV.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên học viên");
            txtTenHV.requestFocus();
            return false;
        }

        if (txtMaLop.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã lớp");
            txtMaLop.requestFocus();
            return false;
        }

        if (txtTenLop.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên lớp");
            txtTenLop.requestFocus();
            return false;
        }

        if (txtDiaChi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ");
            txtDiaChi.requestFocus();
            return false;
        }
        if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập sdt");
            txtSDT.requestFocus();
            return false;
        } else {

            Pattern p = Pattern.compile("^(84|0[3|5|7|8|9][0-9]{1}) ([0-9]{3}) ([0-9]{4})$");
            if (!p.matcher(txtSDT.getText()).find()) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng của Việt Nam vui lòng nhập lại VD:091 450 6901");
                txtSDT.requestFocus();
                return false;
            }

        }

        if (txtEmail.equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập email");
            txtEmail.requestFocus();
            return false;
        } else {
            Pattern p = Pattern.compile("^[a-zA-Z0-9.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
            if (!p.matcher(txtEmail.getText()).find()) {
                JOptionPane.showMessageDialog(this, "Email không đúng định dạng vui lòng nhập đúng định dạng VD:abc@gmail.com");
                txtEmail.requestFocus();
                return false;
            }

        }
        
        if (txtHPNo.equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập học phí còn nợ");
            txtHPNo.requestFocus();
            return false;
        }

        return true;
    }

    boolean checkTrung() {
        Border border = BorderFactory.createLineBorder(Color.red);
        for (HocVien nd : lstHocVien) {

            if (txtEmail.getText().equals(nd.getEmail())) {
                JOptionPane.showMessageDialog(this, "Email bị trùng mời nhập lại");
                txtEmail.requestFocus();
                return false;
            }

            if (txtSDT.getText().equals(nd.getSdt())) {
                JOptionPane.showMessageDialog(this, "Số điện thoại bị trùng mời nhập lại");
                txtSDT.requestFocus();
                return false;
            }
        }

        return true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtTenHV = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtMaLop = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenLop = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtHPNo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHocVien = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        Thêm = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btn_TrangChu1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTenHV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHVActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên học viên");

        jLabel2.setText("Mã lớp");

        txtTenLop.setText("jTextField3");

        jLabel3.setText("Tên lớp");

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });

        jLabel4.setText("Giới tính");

        jLabel5.setText("Ngày sinh");

        jLabel6.setText("Số điện thoại");

        txtSDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtSDTMouseExited(evt);
            }
        });

        jLabel7.setText("Địa chỉ");

        jLabel8.setText("Email");

        jLabel9.setText("Học phí còn nợ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addGap(50, 50, 50)
                        .addComponent(rdoNu))
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtTenHV)
                    .addComponent(txtMaLop)
                    .addComponent(txtTenLop)
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(txtSDT)
                    .addComponent(txtDiaChi)
                    .addComponent(txtEmail)
                    .addComponent(txtHPNo))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenHV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(11, 11, 11)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHPNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblHocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã học viên", "Tên học viên", "Mã lớp", "Tên lớp", "Giới tính", "Ngày Sinh", "SDT", "Email", "Địa chỉ", "Học phí nợ"
            }
        ));
        tblHocVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHocVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHocVien);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Tìm kiếm");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        Thêm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Create.png"))); // NOI18N
        Thêm.setText("Thêm");
        Thêm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThêmActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        jButton6.setText("Clear");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Thêm)
                        .addGap(58, 58, 58)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnTimKiem))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButton6)))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem)
                    .addComponent(btnTimKiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Thêm, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btn_TrangChu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Home.png"))); // NOI18N
        btn_TrangChu1.setText("Trang chủ");
        btn_TrangChu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TrangChu1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel10.setText("QUẢN LÝ HỌC VIÊN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_TrangChu1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(187, 187, 187))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btn_TrangChu1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenHVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenHVActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tblHocVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHocVienMouseClicked
        int vitri = tblHocVien.getSelectedRow();
        if (vitri >=0) {
            String row = (String) tblHocVien.getValueAt(vitri, 1);
            HocVien hv = hvDAO.clickTable(row, conn);
            if (hv != null) {
                txtTenHV.setText(hv.getTenHocVien());
                txtMaLop.setText(String.valueOf(hv.getMaLop()));
                txtTenLop.setText(hv.getTenLop());
                if (hv.getGioiTinh() == 1) {
                    rdoNam.setSelected(true);
                }
                if (hv.getGioiTinh() == 0) {
                    rdoNu.setSelected(true);
                }

                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tblHocVien.getValueAt(vitri, 5));
                    dateNgaySinh.setDate(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }            

                txtSDT.setText(hv.getSdt().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3"));
                txtDiaChi.setText(hv.getDiaChi());
                txtEmail.setText(hv.getEmail());
                txtHPNo.setText(hv.getHocPhiNo()+"");
            }
        }
    }//GEN-LAST:event_tblHocVienMouseClicked

    private void ThêmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThêmActionPerformed
        if (checkNull()) {
            if (checkTrung()) {
                HocVien nd = new HocVien();
                nd.setTenHocVien(txtTenHV.getText());
                nd.setMaLop(Integer.parseInt(txtMaLop.getText()));
                nd.setTenLop(txtTenLop.getText());
                int gioitinh;
                if (rdoNam.isSelected()) {
                    gioitinh = 1;
                    nd.setGioiTinh(gioitinh);
                }
                if (rdoNu.isSelected()) {
                    gioitinh = 0;
                    nd.setGioiTinh(gioitinh);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String date = sdf.format(dateNgaySinh.getDate());
                nd.setNgaySinh(date);
                nd.setDiaChi(txtDiaChi.getText());
                String s = txtSDT.getText().replaceAll("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
                txtSDT.setText(s);
                nd.setSdt(s);
                nd.setEmail(txtEmail.getText());
                nd.setHocPhiNo(Float.parseFloat(txtHPNo.getText()));
                
                
                boolean them = qlDao.insert(nd, conn);
                fillTable();
                if (them == true) {
                    Dialog.alert(null, "ok");
                } else {
                    Dialog.alert(null, "k0");
                }
            }
        }
    }//GEN-LAST:event_ThêmActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (checkNull()) {
                HocVien nd = new HocVien();
                nd.setTenHocVien(txtTenHV.getText());
                int gioiTinh = 1;
                if (rdoNam.isSelected()) {
                    gioiTinh = 1;
                }
                if (rdoNu.isSelected()) {
                    gioiTinh = 0;
                }
                nd.setGioiTinh(gioiTinh);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String date = sdf.format(dateNgaySinh.getDate());
                nd.setNgaySinh(date);
                nd.setDiaChi(txtDiaChi.getText());
                nd.setSdt(txtSDT.getText());
                nd.setEmail(txtEmail.getText());
                nd.setHocPhiNo(Float.parseFloat(txtHPNo.getText()));
                nd.setMaLop(Integer.parseInt(txtMaLop.getText()));
                int vaiTro = 0;
                
                int vitri = tblHocVien.getSelectedRow();
                int row = (int) tblHocVien.getValueAt(vitri, 0);
                nd.setMaHocVien(row);
                qlDao.update(nd, conn);
                String s = txtSDT.getText().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
                txtSDT.setText(s);
                fillTable();
            
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtSDTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSDTMouseExited
        String s = txtSDT.getText().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
        txtSDT.setText(s);
    }//GEN-LAST:event_txtSDTMouseExited

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        fillTable();
        lstHocVien = qlDao.search(txtTimKiem.getText(), conn);
        model.setRowCount(0);
        model = (DefaultTableModel) tblHocVien.getModel();
        for (HocVien nd : lstHocVien) {
            Vector<Object> vec = new Vector<>();
            vec.add(nd.getMaHocVien());
            vec.add(nd.getTenHocVien());
            vec.add(nd.getMaLop());
            vec.add(nd.getTenLop());
            vec.add(nd.getGioiTinh() == 1 ? "Nam" : "Nữ");
            vec.add(nd.getNgaySinh());
            vec.add(nd.getDiaChi());
            vec.add(nd.getSdt().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3"));
            vec.add(nd.getEmail());
            vec.add(nd.getHocPhiNo());
            model.addRow(vec);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btn_TrangChu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TrangChu1ActionPerformed
        ManHinh  manHinh = new ManHinh();
        manHinh.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_TrangChu1ActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyHocVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyHocVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Thêm;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btn_TrangChu1;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblHocVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHPNo;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenHV;
    private javax.swing.JTextField txtTenLop;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
