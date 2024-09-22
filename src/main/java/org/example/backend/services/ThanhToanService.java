package org.example.backend.services;

import org.example.backend.models.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class ThanhToanService extends GenericServiceImpl<ThanhToan , UUID> {
    public ThanhToanService(JpaRepository<ThanhToan, UUID> repository) {
        super(repository);
    }
}
