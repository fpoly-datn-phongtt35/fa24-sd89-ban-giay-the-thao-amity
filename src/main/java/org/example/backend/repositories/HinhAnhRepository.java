package org.example.backend.repositories;

import org.example.backend.models.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HinhAnhRepository extends JpaRepository<HinhAnh, UUID> {
}