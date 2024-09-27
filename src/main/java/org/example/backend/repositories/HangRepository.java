package org.example.backend.repositories;

import org.example.backend.models.Hang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;
import java.util.UUID;

public interface HangRepository extends JpaRepository<Hang, UUID> {
    @Query(value="select * from hang\n" +
            "where deleted=0",nativeQuery = true)
    List<Hang> getAll();
    @Query("""
        update Hang h set h.deleted=:deleted where h.id=:id
""")
    void deletedHang(Boolean deleted,UUID id);

}