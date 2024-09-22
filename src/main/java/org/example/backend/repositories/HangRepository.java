package org.example.backend.repositories;

import org.example.backend.models.Hang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HangRepository extends JpaRepository<Hang, UUID> {
}