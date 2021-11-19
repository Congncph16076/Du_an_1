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
public class KeToan {
    private int maKeToan;
    private  String tenKeToan;
    private  int gioiTinh;
    private  String ngaySinh;
    private  String diaChi;
    private  String SDT;
    private  String Email;

    public KeToan() {
    }

    public KeToan(int maKeToan, String tenKeToan, int gioiTinh, String ngaySinh, String diaChi, String SDT, String Email) {
        this.maKeToan = maKeToan;
        this.tenKeToan = tenKeToan;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.Email = Email;
    }

    public int getMaKeToan() {
        return maKeToan;
    }

    public void setMaKeToan(int maKeToan) {
        this.maKeToan = maKeToan;
    }

    public String getTenKeToan() {
        return tenKeToan;
    }

    public void setTenKeToan(String tenKeToan) {
        this.tenKeToan = tenKeToan;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}
