package org.example.backend.services;

import org.example.backend.common.PageResponse;

import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.dto.response.thongKe.ThongKeResponse;

import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;

import org.example.backend.models.HoaDonChiTiet;
import org.example.backend.repositories.HoaDonChiTietRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietService extends GenericServiceImpl<HoaDonChiTiet, UUID> {
    private final HoaDonChiTietRepository hoaDonChiTietRepository;
    public HoaDonChiTietService(JpaRepository<HoaDonChiTiet, UUID> repository ,HoaDonChiTietRepository hoaDonChiTietRepository) {
        super(repository);
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
    }

    public List<ThongKeResponse> getThongKeData(String trangThai) {
        return hoaDonChiTietRepository.getAllThongKe(trangThai);
    }



    public PageResponse<List<hoaDonChiTietReponse>> getHoaDonChiTiet(int page, int size,UUID idHD) {
        Pageable pageable = PageRequest.of(page, size);
        Page<hoaDonChiTietReponse> hdctPage = hoaDonChiTietRepository.getByPageHoaDonChiTiet(pageable,idHD);
        return PageResponse.<List<hoaDonChiTietReponse>>builder()
                .page(hdctPage.getNumber())
                .size(hdctPage.getSize())
                .totalPage(hdctPage.getTotalPages())
                .items(hdctPage.getContent())
                .build();
    }
//    public Page<hoaDonChiTietReponse> getAllBillPage(Pageable pageable){
//        return hoaDonChiTietRepository.getByPageHoaDon(pageable);

//    }

}
