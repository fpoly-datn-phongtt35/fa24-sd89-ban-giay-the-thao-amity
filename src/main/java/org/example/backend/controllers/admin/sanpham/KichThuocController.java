package org.example.backend.controllers.admin.sanpham;

import org.example.backend.constants.api.Admin;
import org.example.backend.models.KichThuoc;
import org.example.backend.repositories.KichThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class KichThuocController {
    @Autowired
    KichThuocRepository kichThuocRepository;
    @GetMapping(Admin.SIZE_GET_ALL)
    public ResponseEntity<?> getAllKichThuoc() {
       return ResponseEntity.ok(kichThuocRepository.getAllKichThuoc());
    }
    @PostMapping(Admin.SIZE_CREATE)
    public ResponseEntity<?> createKichThuoc(@RequestBody KichThuoc kichThuoc) {
        KichThuoc kt = new KichThuoc();
        kt.setMa(kichThuoc.getMa());
        kt.setTen(kichThuoc.getTen());
        kt.setTrangThai(kichThuoc.getTrangThai());
        return ResponseEntity.ok(kichThuocRepository.save(kt));
    }
    @PutMapping(Admin.SIZE_UPDATE)
    public ResponseEntity<?> update (@PathVariable UUID id) {
        KichThuoc kt = kichThuocRepository.findById(id).orElse(null);
        if (kt != null) {
            kichThuocRepository.deletedKichThuoc(!kt.getDeleted(),id);
            return ResponseEntity.ok("set deleted id " +id );
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(Admin.SIZE_SEARCH)
    public ResponseEntity<?> Search(@RequestParam(value="ten" ,defaultValue = "") String ten){
        return ResponseEntity.ok(kichThuocRepository.search("%"+ten+"%"));
    }

}
