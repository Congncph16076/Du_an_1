/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.sql.Connection;
import DAO.QuanLyGiangVienDAO;
import DAO.QuanLyKeToanDAO;
import Entity.GiangVien;
import Entity.KeToan;
import Entity.NguoiDung;
import TienIchHoTro.Dialog;
import java.awt.Color;
import java.awt.event.KeyEvent;
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
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author congc
 */
public class QuanLyKeToan_GiangVien extends javax.swing.JFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private List<GiangVien> listGV = new ArrayList<>();
    private List<KeToan> listKT = new ArrayList<>();
    private QuanLyGiangVienDAO gvDAO = new QuanLyGiangVienDAO();
    private QuanLyKeToanDAO ktDAO = new QuanLyKeToanDAO();
    private Connection conn = null;

    public QuanLyKeToan_GiangVien() {
        initComponents();
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        fillTableGV();
        fillTableKT();
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbn_NuGV);
        bg.add(rbn_NamGV);
        bg.add(rbn_NuKT);
        bg.add(rbn_namKT);
    }

    private void fillTableGV() {
        dtm = (DefaultTableModel) tbl_tableGV.getModel();
        dtm.setRowCount(0);
        listGV = gvDAO.selectAll(conn);
        for (GiangVien gv : listGV) {
            Object[] obj = new Object[]{
                gv.getMaGiangVien(),
                gv.getTenGiangVien(),
                gv.getGioiTinh() == 0 ? "Nữ" : "Nam",
                gv.getNgaySinh(),
                gv.getDiaChi(),
                gv.getSDT(),
                gv.getEmail()
            };
            dtm.addRow(obj);
        }
    }

    private void fillTableKT() {
        dtm = (DefaultTableModel) tbl_tableKT.getModel();
        dtm.setRowCount(0);
        listKT = ktDAO.selectAll(conn);
        for (KeToan kt : listKT) {
            Object[] obj = new Object[]{
                kt.getMaKeToan(),
                kt.getTenKeToan(),
                kt.getGioiTinh() == 0 ? "Nữ" : "Nam",
                kt.getNgaySinh(),
                kt.getDiaChi(),
                kt.getSDT(),
                kt.getEmail()
            };
            dtm.addRow(obj);
        }
    }

    boolean checkNullGV() {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Border border = BorderFactory.createLineBorder(Color.red);
        if (txt_TenGiangVien.getText().equals("") && txt_TenGiangVien.getText().isEmpty() && txt_diaChiGV.getText().isEmpty() && dateChooser_birthGV.getDateFormatString().equals("")
                && txt_diaChiGV.getText().equals("") && txt_sdtGV.getText().equals("") && txt_sdtGV.getText().isEmpty() && txt_EmailGV.getText().equals("")) {
            lbl_LoiTenGiangVien.setText("Thiếu tên giảng viên rồi nhập lại bạn nhé!");
            lbl_loiBirthGV.setText("Thiếu ngày sinh rồi chọn lại bạn nhé!");
            lbl_loiDiaChiGV.setText("Thiếu địa chỉ giảng viên rồi nhập lại bạn nhé!");
            lbl_loiSDTGV.setText("thiếu số điện thoại giảng viên rồi nhập lại bạn nhé!");
            lbl_loiEmailGV.setText("Thiếu email giảng viên rồi nhập lại bạn nhé!");
            txt_TenGiangVien.setBorder(border);
            dateChooser_birthGV.setBorder(border);
            txt_diaChiGV.setBorder(border);
            txt_sdtGV.setBorder(border);
            txt_EmailGV.setBorder(border);
            lbl_LoiTenGiangVien.setForeground(Color.red);
            lbl_loiBirthGV.setForeground(Color.red);
            lbl_loiDiaChiGV.setForeground(Color.red);
            lbl_loiSDTGV.setForeground(Color.red);
            lbl_loiEmailGV.setForeground(Color.red);
            txt_TenGiangVien.requestFocus();
            return false;
        }

        if (txt_TenGiangVien.getText().equals("")) {
            lbl_LoiTenGiangVien.setText("Thiếu tên giảng viên rồi nhập lại bạn nhé!");
            txt_TenGiangVien.setBorder(border);
            lbl_LoiTenGiangVien.setForeground(Color.red);
            txt_TenGiangVien.requestFocus();
            return false;
        }
//        if (dateChooser_birthGV.getDate().equals("")) {
//            lbl_loiBirthGV.setText("Thiếu ngày sinh rồi chọn lại bạn nhé!");
//            dateChooser_birthGV.setBorder(border);
//            lbl_loiBirthGV.setForeground(Color.red);
//            dateChooser_birthGV.requestFocus();
//            return false;
//        }
        if (txt_diaChiGV.getText().equals("")) {
            lbl_loiDiaChiGV.setText("Thiếu địa chỉ giảng viên rồi nhập lại bạn nhé!");
            txt_diaChiGV.setBorder(border);
            lbl_loiDiaChiGV.setForeground(Color.red);
            txt_diaChiGV.requestFocus();
            return false;
        }
        if (txt_sdtGV.getText().equals("")) {
            lbl_loiSDTGV.setText("thiếu số điện thoại giảng viên rồi nhập lại bạn nhé!");
            txt_sdtGV.setBorder(border);
            lbl_loiSDTGV.setForeground(Color.red);
            txt_sdtGV.requestFocus();
            return false;
        } else {

            Pattern p = Pattern.compile("^(84|0[3|5|7|8|9])+([0-9]{8})$");
            if (!p.matcher(txt_sdtGV.getText()).find()) {
                lbl_loiSDTGV.setText("Số điện thoại không đúng định dạng của Việt Nam vui lòng nhập lại VD:0914506901");
                txt_sdtGV.setBorder(border);
                txt_sdtGV.requestFocus();
                return false;
            }

        }

        if (txt_EmailGV.equals("")) {
            lbl_loiEmailGV.setText("Thiếu email giảng viên rồi nhập lại bạn nhé!");
            txt_EmailGV.setBorder(border);
            lbl_loiEmailGV.setForeground(Color.red);
            txt_EmailGV.requestFocus();
            return false;
        } else {
            Pattern p = Pattern.compile("^[a-zA-Z.%+-]+[0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
            if (!p.matcher(txt_EmailGV.getText()).find()) {
                lbl_loiEmailGV.setText("Email không đúng định dạng vui lòng nhập đúng định dạng VD:abc@gmail.com");
                txt_EmailGV.setBorder(border);
                txt_EmailGV.requestFocus();
                return false;
            }

        }

        return true;
    }

    boolean checkTrung() {
        Border border = BorderFactory.createLineBorder(Color.red);
        for (GiangVien gv : listGV) {
//            if (txt_TenGiangVien.getText().equals(gv.getTenGiangVien())) {
//                lbl_LoiTenGiangVien.setText("Trùng tên rồi nhập lại bạn nhé!");
//                txt_TenGiangVien.setBorder(border);
//                lbl_LoiTenGiangVien.setForeground(Color.red);
//                txt_TenGiangVien.requestFocus();
//                return false;
//            }

            if (txt_EmailGV.getText().equals(gv.getEmail())) {
                lbl_loiEmailGV.setText("Email bị trùng rồi chọn email khác bạn nhé");
                lbl_loiEmailGV.setForeground(Color.red);
                txt_EmailGV.requestFocus();
                return false;
            }

            if (txt_sdtGV.getText().equals(gv.getSDT())) {
                lbl_loiSDTGV.setText("Số điện thoại bị trùng rồi nhập lại bạn nhé!");
                txt_sdtGV.setBorder(border);
                lbl_loiSDTGV.setForeground(Color.red);
                txt_sdtGV.requestFocus();
                return false;
            }
        }

        return true;
    }

    boolean checknullTKGV() {
        Border border = BorderFactory.createLineBorder(Color.red);
        if (txt_TimKiemGV.getText().equals("")) {
            txt_TimKiemGV.setBorder(border);
            txt_TimKiemGV.setForeground(Color.red);
            txt_TimKiemGV.setText("Bạn chưa nhập tên giảng viên bạn cần tìm vui lòng thử lại");
            txt_TimKiemGV.requestFocus();
            return false;
        } else {
            Border border1 = BorderFactory.createLineBorder(Color.black);
            txt_TimKiemGV.setBorder(border1);
            txt_TimKiemGV.setForeground(Color.black);
        }
        return true;
    }

    boolean checknullKT() {
        Border border = BorderFactory.createLineBorder(Color.red);

        if (txt_TenKeToan.getText().equals("") && txt_diaChiKT.getText().equals("")
                && txt_SDTKT.getText().equals("") && txt_EmaiilKT.getText().equals("")) {
            lbl_loiTenKT.setText("thiếu tên kế toán rồi nhập lại bạn nhé!");
            lbl_loibirthKT.setText("thiếu ngày sinh rồi chọn lại bạn nhé! ");
            lbl_loiDiaChiKT.setText("thiếu địa chỉ rồi nhập lại bạn nhé!");
            lbl_loiSDTKT.setText("thiếu số điện thoại rồi nhập lại bạn nhé!");
            lbl_loiEmailKT.setText("thiếu email rồi nhập lại bạn nhé!");
            txt_TenKeToan.setBorder(border);
            txt_diaChiKT.setBorder(border);
            txt_SDTKT.setBorder(border);
            dateChooser_birthKT.setBorder(border);
            txt_EmaiilKT.setBorder(border);
            lbl_loiTenKT.setForeground(Color.red);
            lbl_loibirthKT.setForeground(Color.red);
            lbl_loiDiaChiKT.setForeground(Color.red);
            lbl_loiSDTKT.setForeground(Color.red);
            lbl_loiEmailKT.setForeground(Color.red);
            txt_TenKeToan.requestFocus();
            return false;
        }

        if (txt_TenKeToan.getText().equals("")) {
            lbl_loiTenKT.setText("thiếu tên kế toán rồi nhập lại bạn nhé!");
            txt_TenKeToan.setBorder(border);
            lbl_loiTenKT.setForeground(Color.red);
            txt_TenKeToan.requestFocus();
            return false;
        }

        if (txt_diaChiKT.getText().equals("")) {
            lbl_loiDiaChiKT.setText("thiếu địa chỉ rồi nhập lại bạn nhé!");
            txt_diaChiKT.setBorder(border);
            lbl_loiDiaChiKT.setForeground(Color.red);
            txt_diaChiKT.requestFocus();
            return false;
        }

        if (txt_SDTKT.getText().equals("")) {
            lbl_loiSDTKT.setText("thiếu số điện thoại rồi nhập lại bạn nhé!");
            txt_SDTKT.setBorder(border);
            lbl_loiSDTKT.setForeground(Color.red);
            txt_SDTKT.requestFocus();
            return false;
        } else {
            Pattern p = Pattern.compile("^(84|0[3|5|7|8|9])+([0-9]{8})$");
            if (!p.matcher(txt_SDTKT.getText()).find()) {
                lbl_loiSDTKT.setText("số điện thoại không đúng với định dạng của Việt Nam VD: 0312345678");
                txt_SDTKT.setBorder(border);
                lbl_loiSDTKT.setForeground(Color.red);
                txt_SDTKT.requestFocus();
                return false;
            }
        }

        if (txt_EmaiilKT.getText().equals("")) {
            lbl_loiEmailKT.setText("thiếu email rồi nhập lại bạn nhé!");
            txt_EmaiilKT.setBorder(border);
            lbl_loiEmailKT.setForeground(Color.red);
            txt_EmaiilKT.requestFocus();
            return false;
        } else {
            Pattern p = Pattern.compile("^[a-zA-Z.%+-]+[0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
            if (!p.matcher(txt_EmaiilKT.getText()).find()) {
                lbl_loiEmailKT.setText("Email chưa đúng định dạng VD: abc@gmail.com");
                txt_EmaiilKT.setBorder(border);
                lbl_loiEmailKT.setForeground(Color.red);
                txt_EmaiilKT.requestFocus();
                return false;
            }
        }

        return true;
    }

    boolean checkTrungKT() {
        Border border = BorderFactory.createLineBorder(Color.red);
        for (KeToan kt : listKT) {
            if (txt_SDTKT.getText().equals(kt.getSDT())) {
                lbl_loiSDTKT.setText(" số điện thoại bị trùng rồi nhập lại bạn nhé!");
                txt_SDTKT.setBorder(border);
                lbl_loiSDTKT.setForeground(Color.red);
                txt_SDTKT.requestFocus();
                return false;
            }
            if (txt_EmaiilKT.getText().equals(kt.getEmail())) {
                lbl_loiEmailKT.setText(" email bị trùng rồi rồi nhập lại bạn nhé!");
                txt_EmaiilKT.setBorder(border);
                lbl_loiEmailKT.setForeground(Color.red);
                txt_EmaiilKT.requestFocus();
                return false;
            }

        }

        return true;
    }

    boolean checknullTKKT() {
        Border border = BorderFactory.createLineBorder(Color.red);

        if (txt_TimKiemKT.getText().equals("")) {
            txt_TimKiemKT.setText("Bạn chưa nhập tên nhân viên kế toán vui lòng nhập lại");
            txt_TimKiemKT.setForeground(Color.red);
            txt_TimKiemKT.setBorder(border);
            txt_TimKiemGV.requestFocus();
            return false;
        } else {
            Border border1 = BorderFactory.createLineBorder(Color.black);
            txt_TimKiemKT.setForeground(Color.black);
            txt_TimKiemKT.setBorder(border);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tab_NV = new javax.swing.JTabbedPane();
        tab_GV = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lbl_loiDiaChiGV = new javax.swing.JLabel();
        txt_TenGiangVien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rbn_NamGV = new javax.swing.JRadioButton();
        rbn_NuGV = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        dateChooser_birthGV = new com.toedter.calendar.JDateChooser();
        lbl_loiBirthGV = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_diaChiGV = new javax.swing.JTextField();
        lbl_LoiTenGiangVien = new javax.swing.JLabel();
        lbl_loiSDTGV = new javax.swing.JLabel();
        txt_sdtGV = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lbl_loiEmailGV = new javax.swing.JLabel();
        txt_EmailGV = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_ThemGV = new javax.swing.JButton();
        btn_SuaGV = new javax.swing.JButton();
        btn_XoaGV = new javax.swing.JButton();
        btn_ClearGV = new javax.swing.JButton();
        txt_TimKiemGV = new javax.swing.JTextField();
        btn_TimKiemGV = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_tableGV = new javax.swing.JTable();
        tab_KT = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbl_loiDiaChiKT = new javax.swing.JLabel();
        txt_TenKeToan = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        rbn_namKT = new javax.swing.JRadioButton();
        rbn_NuKT = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        dateChooser_birthKT = new com.toedter.calendar.JDateChooser();
        lbl_loibirthKT = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_diaChiKT = new javax.swing.JTextField();
        lbl_loiTenKT = new javax.swing.JLabel();
        lbl_loiSDTKT = new javax.swing.JLabel();
        txt_SDTKT = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        lbl_loiEmailKT = new javax.swing.JLabel();
        txt_EmaiilKT = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_tableKT = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btn_ThemKT = new javax.swing.JButton();
        btn_SuaKT = new javax.swing.JButton();
        btn_XoaKT = new javax.swing.JButton();
        btn_ClearKT = new javax.swing.JButton();
        txt_TimKiemKT = new javax.swing.JTextField();
        btn_TimKiemKT = new javax.swing.JButton();
        btn_TrangChu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Quản lý Nhân Viên");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl_loiDiaChiGV.setForeground(new java.awt.Color(255, 51, 51));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tên giảng viên");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Giới tính");

        rbn_NamGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbn_NamGV.setText("Nam");

        rbn_NuGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbn_NuGV.setText("Nữ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Ngày sinh");

        lbl_loiBirthGV.setForeground(new java.awt.Color(255, 51, 51));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ");

        lbl_LoiTenGiangVien.setForeground(new java.awt.Color(255, 51, 51));

        lbl_loiSDTGV.setForeground(new java.awt.Color(255, 51, 51));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Số điện thoại");

        lbl_loiEmailGV.setForeground(new java.awt.Color(255, 51, 51));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Email");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_loiBirthGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_loiEmailGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_EmailGV)
                            .addComponent(txt_diaChiGV)
                            .addComponent(dateChooser_birthGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_loiDiaChiGV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_TenGiangVien)
                            .addComponent(lbl_LoiTenGiangVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_sdtGV)
                            .addComponent(lbl_loiSDTGV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel12))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbn_NamGV, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addComponent(rbn_NuGV, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 125, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_LoiTenGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbn_NuGV)
                    .addComponent(rbn_NamGV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateChooser_birthGV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiBirthGV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_diaChiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiDiaChiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_sdtGV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiSDTGV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_EmailGV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiEmailGV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btn_ThemGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Add.png"))); // NOI18N
        btn_ThemGV.setText("Thêm tài khoản");
        btn_ThemGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemGVActionPerformed(evt);
            }
        });

        btn_SuaGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_SuaGV.setText("Sửa tài khoản");
        btn_SuaGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaGVActionPerformed(evt);
            }
        });

        btn_XoaGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Delete.png"))); // NOI18N
        btn_XoaGV.setText("Xóa tài khoản");
        btn_XoaGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaGVActionPerformed(evt);
            }
        });

        btn_ClearGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_ClearGV.setText("Clear ");
        btn_ClearGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearGVActionPerformed(evt);
            }
        });

        txt_TimKiemGV.setText("Nhập tên giảng viên");

        btn_TimKiemGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_TimKiemGV.setText("Tìm kiếm");
        btn_TimKiemGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemGVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_TimKiemGV, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemGV, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_ThemGV)
                        .addGap(10, 10, 10)
                        .addComponent(btn_SuaGV)
                        .addGap(10, 10, 10)
                        .addComponent(btn_XoaGV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ClearGV, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_TimKiemGV)
                    .addComponent(btn_TimKiemGV, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemGV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SuaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XoaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ClearGV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbl_tableGV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã giảng viên", "Tên giảng viên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email"
            }
        ));
        tbl_tableGV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tableGVMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_tableGV);

        javax.swing.GroupLayout tab_GVLayout = new javax.swing.GroupLayout(tab_GV);
        tab_GV.setLayout(tab_GVLayout);
        tab_GVLayout.setHorizontalGroup(
            tab_GVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_GVLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab_GVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab_GVLayout.setVerticalGroup(
            tab_GVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_GVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_GVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(tab_GVLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tab_NV.addTab("Quản lý giảng viên", tab_GV);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl_loiDiaChiKT.setForeground(new java.awt.Color(255, 51, 51));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Tên kế toán");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Giới tính");

        rbn_namKT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbn_namKT.setText("Nam");

        rbn_NuKT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbn_NuKT.setText("Nữ");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Ngày sinh");

        lbl_loibirthKT.setForeground(new java.awt.Color(255, 51, 51));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Địa chỉ");

        lbl_loiTenKT.setForeground(new java.awt.Color(255, 51, 51));

        lbl_loiSDTKT.setForeground(new java.awt.Color(255, 51, 51));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Số điện thoại");

        lbl_loiEmailKT.setForeground(new java.awt.Color(255, 51, 51));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Email");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_loibirthKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_diaChiKT, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooser_birthKT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiDiaChiKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_TenKeToan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_loiTenKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_SDTKT, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_loiSDTKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_EmaiilKT, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_loiEmailKT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel25)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbn_namKT, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addGap(18, 18, 18)
                                .addComponent(rbn_NuKT, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15)
                            .addComponent(jLabel22))
                        .addGap(0, 138, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenKeToan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTenKT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbn_NuKT)
                    .addComponent(rbn_namKT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateChooser_birthKT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loibirthKT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_diaChiKT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiDiaChiKT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_SDTKT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiSDTKT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_EmaiilKT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_loiEmailKT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        tbl_tableKT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã kế toán", "Tên kế toán", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email"
            }
        ));
        tbl_tableKT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tableKTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_tableKT);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btn_ThemKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Add.png"))); // NOI18N
        btn_ThemKT.setText("Thêm tài khoản");
        btn_ThemKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemKTActionPerformed(evt);
            }
        });

        btn_SuaKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_SuaKT.setText("Sửa tài khoản");
        btn_SuaKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaKTActionPerformed(evt);
            }
        });

        btn_XoaKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Delete.png"))); // NOI18N
        btn_XoaKT.setText("Xóa tài khoản");
        btn_XoaKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaKTActionPerformed(evt);
            }
        });

        btn_ClearKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_ClearKT.setText("Clear ");

        txt_TimKiemKT.setText("Nhập mã nhân viên");

        btn_TimKiemKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_TimKiemKT.setText("Tìm kiếm");
        btn_TimKiemKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemKTActionPerformed(evt);
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
                        .addComponent(txt_TimKiemKT, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemKT, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_ThemKT)
                        .addGap(10, 10, 10)
                        .addComponent(btn_SuaKT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_XoaKT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ClearKT, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_TimKiemKT)
                    .addComponent(btn_TimKiemKT, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemKT, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SuaKT, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XoaKT, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ClearKT, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout tab_KTLayout = new javax.swing.GroupLayout(tab_KT);
        tab_KT.setLayout(tab_KTLayout);
        tab_KTLayout.setHorizontalGroup(
            tab_KTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_KTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab_KTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        tab_KTLayout.setVerticalGroup(
            tab_KTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_KTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_KTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 531, Short.MAX_VALUE)
                    .addGroup(tab_KTLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tab_NV.addTab("Quản lý kế toán", tab_KT);

        btn_TrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Home.png"))); // NOI18N
        btn_TrangChu.setText("Trang chủ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btn_TrangChu)
                .addGap(248, 248, 248)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab_NV)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TrangChu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tab_NV, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void tbl_tableGVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tableGVMouseClicked
        int vitri = tbl_tableGV.getSelectedRow();
        if (vitri >= 0) {
            String row = (String) tbl_tableGV.getValueAt(vitri, 1);
            //tbl_tableGV.getValueAt(vitri, 0);
            GiangVien gv = gvDAO.fromTableToText(row, conn);
            if (gv != null) {
                txt_TenGiangVien.setText(gv.getTenGiangVien());
                //int gioiTinh = -1;
                if (gv.getGioiTinh() == 1) {
                    rbn_NamGV.setSelected(true);
                }
                if (gv.getGioiTinh() == 0) {
                    rbn_NuGV.setSelected(true);
                }
                //gv.setGioiTinh(gioiTinh);
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tbl_tableGV.getValueAt(vitri, 3));
                    dateChooser_birthGV.setDate(date);
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLyKeToan_GiangVien.class.getName()).log(Level.SEVERE, null, ex);
                }

                txt_diaChiGV.setText(gv.getDiaChi());
                txt_sdtGV.setText(gv.getSDT());
                txt_EmailGV.setText(gv.getEmail());
            }
        }

    }//GEN-LAST:event_tbl_tableGVMouseClicked

    private void btn_ThemGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemGVActionPerformed
        try {
            if (checkNullGV()) {
                if (checkTrung()) {
                    Border border = BorderFactory.createLineBorder(Color.black);
                    txt_TenGiangVien.setBorder(border);
                    dateChooser_birthGV.setBorder(border);
                    txt_diaChiGV.setBorder(border);
                    txt_sdtGV.setBorder(border);
                    txt_EmailGV.setBorder(border);
                    lbl_LoiTenGiangVien.setText("");
                    lbl_loiBirthGV.setText("");
                    lbl_loiDiaChiGV.setText("");
                    lbl_loiSDTGV.setText("");
                    lbl_loiEmailGV.setText("");
                    GiangVien gv = new GiangVien();
                    int gioiTinh = 0;
                    gv.setTenGiangVien(txt_TenGiangVien.getText());
                    if (rbn_NamGV.isSelected()) {
                        gioiTinh = 1;
                    }
                    if (rbn_NuGV.isSelected()) {
                        gioiTinh = 0;
                    }
                    gv.setGioiTinh(gioiTinh);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String date = sdf.format(dateChooser_birthGV.getDate());
                    gv.setNgaySinh(date);
                    gv.setDiaChi(txt_diaChiGV.getText());
                    gv.setSDT(txt_sdtGV.getText());
                    gv.setEmail(txt_EmailGV.getText());
                    boolean themGV = gvDAO.insert(gv, conn);
                    fillTableGV();
                    Dialog.alert(this, "Thêm thành công");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Dialog.alert(this, "Thêm không thành công" + "" + e.getMessage());
        }
    }//GEN-LAST:event_btn_ThemGVActionPerformed

    private void btn_SuaGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaGVActionPerformed
        try {
            if (checkNullGV()) {
                GiangVien gv = new GiangVien();
                gv.setTenGiangVien(txt_TenGiangVien.getText());
                int gioiTinh = -1;
                if (rbn_NamGV.isSelected()) {
                    gioiTinh = 1;
                }
                if (rbn_NuGV.isSelected()) {
                    gioiTinh = 0;
                }
                gv.setGioiTinh(gioiTinh);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String date = sdf.format(dateChooser_birthGV.getDate());
                gv.setNgaySinh(date);
                gv.setDiaChi(txt_diaChiGV.getText());
                gv.setSDT(txt_sdtGV.getText());
                gv.setEmail(txt_EmailGV.getText());
                int vitri = tbl_tableGV.getSelectedRow();
                int row = (int) tbl_tableGV.getValueAt(vitri, 0);
                gv.setMaGiangVien(row);
                gvDAO.update(gv, conn);
                fillTableGV();
                Dialog.alert(this, "Sửa không thành công");
            }
        } catch (Exception e) {
            Dialog.alert(this, "Sửa không thành công" + "" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_SuaGVActionPerformed

    private void btn_XoaGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaGVActionPerformed
        try {
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn nhân viên này", "Xóa tài khoản nhân viên", JOptionPane.YES_NO_OPTION);
            if (hoi == JOptionPane.YES_OPTION) {
                int vitri = tbl_tableGV.getSelectedRow();
                if (vitri >= 0) {
                    Integer row = (Integer) tbl_tableGV.getValueAt(vitri, 0);
                    GiangVien gv = gvDAO.delete(String.valueOf(row), conn);
                    fillTableGV();
                }
                Dialog.alert(this, "xóa thành công");
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Dialog.alert(this, "xóa không thành công" + " " + e.getMessage());
        }
    }//GEN-LAST:event_btn_XoaGVActionPerformed

    private void btn_TimKiemGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemGVActionPerformed
        try {
            fillTableGV();
            if (checknullTKGV()) {
                if (checkTrung()) {
                    List<GiangVien> list = gvDAO.search(txt_TimKiemGV.getText(), conn);
                    dtm.setRowCount(0);
                    dtm = (DefaultTableModel) tbl_tableGV.getModel();
                    for (GiangVien gv : list) {
                        Vector<Object> vec = new Vector<>();
                        vec.add(gv.getMaGiangVien());
                        vec.add(gv.getTenGiangVien());
                        String gioiTinh = gv.getGioiTinh() == 0 ? "nữ" : "nam";
                        vec.add(gioiTinh);
                        vec.add(gv.getNgaySinh());
                        vec.add(gv.getDiaChi());
                        vec.add(gv.getSDT());
                        vec.add(gv.getEmail());
                        dtm.addRow(vec);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Dialog.alert(this, "Mã giảng viên không tồn tại" + "" + e.getMessage());
        }
    }//GEN-LAST:event_btn_TimKiemGVActionPerformed

    private void btn_ClearGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearGVActionPerformed
        txt_EmailGV.setText("");
        txt_TenGiangVien.setText("");
        txt_sdtGV.setText("");
        txt_diaChiGV.setText("");
        dateChooser_birthGV.setDateFormatString("");
        fillTableGV();
    }//GEN-LAST:event_btn_ClearGVActionPerformed

    private void tbl_tableKTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tableKTMouseClicked
        int vitri = tbl_tableKT.getSelectedRow();
        if (vitri >= 0) {
            String row = (String) tbl_tableKT.getValueAt(vitri, 1);
            KeToan kt = ktDAO.fromTableToText(row, conn);
            if (kt != null) {
                txt_TenKeToan.setText(kt.getTenKeToan());
                if (kt.getGioiTinh() == 1) {
                    rbn_namKT.setSelected(true);
                }
                if (kt.getGioiTinh() == 0) {
                    rbn_NuKT.setSelected(true);
                }
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tbl_tableKT.getValueAt(vitri, 3));
                    dateChooser_birthKT.setDate(date);
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLyKeToan_GiangVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                txt_diaChiKT.setText(kt.getDiaChi());
                txt_SDTKT.setText(kt.getSDT());
                txt_EmaiilKT.setText(kt.getEmail());
            }
        }
    }//GEN-LAST:event_tbl_tableKTMouseClicked

    private void btn_XoaKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaKTActionPerformed
        try {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa nhân viên kế toán này chứ?", "Xóa nhân viên kế toán", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_NO_OPTION) {
                int vitri = tbl_tableKT.getSelectedRow();
                if (vitri >= 0) {
                    int row = (int) tbl_tableKT.getValueAt(vitri, 0);
                    KeToan kt = ktDAO.delete(String.valueOf(row), conn);
                    fillTableKT();
                }
                Dialog.alert(this, "Xóa thành công");
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Dialog.alert(this, "Xóa không thành công" + "" + e.getMessage());
        }
    }//GEN-LAST:event_btn_XoaKTActionPerformed

    private void btn_ThemKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemKTActionPerformed
        try {
            if (checkTrungKT()) {
                if (checknullKT()) {
                    Border border1 = BorderFactory.createLineBorder(Color.black);
                    lbl_loiTenKT.setText("");
                    lbl_loibirthKT.setText("");
                    lbl_loiDiaChiKT.setText("");
                    lbl_loiSDTKT.setText("");
                    lbl_loiEmailKT.setText("");
                    txt_TenKeToan.setBorder(border1);
                    txt_diaChiKT.setBorder(border1);
                    txt_SDTKT.setBorder(border1);
                    dateChooser_birthKT.setBorder(border1);
                    txt_EmaiilKT.setBorder(border1);
                    KeToan kt = new KeToan();
                    kt.setTenKeToan(txt_TenKeToan.getText());
                    int gioiTinh = -1;
                    if (rbn_namKT.isSelected()) {
                        gioiTinh = 1;
                    }
                    if (rbn_NuKT.isSelected()) {
                        gioiTinh = 0;
                    }
                    kt.setGioiTinh(gioiTinh);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String date = sdf.format(dateChooser_birthKT.getDate());
                    kt.setNgaySinh(date);
                    kt.setDiaChi(txt_diaChiKT.getText());
                    kt.setSDT(txt_SDTKT.getText());
                    kt.setEmail(txt_EmaiilKT.getText());
                    boolean themKT = ktDAO.insert(kt, conn);
                    fillTableKT();
                    Dialog.alert(this, "Thêm thành công");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Dialog.alert(this, "Thêm không thành công" + "" + e.getMessage());
        }
    }//GEN-LAST:event_btn_ThemKTActionPerformed

    private void btn_SuaKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaKTActionPerformed
        KeToan kt = new KeToan();
        kt.setTenKeToan(txt_TenKeToan.getText());
        int gioiTinh = -1;
        if (rbn_namKT.isSelected()) {
            gioiTinh = 1;
        }
        if (rbn_NuKT.isSelected()) {
            gioiTinh = 0;
        }
        kt.setGioiTinh(gioiTinh);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(dateChooser_birthKT.getDate());
        kt.setNgaySinh(date);
        kt.setDiaChi(txt_diaChiKT.getText());
        kt.setSDT(txt_SDTKT.getText());
        kt.setEmail(txt_EmaiilKT.getText());
        int vitri = tbl_tableKT.getSelectedRow();
        int row = (int) tbl_tableKT.getValueAt(vitri, 0);
        kt.setMaKeToan(row);
        ktDAO.update(kt, conn);
        fillTableKT();
    }//GEN-LAST:event_btn_SuaKTActionPerformed

    private void btn_TimKiemKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemKTActionPerformed
        try {

            if (checknullTKKT()) {

                List<KeToan> list = ktDAO.search(txt_TimKiemKT.getText(), conn);
                dtm.setRowCount(0);
                dtm = (DefaultTableModel) tbl_tableKT.getModel();
                for (KeToan kt : list) {
                    Vector<Object> vec = new Vector<>();
                    vec.add(kt.getMaKeToan());
                    vec.add(kt.getTenKeToan());
                    String gioiTinh = kt.getGioiTinh() == 0 ? "nữ" : "nam";
                    vec.add(gioiTinh);
                    vec.add(kt.getNgaySinh());
                    vec.add(kt.getDiaChi());
                    vec.add(kt.getSDT());
                    vec.add(kt.getEmail());
                    dtm.addRow(vec);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Dialog.alert(this, " Nhân viên không tồn tại" + "" + e.getMessage());
        }
    }//GEN-LAST:event_btn_TimKiemKTActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyKeToan_GiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyKeToan_GiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyKeToan_GiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyKeToan_GiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyKeToan_GiangVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ClearGV;
    private javax.swing.JButton btn_ClearKT;
    private javax.swing.JButton btn_SuaGV;
    private javax.swing.JButton btn_SuaKT;
    private javax.swing.JButton btn_ThemGV;
    private javax.swing.JButton btn_ThemKT;
    private javax.swing.JButton btn_TimKiemGV;
    private javax.swing.JButton btn_TimKiemKT;
    private javax.swing.JButton btn_TrangChu;
    private javax.swing.JButton btn_XoaGV;
    private javax.swing.JButton btn_XoaKT;
    private com.toedter.calendar.JDateChooser dateChooser_birthGV;
    private com.toedter.calendar.JDateChooser dateChooser_birthKT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_LoiTenGiangVien;
    private javax.swing.JLabel lbl_loiBirthGV;
    private javax.swing.JLabel lbl_loiDiaChiGV;
    private javax.swing.JLabel lbl_loiDiaChiKT;
    private javax.swing.JLabel lbl_loiEmailGV;
    private javax.swing.JLabel lbl_loiEmailKT;
    private javax.swing.JLabel lbl_loiSDTGV;
    private javax.swing.JLabel lbl_loiSDTKT;
    private javax.swing.JLabel lbl_loiTenKT;
    private javax.swing.JLabel lbl_loibirthKT;
    private javax.swing.JRadioButton rbn_NamGV;
    private javax.swing.JRadioButton rbn_NuGV;
    private javax.swing.JRadioButton rbn_NuKT;
    private javax.swing.JRadioButton rbn_namKT;
    private javax.swing.JPanel tab_GV;
    private javax.swing.JPanel tab_KT;
    private javax.swing.JTabbedPane tab_NV;
    private javax.swing.JTable tbl_tableGV;
    private javax.swing.JTable tbl_tableKT;
    private javax.swing.JTextField txt_EmaiilKT;
    private javax.swing.JTextField txt_EmailGV;
    private javax.swing.JTextField txt_SDTKT;
    private javax.swing.JTextField txt_TenGiangVien;
    private javax.swing.JTextField txt_TenKeToan;
    private javax.swing.JTextField txt_TimKiemGV;
    private javax.swing.JTextField txt_TimKiemKT;
    private javax.swing.JTextField txt_diaChiGV;
    private javax.swing.JTextField txt_diaChiKT;
    private javax.swing.JTextField txt_sdtGV;
    // End of variables declaration//GEN-END:variables
}
