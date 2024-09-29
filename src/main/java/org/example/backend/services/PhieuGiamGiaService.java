package org.example.backend.services;

import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.repositories.PhieuGiamGiaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class PhieuGiamGiaService extends GenericServiceImpl<PhieuGiamGia , UUID> {
    public PhieuGiamGiaService(JpaRepository<PhieuGiamGia, UUID> repository, PhieuGiamGiaRepository PGGRepository) {
        super(repository);
        this. PGGrepository= PGGRepository;
    }

    private final PhieuGiamGiaRepository PGGrepository;

    public List<phieuGiamGiaReponse> getPGGGetAll() {
        return PGGrepository.getAllPhieuGiamGia();
    }

    public void setDeletedPhieuGiamGia(Boolean deleted, UUID id){
        PGGrepository.updateDetailPhieuGiamGia(deleted, id);
    }
}
