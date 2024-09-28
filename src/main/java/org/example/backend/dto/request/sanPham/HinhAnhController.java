package org.example.backend.dto.request.sanPham;

import org.example.backend.constants.api.Admin;
import org.example.backend.models.HinhAnh;
import org.example.backend.models.MauSac;
import org.example.backend.repositories.HinhAnhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class HinhAnhController {
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @GetMapping(Admin.IMAGE_GET_ALL)
    public ResponseEntity<?> getAllHinhAnh() {
        return ResponseEntity.ok(hinhAnhRepository.getAll());
    }
    @PostMapping(Admin.IMAGE_CREATE)
    public ResponseEntity<?> createHinhAnh(@RequestBody HinhAnh hinhAnh) {
        HinhAnh m = new HinhAnh();
        m.setMa(hinhAnh.getMa());
        m.setTen(hinhAnh.getTen());
        m.setUrl(hinhAnh.getUrl());
        m.setTrangThai(hinhAnh.getTrangThai());
        return ResponseEntity.ok(hinhAnhRepository.save(m));
    }

    @PutMapping(Admin.IMAGE_SET_UPDATE)
    public ResponseEntity<?>  updateHinhAnh(@PathVariable UUID id) {
        HinhAnh m = hinhAnhRepository.findById(id).orElse(null);
        if(m!=null){
            hinhAnhRepository.setDeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }
}
