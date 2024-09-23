package org.example.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DotGiamGiaRequest {
    private String ma;
    private String ten;
    private BigDecimal giaTri;
    private Instant ngayBatDau;
    private Instant ngayKetThuc;
    private Boolean loai;
}
