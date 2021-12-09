/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author congc
 */
public class Lop {
    private  int maLop;
    private  String tenLop;
    private int maNhanVien;
    private  String tenNhanVien;
    private int siso;
    private  String caHoc;
    private float hocPhi;
    private int maLoaiLop;
    private String tenLoaiLop;
    private int maCapLop;
    private String tenCapLop;
    private String ngayNhapHoc;
    private String ngayKeThuc;
    private  int soLuong;
    private  boolean  trangThai;
    public Lop() {
    }

    public Lop(int maLop, String tenLop, int maNhanVien, String tenNhanVien, int siso, String caHoc, float hocPhi, int maLoaiLop, String tenLoaiLop, int maCapLop, String tenCapLop, String ngayNhapHoc, String ngayKeThuc, int soLuong, boolean trangThai) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.siso = siso;
        this.caHoc = caHoc;
        this.hocPhi = hocPhi;
        this.maLoaiLop = maLoaiLop;
        this.tenLoaiLop = tenLoaiLop;
        this.maCapLop = maCapLop;
        this.tenCapLop = tenCapLop;
        this.ngayNhapHoc = ngayNhapHoc;
        this.ngayKeThuc = ngayKeThuc;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

   

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getSiso() {
        return siso;
    }

    public void setSiso(int siso) {
        this.siso = siso;
    }

    public String getCaHoc() {
        return caHoc;
    }

    public void setCaHoc(String caHoc) {
        this.caHoc = caHoc;
    }

    public float getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(float hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getMaLoaiLop() {
        return maLoaiLop;
    }

    public void setMaLoaiLop(int maLoaiLop) {
        this.maLoaiLop = maLoaiLop;
    }

    public String getTenLoaiLop() {
        return tenLoaiLop;
    }

    public void setTenLoaiLop(String tenLoaiLop) {
        this.tenLoaiLop = tenLoaiLop;
    }

    public int getMaCapLop() {
        return maCapLop;
    }

    public void setMaCapLop(int maCapLop) {
        this.maCapLop = maCapLop;
    }

    public String getTenCapLop() {
        return tenCapLop;
    }

    public void setTenCapLop(String tenCapLop) {
        this.tenCapLop = tenCapLop;
    }

    public String getNgayNhapHoc() {
        return ngayNhapHoc;
    }

    public void setNgayNhapHoc(String ngayNhapHoc) {
        this.ngayNhapHoc = ngayNhapHoc;
    }

    public String getNgayKeThuc() {
        return ngayKeThuc;
    }

    public void setNgayKeThuc(String ngayKeThuc) {
        this.ngayKeThuc = ngayKeThuc;
    }
    
    
}
