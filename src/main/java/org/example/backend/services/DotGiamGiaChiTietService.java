package org.example.backend.services;

import org.example.backend.models.DotGiamGiaChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class DotGiamGiaChiTietService extends GenericServiceImpl<DotGiamGiaChiTiet, UUID> {
    public DotGiamGiaChiTietService(JpaRepository<DotGiamGiaChiTiet, UUID> repository) {
        super(repository);
    }
}
