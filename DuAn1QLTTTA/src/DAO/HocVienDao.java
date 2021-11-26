/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.HocVien;
import Entity.Lop;
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
public class HocVienDao {
    public List<HocVien> listHV(Connection conn) {
        List<HocVien> lstHocVien = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call thong_tin_sv}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                HocVien hocVien = new HocVien();
                hocVien.setMaHocVien(rs.getInt("MAHOCVIEN"));
                hocVien.setTenHocVien(rs.getString("TENHOCVIEN"));
                hocVien.setMaLop(rs.getInt("MALOP"));
                hocVien.setTenLop(rs.getString("TENlOP"));
                hocVien.setGioiTinh(rs.getInt("GIOITINH"));
                hocVien.setNgaySinh(rs.getString("Ngaysinh"));
                hocVien.setSdt(rs.getString("Sdt"));
                hocVien.setEmail(rs.getString("email"));
                hocVien.setDiaChi(rs.getString("diachi"));
                hocVien.setHocPhiNo(rs.getFloat("hocphino"));
                
                lstHocVien.add(hocVien);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstHocVien;
    }
    
    public HocVien clickTable(String ID, Connection conn) {
        CallableStatement call;
        try {
            call = conn.prepareCall("{call tim_kiem_hoc_vien_theo_ten_va_ten_lop(?)}");
            call.setString(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                HocVien hocVien = new HocVien();
                hocVien.setMaHocVien(rs.getInt("MAHOCVIEN"));
                hocVien.setTenHocVien(rs.getString("TENHOCVIEN"));
                hocVien.setMaLop(rs.getInt("MALOP"));
                hocVien.setTenLop(rs.getString("TENlOP"));
                hocVien.setGioiTinh(rs.getInt("GIOITINH"));
                hocVien.setNgaySinh(rs.getString("Ngaysinh"));
                hocVien.setSdt(rs.getString("Sdt"));
                hocVien.setEmail(rs.getString("email"));
                hocVien.setDiaChi(rs.getString("diachi"));
                hocVien.setHocPhiNo(rs.getFloat("hocphino"));
                
                return hocVien;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    
}
