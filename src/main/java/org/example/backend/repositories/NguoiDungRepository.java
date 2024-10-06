package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.common.PageResponse;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.models.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, UUID> {
    @Query("""
    select new org.example.backend.dto.response.NhanVien.NhanVienRespon(
        nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, 
        nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted
    )
    from NguoiDung nd
    where nd.chucVu = 'nhanvien' and nd.deleted = false
    order by nd.ngayTao DESC
""")
    Page<NhanVienRespon> getAllNhanVien(Pageable pageable);


    @Transactional
    @Modifying
    @Query("""
    update NguoiDung nd
    set nd.deleted=true 
    where nd.id=:id
""")
    void deleteNhanVienStatus(UUID id);


    @Query("""
        select new org.example.backend.dto.response.NhanVien.NhanVienRespon(nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted)
        from NguoiDung nd 
        where nd.chucVu = 'nhanvien' and nd.deleted = false 
        order by nd.ngayTao DESC 
    """)
    Page<NhanVienRespon> getAllNhanVienPage(Pageable pageable);

    @Query("""
    select new org.example.backend.dto.response.NhanVien.NhanVienRespon(nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted)
    from NguoiDung nd
    where (nd.ma like :name or nd.ten like :name or nd.sdt like :name) 
      and nd.chucVu = 'nhanvien' 
      and nd.deleted = false
""")
    List<NhanVienRespon> searchUserNhanVien(String name);

    @Query("""
    select new org.example.backend.dto.response.NhanVien.NhanVienRespon(nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted)
    from NguoiDung nd
    where  nd.chucVu = 'nhanvien'  and nd.deleted = false
    order by nd.ten DESC 
""")
    List<NhanVienRespon>  sortNhanVien();










































    //    KhachHang
    @Query("""
    select new org.example.backend.dto.response.khachHang.KhachHangResponse(nd.id,nd.ma,nd.email,nd.sdt,nd.ten,nd.diaChi,nd.ngaySinh,nd.gioiTinh,nd.diem,nd.trangThai, nd.deleted, nd.chucVu)
    from NguoiDung nd where nd.chucVu = 'khachhang' and nd.deleted = false
""")
    List<KhachHangResponse> getAllKhachHang();
    @Transactional
    @Modifying
    @Query("""
    update NguoiDung nd
    set nd.deleted=true 
    where nd.id=:id
""")
    void deletedKhachHangStatus(UUID id);

}