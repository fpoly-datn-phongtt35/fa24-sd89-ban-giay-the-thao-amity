package org.example.backend.repositories;

import org.example.backend.models.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
}