/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author vinhn
 */
public class DiemDanh {
    private  int maDiemDanh;
    private  boolean  trangThai;
    private String tenHocVien,tenLop,ngayHoc,caHoc,ghiChu;
    private int maBuoiHoc,maBienLai;

    public DiemDanh() {
    }

    public DiemDanh(int maDiemDanh, boolean trangThai, String tenHocVien, String tenLop, String ngayHoc, String caHoc, String ghiChu, int maBuoiHoc, int maBienLai) {
        this.maDiemDanh = maDiemDanh;
        this.trangThai = trangThai;
        this.tenHocVien = tenHocVien;
        this.tenLop = tenLop;
        this.ngayHoc = ngayHoc;
        this.caHoc = caHoc;
        this.ghiChu = ghiChu;
        this.maBuoiHoc = maBuoiHoc;
        this.maBienLai = maBienLai;
    }

    public int getMaDiemDanh() {
        return maDiemDanh;
    }

    public void setMaDiemDanh(int maDiemDanh) {
        this.maDiemDanh = maDiemDanh;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenHocVien() {
        return tenHocVien;
    }

    public void setTenHocVien(String tenHocVien) {
        this.tenHocVien = tenHocVien;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getNgayHoc() {
        return ngayHoc;
    }

    public void setNgayHoc(String ngayHoc) {
        this.ngayHoc = ngayHoc;
    }

    public String getCaHoc() {
        return caHoc;
    }

    public void setCaHoc(String caHoc) {
        this.caHoc = caHoc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getMaBuoiHoc() {
        return maBuoiHoc;
    }

    public void setMaBuoiHoc(int maBuoiHoc) {
        this.maBuoiHoc = maBuoiHoc;
    }

    public int getMaBienLai() {
        return maBienLai;
    }

    public void setMaBienLai(int maBienLai) {
        this.maBienLai = maBienLai;
    }
    
}
