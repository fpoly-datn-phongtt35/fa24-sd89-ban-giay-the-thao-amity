package org.example.backend.dto.response.khachHang;

import java.time.Instant;

public class KhachHangResponse {
    private String ma;
    private String email;
    private String sdt;
    private String ten;
    private String diaChi;
    private Instant ngaySinh;
    private String gioiTinh;
    private Integer diem;
    private String nguoiTao;
    private String nguoiSua;
    private Instant ngayTao;
    private Instant ngaySua;
    private String trangThai;
    private Boolean deleted;

    public KhachHangResponse() {
    }

    public KhachHangResponse(String ma, String email, String sdt, String ten, String diaChi, Instant ngaySinh, String gioiTinh, Integer diem, String nguoiTao, String nguoiSua, Instant ngayTao, Instant ngaySua, String trangThai, Boolean deleted) {
        this.ma = ma;
        this.email = email;
        this.sdt = sdt;
        this.ten = ten;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diem = diem;
        this.nguoiTao = nguoiTao;
        this.nguoiSua = nguoiSua;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.deleted = deleted;
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

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoiSua() {
        return nguoiSua;
    }

    public void setNguoiSua(String nguoiSua) {
        this.nguoiSua = nguoiSua;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Instant getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Instant ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
