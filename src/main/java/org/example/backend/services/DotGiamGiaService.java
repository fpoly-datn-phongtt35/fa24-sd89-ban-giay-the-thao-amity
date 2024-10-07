package org.example.backend.services;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.PaginationConstants;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaSearch;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.models.DotGiamGia;
import org.example.backend.repositories.DotGiamGiaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
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

    public PageResponse<List<DotGiamGiaResponse>> dotGiamGiaGetAllPaginate(int page, int size, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<DotGiamGiaResponse> dotGiamGiaResponsePage = dotGiamGiaRepository.getAllDotGiamGiaPaginate(pageable);
        return PageResponse.<List<DotGiamGiaResponse>>builder()
                .page(dotGiamGiaResponsePage.getNumber())
                .size(dotGiamGiaResponsePage.getSize())
                .totalPage(dotGiamGiaResponsePage.getTotalPages())
                .items(dotGiamGiaResponsePage.getContent()).build();
    }

    public PageResponse<List<DotGiamGiaResponse>> searchDotGiamGia(int page, int size, String sortBy, String sortDir, DotGiamGiaSearch dotGiamGiaSearch) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<DotGiamGiaResponse> searchDotGiamGiaPaginate = dotGiamGiaRepository.searchDotGiamGiaPaginate(pageable, dotGiamGiaSearch);

        return PageResponse.<List<DotGiamGiaResponse>>builder()
                .page(searchDotGiamGiaPaginate.getNumber())
                .size(searchDotGiamGiaPaginate.getSize())
                .totalPage(searchDotGiamGiaPaginate.getTotalPages())
                .items(searchDotGiamGiaPaginate.getContent()).build();

    }


}
