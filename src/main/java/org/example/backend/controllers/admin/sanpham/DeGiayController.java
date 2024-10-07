package org.example.backend.controllers.admin.sanpham;

import org.example.backend.constants.api.Admin;
import org.example.backend.models.DeGiay;
import org.example.backend.models.MauSac;
import org.example.backend.repositories.DeGiayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class DeGiayController {
    @Autowired
    DeGiayRepository deGiayRepository;
    @GetMapping(Admin.SOLE_GET_ALL)
    public ResponseEntity<?> getAllDeGiay() {
        return ResponseEntity.ok(deGiayRepository.getAll());
    }
    @PostMapping(Admin.SOLE_CREATE)
    public ResponseEntity<?> createMauSac(@RequestBody DeGiay deGiay) {
        DeGiay d = new DeGiay();
        d.setMa(deGiay.getMa());
        d.setTen(deGiay.getTen());
        d.setTrangThai(deGiay.getTrangThai());
        return ResponseEntity.ok(deGiayRepository.save(d));
    }

    @PutMapping(Admin.SOLE_SET_UPDATE)
    public ResponseEntity<?>  updateDeGiay(@PathVariable UUID id) {
        DeGiay m = deGiayRepository.findById(id).orElse(null);
        if(m!=null){
            deGiayRepository.setdeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping(Admin.SOLE_SEARCH)
    public ResponseEntity<?> Search(@RequestParam(value="ten" ,defaultValue = "") String ten){
        return ResponseEntity.ok(deGiayRepository.search("%"+ten+"%"));
    }
}
