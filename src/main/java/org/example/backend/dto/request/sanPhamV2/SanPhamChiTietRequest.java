package org.example.backend.dto.request.sanPhamV2;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SanPhamChiTietRequest {
    private UUID idSanPham;
    private List<UUID> mauSacIds;
    private List<UUID> kichThuocIds;
//    private UUID hang;
//    private BigDecimal giaBan;
//    private BigDecimal giaNhap;
    private String moTa;
}
