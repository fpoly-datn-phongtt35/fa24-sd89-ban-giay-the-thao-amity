package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DotGiamGiaDTO {

    private String ma;
    private String ten;
    private BigDecimal giaTri;
    private Instant ngayBatDau;
    private Instant ngayKetThuc;
    private Boolean loai;

}
