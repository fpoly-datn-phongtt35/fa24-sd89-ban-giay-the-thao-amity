package org.example.backend.repositories;

import org.example.backend.models.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeGiayRepository extends JpaRepository<DeGiay, UUID> {
}