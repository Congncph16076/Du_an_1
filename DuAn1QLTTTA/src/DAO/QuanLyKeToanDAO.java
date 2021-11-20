/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.KeToan;
import java.sql.Connection;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author congc
 */
public class QuanLyKeToanDAO implements EntityDAO<KeToan, String> {

    @Override
    public boolean insert(KeToan kt, Connection conn) {
        String themKT = "INSERT INTO dbo.KETOAN(TENKETOAN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL)\n"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(themKT);

            ptmt.setString(1, kt.getTenKeToan());
            ptmt.setInt(2, kt.getGioiTinh());
            ptmt.setString(3, kt.getNgaySinh());
            ptmt.setString(4, kt.getDiaChi());
            ptmt.setString(5, kt.getSDT());
            ptmt.setString(6, kt.getEmail());
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
    public void update(KeToan kt, Connection conn) {
        String updateKT = "UPDATE dbo.KETOAN SET TENKETOAN=?,GIOITINH=?,NGAYSINH=?,DIACHI=?,SDT=?,EMAIL=?\n"
                + "WHERE MAKETOAN = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(updateKT);
            ptmt.setString(1, kt.getTenKeToan());
            ptmt.setInt(2, kt.getGioiTinh());
            ptmt.setString(3, kt.getNgaySinh());
            ptmt.setString(4, kt.getDiaChi());
            ptmt.setString(5, kt.getSDT());
            ptmt.setString(6, kt.getEmail());
            ptmt.setInt(7, kt.getMaKeToan());
            int kq = ptmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public KeToan delete(String ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call xoa_update_Ke_Toan(?)}");
            call.setString(1, ID);
             call.executeUpdate();
//            while (rs.next()) {
//                KeToan kt = new KeToan();
//                kt.setMaKeToan(rs.getInt("MAKETOAN"));
//                kt.setTenKeToan(rs.getString("TENKETOAN"));
//                kt.setGioiTinh(rs.getInt("GIOITINH"));
//                kt.setNgaySinh(rs.getString("NGAYSINH"));
//                kt.setDiaChi(rs.getString("DIACHI"));
//                kt.setSDT(rs.getString("SDT"));
//                kt.setEmail(rs.getString("EMAIL"));
//                //return kt;
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<KeToan> selectAll(Connection conn) {
        List<KeToan> listKT = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call thong_tin_ke_toan}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                KeToan kt = new KeToan();
                kt.setMaKeToan(rs.getInt("MAKETOAN"));
                kt.setTenKeToan(rs.getString("TENKETOAN"));
                kt.setGioiTinh(rs.getInt("GIOITINH"));
                kt.setNgaySinh(rs.getString("ngaysinh"));
                kt.setDiaChi(rs.getString("DIACHI"));
                kt.setSDT(rs.getString("SDT"));
                kt.setEmail(rs.getString("EMAIL"));
                listKT.add(kt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listKT;
    }

    @Override
    public KeToan fromTableToText(String ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_nhan_vien_ke_toan(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                KeToan kt = new KeToan();
                kt.setMaKeToan(rs.getInt("MAKETOAN"));
                kt.setTenKeToan(rs.getString("TENKETOAN"));
                kt.setGioiTinh(rs.getInt("GIOITINH"));
                kt.setNgaySinh(rs.getString("NGAYSINH"));
                kt.setDiaChi(rs.getString("DIACHI"));
                kt.setSDT(rs.getString("SDT"));
                kt.setEmail(rs.getString("EMAIL"));
                return kt;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<KeToan> search(String ID, Connection conn) {
        List<KeToan> listKT = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_nhan_vien_ke_toan(?)}");
             call.setString(1, "%"+ID+"%");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                KeToan kt = new KeToan();
                kt.setMaKeToan(rs.getInt("MAKETOAN"));
                kt.setTenKeToan(rs.getString("TENKETOAN"));
                kt.setGioiTinh(rs.getInt("GIOITINH"));
                kt.setNgaySinh(rs.getString("NGAYSINH"));
                kt.setDiaChi(rs.getString("DIACHI"));
                kt.setSDT(rs.getString("SDT"));
                kt.setEmail(rs.getString("EMAIL"));
                listKT.add(kt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listKT;
    }

}
