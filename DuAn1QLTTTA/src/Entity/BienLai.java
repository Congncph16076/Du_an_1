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
public class BienLai {
    private int maBienLai, maHocVien,maLop,maNhanVien, maDangKi, maDotThi;
    private float thanhTien, diemThi, diemTP, diemTong;
    private Date ngayThuTien;

    public BienLai() {
    }

    public BienLai(int maBienLai, int maHocVien, int maLop, int maNhanVien, int maDangKi, int maDotThi, float thanhTien, float diemThi, float diemTP, float diemTong, Date ngayThuTien) {
        this.maBienLai = maBienLai;
        this.maHocVien = maHocVien;
        this.maLop = maLop;
        this.maNhanVien = maNhanVien;
        this.maDangKi = maDangKi;
        this.maDotThi = maDotThi;
        this.thanhTien = thanhTien;
        this.diemThi = diemThi;
        this.diemTP = diemTP;
        this.diemTong = diemTong;
        this.ngayThuTien = ngayThuTien;
    }

    public int getMaBienLai() {
        return maBienLai;
    }

    public void setMaBienLai(int maBienLai) {
        this.maBienLai = maBienLai;
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

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaDangKi() {
        return maDangKi;
    }

    public void setMaDangKi(int maDangKi) {
        this.maDangKi = maDangKi;
    }

    public int getMaDotThi() {
        return maDotThi;
    }

    public void setMaDotThi(int maDotThi) {
        this.maDotThi = maDotThi;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public float getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(float diemThi) {
        this.diemThi = diemThi;
    }

    public float getDiemTP() {
        return diemTP;
    }

    public void setDiemTP(float diemTP) {
        this.diemTP = diemTP;
    }

    public float getDiemTong() {
        return diemTong;
    }

    public void setDiemTong(float diemTong) {
        this.diemTong = diemTong;
    }

    public Date getNgayThuTien() {
        return ngayThuTien;
    }

    public void setNgayThuTien(Date ngayThuTien) {
        this.ngayThuTien = ngayThuTien;
    }

    @Override
    public String toString() {
        return "BienLai{" + "maBienLai=" + maBienLai + ", maHocVien=" + maHocVien + ", maLop=" + maLop + ", maNhanVien=" + maNhanVien + ", maDangKi=" + maDangKi + ", maDotThi=" + maDotThi + ", thanhTien=" + thanhTien + ", diemThi=" + diemThi + ", diemTP=" + diemTP + ", diemTong=" + diemTong + ", ngayThuTien=" + ngayThuTien + '}';
    }
    
    
}
