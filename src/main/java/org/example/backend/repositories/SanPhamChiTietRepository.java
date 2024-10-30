package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.SanPham.SanPhamChiTietRespon;
import org.example.backend.models.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, UUID> {
    @Query("""
        select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
        s.idSanPham.ten,s.idHang.id,s.idHang.ten,s.idDanhMuc.id,s.idDanhMuc.ten,s.idDeGiay.id,s.idDeGiay.ten,s.idMauSac.id,
        s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
        s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh
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
    select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(
        s.id, s.idSanPham.id, s.idSanPham.ten, s.idHang.id, s.idHang.ten, s.idDanhMuc.id, s.idDanhMuc.ten,
        s.idDeGiay.id, s.idDeGiay.ten, s.idMauSac.id, s.idMauSac.ten, s.idKichThuoc.id, s.idKichThuoc.ten,
        s.soLuong, s.giaBan, s.giaNhap, s.trangThai, s.hinhAnh
    )
    from SanPhamChiTiet s
    where s.deleted = false
    and s.idSanPham.ten like :ten
    and (:giaLonHon is null or s.giaBan >= :giaLonHon)
    and (:giaNhoHon is null or s.giaBan < :giaNhoHon)
    and (:trangThai is null or s.trangThai = :trangThai)
""")
    List<SanPhamChiTietRespon> search(String ten, Double giaLonHon, Double giaNhoHon, String trangThai);



    @Query("""
        select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
        s.idSanPham.ten,s.idHang.id,s.idHang.ten,s.idDanhMuc.id,s.idDanhMuc.ten,s.idDeGiay.id,s.idDeGiay.ten,s.idMauSac.id,
        s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
        s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh
        )
        from SanPhamChiTiet  s
        where s.deleted=false 
        order by s.ngayTao DESC 
""")
    Page<SanPhamChiTietRespon> phanTrang(Pageable pageable);

    @Query("""
         select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
        s.idSanPham.ten,s.idHang.id,s.idHang.ten,s.idDanhMuc.id,s.idDanhMuc.ten,s.idDeGiay.id,s.idDeGiay.ten,s.idMauSac.id,
        s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
        s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh
        )
        from SanPhamChiTiet  s
        where s.deleted=false  and s.idSanPham.id =:idSanPham
        
""")

    List<SanPhamChiTietRespon> findByIdSpct(UUID idSanPham);
}