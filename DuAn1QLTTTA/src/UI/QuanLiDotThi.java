/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.DotThiDAO;
import Entity.DotThi;
import Entity.Lop;
import TienIchHoTro.Dialog;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author congc
 */
public class QuanLiDotThi extends javax.swing.JInternalFrame {

    List<DotThi> listDAO = new ArrayList<>();
    DotThiDAO dtDAO = new DotThiDAO();
    private DefaultTableModel dtm = new DefaultTableModel();
    private Connection conn = null;
    Border border = BorderFactory.createLineBorder(Color.red);
    Border border1 = BorderFactory.createLineBorder(Color.black);

    public QuanLiDotThi() {
        initComponents();
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        fillTable();
        initCBC();
        //txt_tenLop.setEnabled(false);
        
    }

    void fillTable() {
        dtm = (DefaultTableModel) tbl_dotThi.getModel();
        dtm.setRowCount(0);
        listDAO = dtDAO.selectAll(conn);
        for (DotThi dt : listDAO) {
            Object[] obj = new Object[]{
                dt.getMaDotThi(), dt.getMaLop(), dt.getTenLop(), dt.getNgayThi(), dt.getCaThi(), dt.getSiso(), dt.getVang()
            };
            dtm.addRow(obj);
        }
    }
    

    void initCBC() {
        this.cbc_caThi.removeAllItems();
        String[] caThi = new String[]{
            "Ca thi", "Ca 1", "Ca 2", "Ca 3", "Ca 4", "Ca 5", "Ca 6"
        };
        for (int i = 0; i < caThi.length; i++) {
            cbc_caThi.addItem(caThi[i]);
        }
    }

    boolean checknull() {
        if (txt_maLop.getText().equals("")) {
            lbl_loiMaLop.setText("Bạn chưa nhập mã lớp!");
            txt_maLop.setBorder(border);
            lbl_loiMaLop.setForeground(Color.red);
            txt_maLop.requestFocus();
            return false;
        } else {
            lbl_loiMaLop.setText("");
            txt_maLop.setBorder(border1);
        }

        if (txt_tenLop.getText().equals("")) {
            lbl_loiTenLop.setText("Bạn chưa nhập tên lớp");
            txt_tenLop.setBorder(border);
            lbl_loiTenLop.setForeground(Color.red);
            txt_tenLop.requestFocus();
            return false;
        } else {
            lbl_loiTenLop.setText("");
            txt_tenLop.setBorder(border1);
        }

        if (((JTextField) date_ngayThi.getDateEditor().getUiComponent()).getText().equals("")) {
            lbl_loiNgayThi.setText("Bạn chưa chọn ngày thi");
            date_ngayThi.setBorder(border);
            lbl_loiNgayThi.setForeground(Color.red);
            date_ngayThi.requestFocus();
            return false;
        } else {
            lbl_loiNgayThi.setText("");
            date_ngayThi.setBorder(border1);
        }

        if (cbc_caThi.getSelectedIndex() == 0) {
            lbl_loiCaThi.setText("Bạn không được chọn mục đầu tiên");
            cbc_caThi.setBorder(border);
            lbl_loiCaThi.setForeground(Color.red);
            cbc_caThi.requestFocus();
        } else {
            lbl_loiCaThi.setText("");
            cbc_caThi.setBorder(border1);
        }

        if (txt_siSo.getText().equals("")) {
            lbl_loiSiSo.setText("Bạn chưa nhập sĩ số lớp thi!");
            txt_siSo.setBorder(border);
            lbl_loiSiSo.setForeground(Color.red);
            txt_siSo.requestFocus();
            return false;
        } else {
            Pattern p = Pattern.compile("^\\d+$");
            if (!p.matcher(txt_siSo.getText()).find()) {
                lbl_loiSiSo.setText("Bạn chỉ được nhập số ở trong này");
                txt_siSo.setBorder(border);
                lbl_loiSiSo.setForeground(Color.red);
                txt_siSo.requestFocus();
                return false;
            } else {
                lbl_loiSiSo.setText("");
                txt_siSo.setBorder(border1);
            }
        }

        if (txt_vang.getText().equals("")) {
            lbl_loiVang.setText("Bạn chưa nhập số học viên vắng của lớp thi đó!");
            txt_vang.setBorder(border);
            lbl_loiVang.setForeground(Color.red);
            txt_vang.requestFocus();
            return false;
        } else {
            Pattern p = Pattern.compile("^\\d+$");
            if (!p.matcher(txt_vang.getText()).find()) {
                lbl_loiVang.setText("Bạn chỉ được nhập số ở trong này !");
                txt_vang.setBorder(border);
                lbl_loiVang.setForeground(Color.red);
                txt_vang.requestFocus();
                return false;
            } else {
                lbl_loiVang.setText("");
                txt_vang.setBorder(border1);
            }
        }

        return true;
    }

    boolean checkTimKiem() {
        String timKiem = ((JTextField) date_timKiem.getDateEditor().getUiComponent()).getText();
        if (timKiem.equals("")) {
            lbl_loiTimKiem.setText("Bạn chưa chọn ngày thi cần tìm!");
            date_timKiem.setBorder(border);
            lbl_loiTimKiem.setForeground(Color.red);
            date_timKiem.requestFocus();
            return false;
        } else {
            lbl_loiTimKiem.setText("");
            date_timKiem.setBorder(border1);
        }
        return true;
    }

//    boolean chekTrungTK() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        String date = sdf.format(date_timKiem.getDate());
//        String timKiem = ((JTextField) date_timKiem.getDateEditor().getUiComponent()).getText();
//        for (DotThi dt : listDAO) {
//            if (timKiem.equals(date)) {
//                JOptionPane.showMessageDialog(null, "Không tồn tại đợt thi ở ngày bạn tìm \n Vui lòng thử lại", "Lỗi rồi", DO_NOTHING_ON_CLOSE);
//                date_timKiem.setBorder(border);
//                date_timKiem.requestFocus();
//                return false;
//            } else {
//                date_timKiem.setBorder(border1);
//            }
//        }
//        return true;
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Frame_lop = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblChuyenLop = new javax.swing.JTable();
        btn_tim = new javax.swing.JButton();
        cboCH = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cboCL = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cboLL = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_dotThi = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        date_timKiem = new com.toedter.calendar.JDateChooser();
        btn_timKiem = new javax.swing.JButton();
        btn_themDotThi = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_suaDotThi = new javax.swing.JButton();
        lbl_loiTimKiem = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        date_ngayThi = new com.toedter.calendar.JDateChooser();
        lbl_loiMaLop = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_loiNgayThi = new javax.swing.JLabel();
        txt_maLop = new javax.swing.JTextField();
        lbl_loiTenLop = new javax.swing.JLabel();
        txt_tenLop = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbc_caThi = new javax.swing.JComboBox<>();
        lbl_loiCaThi = new javax.swing.JLabel();
        lbl_loiSiSo = new javax.swing.JLabel();
        txt_siSo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lbl_loiVang = new javax.swing.JLabel();
        txt_vang = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        tblChuyenLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã lớp", "Tên lớp"
            }
        ));
        tblChuyenLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChuyenLopMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblChuyenLop);

        btn_tim.setText("Tìm");
        btn_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timActionPerformed(evt);
            }
        });

        cboCH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca 1 (7h-9h) 246", "Ca 1 (7h-9h) 357", "Ca 2 (9h-11h) 246", "Ca 2 (9h-11h) 357", "Ca 3 (12h-14h) 246", "Ca 3 (12h-14h) 357", "Ca 4 (14h-16h) 246", "Ca 4 (14h-16h) 357" }));

        jLabel14.setText("Ca học");

        cboCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));

        jLabel12.setText("Cấp lớp");

        cboLL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toeic", "Anh Văn Gia Tiếp", "Anh Văn Tổng Quát" }));

        jLabel15.setText("Loại lớp");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboLL, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboCL, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboCH, 0, 209, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_tim)
                        .addGap(29, 29, 29))))
        );

        javax.swing.GroupLayout Frame_lopLayout = new javax.swing.GroupLayout(Frame_lop.getContentPane());
        Frame_lop.getContentPane().setLayout(Frame_lopLayout);
        Frame_lopLayout.setHorizontalGroup(
            Frame_lopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Frame_lopLayout.setVerticalGroup(
            Frame_lopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Frame_lopLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Quản lý đợt thi");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Quản lí đợt thi");

        tbl_dotThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã đợt thi", "Mã lớp", "Tên lớp", "Ngày thi", "Ca thi", "Sĩ số", "Vắng"
            }
        ));
        tbl_dotThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dotThiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_dotThi);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tìm kiếm");

        btn_timKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_timKiem.setText("Tìm kiếm");
        btn_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemActionPerformed(evt);
            }
        });

        btn_themDotThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Create.png"))); // NOI18N
        btn_themDotThi.setText("Thêm");
        btn_themDotThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themDotThiActionPerformed(evt);
            }
        });

        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_suaDotThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_suaDotThi.setText("Sửa");
        btn_suaDotThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaDotThiActionPerformed(evt);
            }
        });

        lbl_loiTimKiem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbl_loiTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(date_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(btn_themDotThi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_suaDotThi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(305, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_timKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_timKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_themDotThi, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btn_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_suaDotThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Ca thi");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Ngày thi");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Mã lớp");

        txt_maLop.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_maLopFocusLost(evt);
            }
        });
        txt_maLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_maLopMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Tên lớp");

        cbc_caThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Sĩ số");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Vắng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_loiMaLop, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(txt_maLop)
                    .addComponent(lbl_loiTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_tenLop)
                    .addComponent(date_ngayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbc_caThi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiCaThi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loiSiSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_siSo)
                    .addComponent(lbl_loiVang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_vang)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maLop, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenLop, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_ngayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbc_caThi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiCaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_siSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiSiSo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_vang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiVang, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(14, 14, 14))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(447, 447, 447)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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

    private void tbl_dotThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dotThiMouseClicked
        int vitri = tbl_dotThi.getSelectedRow();
        if (vitri >= 0) {
            int row = (int) tbl_dotThi.getValueAt(vitri, 0);
            DotThi dt = dtDAO.clickTable(row, conn);
            if (dt != null) {
                txt_maLop.setText(String.valueOf(dt.getMaLop()));
                txt_tenLop.setText(dt.getTenLop());
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tbl_dotThi.getValueAt(vitri, 3));
                    date_ngayThi.setDate(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                cbc_caThi.setSelectedIndex(dt.getCaThi());
                txt_siSo.setText(String.valueOf(dt.getSiso()));
                txt_vang.setText(String.valueOf(dt.getVang()));
            }
        }
    }//GEN-LAST:event_tbl_dotThiMouseClicked

    private void btn_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemActionPerformed
        if (checkTimKiem()) {
            //if (chekTrungTK()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(date_timKiem.getDate());
            fillTable();
            listDAO = dtDAO.search(date, conn);
            dtm.setRowCount(0);
            dtm = (DefaultTableModel) tbl_dotThi.getModel();
            for (DotThi dt : listDAO) {
                Vector<Object> vec = new Vector<>();
                vec.add(dt.getMaDotThi());
                vec.add(dt.getMaLop());
                vec.add(dt.getTenLop());
                vec.add(dt.getNgayThi());
                vec.add(dt.getCaThi());
                vec.add(dt.getSiso());
                vec.add(dt.getVang());
                dtm.addRow(vec);
            }
            //}

        }
    }//GEN-LAST:event_btn_timKiemActionPerformed

    private void btn_themDotThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themDotThiActionPerformed
        if (checknull()) {
            DotThi dt = new DotThi();
            dt.setMaLop(Integer.parseInt(txt_maLop.getText()));
            dt.setTenLop(txt_tenLop.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdf.format(date_ngayThi.getDate());
            dt.setNgayThi(date);
            dt.setCaThi(cbc_caThi.getSelectedIndex());
            dt.setSiso(Integer.parseInt(txt_siSo.getText()));
            dt.setVang(Integer.parseInt(txt_vang.getText()));
            boolean them = dtDAO.insert(dt, conn);
            fillTable();
            Dialog.alert(null, "Thêm thành công");
        }
    }//GEN-LAST:event_btn_themDotThiActionPerformed

    private void btn_suaDotThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaDotThiActionPerformed
        if (checknull()) {
            DotThi dt = new DotThi();
            dt.setMaLop(Integer.parseInt(txt_maLop.getText()));
            dt.setTenLop(txt_tenLop.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdf.format(date_ngayThi.getDate());
            dt.setNgayThi(date);
            dt.setCaThi(cbc_caThi.getSelectedIndex());
            dt.setSiso(Integer.parseInt(txt_siSo.getText()));
            dt.setVang(Integer.parseInt(txt_vang.getText()));
            int maDotThi = (int) tbl_dotThi.getValueAt(tbl_dotThi.getSelectedRow(), 0);
            dt.setMaDotThi(maDotThi);
            dtDAO.update(dt, conn);
            fillTable();
            Dialog.alert(null, "Sửa thành  công");
        }
    }//GEN-LAST:event_btn_suaDotThiActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        fillTable();
        txt_maLop.setText("");
        txt_tenLop.setText("");
        cbc_caThi.setSelectedIndex(0);
        txt_siSo.setText("");
        txt_vang.setText("");
        ((JTextField) date_ngayThi.getDateEditor().getUiComponent()).setText("");
        ((JTextField) date_timKiem.getDateEditor().getUiComponent()).setText("");
        lbl_loiMaLop.setText("");
        txt_maLop.setBorder(border1);
        lbl_loiTenLop.setText("");
        txt_tenLop.setBorder(border1);
        lbl_loiNgayThi.setText("");
        date_ngayThi.setBorder(border1);
        lbl_loiCaThi.setText("");
        cbc_caThi.setBorder(border1);
        lbl_loiSiSo.setText("");
        txt_siSo.setBorder(border1);
        lbl_loiVang.setText("");
        txt_vang.setBorder(border1);
        lbl_loiTimKiem.setText("");
        date_timKiem.setBorder(border1);
    }//GEN-LAST:event_btn_clearActionPerformed

    private void txt_maLopFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_maLopFocusLost
        if (!txt_maLop.getText().equals("")) {
            DotThi dt = dtDAO.hienThiLop(Integer.parseInt(txt_maLop.getText()), conn);
            txt_tenLop.setText(dt.getTenLop());
            System.out.println(dt.getTenLop());
        }

    }//GEN-LAST:event_txt_maLopFocusLost

    private void btn_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timActionPerformed
       
        try {
            String ll = (String) cboLL.getSelectedItem();
            String cl = (String) cboCL.getSelectedItem();
            String ch = (String) cboCH.getSelectedItem();
           CallableStatement  call = conn.prepareCall("{call tim_lop_thi(?,?,?)}");
            call.setString(1, ll);
            call.setString(2, cl);
            call.setString(3, ch);
            ResultSet rs = call.executeQuery();
            dtm = (DefaultTableModel) tblChuyenLop.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Lop lop = new Lop();
                int maLop = rs.getInt("MALOP");
                String tenLop = rs.getString("TENLOP");

                dtm.addRow(new Object[]{
                    maLop, tenLop
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_timActionPerformed

    private void txt_maLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_maLopMouseClicked
        Frame_lop.setVisible(true);
        Frame_lop.setSize(560, 300);
        Frame_lop.setLocationRelativeTo(null);
        Frame_lop.setTitle("Lớp thi");
    }//GEN-LAST:event_txt_maLopMouseClicked

    private void tblChuyenLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChuyenLopMouseClicked
        int viTri =  tblChuyenLop.getSelectedRow();
        int maLop = (int) tblChuyenLop.getValueAt(viTri, 0);
        txt_maLop.setText(String.valueOf(maLop));
        txt_tenLop.setText((String) tblChuyenLop.getValueAt(viTri, 1));
        Frame_lop.dispose();
    }//GEN-LAST:event_tblChuyenLopMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame Frame_lop;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_suaDotThi;
    private javax.swing.JButton btn_themDotThi;
    private javax.swing.JButton btn_tim;
    private javax.swing.JButton btn_timKiem;
    private javax.swing.JComboBox<String> cbc_caThi;
    private javax.swing.JComboBox<String> cboCH;
    private javax.swing.JComboBox<String> cboCL;
    private javax.swing.JComboBox<String> cboLL;
    private com.toedter.calendar.JDateChooser date_ngayThi;
    private com.toedter.calendar.JDateChooser date_timKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_loiCaThi;
    private javax.swing.JLabel lbl_loiMaLop;
    private javax.swing.JLabel lbl_loiNgayThi;
    private javax.swing.JLabel lbl_loiSiSo;
    private javax.swing.JLabel lbl_loiTenLop;
    private javax.swing.JLabel lbl_loiTimKiem;
    private javax.swing.JLabel lbl_loiVang;
    private javax.swing.JTable tblChuyenLop;
    private javax.swing.JTable tbl_dotThi;
    private javax.swing.JTextField txt_maLop;
    private javax.swing.JTextField txt_siSo;
    private javax.swing.JTextField txt_tenLop;
    private javax.swing.JTextField txt_vang;
    // End of variables declaration//GEN-END:variables
}
