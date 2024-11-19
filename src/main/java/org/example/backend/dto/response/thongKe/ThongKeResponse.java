package org.example.backend.dto.response.thongKe;


import jakarta.persistence.*;
import lombok.*;
import org.example.backend.constants.Status;
import org.example.backend.models.HoaDon;
import org.example.backend.models.SanPhamChiTiet;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.example.backend.constants.Constant.CURRENT_TIME;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ThongKeResponse {
    private UUID idHoaDon;           // ID hóa đơn
    private double tongSoLuong;      // Tổng số lượng bán ra
    private double doanhThu;         // Doanh thu sau chiết khấu
    private double tongGiaNhap;      // Tổng giá nhập
    private double lai;              // Lợi nhuận
    private String trangThai;        // Trạng thái
    private Boolean deleted;
}
