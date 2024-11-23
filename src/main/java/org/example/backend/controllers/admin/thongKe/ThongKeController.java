package org.example.backend.controllers.admin.thongKe;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.response.thongKe.ThongKeResponse;
import org.example.backend.services.HoaDonChiTietService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThongKeController {
    private final HoaDonChiTietService hoaDonChiTietService;

    public ThongKeController(HoaDonChiTietService hoaDonChiTietService) {
        this.hoaDonChiTietService = hoaDonChiTietService;
    }


    @GetMapping(Admin.THONG_KE)
    public ResponseEntity<List<ThongKeResponse>> getThongKe(@RequestParam(defaultValue = "Đã thanh toán") String trangThai) {
        List<ThongKeResponse> thongKeData = hoaDonChiTietService.getThongKeData(trangThai);
        return ResponseEntity.ok(thongKeData);  // Trả về đối tượng thống kê
    }
    @GetMapping(Admin.THONG_KE_MONTH)
    public ResponseEntity<List<ThongKeResponse>> getThongKeMouth(
            @RequestParam(defaultValue = "Đã thanh toán") String trangThai,
            @RequestParam(defaultValue = "0") int month
    ) {
        List<ThongKeResponse> thongKeData = hoaDonChiTietService.getThongKeDataMonth(trangThai,month);
        return ResponseEntity.ok(thongKeData);  // Trả về đối tượng thống kê
    }



}
