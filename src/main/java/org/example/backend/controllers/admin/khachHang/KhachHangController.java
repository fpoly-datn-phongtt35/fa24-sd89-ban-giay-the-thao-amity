package org.example.backend.controllers.admin.khachHang;

import org.example.backend.dto.request.khachHang.KhachHangCreate;
import org.example.backend.mapper.KhachHangMapper;
import org.example.backend.models.NguoiDung;
import org.example.backend.services.KhachHangService;
import org.example.backend.services.NguoiDungService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.example.backend.constants.api.Admin.CART_CREATE;
import static org.example.backend.constants.api.Admin.CUSTOMER_GET_ALL;

@RestController
public class KhachHangController {
final KhachHangService khachHangService;
final KhachHangMapper khachHangMapper;

public KhachHangController(KhachHangService khachHangService, KhachHangMapper khachHangMapper){
    this.khachHangService = khachHangService;
    this.khachHangMapper = khachHangMapper;
}
@GetMapping(CUSTOMER_GET_ALL)
    public ResponseEntity getAllCustomer(){
    return ResponseEntity.ok().body(khachHangService.getAllKhachHang());
}
@PostMapping(CART_CREATE)
    public ResponseEntity<?> createCustomer(@RequestBody KhachHangCreate khachHangCreate){
    NguoiDung nguoiDung = new NguoiDung();
    nguoiDung = khachHangMapper.createToKhachHang(khachHangCreate);
    return ResponseEntity.ok().body(khachHangService.save(nguoiDung));
}
}
