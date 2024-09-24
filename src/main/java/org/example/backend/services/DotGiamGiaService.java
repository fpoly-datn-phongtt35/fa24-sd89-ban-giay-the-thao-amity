package org.example.backend.services;

import org.example.backend.dto.response.dotGiamGia.DotGiamGiaGetAll;
import org.example.backend.models.DotGiamGia;
import org.example.backend.repositories.DotGiamGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DotGiamGiaService extends GenericServiceImpl<DotGiamGia, UUID> {
    public DotGiamGiaService(JpaRepository<DotGiamGia, UUID> repository, DotGiamGiaRepository dotGiamGiaRepository) {
        super(repository);
        this.dotGiamGiaRepository = dotGiamGiaRepository;
    }

    private final DotGiamGiaRepository dotGiamGiaRepository;

    public List<DotGiamGiaGetAll> getDotGiamGiaGetAll() {
        return dotGiamGiaRepository.getAllDotGiamGia();
    }
}
