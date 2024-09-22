package org.example.backend.repositories;

import org.example.backend.models.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MauSacRepository extends JpaRepository<MauSac, UUID> {
}