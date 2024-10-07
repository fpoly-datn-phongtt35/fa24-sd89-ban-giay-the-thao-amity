package org.example.backend.dto.response.SanPham;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamChiTietRespon {
    private UUID uuid;
    private String ten;
    private String hang;
    private String danhMuc;
    private String deGiay;
    private String chatLieu;
    private String mauSac;
    private String kichThuoc;
    public String lopLot;

    public int soLuong;
    public BigDecimal giaBan;
    public BigDecimal giaNhap;
    public String trangThai;
    public String hinhAnh;

}
