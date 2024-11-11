package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.banHang.BanHangResponse;
import org.example.backend.dto.response.banHang.TrangThaiRespon;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.models.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
//    @Query("""
//      select new org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose(
//            qldh.id,qldh.ma,count(hdct.idHoaDon),qldh.tongTien,qldh.idNguoiDung.ten,qldh.ngayTao,qldh.loaiHoaDon,qldh.trangThai,qldh.deleted
//        )
//        from HoaDon qldh, HoaDonChiTiet hdct
//        where qldh.deleted = false
//""")
//    Page<QuanLyDonHangRespose> getByPageHoaDon(Pageable pageable);


    @Modifying
    @Transactional
    @Query("""
                    update HoaDon h set h.deleted=:deleted where h.id=:id
                
            """)
    void setDeleted(Boolean deleted, UUID id);

    @Query("""

        select new   org.example.backend.dto.response.banHang.BanHangResponse(h.id,h.ma,h.trangThai)
        from HoaDon h where h.deleted = false and h.trangThai = :trangThai
        order by h.ngayTao desc 
""")
    List<BanHangResponse> getAllBanHang(String trangThai);


    @Query("""
                    select new  org.example.backend.dto.response.banHang.TrangThaiRespon(h.trangThai,count(h.id))
                    from HoaDon h
                    where h.deleted = false and h.trangThai = :trangThai
                    group by h.trangThai
                    
            """)
    Optional<TrangThaiRespon> getAllTrangThai(String trangThai);

    @Query("""
                  select new org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose(
                        hd.id, hd.ma, hd.idNguoiDung.ten, hd.soDienThoai, hd.diaChi, hd.tongTien, hd.loaiHoaDon, hd.ngayTao, hd.trangThai, hd.deleted
                    )
                    from HoaDon hd
                    where hd.deleted = false
            """)
    List<QuanLyDonHangRespose> GetAllHoaDon();

    @Query("""
                  select new org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose(
                        hd.id, hd.ma, hd.idNguoiDung.ten, hd.soDienThoai, hd.diaChi, hd.tongTien, hd.loaiHoaDon, hd.ngayTao, hd.trangThai, hd.deleted
                    )
                    from HoaDon hd
                    where hd.deleted = false
                    AND (COALESCE(:#{#status}, '') = '' OR hd.trangThai = :#{#status})
            """)
    Page<QuanLyDonHangRespose> GetAllHoaDonByTrangThai(Pageable pageable, String status);

    // Đếm hóa đơn dựa trên từng trạng thái cụ thể
    @Query("SELECT COUNT(hd) from HoaDon hd" +
            " where hd.deleted = false" +
            " AND (COALESCE(:#{#status}, '') = '' OR hd.trangThai = :#{#status})")
    Long countHoaDonByTrangThai(String status);

    // Trả về số lượng hóa đơn theo danh sách trạng thái (giữ thứ tự của danh sách trạng thái)
    default List<Long> countHoaDonByTrangThaiList(List<String> statuses) {
        List<Long> counts = new ArrayList<>();
        for (String status : statuses) {
            counts.add(countHoaDonByTrangThai(status));
        }
        return counts;
    }

    @Query("""
            select new org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose(
                        hd.id, hd.ma, hd.idNguoiDung.ten, hd.soDienThoai, hd.diaChi, hd.tongTien, hd.loaiHoaDon, hd.ngayTao, hd.trangThai, hd.deleted
                    )
                    from HoaDon hd
                    where hd.deleted = false
                AND (COALESCE(:#{#keyFind}, '') = '' OR hd.ma like %:#{#keyFind}% OR hd.idNguoiDung.ten like %:#{#keyFind}% 
                OR hd.soDienThoai like %:#{#keyFind}% OR hd.diaChi like %:#{#keyFind}%)
                AND (COALESCE(:#{#minNgay}, null) IS NULL OR hd.ngayTao >= :#{#minNgay})
                AND (COALESCE(:#{#maxNgay}, null) IS NULL OR hd.ngayTao <= :#{#maxNgay})
                AND (COALESCE(:#{#loai}, '') = '' OR hd.loaiHoaDon = :#{#loai})
                AND ((COALESCE(:#{#minGia}, null) IS NULL OR hd.tongTien >= :#{#minGia})
                AND (COALESCE(:#{#maxGia}, null) IS NULL OR hd.tongTien <= :#{#maxGia}))
                AND (COALESCE(:#{#status}, '') = '' OR hd.trangThai = :#{#status})
                """)
    Page<QuanLyDonHangRespose> searchHoaDon(Pageable pageable, String keyFind, String loai,
                                                 Instant minNgay, Instant maxNgay, BigDecimal minGia, BigDecimal maxGia,String status);
}
