/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.LopDAO;
import DAO.QuanLyNVDAO;
import Entity.Lop;
import Entity.NguoiDung;
import TienIchHoTro.Dialog;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class QLLop extends javax.swing.JInternalFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private List<Lop> listLop = new ArrayList<>();
    LopDAO lopDAO = new LopDAO();
    Connection conn = null;
    Border boder = BorderFactory.createLineBorder(Color.red);
    Border boder1 = BorderFactory.createLineBorder(Color.black);
    private DefaultComboBoxModel<String> dcb = new DefaultComboBoxModel();
    NguoiDung nd = new NguoiDung();

    public QLLop() {
        initComponents();
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        listCBCLoaiLop();
        listCBCCapLop();
        listCaHoc();
        fillTable();
        filltableLL();
        filltableCL();
        for (int i = 0; i < tbl_lop.getRowCount(); i++) {
            
        }
        txt_tenGiangVien.setEditable(false);
        txt_tenGiangVien.setEnabled(false);
        ButtonGroup btnGr = new ButtonGroup();
        btnGr.add(rbn_HoatDong);
        btnGr.add(rbn_KetThuc);
        
    }

    void listCBCLoaiLop() {
        this.cbc_loaiLop.removeAllItems();
        listLop = lopDAO.cbcLoaiLop(conn);
        this.cbc_loaiLop.addItem("Loại lớp");
        for (Lop lop : listLop) {
            this.cbc_loaiLop.addItem(lop.getTenLoaiLop());
        }
    }

    void listCBCCapLop() {
        this.cbc_capLop.removeAllItems();
        listLop = lopDAO.cbcCapLop(conn);
        this.cbc_capLop.addItem("Cấp Lớp");
        for (Lop lop : listLop) {
            this.cbc_capLop.addItem(lop.getTenCapLop());
        }
    }

    void listCaHoc() {
        this.cbc_caHoc.removeAllItems();
        String[] caHoc = new String[]{
            "Ca học", "Ca 1 (7h-9h) 246", "Ca 2 (9h-11h) 246", "Ca 3 (12h-14h) 246", "Ca 4 (14h-16h) 246",
             "Ca 1 (7h-9h) 357", "Ca 2 (9h-11h) 357", "Ca 3 (12h-14h) 357", "Ca 4 (14h-16h) 357"
        };
        for (int i = 0; i < caHoc.length; i++) {
            cbc_caHoc.addItem(caHoc[i]);
        }
    }

    void fillTable() {
        dtm = (DefaultTableModel) tbl_lop.getModel();
        dtm.setRowCount(0);
        listLop = lopDAO.listLop(conn);
        for (Lop l : listLop) {
            Object[] obj = new Object[]{
                l.getMaLop(), l.getTenLop(), l.getMaNhanVien(), l.getTenNhanVien(),
                l.getHocPhi(),
                l.getCaHoc(), l.getSiso(), l.getTenLoaiLop(), l.getTenCapLop(), l.getNgayNhapHoc(), l.getNgayKeThuc(),
                 l.isTrangThai() == true ? "Hoạt động" : "Đã kết thúc"
            };
            dtm.addRow(obj);
        }
    }

    void filltableLL() {
        dtm = (DefaultTableModel) tbl_loaiLop.getModel();
        dtm.setRowCount(0);
        listLop = lopDAO.tblLoaiLop(conn);
        for (Lop l : listLop) {
            Object[] obj = new Object[]{
                l.getMaLoaiLop(), l.getTenLoaiLop(), l.getSoLuong()
            };
            dtm.addRow(obj);
        }
    }

    void filltableCL() {
        dtm = (DefaultTableModel) tbl_capLop.getModel();
        dtm.setRowCount(0);
        listLop = lopDAO.tblCapLop(conn);
        for (Lop l : listLop) {
            Object[] obj = new Object[]{
                l.getMaCapLop(), l.getTenCapLop(), l.getSoLuong()
            };
            dtm.addRow(obj);
        }
    }

    boolean checknull() {
        String datebatdau = ((JTextField) date_ngayBatDau.getDateEditor().getUiComponent()).getText();
        String dateketthuc = ((JTextField) date_ngayKetThuc.getDateEditor().getUiComponent()).getText();
        if (txt_tenLop.getText().equals("")) {
            lbl_loiTenLop.setText("Thiếu tên lớp rồi bạn!");
            lbl_loiTenLop.setForeground(Color.red);
            txt_tenLop.setBorder(boder);
            txt_tenLop.requestFocus();
            return false;
        } else {
            txt_tenLop.setBorder(boder1);
            lbl_loiTenLop.setText("");
        }

        if (txt_maGiangvien.getText().equals("")) {
            lbl_loiMaGiangVien.setText("thiếu mã giảng viên rồi bạn!");
            lbl_loiMaGiangVien.setForeground(Color.red);
            txt_maGiangvien.setBorder(boder);
            txt_maGiangvien.requestFocus();
            return false;
        } else {
            lbl_loiMaGiangVien.setText("");
            txt_maGiangvien.setBorder(boder1);

        }

        if (txt_hocPhi.getText().equals("")) {
            lbl_loiHocPhi.setText("Thiếu học phí rồi bạn!");
            lbl_loiHocPhi.setForeground(Color.red);
            txt_hocPhi.setBorder(boder);
            return false;
        } else {
            lbl_loiHocPhi.setText("");
            txt_hocPhi.setBorder(boder1);
        }

        if (cbc_caHoc.getSelectedIndex() == 0) {
            lbl_loiCaHoc.setText("Bạn không được chọn mục đầu tiên");
            lbl_loiCaHoc.setForeground(Color.red);
            cbc_caHoc.setBorder(boder);
            cbc_caHoc.requestFocus();
            return false;
        } else {
            lbl_loiCaHoc.setText("");
            cbc_caHoc.setBorder(boder1);
        }

        if (txt_siSo.getText().equals("")) {
            lbl_loiSiSo.setText("Bạn thiếu sĩ số rồi");
            lbl_loiSiSo.setForeground(Color.red);
            txt_siSo.setBorder(boder);
            txt_siSo.requestFocus();
            return false;
        } else {
            lbl_loiSiSo.setText("");
            txt_siSo.setBorder(boder1);
        }

        if (datebatdau.equals("")) {
            lbl_loiNgayBatDau.setText("thiếu ngày bắt đầu học rồi bạn!");
            lbl_loiNgayBatDau.setForeground(Color.red);
            date_ngayBatDau.setBorder(boder);
            date_ngayBatDau.requestFocus();
            return false;
        } else {
            lbl_loiNgayBatDau.setText("");
            date_ngayBatDau.setBorder(boder1);
        }
        if (dateketthuc.equals("")) {
            lbl_loiNgayKetThuc.setText("thiếu ngày kết thúc học rồi bạn!");
            lbl_loiNgayKetThuc.setForeground(Color.red);
            date_ngayBatDau.setBorder(boder);
            date_ngayKetThuc.requestFocus();
        } else {
            lbl_loiNgayKetThuc.setText("");
            date_ngayKetThuc.setBorder(boder1);
        }
        return true;
    }

    boolean checknullTKLop() {
        if (txt_timKiem.getText().equals("")) {
            txt_timKiem.setText("Bạn chưa nhập tên lớp cần tìm vui lòng thử lại!");
            txt_timKiem.setBorder(boder);
            txt_timKiem.setForeground(Color.red);
            txt_timKiem.requestFocus();
            return false;
        } else {
            txt_timKiem.setBorder(boder1);
            txt_timKiem.setForeground(Color.black);
        }
        return true;
    }

    boolean checknullLoaiLop() {
        if (txt_timKiemLoaiLop.getText().equals("")) {
            txt_timKiemLoaiLop.setText("Bạn chưa nhập loại lớp cần tìm vui lòng thử lại!");
            txt_timKiemLoaiLop.setBorder(boder);
            txt_timKiemLoaiLop.setForeground(Color.red);
            txt_timKiemLoaiLop.requestFocus();
            return false;
        } else {
            txt_timKiemLoaiLop.setBorder(boder1);
            txt_timKiemLoaiLop.setForeground(Color.black);
        }
        return true;
    }

    boolean checknullCapLop() {
        if (txt_timKiemCapLop.getText().equals("")) {
            txt_timKiemCapLop.setText("Bạn chưa nhập cấp lớp cần tìm vui lòng thử lại!");
            txt_timKiemCapLop.setBorder(boder);
            txt_timKiemCapLop.setForeground(Color.red);
            txt_timKiemCapLop.requestFocus();
            return false;
        } else {
            txt_timKiemCapLop.setBorder(boder1);
            txt_timKiemCapLop.setForeground(Color.black);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_loaiLop = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        txt_tenLoaiLop = new javax.swing.JTextField();
        lbl_LoiTenLoaiLop2 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_timKiemLoaiLop = new javax.swing.JTextField();
        btn_timKiemLoaiLop = new javax.swing.JButton();
        btn_themLoaiLop = new javax.swing.JButton();
        btn_suaLoaiLop = new javax.swing.JButton();
        btn_xoaLoaiLop = new javax.swing.JButton();
        btn_clearLoaiLop = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_capLop = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        lbl_loiTenCapLop = new javax.swing.JLabel();
        txt_tenCapLop = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_timKiemCapLop = new javax.swing.JTextField();
        btn_timKiemCapLop = new javax.swing.JButton();
        btn_themCapLop = new javax.swing.JButton();
        btn_suaCapLop = new javax.swing.JButton();
        btn_xoaCapLop = new javax.swing.JButton();
        btn_clearCapLop = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        txt_timKiem = new javax.swing.JTextField();
        btn_timKiemLop = new javax.swing.JButton();
        btn_themLop = new javax.swing.JButton();
        btn_suaLop = new javax.swing.JButton();
        btn_xoaLop = new javax.swing.JButton();
        btn_clearLop = new javax.swing.JButton();
        btnChotDiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_lop = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txt_tenLop = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txt_maGiangvien = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txt_hocPhi = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        cbc_caHoc = new javax.swing.JComboBox<>();
        lbl_loiHocPhi = new javax.swing.JLabel();
        lbl_loiMaGiangVien = new javax.swing.JLabel();
        lbl_loiTenLop = new javax.swing.JLabel();
        lbl_loiCaHoc = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txt_siSo = new javax.swing.JTextField();
        lbl_loiSiSo = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        cbc_loaiLop = new javax.swing.JComboBox<>();
        lbl_loiLoaiLop = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        cbc_capLop = new javax.swing.JComboBox<>();
        lbl_loiCapLop = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        date_ngayBatDau = new com.toedter.calendar.JDateChooser();
        lbl_loiNgayBatDau = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        date_ngayKetThuc = new com.toedter.calendar.JDateChooser();
        lbl_loiNgayKetThuc = new javax.swing.JLabel();
        txt_tenGiangVien = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        rbn_HoatDong = new javax.swing.JRadioButton();
        rbn_KetThuc = new javax.swing.JRadioButton();
        lbl_loiHoatDong = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Quản lý lớp");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Quản lý lớp");

        tbl_loaiLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã loại lớp", "Loại lớp", "Số lượng"
            }
        ));
        tbl_loaiLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_loaiLopMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_loaiLop);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Loại lớp");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tenLoaiLop)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(0, 225, Short.MAX_VALUE))
                    .addComponent(lbl_LoiTenLoaiLop2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(98, 98, 98))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenLoaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_LoiTenLoaiLop2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tìm kiếm");

        btn_timKiemLoaiLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_timKiemLoaiLop.setText("Tìm kiếm");
        btn_timKiemLoaiLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemLoaiLopActionPerformed(evt);
            }
        });

        btn_themLoaiLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Add.png"))); // NOI18N
        btn_themLoaiLop.setText("Thêm");
        btn_themLoaiLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themLoaiLopActionPerformed(evt);
            }
        });

        btn_suaLoaiLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_suaLoaiLop.setText("Sửa");
        btn_suaLoaiLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaLoaiLopActionPerformed(evt);
            }
        });

        btn_xoaLoaiLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Delete.png"))); // NOI18N
        btn_xoaLoaiLop.setText("Xóa");
        btn_xoaLoaiLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaLoaiLopActionPerformed(evt);
            }
        });

        btn_clearLoaiLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_clearLoaiLop.setText("Clear");
        btn_clearLoaiLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearLoaiLopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(40, 40, 40)
                        .addComponent(txt_timKiemLoaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btn_timKiemLoaiLop))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btn_themLoaiLop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_suaLoaiLop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_xoaLoaiLop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_clearLoaiLop)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_timKiemLoaiLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_timKiemLoaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_themLoaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_suaLoaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoaLoaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clearLoaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 327, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(262, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Loại lớp", jPanel11);

        tbl_capLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã cấp lớp", "Cấp lớp", "Số lượng"
            }
        ));
        tbl_capLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_capLopMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_capLop);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Cấp lớp");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tenCapLop)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(0, 228, Short.MAX_VALUE))
                    .addComponent(lbl_loiTenCapLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(98, 98, 98))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTenCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Tìm kiếm");

        btn_timKiemCapLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_timKiemCapLop.setText("Tìm kiếm");
        btn_timKiemCapLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemCapLopActionPerformed(evt);
            }
        });

        btn_themCapLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Add.png"))); // NOI18N
        btn_themCapLop.setText("Thêm");
        btn_themCapLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themCapLopActionPerformed(evt);
            }
        });

        btn_suaCapLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_suaCapLop.setText("Sửa");
        btn_suaCapLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaCapLopActionPerformed(evt);
            }
        });

        btn_xoaCapLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Delete.png"))); // NOI18N
        btn_xoaCapLop.setText("Xóa");
        btn_xoaCapLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaCapLopActionPerformed(evt);
            }
        });

        btn_clearCapLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_clearCapLop.setText("Clear");
        btn_clearCapLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearCapLopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(40, 40, 40)
                        .addComponent(txt_timKiemCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btn_timKiemCapLop))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btn_themCapLop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_suaCapLop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_xoaCapLop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_clearCapLop)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_timKiemCapLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_timKiemCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_themCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_suaCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoaCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clearCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cấp lớp", jPanel12);

        jPanel6.setPreferredSize(new java.awt.Dimension(1267, 1267));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Tìm kiếm");

        txt_timKiem.setText("Nhập tên lớp");

        btn_timKiemLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_timKiemLop.setText("Tìm Kiếm");
        btn_timKiemLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemLopActionPerformed(evt);
            }
        });

        btn_themLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Create.png"))); // NOI18N
        btn_themLop.setText("Thêm");
        btn_themLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themLopActionPerformed(evt);
            }
        });

        btn_suaLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_suaLop.setText("Sửa");
        btn_suaLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaLopActionPerformed(evt);
            }
        });

        btn_xoaLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Delete.png"))); // NOI18N
        btn_xoaLop.setText("Xóa");
        btn_xoaLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaLopActionPerformed(evt);
            }
        });

        btn_clearLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_clearLop.setText("Clear");
        btn_clearLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearLopActionPerformed(evt);
            }
        });

        btnChotDiem.setText("Chốt Điểm");
        btnChotDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChotDiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_themLop)
                        .addGap(18, 18, 18)
                        .addComponent(btn_suaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_xoaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_timKiemLop)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_clearLop, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnChotDiem)))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_timKiem)
                        .addComponent(btn_timKiemLop)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChotDiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_clearLop, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_xoaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_suaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_themLop, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tbl_lop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã lớp", "Tên lớp", "Mã giảng viên", "Tên giảng viên", "Học phí", "Ca học", "Sĩ số", "Loại lớp", "Cấp lớp", "Ngày nhập học", "Ngày kết thúc", "Trạng Thái"
            }
        ));
        tbl_lop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_lopMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_lop);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Tên lớp");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Mã giảng viên");

        txt_maGiangvien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_maGiangvienFocusLost(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Học phí");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Ca học");

        cbc_caHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Sĩ số");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Loại Lớp");

        cbc_loaiLop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbc_loaiLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbc_loaiLopActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Cấp lớp");

        cbc_capLop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Ngày bắt đầu");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("Ngày kết Thúc");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Tên giảng viên");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Trạng Thái");

        rbn_HoatDong.setText("Hoạt động");

        rbn_KetThuc.setText("Đã kết thúc");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(rbn_HoatDong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbn_KetThuc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_loiHoatDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_loiHoatDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbn_HoatDong)
                            .addComponent(rbn_KetThuc))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tenLop, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(lbl_loiTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_maGiangvien, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(lbl_loiMaGiangVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_tenGiangVien, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(txt_hocPhi, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(lbl_loiHocPhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiCaHoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbc_caHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiSiSo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiLoaiLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbc_loaiLop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiCapLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbc_capLop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_siSo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(date_ngayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_ngayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel33)
                            .addComponent(jLabel38)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maGiangvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiMaGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenGiangVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_hocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbc_caHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiCaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_siSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiSiSo, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41)
                .addGap(8, 8, 8)
                .addComponent(cbc_loaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiLoaiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addGap(8, 8, 8)
                .addComponent(cbc_capLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiCapLop, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(270, 270, 270))
        );

        jTabbedPane1.addTab("Lớp", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jLabel1)
                .addContainerGap(575, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(743, 743, 743))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_loaiLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_loaiLopMouseClicked
        int vitri = tbl_loaiLop.getSelectedRow();
        if (vitri >= 0) {
            int row = (int) tbl_loaiLop.getValueAt(vitri, 0);
            Lop l = lopDAO.clickLoaiLop(row, conn);
            if (l != null) {
                txt_tenLoaiLop.setText(l.getTenLoaiLop());
            }
        }
    }//GEN-LAST:event_tbl_loaiLopMouseClicked

    private void btn_timKiemLoaiLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemLoaiLopActionPerformed
        if (checknullLoaiLop()) {
            filltableLL();
            listLop = lopDAO.timKiemLoaiLop(txt_timKiemLoaiLop.getText(), conn);
            dtm.setRowCount(0);
            dtm = (DefaultTableModel) tbl_loaiLop.getModel();
            for (Lop lop : listLop) {
                Vector<Object> vec = new Vector<>();
                vec.add(lop.getMaLoaiLop());
                vec.add(lop.getTenLoaiLop());
                dtm.addRow(vec);
            }
        }
    }//GEN-LAST:event_btn_timKiemLoaiLopActionPerformed

    private void btn_themLoaiLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themLoaiLopActionPerformed
        Lop lop = new Lop();
        lop.setTenLoaiLop(txt_tenLoaiLop.getText());
        boolean them = lopDAO.insertLoaiLop(lop, conn);
        filltableLL();
    }//GEN-LAST:event_btn_themLoaiLopActionPerformed

    private void btn_suaLoaiLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaLoaiLopActionPerformed
        Lop lop = new Lop();
        lop.setTenLoaiLop(txt_tenLoaiLop.getText());
        int maLoaiLop = (int) tbl_loaiLop.getValueAt(tbl_loaiLop.getSelectedRow(), 0);
        lop.setMaLoaiLop(maLoaiLop);
        lopDAO.updateLoaiLop(lop, conn);
        filltableLL();
    }//GEN-LAST:event_btn_suaLoaiLopActionPerformed

    private void btn_xoaLoaiLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaLoaiLopActionPerformed
        int chon = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa chứ?", "Xóa loại Lớp", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            int vitri = tbl_loaiLop.getSelectedRow();
            int row = (int) tbl_loaiLop.getValueAt(vitri, 0);
            Lop lop = lopDAO.xoaLoaiLop(row, conn);
            filltableLL();
            Dialog.alert(null, "xóa thành công");
            btn_clearLoaiLopActionPerformed(evt);
        } else {

        }
    }//GEN-LAST:event_btn_xoaLoaiLopActionPerformed

    private void btn_clearLoaiLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearLoaiLopActionPerformed
        filltableLL();
        txt_tenLoaiLop.setText("");
        txt_timKiemLoaiLop.setBorder(boder1);
        txt_timKiemLoaiLop.setForeground(Color.black);
    }//GEN-LAST:event_btn_clearLoaiLopActionPerformed

    private void tbl_capLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_capLopMouseClicked
        int vitri = tbl_capLop.getSelectedRow();
        if (vitri >= 0) {
            int row = (int) tbl_capLop.getValueAt(vitri, 0);
            Lop l = lopDAO.clickCapLop(row, conn);
            if (l != null) {
                txt_tenCapLop.setText(l.getTenCapLop());
            }
        }
    }//GEN-LAST:event_tbl_capLopMouseClicked

    private void btn_timKiemCapLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemCapLopActionPerformed
        if (checknullCapLop()) {
            filltableCL();
            listLop = lopDAO.timKiemCapLop(txt_timKiemCapLop.getText(), conn);
            dtm.setRowCount(0);
            dtm = (DefaultTableModel) tbl_capLop.getModel();
            for (Lop lop : listLop) {
                Vector<Object> vec = new Vector<>();
                vec.add(lop.getMaCapLop());
                vec.add(lop.getTenCapLop());
                dtm.addRow(vec);
            }
        }
    }//GEN-LAST:event_btn_timKiemCapLopActionPerformed

    private void btn_themCapLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themCapLopActionPerformed
        Lop lop = new Lop();
        lop.setTenCapLop(txt_tenCapLop.getText());
        boolean them = lopDAO.insertCapLop(lop, conn);
        filltableCL();
    }//GEN-LAST:event_btn_themCapLopActionPerformed

    private void btn_suaCapLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaCapLopActionPerformed
        Lop lop = new Lop();
        lop.setTenCapLop(txt_tenCapLop.getText());
        int maCapLop = (int) tbl_capLop.getValueAt(tbl_capLop.getSelectedRow(), 0);
        lop.setMaCapLop(maCapLop);
        lopDAO.updateCapLop(lop, conn);
        filltableCL();
    }//GEN-LAST:event_btn_suaCapLopActionPerformed

    private void btn_xoaCapLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaCapLopActionPerformed
        int chon = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa chứ?", "Xóa cấp Lớp", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            int vitri = tbl_capLop.getSelectedRow();
            int row = (int) tbl_capLop.getValueAt(vitri, 0);
            Lop lop = lopDAO.xoaCapLop(row, conn);
            filltableCL();
            Dialog.alert(null, "xóa thành công");
            btn_clearCapLopActionPerformed(evt);
        } else {

        }
    }//GEN-LAST:event_btn_xoaCapLopActionPerformed

    private void btn_clearCapLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearCapLopActionPerformed
        filltableCL();
        txt_tenCapLop.setText("");
        txt_timKiemCapLop.setBorder(boder1);
        txt_timKiemCapLop.setForeground(Color.black);
    }//GEN-LAST:event_btn_clearCapLopActionPerformed

    private void btn_timKiemLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemLopActionPerformed
        if (checknullTKLop()) {
            fillTable();
            listLop = lopDAO.timKiemLop(txt_timKiem.getText(), conn);
            dtm.setRowCount(0);
            dtm = (DefaultTableModel) tbl_lop.getModel();
            for (Lop lop : listLop) {
                Vector<Object> vec = new Vector<>();
                vec.add(lop.getMaLop());
                vec.add(lop.getTenLop());
                vec.add(lop.getMaNhanVien());
                vec.add(lop.getTenNhanVien());
                vec.add(lop.getHocPhi());
                vec.add(lop.getCaHoc());
                vec.add(lop.getSiso());
                vec.add(lop.getTenLoaiLop());
                vec.add(lop.getTenCapLop());
                vec.add(lop.getNgayNhapHoc());
                vec.add(lop.getNgayKeThuc());
                dtm.addRow(vec);
                System.out.println(lop.getTenLop());
            }
        }
    }//GEN-LAST:event_btn_timKiemLopActionPerformed

    private void btn_themLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themLopActionPerformed
        if (checknull()) {
            Lop lop = new Lop();
            lop.setTenLop(txt_tenLop.getText());
            lop.setMaNhanVien(Integer.parseInt(txt_maGiangvien.getText()));
            lop.setHocPhi(Float.parseFloat(txt_hocPhi.getText()));
            lop.setCaHoc(String.valueOf(cbc_caHoc.getSelectedItem()));
            lop.setSiso(Integer.parseInt(txt_siSo.getText()));
//            String caHoc = String.valueOf(cbc_caHoc.getSelectedIndex());
//            lop.setCaHoc(caHoc);
            lop.setMaLoaiLop(cbc_loaiLop.getSelectedIndex());
            lop.setMaCapLop(cbc_capLop.getSelectedIndex());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdf.format(date_ngayBatDau.getDate());
            String date1 = sdf.format(date_ngayKetThuc.getDate());
            lop.setNgayNhapHoc(date);
            lop.setNgayKeThuc(date1);
            boolean trangThai;
            if (rbn_HoatDong.isSelected()) {
                trangThai = true;
                lop.setTrangThai(trangThai);
            }
            if (rbn_KetThuc.isSelected()) {
                trangThai = false;
                lop.setTrangThai(trangThai);
            }
            boolean them = lopDAO.insertLop(lop, conn);

            fillTable();
            Dialog.alert(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btn_themLopActionPerformed

    private void btn_suaLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaLopActionPerformed
        Lop lop = new Lop();
        lop.setTenLop(txt_tenLop.getText());
        lop.setMaNhanVien(Integer.parseInt(txt_maGiangvien.getText()));
        lop.setHocPhi(Float.parseFloat(txt_hocPhi.getText()));
        lop.setCaHoc(String.valueOf(cbc_caHoc.getSelectedItem()));
        lop.setSiso(Integer.parseInt(txt_siSo.getText()));
//        String caHoc = String.valueOf(cbc_caHoc.getSelectedItem());
//        lop.setCaHoc(caHoc);
        lop.setMaLoaiLop(cbc_loaiLop.getSelectedIndex());
            lop.setMaCapLop(cbc_capLop.getSelectedIndex());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(date_ngayBatDau.getDate());
        String date1 = sdf.format(date_ngayKetThuc.getDate());
        lop.setNgayNhapHoc(date);
        lop.setNgayKeThuc(date1);
        boolean trangThai;
        if (rbn_HoatDong.isSelected()) {
            trangThai = true;
            lop.setTrangThai(trangThai);
        }
        if (rbn_KetThuc.isSelected()) {
            trangThai = false;
            lop.setTrangThai(trangThai);
        }
        int vitri = tbl_lop.getSelectedRow();
        int malop = (int) tbl_lop.getValueAt(vitri, 0);
        lop.setMaLop(malop);
        lopDAO.updateLop(lop, conn);
        fillTable();
        Dialog.alert(null, "Sửa thành công");
    }//GEN-LAST:event_btn_suaLopActionPerformed

    private void btn_xoaLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaLopActionPerformed
        int chon = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa chứ?", "Xóa Lớp", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            int vitri = tbl_lop.getSelectedRow();
            int row = (int) tbl_lop.getValueAt(vitri, 0);
            Lop lp = lopDAO.xoaLop(row, conn);
            fillTable();
            Dialog.alert(null, "xóa thành công");
            btn_clearLopActionPerformed(evt);
        } else {
        }
    }//GEN-LAST:event_btn_xoaLopActionPerformed

    private void btn_clearLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearLopActionPerformed
        fillTable();
        txt_timKiem.setText("");
        txt_tenLop.setText("");
        txt_maGiangvien.setText("");
        txt_tenGiangVien.setText("");
        txt_hocPhi.setText("");
        cbc_caHoc.setSelectedIndex(0);
        txt_siSo.setText("");
        cbc_loaiLop.setSelectedIndex(0);
        cbc_capLop.setSelectedIndex(0);
        ((JTextField) date_ngayBatDau.getDateEditor().getUiComponent()).setText("");
        ((JTextField) date_ngayKetThuc.getDateEditor().getUiComponent()).setText("");
        txt_tenLop.setBorder(boder1);
        lbl_loiTenLop.setText("");
        lbl_loiMaGiangVien.setText("");
        txt_maGiangvien.setBorder(boder1);
        lbl_loiHocPhi.setText("");
        txt_hocPhi.setBorder(boder1);
        lbl_loiCaHoc.setText("");
        cbc_caHoc.setBorder(boder1);
        lbl_loiSiSo.setText("");
        txt_siSo.setBorder(boder1);
        lbl_loiNgayBatDau.setText("");
        date_ngayBatDau.setBorder(boder1);
        lbl_loiNgayKetThuc.setText("");
        date_ngayKetThuc.setBorder(boder1);
        txt_timKiem.setBorder(boder1);
        txt_timKiem.setForeground(Color.black);
    }//GEN-LAST:event_btn_clearLopActionPerformed

    private void tbl_lopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_lopMouseClicked
        int vitri = tbl_lop.getSelectedRow();
        if (vitri > -1) {
            int row = (int) tbl_lop.getValueAt(vitri, 0);
            Lop l = lopDAO.clickTable(row, conn);
            if (l != null) {
                txt_tenLop.setText(l.getTenLop());
                txt_maGiangvien.setText(String.valueOf(l.getMaNhanVien()));
                txt_tenGiangVien.setText(l.getTenNhanVien());
                txt_hocPhi.setText(String.valueOf(l.getHocPhi()));
                cbc_caHoc.setSelectedItem(l.getCaHoc());
                txt_siSo.setText(String.valueOf(l.getSiso()));
                cbc_loaiLop.setSelectedItem(l.getTenLoaiLop());
                cbc_capLop.setSelectedItem(l.getTenCapLop());
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tbl_lop.getValueAt(vitri, 9));
                    date_ngayBatDau.setDate(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tbl_lop.getValueAt(vitri, 10));
                    date_ngayKetThuc.setDate(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                boolean trangThai;

                if (l.isTrangThai() == true) {
                    rbn_HoatDong.setSelected(true);
                }
                if (l.isTrangThai() == false) {
                    rbn_KetThuc.setSelected(true);
                }
            }
        }
    }//GEN-LAST:event_tbl_lopMouseClicked

    private void cbc_loaiLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbc_loaiLopActionPerformed
        if (cbc_loaiLop.getSelectedIndex() == 0) {
            lbl_loiLoaiLop.setText("Bạn không được chọn phần đầu tiên");
            lbl_loiLoaiLop.setForeground(Color.red);
            cbc_loaiLop.setBorder(boder);
            cbc_loaiLop.requestFocus();
            cbc_capLop.setEnabled(false);
            return;
        } else {
            cbc_capLop.setEnabled(true);
            lbl_loiLoaiLop.setText("");
            cbc_loaiLop.setBorder(boder1);

        }
    }//GEN-LAST:event_cbc_loaiLopActionPerformed

    private void txt_maGiangvienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_maGiangvienFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maGiangvienFocusLost

    private void btnChotDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChotDiemActionPerformed
            
                      
            
                try {
                    CallableStatement call = conn.prepareCall("{call diem_thanh_phan(?)}");
                    call.setInt(1, (int) tbl_lop.getValueAt(tbl_lop.getSelectedRow(), 0));
                    int kq = call.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
        
    }//GEN-LAST:event_btnChotDiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChotDiem;
    private javax.swing.JButton btn_clearCapLop;
    private javax.swing.JButton btn_clearLoaiLop;
    private javax.swing.JButton btn_clearLop;
    private javax.swing.JButton btn_suaCapLop;
    private javax.swing.JButton btn_suaLoaiLop;
    private javax.swing.JButton btn_suaLop;
    private javax.swing.JButton btn_themCapLop;
    private javax.swing.JButton btn_themLoaiLop;
    private javax.swing.JButton btn_themLop;
    private javax.swing.JButton btn_timKiemCapLop;
    private javax.swing.JButton btn_timKiemLoaiLop;
    private javax.swing.JButton btn_timKiemLop;
    private javax.swing.JButton btn_xoaCapLop;
    private javax.swing.JButton btn_xoaLoaiLop;
    private javax.swing.JButton btn_xoaLop;
    private javax.swing.JComboBox<String> cbc_caHoc;
    private javax.swing.JComboBox<String> cbc_capLop;
    private javax.swing.JComboBox<String> cbc_loaiLop;
    private com.toedter.calendar.JDateChooser date_ngayBatDau;
    private com.toedter.calendar.JDateChooser date_ngayKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_LoiTenLoaiLop2;
    private javax.swing.JLabel lbl_loiCaHoc;
    private javax.swing.JLabel lbl_loiCapLop;
    private javax.swing.JLabel lbl_loiHoatDong;
    private javax.swing.JLabel lbl_loiHocPhi;
    private javax.swing.JLabel lbl_loiLoaiLop;
    private javax.swing.JLabel lbl_loiMaGiangVien;
    private javax.swing.JLabel lbl_loiNgayBatDau;
    private javax.swing.JLabel lbl_loiNgayKetThuc;
    private javax.swing.JLabel lbl_loiSiSo;
    private javax.swing.JLabel lbl_loiTenCapLop;
    private javax.swing.JLabel lbl_loiTenLop;
    private javax.swing.JRadioButton rbn_HoatDong;
    private javax.swing.JRadioButton rbn_KetThuc;
    private javax.swing.JTable tbl_capLop;
    private javax.swing.JTable tbl_loaiLop;
    private javax.swing.JTable tbl_lop;
    private javax.swing.JTextField txt_hocPhi;
    private javax.swing.JTextField txt_maGiangvien;
    private javax.swing.JTextField txt_siSo;
    private javax.swing.JTextField txt_tenCapLop;
    private javax.swing.JTextField txt_tenGiangVien;
    private javax.swing.JTextField txt_tenLoaiLop;
    private javax.swing.JTextField txt_tenLop;
    private javax.swing.JTextField txt_timKiem;
    private javax.swing.JTextField txt_timKiemCapLop;
    private javax.swing.JTextField txt_timKiemLoaiLop;
    // End of variables declaration//GEN-END:variables
}
