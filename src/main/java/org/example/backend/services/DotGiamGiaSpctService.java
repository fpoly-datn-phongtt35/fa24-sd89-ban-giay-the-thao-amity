package org.example.backend.services;

import org.example.backend.models.DotGiamGiaSpct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class DotGiamGiaSpctService extends GenericServiceImpl<DotGiamGiaSpct, UUID> {
    public DotGiamGiaSpctService(JpaRepository<DotGiamGiaSpct, UUID> repository) {
        super(repository);
    }
}
