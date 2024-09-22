package org.example.backend.repositories;

import org.example.backend.models.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ThanhToanRepository extends JpaRepository<ThanhToan, UUID> {
}