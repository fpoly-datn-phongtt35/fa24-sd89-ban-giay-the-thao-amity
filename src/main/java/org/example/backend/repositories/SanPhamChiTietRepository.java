package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaSearch;
import org.example.backend.dto.request.sanPham.SanPhamChiTietSearchRequest;
import org.example.backend.dto.response.SanPham.SanPhamChiTietRespon;
import org.example.backend.dto.response.SanPham.SanPhamClientResponse;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.models.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, UUID> {
    @Query("""
        select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
        s.idSanPham.ten,s.idHang.id,s.idHang.ten,s.idDanhMuc.id,s.idDanhMuc.ten,s.idDeGiay.id,s.idDeGiay.ten,s.idMauSac.id,
        s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
        s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
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
        s.soLuong, s.giaBan, s.giaNhap, s.trangThai, s.hinhAnh,s.moTa
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
        s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
        )
        from SanPhamChiTiet  s
        where s.deleted=false 
        order by s.ngayTao DESC 
""")
    Page<SanPhamChiTietRespon> phanTrang(Pageable pageable);

    @Query("""
                select new org.example.backend.dto.response.SanPham.SanPhamClientResponse(
                                           dggspct.idSpct.id, dggspct.idDotGiamGia.id, dggspct.idSpct.giaBan, dggspct.idDotGiamGia.loai, dggspct.idDotGiamGia.giaTri,dggspct.idSpct.hinhAnh
                                       )
                                       from
                                        DotGiamGiaSpct dggspct

            """)
    Page<SanPhamClientResponse> getAllSpctAndDgg(Pageable pageable);
    @Query("""
         select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
        s.idSanPham.ten,s.idHang.id,s.idHang.ten,s.idDanhMuc.id,s.idDanhMuc.ten,s.idDeGiay.id,s.idDeGiay.ten,s.idMauSac.id,
        s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
        s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
        )
        from SanPhamChiTiet  s
        where s.deleted=false  and s.idSanPham.id =:idSanPham
        
""")

    List<SanPhamChiTietRespon> findByIdSpct(UUID idSanPham);

    @Query("""
        select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
        s.idSanPham.ten,s.idHang.id,s.idHang.ten,s.idDanhMuc.id,s.idDanhMuc.ten,s.idDeGiay.id,s.idDeGiay.ten,s.idMauSac.id,
        s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
        s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
        )
        from SanPhamChiTiet s
        where s.deleted=false  
        and s.idSanPham.id = :#{#SPCTSearch.idSanPham}
        AND (COALESCE(:#{#SPCTSearch.hang}, '') = '' OR s.idHang.ten LIKE %:#{#SPCTSearch.hang}%)
        AND (COALESCE(:#{#SPCTSearch.kichThuoc}, '') ='' OR s.idKichThuoc.ten LIKE %:#{#SPCTSearch.kichThuoc}%)
        AND (COALESCE(:#{#SPCTSearch.mauSac}, '') ='' OR s.idMauSac.ten LIKE %:#{#SPCTSearch.mauSac}%)
        
""")
    Page<SanPhamChiTietRespon> findByIdSpct1(Pageable pageable, SanPhamChiTietSearchRequest SPCTSearch);

//    @Query("""
//    select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(
//        s.id, s.idSanPham.id, s.idSanPham.ten, s.idHang.id, s.idHang.ten,
//        s.idDanhMuc.id, s.idDanhMuc.ten, s.idDeGiay.id, s.idDeGiay.ten,
//        s.idMauSac.id, s.idMauSac.ten, s.idKichThuoc.id, s.idKichThuoc.ten,
//        s.soLuong, s.giaBan, s.giaNhap, s.trangThai, s.hinhAnh
//    )
//    from SanPhamChiTiet s
//    where s.deleted = false
//      and s.idSanPham.id = :#{#dotGiamGiaSearch.idSanPham}
//      and (:#{#dotGiamGiaSearch.hang} IS NULL OR s.idHang.ten LIKE %:#{#dotGiamGiaSearch.hang}%)
//      and (:#{#dotGiamGiaSearch.kichThuoc} IS NULL OR s.idKichThuoc.ten LIKE %:#{#dotGiamGiaSearch.kichThuoc}%)
//      and (:#{#dotGiamGiaSearch.mauSac} IS NULL OR s.idMauSac.ten LIKE %:#{#dotGiamGiaSearch.mauSac}%)
//""")
//    Page<SanPhamChiTietRespon> findByIdSpct1(Pageable pageable, SanPhamChiTietSearchRequest dotGiamGiaSearch);
//

    @Query("""
         select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
        s.idSanPham.ten,s.idHang.id,s.idHang.ten,s.idDanhMuc.id,s.idDanhMuc.ten,s.idDeGiay.id,s.idDeGiay.ten,s.idMauSac.id,
        s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
        s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
        )
        from SanPhamChiTiet  s
        where s.deleted=false  and s.id =:id
        
""")
    Optional<SanPhamChiTietRespon> timspctQuetQR(UUID id);
}