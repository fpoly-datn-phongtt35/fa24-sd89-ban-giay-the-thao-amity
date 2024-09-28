package org.example.backend.dto.response.SanPham;

import java.time.Instant;

public class SanPhamResponse {
    private String ma;
    private String ten;
    private Instant ngayTao;
    private String tenChatLieu;
    private String tenLopLot;
    private String trangThai;

    public SanPhamResponse() {
    }

    public SanPhamResponse(String ma, String ten, Instant ngayTao, String tenChatLieu, String tenLopLot, String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.ngayTao = ngayTao;
        this.tenChatLieu = tenChatLieu;
        this.tenLopLot = tenLopLot;
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

    public Instant getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getTenLopLot() {
        return tenLopLot;
    }

    public void setTenLopLot(String tenLopLot) {
        this.tenLopLot = tenLopLot;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
