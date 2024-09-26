package org.example.backend.repositories;

import org.example.backend.models.DotGiamGiaSpct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DotGiamGiaSpctRepository extends JpaRepository<DotGiamGiaSpct, UUID> {
    @Query("""
    update DotGiamGiaSpct ds set ds.deleted=:deleted where ds.id=:id
""")
    void setDeletedDGGSpct(Boolean deleted, UUID id);
}