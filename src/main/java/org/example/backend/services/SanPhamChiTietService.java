package org.example.backend.services;

import org.example.backend.common.PageResponse;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.banHang.banHangClientResponse;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.repositories.PhieuGiamGiaRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SanPhamChiTietService extends GenericServiceImpl<SanPhamChiTiet, UUID> {
    public SanPhamChiTietService(JpaRepository<SanPhamChiTiet, UUID> repository, SanPhamChiTietRepository SPCTRepository) {
        super(repository);
        this.SPCTRepository = SPCTRepository;
    }

    private final SanPhamChiTietRepository SPCTRepository;

    public PageResponse<List<banHangClientResponse>> getbanHangClient(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<banHangClientResponse> bhPage = SPCTRepository.getBanHangClient(pageable);
        return PageResponse.<List<banHangClientResponse>>builder()
                .page(bhPage.getNumber())
                .size(bhPage.getSize())
                .totalPage(bhPage.getTotalPages())
                .items(bhPage.getContent()).build();
    }

}
