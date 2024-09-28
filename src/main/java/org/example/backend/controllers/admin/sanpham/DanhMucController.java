package org.example.backend.controllers.admin.sanpham;

import org.example.backend.constants.api.Admin;
import org.example.backend.models.DanhMuc;
import org.example.backend.models.MauSac;
import org.example.backend.repositories.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class DanhMucController {
    @Autowired
    DanhMucRepository danhMucRepository;
    @GetMapping(Admin.LIST_GET_ALL)
    public ResponseEntity<?> getAllMauSac() {
        return ResponseEntity.ok(danhMucRepository.getAll());
    }
    @PostMapping(Admin.LIST_CREATE)
    public ResponseEntity<?> createMauSac(@RequestBody DanhMuc danhMuc) {
        DanhMuc m = new DanhMuc();
        m.setMa(danhMuc.getMa());
        m.setTen(danhMuc.getTen());
        m.setTrangThai(danhMuc.getTrangThai());
        return ResponseEntity.ok(danhMucRepository.save(m));
    }

    @PutMapping(Admin.LIST_SET_UPDATE)
    public ResponseEntity<?>  updateMauSac(@PathVariable UUID id) {
        DanhMuc m = danhMucRepository.findById(id).orElse(null);
        if(m!=null){
            danhMucRepository.setdeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }

}
