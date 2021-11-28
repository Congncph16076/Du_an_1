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
public class BuoiHoc {
    private int maBuoiHoc, maLopHoc;
    private String ngayHoc;
    private String caHoc, ghiChu,tenLopHoc;

    public BuoiHoc() {
    }

    public BuoiHoc(int maBuoiHoc, int maLopHoc, String ngayHoc, String caHoc, String ghiChu, String tenLopHoc) {
        this.maBuoiHoc = maBuoiHoc;
        this.maLopHoc = maLopHoc;
        this.ngayHoc = ngayHoc;
        this.caHoc = caHoc;
        this.ghiChu = ghiChu;
        this.tenLopHoc = tenLopHoc;
    }

    public int getMaBuoiHoc() {
        return maBuoiHoc;
    }

    public void setMaBuoiHoc(int maBuoiHoc) {
        this.maBuoiHoc = maBuoiHoc;
    }

    public int getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(int maLopHoc) {
        this.maLopHoc = maLopHoc;
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

    public String getTenLopHoc() {
        return tenLopHoc;
    }

    public void setTenLopHoc(String tenLopHoc) {
        this.tenLopHoc = tenLopHoc;
    }
    
   
}
