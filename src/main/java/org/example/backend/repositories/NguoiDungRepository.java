package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, UUID> {
    @Query("""
    select new org.example.backend.dto.response.NhanVien.NhanVienRespon(nd.id,nd.ma,nd.email,nd.sdt,nd.matKhau,nd.ten,nd.diaChi,nd.ngaySinh,nd.gioiTinh,nd.hinhAnh,nd.cccd,nd.chucVu,nd.deleted)
    from NguoiDung nd where nd.chucVu = 'nhanvien' and nd.deleted = true
""")
    List<NhanVienRespon> getAllNhanVien();

    @Transactional
    @Modifying
    @Query("""
    update NguoiDung nd
    set nd.deleted=false 
    where nd.id=:id
""")
    void deleteNhanVienStatus(UUID id);

    //    KhachHang
    @Query("""
    select new org.example.backend.dto.response.khachHang.KhachHangResponse(nd.ma,nd.email,nd.sdt,nd.ten,nd.diaChi,nd.ngaySinh,nd.gioiTinh,nd.diem,nd.nguoiTao,nd.nguoiSua, nd.ngayTao, nd.ngaySua,nd.trangThai,nd.deleted)
    from NguoiDung nd where nd.chucVu = 'khachhang'
""")
    List<KhachHangResponse> getAllKhachHang();
    @Transactional
    @Modifying
    @Query("""
    update NguoiDung nd
    set nd.deleted=false 
    where nd.id=:id
""")
    void deletedKhachHangStatus(UUID id);
}