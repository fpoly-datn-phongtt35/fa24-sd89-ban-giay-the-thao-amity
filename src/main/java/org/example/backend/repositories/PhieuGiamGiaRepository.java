package org.example.backend.repositories;

import org.example.backend.models.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, UUID> {

}