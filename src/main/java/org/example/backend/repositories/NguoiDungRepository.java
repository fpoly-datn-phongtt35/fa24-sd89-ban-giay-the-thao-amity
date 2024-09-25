package org.example.backend.repositories;

import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, UUID> {
    @Query("""
    select new org.example.backend.dto.response.NhanVien.NhanVienRespon(nd.id,nd.ma,nd.email,nd.sdt,nd.matKhau,nd.ten,nd.diaChi,nd.ngaySinh,nd.gioiTinh,nd.hinhAnh,nd.cccd,nd.chucVu)
    from NguoiDung nd where nd.chucVu = 'nhanvien'
""")
    List<NhanVienRespon> getAllNhanVien();
}