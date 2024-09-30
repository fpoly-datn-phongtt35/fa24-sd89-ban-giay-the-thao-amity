package org.example.backend.dto.request.khachHang;

import java.time.Instant;
import java.util.UUID;

public class KhachHangUpdate {
    private UUID id;
    private String ma;
    private String email;
    private String sdt;
    private String ten;
    private String diaChi;
    private Instant ngaySinh;
    private String gioiTinh;
    private Integer diem;
    private String trangThai="khachhang";

    public KhachHangUpdate() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Instant getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Instant ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Integer getDiem() {
        return diem;
    }

    public void setDiem(Integer diem) {
        this.diem = diem;
    }



    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }



    public KhachHangUpdate(UUID id, String ma, String email, String sdt, String ten, String diaChi, Instant ngaySinh, String gioiTinh, Integer diem, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.email = email;
        this.sdt = sdt;
        this.ten = ten;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diem = diem;
        this.trangThai = trangThai;
    }
}
