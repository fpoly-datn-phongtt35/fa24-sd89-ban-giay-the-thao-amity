package org.example.backend.services;

import org.example.backend.common.PageResponse;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.repositories.PhieuGiamGiaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
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

    public PageResponse<List<phieuGiamGiaReponse>> searchPGG(int page, int itemsPerPage,String keyFind,
      String trangThai, Integer sapXep, Instant minNgay, Instant maxNgay, BigDecimal minGia, BigDecimal maxGia) {
        // Tạo đối tượng Sort dựa trên giá trị của tham số sapXep
        Sort sort = Sort.unsorted();  // Mặc định không sắp xếp nếu sapXep không khớp

        switch (sapXep) {
            case 1:
                sort = Sort.by(Sort.Order.desc("ngayTao")); // sapXep = 1, sắp xếp theo ngayTao giảm dần
                break;
            case 2:
                sort = Sort.by(Sort.Order.asc("ngayTao"));  // sapXep = 2, sắp xếp theo ngayTao tăng dần
                break;
            case 3:
                sort = Sort.by(Sort.Order.desc("ngaySua")); // sapXep = 3, sắp xếp theo ngaySua giảm dần
                break;
            default:
                break; // Không sắp xếp nếu sapXep không có giá trị mong đợi
        }

        // Tạo Pageable với phân trang và sắp xếp
        Pageable pageable = PageRequest.of(page, itemsPerPage, sort);

//        Pageable pageable = PageRequest.of(page, itemsPerPage);
        Page<phieuGiamGiaReponse> PGGPage = PGGrepository.searchPhieuGiamGia(pageable,keyFind,trangThai,minNgay,maxNgay,minGia,maxGia);
        return PageResponse.<List<phieuGiamGiaReponse>>builder()
                .page(PGGPage.getNumber())
                .size(PGGPage.getSize())
                .totalPage(PGGPage.getTotalPages())
                .items(PGGPage.getContent())
                .build();
    }
}
