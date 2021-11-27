/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.DotThi;
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
public class DotThiDAO implements EntityDAO<DotThi, String> {

    public DotThi hienThiLop(int ID, Connection conn) {
        String sql = "SELECT TENLOP FROM dbo.LOP\n"
                + "WHERE MALOP = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, ID);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                DotThi dt = new DotThi();
                dt.setTenLop(rs.getString("TENLOP"));
                return dt;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public DotThi clickTable(int ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call click_ngay(?)}");
            call.setInt(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                DotThi dt = new DotThi();
                dt.setMaDotThi(rs.getInt("MADOTTHI"));
                dt.setMaLop(rs.getInt("MALOP"));
                dt.setTenLop(rs.getString("TENLOP"));
                dt.setNgayThi(rs.getString("ngaythi"));
                dt.setCaThi(rs.getInt("cathi"));
                dt.setSiso(rs.getInt("SISO"));
                dt.setVang(rs.getInt("VANG"));
                return dt;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(DotThi dt, Connection conn) {
        String sql = "INSERT INTO dbo.DOTTHI(NGAYTHI,CATHI,SISO,VANG,MALOP)\n"
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, dt.getNgayThi());
            ptmt.setInt(2, dt.getCaThi());
            ptmt.setInt(3, dt.getSiso());
            ptmt.setInt(4, dt.getVang());
            ptmt.setInt(5, dt.getMaLop());
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
    public void update(DotThi dt, Connection conn) {
        String sql = "UPDATE dbo.DOTTHI SET NGAYTHI=?,cathi= ?,SISO=?,VANG=?,MALOP=?\n"
                + "WHERE MADOTTHI = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, dt.getNgayThi());
            ptmt.setInt(2, dt.getCaThi());
            ptmt.setInt(3, dt.getSiso());
            ptmt.setInt(4, dt.getVang());
            ptmt.setInt(5, dt.getMaLop());
            ptmt.setInt(6, dt.getMaDotThi());
            int kq = ptmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public DotThi delete(String key, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DotThi> selectAll(Connection conn) {
        List<DotThi> list = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call lay_thong_tin_dot_thi}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                DotThi dt = new DotThi();
                dt.setMaDotThi(rs.getInt("MADOTTHI"));
                dt.setMaLop(rs.getInt("MALOP"));
                dt.setTenLop(rs.getString("TENLOP"));
                dt.setNgayThi(rs.getString("ngaythi"));
                dt.setCaThi(rs.getInt("cathi"));
                dt.setSiso(rs.getInt("SISO"));
                dt.setVang(rs.getInt("VANG"));
                list.add(dt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public DotThi fromTableToText(String key, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DotThi> search(String ID, Connection conn) {
        List<DotThi> list = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_dot_thi(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                DotThi dt = new DotThi();
                dt.setMaDotThi(rs.getInt("MADOTTHI"));
                dt.setMaLop(rs.getInt("MALOP"));
                dt.setTenLop(rs.getString("TENLOP"));
                dt.setNgayThi(rs.getString("ngaythi"));
                dt.setCaThi(rs.getInt("cathi"));
                dt.setSiso(rs.getInt("SISO"));
                dt.setVang(rs.getInt("VANG"));
                list.add(dt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
