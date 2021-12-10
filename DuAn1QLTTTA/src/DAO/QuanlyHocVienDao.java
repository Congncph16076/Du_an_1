/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.DangKi;
import Entity.HocVien;
import Entity.Lop;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinhn
 */
public class QuanlyHocVienDao implements EntityDAO<HocVien, String> {

    @Override
    public boolean insert(HocVien hv, Connection conn) {
        String themHV = "INSERT  INTO dbo.HOCVIEN(TENHOCVIEN,MALOP,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL,HOCPHINO)\n"
                + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(themHV);
            ptmt.setString(1, hv.getTenHocVien());
            ptmt.setInt(2, hv.getMaLop());
            ptmt.setInt(3, hv.getGioiTinh());
            ptmt.setString(4, hv.getNgaySinh());
            ptmt.setString(5, hv.getSdt());
            ptmt.setString(6, hv.getDiaChi());
            ptmt.setString(7, hv.getEmail());
            ptmt.setFloat(8, hv.getHocPhiNo());
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
    public void update(HocVien hv, Connection conn) {
        String UpdateHV = "UPDATE dbo.HOCVIEN SET TENHOCVIEN = ?,MALOP = ?,GIOITINH = ?,NGAYSINH = ?,SDT =?,DIACHI =?,EMAIL = ?, HOCPHINO = ?\n"
                + "WHERE MAHOCVIEN = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(UpdateHV);
            ptmt.setString(1, hv.getTenHocVien());
            ptmt.setInt(2, hv.getMaLop());
            ptmt.setInt(3, hv.getGioiTinh());
            ptmt.setString(4, hv.getNgaySinh());
            ptmt.setString(5, hv.getSdt());
            ptmt.setString(6, hv.getDiaChi());
            ptmt.setString(7, hv.getEmail());
            ptmt.setFloat(8, hv.getHocPhiNo());
            ptmt.setInt(9, hv.getMaHocVien());
            ptmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<HocVien> selectAll(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HocVien fromTableToText(String key, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HocVien> search(String ID, Connection conn) {
        List<HocVien> listHV = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call tim_kiem_hoc_vien_theo_ten(?)}");
            call.setString(1, "%" + ID + "%");
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
                listHV.add(hocVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHV;
    }
    
    public boolean chuyenLop(int malopmoi,int malopcu,int mahv , Connection conn) {
        
        try {
            
            CallableStatement call = conn.prepareCall("{call chuyen_lop(?,?,?)}");
            call.setInt(1,  malopmoi);
            call.setInt(2,  malopcu);
            call.setInt(3,  mahv);
            
            
           int rs =call.executeUpdate();
            if (rs>0) {
                return true;
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public HocVien delete(String key, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
