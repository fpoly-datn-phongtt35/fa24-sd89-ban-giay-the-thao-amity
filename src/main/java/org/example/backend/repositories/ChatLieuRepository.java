package org.example.backend.repositories;

import org.example.backend.dto.response.SanPham.ChatLieuRespon;
import org.example.backend.models.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
    @Query("""
    select new org.example.backend.dto.response.SanPham.ChatLieuRespon(l.ma,l.ten,l.trangThai)
    from ChatLieu l 
    where l.deleted=false 
""")
    List<ChatLieuRespon> getAll();
    @Modifying
    @Transactional
    @Query("""
        update ChatLieu l set l.deleted=:deleted where l.id=:id
""")
    void setdeleted(boolean deleted,UUID id);
}