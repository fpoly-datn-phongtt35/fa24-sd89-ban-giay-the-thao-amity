package org.example.backend.services;

import org.example.backend.models.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GioHangChiTietService extends GenericServiceImpl<GioHangChiTiet, UUID> {
    public GioHangChiTietService(JpaRepository<GioHangChiTiet, UUID> repository) {
        super(repository);
    }
}
