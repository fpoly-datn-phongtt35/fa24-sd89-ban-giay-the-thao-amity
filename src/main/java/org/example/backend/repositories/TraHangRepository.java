package org.example.backend.repositories;

import org.example.backend.models.TraHang;
import org.example.backend.dto.response.traHang.TraHangResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TraHangRepository extends JpaRepository<TraHang, UUID> {
//    @Query("""
//      select new org.example.backend.dto.response.traHang.TraHangResponse(
//      t.id, t.idSpct, t.soLuong, t.lyDo, t.ngayTao, t.ngaySua, t.nguoiTao,
//       t.nguoiSua, t.trangThai, t.deleted
//      )
//      from TraHang t ,
//          SanPhamChiTiet sp
//      where t.deleted = false
//""")
//    List<TraHangResponse> getAllTraHang();

    @Query("""
    select new org.example.backend.dto.response.traHang.TraHangResponse(
        t.id, t.idSpct.id, spct.idSanPham.ten, spct.idMauSac.ten, spct.idKichThuoc.ten, 
        t.soLuong, t.lyDo, t.ngayTao, t.ngaySua, t.nguoiTao, t.nguoiSua, t.trangThai, 
        spct.hinhAnh, t.deleted
    )
    from TraHang t 
    left join SanPhamChiTiet spct on t.idSpct.id = spct.id
    where t.deleted = false and t.nguoiTao = :nguoiTao
""")
    List<TraHangResponse> getAllTraHangClient(String nguoiTao);




}
