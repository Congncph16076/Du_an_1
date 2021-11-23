/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.NguoiDung;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DangNhapDAO {

    public List<NguoiDung> dangNhapQuanLy(NguoiDung nd, Connection conn) {
        List<NguoiDung> listND = new ArrayList<>();
        String dangNhapQuanLy = "SELECT TENDANGNHAP,MATKHAU from dbo.NGUOIDUNG\n"
                + "WHERE TENDANGNHAP =? AND MATKHAU = ? AND TENVAITRO = 0";
        try {
            PreparedStatement ptmt = conn.prepareStatement(dangNhapQuanLy);
            ptmt.setString(1, nd.getTenDangNhap());
            ptmt.setString(2, nd.getMatKhau());
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                //NguoiDung nd = new NguoiDung();
                nd.setTenDangNhap(rs.getString("TENDANGNHAP"));
                nd.setMatKhau(rs.getString("MATKHAU"));
                //nd.setVaiTro(rs.getInt(0));
                listND.add(nd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listND;
    }

    public List<NguoiDung> listQuanLy(NguoiDung nd, Connection conn) {
        List<NguoiDung> listND = new ArrayList<>();
        String dangNhapQuanLy = "SELECT TENDANGNHAP,MATKHAU from dbo.NGUOIDUNG\n"
                + "WHERE TENVAITRO  = 0";
        try {
            PreparedStatement ptmt = conn.prepareStatement(dangNhapQuanLy);

            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                //NguoiDung nd = new NguoiDung();
                nd.setTenDangNhap(rs.getString("TENDANGNHAP"));
                nd.setMatKhauCu(rs.getString("MATKHAU"));
                //nd.setVaiTro(rs.getInt(0));
                listND.add(nd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listND;
    }

    public List<NguoiDung> dangNhapKeToan(NguoiDung nd, Connection conn) {
        List<NguoiDung> listND = new ArrayList<>();
        String dangNhapQuanLy = "SELECT TENDANGNHAP,MATKHAU from dbo.NGUOIDUNG\n"
                + "WHERE TENDANGNHAP =? AND MATKHAU = ? AND TENVAITRO = 1";
        try {
            PreparedStatement ptmt = conn.prepareStatement(dangNhapQuanLy);
            ptmt.setString(1, nd.getTenDangNhap());
            ptmt.setString(2, nd.getMatKhau());
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                //NguoiDung nd = new NguoiDung();
                nd.setTenDangNhap(rs.getString("TENDANGNHAP"));
                nd.setMatKhau(rs.getString("MATKHAU"));
                //nd.setVaiTro(rs.getInt(1));
                listND.add(nd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listND;
    }

    public List<NguoiDung> listKeToan(NguoiDung nd, Connection conn) {
        List<NguoiDung> listND = new ArrayList<>();
        String dangNhapQuanLy = "SELECT TENDANGNHAP,MATKHAU from dbo.NGUOIDUNG\n"
                + "WHERE TENVAITRO = 1";
        try {
            PreparedStatement ptmt = conn.prepareStatement(dangNhapQuanLy);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                nd.setTenDangNhap(rs.getString("TENDANGNHAP"));
                nd.setMatKhauCu(rs.getString("MATKHAU"));
                listND.add(nd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listND;
    }

    public List<NguoiDung> dangNhapGiangVien(NguoiDung nd, Connection conn) {
        List<NguoiDung> listND = new ArrayList<>();
        String dangNhapQuanLy = "SELECT TENDANGNHAP,MATKHAU from dbo.NGUOIDUNG\n"
                + "WHERE TENDANGNHAP =? AND MATKHAU = ? AND TENVAITRO = 2";
        try {
            PreparedStatement ptmt = conn.prepareStatement(dangNhapQuanLy);
            ptmt.setString(1, nd.getTenDangNhap());
            ptmt.setString(2, nd.getMatKhau());
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                //NguoiDung nd = new NguoiDung();
                nd.setTenDangNhap(rs.getString("TENDANGNHAP"));
                nd.setMatKhau(rs.getString("MATKHAU"));
                //nd.setVaiTro(rs.getInt("TENVAITRO"));
                listND.add(nd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listND;
    }

    public List<NguoiDung> listGiangVien(NguoiDung nd, Connection conn) {
        List<NguoiDung> listND = new ArrayList<>();
        String dangNhapQuanLy = "SELECT TENDANGNHAP,MATKHAU from dbo.NGUOIDUNG\n"
                + "WHERE TENVAITRO = 2";
        try {
            PreparedStatement ptmt = conn.prepareStatement(dangNhapQuanLy);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                //NguoiDung nd = new NguoiDung();
                nd.setTenDangNhap(rs.getString("TENDANGNHAP"));
                nd.setMatKhauCu(rs.getString("MATKHAU"));
                //nd.setVaiTro(rs.getInt("TENVAITRO"));
                listND.add(nd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listND;
    }

    public void updateMK(NguoiDung nd, Connection conn) {
        String update_MK = "UPDATE dbo.NGUOIDUNG SET MATKHAU = ? \n"
                + "WHERE TENDANGNHAP = ?  AND TENVAITRO = 0 AND MATKHAU = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(update_MK);

            ptmt.setString(1, nd.getMatKhau());
            ptmt.setString(2, nd.getTenDangNhap());
            ptmt.setString(3, nd.getMatKhauCu());
            int kq = ptmt.executeUpdate();
//            if (kq > 0) {
//                nd.setMatKhau("MATKHAU");
//                nd.setTenDangNhap("TENDANGNHAP");
//                nd.setMatKhau("MATKHAU");
//            }
            //return nd;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //return  null;
    }

    public void updateMKGV(NguoiDung nd, Connection conn) {
        String update_MK = "UPDATE dbo.NGUOIDUNG SET MATKHAU = ? \n"
                + "WHERE TENDANGNHAP = ?  AND TENVAITRO = 2 AND MATKHAU = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(update_MK);
            ptmt.setString(1, nd.getMatKhau());
            ptmt.setString(2, nd.getTenDangNhap());
            ptmt.setString(3, nd.getMatKhauCu());
            int kq = ptmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateMKKT(NguoiDung nd, Connection conn) {
        String update_MK = "UPDATE dbo.NGUOIDUNG SET MATKHAU = ? \n"
                + "WHERE TENDANGNHAP = ?  AND MATKHAU = ?  AND TENVAITRO = 1";
        try {
            PreparedStatement ptmt = conn.prepareStatement(update_MK);
            ptmt.setString(1, nd.getMatKhau());
            ptmt.setString(2, nd.getTenDangNhap());
            ptmt.setString(3, nd.getMatKhauCu());
            int kq = ptmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
