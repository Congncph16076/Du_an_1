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
public class DotThi {
    private int maDotThi;
    private  int maLop;
    private  String tenLop;
    private  String ngayThi;
    private int caThi;
    private int  siso;
    private int vang;

    public DotThi() {
    }

    public DotThi(int maDotThi, int maLop, String tenLop, String ngayThi, int caThi, int siso, int vang) {
        this.maDotThi = maDotThi;
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.ngayThi = ngayThi;
        this.caThi = caThi;
        this.siso = siso;
        this.vang = vang;
    }

    public int getMaDotThi() {
        return maDotThi;
    }

    public void setMaDotThi(int maDotThi) {
        this.maDotThi = maDotThi;
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

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public int getCaThi() {
        return caThi;
    }

    public void setCaThi(int caThi) {
        this.caThi = caThi;
    }

    public int getSiso() {
        return siso;
    }

    public void setSiso(int siso) {
        this.siso = siso;
    }

    public int getVang() {
        return vang;
    }

    public void setVang(int vang) {
        this.vang = vang;
    }
    
    
    
}
