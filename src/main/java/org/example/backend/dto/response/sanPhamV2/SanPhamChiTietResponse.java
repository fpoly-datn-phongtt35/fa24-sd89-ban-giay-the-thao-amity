package org.example.backend.dto.response.sanPhamV2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class SanPhamChiTietResponse {
    private UUID idSanPham;
    private String ten;
    private String chatLieu;
    private String lopLot;
    private List<Variant> variants;

    public SanPhamChiTietResponse(UUID idSanPham, String ten, String chatLieu, String lopLot, List<Variant> variants) {
        this.idSanPham = idSanPham;
        this.ten = ten;
        this.chatLieu = chatLieu;
        this.lopLot = lopLot;
        this.variants = variants;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Variant {
        private UUID id; // id sản phẩm chi tiết
        private String ten;
        private String mauSac;
        private String kichThuoc;
        private String deGiay;
        private String danhMuc;
        private String hang;
        private BigDecimal giaBan;
        private Integer soLuong;
        private String hinhAnh;
        private Boolean deleted;
    }

}