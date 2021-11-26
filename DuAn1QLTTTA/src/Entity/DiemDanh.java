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
    private int maDiemDanh, maBuoiHoc, maBienLai;
    private boolean trangThai;
    private String ghiChu;

    public DiemDanh() {
    }

    public DiemDanh(int maDiemDanh, int maBuoiHoc, int maBienLai, boolean trangThai, String ghiChu) {
        this.maDiemDanh = maDiemDanh;
        this.maBuoiHoc = maBuoiHoc;
        this.maBienLai = maBienLai;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public int getMaDiemDanh() {
        return maDiemDanh;
    }

    public void setMaDiemDanh(int maDiemDanh) {
        this.maDiemDanh = maDiemDanh;
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

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "DiemDanh{" + "maDiemDanh=" + maDiemDanh + ", maBuoiHoc=" + maBuoiHoc + ", maBienLai=" + maBienLai + ", trangThai=" + trangThai + ", ghiChu=" + ghiChu + '}';
    }
    
    
}
