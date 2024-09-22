package org.example.backend.repositories;

import org.example.backend.models.TraHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TraHangRepository extends JpaRepository<TraHang, UUID> {
}