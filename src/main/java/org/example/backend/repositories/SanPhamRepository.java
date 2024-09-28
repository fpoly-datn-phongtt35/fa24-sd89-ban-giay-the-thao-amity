package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.SanPham.SanPhamResponse;
import org.example.backend.models.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query("""
    select new org.example.backend.dto.response.SanPham.SanPhamResponse(s.ma,s.ten,s.ngayTao,s.idChatLieu.ten,s.idLopLot.ten,s.trangThai)
    from SanPham s where s.deleted= false 
""")
    List<SanPhamResponse> getAll();

    @Modifying
    @Transactional
    @Query("""
        update SanPham s set s.deleted=:deleted where s.id=:id
""")
    void setDeleted(Boolean deleted,UUID id);
}