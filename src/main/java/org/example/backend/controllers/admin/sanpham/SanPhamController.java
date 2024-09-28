package org.example.backend.controllers.admin.sanpham;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.SanPhamRequest;
import org.example.backend.models.SanPham;
import org.example.backend.repositories.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SanPhamController {
    @Autowired
    SanPhamRepository sanPhamRepository;
    @GetMapping(Admin.PRODUCT_GET_ALL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(sanPhamRepository.getAll());
    }
    @PostMapping(Admin.PRODUCT_CREATE)
    public ResponseEntity<?> create(@RequestBody SanPhamRequest sanPhamRequest) {
        SanPham sp = new SanPham();
        sp.setMa(sanPhamRequest.getMa());
        sp.setTen(sanPhamRequest.getTen());
        sp.setIdChatLieu(sanPhamRequest.getIdChatLieu());
        sp.setIdLopLot(sanPhamRequest.getIdLopLot());
        sp.setTrangThai(sanPhamRequest.getTrangThai());
        return ResponseEntity.ok(sanPhamRepository.save(sp));
    }
}
