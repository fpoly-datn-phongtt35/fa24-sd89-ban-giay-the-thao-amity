package org.example.backend.dto.response.SanPham;

public class SanPhamResponse {
    private String ma;
    private String ten;
    private String ngayTao;
    private int soLuong;
    private String trangThai;

    public SanPhamResponse(String ma) {
        this.ma = ma;
    }

    public SanPhamResponse(String ma, String ten, String ngayTao, int soLuong, String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.ngayTao = ngayTao;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
