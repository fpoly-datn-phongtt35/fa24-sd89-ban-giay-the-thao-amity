package org.example.backend.repositories;


import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.dto.response.thongKe.ThongKeResponse;

import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;

import org.example.backend.models.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {

//    @Query("""
//        select new org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose(
//            qldh.id, qldh.ma, count(hdct.idHoaDon), qldh.tongTien, qldh.idNguoiDung.ten,
//            qldh.ngayTao, qldh.loaiHoaDon, qldh.trangThai, qldh.deleted
//        )
//        from HoaDonChiTiet hdct
//        join hdct.idHoaDon qldh
//        where qldh.deleted = false
//        group by qldh.id, qldh.ma, qldh.tongTien, qldh.idNguoiDung.ten,
//                 qldh.ngayTao, qldh.loaiHoaDon, qldh.trangThai, qldh.deleted
//""")
//    Page<QuanLyDonHangRespose> getByPageHoaDon(Pageable pageable);

    @Query("""
    SELECT new org.example.backend.dto.response.thongKe.ThongKeResponse(
        hd.id,
        SUM(hdct.soLuong),
        SUM(hdct.soLuong * (hdct.gia - COALESCE(hdct.giaGiam, 0))),
        SUM(hdct.soLuong * spct.giaNhap),
        (SUM(hdct.soLuong * (hdct.gia - COALESCE(hdct.giaGiam, 0)))) - (SUM(hdct.soLuong * spct.giaNhap)),
        hd.trangThai,
        hd.deleted)
    FROM HoaDonChiTiet hdct
    JOIN HoaDon hd ON hdct.idHoaDon.id = hd.id
    JOIN SanPhamChiTiet spct ON hdct.idSpct.id = spct.id
    WHERE hd.trangThai = :trangThai
      AND hd.deleted = false
      AND FUNCTION('YEAR', hd.ngayTao) = :year
    GROUP BY hd.id, hd.trangThai, hd.deleted
""")
    List<ThongKeResponse> getAllThongKe(@Param("trangThai") String trangThai, @Param("year") int year);

    @Query("""
    SELECT new org.example.backend.dto.response.thongKe.ThongKeResponse(
        hd.id,
        SUM(hdct.soLuong),
        SUM(hdct.soLuong * (hdct.gia - COALESCE(hdct.giaGiam, 0))),
        SUM(hdct.soLuong * spct.giaNhap),
        (SUM(hdct.soLuong * (hdct.gia - COALESCE(hdct.giaGiam, 0)))) - (SUM(hdct.soLuong * spct.giaNhap)),
        hd.trangThai,
        hd.deleted)
    FROM HoaDonChiTiet hdct
    JOIN HoaDon hd ON hdct.idHoaDon.id = hd.id
    JOIN SanPhamChiTiet spct ON hdct.idSpct.id = spct.id
    WHERE hd.trangThai = :trangThai
      AND hd.deleted = false
      AND FUNCTION('YEAR', hd.ngayTao) = :year
      AND FUNCTION('MONTH', hd.ngayTao) = :month
    GROUP BY hd.id, hd.trangThai, hd.deleted
""")
    List<ThongKeResponse> getAllThongKeByMonth(@Param("trangThai") String trangThai, @Param("year") int year, @Param("month") int month);

    @Query("""
    SELECT new org.example.backend.dto.response.thongKe.ThongKeResponse(
        hd.id,
        SUM(hdct.soLuong),
        SUM(hdct.soLuong * (hdct.gia - COALESCE(hdct.giaGiam, 0))),
        SUM(hdct.soLuong * spct.giaNhap),
        (SUM(hdct.soLuong * (hdct.gia - COALESCE(hdct.giaGiam, 0)))) - (SUM(hdct.soLuong * spct.giaNhap)),
        hd.trangThai,
        hd.deleted)
    FROM HoaDonChiTiet hdct
    JOIN HoaDon hd ON hdct.idHoaDon.id = hd.id
    JOIN SanPhamChiTiet spct ON hdct.idSpct.id = spct.id
    WHERE hd.trangThai = :trangThai
      AND hd.deleted = false
      AND FUNCTION('YEAR', hd.ngayTao) = :year
      AND FUNCTION('MONTH', hd.ngayTao) = :month
      AND FUNCTION('DAY', hd.ngayTao) = :day
    GROUP BY hd.id, hd.trangThai, hd.deleted
""")
    List<ThongKeResponse> getAllThongKeByDay(@Param("trangThai") String trangThai, @Param("year") int year, @Param("month") int month,@Param("day") int day);













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

    @Query("""
    select new org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse(
    hdct.id, spct.id, hd.id, spct.idSanPham.ten, spct.idMauSac.ten, spct.idKichThuoc.ten, spct.idHang.ten,
    hdct.soLuong, hdct.gia, spct.hinhAnh, hdct.ngayTao, hdct.deleted)
    from HoaDonChiTiet hdct
    join hdct.idHoaDon hd
    join hdct.idSpct spct
    where hdct.deleted = false and hd.ma = :ma
""")
    List<hoaDonChiTietReponse> getByMaHoaDonChiTiet(String ma);






}