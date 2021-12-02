/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

//THANHTIEN,MAHOCVIEN,MALOP,MANHANVIEN,MADANGKI,MADOTTHI,DIEMTHI,DIEMTHANHPHAN,DIEMTONG,NGAYTHUTIEN
public class BienLai {
    private  int maBienLai;
    private  Float thanhTien;
    private  int maLop;
    private  int maNhanVien;
    private  int maDangKi;
    private  int maDotThi;
    private  int maHocVien;
    private Float diemThi;
    private Float diemThanhPhan;
    private Float diemTong;
    private String ngayThuTien;
    private String tenNhanVien;
    private  String tenLop;
    private  String tenHocVien;
    private  float  hocPhi;
    private  float  hocPhiNo;
    private String tenLoaiLop;
    private String tenCapLop;

    public BienLai() {
    }

    public BienLai(int maBienLai, Float thanhTien, int maLop, int maNhanVien, int maDangKi, int maDotThi, int maHocVien, Float diemThi, Float diemThanhPhan, Float diemTong, String ngayThuTien, String tenNhanVien, String tenLop, String tenHocVien, float hocPhi, float hocPhiNo, String tenLoaiLop, String tenCapLop) {
        this.maBienLai = maBienLai;
        this.thanhTien = thanhTien;
        this.maLop = maLop;
        this.maNhanVien = maNhanVien;
        this.maDangKi = maDangKi;
        this.maDotThi = maDotThi;
        this.maHocVien = maHocVien;
        this.diemThi = diemThi;
        this.diemThanhPhan = diemThanhPhan;
        this.diemTong = diemTong;
        this.ngayThuTien = ngayThuTien;
        this.tenNhanVien = tenNhanVien;
        this.tenLop = tenLop;
        this.tenHocVien = tenHocVien;
        this.hocPhi = hocPhi;
        this.hocPhiNo = hocPhiNo;
        this.tenLoaiLop = tenLoaiLop;
        this.tenCapLop = tenCapLop;
    }

    public String getTenLoaiLop() {
        return tenLoaiLop;
    }

    public void setTenLoaiLop(String tenLoaiLop) {
        this.tenLoaiLop = tenLoaiLop;
    }

    public String getTenCapLop() {
        return tenCapLop;
    }

    public void setTenCapLop(String tenCapLop) {
        this.tenCapLop = tenCapLop;
    }

    

    public String getTenHocVien() {
        return tenHocVien;
    }

    public void setTenHocVien(String tenHocVien) {
        this.tenHocVien = tenHocVien;
    }



    public int getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(int maHocVien) {
        this.maHocVien = maHocVien;
    }

    

    public int getMaBienLai() {
        return maBienLai;
    }

    public void setMaBienLai(int maBienLai) {
        this.maBienLai = maBienLai;
    }

    public Float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Float thanhTien) {
        this.thanhTien = thanhTien;
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

    public Float getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(Float diemThi) {
        this.diemThi = diemThi;
    }

    public Float getDiemThanhPhan() {
        return diemThanhPhan;
    }

    public void setDiemThanhPhan(Float diemThanhPhan) {
        this.diemThanhPhan = diemThanhPhan;
    }

    public Float getDiemTong() {
        return diemTong;
    }

    public void setDiemTong(Float diemTong) {
        this.diemTong = diemTong;
    }

    public String getNgayThuTien() {
        return ngayThuTien;
    }

    public void setNgayThuTien(String ngayThuTien) {
        this.ngayThuTien = ngayThuTien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public float getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(float hocPhi) {
        this.hocPhi = hocPhi;
    }

    public float getHocPhiNo() {
        return hocPhiNo;
    }

    public void setHocPhiNo(float hocPhiNo) {
        this.hocPhiNo = hocPhiNo;
    }
    
    
    
}
