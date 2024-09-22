package org.example.backend.services;

import org.example.backend.models.DotGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DotGiamGiaService extends GenericServiceImpl<DotGiamGia, UUID> {
    public DotGiamGiaService(JpaRepository<DotGiamGia, UUID> repository) {
        super(repository);
    }
}
