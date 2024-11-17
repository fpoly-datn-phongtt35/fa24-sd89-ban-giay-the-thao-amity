package org.example.backend.repositories;

import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;
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
        select new org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse(
        hdct.id,spct.id,hd.id,spct.idSanPham.ten,spct.idMauSac.ten,spct.idKichThuoc.ten,spct.idHang.ten,
        hdct.soLuong,hdct.gia,spct.hinhAnh,hdct.ngayTao,hdct.deleted)
        from HoaDonChiTiet hdct
        join hdct.idHoaDon hd
        join hdct.idSpct spct
        where hdct.deleted = false and hd.id = :idHD
""")
    Page<hoaDonChiTietReponse> getByPageHoaDonChiTiet(Pageable pageable,UUID idHD);

}