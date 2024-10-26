package org.example.backend.repositories;

import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.models.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    @Query("""
      select new org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose(
            qldh.id,qldh.ma,count(hdct.idHoaDon),qldh.tongTien,qldh.idNguoiDung.ten,qldh.ngayTao,qldh.loaiHoaDon,qldh.trangThai,qldh.deleted
        )
        from HoaDon qldh, HoaDonChiTiet hdct
        where qldh.deleted = false 
""")
    Page<QuanLyDonHangRespose> getByPageHoaDon(Pageable pageable);
}