/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.BienLai;
import Entity.DiemDanh;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiemDanhDAO {

    public List<DiemDanh> listDD(Connection conn) {
        List<DiemDanh> list = new ArrayList<>();
        try {
//            MADIEMDANH,TRANGTHAI
//	,CONVERT(NVARCHAR(20),BUOIHOC.NGAYHOC,103)[ngayhoc],BUOIHOC.CAHOC 
//	,dbo.DIEMDANH.GHICHU,dbo.DIEMDANH.MABUOIHOC,BIENLAI.MABIENLAI
            CallableStatement call = conn.prepareCall("{call thong_tin_diem_danh}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                DiemDanh dd = new DiemDanh();
                dd.setMaDiemDanh(rs.getInt("MADIEMDANH"));
                dd.setTrangThai(rs.getBoolean("TRANGTHAI"));
                dd.setNgayHoc(rs.getString("ngayhoc"));
                dd.setCaHoc(rs.getString("CAHOC"));
                dd.setGhiChu(rs.getString("GHICHU"));
                dd.setMaBuoiHoc(rs.getInt("MABUOIHOC"));
                dd.setMaBienLai(rs.getInt("MABIENLAI"));
                list.add(dd);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public DiemDanh clickTable(int ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call clickTableDiemDanh(?)}");
            call.setInt(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                DiemDanh dd = new DiemDanh();
                dd.setMaDiemDanh(rs.getInt("MADIEMDANH"));
                dd.setTrangThai(rs.getBoolean("TRANGTHAI"));
                dd.setNgayHoc(rs.getString("ngayhoc"));
                dd.setCaHoc(rs.getString("CAHOC"));
                dd.setGhiChu(rs.getString("GHICHU"));
                dd.setMaBuoiHoc(rs.getInt("MABUOIHOC"));
                dd.setMaBienLai(rs.getInt("MABIENLAI"));
                return dd;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean themDiemDanh(DiemDanh dd, Connection conn) {
        String sql = "INSERT INTO dbo.DIEMDANH(TRANGTHAI,GHICHU,MABUOIHOC,MABIENLAI)\n"
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setBoolean(1, dd.isTrangThai());
            ptmt.setString(2, dd.getGhiChu());
            ptmt.setInt(3, dd.getMaBuoiHoc());
            ptmt.setInt(4, dd.getMaBienLai());
            int kq = ptmt.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void suaDiemDanh(DiemDanh dd, Connection conn) {
        String sql = "UPDATE dbo.DIEMDANH SET TRANGTHAI=?,GHICHU=?,MABUOIHOC=?,MABIENLAI=?\n"
                + "WHERE MADIEMDANH = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setBoolean(1, dd.isTrangThai());
            ptmt.setString(2, dd.getGhiChu());
            ptmt.setInt(3, dd.getMaBuoiHoc());
            ptmt.setInt(4, dd.getMaBienLai());
            ptmt.setInt(5, dd.getMaDiemDanh());
            int kq = ptmt.executeUpdate();
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<DiemDanh> timKiemDD(String ID,Connection conn) {
        List<DiemDanh> list = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_diem_danh_theo_ten_hoc_vien(?)}");
            call.setString(1, "%"+ID+"%");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                DiemDanh dd = new DiemDanh();
                dd.setMaDiemDanh(rs.getInt("MADIEMDANH"));
                dd.setTrangThai(rs.getBoolean("TRANGTHAI"));
                dd.setTenHocVien(rs.getString("TENHOCVIEN"));
                dd.setTenLop(rs.getString("TENLOP"));
                dd.setNgayHoc(rs.getString("ngayhoc"));
                dd.setCaHoc(rs.getString("CAHOC"));
                dd.setGhiChu(rs.getString("GHICHU"));
                dd.setMaBuoiHoc(rs.getInt("MABUOIHOC"));
                dd.setMaBienLai(rs.getInt("MABIENLAI"));
                list.add(dd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
