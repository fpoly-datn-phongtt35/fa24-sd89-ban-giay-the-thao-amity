package org.example.backend.repositories;

import org.example.backend.models.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KichThuocRepository extends JpaRepository<KichThuoc, UUID> {
}