/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.BuoiHocDAO;
import DAO.DiemDanhDAO;
import Entity.BuoiHoc;
import Entity.DiemDanh;
import TienIchHoTro.Dialog;
import java.awt.Color;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author congc
 */
public class QLBuoiHoc_DiemDanh extends javax.swing.JInternalFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    Connection conn = null;
    Border boder = BorderFactory.createLineBorder(Color.red);
    Border boder1 = BorderFactory.createLineBorder(Color.black);
    BuoiHocDAO bhDAO = new BuoiHocDAO();
    DiemDanhDAO ddDAO = new DiemDanhDAO();
    List<BuoiHoc> listBH = new ArrayList<>();
    List<DiemDanh> listDD = new ArrayList<>();

    public QLBuoiHoc_DiemDanh() {
        initComponents();
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        fillBH();
        fillDD();
        initCBC();
        ButtonGroup grp = new ButtonGroup();
        grp.add(rbn_Co);
        grp.add(rbn_vang);

    }

    void fillBH() {
        dtm = (DefaultTableModel) tbl_buoiHoc.getModel();
        dtm.setRowCount(0);
        listBH = bhDAO.listBuoiHoc(conn);
        for (BuoiHoc bh : listBH) {
            Object obj[] = new Object[]{
                bh.getMaBuoiHoc(), bh.getMaLopHoc(), bh.getTenLopHoc(), bh.getNgayHoc(), bh.getCaHoc(), bh.getGhiChu()
            };
            dtm.addRow(obj);
        }
    }

    void fillDD() {
        dtm = (DefaultTableModel) tbl_diemDanh.getModel();
        dtm.setRowCount(0);
        listDD = ddDAO.listDD(conn);
        for (DiemDanh dd : listDD) {
            Object[] obj = new Object[]{
                dd.getMaDiemDanh(), dd.isTrangThai() == true ? "Có mặt" : "Vắng",
                 dd.getNgayHoc(), dd.getCaHoc(),
                dd.getGhiChu(), dd.getMaBuoiHoc(), dd.getMaBienLai()
            };
            dtm.addRow(obj);
        }
    }

    void initCBC() {
        this.cbc_caHoc.removeAllItems();
        String[] caHoc = new String[]{
            "Ca học", "Ca 1 (7h-9h) 246", "Ca 2 (9h-11h) 246", "Ca 3 (12h-14h) 246", "Ca 4 (14h-16h) 246"
                , "Ca 1 (7h-9h) 357", "Ca 2 (9h-11h) 357", "Ca 3 (12h-14h) 357", "Ca 4 (14h-16h) 357"
        };
        for (int i = 0; i < caHoc.length; i++) {
            cbc_caHoc.addItem(caHoc[i]);
        }
    }

    boolean checknullBH() {
        String date = ((JTextField) date_ngayHoc.getDateEditor().getUiComponent()).getText();
        if (date.equals("")) {
            lbl_loiNgayHoc.setText("Bạn chưa chọn ngày học!");
            date_ngayHoc.setBorder(boder);
            lbl_loiNgayHoc.setForeground(Color.red);
            date_ngayHoc.requestFocus();
            return false;
        } else {
            lbl_loiNgayHoc.setText("");
            date_ngayHoc.setBorder(boder1);
        }

        if (cbc_caHoc.getSelectedIndex() == 0) {
            lbl_loiCaHoc.setText("Bạn chưa chọn ca học");
            cbc_caHoc.setBorder(boder);
            lbl_loiCaHoc.setForeground(Color.red);
            cbc_caHoc.requestFocus();
            return false;
        } else {
            lbl_loiCaHoc.setText("");
            cbc_caHoc.setBorder(boder1);
        }

        if (txt_maLopHoc.getText().equals("")) {
            lbl_loiMaLopHoc.setText("Bạn chưa nhập mã lớp học!");
            txt_maLopHoc.setBorder(boder);
            lbl_loiMaLopHoc.setForeground(Color.red);
            txt_maLopHoc.requestFocus();
            return false;
        } else {
            lbl_loiMaLopHoc.setText("");
            txt_maLopHoc.setBorder(boder1);
        }
        return true;
    }

    boolean checkTKBH() {
        String date = ((JTextField) date_timKiemBH.getDateEditor().getUiComponent()).getText();
        if (date.equals("")) {
            lbl_loiTimKiemBuoiHoc.setText("Bạn chưa chọn ngày học!");
            date_timKiemBH.setBorder(boder);
            lbl_loiTimKiemBuoiHoc.setForeground(Color.red);
            date_timKiemBH.requestFocus();
            return false;
        } else {
            lbl_loiTimKiemBuoiHoc.setText("");
            date_timKiemBH.setBorder(boder1);
        }
        return true;
    }

    boolean checknullDD() {
        if (rbn_Co.isSelected() == false && rbn_vang.isSelected() == false) {
            lbl_loiTrangThai.setText("Bạn chưa chọn trạng thái của sinh viên đó");
            pan_TrangThai.setBorder(boder);
            lbl_loiTrangThai.setForeground(Color.red);
            return false;
        } else {
            lbl_loiTrangThai.setText("");
            pan_TrangThai.setBorder(boder1);
        }

        if (txt_maBienLai.getText().equals("")) {
            lbl_loiMaBienLai.setText("Bạn chưa nhập mã biên lai của học viên đó!");
            txt_maBienLai.setBorder(boder);
            lbl_loiMaBienLai.setForeground(Color.red);
            txt_maBienLai.requestFocus();
            return false;
        } else {
            lbl_loiMaBienLai.setText("");
            txt_maBienLai.setBorder(boder1);
        }

        if (txt_maBuoiHoc.getText().equals("")) {
            lbl_loiMaBuoiHoc.setText("Bạn chưa nhập mã buổi học!");
            txt_maBuoiHoc.setBorder(boder);
            lbl_loiMaBuoiHoc.setForeground(Color.red);
            txt_maBuoiHoc.requestFocus();
            return false;
        } else {
            lbl_loiMaBuoiHoc.setText("");
            txt_maBuoiHoc.setBorder(boder1);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        date_ngayHoc = new com.toedter.calendar.JDateChooser();
        lbl_loiNgayHoc = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_loiCaHoc = new javax.swing.JLabel();
        txt_maLopHoc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lbl_loiMaLopHoc = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_GhiChu = new javax.swing.JTextArea();
        cbc_caHoc = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_buoiHoc = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btn_themBuoiHoc = new javax.swing.JButton();
        btn_SuaBuoiHoc = new javax.swing.JButton();
        btn_clearBuoiHoc = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        date_timKiemBH = new com.toedter.calendar.JDateChooser();
        btn_timKiemBuoiHoc = new javax.swing.JButton();
        lbl_loiTimKiemBuoiHoc = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbl_loiTrangThai = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_maBienLai = new javax.swing.JTextField();
        lbl_loiMaBienLai = new javax.swing.JLabel();
        txt_maBuoiHoc = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lbl_loiMaBuoiHoc = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_GhiChuDiemDanh = new javax.swing.JTextArea();
        pan_TrangThai = new javax.swing.JPanel();
        rbn_vang = new javax.swing.JRadioButton();
        rbn_Co = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_diemDanh = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btn_themDiemDanh = new javax.swing.JButton();
        btn_SuaBuoiHoc1 = new javax.swing.JButton();
        btn_clearBuoiHoc1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btn_timKiemBuoiHoc1 = new javax.swing.JButton();
        lbl_loiTimKiemDiemDanh = new javax.swing.JLabel();
        date_timKiemDD = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Quản lý buổi học điểm danh");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Ngày học");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Ca học");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Ghi chú");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Mã lớp học");

        txt_GhiChu.setColumns(20);
        txt_GhiChu.setRows(5);
        jScrollPane1.setViewportView(txt_GhiChu);

        cbc_caHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_ngayHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiNgayHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiCaHoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                    .addComponent(txt_maLopHoc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_loiMaLopHoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbc_caHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_ngayHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiNgayHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(cbc_caHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiCaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(lbl_loiMaLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );

        tbl_buoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã buổi học", "Mã lớp học", "Tên lớp học", "Ngày học", "Ca học", "Ghi chú"
            }
        ));
        tbl_buoiHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_buoiHocMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_buoiHoc);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_themBuoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Create.png"))); // NOI18N
        btn_themBuoiHoc.setText("Thêm");
        btn_themBuoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themBuoiHocActionPerformed(evt);
            }
        });

        btn_SuaBuoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_SuaBuoiHoc.setText("Sửa");
        btn_SuaBuoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaBuoiHocActionPerformed(evt);
            }
        });

        btn_clearBuoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_clearBuoiHoc.setText("Clear");
        btn_clearBuoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearBuoiHocActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Tìm kiếm");

        btn_timKiemBuoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_timKiemBuoiHoc.setText("Tìm kiếm");
        btn_timKiemBuoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemBuoiHocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_loiTimKiemBuoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_themBuoiHoc)
                        .addGap(18, 18, 18)
                        .addComponent(btn_SuaBuoiHoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_clearBuoiHoc))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_timKiemBH, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_timKiemBuoiHoc)))
                .addContainerGap(369, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_timKiemBuoiHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_timKiemBH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTimKiemBuoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_themBuoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_SuaBuoiHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_clearBuoiHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Buổi học", jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Trạng thái");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Ghi chú");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Mã biên lai");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Mã buổi học");

        txt_GhiChuDiemDanh.setColumns(20);
        txt_GhiChuDiemDanh.setRows(5);
        jScrollPane3.setViewportView(txt_GhiChuDiemDanh);

        rbn_vang.setText("Vắng");

        rbn_Co.setText("Có");

        javax.swing.GroupLayout pan_TrangThaiLayout = new javax.swing.GroupLayout(pan_TrangThai);
        pan_TrangThai.setLayout(pan_TrangThaiLayout);
        pan_TrangThaiLayout.setHorizontalGroup(
            pan_TrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_TrangThaiLayout.createSequentialGroup()
                .addComponent(rbn_vang)
                .addGap(18, 18, 18)
                .addComponent(rbn_Co)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pan_TrangThaiLayout.setVerticalGroup(
            pan_TrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_TrangThaiLayout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addGroup(pan_TrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbn_vang)
                    .addComponent(rbn_Co)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_loiMaBuoiHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addComponent(pan_TrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_maBuoiHoc)
                    .addComponent(txt_maBienLai, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_loiMaBienLai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pan_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lbl_loiTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maBuoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_loiMaBuoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_maBienLai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiMaBienLai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        tbl_diemDanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã điểm danh", "Trạng thái", "Ngày học", "Ca học", "Ghi chú", "Mã buổi học", "Mã biên lai"
            }
        ));
        tbl_diemDanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_diemDanhMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_diemDanh);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_themDiemDanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Create.png"))); // NOI18N
        btn_themDiemDanh.setText("Thêm");
        btn_themDiemDanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themDiemDanhActionPerformed(evt);
            }
        });

        btn_SuaBuoiHoc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_SuaBuoiHoc1.setText("Sửa");
        btn_SuaBuoiHoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaBuoiHoc1ActionPerformed(evt);
            }
        });

        btn_clearBuoiHoc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_clearBuoiHoc1.setText("Clear");
        btn_clearBuoiHoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearBuoiHoc1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Tìm kiếm");

        btn_timKiemBuoiHoc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_timKiemBuoiHoc1.setText("Tìm kiếm");
        btn_timKiemBuoiHoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemBuoiHoc1ActionPerformed(evt);
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
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_timKiemDD, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_timKiemBuoiHoc1))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_themDiemDanh)
                        .addGap(18, 18, 18)
                        .addComponent(btn_SuaBuoiHoc1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_clearBuoiHoc1))
                    .addComponent(lbl_loiTimKiemDiemDanh, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(date_timKiemDD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_timKiemBuoiHoc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(lbl_loiTimKiemDiemDanh, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_themDiemDanh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_SuaBuoiHoc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_clearBuoiHoc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Điểm danh", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
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

    private void tbl_buoiHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_buoiHocMouseClicked
        int vitri = tbl_buoiHoc.getSelectedRow();
        if (vitri > -1) {
            int row = (int) tbl_buoiHoc.getValueAt(vitri, 0);
            BuoiHoc bh = bhDAO.clickTableBH(row, conn);
            if (bh != null) {
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tbl_buoiHoc.getValueAt(vitri, 3));
                    date_ngayHoc.setDate(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                cbc_caHoc.setSelectedItem(bh.getCaHoc());
                txt_GhiChu.setText(bh.getGhiChu());
                txt_maLopHoc.setText(String.valueOf(bh.getMaLopHoc()));

            }
        }
    }//GEN-LAST:event_tbl_buoiHocMouseClicked

    private void btn_themBuoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themBuoiHocActionPerformed
        if (checknullBH()) {
            BuoiHoc bh = new BuoiHoc();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdf.format(date_ngayHoc.getDate());
            bh.setNgayHoc(date);
            bh.setCaHoc(String.valueOf(cbc_caHoc.getSelectedItem()));
            bh.setGhiChu(txt_GhiChu.getText());
            bh.setMaLopHoc(Integer.parseInt(txt_maLopHoc.getText()));
            boolean them = bhDAO.themBH(bh, conn);
            fillBH();
            Dialog.alert(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btn_themBuoiHocActionPerformed

    private void btn_SuaBuoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaBuoiHocActionPerformed
        if (checknullBH()) {
            BuoiHoc bh = new BuoiHoc();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdf.format(date_ngayHoc.getDate());
            bh.setNgayHoc(date);
            bh.setCaHoc(String.valueOf(cbc_caHoc.getSelectedItem()));
            bh.setGhiChu(txt_GhiChu.getText());
            bh.setMaLopHoc(Integer.parseInt(txt_maLopHoc.getText()));
            int row = (int) tbl_buoiHoc.getValueAt(tbl_buoiHoc.getSelectedRow(), 0);
            bh.setMaBuoiHoc(row);
            bhDAO.suaBH(bh, conn);
            fillBH();
            Dialog.alert(null, "Sửa thành công thành công");
        }
    }//GEN-LAST:event_btn_SuaBuoiHocActionPerformed

    private void btn_clearBuoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearBuoiHocActionPerformed
        txt_GhiChu.setText("");
        cbc_caHoc.setSelectedIndex(0);
        txt_maLopHoc.setText("");
        ((JTextField) date_ngayHoc.getDateEditor().getUiComponent()).setText("");
        fillBH();
        ((JTextField) date_timKiemBH.getDateEditor().getUiComponent()).setText("");
        lbl_loiTimKiemBuoiHoc.setText("");
        date_timKiemBH.setBorder(boder1);
        lbl_loiMaLopHoc.setText("");
        txt_maLopHoc.setBorder(boder1);
        lbl_loiCaHoc.setText("");
        cbc_caHoc.setBorder(boder1);
        lbl_loiNgayHoc.setText("");
        date_ngayHoc.setBorder(boder1);
    }//GEN-LAST:event_btn_clearBuoiHocActionPerformed

    private void btn_timKiemBuoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemBuoiHocActionPerformed
        if (checkTKBH()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(date_timKiemBH.getDate());
            fillBH();
            listBH = bhDAO.timKiemBuoiHoc(date, conn);
            dtm.setRowCount(0);
            dtm = (DefaultTableModel) tbl_buoiHoc.getModel();
            for (BuoiHoc bh : listBH) {
                Vector<Object> vec = new Vector<>();
                vec.add(bh.getMaBuoiHoc());
                vec.add(bh.getMaLopHoc());
                vec.add(bh.getTenLopHoc());
                vec.add(bh.getNgayHoc());
                vec.add(bh.getCaHoc());
                vec.add(bh.getGhiChu());
                dtm.addRow(vec);
            }
        }
    }//GEN-LAST:event_btn_timKiemBuoiHocActionPerformed

    private void tbl_diemDanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_diemDanhMouseClicked
        int vitri = tbl_diemDanh.getSelectedRow();
        if (vitri > -1) {
            int row = (int) tbl_diemDanh.getValueAt(vitri, 0);
            DiemDanh dd = ddDAO.clickTable(row, conn);
            if (dd != null) {

                if (dd.isTrangThai() == true) {
                    rbn_Co.setSelected(true);
                }
                if (dd.isTrangThai() == false) {
                    rbn_vang.setSelected(true);
                }
                txt_GhiChuDiemDanh.setText(dd.getGhiChu());
                txt_maBuoiHoc.setText(String.valueOf(dd.getMaBuoiHoc()));
                txt_maBienLai.setText(String.valueOf(dd.getMaBienLai()));
            }
        }
    }//GEN-LAST:event_tbl_diemDanhMouseClicked

    private void btn_themDiemDanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themDiemDanhActionPerformed
        if (checknullDD()) {
            DiemDanh dd = new DiemDanh();
            boolean trangThai;
            if (rbn_Co.isSelected()) {
                trangThai = true;
                dd.setTrangThai(trangThai);
            }
            if (rbn_vang.isSelected()) {
                trangThai = false;
                dd.setTrangThai(trangThai);
            }
            dd.setGhiChu(txt_GhiChuDiemDanh.getText());
            dd.setMaBuoiHoc(Integer.parseInt(txt_maBuoiHoc.getText()));
            dd.setMaBienLai(Integer.parseInt(txt_maBienLai.getText()));
            boolean them = ddDAO.themDiemDanh(dd, conn);
            fillDD();
            Dialog.alert(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btn_themDiemDanhActionPerformed

    private void btn_SuaBuoiHoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaBuoiHoc1ActionPerformed
        if (checknullDD()) {
            DiemDanh dd = new DiemDanh();
            boolean trangThai;
            if (rbn_Co.isSelected()) {
                trangThai = true;
                dd.setTrangThai(trangThai);
            }
            if (rbn_vang.isSelected()) {
                trangThai = false;
                dd.setTrangThai(trangThai);
            }
            dd.setGhiChu(txt_GhiChuDiemDanh.getText());
            dd.setMaBuoiHoc(Integer.parseInt(txt_maBuoiHoc.getText()));
            dd.setMaBienLai(Integer.parseInt(txt_maBienLai.getText()));
            int row = (int) tbl_diemDanh.getValueAt(tbl_diemDanh.getSelectedRow(), 0);
            dd.setMaDiemDanh(row);
            ddDAO.suaDiemDanh(dd, conn);
            fillDD();
            Dialog.alert(null, "Sửa thành công");
        }
    }//GEN-LAST:event_btn_SuaBuoiHoc1ActionPerformed

    private void btn_timKiemBuoiHoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemBuoiHoc1ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(date_timKiemDD.getDate());
        fillDD();
        listDD = ddDAO.timKiemDD(date, conn);
        dtm.setRowCount(0);
        dtm = (DefaultTableModel) tbl_diemDanh.getModel();
        for (DiemDanh dd : listDD) {
            Vector<Object> vec = new Vector<>();
            vec.add(dd.getMaDiemDanh());
            vec.add(dd.isTrangThai() == true ? "Có mặt" : "Vắng");
            vec.add(dd.getNgayHoc());
            vec.add(dd.getCaHoc());
            vec.add(dd.getGhiChu());
            vec.add(dd.getMaBuoiHoc());
            vec.add(dd.getMaBienLai());
            dtm.addRow(vec);
        }
    }//GEN-LAST:event_btn_timKiemBuoiHoc1ActionPerformed

    private void btn_clearBuoiHoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearBuoiHoc1ActionPerformed
        txt_GhiChuDiemDanh.setText("");
        txt_maBuoiHoc.setText("");
        txt_maBienLai.setText("");
        fillDD();
        lbl_loiTrangThai.setText("");
        pan_TrangThai.setBorder(boder1);
        lbl_loiMaBienLai.setText("");
        txt_maBienLai.setBorder(boder1);
        lbl_loiMaBuoiHoc.setText("");
        txt_maBuoiHoc.setBorder(boder1);
        rbn_Co.setSelected(false);
        rbn_vang.setSelected(false);
    }//GEN-LAST:event_btn_clearBuoiHoc1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_SuaBuoiHoc;
    private javax.swing.JButton btn_SuaBuoiHoc1;
    private javax.swing.JButton btn_clearBuoiHoc;
    private javax.swing.JButton btn_clearBuoiHoc1;
    private javax.swing.JButton btn_themBuoiHoc;
    private javax.swing.JButton btn_themDiemDanh;
    private javax.swing.JButton btn_timKiemBuoiHoc;
    private javax.swing.JButton btn_timKiemBuoiHoc1;
    private javax.swing.JComboBox<String> cbc_caHoc;
    private com.toedter.calendar.JDateChooser date_ngayHoc;
    private com.toedter.calendar.JDateChooser date_timKiemBH;
    private com.toedter.calendar.JDateChooser date_timKiemDD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_loiCaHoc;
    private javax.swing.JLabel lbl_loiMaBienLai;
    private javax.swing.JLabel lbl_loiMaBuoiHoc;
    private javax.swing.JLabel lbl_loiMaLopHoc;
    private javax.swing.JLabel lbl_loiNgayHoc;
    private javax.swing.JLabel lbl_loiTimKiemBuoiHoc;
    private javax.swing.JLabel lbl_loiTimKiemDiemDanh;
    private javax.swing.JLabel lbl_loiTrangThai;
    private javax.swing.JPanel pan_TrangThai;
    private javax.swing.JRadioButton rbn_Co;
    private javax.swing.JRadioButton rbn_vang;
    private javax.swing.JTable tbl_buoiHoc;
    private javax.swing.JTable tbl_diemDanh;
    private javax.swing.JTextArea txt_GhiChu;
    private javax.swing.JTextArea txt_GhiChuDiemDanh;
    private javax.swing.JTextField txt_maBienLai;
    private javax.swing.JTextField txt_maBuoiHoc;
    private javax.swing.JTextField txt_maLopHoc;
    // End of variables declaration//GEN-END:variables
}
