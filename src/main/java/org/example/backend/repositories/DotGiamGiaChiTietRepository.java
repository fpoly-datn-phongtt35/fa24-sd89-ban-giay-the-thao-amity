package org.example.backend.repositories;

import org.example.backend.models.DotGiamGiaChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DotGiamGiaChiTietRepository extends JpaRepository<DotGiamGiaChiTiet, UUID> {
}