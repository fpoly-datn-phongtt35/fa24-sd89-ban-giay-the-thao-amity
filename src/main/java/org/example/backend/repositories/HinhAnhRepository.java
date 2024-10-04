package org.example.backend.repositories;

import org.example.backend.dto.response.SanPham.HinhAnhRespon;
import org.example.backend.models.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface HinhAnhRepository extends JpaRepository<HinhAnh, UUID> {
    @Query("""
    select new org.example.backend.dto.response.SanPham.HinhAnhRespon(h.id,h.ma,h.ten,h.url,h.trangThai)
    from HinhAnh h 
    where h.deleted=false 
""")
    List<HinhAnhRespon> getAll();

    @Modifying
    @Transactional
    @Query("""
        update HinhAnh h set h.deleted=:deleted where h.id=:id
""")
    void setDeleted(Boolean deleted,UUID id);
}