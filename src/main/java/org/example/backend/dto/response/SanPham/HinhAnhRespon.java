package org.example.backend.dto.response.SanPham;

public class HinhAnhRespon {
    private String ma;
    private String ten;
    private String url;
    private String trangThai;

    public HinhAnhRespon() {
    }

    public HinhAnhRespon(String ma, String ten, String url, String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.url = url;
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }
}
