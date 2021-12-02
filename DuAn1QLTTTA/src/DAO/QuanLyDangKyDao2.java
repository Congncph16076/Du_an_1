/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.DangKi;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinhn
 */
public class QuanLyDangKyDao2 implements EntityDAO<DangKi, String>{
    @Override
    public boolean insert(DangKi dk, Connection conn) {
        String themDK = "INSERT INTO dbo.Dangki(TENHOCVIEN,NGAYSINH ,GIOITINH ,SDT ,EMAIL ,DIACHI ,TENCAPLOP,TENLOAILOP ,HOCPHI,CAHOC,NGAYDANGKI )\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
            
        
        try {
            PreparedStatement ptmt = conn.prepareStatement(themDK);
            ptmt.setString(1, dk.getTenHocVien());
            ptmt.setString(2, dk.getNgaySinh());
            ptmt.setInt(3, dk.getGioiTinh());
            ptmt.setString(4, dk.getSdt());
            ptmt.setString(5, dk.getEmail());
            ptmt.setString(6, dk.getDiaChi());            
            ptmt.setString(7, dk.getTenCapLop());
            ptmt.setString(8, dk.getTenLoaiLop());
            ptmt.setFloat(9, dk.getHocPhi());
            ptmt.setString(10, dk.getCaHoc());
            ptmt.setString(11, dk.getNgayDangKi());
            
            int kq = ptmt.executeUpdate();
            if (kq > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;   
    }
    
    public boolean insert2(DangKi dk, Connection conn) {
        String themDK = "INSERT INTO dbo.bienlai(madangki)\n"
                + "VALUES(?)";
        
            
        
        try {
            PreparedStatement ptmt = conn.prepareStatement(themDK);
            ptmt.setInt(1, dk.getMaDangKi());
            
            
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
    public void update(DangKi dk, Connection conn) {
        String updateDK = "UPDATE dbo.dangki SET TENhocvien = ?,GIOITINH = ?"
                + ",NGAYSINH = ?,DIACHI = ?,SDT = ?,EMAIL = ?,TENcaplop = ?,TENLOAILOP = ?,HOCPHI = ?,cahoc=?, NGAYDANGKI=? \n"
                + "WHERE MADANGKI = ?";

        try {
            PreparedStatement ptmt = conn.prepareStatement(updateDK);
            ptmt.setString(1, dk.getTenHocVien());
            ptmt.setInt(2, dk.getGioiTinh());
            ptmt.setString(3, dk.getNgaySinh());
            ptmt.setString(4, dk.getDiaChi());
            ptmt.setString(5, dk.getSdt());
            ptmt.setString(6, dk.getEmail());
            ptmt.setString(7, dk.getTenCapLop());
            ptmt.setString(8, dk.getTenLoaiLop());
            ptmt.setFloat(9, dk.getHocPhi());
            ptmt.setString(10, dk.getCaHoc());
            ptmt.setString(11, dk.getNgayDangKi());
            ptmt.setInt(12, dk.getMaDangKi());
            ptmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override 
    public DangKi delete(String ID, Connection conn) {
         try {
            //Connection conn = TienIchHoTro.ConnectToSQL.getConnect();
            CallableStatement call = conn.prepareCall("{call xoa_ban_dang_ki(?)}");
            call.setString(1, ID);
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DangKi> selectAll(Connection conn) {
        List<DangKi> listDK = new ArrayList<>();

        try {
            CallableStatement call = conn.prepareCall("{call thong_tin_dang_ki}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
               DangKi dk = new DangKi();
               dk.setMaDangKi(rs.getInt("MAdangki"));
               dk.setTenHocVien(rs.getString("TENhocvien"));
               dk.setGioiTinh(rs.getInt("GIOITINH"));
               dk.setNgaySinh(rs.getString("NGAYSINH"));
               dk.setDiaChi(rs.getString("DIACHI"));
               dk.setSdt(rs.getString("SDT"));
               dk.setEmail(rs.getString("EMAIL"));
               dk.setTenLoaiLop(rs.getString("TENloailop"));
               dk.setTenCapLop(rs.getString("tencaplop"));
               dk.setHocPhi(rs.getFloat("hocphi"));
               dk.setCaHoc(rs.getString("cahoc"));
               //dk.setNgayNhapHoc(rs.getString("ngaynhaphoc"));
               dk.setNgayDangKi(rs.getString("ngaydangki"));
               dk.setMaHocVien(rs.getInt("mahocvien"));
                listDK.add(dk);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDK;
    }

    @Override
    public DangKi fromTableToText(String ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_ban_dang_ki_theo_mdk(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                DangKi dk = new DangKi();
                dk.setMaDangKi(rs.getInt("MAdangki"));
                dk.setTenHocVien(rs.getString("TENhocvien"));
                dk.setGioiTinh(rs.getInt("GIOITINH"));
                dk.setNgaySinh(rs.getString("NGAYSINH"));
                dk.setDiaChi(rs.getString("DIACHI"));
                dk.setSdt(rs.getString("SDT"));
                dk.setEmail(rs.getString("EMAIL"));
                dk.setTenLoaiLop(rs.getString("TENloailop"));
                dk.setTenCapLop(rs.getString("tencaplop"));
                dk.setHocPhi(rs.getFloat("hocphi"));
                dk.setCaHoc(rs.getString("cahoc"));
                //dk.setNgayNhapHoc(rs.getString("ngaynhaphoc"));
                dk.setNgayDangKi(rs.getString("ngaydangki"));
                dk.setMaHocVien(rs.getInt("mahocvien"));
                return dk;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DangKi> search(String ID, Connection conn) {
        List<DangKi> listDK = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_ban_dang_ki_theo_tendangki(?)}");
            call.setString(1, "%"+ID+"%");
            ResultSet rs = call.executeQuery();

            while (rs.next()) {
                DangKi dk = new DangKi();
                dk.setMaDangKi(rs.getInt("MAdangki"));
                dk.setTenHocVien(rs.getString("TENhocvien"));
                dk.setGioiTinh(rs.getInt("GIOITINH"));
                dk.setNgaySinh(rs.getString("NGAYSINH"));
                dk.setDiaChi(rs.getString("DIACHI"));
                dk.setSdt(rs.getString("SDT"));
                dk.setEmail(rs.getString("EMAIL"));
                dk.setTenLoaiLop(rs.getString("TENloailop"));
                dk.setTenCapLop(rs.getString("tencaplop"));
                dk.setHocPhi(rs.getFloat("hocphi"));
                dk.setCaHoc(rs.getString("cahoc"));
                //dk.setNgayNhapHoc(rs.getString("ngaynhaphoc"));
                dk.setNgayDangKi(rs.getString("ngaydangki"));
                dk.setMaHocVien(rs.getInt("mahocvien"));
                listDK.add(dk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDK;
    }
}
