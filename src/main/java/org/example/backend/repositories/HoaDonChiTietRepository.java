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
            qldh.id,qldh.idHoaDon.ma,qldh.soLuong,qldh.idHoaDon.tongTien,qldh.idHoaDon.idNguoiDung.ten,qldh.ngayTao,qldh.idHoaDon.loaiHoaDon,qldh.trangThai,qldh.deleted
        )
        from HoaDonChiTiet qldh
        where qldh.deleted = false 
        and qldh.idHoaDon.idNguoiDung.chucVu ='khachhang'
        order by qldh.ngayTao desc 
""")
    Page<QuanLyDonHangRespose> getByPageHoaDon(Pageable pageable);

//    @Query("""
//        select new org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose(
//            qldh.id,qldh.idHoaDon.ma,qldh.soLuong,qldh.idHoaDon.tongTien,qldh.idHoaDon.idNguoiDung.ten,qldh.ngayTao,qldh.idHoaDon.loaiHoaDon,qldh.trangThai,qldh.deleted
//        )
//        from HoaDonChiTiet qldh
//        where qldh.deleted = false
//         and (
//            (:keyword is null or :keyword = '' or
//            lower(qldh.idHoaDon.ma) like lower(concat('%', :keyword, '%'))
//          )
//        )
//
//        and (:gioiTinh is null or :gioiTinh = '' or nd.gioiTinh = :gioiTinh)
//        and (:trangThai is null or :trangThai = '' or nd.trangThai = :trangThai)
//        order by qldh.ngayTao desc
//""")
//    List<QuanLyDonHangRespose> searchHoaDon();
}