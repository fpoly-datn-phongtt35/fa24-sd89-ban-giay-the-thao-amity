package org.example.backend.repositories;

import org.example.backend.models.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, UUID> {
}