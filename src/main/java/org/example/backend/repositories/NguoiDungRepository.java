package org.example.backend.repositories;

import org.example.backend.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, UUID> {
}