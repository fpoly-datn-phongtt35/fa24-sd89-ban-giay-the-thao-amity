package org.example.backend.repositories;

import org.example.backend.models.DotGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DotGiamGiaRepository extends JpaRepository<DotGiamGia, UUID> {
}