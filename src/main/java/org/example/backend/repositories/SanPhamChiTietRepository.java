package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.SanPham.SanPhamChiTietRespon;
import org.example.backend.models.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, UUID> {
    @Query("""
        select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,
        s.idSanPham.ten,s.idHang.ten,s.idDanhMuc.ten,s.idDeGiay.ten,s.idSanPham.idChatLieu.ten,s.idMauSac.ten,s.idKichThuoc.ten,
        s.idSanPham.idLopLot.ten,s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh
        )
        from SanPhamChiTiet  s
        where s.deleted=false 
""")
    List<SanPhamChiTietRespon> getAll();

    @Modifying
    @Transactional
    @Query("""
        update SanPhamChiTiet s set s.deleted=:deleted where s.id=:id
""")
    void setDeleted(Boolean deleted,UUID id);

    @Query("""
        select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,
        s.idSanPham.ten,s.idHang.ten,s.idDanhMuc.ten,s.idDeGiay.ten,s.idSanPham.idChatLieu.ten,s.idMauSac.ten,s.idKichThuoc.ten,
        s.idSanPham.idLopLot.ten,s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh
        )
        from SanPhamChiTiet  s
        where s.deleted=false  and s.idSanPham.ten like :ten
""")
    List<SanPhamChiTietRespon> search(String ten);

    @Query("""
        select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,
        s.idSanPham.ten,s.idHang.ten,s.idDanhMuc.ten,s.idDeGiay.ten,s.idSanPham.idChatLieu.ten,s.idMauSac.ten,s.idKichThuoc.ten,
        s.idSanPham.idLopLot.ten,s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh
        )
        from SanPhamChiTiet  s
        where s.deleted=false 
        order by s.ngayTao DESC 
""")
    Page<SanPhamChiTietRespon> phanTrang(Pageable pageable);
}