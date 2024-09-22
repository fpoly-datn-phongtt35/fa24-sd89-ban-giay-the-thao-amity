package org.example.backend.services;

import org.example.backend.models.DotGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class DotGiamGiaService extends GenericServiceImpl<DotGiamGia, UUID> {
    public DotGiamGiaService(JpaRepository<DotGiamGia, UUID> repository) {
        super(repository);
    }
}
