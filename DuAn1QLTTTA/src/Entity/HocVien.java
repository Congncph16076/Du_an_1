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
public class HocVien {
    private String tenHocVien, diaChi, sdt, email, tenLop,ngaySinh;
    private int maHocVien, maLop, soBuoiNghi,gioiTinh;
    private float hocPhiNo;

    public HocVien() {
    }

    public HocVien(String tenHocVien, String diaChi, String sdt, String email, String tenLop, String ngaySinh, int maHocVien, int maLop, int soBuoiNghi, int gioiTinh, float hocPhiNo) {
        this.tenHocVien = tenHocVien;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.tenLop = tenLop;
        this.ngaySinh = ngaySinh;
        this.maHocVien = maHocVien;
        this.maLop = maLop;
        this.soBuoiNghi = soBuoiNghi;
        this.gioiTinh = gioiTinh;
        this.hocPhiNo = hocPhiNo;
    }

    public String getTenHocVien() {
        return tenHocVien;
    }

    public void setTenHocVien(String tenHocVien) {
        this.tenHocVien = tenHocVien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(int maHocVien) {
        this.maHocVien = maHocVien;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public int getSoBuoiNghi() {
        return soBuoiNghi;
    }

    public void setSoBuoiNghi(int soBuoiNghi) {
        this.soBuoiNghi = soBuoiNghi;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public float getHocPhiNo() {
        return hocPhiNo;
    }

    public void setHocPhiNo(float hocPhiNo) {
        this.hocPhiNo = hocPhiNo;
    }

    
    
    
    
    
}
