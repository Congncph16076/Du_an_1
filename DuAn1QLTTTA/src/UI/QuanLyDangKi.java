/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.QuanLyDangKiDao;
import DAO.QuanLyDangKyDao2;
import Entity.DangKi;
import TienIchHoTro.Dialog;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vinhn
 */
public class QuanLyDangKi extends javax.swing.JInternalFrame {

    private DefaultTableModel model = new DefaultTableModel();
    private List<DangKi> lstDK = new ArrayList<>();
    QuanLyDangKiDao dkDAO = new QuanLyDangKiDao();
    QuanLyDangKyDao2 dkDAO2 = new QuanLyDangKyDao2();
    Connection conn = null;
    private DefaultComboBoxModel<String> cbox = new DefaultComboBoxModel();
    public QuanLyDangKi() {
        initComponents();
        
        this.setTitle("Quản lý đăng kí học viên");
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        fillTable();
    }
    
    public void clear(){
        txtMaHV.setText("");
        txtTenHV.setText("");
        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000");
            dateNgaySinh.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(QuanLyDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000");
            dateNhapHoc.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(QuanLyDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000");
            dateDangKi.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(QuanLyDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtTenCapLop.setText("");
        txtTenLoaiLop.setText("");
        txtHPhi.setText("");
        txtCaHoc.setText("");
        fillTable();
    }
    
    void fillTable() {
        model = (DefaultTableModel) tblDangKi.getModel();
        model.setRowCount(0);
        lstDK = dkDAO.selectAll(conn);
        for (DangKi nd : lstDK) {
            Object[] obj = new Object[]{
                nd.getMaDangKi(), nd.getTenHocVien(),nd.getNgaySinh(), nd.getGioiTinh()== 1 ? "Nam" : "Nữ",
                nd.getSdt().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3"), nd.getEmail(), nd.getDiaChi(),
                nd.getTenCapLop(), nd.getTenLoaiLop(),nd.getHocPhi(), nd.getCaHoc(), nd.getNgayNhapHoc(),
                nd.getNgayDangKi(), nd.getMaHocVien()
            };
            model.addRow(obj);
        }
    }
    boolean checkNull() {
        if (txtTenHV.getText().equals("") && txtMaHV.getText().equals("") && txtTenCapLop.getText().equals("")
                && txtDiaChi.getText().equals("") && txtSDT.getText().equals("") && txtEmail.getText().equals("")
                && dateNgaySinh.getDate().equals("")&& txtTenLoaiLop.getText().equals("")&& txtCaHoc.getText().equals("")
                && txtHPhi.getText().equals("")&& dateNhapHoc.getDate().equals("")&& dateNgaySinh.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập thông tin nào mời nhập lại");
            txtMaHV.requestFocus();
            return false;
        }
        

        if (txtTenHV.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên học viên");
            txtTenHV.requestFocus();
            return false;
        }

        if (dateNgaySinh.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày sinh");
            dateNgaySinh.requestFocus();
            return false;
        }

        if (txtTenCapLop.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên cấp lớp");
            txtTenCapLop.requestFocus();
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

            Pattern p = Pattern.compile("^(\\+\\d{2}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
            if (!p.matcher(txtSDT.getText()).find()) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng của Việt Nam vui lòng nhập lại VD:091 450 6901");
                txtSDT.requestFocus();
                return false;
            }

        }
        if (txtCaHoc.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập ca học");
            txtCaHoc.requestFocus();
            return false;
        }
        if (txtTenLoaiLop.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên loại lớp");
            txtTenLoaiLop.requestFocus();
            return false;
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
        if (dateNhapHoc.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày nhập học");
            dateNhapHoc.requestFocus();
            return false;
        }
        if (dateDangKi.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày đăng kí");
            dateDangKi.requestFocus();
            return false;
        }
        
        if (txtHPhi.equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập học phí");
            txtHPhi.requestFocus();
            return false;
        }
        
        if (rdoChuaHoc.isSelected()&&Integer.parseInt(txtMaHV.getText())!=0||rdoDaHoc.isSelected()&&Integer.parseInt(txtMaHV.getText())==0) {
            JOptionPane.showMessageDialog(this, "Mã học viên chưa đúng");
            txtMaHV.requestFocus();
            return false;
        }

        return true;
    }

    boolean checkTrung() {
//        Border border = BorderFactory.createLineBorder(Color.red);
        for (DangKi nd : lstDK) {

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
    
    public DangKi clickTable(String ID, Connection conn) {
        CallableStatement call;
        try {
            call = conn.prepareCall("{call tim_kiem_ban_dang_ki_theo_mdk(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                DangKi dk = new DangKi();
                dk.setMaDangKi(rs.getInt("MAdangki"));
                dk.setTenHocVien(rs.getString("TENhocvien"));
                dk.setGioiTinh(rs.getInt("GIOITINH"));
                dk.setNgaySinh(rs.getString("NGAYSINH"));
                dk.setDiaChi(rs.getString("DIACHI"));
                dk.setSdt(rs.getString("SDT"));
                dk.setEmail(rs.getString("EMAIL"));
                dk.setTenLoaiLop(rs.getString("TENloailop"));
                dk.setTenCapLop(rs.getString("tencaplop"));
                dk.setHocPhi(rs.getFloat("hocphi"));
                dk.setCaHoc(rs.getString("cahoc"));
                dk.setNgayNhapHoc(rs.getString("ngaynhaphoc"));
                dk.setMaHocVien(rs.getInt("mahocvien"));
                
                return dk;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDangKi = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtTenHV = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
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
        txtTenCapLop = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtHPhi = new javax.swing.JTextField();
        txtTenLoaiLop = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        dateDangKi = new com.toedter.calendar.JDateChooser();
        dateNhapHoc = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCaHoc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtMaHV = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        rdoDaHoc = new javax.swing.JRadioButton();
        rdoChuaHoc = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(1081, 650));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Tìm kiếm");

        txtTimKiem.setText("Mời nhập số điện thoại");
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseExited(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Create.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnClear))
                .addGap(18, 18, 18))
        );

        tblDangKi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đăng kí", "Tên học viên", "Ngày sinh", "Giới tính", "SDT", "Email", "Địa chỉ", "Tên cấp lớp", "Tên loại lớp", "Học phí ", "Ca học", "Ngày nhập học", "Ngày đăng kí", "Mã học viên"
            }
        ));
        tblDangKi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDangKiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDangKi);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTenHV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHVActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên học viên");

        jLabel2.setText("Email");

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

        jLabel8.setText("Tên cấp lớp");

        jLabel9.setText("Học phí ");

        txtTenLoaiLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenLoaiLopActionPerformed(evt);
            }
        });

        jLabel11.setText("Tên loại lớp");

        jLabel3.setText("Ngày nhập học");

        jLabel12.setText("Ngày đăng kí");

        txtCaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCaHocActionPerformed(evt);
            }
        });

        jLabel13.setText("Ca học");

        jLabel14.setText("Mã học viên");

        buttonGroup2.add(rdoDaHoc);
        rdoDaHoc.setText("Đã học");

        buttonGroup2.add(rdoChuaHoc);
        rdoChuaHoc.setSelected(true);
        rdoChuaHoc.setText("Đăng kí mới");
        rdoChuaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChuaHocActionPerformed(evt);
            }
        });

        jLabel15.setText("Tình trạng hồ sơ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtTenHV, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addGap(59, 59, 59)
                        .addComponent(rdoNu))
                    .addComponent(jLabel15)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoChuaHoc)
                        .addGap(43, 43, 43)
                        .addComponent(rdoDaHoc)))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTenLoaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel9)
                                    .addComponent(txtHPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(txtCaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateDangKi, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel3)
                            .addComponent(dateNhapHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaHV, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel3))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoDaHoc)
                        .addComponent(rdoChuaHoc))
                    .addComponent(dateNhapHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTenLoaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTenHV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateDangKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel7))
                    .addComponent(jLabel9)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(txtMaHV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel10.setText("QUẢN LÝ ĐĂNG KÍ HỌC VIÊN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(271, 271, 271))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        try {
            String s = txtTimKiem.getText().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
            txtTimKiem.setText(s);
            fillTable();
            lstDK = dkDAO.search(txtTimKiem.getText(), conn);
            model.setRowCount(0);
            model = (DefaultTableModel) tblDangKi.getModel();
            for (DangKi nd : lstDK) {
                Vector<Object> vec = new Vector<>();
                vec.add(nd.getMaDangKi());
                vec.add(nd.getTenHocVien());
                vec.add(nd.getMaHocVien());
                vec.add(nd.getTenCapLop());
                vec.add(nd.getTenLoaiLop());
                vec.add(nd.getGioiTinh() == 1 ? "Nam" : "Nữ");
                vec.add(nd.getNgaySinh());
                vec.add(nd.getDiaChi());
                vec.add(nd.getSdt().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3"));
                vec.add(nd.getEmail());
                vec.add(nd.getCaHoc());
                vec.add(nd.getNgayNhapHoc());
                vec.add(nd.getNgayDangKi());
                vec.add(nd.getHocPhi());
                model.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            if (checkNull()) {
            
                DangKi nd = new DangKi();
                nd.setTenHocVien(txtTenHV.getText());
                if (Integer.parseInt(txtMaHV.getText())==0) {
                    rdoChuaHoc.setSelected(true);
                    
                } else {
                    rdoDaHoc.setSelected(true);
                    nd.setMaHocVien(Integer.parseInt(txtMaHV.getText()));
                }
                nd.setTenCapLop(txtTenCapLop.getText());
                nd.setTenLoaiLop(txtTenLoaiLop.getText());
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
                String date2 = sdf.format(dateNhapHoc.getDate());
                nd.setNgayNhapHoc(date2);
                String date3 = sdf.format(dateDangKi.getDate());
                nd.setNgayDangKi(date3);
                nd.setDiaChi(txtDiaChi.getText());
                String s = txtSDT.getText().replaceAll("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
                txtSDT.setText(s);
                nd.setSdt(s);
                nd.setEmail(txtEmail.getText());
                nd.setCaHoc(txtCaHoc.getText());
                nd.setHocPhi(Float.parseFloat(txtHPhi.getText()));
                boolean them;
                if (Integer.parseInt(txtMaHV.getText())==0) {
                    
                    them = dkDAO2.insert(nd, conn);
                }else{
                    
                    them = dkDAO.insert(nd, conn);
                }
                fillTable();
                if (them == true) {
                    Dialog.alert(null, "ok");
                } else {
                    Dialog.alert(null, "k0");
                }
            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            if (checkNull()) {
                DangKi nd = new DangKi();
                nd.setTenHocVien(txtTenHV.getText());
                
                nd.setTenCapLop(txtTenCapLop.getText());
                nd.setTenLoaiLop(txtTenLoaiLop.getText());
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
                String date2 = sdf.format(dateNhapHoc.getDate());
                nd.setNgayNhapHoc(date2);
                String date3 = sdf.format(dateDangKi.getDate());
                nd.setNgayDangKi(date3);
                nd.setDiaChi(txtDiaChi.getText());
//                String s = txtSDT.getText().replaceAll("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
//                txtSDT.setText(s);
//                nd.setSdt(s);
                nd.setSdt(txtSDT.getText());
                nd.setEmail(txtEmail.getText());
                nd.setHocPhi(Float.parseFloat(txtHPhi.getText()));
                nd.setCaHoc(txtCaHoc.getText());

                if (Integer.parseInt(txtMaHV.getText())==0) {
                    rdoChuaHoc.setSelected(true);
                    nd.setMaHocVien(0);
                } else {
                    rdoDaHoc.setSelected(true);
                    nd.setMaHocVien(Integer.parseInt(txtMaHV.getText()));
                }
                
                int vitri = tblDangKi.getSelectedRow();
                int row = (int) tblDangKi.getValueAt(vitri, 0);
                nd.setMaDangKi(row);
                if (Integer.parseInt(txtMaHV.getText())==0) {
                    dkDAO2.update(nd, conn);
                    
                }else{
                    dkDAO.update(nd, conn);
                    
                }
                

                fillTable();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            int vitri = tblDangKi.getSelectedRow();
            if (vitri>=0) {
                int a = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?");
                if (a==0) {
                    
                    int row;
                    row = (int) tblDangKi.getValueAt(vitri, 0);
                    DangKi hv = dkDAO.delete(String.valueOf(row), conn);
                    clear();
                    
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mời chọn đối tượng");
            }
                
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblDangKiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDangKiMouseClicked
        try {
            int vitri = tblDangKi.getSelectedRow();
            if (vitri >=0) {
                int row;
                row = (int) tblDangKi.getValueAt(vitri, 0);
                DangKi hv = dkDAO.fromTableToText(String.valueOf(row), conn);
                if (hv != null) {
                    if (hv.getMaHocVien()==0) {
                        rdoChuaHoc.setSelected(true);
                        txtMaHV.setText("0");
                        btnXoa.setEnabled(true);
                    } else {
                        rdoDaHoc.setSelected(true);
                        txtMaHV.setText(hv.getMaHocVien()+"");
                        btnXoa.setEnabled(false);
                    }
                    txtMaHV.setText(hv.getMaHocVien()+"");
                    txtTenHV.setText(hv.getTenHocVien());
                    try {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tblDangKi.getValueAt(vitri, 2));
                        dateNgaySinh.setDate(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    if (hv.getGioiTinh() == 1) {
                        rdoNam.setSelected(true);
                    }
                    if (hv.getGioiTinh() == 0) {
                        rdoNu.setSelected(true);
                    }

                    txtSDT.setText(hv.getSdt().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3"));
                    txtEmail.setText(String.valueOf(hv.getEmail()));
                    txtDiaChi.setText(hv.getDiaChi());
                    txtTenCapLop.setText(hv.getTenCapLop());
                    txtTenLoaiLop.setText(hv.getTenLoaiLop());
                    txtHPhi.setText(hv.getHocPhi()+"");
                    txtCaHoc.setText(hv.getCaHoc());

                    try {
                        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse((String) tblDangKi.getValueAt(vitri, 11));
                        dateNhapHoc.setDate(date2);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse((String) tblDangKi.getValueAt(vitri, 12));
                        dateDangKi.setDate(date3);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }    
                
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblDangKiMouseClicked

    private void txtTenHVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenHVActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void txtSDTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSDTMouseExited
        String s = txtSDT.getText().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
        txtSDT.setText(s);
    }//GEN-LAST:event_txtSDTMouseExited

    private void txtTenLoaiLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenLoaiLopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenLoaiLopActionPerformed

    private void txtCaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCaHocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaHocActionPerformed

    private void txtTimKiemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseExited
        
    }//GEN-LAST:event_txtTimKiemMouseExited

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void rdoChuaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChuaHocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoChuaHocActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        try {
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
                    
        
    }//GEN-LAST:event_btnClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dateDangKi;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private com.toedter.calendar.JDateChooser dateNhapHoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JRadioButton rdoChuaHoc;
    private javax.swing.JRadioButton rdoDaHoc;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblDangKi;
    private javax.swing.JTextField txtCaHoc;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHPhi;
    private javax.swing.JTextField txtMaHV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenCapLop;
    private javax.swing.JTextField txtTenHV;
    private javax.swing.JTextField txtTenLoaiLop;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
