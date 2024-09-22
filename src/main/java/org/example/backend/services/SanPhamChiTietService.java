package org.example.backend.services;

import org.example.backend.models.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class SanPhamChiTietService extends GenericServiceImpl<SanPhamChiTiet , UUID> {
    public SanPhamChiTietService(JpaRepository<SanPhamChiTiet, UUID> repository) {
        super(repository);
    }
}
