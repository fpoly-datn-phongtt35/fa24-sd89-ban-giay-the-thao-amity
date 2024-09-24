package org.example.backend.dto.request.dotGiamGia;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

import static org.example.backend.constants.Constant.CURRENT_TIME;

@Builder

public class DotGiamGiaCreate {
    private String ma;
    private String ten;
    private BigDecimal giaTri;
    private Instant ngayBatDau;
    private Instant ngayKetThuc;
    private Instant ngayTao = CURRENT_TIME;
    private Boolean loai;
    private String trangThai;


    public DotGiamGiaCreate(String ma, String ten, BigDecimal giaTri, Instant ngayBatDau, Instant ngayKetThuc, Instant ngayTao, Boolean loai,String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.ngayTao = ngayTao;
        this.loai = loai;
        this.trangThai = trangThai;
    }

    public DotGiamGiaCreate() {
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

    public BigDecimal getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(BigDecimal giaTri) {
        this.giaTri = giaTri;
    }

    public Instant getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Instant ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Instant getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Instant ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Boolean getLoai() {
        return loai;
    }

    public void setLoai(Boolean loai) {
        this.loai = loai;
    }


    @Override
    public String toString() {
        return "DotGiamGiaRequest{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", giaTri=" + giaTri +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", ngaySua=" + ngayTao +
                ", loai=" + loai +
                '}';
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
