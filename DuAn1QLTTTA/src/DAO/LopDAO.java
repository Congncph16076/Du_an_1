package DAO;

import Entity.Lop;
import Entity.NguoiDung;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LopDAO {

    public List<Lop> cbcLoaiLop(Connection conn) {
        String list = "SELECT TENLOAILOP FROM dbo.LOAILOP";
        List<Lop> listLoaiL = new ArrayList<>();
        try {
            PreparedStatement ptmt = conn.prepareStatement(list);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Lop lop = new Lop();
                lop.setTenLoaiLop(rs.getString("TENLOAILOP"));
                listLoaiL.add(lop);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listLoaiL;
    }

    public List<Lop> cbcCapLop(Connection conn) {
        String list = "SELECT TENCAPLOP FROM dbo.CAPLOP";
        List<Lop> listLoaiL = new ArrayList<>();
        try {
            PreparedStatement ptmt = conn.prepareStatement(list);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Lop lop = new Lop();
                lop.setTenCapLop(rs.getString("TENCAPLOP"));
                listLoaiL.add(lop);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listLoaiL;
    }

    public List<Lop> listLop(Connection conn) {

        List<Lop> listLoaiL = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("{call thong_tin_lop}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Lop lop = new Lop();
                lop.setMaLop(rs.getInt("MALOP"));
                lop.setTenLop(rs.getString("TENLOP"));
                lop.setMaNhanVien(rs.getInt("MANHANVIEN"));
                lop.setTenNhanVien(rs.getString("TENNHANVIEN"));
                lop.setHocPhi(rs.getFloat("HOCPHI"));
                lop.setCaHoc(rs.getString("CAHOC"));
                lop.setSiso(rs.getInt("SISO"));
                lop.setTenLoaiLop(rs.getString("TENLOAILOP"));
                lop.setTenCapLop(rs.getString("TENCAPLOP"));
                lop.setNgayNhapHoc(rs.getString("NGAYNHAPHOC"));
                lop.setNgayKeThuc(rs.getString("NGAYKETTHUC"));
                listLoaiL.add(lop);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listLoaiL;
    }

    public Lop clickTable(int ID, Connection conn) {
        CallableStatement call;
        try {
            call = conn.prepareCall("{call click_table_lop(?)}");
            call.setInt(1, ID);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Lop lop = new Lop();
                lop.setMaLop(rs.getInt("MALOP"));
                lop.setTenLop(rs.getString("TENLOP"));
                lop.setMaNhanVien(rs.getInt("MANHANVIEN"));
                lop.setTenNhanVien(rs.getString("TENNHANVIEN"));
                lop.setHocPhi(rs.getFloat("HOCPHI"));
                lop.setCaHoc(rs.getString("CAHOC"));
                lop.setSiso(rs.getInt("SISO"));
//                lop.setMaLoaiLop(rs.getInt("MALOAILOP"));
//                lop.setMaCapLop(rs.getInt("MACAPLOP"));
                lop.setTenLoaiLop(rs.getString("TENLOAILOP"));
                lop.setTenCapLop(rs.getString("TENCAPLOP"));
                lop.setNgayNhapHoc(rs.getString("NGAYNHAPHOC"));
                lop.setNgayKeThuc(rs.getString("NGAYKETTHUC"));
                return lop;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Lop> tblLoaiLop(Connection conn) {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.LOAILOP";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Lop l = new Lop();
                l.setMaLoaiLop(rs.getInt("MALOAILOP"));
                l.setTenLoaiLop(rs.getString("TENLOAILOP"));
                list.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Lop> tblCapLop(Connection conn) {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.CAPLOP";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Lop l = new Lop();
                l.setMaCapLop(rs.getInt("MACAPLOP"));
                l.setTenCapLop(rs.getString("TENCAPLOP"));
                list.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Lop clickLoaiLop(int ID, Connection conn) {
        String sql = "SELECT * FROM dbo.LOAILOP\n"
                + "WHERE MALOAILOP = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, ID);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Lop l = new Lop();
                l.setTenLoaiLop(rs.getString("TENLOAILOP"));
                return l;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Lop clickCapLop(int ID, Connection conn) {
        String sql = "SELECT* FROM dbo.CAPLOP\n"
                + "WHERE MACAPLOP = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, ID);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Lop l = new Lop();
                l.setTenCapLop(rs.getString("TENCAPLOP"));
                return l;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertLop(Lop lop, Connection conn) {
        String sql = "INSERT INTO dbo.LOP(TENLOP,SISO,CAHOC,HOCPHI,NGAYNHAPHOC,NGAYKETTHUC,MALOAILOP,MACAPLOP,MANHANVIEN)\n"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, lop.getTenLop());
            ptmt.setInt(2, lop.getSiso());
            ptmt.setString(3, lop.getCaHoc());
            ptmt.setFloat(4, lop.getHocPhi());
            ptmt.setString(5, lop.getNgayNhapHoc());
            ptmt.setString(6, lop.getNgayKeThuc());
            ptmt.setInt(7, lop.getMaLoaiLop());
            ptmt.setInt(8, lop.getMaCapLop());
            ptmt.setInt(9, lop.getMaNhanVien());
            int kq = ptmt.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean insertLoaiLop(Lop lop, Connection conn) {
        String sql = "INSERT INTO dbo.LOAILOP(TENLOAILOP)\n"
                + "VALUES(?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, lop.getTenLoaiLop());
            int kq = ptmt.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean insertCapLop(Lop lop, Connection conn) {
        String sql = "INSERT INTO dbo.CAPLOP(TENCAPLOP)\n"
                + "VALUES(?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, lop.getTenCapLop());
            int kq = ptmt.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void updateLop(Lop lop, Connection conn) {
        String sql = "UPDATE dbo.LOP SET TENLOP=?,SISO=?,CAHOC=?,HOCPHI=?"
                + ",NGAYNHAPHOC=?,NGAYKETTHUC=?,MALOAILOP=?,MACAPLOP=?,MANHANVIEN=? \n"
                + "WHERE MALOP =?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, lop.getTenLop());
            ptmt.setInt(2, lop.getSiso());
            ptmt.setString(3, lop.getCaHoc());
            ptmt.setFloat(4, lop.getHocPhi());
            ptmt.setString(5, lop.getNgayNhapHoc());
            ptmt.setString(6, lop.getNgayKeThuc());
            ptmt.setInt(7, lop.getMaLoaiLop());
            ptmt.setInt(8, lop.getMaCapLop());
            ptmt.setInt(9, lop.getMaNhanVien());
            ptmt.setInt(10, lop.getMaLop());
            int kq = ptmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //BUG
    public void updateLoaiLop(Lop lop, Connection conn) {
        String sql = "UPDATE dbo.LOAILOP SET TENLOAILOP = ?\n"
                + "WHERE MALOAILOP =?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, lop.getTenLoaiLop());
            ptmt.setInt(2, lop.getMaLoaiLop());
            int kq = ptmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //BUG
    public void updateCapLop(Lop lop, Connection conn) {
        String sql = "UPDATE dbo.CAPLOP SET TENCAPLOP = ?\n"
                + "WHERE MACAPLOP = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, lop.getTenCapLop());
            ptmt.setInt(2, lop.getMaCapLop());
            int kq = ptmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Lop xoaLop(int ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call xoa_update_lop(?)}");
            call.setInt(1, ID);
            call.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Lop xoaLoaiLop(int ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call xoa_update_loai_lop(?)}");
            call.setInt(1, ID);
            call.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Lop xoaCapLop(int ID, Connection conn) {
        try {
            CallableStatement call = conn.prepareCall("{call xoa_update_cap_lop(?)}");
            call.setInt(1, ID);
            call.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Lop> timKiemLop(String ID, Connection conn) {
        List<Lop> list = new ArrayList<>();
 
        try {
            //PreparedStatement ptmt = conn.prepareStatement(ID)
            CallableStatement call = conn.prepareCall("{call  tim_kiem_lop_theo_ten_lop(?)}");
            call.setString(1, "%" + ID + "%");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Lop lop = new Lop();
                lop.setMaLop(rs.getInt("MALOP"));
                lop.setTenLop(rs.getString("TENLOP"));
                lop.setMaNhanVien(rs.getInt("MANHANVIEN"));
                lop.setTenNhanVien(rs.getString("TENNHANVIEN"));
                lop.setHocPhi(rs.getFloat("HOCPHI"));
                lop.setCaHoc(rs.getString("CAHOC"));
                lop.setSiso(rs.getInt("SISO"));
                //lop.setMaLoaiLop(rs.getInt("MALOAILOP"));
                //lop.setMaCapLop(rs.getInt("MACAPLOP"));
                lop.setTenLoaiLop(rs.getString("TENLOAILOP"));
                lop.setTenCapLop(rs.getString("TENCAPLOP"));
                lop.setNgayNhapHoc(rs.getString("ngaynhaphoc"));
                lop.setNgayKeThuc(rs.getString("ngayketthuc"));
                list.add(lop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Lop> timKiemLoaiLop(String ID, Connection conn) {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.LOAILOP\n"
                + "WHERE TENLOAILOP LIKE ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, "%" + ID + "%");
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Lop l = new Lop();
                l.setMaLoaiLop(rs.getInt("MALOAILOP"));
                l.setTenLoaiLop(rs.getString("TENLOAILOP"));
                list.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Lop> timKiemCapLop(String ID, Connection conn) {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.CAPLOP\n"
                + "WHERE TENCAPLOP LIKE ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, "%" + ID + "%");
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Lop l = new Lop();
                l.setMaCapLop(rs.getInt("MACAPLOP"));
                l.setTenCapLop(rs.getString("TENCAPLOP"));
                list.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
