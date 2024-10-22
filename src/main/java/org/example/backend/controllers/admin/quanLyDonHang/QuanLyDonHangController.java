package org.example.backend.controllers.admin.quanLyDonHang;


import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.repositories.HoaDonChiTietRepository;
import org.example.backend.services.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.backend.constants.api.Admin.BILL_GET_ALL;
import static org.example.backend.constants.api.Admin.PAGE_BILL;

@RestController
public class QuanLyDonHangController {

    final HoaDonChiTietRepository hoaDonChiTietRepository;
    private final HoaDonChiTietService hoaDonChiTietService;

    public QuanLyDonHangController(HoaDonChiTietRepository hoaDonChiTietRepository, HoaDonChiTietService hoaDonChiTietService){
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
        this.hoaDonChiTietService= hoaDonChiTietService;
    }
    @GetMapping(BILL_GET_ALL)
    public ResponseEntity<ResponseData<PageResponse<List<QuanLyDonHangRespose>>>> getAllHoaDon(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ){
             PageResponse<List<QuanLyDonHangRespose>> qlhdPage = hoaDonChiTietService.getAllNhanVien(page,size);
             ResponseData<PageResponse<List<QuanLyDonHangRespose>>> responseData = ResponseData.<PageResponse<List<QuanLyDonHangRespose>>>builder()
               .message("Get all done")
               .status(HttpStatus.OK.value())
               .data(qlhdPage)
                     .build();
             return ResponseEntity.ok(responseData);
    }

    @GetMapping(PAGE_BILL)
    public ResponseEntity<ResponseData<PageResponse<List<QuanLyDonHangRespose>>>> getAllBillPage(
            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable phanTrang = PageRequest.of(page, itemsPerPage);
        Page<QuanLyDonHangRespose> billPage = hoaDonChiTietService.getAllBillPage(phanTrang);

        PageResponse<List<QuanLyDonHangRespose>> pageResponse = PageResponse.<List<QuanLyDonHangRespose>>builder()
                .page(billPage.getNumber())
                .size(billPage.getSize())
                .totalPage(billPage.getTotalPages())
                .items(billPage.getContent())
                .build();

        ResponseData<PageResponse<List<QuanLyDonHangRespose>>> responseData = ResponseData.<PageResponse<List<QuanLyDonHangRespose>>>builder()
                .message("Get paginated users done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }


}
