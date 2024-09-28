package org.example.backend.dto.response.SanPham;

public class ChatLieuRespon {
    private String ma;
    private String ten;
    private String trangThai;

    public ChatLieuRespon() {
    }

    public ChatLieuRespon(String ma, String ten, String trangThai) {
        this.ma = ma;
        this.ten = ten;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
