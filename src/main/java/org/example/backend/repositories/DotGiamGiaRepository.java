package org.example.backend.repositories;

import org.example.backend.dto.response.dotGiamGia.DotGiamGiaGetAll;
import org.example.backend.models.DotGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DotGiamGiaRepository extends JpaRepository<DotGiamGia, UUID> {
    @Query("""
    select d.id, d.ma, d.ten, d.giaTri, d.ngayBatDau, d.ngayKetThuc, d.loai, d.trangThai
    from DotGiamGia d
""")
    List<DotGiamGiaGetAll> getAllDotGiamGia();

}