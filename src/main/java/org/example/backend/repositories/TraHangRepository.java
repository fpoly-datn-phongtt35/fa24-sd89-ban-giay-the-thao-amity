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
    @Query("""
      select new org.example.backend.dto.response.traHang.TraHangResponse(
      t.id, t.idSpct, t.soLuong, t.lyDo, t.ngayTao, t.ngaySua, t.nguoiTao,
       t.nguoiSua, t.trangThai, t.deleted
      )
      from TraHang t ,
          SanPhamChiTiet sp 
      where t.deleted = false
""")
    List<TraHangResponse> getAllTraHang();
}
