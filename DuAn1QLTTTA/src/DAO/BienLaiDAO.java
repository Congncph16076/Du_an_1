/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.BienLai;
import java.sql.Connection;
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

/**
 *
 * @author congc
 */
public class BienLaiDAO {

    public List<BienLai> listHVCu(Connection conn) {
        List<BienLai> list = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call thong_tin_bien_lai_hvcu}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaDangKi(rs.getInt("MADANGKI"));
                bl.setMaHocVien(rs.getInt("MAHOCVIEN"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setHocPhi(rs.getFloat("HOCPHI"));
                bl.setHocPhiNo(rs.getFloat("HOCPHINO"));
                bl.setThanhTien(rs.getFloat("THANHTIEN"));
                bl.setMaNhanVien(rs.getInt("MANHANVIEN"));
                bl.setTenNhanVien(rs.getString("TENNHANVIEN"));
                bl.setNgayThuTien(rs.getString("ngaythutien"));
                list.add(bl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public BienLai clickTableHVCu(int ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_bien_laiCu(?)}");
            call.setInt(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaDangKi(rs.getInt("MADANGKI"));
                bl.setMaHocVien(rs.getInt("MAHOCVIEN"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setHocPhi(rs.getFloat("HOCPHI"));
                bl.setHocPhiNo(rs.getFloat("HOCPHINO"));
                bl.setThanhTien(rs.getFloat("THANHTIEN"));
                bl.setMaNhanVien(rs.getInt("MANHANVIEN"));
                bl.setTenNhanVien(rs.getString("TENNHANVIEN"));
                bl.setNgayThuTien(rs.getString("ngaythutien"));
                return bl;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean themHVCu(BienLai bl, Connection conn) {
        String sql = "INSERT INTO dbo.BIENLAI(THANHTIEN,MAHOCVIEN,MANHANVIEN,MADANGKI,NGAYTHUTIEN)\n"
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setFloat(1, bl.getThanhTien());
            ptmt.setInt(2, bl.getMaHocVien());
            ptmt.setInt(3, bl.getMaNhanVien());
            ptmt.setInt(4, bl.getMaDangKi());
            ptmt.setString(5, bl.getNgayThuTien());
            int kq = ptmt.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void suaHVCu(BienLai bl, Connection conn) {
        String sql = "UPDATE dbo.BIENLAI SET THANHTIEN =? ,MAHOCVIEN =?,MANHANVIEN =?,MADANGKI =?,NGAYTHUTIEN =?\n"
                + "WHERE MABIENLAI = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setFloat(1, bl.getThanhTien());
            ptmt.setInt(2, bl.getMaHocVien());
            ptmt.setInt(3, bl.getMaNhanVien());
            ptmt.setInt(4, bl.getMaDangKi());
            ptmt.setString(5, bl.getNgayThuTien());
            ptmt.setInt(6, bl.getMaBienLai());
            int kq = ptmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<BienLai> timBienLaiCu(String ID, Connection conn) {
        List<BienLai> list = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_bien_laiCu_theo_Ngay(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaDangKi(rs.getInt("MADANGKI"));
                bl.setMaHocVien(rs.getInt("MAHOCVIEN"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setHocPhi(rs.getFloat("HOCPHI"));
                bl.setHocPhiNo(rs.getFloat("HOCPHINO"));
                bl.setThanhTien(rs.getFloat("THANHTIEN"));
                bl.setMaNhanVien(rs.getInt("MANHANVIEN"));
                bl.setTenNhanVien(rs.getString("TENNHANVIEN"));
                bl.setNgayThuTien(rs.getString("ngaythutien"));
                list.add(bl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<BienLai> listHVMoi(Connection conn) {
        List<BienLai> list = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call thong_tin_bien_lai_moi}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaDangKi(rs.getInt("MADANGKI"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setHocPhi(rs.getFloat("HOCPHI"));
                bl.setHocPhiNo(rs.getFloat("HOCPHINO"));
                bl.setThanhTien(rs.getFloat("THANHTIEN"));
                bl.setNgayThuTien(rs.getString("ngaythutien"));
                bl.setMaNhanVien(rs.getInt("MANHANVIEN"));
                bl.setTenNhanVien(rs.getString("TENNHANVIEN"));
                list.add(bl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public BienLai clickTableMoi(int ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call click_table_HV_moi(?)}");
            call.setInt(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaDangKi(rs.getInt("MADANGKI"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setHocPhi(rs.getFloat("HOCPHI"));
                bl.setHocPhiNo(rs.getFloat("HOCPHINO"));
                bl.setThanhTien(rs.getFloat("THANHTIEN"));
                bl.setNgayThuTien(rs.getString("ngaythutien"));
                bl.setMaNhanVien(rs.getInt("MANHANVIEN"));
                bl.setTenNhanVien(rs.getString("TENNHANVIEN"));
                return bl;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean themHVMoi(BienLai bl, Connection conn) {
        String sql = "INSERT INTO dbo.BIENLAI(THANHTIEN,MANHANVIEN,MADANGKI,NGAYTHUTIEN)\n"
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setFloat(1, bl.getThanhTien());
            ptmt.setInt(2, bl.getMaNhanVien());
            ptmt.setInt(3, bl.getMaDangKi());
            ptmt.setString(4, bl.getNgayThuTien());
            int kq = ptmt.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void suaHVMoi(BienLai bl, Connection conn) {
        String sql = "UPDATE dbo.BIENLAI SET THANHTIEN = ?,MANHANVIEN = ?,MADANGKI = ?,NGAYTHUTIEN =?\n"
                + "WHERE MABIENLAI =?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setFloat(1, bl.getThanhTien());
            ptmt.setInt(2, bl.getMaNhanVien());
            ptmt.setInt(3, bl.getMaDangKi());
            ptmt.setString(4, bl.getNgayThuTien());
            ptmt.setInt(5, bl.getMaBienLai());
            int kq = ptmt.executeUpdate();
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public   List<BienLai> timBienLaiMoi(String ID , Connection conn){
        List<BienLai> list = new ArrayList<>();
        CallableStatement call;
        try {
            call = conn.prepareCall("{call tim_Kiem_HV_Moi(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaDangKi(rs.getInt("MADANGKI"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setHocPhi(rs.getFloat("HOCPHI"));
                bl.setHocPhiNo(rs.getFloat("HOCPHINO"));
                bl.setThanhTien(rs.getFloat("THANHTIEN"));
                bl.setNgayThuTien(rs.getString("ngaythutien"));
                bl.setMaNhanVien(rs.getInt("MANHANVIEN"));
                bl.setTenNhanVien(rs.getString("TENNHANVIEN"));
                list.add(bl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  list;
        
    }

}