package org.example.backend.services;

import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.models.DotGiamGia;
import org.example.backend.repositories.DotGiamGiaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<DotGiamGiaResponse> getDotGiamGiaGetAll() {
        return dotGiamGiaRepository.getAllDotGiamGia();
    }

    public void setDeletedDotGiamGia(Boolean deleted, UUID id){
        dotGiamGiaRepository.updateDetailDotGiamGia(deleted, id);
    }

    public Page<DotGiamGiaResponse> dotGiamGiaGetAllPaginate(Pageable pageable) {
        return dotGiamGiaRepository.getAllDotGiamGiaPaginate(pageable);
    }


}
