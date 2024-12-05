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

import java.math.BigDecimal;
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

    public PageResponse<List<banHangClientResponse>> getbanHangClientbyIDDGG(int page, int size,UUID id) {
        Pageable pageable = PageRequest.of(page, size);
        Page<banHangClientResponse> bhPage = SPCTRepository.getBanHangClientbyIDDGG(pageable,id);
        return PageResponse.<List<banHangClientResponse>>builder()
                .page(bhPage.getNumber())
                .size(bhPage.getSize())
                .totalPage(bhPage.getTotalPages())
                .items(bhPage.getContent()).build();
    }

    public PageResponse<List<banHangClientResponse>> searchBanHangClient(
            int page, int itemsPerPage,
            String tenSp, String tenKichThuoc, String tenMauSac,String tenDanhMuc,String tenHang,
            BigDecimal giaMin, BigDecimal giaMax
    ) {
        Pageable pageable = PageRequest.of(page, itemsPerPage);
        Page<banHangClientResponse> results = SPCTRepository.searchBanHangClient(
                tenSp, tenKichThuoc, tenMauSac,tenDanhMuc,tenHang, giaMin, giaMax, pageable
        );

        return PageResponse.<List<banHangClientResponse>>builder()
                .page(results.getNumber())
                .size(results.getSize())
                .totalPage(results.getTotalPages())
                .items(results.getContent()).build();
    }


    public List<banHangClientResponse> getTop5SanPhamMoiNhat() {
        return SPCTRepository.getTop5SanPhamMoiNhat();
    }

    public List<banHangClientResponse> getSanPhamGiamGia(List<String> trangThais, UUID id) {
        return SPCTRepository.showGiamGiaTheoSp(trangThais, id);
    }

}
