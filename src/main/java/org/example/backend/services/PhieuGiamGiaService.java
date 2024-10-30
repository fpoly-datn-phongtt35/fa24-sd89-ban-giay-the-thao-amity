package org.example.backend.services;

import org.example.backend.common.PageResponse;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.repositories.PhieuGiamGiaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PageResponse<List<phieuGiamGiaReponse>> getAllPGG(int page, int itemsPerPage) {
        Pageable pageable = PageRequest.of(page, itemsPerPage);
        Page<phieuGiamGiaReponse> PGGPage = PGGrepository.getAllPhieuGiamGia(pageable);

        return PageResponse.<List<phieuGiamGiaReponse>>builder()
                .page(PGGPage.getNumber())
                .size(PGGPage.getSize())
                .totalPage(PGGPage.getTotalPages())
                .items(PGGPage.getContent())
                .build();
    }

    private final PhieuGiamGiaRepository PGGrepository;

    public Page<phieuGiamGiaReponse> getPGGGetAll(Pageable pageable) {
        return PGGrepository.getAllPhieuGiamGia(pageable);
    }

    public void setDeletedPhieuGiamGia(Boolean deleted, UUID id){
        PGGrepository.updateDetailPhieuGiamGia(deleted, id);
    }

public List<phieuGiamGiaReponse> searchPGG(String find, String filterType) {
    switch (filterType) {
        case "tienMat":
            return PGGrepository.searchByLoaiAndFind(true, "%" + find + "%");
        case "phanTram":
            return PGGrepository.searchByLoaiAndFind(false, "%" + find + "%");
        case "dangHoatDong":
            return PGGrepository.searchByTrangThaiAndFind("Hoạt Động", "%" + find + "%");
        case "ngungHoatDong":
            return PGGrepository.searchByTrangThaiAndFind("ngừng hoạt động", "%" + find + "%");
        default:
            return PGGrepository.searchPGG("%" + find + "%");
    }
}
}
