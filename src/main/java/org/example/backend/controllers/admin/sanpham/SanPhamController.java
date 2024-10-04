package org.example.backend.controllers.admin.sanpham;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.SanPhamRequest;
import org.example.backend.dto.response.SanPham.SanPhamDetailRespon;
import org.example.backend.models.MauSac;
import org.example.backend.models.SanPham;
import org.example.backend.repositories.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @PutMapping(Admin.PRODUCT_UPDATE)
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody SanPhamRequest sanPhamRequest) {
        SanPham sp = sanPhamRepository.findById(id).orElse(null);
        if (sp!= null){
            sp.setMa(sanPhamRequest.getMa());
            sp.setTen(sanPhamRequest.getTen());
            sp.setIdChatLieu(sanPhamRequest.getIdChatLieu());
            sp.setIdLopLot(sanPhamRequest.getIdLopLot());
            sp.setTrangThai(sanPhamRequest.getTrangThai());
            return ResponseEntity.ok(sanPhamRepository.save(sp));
        }
        SanPham sanPham = new SanPham();
        sanPham.setMa(sanPhamRequest.getMa());
        sanPham.setTen(sanPhamRequest.getTen());
        sanPham.setIdChatLieu(sanPhamRequest.getIdChatLieu());
        sanPham.setIdLopLot(sanPhamRequest.getIdLopLot());
        sanPham.setTrangThai(sanPhamRequest.getTrangThai());
        return ResponseEntity.ok(sanPhamRepository.save(sanPham));
    }

    @GetMapping(Admin.PRODUCT_DETAIL_BY_ID)
    public ResponseEntity<?> get(@PathVariable UUID id) {


        List<SanPhamDetailRespon> productDetails = sanPhamRepository.getAllCTSPByIdSp(id);


        if (productDetails == null || productDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(productDetails);
    }
    @PutMapping(Admin.PRODUCT_SET_DELETE)
    public ResponseEntity<?>  updateSanPham(@PathVariable UUID id) {
        SanPham m = sanPhamRepository.findById(id).orElse(null);
        if(m!=null){
            sanPhamRepository.setDeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }
}
