package org.example.backend.repositories;

import org.example.backend.models.DotGiamGiaSpct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DotGiamGiaSpctRepository extends JpaRepository<DotGiamGiaSpct, UUID> {
}