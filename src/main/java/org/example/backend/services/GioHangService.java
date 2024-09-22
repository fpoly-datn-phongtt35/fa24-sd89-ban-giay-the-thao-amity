package org.example.backend.services;

import org.example.backend.models.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class GioHangService extends GenericServiceImpl<GioHang, UUID> {
    public GioHangService(JpaRepository<GioHang, UUID> repository) {
        super(repository);
    }
}
