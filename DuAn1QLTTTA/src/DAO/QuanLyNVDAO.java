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

/**
 *
 * @author congc
 */
public class QuanLyNVDAO implements EntityDAO<NguoiDung, String> {

//    public GiangVien text(int ID,Connection conn){
//        try {
//            CallableStatement call = conn.prepareCall("{call text_giang_vien(?)}");
//            call.setInt(1, ID);
//            ResultSet rs = call.executeQuery();
//            while (rs.next()) {
//                GiangVien GV = new GiangVien();
//                GV.setTenGiangVien(rs.getString("TENGIANGVIEN"));
//                GV.setGioiTinh(rs.getInt("GIOITINH"));
//                GV.setNgaySinh(rs.getString("ngaysinh"));
//                GV.setDiaChi(rs.getString("DIACHI"));
//                GV.setSDT(rs.getString("SDT"));
//                GV.setEmail(rs.getString("EMAIl"));
//                
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    @Override
    public boolean insert(NguoiDung ND, Connection conn) {
        String themGV = "INSERT INTO dbo.NGUOIDUNG(TENNHANVIEN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL,TENDANGNHAP,MATKHAU,TENVAITRO)\n"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(themGV);
            ptmt.setString(1, ND.getTenNhanVien());
            ptmt.setBoolean(2, ND.isGioiTinh());
            ptmt.setString(3, ND.getNgaySinh());
            ptmt.setString(4, ND.getDiaChi());
            ptmt.setString(5, ND.getSDT());
            ptmt.setString(6, ND.getEmail());
            ptmt.setString(7, ND.getTenDangNhap());
            ptmt.setString(8, ND.getMatKhau());
            ptmt.setInt(9, ND.getVaiTro());
            int kq = ptmt.executeUpdate();
            if (kq > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(NguoiDung ND, Connection conn) {
        String updateGV = "UPDATE dbo.NGUOIDUNG SET TENNHANVIEN = ?,GIOITINH = ?,NGAYSINH = ?,DIACHI = ?,SDT = ?,EMAIL = ?,TENDANGNHAP = ?,MATKHAU = ?,TENVAITRO = ?\n"
                + "WHERE MANHANVIEN = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(updateGV);
            ptmt.setString(1, ND.getTenNhanVien());
            ptmt.setBoolean(2, ND.isGioiTinh());
            ptmt.setString(3, ND.getNgaySinh());
            ptmt.setString(4, ND.getDiaChi());
            ptmt.setString(5, ND.getSDT());
            ptmt.setString(6, ND.getEmail());
            ptmt.setString(7,ND.getTenDangNhap());
            ptmt.setString(8, ND.getMatKhau());
            ptmt.setInt(9, ND.getVaiTro());
            ptmt.setInt(10, ND.getMaNhanVien());
           ptmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public NguoiDung delete(String ID, Connection conn) {
//        String deleteGV = "DELETE FROM dbo.GIANGVIEN\n"
//                + "WHERE MAGIANGVIEN = ?";
        try {
            //Connection conn = TienIchHoTro.ConnectToSQL.getConnect();
            CallableStatement call = conn.prepareCall("{call xoa_update_nguoi_dung(?)}");
            call.setString(1, ID);
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NguoiDung> selectAll(Connection conn) {
        List<NguoiDung> listND = new ArrayList<>();

        try {
            CallableStatement call = conn.prepareCall("{call thong_tin_tai_khoan}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
               NguoiDung nd = new NguoiDung();
               nd.setMaNhanVien(rs.getInt("MANHANVIEN"));
               nd.setTenNhanVien(rs.getString("TENNHANVIEN"));
               nd.setGioiTinh(rs.getBoolean("GIOITINH"));
               nd.setNgaySinh(rs.getString("NGAYSINH"));
               nd.setDiaChi(rs.getString("DIACHI"));
               nd.setSDT(rs.getString("SDT"));
               nd.setEmail(rs.getString("EMAIL"));
               nd.setTenDangNhap(rs.getString("TENDANGNHAP"));
               nd.setMatKhau(rs.getString("MATKHAU"));
                nd.setVaiTro(rs.getInt("TENVAITRO"));
                listND.add(nd);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listND;
    }

    @Override
    public NguoiDung fromTableToText(String ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_tk_nhan_vien(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung();
                nd.setMaNhanVien(rs.getInt("MANHANVIEN"));
               nd.setTenNhanVien(rs.getString("TENNHANVIEN"));
               nd.setGioiTinh(rs.getBoolean("GIOITINH"));
               nd.setNgaySinh(rs.getString("NGAYSINH"));
               nd.setDiaChi(rs.getString("DIACHI"));
               nd.setSDT(rs.getString("SDT"));
               nd.setEmail(rs.getString("EMAIL"));
               nd.setTenDangNhap(rs.getString("TENDANGNHAP"));
               nd.setMatKhau(rs.getString("MATKHAU"));
                nd.setVaiTro(rs.getInt("TENVAITRO"));
                return nd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NguoiDung> search(String ID, Connection conn) {
        List<NguoiDung> listND = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_tk_nhan_vien(?)}");
            call.setString(1, ID);
            
            ResultSet rs = call.executeQuery();

            while (rs.next()) {
                NguoiDung nd = new NguoiDung();
                nd.setMaNhanVien(rs.getInt("MANHANVIEN"));
               nd.setTenNhanVien(rs.getString("TENNHANVIEN"));
               nd.setGioiTinh(rs.getBoolean("GIOITINH"));
               nd.setNgaySinh(rs.getString("NGAYSINH"));
               nd.setDiaChi(rs.getString("DIACHI"));
               nd.setSDT(rs.getString("SDT"));
               nd.setEmail(rs.getString("EMAIL"));
               nd.setTenDangNhap(rs.getString("TENDANGNHAP"));
               nd.setMatKhau(rs.getString("MATKHAU"));
                nd.setVaiTro(rs.getInt("TENVAITRO"));
                listND.add(nd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listND;
    }

}
