package org.example.backend.services;

import org.example.backend.models.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class HoaDonService extends GenericServiceImpl<HoaDon, UUID> {
    public HoaDonService(JpaRepository<HoaDon, UUID> repository) {
        super(repository);
    }
}
