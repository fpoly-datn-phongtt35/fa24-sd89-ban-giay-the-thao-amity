package org.example.backend.services;

import org.example.backend.models.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class HoaDonChiTietService extends GenericServiceImpl<HoaDonChiTiet, UUID> {
    public HoaDonChiTietService(JpaRepository<HoaDonChiTiet, UUID> repository) {
        super(repository);
    }
}
