package org.example.backend.dto.request.nhanVien;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class NhanVienRequestUpdate {
    private UUID id;

    private String ma;

    private String email;

    private String sdt;

    private String matKhau;

    private String ten;

    private String diaChi;

    private Instant ngaySinh;

    private String gioiTinh;

    private String hinhAnh;

    private String cccd;

//    private Integer diem;
//
//    private String nguoiTao;
//
//    private String nguoiSua;
//
//    private Instant ngayTao;
//
//    private Instant ngaySua;

    private String chucVu;

    private String trangThai;

    private Boolean deleted;
}
