package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.banHang.BanHangResponse;
import org.example.backend.dto.response.banHang.TrangThaiRespon;
import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.models.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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


    @Modifying
    @Transactional
    @Query("""
        update HoaDon h set h.deleted=:deleted where h.id=:id
    
""")
    void setDeleted(Boolean deleted, UUID id);

    @Query("""
        select new   org.example.backend.dto.response.banHang.BanHangResponse(h.id,h.ma,h.trangThai)
        from HoaDon h where h.deleted = false
        order by h.ngayTao desc 
""")
    List<BanHangResponse> getAllBanHang();

    @Query("""
        select new  org.example.backend.dto.response.banHang.TrangThaiRespon(h.trangThai,count(h.id))
        from HoaDon h
        where h.deleted = false and h.trangThai = :trangThai
        group by h.trangThai
        
""")
    Optional<TrangThaiRespon> getAllTrangThai(String trangThai);
}