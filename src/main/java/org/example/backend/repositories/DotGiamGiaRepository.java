package org.example.backend.repositories;

import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.models.DotGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface DotGiamGiaRepository extends JpaRepository<DotGiamGia, UUID> {
    @Query("""
    select new org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse(d.id, d.ma, d.ten, d.giaTri, d.ngayBatDau, d.ngayKetThuc, d.loai, d.trangThai, d.hinhThuc, d.dieuKien)
    from DotGiamGia d where d.deleted=false 
""")
    List<DotGiamGiaResponse> getAllDotGiamGia();

    @Query("""
    update DotGiamGia d 
    set d.deleted=:deleted where d.id=:id 
""")

    @Modifying
    @Transactional
    void updateDetailDotGiamGia(Boolean deleted, UUID id);

}