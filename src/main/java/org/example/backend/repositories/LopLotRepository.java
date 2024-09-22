package org.example.backend.repositories;

import org.example.backend.models.LopLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LopLotRepository extends JpaRepository<LopLot, UUID> {
}