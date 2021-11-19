/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.GiangVien;
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
public class QuanLyGiangVienDAO implements EntityDAO<GiangVien, String> {

    @Override
    public boolean insert(GiangVien GV, Connection conn) {
        String themGV = "INSERT INTO dbo.GIANGVIEN(TENGIANGVIEN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL)\n"
                + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ptmt = conn.prepareStatement(themGV);
            ptmt.setString(1, GV.getTenGiangVien());
            ptmt.setInt(2, GV.getGioiTinh());
            ptmt.setString(3, GV.getNgaySinh());
            ptmt.setString(4, GV.getDiaChi());
            ptmt.setString(5, GV.getSDT());
            ptmt.setString(6, GV.getEmail());
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
    public void update(GiangVien GV, Connection conn) {
        String updateGV = "UPDATE dbo.GIANGVIEN SET TENGIANGVIEN= ?,GIOITINH= ?,NGAYSINH= ?,DIACHI= ?,SDT= ?,EMAIL= ?\n"
                + "WHERE MAGIANGVIEN = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(updateGV);
            ptmt.setString(1, GV.getTenGiangVien());
            ptmt.setInt(2, GV.getGioiTinh());
            ptmt.setString(3, GV.getNgaySinh());
            ptmt.setString(4, GV.getDiaChi());
            ptmt.setString(5, GV.getSDT());
            ptmt.setString(6, GV.getEmail());
            ptmt.setInt(7, GV.getMaGiangVien());
            int kq = ptmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public GiangVien delete(String ID, Connection conn) {
        String deleteGV = "DELETE FROM dbo.GIANGVIEN\n"
                + "WHERE MAGIANGVIEN = ?";
        try {
            //Connection conn = TienIchHoTro.ConnectToSQL.getConnect();
            PreparedStatement ptmt = conn.prepareStatement(deleteGV);
            ptmt.setString(1, ID);
            ptmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GiangVien> selectAll(Connection conn) {
        List<GiangVien> listGV = new ArrayList<>();

        try {
            CallableStatement call = conn.prepareCall("{call thong_tin_giang_vien}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                GiangVien GV = new GiangVien();
                GV.setMaGiangVien(rs.getInt("MAGIANGVIEN"));
                GV.setTenGiangVien(rs.getString("TENGIANGVIEN"));
                GV.setGioiTinh(rs.getInt("GIOITINH"));
                GV.setNgaySinh(rs.getString("ngaysinh"));
                GV.setDiaChi(rs.getString("DIACHI"));
                GV.setSDT(rs.getString("SDT"));
                GV.setEmail(rs.getString("EMAIl"));
                listGV.add(GV);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGV;
    }

    @Override
    public GiangVien fromTableToText(String ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_giang_vien(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                GiangVien GV = new GiangVien();
                GV.setTenGiangVien(rs.getString("TENGIANGVIEN"));
                GV.setGioiTinh(rs.getInt("GIOITINH"));
                GV.setNgaySinh(rs.getString("ngaysinh"));
                GV.setDiaChi(rs.getString("DIACHI"));
                GV.setSDT(rs.getString("SDT"));
                GV.setEmail(rs.getString("EMAIl"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GiangVien> search(String ID, Connection conn) {
        List<GiangVien> listGV = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_giang_vien(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();

            while (rs.next()) {
                GiangVien GV = new GiangVien();
                GV.setMaGiangVien(rs.getInt("MAGIANGVIEN"));
                GV.setTenGiangVien(rs.getString("TENGIANGVIEN"));
                GV.setGioiTinh(rs.getInt("GIOITINH"));
                GV.setNgaySinh(rs.getString("ngaysinh"));
                GV.setDiaChi(rs.getString("DIACHI"));
                GV.setSDT(rs.getString("SDT"));
                GV.setEmail(rs.getString("EMAIl"));
                listGV.add(GV);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  listGV;
    }

}
