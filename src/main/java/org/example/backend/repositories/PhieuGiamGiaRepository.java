package org.example.backend.repositories;

import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, UUID> {
    @Query("""
                select new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(
                p.id, p.ma, p.ten,p.loai, p.giaTri,p.giamToiDa,p.mucDo,
                 p.ngayBatDau, p.ngayKetThuc,p.soLuong, p.dieuKien, p.trangThai)
                from PhieuGiamGia p where p.deleted=false 
            """)
    List<phieuGiamGiaReponse> getAllPhieuGiamGia();

    @Query("""
                update PhieuGiamGia p 
                set p.deleted=:deleted where p.id=:id 
            """)

    @Modifying
    @Transactional
    void updateDetailPhieuGiamGia(Boolean deleted, UUID id);
}