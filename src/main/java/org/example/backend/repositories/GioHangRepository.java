package org.example.backend.repositories;

import org.example.backend.models.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GioHangRepository extends JpaRepository<GioHang, UUID> {
}