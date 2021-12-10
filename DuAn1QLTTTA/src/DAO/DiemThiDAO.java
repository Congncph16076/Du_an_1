/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.BienLai;
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
public class DiemThiDAO {

    public List<BienLai> listChuaThi(Connection conn) {
        List<BienLai> list = new ArrayList<>();
        String sql = "SELECT MABIENLAI,MALOP,TENHOCVIEN,MADOTTHI,DIEMTHI,DIEMTHANHPHAN,DIEMTONG=(DIEMTHI+DIEMTHANHPHAN) FROM dbo.BIENLAI\n"
                + "JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI\n"
                + "WHERE (DIEMTHI+DIEMTHANHPHAN) is NULL\n"
                +"ORDER BY MABIENLAI desc";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaLop(rs.getInt("MALOP"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setMaDotThi(rs.getInt("MADOTTHI"));
                bl.setDiemThi(rs.getFloat("DIEMTHI"));
                bl.setDiemThanhPhan(rs.getFloat("DIEMTHANHPHAN"));
                bl.setDiemTong(rs.getFloat("DIEMTONG"));
                list.add(bl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public BienLai clickTableChuaThi(int ID, Connection conn) {
        String sql = "SELECT MABIENLAI,MALOP,TENHOCVIEN,MADOTTHI,DIEMTHI,DIEMTHANHPHAN,DIEMTONG=(DIEMTHI+DIEMTHANHPHAN) FROM dbo.BIENLAI\n"
                + "JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI\n"
                + "WHERE (DIEMTHI+DIEMTHANHPHAN) IS  NULL AND MABIENLAI =?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, ID);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaLop(rs.getInt("MALOP"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setMaDotThi(rs.getInt("MADOTTHI"));
                bl.setDiemThi(rs.getFloat("DIEMTHI"));
                bl.setDiemThanhPhan(rs.getFloat("DIEMTHANHPHAN"));
                bl.setDiemTong(rs.getFloat("DIEMTONG"));
                return bl;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<BienLai> listDaThi(Connection conn) {
        List<BienLai> list = new ArrayList<>();
        String sql = "SELECT MABIENLAI,MALOP,TENHOCVIEN,MADOTTHI,DIEMTHI,DIEMTHANHPHAN,DIEMTONG=(DIEMTHI*0.7)+(DIEMTHANHPHAN*0.3) FROM dbo.BIENLAI\n"
                + "JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI\n"
                + "WHERE (DIEMTHI+DIEMTHANHPHAN) is NOT NULL\n"
                +"ORDER BY MABIENLAI desc";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaLop(rs.getInt("MALOP"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setMaDotThi(rs.getInt("MADOTTHI"));
                bl.setDiemThi(rs.getFloat("DIEMTHI"));
                bl.setDiemThanhPhan(rs.getFloat("DIEMTHANHPHAN"));
                bl.setDiemTong(rs.getFloat("DIEMTONG"));
                list.add(bl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public BienLai clickTableDaThi(int ID, Connection conn) {
        String sql = "SELECT MABIENLAI,MALOP,TENHOCVIEN,MADOTTHI,DIEMTHI,DIEMTHANHPHAN,DIEMTONG=(DIEMTHI+DIEMTHANHPHAN) FROM dbo.BIENLAI\n"
                + "JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI\n"
                + "WHERE (DIEMTHI+DIEMTHANHPHAN) IS NOT  NULL AND MABIENLAI =?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, ID);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaLop(rs.getInt("MALOP"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setMaDotThi(rs.getInt("MADOTTHI"));
                bl.setDiemThi(rs.getFloat("DIEMTHI"));
                bl.setDiemThanhPhan(rs.getFloat("DIEMTHANHPHAN"));
                bl.setDiemTong(rs.getFloat("DIEMTONG"));
                return bl;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void suaChuaThi(Connection conn, BienLai bl) {
        String sql = "UPDATE dbo.BIENLAI SET MALOP=?,MADOTTHI=?,DIEMTHI=?,DIEMTHANHPHAN=?,DIEMTONG=(diemthanhphan*0.3)+(diemthi*0.7)\n"
                + "WHERE MABIENLAI = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, String.valueOf(bl.getMaLop()));
            ptmt.setString(2, String.valueOf(bl.getMaDotThi()));
            ptmt.setFloat(3, bl.getDiemThi());
            ptmt.setFloat(4, bl.getDiemThanhPhan());

            ptmt.setInt(6, bl.getMaBienLai());
            int kq = ptmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<BienLai> timKiemChuaThi(String ID, Connection conn) {
        List<BienLai> list = new ArrayList<>();
        String sql = "SELECT MABIENLAI,MALOP,TENHOCVIEN,MADOTTHI,DIEMTHI,DIEMTHANHPHAN,DIEMTONG=(DIEMTHI+DIEMTHANHPHAN) FROM dbo.BIENLAI\n"
                + "JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI\n"
                + "WHERE (DIEMTHI+DIEMTHANHPHAN) IS  NULL AND TENHOCVIEN LIKE ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, "%"+ID+"%");
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaLop(rs.getInt("MALOP"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setMaDotThi(rs.getInt("MADOTTHI"));
                bl.setDiemThi(rs.getFloat("DIEMTHI"));
                bl.setDiemThanhPhan(rs.getFloat("DIEMTHANHPHAN"));
                bl.setDiemTong(rs.getFloat("DIEMTONG"));
                list.add(bl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public List<BienLai> timKiemDaThi(String ID, Connection conn) {
        List<BienLai> list = new ArrayList<>();
        String sql = "SELECT MABIENLAI,MALOP,TENHOCVIEN,MADOTTHI,DIEMTHI,DIEMTHANHPHAN,DIEMTONG=(DIEMTHI+DIEMTHANHPHAN) FROM dbo.BIENLAI\n"
                + "JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI\n"
                + "WHERE (DIEMTHI+DIEMTHANHPHAN) IS NOT NULL AND TENHOCVIEN LIKE ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, "%"+ID+"%");
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaLop(rs.getInt("MALOP"));
                bl.setTenHocVien(rs.getString("TENHOCVIEN"));
                bl.setMaDotThi(rs.getInt("MADOTTHI"));
                bl.setDiemThi(rs.getFloat("DIEMTHI"));
                bl.setDiemThanhPhan(rs.getFloat("DIEMTHANHPHAN"));
                bl.setDiemTong(rs.getFloat("DIEMTONG"));
                list.add(bl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
