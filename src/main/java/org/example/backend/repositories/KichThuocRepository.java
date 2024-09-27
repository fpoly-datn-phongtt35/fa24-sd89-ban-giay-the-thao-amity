package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.models.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface KichThuocRepository extends JpaRepository<KichThuoc, UUID> {
    @Query(value="select *from kich_thuoc where deleted=0",nativeQuery = true)
    public List<KichThuoc> getAllKichThuoc();
    @Modifying
    @Transactional
    @Query("""
    update KichThuoc k set k.deleted=:deleted where k.id=:id
""")
    void deletedKichThuoc(Boolean deleted,UUID id);
}