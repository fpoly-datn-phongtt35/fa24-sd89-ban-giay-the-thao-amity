package org.example.backend.repositories;

import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.models.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query("""
        select new org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose(
            qldh.id, qldh.ma, count(hdct.idHoaDon), qldh.tongTien, qldh.idNguoiDung.ten, 
            qldh.ngayTao, qldh.loaiHoaDon, qldh.trangThai, qldh.deleted
        )
        from HoaDonChiTiet hdct
        join hdct.idHoaDon qldh
        where qldh.deleted = false 
        group by qldh.id, qldh.ma, qldh.tongTien, qldh.idNguoiDung.ten, 
                 qldh.ngayTao, qldh.loaiHoaDon, qldh.trangThai, qldh.deleted
""")
    Page<QuanLyDonHangRespose> getByPageHoaDon(Pageable pageable);

}