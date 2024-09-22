package org.example.backend.services;

import org.example.backend.models.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class LichSuHoaDonService extends GenericServiceImpl<LichSuHoaDon , UUID> {
    public LichSuHoaDonService(JpaRepository<LichSuHoaDon, UUID> repository) {
        super(repository);
    }
}
