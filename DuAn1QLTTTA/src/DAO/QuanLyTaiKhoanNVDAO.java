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

public class QuanLyTaiKhoanNVDAO implements EntityDAO<NguoiDung, Integer> {

    @Override
    public boolean insert(NguoiDung nguoiDung, Connection conn) {
        String them_TKNV = "INSERT INTO dbo.NGUOIDUNG(TENDANGNHAP,MATKHAU,TENVAITRO)\n"
                + "VALUES(?,?,?)";
        //Connection conn = TienIchHoTro.ConnectToSQL.getConnect();

        try {
            PreparedStatement ptmt = conn.prepareStatement(them_TKNV);
            ptmt.setString(1, nguoiDung.getTenDangNhap());
            ptmt.setString(2, nguoiDung.getMatKhau());
            ptmt.setInt(3, nguoiDung.getVaiTro());
            int kq = ptmt.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(NguoiDung nguoiDung, Connection conn) {
        String updateTKNV = "UPDATE dbo.NGUOIDUNG SET TENDANGNHAP=?,MATKHAU=?,TENVAITRO=?\n"
                + "WHERE MANHANVIEN = ?";
        try {
            //Connection conn = TienIchHoTro.ConnectToSQL.getConnect();
            PreparedStatement ptmt = conn.prepareStatement(updateTKNV);
            ptmt.setString(1, nguoiDung.getTenDangNhap());
            ptmt.setString(2, nguoiDung.getMatKhau());
            ptmt.setInt(3, nguoiDung.getVaiTro());
            ptmt.setInt(4, nguoiDung.getMaNhanVien());
            ptmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<NguoiDung> selectAll(Connection conn) {
        List<NguoiDung> listNguoiDung = new ArrayList<>();

        //Connection conn = TienIchHoTro.ConnectToSQL.getConnect();
        try {
            CallableStatement call = conn.prepareCall("{call thong_tin_tai_khoan}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                NguoiDung nguoidung = new NguoiDung();
                nguoidung.setMaNhanVien(rs.getInt("MANHANVIEN"));
                nguoidung.setTenDangNhap(rs.getString("TENDANGNHAP"));
                nguoidung.setMatKhau(rs.getString("MATKHAU"));
                nguoidung.setVaiTro(rs.getInt("TENVAITRO"));
                listNguoiDung.add(nguoidung);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNguoiDung;
    }

    @Override
    public NguoiDung delete(Integer ID, Connection conn) {
//        String xoaTKNV = "DELETE FROM dbo.NGUOIDUNG\n"
//                + "WHERE MANHANVIEN = ?" ;

        try {
            //Connection conn = TienIchHoTro.ConnectToSQL.getConnect();
            CallableStatement call = conn.prepareCall("{call xoa_update_nguoi_dung(?)}");
            call.setInt(1, ID);
            call.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NguoiDung fromTableToText(Integer ID, Connection conn) {
        try {
            //Connection conn = TienIchHoTro.ConnectToSQL.getConnect();
            CallableStatement call = conn.prepareCall("{call tim_kiem_tk_nhan_vien(?)}");
            call.setInt(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                NguoiDung nguoidung = new NguoiDung();
                nguoidung.setTenDangNhap(rs.getString("TENDANGNHAP"));
                nguoidung.setMatKhau(rs.getString("MATKHAU"));
                nguoidung.setVaiTro(rs.getInt("TENVAITRO"));
                return nguoidung;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NguoiDung> search(Integer ID, Connection conn) {
        List<NguoiDung> listNguoiDung = new ArrayList<>();

        //Connection conn = TienIchHoTro.ConnectToSQL.getConnect();
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_tk_nhan_vien(?)}");
            call.setInt(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                NguoiDung nguoidung = new NguoiDung();
                nguoidung.setMaNhanVien(rs.getInt("MANHANVIEN"));
                nguoidung.setTenDangNhap(rs.getString("TENDANGNHAP"));
                nguoidung.setMatKhau(rs.getString("MATKHAU"));
                nguoidung.setVaiTro(rs.getInt("TENVAITRO"));
                listNguoiDung.add(nguoidung);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNguoiDung;
    }

}
