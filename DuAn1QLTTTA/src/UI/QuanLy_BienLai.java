/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.BienLaiDAO;
import DAO.DiemThiDAO;
import Entity.BienLai;
import TienIchHoTro.Dialog;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class QuanLy_BienLai extends javax.swing.JInternalFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    Connection conn = null;
    Border boder = BorderFactory.createLineBorder(Color.red);
    Border boder1 = BorderFactory.createLineBorder(Color.black);
    List<BienLai> list = new ArrayList<>();
    BienLaiDAO blDAO = new BienLaiDAO();
    DiemThiDAO dtDAO = new DiemThiDAO();

    public QuanLy_BienLai() {
        initComponents();
        conn = TienIchHoTro.ConnectToSQL.getConnect();
        fillTableHVCu();
        fillTableHVMoi();
        fillTableChuaThi();
        fillTableDaThi();
//        txt_tenHocVien.setEnabled(false);
//        txt_tenHocVien1.setEnabled(false);
    }

    void fillTableHVCu() {
        dtm = (DefaultTableModel) tbl_HVCu.getModel();
        dtm.setRowCount(0);
        list = blDAO.listHVCu(conn);
        for (BienLai bl : list) {
            Object obj[] = new Object[]{
                bl.getMaBienLai(), bl.getMaHocVien(),
                bl.getTenHocVien(),bl.getTenLop(), bl.getHocPhiNo(),
                bl.getNgayThuTien(), bl.getMaNhanVien()
            };
            dtm.addRow(obj);
        }
    }

    void fillTableHVMoi() {
        dtm = (DefaultTableModel) tbl_HVMoi.getModel();
        dtm.setRowCount(0);
        list = blDAO.listHVMoi(conn);
        for (BienLai bl : list) {
            Object obj[] = new Object[]{
                bl.getMaBienLai(), bl.getMaHocVien(),
                bl.getTenHocVien(), bl.getThanhTien(),
                bl.getNgayThuTien(), bl.getMaNhanVien()
            };
            dtm.addRow(obj);
        }
    }

    void fillTableChuaThi() {
        dtm = (DefaultTableModel) tbl_chuaThi.getModel();
        dtm.setRowCount(0);
        list = dtDAO.listChuaThi(conn);
        for (BienLai bl : list) {
            Object obj[] = new Object[]{
                bl.getMaBienLai(), bl.getMaLop(), bl.getTenHocVien(), bl.getMaDotThi(),
                bl.getDiemThi(), bl.getDiemThanhPhan(), bl.getDiemTong()
            };
            dtm.addRow(obj);
        }
    }

    void fillTableDaThi() {
        dtm = (DefaultTableModel) tbl_daThi.getModel();
        dtm.setRowCount(0);
        list = dtDAO.listDaThi(conn);
        for (BienLai bl : list) {
            Object obj[] = new Object[]{
                bl.getMaBienLai(), bl.getMaLop(), bl.getTenHocVien(), bl.getMaDotThi(),
                bl.getDiemThi(), bl.getDiemThanhPhan(), bl.getDiemTong()
            };
            dtm.addRow(obj);
        }
    }

    boolean checkHVMoi() {
        if (txt_maDangKiHVMoi.getText().equals("")) {
            lbl_loiMaDangKiHVMoi.setText("Bạn chưa nhập mã đăng kí của học viên!");
            txt_maDangKiHVMoi.setBorder(boder);
            lbl_loiMaDangKiHVMoi.setForeground(Color.red);
            txt_maDangKiHVMoi.requestFocus();
            return false;
        } else {
            lbl_loiMaDangKiHVMoi.setText("");
            txt_maDangKiHVMoi.setBorder(boder1);
        }

        if (txt_thanhTienHVMoi.getText().equals("")) {
            lbl_loiThanhTienHVMoi.setText("Bạn chưa nhập thành tiền!");
            txt_thanhTienHVMoi.setBorder(boder);
            lbl_loiThanhTienHVMoi.setForeground(Color.red);
            txt_thanhTienHVMoi.requestFocus();
            return false;
        } else {
            Pattern p = Pattern.compile("^\\d+$");
            if (!p.matcher(txt_thanhTienHVMoi.getText()).find()) {
                lbl_loiThanhTienHVMoi.setText("Bạn chỉ được nhập số vào đây!");
                txt_thanhTienHVMoi.setBorder(boder);
                lbl_loiThanhTienHVMoi.setForeground(Color.red);
                txt_thanhTienHVMoi.requestFocus();
                return false;
            } else {
                lbl_loiThanhTienHVMoi.setText("");
                txt_thanhTienHVMoi.setBorder(boder1);
            }
        }

        String date = ((JTextField) date_ngayThuTienHVMoi.getDateEditor().getUiComponent()).getText();
        if (date.equals("")) {
            lbl_loiNgayThuTienHVMoi.setText("Bạn chưa chọn ngày thi tiền");
            date_ngayThuTienHVMoi.setBorder(boder);
            lbl_loiNgayThuTienHVMoi.setForeground(Color.red);
            date_ngayThuTienHVMoi.requestFocus();
            return false;
        } else {
            lbl_loiNgayThuTienHVMoi.setText("");
            date_ngayThuTienHVMoi.setBorder(boder1);
        }

        if (txt_maNhanVienHVMoi.getText().equals("")) {
            lbl_loitimKiemHVMOI.setText("Bạn chưa nhập mã nhân viên");
            txt_maNhanVienHVMoi.setBorder(boder);
            lbl_loitimKiemHVMOI.setForeground(Color.red);
            txt_maNhanVienHVMoi.requestFocus();
            return false;
        } else {
            lbl_loitimKiemHVMOI.setText("");
            txt_maNhanVienHVMoi.setBorder(boder1);
        }

        return true;
    }

    boolean checkNullTKHVMoi() {
        String date = ((JTextField) date_timHVMoi.getDateEditor().getUiComponent()).getText();
        if (date.equals("")) {
            lbl_loitimKiemHVMOI.setText("Bạn chưa chọn ngày thu tiền!");
            date_timHVMoi.setBorder(boder);
            lbl_loitimKiemHVMOI.setForeground(Color.red);
            date_timHVMoi.requestFocus();
            return false;
        } else {
            lbl_loitimKiemHVMOI.setText("");
            date_timHVMoi.setBorder(boder1);
        }
        return true;
    }

    

   

    boolean checkDaThi() {
        if (txt_maLop1.getText().equals("")) {
            lbl_loMaLop1.setText("Bạn chưa nhập mã lớp");
            txt_maLop1.setBorder(boder);
            lbl_loMaLop1.setForeground(Color.red);
            txt_maLop1.requestFocus();
            return false;
        } else {
            lbl_loMaLop1.setText("");
            txt_maLop1.setBorder(boder1);
        }

        if (txt_maDotthi1.getText().equals("")) {
            lbl_loiMaDotThi1.setText("Bạn chưa nhập mã đợt thi !");
            txt_maDotthi1.setBorder(boder);
            lbl_loiMaDotThi1.setForeground(Color.red);
            txt_maDotthi1.requestFocus();
            return false;
        } else {
            lbl_loiMaDotThi1.setText("");
            txt_maDotthi1.setBorder(boder1);
        }

        if (txt_diemThi1.getText().equals("")) {
            lbl_loiDiemThi1.setText("Bạn chưa nhập điểm thi!");
            txt_diemThi1.setBorder(boder);
            lbl_loiDiemThi1.setForeground(Color.red);
            txt_diemThi1.requestFocus();
            return false;
        } else {
            lbl_loiDiemThi1.setText("");
            txt_diemThi1.setBorder(boder1);
        }

        if (txt_diemThanhPhan1.getText().equals("")) {
            lbl_loiDiemThanhPhan1.setText("Bạn chưa nhập điểm thành phần!");
            txt_diemThanhPhan1.setBorder(boder);
            lbl_loiDiemThanhPhan1.setForeground(Color.red);
            txt_diemThanhPhan1.requestFocus();
            return false;
        } else {
            lbl_loiDiemThanhPhan1.setText("");
            txt_diemThanhPhan1.setBorder(boder1);
        }

        if (txt_diemTong1.getText().equals("")) {
            lbl_loiDiemTong1.setText("Bạn chưa nhập điểm tổng!");
            txt_diemTong1.setBorder(boder);
            lbl_loiDiemTong1.setForeground(Color.red);
            txt_diemTong1.requestFocus();
            return false;
        } else {
            lbl_loiDiemTong1.setText("");
            txt_diemTong1.setBorder(boder1);
        }

        return true;
    }

    boolean checkTimKiemChuaThi() {
        if (txt_timKiemChuaThi.getText().equals("")) {
            txt_timKiemChuaThi.setText("Bạn chưa nhập tên học viên!");
            txt_timKiemChuaThi.setBorder(boder);
            txt_timKiemChuaThi.setForeground(Color.red);
            txt_timKiemChuaThi.requestFocus();
            return false;
        } else {
            txt_timKiemChuaThi.setText("");
            txt_timKiemChuaThi.setBorder(boder1);
        }

        return true;
    }

    boolean checkTimKiemDaThi() {
        if (txt_timKiemDaThi.getText().equals("")) {
            txt_timKiemDaThi.setText("Bạn chưa nhập tên học viên!");
            txt_timKiemDaThi.setBorder(boder);
            txt_timKiemDaThi.setForeground(Color.red);
            txt_timKiemDaThi.requestFocus();
            return false;
        } else {
            txt_timKiemDaThi.setText("");
            txt_timKiemDaThi.setBorder(boder1);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        lbl_loiMaDangKi = new javax.swing.JLabel();
        txt_maDangKiHVMoi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lbl_loiNgayThuTienHVMoi = new javax.swing.JLabel();
        txt_thanhTienHVMoi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        date_ngayThuTienHVMoi = new com.toedter.calendar.JDateChooser();
        lbl_loiThanhTienHVMoi = new javax.swing.JLabel();
        txt_maNhanVienHVMoi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lbl_loiMaDangKiHVMoi = new javax.swing.JLabel();
        lbl_loiMaNhanVienHVMoi1 = new javax.swing.JLabel();
        lbl_loiMaHocVien1 = new javax.swing.JLabel();
        txt_maHocVien1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_HVMoi = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btn_ClearMoi = new javax.swing.JButton();
        btn_SuaHVMoi = new javax.swing.JButton();
        btn_themHVMoi = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        date_timHVMoi = new com.toedter.calendar.JDateChooser();
        btn_HVMoi = new javax.swing.JButton();
        lbl_loitimKiemHVMOI = new javax.swing.JLabel();
        btn_bienLaiHVMoi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_HVCu = new javax.swing.JTable();
        txtTenhv = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        jPanel39 = new javax.swing.JPanel();
        jTabbedPane10 = new javax.swing.JTabbedPane();
        jPanel38 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        lbl_loMaLop9 = new javax.swing.JLabel();
        lbl_loiTenHocVien9 = new javax.swing.JLabel();
        txt_tenHocVien9 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        lbl_loiMaDotThi9 = new javax.swing.JLabel();
        txt_maDotthi9 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        lbl_loiDiemThi9 = new javax.swing.JLabel();
        txt_diemThi9 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        lbl_loiDiemThanhPhan9 = new javax.swing.JLabel();
        txt_diemThanhPhan9 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        lbl_loiDiemTong9 = new javax.swing.JLabel();
        txt_diemTong9 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txt_maLop9 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_chuaThi = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_timKiemChuaThi = new javax.swing.JTextField();
        btn_TimKiemChuaThi = new javax.swing.JButton();
        btn_suaHVChuaThi = new javax.swing.JButton();
        btn_ClearHVChuaThi = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        lbl_loMaLop1 = new javax.swing.JLabel();
        lbl_loiTenHocVien1 = new javax.swing.JLabel();
        txt_tenHocVien1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        lbl_loiMaDotThi1 = new javax.swing.JLabel();
        txt_maDotthi1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        lbl_loiDiemThi1 = new javax.swing.JLabel();
        txt_diemThi1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        lbl_loiDiemThanhPhan1 = new javax.swing.JLabel();
        txt_diemThanhPhan1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        lbl_loiDiemTong1 = new javax.swing.JLabel();
        txt_diemTong1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_maLop1 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_timKiemDaThi = new javax.swing.JTextField();
        btn_timKiemHVDaThi = new javax.swing.JButton();
        btn_suaHVDaThi = new javax.swing.JButton();
        btn_ClearHVDaThi = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_daThi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Quản lý biên lai");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Mã biên lai");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Ngày thu tiền");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Thành tiền");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Mã nhân viên");

        txt_maHocVien1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_maHocVien1FocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Mã Học Viên");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_loiThanhTienHVMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_ngayThuTienHVMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_thanhTienHVMoi)
                            .addComponent(lbl_loiNgayThuTienHVMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_maNhanVienHVMoi)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel13))
                                .addGap(0, 230, Short.MAX_VALUE))
                            .addComponent(lbl_loiMaDangKiHVMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_loiMaDangKi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txt_maDangKiHVMoi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbl_loiMaNhanVienHVMoi1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_loiMaHocVien1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_maHocVien1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addGap(0, 245, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maDangKiHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_loiMaDangKi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_loiMaDangKiHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_thanhTienHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiThanhTienHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_ngayThuTienHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiNgayThuTienHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maNhanVienHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_loiMaNhanVienHVMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 442, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(358, 358, 358)
                    .addComponent(jLabel15)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txt_maHocVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbl_loiMaHocVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(359, Short.MAX_VALUE)))
        );

        jScrollPane1.setViewportView(jPanel4);

        tbl_HVMoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã biên lai", "Mã học viên", "Tên người đăng kí", "Thành tiền", "Ngày thu tiền", "Mã nhân viên"
            }
        ));
        tbl_HVMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HVMoiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_HVMoi);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_ClearMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_ClearMoi.setText("Clear");
        btn_ClearMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearMoiActionPerformed(evt);
            }
        });

        btn_SuaHVMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_SuaHVMoi.setText("Sửa");
        btn_SuaHVMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaHVMoiActionPerformed(evt);
            }
        });

        btn_themHVMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Create.png"))); // NOI18N
        btn_themHVMoi.setText("Xếp lớp");
        btn_themHVMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themHVMoiActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Tìm kiếm");

        btn_HVMoi.setText("Tìm Kiếm");
        btn_HVMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HVMoiActionPerformed(evt);
            }
        });

        btn_bienLaiHVMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Print preview.png"))); // NOI18N
        btn_bienLaiHVMoi.setText("Xuất Biên lai");
        btn_bienLaiHVMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bienLaiHVMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbl_loitimKiemHVMOI, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(date_timHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_HVMoi)
                            .addGap(28, 28, 28)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_themHVMoi)
                        .addGap(18, 18, 18)
                        .addComponent(btn_SuaHVMoi)
                        .addGap(15, 15, 15)
                        .addComponent(btn_ClearMoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_bienLaiHVMoi)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date_timHVMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_HVMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(lbl_loitimKiemHVMOI, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_themHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SuaHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ClearMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bienLaiHVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Chưa xếp lớp", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbl_HVCu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã biên lai", "Mã Học Viên", "Tên học viên", "Tên lớp", "Học phí nợ", "Ngày thu tiền", "Mã nhân viên"
            }
        ));
        tbl_HVCu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HVCuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_HVCu);

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setText("Tên học viên");

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtTenhv, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 688, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenhv, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đã có lớp", jPanel3);

        jTabbedPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setText("Tên học viên");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setText("Mã đợt thi");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setText("Điểm thi");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setText("Điểm thành phần");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setText("Điểm tổng");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setText("Mã lớp");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_loMaLop9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addComponent(lbl_loiTenHocVien9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_tenHocVien9)
                    .addComponent(lbl_loiMaDotThi9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_maDotthi9)
                    .addComponent(lbl_loiDiemThi9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_diemThi9)
                    .addComponent(lbl_loiDiemThanhPhan9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_diemThanhPhan9)
                    .addComponent(lbl_loiDiemTong9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_diemTong9)
                    .addComponent(txt_maLop9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel70)
                            .addComponent(jLabel71)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74)
                            .addComponent(jLabel75)
                            .addComponent(jLabel72))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maLop9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loMaLop9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenHocVien9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTenHocVien9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maDotthi9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiMaDotThi9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_diemThi9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiDiemThi9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_diemThanhPhan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiDiemThanhPhan9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_diemTong9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiDiemTong9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_chuaThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã biên lai", "Mã lớp", "Tên học viên", "Mã đợt thi", "Điểm thi", "Điểm thành phần", "Điểm tổng"
            }
        ));
        tbl_chuaThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_chuaThiMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_chuaThi);

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Tìm kiếm ");

        txt_timKiemChuaThi.setText("Nhập tên học viên");

        btn_TimKiemChuaThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_TimKiemChuaThi.setText("Tìm kiếm");
        btn_TimKiemChuaThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemChuaThiActionPerformed(evt);
            }
        });

        btn_suaHVChuaThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_suaHVChuaThi.setText("Sửa");
        btn_suaHVChuaThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaHVChuaThiActionPerformed(evt);
            }
        });

        btn_ClearHVChuaThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_ClearHVChuaThi.setText("Clear");
        btn_ClearHVChuaThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearHVChuaThiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btn_suaHVChuaThi)
                        .addGap(29, 29, 29)
                        .addComponent(btn_ClearHVChuaThi)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txt_timKiemChuaThi, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemChuaThi)
                        .addGap(293, 293, 293))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_timKiemChuaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_TimKiemChuaThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_suaHVChuaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ClearHVChuaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane10.addTab("Học viên chưa thi", jPanel38);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Tên học viên");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Mã đợt thi");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Điểm thi");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Điểm thành phần");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Điểm tổng");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Mã lớp");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_loMaLop1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addComponent(lbl_loiTenHocVien1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_tenHocVien1)
                    .addComponent(lbl_loiMaDotThi1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_maDotthi1)
                    .addComponent(lbl_loiDiemThi1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_diemThi1)
                    .addComponent(lbl_loiDiemThanhPhan1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_diemThanhPhan1)
                    .addComponent(lbl_loiDiemTong1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_diemTong1)
                    .addComponent(txt_maLop1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19)
                            .addComponent(jLabel23)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel21))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maLop1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loMaLop1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenHocVien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiTenHocVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maDotthi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiMaDotThi1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_diemThi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiDiemThi1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_diemThanhPhan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiDiemThanhPhan1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_diemTong1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiDiemTong1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Tìm kiếm");

        txt_timKiemDaThi.setText("Nhập tên học viên");

        btn_timKiemHVDaThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Search.png"))); // NOI18N
        btn_timKiemHVDaThi.setText("Tìm kiếm");
        btn_timKiemHVDaThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemHVDaThiActionPerformed(evt);
            }
        });

        btn_suaHVDaThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btn_suaHVDaThi.setText("Sửa");
        btn_suaHVDaThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaHVDaThiActionPerformed(evt);
            }
        });

        btn_ClearHVDaThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Refresh.png"))); // NOI18N
        btn_ClearHVDaThi.setText("Clear");
        btn_ClearHVDaThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearHVDaThiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btn_suaHVDaThi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ClearHVDaThi)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txt_timKiemDaThi, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_timKiemHVDaThi)
                        .addGap(293, 293, 293))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txt_timKiemDaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_timKiemHVDaThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_suaHVDaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ClearHVDaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tbl_daThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã biên lai", "Mã lớp", "Tên học viên", "Mã đợt thi", "Điểm thi", "Điểm thành phần", "Điểm tổng"
            }
        ));
        tbl_daThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_daThiMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_daThi);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane10.addTab("Học viên đã thi", jPanel10);

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane10)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane10)
        );

        jTabbedPane1.addTab("Điểm thi", jPanel39);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Quản lý biên lai");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(434, 434, 434)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Học chưa xếp lớp");

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

    private void tbl_daThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_daThiMouseClicked
        int vitri = tbl_daThi.getSelectedRow();
        if (vitri >= 0) {
            int row = (int) tbl_daThi.getValueAt(vitri, 0);
            BienLai bl = dtDAO.clickTableDaThi(row, conn);
            if (bl != null) {
                txt_maLop1.setText(String.valueOf(bl.getMaLop()));
                txt_tenHocVien1.setText(bl.getTenHocVien());
                txt_maDotthi1.setText(String.valueOf(bl.getMaDotThi()));
                txt_diemThi1.setText(String.valueOf(bl.getDiemThi()));
                txt_diemThanhPhan1.setText(String.valueOf(bl.getDiemThanhPhan()));
                txt_diemTong1.setText(String.valueOf(bl.getDiemTong()));
            }
        }
    }//GEN-LAST:event_tbl_daThiMouseClicked

    private void btn_ClearHVDaThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearHVDaThiActionPerformed
        txt_maLop1.setText("");
        txt_tenHocVien1.setText("");
        txt_maDotthi1.setText("");
        txt_diemThi1.setText("");
        txt_diemThanhPhan1.setText("");
        txt_diemTong1.setText("");
        fillTableDaThi();
        lbl_loMaLop1.setText("");
        txt_maLop1.setBorder(boder1);
        lbl_loiMaDotThi1.setText("");
        txt_maDotthi1.setBorder(boder1);
        lbl_loiDiemThi1.setText("");
        txt_diemThi1.setBorder(boder1);
        lbl_loiDiemThi1.setText("");
        txt_diemThi1.setBorder(boder1);
        lbl_loiDiemThanhPhan1.setText("");
        txt_diemThanhPhan1.setBorder(boder1);
        lbl_loiDiemTong1.setText("");
        txt_diemTong1.setBorder(boder1);
        txt_timKiemDaThi.setText("");
        txt_timKiemDaThi.setBorder(boder1);
    }//GEN-LAST:event_btn_ClearHVDaThiActionPerformed

    private void btn_suaHVDaThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaHVDaThiActionPerformed
        if (checkDaThi()) {
            BienLai bl = new BienLai();
            bl.setMaLop(Integer.parseInt(txt_maLop1.getText()));
            bl.setMaDotThi(Integer.parseInt(txt_maDotthi1.getText()));
            bl.setDiemThi(Float.parseFloat(txt_diemThi1.getText()));
            bl.setDiemThanhPhan(Float.parseFloat(txt_diemThanhPhan1.getText()));
            bl.setDiemTong(Float.parseFloat(txt_diemTong1.getText()));
            int row = (int) tbl_daThi.getValueAt(tbl_daThi.getSelectedRow(), 0);
            bl.setMaBienLai(row);
            dtDAO.suaChuaThi(conn, bl);
            fillTableDaThi();
        }
    }//GEN-LAST:event_btn_suaHVDaThiActionPerformed

    private void btn_timKiemHVDaThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemHVDaThiActionPerformed
        if (checkTimKiemDaThi()) {
            dtm = (DefaultTableModel) tbl_daThi.getModel();
            dtm.setRowCount(0);
            list = dtDAO.timKiemDaThi(txt_timKiemDaThi.getText(), conn);
            for (BienLai bl : list) {
                Vector<Object> vec = new Vector<>();
                vec.add(bl.getMaBienLai());
                vec.add(bl.getMaLop());
                vec.add(bl.getTenHocVien());
                vec.add(bl.getMaDotThi());
                vec.add(bl.getDiemThi());
                vec.add(bl.getDiemThanhPhan());
                vec.add(bl.getDiemTong());
                dtm.addRow(vec);
            }
        }
    }//GEN-LAST:event_btn_timKiemHVDaThiActionPerformed

    private void btn_ClearHVChuaThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearHVChuaThiActionPerformed
        txt_maLop9.setText("");
        txt_tenHocVien9.setText("");
        txt_maDotthi9.setText("");
        txt_diemThi9.setText("");
        txt_diemThanhPhan9.setText("");
        txt_diemTong9.setText("");
        fillTableChuaThi();
        txt_timKiemChuaThi.setText("");
        txt_timKiemChuaThi.setBorder(boder1);
    }//GEN-LAST:event_btn_ClearHVChuaThiActionPerformed

    private void btn_suaHVChuaThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaHVChuaThiActionPerformed
        BienLai bl = new BienLai();
        bl.setMaLop(Integer.parseInt(txt_maLop9.getText()));
        bl.setMaDotThi(Integer.parseInt(txt_maDotthi9.getText()));
        bl.setDiemThi(Float.parseFloat(txt_diemThi9.getText()));
        bl.setDiemThanhPhan(Float.parseFloat(txt_diemThanhPhan9.getText()));
        bl.setDiemTong(Float.parseFloat(txt_diemTong9.getText()));
        int row = (int) tbl_chuaThi.getValueAt(tbl_chuaThi.getSelectedRow(), 0);
        bl.setMaBienLai(row);
        dtDAO.suaChuaThi(conn, bl);
        fillTableChuaThi();
    }//GEN-LAST:event_btn_suaHVChuaThiActionPerformed

    private void btn_TimKiemChuaThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemChuaThiActionPerformed
        if (checkTimKiemChuaThi()) {
            dtm = (DefaultTableModel) tbl_chuaThi.getModel();
            dtm.setRowCount(0);
            list = dtDAO.timKiemChuaThi(txt_timKiemChuaThi.getText(), conn);
            for (BienLai bl : list) {
                Vector<Object> vec = new Vector<>();
                vec.add(bl.getMaBienLai());
                vec.add(bl.getMaLop());
                vec.add(bl.getTenHocVien());
                vec.add(bl.getMaDotThi());
                vec.add(bl.getDiemThi());
                vec.add(bl.getDiemThanhPhan());
                vec.add(bl.getDiemTong());
                dtm.addRow(vec);
            }
        }
    }//GEN-LAST:event_btn_TimKiemChuaThiActionPerformed

    private void tbl_chuaThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_chuaThiMouseClicked
        int vitri = tbl_chuaThi.getSelectedRow();
        if (vitri >= 0) {
            int row = (int) tbl_chuaThi.getValueAt(vitri, 0);
            BienLai bl = dtDAO.clickTableChuaThi(row, conn);
            if (bl != null) {
                try {
                    String a= String.valueOf(bl.getMaLop());
                    txt_maLop9.setText(a);
                } catch (Exception e) {
                    txt_maLop9.setText("0");
                }
               
                txt_tenHocVien9.setText(bl.getTenHocVien());
                txt_maDotthi9.setText(String.valueOf(bl.getMaDotThi()));
                txt_diemThi9.setText(String.valueOf(bl.getDiemThi()));
                txt_diemThanhPhan9.setText(String.valueOf(bl.getDiemThanhPhan()));
                txt_diemTong9.setText(String.valueOf(bl.getDiemTong()));
            }
        }
    }//GEN-LAST:event_tbl_chuaThiMouseClicked

    private void tbl_HVCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HVCuMouseClicked
        
    }//GEN-LAST:event_tbl_HVCuMouseClicked

    private void btn_bienLaiHVMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bienLaiHVMoiActionPerformed
        Document doc = new Document();
        BienLai bl = new BienLai();
        int viTri = tbl_HVMoi.getSelectedRow();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        if (viTri > -1) {
            int row = (int) tbl_HVMoi.getValueAt(viTri, 0);
            bl = blDAO.bienLai(row, conn);
            if (bl != null) {
                try {
                    FileOutputStream fos = new FileOutputStream("C:\\Users\\congc\\Desktop\\Bienlai"+bl.getMaBienLai()+".pdf");
                    String hocPhi1= vn.format( bl.getHocPhi());
                    String hocPhiNo1= vn.format( bl.getHocPhiNo());
                    String thanhTien1= vn.format( bl.getThanhTien());
                    PdfWriter writer = PdfWriter.getInstance(doc, fos);
                    writer.setPdfVersion(PdfWriter.VERSION_1_7);

                    doc.open();
                    Font fontNgay = new Font(Font.FontFamily.TIMES_ROMAN, 24.0f, Font.NORMAL, BaseColor.BLACK);
                    Font font = new Font(Font.FontFamily.TIMES_ROMAN, 24.0f, Font.BOLD, BaseColor.BLACK);
                    Font fontBold = new Font(Font.FontFamily.TIMES_ROMAN, 36.0f, Font.BOLD, BaseColor.BLACK);
                    Font fontBoldInfor = new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, Font.BOLD, BaseColor.BLACK);
                    Paragraph tenTrungTam = new Paragraph("English center", font);
                    Paragraph tenTrungTam1 = new Paragraph("Language School", fontBoldInfor);
                    Paragraph maDangKi = new Paragraph("ID: "+bl.getMaBienLai(), font);
                    Paragraph tieuDe = new Paragraph("Receipt of payment", fontBold);
                    Paragraph ngayThu = new Paragraph("Day " + bl.getNgayThuTien(), fontNgay);
                    Paragraph hoTen = new Paragraph("Name Stundent: "+bl.getTenHocVien(), fontBoldInfor);
                    Paragraph ID = new Paragraph("ID Student: "+bl.getMaHocVien(), fontBoldInfor);
                    Paragraph loaiLop = new Paragraph("Class Type: "+bl.getTenLoaiLop(), fontBoldInfor);
                    Paragraph capLop = new Paragraph("Class Level: "+bl.getTenCapLop(), fontBoldInfor);
                    Paragraph hocPhi = new Paragraph("Tuition:          " +hocPhi1+"    "+ "VND", fontBoldInfor);
                    Paragraph hocPhiNo = new Paragraph("Debt Tuition: " +hocPhiNo1+"                  "+ "VND", fontBoldInfor);
                    Paragraph thanhTien = new Paragraph("Into Money:   "+thanhTien1 +"    "+ "VND", fontBoldInfor);

                    Paragraph nguoiHoc = new Paragraph("Subscribers", fontBoldInfor);
                    Paragraph nguoiThu = new Paragraph("The cashier", fontBoldInfor);

                    tenTrungTam.setAlignment(Element.ALIGN_LEFT);
                    tenTrungTam1.setAlignment(Element.ALIGN_LEFT);
                    maDangKi.setAlignment(Element.ALIGN_RIGHT);
                    tieuDe.setAlignment(Element.ALIGN_CENTER);
                    ngayThu.setAlignment(Element.ALIGN_CENTER);
                    nguoiThu.setAlignment(Element.ALIGN_RIGHT);

                    tenTrungTam1.setIndentationLeft(15);
                    maDangKi.setIndentationRight(40);
                    hoTen.setIndentationLeft(30);
                    ID.setIndentationLeft(hoTen.getIndentationLeft());
                    loaiLop.setIndentationLeft(hoTen.getIndentationLeft());
                    capLop.setIndentationLeft(hoTen.getIndentationLeft());
                    thanhTien.setIndentationLeft(hoTen.getIndentationLeft());
                    hocPhiNo.setIndentationLeft(hoTen.getIndentationLeft());
                    hocPhi.setIndentationLeft(hoTen.getIndentationLeft());
                    nguoiHoc.setIndentationLeft(hoTen.getIndentationLeft());
                    nguoiThu.setIndentationRight(30);

                    maDangKi.setSpacingBefore(-50);
                    hoTen.setSpacingBefore(40);
                    ID.setSpacingBefore(10);
                    loaiLop.setSpacingBefore(10);
                    capLop.setSpacingBefore(10);
                    thanhTien.setSpacingBefore(10);
                    hocPhiNo.setSpacingBefore(10);
                    hocPhi.setSpacingBefore(10);
                    thanhTien.setSpacingAfter(50);
                    nguoiThu.setSpacingBefore(-21);

                    doc.add(tenTrungTam);
                    doc.add(tenTrungTam1);
                    doc.add(maDangKi);
                    doc.add(tieuDe);
                    doc.add(ngayThu);
                    doc.add(hoTen);
                    doc.add(ID);
                    doc.add(loaiLop);
                    doc.add(capLop);
                    doc.add(hocPhi);
                    doc.add(hocPhiNo);
                    doc.add(thanhTien);
                    doc.add(nguoiHoc);
                    doc.add(nguoiThu);

                    doc.close();
                } catch (DocumentException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Dialog.alert(null, "Xuất biên lai thành công");
            }
        }
    }//GEN-LAST:event_btn_bienLaiHVMoiActionPerformed

    private void btn_HVMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HVMoiActionPerformed
        if (checkNullTKHVMoi()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(date_timHVMoi.getDate());
            fillTableHVMoi();
            list = blDAO.timBienLaiMoi(date, conn);
            dtm.setRowCount(0);
            dtm = (DefaultTableModel) tbl_HVMoi.getModel();
            for (BienLai bl : list) {
                Vector<Object> vec = new Vector<>();
                vec.add(bl.getMaBienLai());
                vec.add(bl.getMaDangKi());
                vec.add(bl.getTenHocVien());
                vec.add(bl.getHocPhi());
                vec.add(bl.getHocPhiNo());
                vec.add(bl.getThanhTien());
                vec.add(bl.getNgayThuTien());
                vec.add(bl.getMaNhanVien());
                vec.add(bl.getTenNhanVien());
                dtm.addRow(vec);
            }
        }
    }//GEN-LAST:event_btn_HVMoiActionPerformed

    private void btn_themHVMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themHVMoiActionPerformed
        //        if (checkHVMoi()) {
            //            BienLai bl = new BienLai();
            //            bl.setMaDangKi(Integer.parseInt(txt_maDangKiHVMoi.getText()));
            //            bl.setThanhTien(Float.parseFloat(txt_thanhTienHVMoi.getText()));
            //            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            //            String date = sdf.format(date_ngayThuTienHVMoi.getDate());
            //            bl.setNgayThuTien(date);
            //            bl.setMaNhanVien(Integer.parseInt(txt_maNhanVienHVMoi.getText()));
            //            boolean them = blDAO.themHVMoi(bl, conn);
            //            fillTableHVMoi();
            //            Dialog.alert(null, "Thêm thành công");
            //        }
        try {
            int vitri = tbl_HVMoi.getSelectedRow();
            if (vitri>=0) {
                int row;
                row = (int) tbl_HVMoi.getValueAt(vitri, 0);
                boolean bl = blDAO.xepLop(row, conn);
                if (bl==true) {
                    JOptionPane.showMessageDialog(this, "Đã xếp học viên vào lớp");
                    boolean hv = blDAO.themHV(row,conn);
                } else {
                    JOptionPane.showMessageDialog(this, "Hiện tại chưa có lớp phù hợp, mời tạo thêm");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mời chọn đối tượng");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        fillTableHVCu();
    }//GEN-LAST:event_btn_themHVMoiActionPerformed

    private void btn_SuaHVMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaHVMoiActionPerformed
        if (checkHVMoi()) {
            BienLai bl = new BienLai();
            bl.setMaHocVien(Integer.parseInt(txt_maHocVien1.getText()));
            bl.setMaDangKi(Integer.parseInt(txt_maDangKiHVMoi.getText()));
            bl.setThanhTien(Float.parseFloat(txt_thanhTienHVMoi.getText()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdf.format(date_ngayThuTienHVMoi.getDate());
            bl.setNgayThuTien(date);
            bl.setMaNhanVien(Integer.parseInt(txt_maNhanVienHVMoi.getText()));
            int row = (int) tbl_HVMoi.getValueAt(tbl_HVMoi.getSelectedRow(), 0);
            bl.setMaBienLai(row);
            blDAO.suaHVMoi(bl, conn);
            fillTableHVMoi();
            Dialog.alert(null, "Sửa thành công");
        }
    }//GEN-LAST:event_btn_SuaHVMoiActionPerformed

    private void btn_ClearMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearMoiActionPerformed
        fillTableHVMoi();
        txt_maHocVien1.setText("");
         lbl_loiMaHocVien1.setText("");
        txt_maHocVien1.setBorder(boder1);
        txt_maDangKiHVMoi.setText("");
        txt_maNhanVienHVMoi.setText("");
        txt_thanhTienHVMoi.setText("");
        ((JTextField) date_ngayThuTienHVMoi.getDateEditor().getUiComponent()).setText("");
        ((JTextField) date_timHVMoi.getDateEditor().getUiComponent()).setText("");
        lbl_loiMaDangKiHVMoi.setText("");
        txt_maDangKiHVMoi.setBorder(boder1);
        lbl_loiThanhTienHVMoi.setText("");
        txt_thanhTienHVMoi.setBorder(boder1);
        lbl_loiNgayThuTienHVMoi.setText("");
        date_ngayThuTienHVMoi.setBorder(boder1);
        lbl_loitimKiemHVMOI.setText("");
        txt_maNhanVienHVMoi.setBorder(boder1);
        lbl_loitimKiemHVMOI.setText("");
        date_timHVMoi.setBorder(boder1);
    }//GEN-LAST:event_btn_ClearMoiActionPerformed

    private void tbl_HVMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HVMoiMouseClicked
        int vitri = tbl_HVMoi.getSelectedRow();
        if (vitri > -1) {
            int row = (int) tbl_HVMoi.getValueAt(vitri, 0);
            BienLai bl = blDAO.clickTableMoi(row, conn);
            if (bl != null) {
                try {
                    String a= String.valueOf(bl.getMaHocVien());
                    txt_maHocVien1.setText(a);
                } catch (Exception e) {
                    txt_maHocVien1.setText("0");
                }
                
                txt_maDangKiHVMoi.setText(String.valueOf(bl.getMaDangKi()));
                txt_thanhTienHVMoi.setText(String.valueOf(bl.getThanhTien()));
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tbl_HVMoi.getValueAt(vitri, 4));
                    date_ngayThuTienHVMoi.setDate(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                txt_maNhanVienHVMoi.setText(String.valueOf(bl.getMaNhanVien()));
            }
        }
    }//GEN-LAST:event_tbl_HVMoiMouseClicked

    private void txt_maHocVien1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_maHocVien1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maHocVien1FocusLost

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        try {
            //            String s = txtTimKiem.getText().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1 $2 $3");
            //            txtTimKiem.setText(s);
            fillTableHVCu();
            list = blDAO.timBienLaiCu(txtTenhv.getText(), conn);
            dtm.setRowCount(0);
            dtm = (DefaultTableModel) tbl_HVCu.getModel();
            for (BienLai nd : list) {
                Vector<Object> vec = new Vector<>();
                vec.add(nd.getMaBienLai());
                vec.add(nd.getTenHocVien());
                vec.add(nd.getMaHocVien());        
                vec.add(nd.getTenLop());
                vec.add(nd.getHocPhiNo());
                vec.add(nd.getNgayThuTien());
                vec.add(nd.getMaNhanVien());
                
                dtm.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btn_ClearHVChuaThi;
    private javax.swing.JButton btn_ClearHVDaThi;
    private javax.swing.JButton btn_ClearMoi;
    private javax.swing.JButton btn_HVMoi;
    private javax.swing.JButton btn_SuaHVMoi;
    private javax.swing.JButton btn_TimKiemChuaThi;
    private javax.swing.JButton btn_bienLaiHVMoi;
    private javax.swing.JButton btn_suaHVChuaThi;
    private javax.swing.JButton btn_suaHVDaThi;
    private javax.swing.JButton btn_themHVMoi;
    private javax.swing.JButton btn_timKiemHVDaThi;
    private com.toedter.calendar.JDateChooser date_ngayThuTienHVMoi;
    private com.toedter.calendar.JDateChooser date_timHVMoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane10;
    private javax.swing.JLabel lbl_loMaLop1;
    private javax.swing.JLabel lbl_loMaLop9;
    private javax.swing.JLabel lbl_loiDiemThanhPhan1;
    private javax.swing.JLabel lbl_loiDiemThanhPhan9;
    private javax.swing.JLabel lbl_loiDiemThi1;
    private javax.swing.JLabel lbl_loiDiemThi9;
    private javax.swing.JLabel lbl_loiDiemTong1;
    private javax.swing.JLabel lbl_loiDiemTong9;
    private javax.swing.JLabel lbl_loiMaDangKi;
    private javax.swing.JLabel lbl_loiMaDangKiHVMoi;
    private javax.swing.JLabel lbl_loiMaDotThi1;
    private javax.swing.JLabel lbl_loiMaDotThi9;
    private javax.swing.JLabel lbl_loiMaHocVien1;
    private javax.swing.JLabel lbl_loiMaNhanVienHVMoi1;
    private javax.swing.JLabel lbl_loiNgayThuTienHVMoi;
    private javax.swing.JLabel lbl_loiTenHocVien1;
    private javax.swing.JLabel lbl_loiTenHocVien9;
    private javax.swing.JLabel lbl_loiThanhTienHVMoi;
    private javax.swing.JLabel lbl_loitimKiemHVMOI;
    private javax.swing.JTable tbl_HVCu;
    private javax.swing.JTable tbl_HVMoi;
    private javax.swing.JTable tbl_chuaThi;
    private javax.swing.JTable tbl_daThi;
    private javax.swing.JTextField txtTenhv;
    private javax.swing.JTextField txt_diemThanhPhan1;
    private javax.swing.JTextField txt_diemThanhPhan9;
    private javax.swing.JTextField txt_diemThi1;
    private javax.swing.JTextField txt_diemThi9;
    private javax.swing.JTextField txt_diemTong1;
    private javax.swing.JTextField txt_diemTong9;
    private javax.swing.JTextField txt_maDangKiHVMoi;
    private javax.swing.JTextField txt_maDotthi1;
    private javax.swing.JTextField txt_maDotthi9;
    private javax.swing.JTextField txt_maHocVien1;
    private javax.swing.JTextField txt_maLop1;
    private javax.swing.JTextField txt_maLop9;
    private javax.swing.JTextField txt_maNhanVienHVMoi;
    private javax.swing.JTextField txt_tenHocVien1;
    private javax.swing.JTextField txt_tenHocVien9;
    private javax.swing.JTextField txt_thanhTienHVMoi;
    private javax.swing.JTextField txt_timKiemChuaThi;
    private javax.swing.JTextField txt_timKiemDaThi;
    // End of variables declaration//GEN-END:variables
}
