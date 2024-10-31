package org.example.backend.dto.response.traHang;



import lombok.*;
import org.example.backend.models.SanPhamChiTiet;

import java.time.Instant;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TraHangResponse {
    private UUID id;
    private SanPhamChiTiet idSpct;
    private Integer soLuong;
    private String lyDo;
    private Instant ngayTao;
    private Instant ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private String trangThai;
    private Boolean deleted;

}
