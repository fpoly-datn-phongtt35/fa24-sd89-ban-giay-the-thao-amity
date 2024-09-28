package org.example.backend.repositories;

import org.example.backend.models.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
//    @Query("""
//    select new org.example.backend.dto.response.SanPham (s.ma,s.ten,s.ngayTao,spct.soLuong,s.trangThai)
//    from SanPham s join SanPhamChiTiet spct on spct.idSanPham=s.id
//""")
//    List<SanPhamResponse> getAllSanPham();
}