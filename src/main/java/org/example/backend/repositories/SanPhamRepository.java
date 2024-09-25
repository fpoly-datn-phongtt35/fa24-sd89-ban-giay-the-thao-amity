package org.example.backend.repositories;

import org.example.backend.dto.response.SanPham.SanPhamResponse;
import org.example.backend.models.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
//    @Query("""
//    select new org.example.backend.dto.response.SanPham (s.ma,s.ten,s.ngayTao,spct.soLuong,s.trangThai)
//    from SanPham s join SanPhamChiTiet spct on spct.idSanPham=s.id
//""")
//    List<SanPhamResponse> getAllSanPham();
}