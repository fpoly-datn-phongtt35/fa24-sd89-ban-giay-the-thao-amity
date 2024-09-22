package org.example.backend.services;

import org.example.backend.models.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class PhieuGiamGiaService extends GenericServiceImpl<PhieuGiamGia , UUID> {
    public PhieuGiamGiaService(JpaRepository<PhieuGiamGia, UUID> repository) {
        super(repository);
    }
}
