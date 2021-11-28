/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.BuoiHoc;
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
public class BuoiHocDAO {

    public List<BuoiHoc> listBuoiHoc(Connection conn) {
        List<BuoiHoc> list = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call thong_tin_buoi_hoc}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BuoiHoc bh = new BuoiHoc();
                bh.setMaBuoiHoc(rs.getInt("MABUOIHOC"));
                bh.setMaLopHoc(rs.getInt("MALOPHOC"));
                bh.setTenLopHoc(rs.getString("TENLOP"));
                bh.setNgayHoc(rs.getString("ngayhoc"));
                bh.setCaHoc(rs.getString("CAHOC"));
                bh.setGhiChu(rs.getString("GHICHU"));
                list.add(bh);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public BuoiHoc clickTableBH(int ID, Connection conn) {
        String sql = "SELECT MABUOIHOC,MALOPHOC,TENLOP,CONVERT(NVARCHAR(20),NGAYHOC,103)[ngayhoc],BUOIHOC.CAHOC,GHICHU FROM dbo.BUOIHOC\n"
                + "JOIN dbo.LOP ON LOP.MALOP = BUOIHOC.MALOPHOC\n"
                + "WHERE MABUOIHOC= ?\n"
                + "ORDER BY MABUOIHOC DESC";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, ID);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                BuoiHoc bh = new BuoiHoc();
                bh.setMaBuoiHoc(rs.getInt("MABUOIHOC"));
                bh.setMaLopHoc(rs.getInt("MALOPHOC"));
                bh.setTenLopHoc(rs.getString("TENLOP"));
                bh.setNgayHoc(rs.getString("ngayhoc"));
                bh.setCaHoc(rs.getString("CAHOC"));
                bh.setGhiChu(rs.getString("GHICHU"));
                return bh;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean themBH(BuoiHoc bh, Connection conn) {
        String sql = "INSERT INTO dbo.BUOIHOC(NGAYHOC,CAHOC,GHICHU,MALOPHOC)\n"
                + "VALUES(?,?,?,?)";
        PreparedStatement ptmt;
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, bh.getNgayHoc());
            ptmt.setString(2, bh.getCaHoc());
            ptmt.setString(3, bh.getGhiChu());
            ptmt.setInt(4, bh.getMaLopHoc());
            int kq = ptmt.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void suaBH(BuoiHoc bh, Connection conn) {
        String sql = "UPDATE dbo.BUOIHOC SET NGAYHOC=?,CAHOC=?,GHICHU=?,MALOPHOC=?\n"
                + "WHERE MABUOIHOC =?";
        PreparedStatement ptmt;
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, bh.getNgayHoc());
            ptmt.setString(2, bh.getCaHoc());
            ptmt.setString(3, bh.getGhiChu());
            ptmt.setInt(4, bh.getMaLopHoc());
            ptmt.setInt(5, bh.getMaBuoiHoc());
            int kq = ptmt.executeUpdate();
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<BuoiHoc> timKiemBuoiHoc(String ID,Connection conn) {
        List<BuoiHoc> list = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_buoi_hoc_theo_ngay(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BuoiHoc bh = new BuoiHoc();
                bh.setMaBuoiHoc(rs.getInt("MABUOIHOC"));
                bh.setMaLopHoc(rs.getInt("MALOPHOC"));
                bh.setTenLopHoc(rs.getString("TENLOP"));
                bh.setNgayHoc(rs.getString("ngayhoc"));
                bh.setCaHoc(rs.getString("CAHOC"));
                bh.setGhiChu(rs.getString("GHICHU"));
                list.add(bh);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
