package org.example.backend.services;

import org.example.backend.models.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class GioHangChiTietService extends GenericServiceImpl<GioHangChiTiet, UUID> {
    public GioHangChiTietService(JpaRepository<GioHangChiTiet, UUID> repository) {
        super(repository);
    }
}
