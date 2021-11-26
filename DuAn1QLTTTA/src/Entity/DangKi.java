/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author vinhn
 */
public class DangKi {
    private int maDangKi, maHocVien, gioiTinh;
    private String tenHocVien, email,sdt, diaChi, tenCapLop, tenLoaiLop,caHoc,ngaySinh,ngayNhapHoc, ngayDangKi;
    private float hocPhi;

    public DangKi() {
    }

    public DangKi(int maDangKi, int maHocVien, int gioiTinh, String tenHocVien, String email, String sdt, String diaChi, String tenCapLop, String tenLoaiLop, String caHoc, String ngaySinh, String ngayNhapHoc, String ngayDangKi, float hocPhi) {
        this.maDangKi = maDangKi;
        this.maHocVien = maHocVien;
        this.gioiTinh = gioiTinh;
        this.tenHocVien = tenHocVien;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.tenCapLop = tenCapLop;
        this.tenLoaiLop = tenLoaiLop;
        this.caHoc = caHoc;
        this.ngaySinh = ngaySinh;
        this.ngayNhapHoc = ngayNhapHoc;
        this.ngayDangKi = ngayDangKi;
        this.hocPhi = hocPhi;
    }

    public int getMaDangKi() {
        return maDangKi;
    }

    public void setMaDangKi(int maDangKi) {
        this.maDangKi = maDangKi;
    }

    public int getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(int maHocVien) {
        this.maHocVien = maHocVien;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getTenHocVien() {
        return tenHocVien;
    }

    public void setTenHocVien(String tenHocVien) {
        this.tenHocVien = tenHocVien;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenCapLop() {
        return tenCapLop;
    }

    public void setTenCapLop(String tenCapLop) {
        this.tenCapLop = tenCapLop;
    }

    public String getTenLoaiLop() {
        return tenLoaiLop;
    }

    public void setTenLoaiLop(String tenLoaiLop) {
        this.tenLoaiLop = tenLoaiLop;
    }

    public String getCaHoc() {
        return caHoc;
    }

    public void setCaHoc(String caHoc) {
        this.caHoc = caHoc;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNgayNhapHoc() {
        return ngayNhapHoc;
    }

    public void setNgayNhapHoc(String ngayNhapHoc) {
        this.ngayNhapHoc = ngayNhapHoc;
    }

    public String getNgayDangKi() {
        return ngayDangKi;
    }

    public void setNgayDangKi(String ngayDangKi) {
        this.ngayDangKi = ngayDangKi;
    }

    public float getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(float hocPhi) {
        this.hocPhi = hocPhi;
    }

    

    
    
}
