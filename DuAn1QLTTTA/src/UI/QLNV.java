/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.QuanLyNVDAO;
import java.sql.Connection;
import Entity.NguoiDung;
import TienIchHoTro.Dialog;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class QLNV extends javax.swing.JInternalFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private List<NguoiDung> listND = new ArrayList<>();
    private Connection conn = null;
    QuanLyNVDAO nvDAO = new QuanLyNVDAO();
    Border border = BorderFactory.createLineBorder(Color.red);
    Border border1 = BorderFactory.createLineBorder(Color.black);

    public QLNV() {
        initComponents();
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        fillTableGV();

        ButtonGroup bg = new ButtonGroup();
        bg.add(chk_giangVien);
        bg.add(chk_keToan);
        bg.add(chk_quanLy);
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(rbn_NuNV);
        bg1.add(rbn_namNV);
        this.setTitle("Quản lý nhân viên");
        //this.setLocationRelativeTo(null);
    }

    private void fillTableGV() {
        dtm = (DefaultTableModel) tbl_tableNV.getModel();
        dtm.setRowCount(0);
        listND = nvDAO.selectAll(conn);
        for (NguoiDung nd : listND) {
            Object[] obj = new Object[]{
                nd.getMaNhanVien(), nd.getTenNhanVien(), nd.isGioiTinh() == true ? "Nam" : "Nữ",
                nd.getNgaySinh(),
                nd.getDiaChi(), nd.getSDT().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3"), nd.getEmail(),
                nd.getTenDangNhap(), nd.getMatKhau(), nd.getVaiTro() == 1 ? "Kế toán" : nd.getVaiTro() == 2 ? "Giảng viên" : "Quản lý"
            };
            dtm.addRow(obj);
        }

    }

    boolean checkNullNV() {

//        if (txt_tenNhanVien.getText().equals("") && txt_dangNhapNV.getText().equals("") && txt_matKhauNV.getText().equals("")
//                && txt_diaChiNV.getText().equals("") && txt_SDTNV.getText().equals("") && txt_EmailNV.getText().equals("")
//                && rbn_namNV.isSelected() == false && rbn_NuNV.isSelected() == false) {
//            lbl_loiTenNV.setText("Thiếu tên giảng viên rồi nhập lại bạn nhé!");
//            lbl_loibirthNV.setText("Thiếu ngày sinh rồi chọn lại bạn nhé!");
//            lbl_loiDiaChiNV.setText("Thiếu địa chỉ giảng viên rồi nhập lại bạn nhé!");
//            lbl_loiSDTNV.setText("thiếu số điện thoại giảng viên rồi nhập lại bạn nhé!");
//            lbl_loiEmailNV.setText("Thiếu email giảng viên rồi nhập lại bạn nhé!");
//            lbl_loiDangNhapNV.setText("Thiếu tên đăng nhập rồi nhập lại bạn nhé!");
//            lbl_loiMatKhauNV.setText("thiếu mật khẩu rồi nhập lại bạn nhé");
//            lbl_loiGioiTinh.setText("Bạn chưa chọn giới tính!");
//            txt_tenNhanVien.setBorder(border);
//            dateChooser_birthNV.setBorder(border);
//            txt_diaChiNV.setBorder(border);
//            txt_SDTNV.setBorder(border);
//            txt_EmailNV.setBorder(border);
//            pan_gioiTinh.setBorder(border);
//            txt_dangNhapNV.setBorder(border);
//            txt_matKhauNV.setBorder(border);
//            lbl_loiTenNV.setForeground(Color.red);
//            lbl_loibirthNV.setForeground(Color.red);
//            lbl_loiDiaChiNV.setForeground(Color.red);
//            lbl_loiSDTNV.setForeground(Color.red);
//            lbl_loiEmailNV.setForeground(Color.red);
//            lbl_loiDangNhapNV.setForeground(Color.red);
//            lbl_loiMatKhauNV.setForeground(Color.red);
//            lbl_loiGioiTinh.setForeground(Color.red);
//            txt_tenNhanVien.requestFocus();
//            return false;
//        }
        if (txt_tenNhanVien.getText().equals("")) {
            lbl_loiTenNV.setText("Thiếu tên giảng viên rồi nhập lại bạn nhé!");
            txt_tenNhanVien.setBorder(border);
            lbl_loiTenNV.setForeground(Color.red);
            txt_tenNhanVien.requestFocus();
            return false;
        } else {
            lbl_loiTenNV.setText("");
            txt_tenNhanVien.setBorder(border1);
        }

        if (rbn_namNV.isSelected() == false && rbn_NuNV.isSelected() == false) {
            lbl_loiGioiTinh.setText("Bạn chưa chọn giới tính!");
            lbl_loiGioiTinh.setForeground(Color.red);
            pan_gioiTinh.setBorder(border);

            return false;
        } else {
            lbl_loiGioiTinh.setText("");
            pan_gioiTinh.setBorder(border1);

        }

        String date = ((JTextField) dateChooser_birthNV.getDateEditor().getUiComponent()).getText();
        if (date.equals("")) {
            lbl_loibirthNV.setText("Thiếu ngày sinh rồi chọn lại bạn nhé!");
            dateChooser_birthNV.setBorder(border);
            lbl_loibirthNV.setForeground(Color.red);
            dateChooser_birthNV.requestFocus();
            return false;
        } else {
            lbl_loibirthNV.setText("");
            dateChooser_birthNV.setBorder(border1);
        }

        if (txt_dangNhapNV.getText().equals("")) {
            lbl_loiDangNhapNV.setText("Thiếu tên đăng nhập rồi nhập lại bạn nhé!");
            txt_dangNhapNV.setBorder(border);
            lbl_loiDangNhapNV.setForeground(Color.red);
            txt_dangNhapNV.requestFocus();
            return false;
        } else {
            lbl_loiDangNhapNV.setText("");
            txt_dangNhapNV.setBorder(border1);

        }

        if (txt_matKhauNV.getText().equals("")) {
            lbl_loiMatKhauNV.setText("thiếu mật khẩu rồi nhập lại bạn nhé");
            txt_matKhauNV.setBorder(border);
            lbl_loiMatKhauNV.setForeground(Color.red);
            txt_matKhauNV.requestFocus();
            return false;
        } else {
            lbl_loiMatKhauNV.setText("");
            txt_matKhauNV.setBorder(border1);

        }

        if (txt_diaChiNV.getText().equals("")) {
            lbl_loiDiaChiNV.setText("Thiếu địa chỉ giảng viên rồi nhập lại bạn nhé!");
            txt_diaChiNV.setBorder(border);
            lbl_loiDiaChiNV.setForeground(Color.red);
            txt_diaChiNV.requestFocus();
            return false;
        } else {
            lbl_loiDiaChiNV.setText("");
            txt_diaChiNV.setBorder(border1);

        }

        if (txt_SDTNV.getText().equals("")) {
            lbl_loiSDTNV.setText("thiếu số điện thoại giảng viên rồi nhập lại bạn nhé!");
            txt_SDTNV.setBorder(border);
            lbl_loiSDTNV.setForeground(Color.red);
            txt_SDTNV.requestFocus();
            return false;
        } else {

            Pattern p = Pattern.compile("^(\\+\\d{2}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
            if (!p.matcher(txt_SDTNV.getText()).find()) {
                lbl_loiSDTNV.setText("Số điện thoại không đúng định dạng của Việt Nam vui lòng nhập lại VD:091 450 6901");
                txt_SDTNV.setBorder(border);
                txt_SDTNV.requestFocus();
                return false;
            } else {
                lbl_loiSDTNV.setText("");
                txt_SDTNV.setBorder(border1);
            }

        }

        if (txt_EmailNV.equals("")) {
            lbl_loiEmailNV.setText("Thiếu email giảng viên rồi nhập lại bạn nhé!");
            txt_EmailNV.setBorder(border);
            lbl_loiEmailNV.setForeground(Color.red);
            txt_EmailNV.requestFocus();
            return false;
        } else {
            Pattern p = Pattern.compile("^[a-zA-Z0-9.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
            if (!p.matcher(txt_EmailNV.getText()).find()) {
                lbl_loiEmailNV.setText("Email không đúng định dạng vui lòng nhập đúng định dạng VD:abc@gmail.com");
                txt_EmailNV.setBorder(border);
                txt_EmailNV.requestFocus();
                return false;
            } else {
                lbl_loiEmailNV.setText("");
                txt_EmailNV.setBorder(border1);
            }

        }

        return true;
    }

    boolean checkTrung() {
        Border border = BorderFactory.createLineBorder(Color.red);
        for (NguoiDung nd : listND) {
            if (txt_dangNhapNV.getText().equals(nd.getTenDangNhap())) {
                lbl_loiDangNhapNV.setText("Tên đăng nhập bị trùng rồi vui lòng chọn tên đăng nhập khác");
                txt_dangNhapNV.setBorder(border);
                lbl_loiDangNhapNV.setForeground(Color.red);
                txt_dangNhapNV.requestFocus();
                return false;
            }

            if (txt_EmailNV.getText().equals(nd.getEmail())) {
                lbl_loiEmailNV.setText("Email bị trùng rồi chọn email khác bạn nhé");
                lbl_loiEmailNV.setForeground(Color.red);
                txt_EmailNV.requestFocus();
                return false;
            }

            if (txt_SDTNV.getText().equals(nd.getSDT())) {
                lbl_loiSDTNV.setText("Số điện thoại bị trùng rồi nhập lại bạn nhé!");
                txt_SDTNV.setBorder(border);
                lbl_loiSDTNV.setForeground(Color.red);
                txt_SDTNV.requestFocus();
                return false;
            }
        }

        return true;
    }

    boolean checknullTKNV() {
        Border border = BorderFactory.createLineBorder(Color.red);
        if (txt_timKiemNV.getText().equals("")) {
            txt_timKiemNV.setBorder(border);
            txt_timKiemNV.setForeground(Color.red);
            txt_timKiemNV.setText("Bạn chưa nhập mã nhân viên cần tìm vui lòng thử lại");
            txt_timKiemNV.requestFocus();
            return false;
        } else {

            txt_timKiemNV.setBorder(border1);
            txt_timKiemNV.setForeground(Color.black);
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        lbl_loiDiaChiNV = new javax.swing.JLabel();
        txt_tenNhanVien = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        dateChooser_birthNV = new com.toedter.calendar.JDateChooser();
        lbl_loibirthNV = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txt_diaChiNV = new javax.swing.JTextField();
        lbl_loiTenNV = new javax.swing.JLabel();
        lbl_loiSDTNV = new javax.swing.JLabel();
        txt_SDTNV = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        lbl_loiEmailNV = new javax.swing.JLabel();
        txt_EmailNV = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txt_dangNhapNV = new javax.swing.JTextField();
        lbl_loiDangNhapNV = new javax.swing.JLabel();
        lbl_loiMatKhauNV = new javax.swing.JLabel();
        txt_matKhauNV = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        pan_gioiTinh = new javax.swing.JPanel();
        rbn_namNV = new javax.swing.JRadioButton();
        rbn_NuNV = new javax.swing.JRadioButton();
        lbl_loiGioiTinh = new javax.swing.JLabel();
        chk_giangVien = new javax.swing.JCheckBox();
        chk_keToan = new javax.swing.JCheckBox();
        chk_quanLy = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_tableNV = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btn_ThemNV = new javax.swing.JButton();
        btn_SuaNV = new javax.swing.JButton();
        btn_ClearKT = new javax.swing.JButton();
        txt_timKiemNV = new javax.swing.JTextField();
        btn_TimKiemNV = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Quản lý nhân viên");
        setPreferredSize(new java.awt.Dimension(1189, 637));

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl_loiDiaChiNV.setForeground(new java.awt.Color(255, 51, 51));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Tên nhân viên");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Giới tính");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("Ngày sinh");

        lbl_loibirthNV.setForeground(new java.awt.Color(255, 51, 51));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Địa chỉ");

        lbl_loiTenNV.setForeground(new java.awt.Color(255, 51, 51));

        lbl_loiSDTNV.setForeground(new java.awt.Color(255, 51, 51));

        txt_SDTNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_SDTNVMouseExited(evt);
            }
        });
        txt_SDTNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SDTNVActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("Số điện thoại");

        lbl_loiEmailNV.setForeground(new java.awt.Color(255, 51, 51));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("Email");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Tên đăng nhập");

        lbl_loiDangNhapNV.setForeground(new java.awt.Color(255, 51, 51));

        lbl_loiMatKhauNV.setForeground(new java.awt.Color(255, 51, 51));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("Mật khẩu");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("Vai trò");

        rbn_namNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbn_namNV.setText("Nam");

        rbn_NuNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbn_NuNV.setText("Nữ");

        javax.swing.GroupLayout pan_gioiTinhLayout = new javax.swing.GroupLayout(pan_gioiTinh);
        pan_gioiTinh.setLayout(pan_gioiTinhLayout);
        pan_gioiTinhLayout.setHorizontalGroup(
            pan_gioiTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_gioiTinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbn_namNV, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbn_NuNV, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );
        pan_gioiTinhLayout.setVerticalGroup(
            pan_gioiTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_gioiTinhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_gioiTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbn_namNV)
                    .addComponent(rbn_NuNV))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lbl_loiGioiTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        chk_giangVien.setText("Giảng viên");

        chk_keToan.setText("Kế toán");

        chk_quanLy.setText("Quản lý");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tenNhanVien)
                    .addComponent(lbl_loiTenNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan_gioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loibirthNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateChooser_birthNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiDangNhapNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_dangNhapNV)
                    .addComponent(txt_matKhauNV)
                    .addComponent(lbl_loiMatKhauNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiDiaChiNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_diaChiNV)
                    .addComponent(lbl_loiSDTNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_SDTNV)
                    .addComponent(lbl_loiEmailNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_EmailNV)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel42)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(chk_giangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chk_keToan, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chk_quanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pan_gioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateChooser_birthNV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loibirthNV, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dangNhapNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiDangNhapNV, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_matKhauNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiMatKhauNV, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_giangVien)
                    .addComponent(chk_keToan)
                    .addComponent(chk_quanLy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_diaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiDiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_SDTNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiSDTNV, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_EmailNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiEmailNV, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jScrollPane3.setViewportView(jPanel10);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Quản lý Nhân Viên");

        tbl_tableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email", "Tên đăng nhập", "Mật khẩu", "Vai trò"
            }
        ));
        tbl_tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tableNVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_tableNV);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btn_ThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Add.png"))); // NOI18N
        btn_ThemNV.setText("Thêm nhân viên");
        btn_ThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNVActionPerformed(evt);
            }
        });

        btn_SuaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_SuaNV.setText("Sửa nhân viên");
        btn_SuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaNVActionPerformed(evt);
            }
        });

        btn_ClearKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_ClearKT.setText("Clear ");
        btn_ClearKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearKTActionPerformed(evt);
            }
        });

        txt_timKiemNV.setText("Nhập mã nhân viên");

        btn_TimKiemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_TimKiemNV.setText("Tìm kiếm");
        btn_TimKiemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txt_timKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_ThemNV)
                        .addGap(10, 10, 10)
                        .addComponent(btn_SuaNV)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ClearKT, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_timKiemNV)
                    .addComponent(btn_TimKiemNV, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ClearKT, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
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

    private void btn_TimKiemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemNVActionPerformed
        if (checknullTKNV()) {
            fillTableGV();
            listND = nvDAO.search(txt_timKiemNV.getText(), conn);
            dtm.setRowCount(0);
            dtm = (DefaultTableModel) tbl_tableNV.getModel();
            for (NguoiDung nd : listND) {
                Vector<Object> vec = new Vector<>();
                vec.add(nd.getMaNhanVien());
                vec.add(nd.getTenNhanVien());
                vec.add(nd.isGioiTinh() == true ? "Nam" : "Nữ");
                vec.add(nd.getNgaySinh());
                vec.add(nd.getDiaChi());
                vec.add(nd.getSDT().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3"));
                vec.add(nd.getEmail());
                vec.add(nd.getTenDangNhap());
                vec.add(nd.getMatKhau());
                vec.add(nd.getVaiTro() == 1 ? "Kế toán" : nd.getVaiTro() == 2 ? "Giảngs viên" : "Quản lý");
                dtm.addRow(vec);
            }
        }
    }//GEN-LAST:event_btn_TimKiemNVActionPerformed

    private void btn_SuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaNVActionPerformed

        if (checkNullNV()) {
            NguoiDung nd = new NguoiDung();
            nd.setTenNhanVien(txt_tenNhanVien.getText());
            boolean gioiTinh = false;
            if (rbn_namNV.isSelected()) {
                gioiTinh = true;
            }
            if (rbn_NuNV.isSelected()) {
                gioiTinh = false;
            }
            nd.setGioiTinh(gioiTinh);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdf.format(dateChooser_birthNV.getDate());
            nd.setNgaySinh(date);
            nd.setDiaChi(txt_diaChiNV.getText());
            nd.setSDT(txt_SDTNV.getText());
            nd.setEmail(txt_EmailNV.getText());
            nd.setTenDangNhap(txt_dangNhapNV.getText());
            nd.setMatKhau(txt_matKhauNV.getText());
            int vaiTro = 0;
            if (chk_keToan.isSelected()) {
                vaiTro = 1;
            }
            if (chk_giangVien.isSelected()) {
                vaiTro = 2;
            }
            if (chk_quanLy.isSelected()) {
                vaiTro = 0;
            }
            nd.setVaiTro(vaiTro);
            int vitri = tbl_tableNV.getSelectedRow();
            int row = (int) tbl_tableNV.getValueAt(vitri, 0);
            nd.setMaNhanVien(row);
            nvDAO.update(nd, conn);
            String s = txt_SDTNV.getText().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
            txt_SDTNV.setText(s);
            fillTableGV();
            Dialog.alert(null, "Sửa thành công");
        }
    }//GEN-LAST:event_btn_SuaNVActionPerformed

    private void btn_ThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNVActionPerformed
        if (checkNullNV()) {
            if (checkTrung()) {
                NguoiDung nd = new NguoiDung();
                nd.setTenNhanVien(txt_tenNhanVien.getText());
                Boolean gioitinh;
                if (rbn_namNV.isSelected()) {
                    gioitinh = true;
                    nd.setGioiTinh(gioitinh);
                }
                if (rbn_NuNV.isSelected()) {
                    gioitinh = false;
                    nd.setGioiTinh(gioitinh);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String date = sdf.format(dateChooser_birthNV.getDate());
                nd.setNgaySinh(date);
                nd.setDiaChi(txt_diaChiNV.getText());
                String s = txt_SDTNV.getText().replaceAll("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
                txt_SDTNV.setText(s);
                nd.setSDT(s);
                nd.setEmail(txt_EmailNV.getText());
                nd.setTenDangNhap(txt_dangNhapNV.getText());
                nd.setMatKhau(txt_matKhauNV.getText());
                int vaiTro = 0;
                if (chk_keToan.isSelected()) {
                    vaiTro = 1;
                }
                if (chk_giangVien.isSelected()) {
                    vaiTro = 2;
                }
                nd.setVaiTro(vaiTro);
                boolean them = nvDAO.insert(nd, conn);
                fillTableGV();

                Dialog.alert(null, "Thêm thành công");

            }
        }
    }//GEN-LAST:event_btn_ThemNVActionPerformed

    private void tbl_tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tableNVMouseClicked
        int vitri = tbl_tableNV.getSelectedRow();
        if (vitri >= 0) {
            int row = (int) tbl_tableNV.getValueAt(vitri, 0);
            NguoiDung nd = nvDAO.fromTableToText(String.valueOf(row), conn);
            if (nd != null) {
                txt_tenNhanVien.setText(nd.getTenNhanVien());
                if (nd.isGioiTinh() == true) {
                    rbn_namNV.setSelected(true);
                }
                if (nd.isGioiTinh() == false) {
                    rbn_NuNV.setSelected(true);
                }

                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tbl_tableNV.getValueAt(vitri, 3));
                    dateChooser_birthNV.setDate(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                txt_diaChiNV.setText(nd.getDiaChi());

                txt_SDTNV.setText(nd.getSDT().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3"));
                txt_EmailNV.setText(nd.getEmail());
                txt_dangNhapNV.setText(nd.getTenDangNhap());
                txt_matKhauNV.setText(nd.getMatKhau());
                if (nd.getVaiTro() == 1) {
                    chk_keToan.setSelected(true);
                }
                if (nd.getVaiTro() == 2) {
                    chk_giangVien.setSelected(true);
                }
                if (nd.getVaiTro() == 0) {
                    chk_quanLy.setSelected(true);
                }

            }
        }
    }//GEN-LAST:event_tbl_tableNVMouseClicked

    private void txt_SDTNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SDTNVActionPerformed

    }//GEN-LAST:event_txt_SDTNVActionPerformed

    private void txt_SDTNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_SDTNVMouseExited

        String s = txt_SDTNV.getText().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
        txt_SDTNV.setText(s);
    }//GEN-LAST:event_txt_SDTNVMouseExited

    private void btn_ClearKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearKTActionPerformed
        txt_EmailNV.setText("");
        txt_SDTNV.setText("");
        txt_dangNhapNV.setText("");
        txt_diaChiNV.setText("");
        txt_matKhauNV.setText("");
        txt_tenNhanVien.setText("");
        txt_timKiemNV.setText("");
        ((JTextField) dateChooser_birthNV.getDateEditor().getUiComponent()).setText("");
        chk_giangVien.setEnabled(false);
        chk_keToan.setEnabled(false);
        chk_quanLy.setEnabled(false);
        lbl_loiTenNV.setText("");
        txt_tenNhanVien.setBorder(border1);
        lbl_loiGioiTinh.setText("");
        pan_gioiTinh.setBorder(border1);
        lbl_loibirthNV.setText("");
        dateChooser_birthNV.setBorder(border1);
        lbl_loiDangNhapNV.setText("");
        txt_dangNhapNV.setBorder(border1);
        lbl_loiMatKhauNV.setText("");
        txt_matKhauNV.setBorder(border1);
        lbl_loiDiaChiNV.setText("");
        txt_diaChiNV.setBorder(border1);
        lbl_loiSDTNV.setText("");
        txt_SDTNV.setBorder(border1);
        lbl_loiEmailNV.setText("");
        txt_EmailNV.setBorder(border1);
        txt_timKiemNV.setText("Nhập mã nhân viên");
        txt_timKiemNV.setBorder(border1);
        txt_timKiemNV.setForeground(Color.black);
       fillTableGV();
       
    }//GEN-LAST:event_btn_ClearKTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ClearKT;
    private javax.swing.JButton btn_SuaNV;
    private javax.swing.JButton btn_ThemNV;
    private javax.swing.JButton btn_TimKiemNV;
    private javax.swing.JCheckBox chk_giangVien;
    private javax.swing.JCheckBox chk_keToan;
    private javax.swing.JCheckBox chk_quanLy;
    private com.toedter.calendar.JDateChooser dateChooser_birthNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_loiDangNhapNV;
    private javax.swing.JLabel lbl_loiDiaChiNV;
    private javax.swing.JLabel lbl_loiEmailNV;
    private javax.swing.JLabel lbl_loiGioiTinh;
    private javax.swing.JLabel lbl_loiMatKhauNV;
    private javax.swing.JLabel lbl_loiSDTNV;
    private javax.swing.JLabel lbl_loiTenNV;
    private javax.swing.JLabel lbl_loibirthNV;
    private javax.swing.JPanel pan_gioiTinh;
    private javax.swing.JRadioButton rbn_NuNV;
    private javax.swing.JRadioButton rbn_namNV;
    private javax.swing.JTable tbl_tableNV;
    private javax.swing.JTextField txt_EmailNV;
    private javax.swing.JTextField txt_SDTNV;
    private javax.swing.JTextField txt_dangNhapNV;
    private javax.swing.JTextField txt_diaChiNV;
    private javax.swing.JTextField txt_matKhauNV;
    private javax.swing.JTextField txt_tenNhanVien;
    private javax.swing.JTextField txt_timKiemNV;
    // End of variables declaration//GEN-END:variables
}
