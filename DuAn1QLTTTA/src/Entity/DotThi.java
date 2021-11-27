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
public class DotThi {
    private int maDotThi, caThi, SiSo, Vang, maLop;
    private Date ngayThi;

    public DotThi() {
    }

    public DotThi(int maDotThi, int caThi, int SiSo, int Vang, int maLop, Date ngayThi) {
        this.maDotThi = maDotThi;
        this.caThi = caThi;
        this.SiSo = SiSo;
        this.Vang = Vang;
        this.maLop = maLop;
        this.ngayThi = ngayThi;
    }

    public int getMaDotThi() {
        return maDotThi;
    }

    public void setMaDotThi(int maDotThi) {
        this.maDotThi = maDotThi;
    }

    public int getCaThi() {
        return caThi;
    }

    public void setCaThi(int caThi) {
        this.caThi = caThi;
    }

    public int getSiSo() {
        return SiSo;
    }

    public void setSiSo(int SiSo) {
        this.SiSo = SiSo;
    }

    public int getVang() {
        return Vang;
    }

    public void setVang(int Vang) {
        this.Vang = Vang;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public Date getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(Date ngayThi) {
        this.ngayThi = ngayThi;
    }

    @Override
    public String toString() {
        return "DotThi{" + "maDotThi=" + maDotThi + ", caThi=" + caThi + ", SiSo=" + SiSo + ", Vang=" + Vang + ", maLop=" + maLop + ", ngayThi=" + ngayThi + '}';
    }
    
    
}
