package org.example.backend.services;

import org.example.backend.models.TraHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class TraHangService extends GenericServiceImpl<TraHang, UUID> {
    public TraHangService(JpaRepository<TraHang, UUID> repository) {
        super(repository);
    }
}
