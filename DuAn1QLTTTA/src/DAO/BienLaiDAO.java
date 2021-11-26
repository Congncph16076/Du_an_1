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
    public  List<BienLai> listHVCu(BienLai bienLai, Connection  conn){
       List<BienLai> list = new ArrayList<>();
        try {
            CallableStatement  call = conn.prepareCall("{call thong_tin_bien_lai_hvcu}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {                
                BienLai bl = new BienLai();
                bl.setMaBienLai(rs.getInt("MABIENLAI"));
                bl.setMaHocVien(rs.getInt("BIENLAI.MAHOCVIEN"));
                bl.setTenHocVien(rs.getString("HOCVIEN.TENHOCVIEN"));
                bl.setHocPhi(rs.getFloat("DANGKI.HOCPHI"));
                bl.setHocPhiNo(rs.getFloat("DANGKI.HOCPHINO"));
                bl.setThanhTien(rs.getFloat("THANHTIEN"));
                bl.setMaLop(rs.getInt("dbo.HOCVIEN.MALOP"));
                bl.setTenLop(rs.getString("dbo.lop.TENLOP"));
                bl.setMaNhanVien(rs.getInt("dbo.NGUOIDUNG.MANHANVIEN"));
                bl.setTenNhanVien(rs.getString("dbo.NGUOIDUNG.TENNHANVIEN"));
                bl.setNgayThuTien(rs.getString("ngaythutien"));
               list.add(bl);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return  list;
    }
}
