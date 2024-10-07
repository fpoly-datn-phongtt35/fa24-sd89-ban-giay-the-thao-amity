package org.example.backend.controllers.admin.sanpham;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.LopLotRequest;
import org.example.backend.models.LopLot;
import org.example.backend.models.MauSac;
import org.example.backend.repositories.LopLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class LopLotController {
    @Autowired
    LopLotRepository lopLotRepository;
    @GetMapping(Admin.LINING_GET_ALL)
    public ResponseEntity<?> getAllLoplot() {
        return ResponseEntity.ok(lopLotRepository.getAll());
    }
    @PostMapping(Admin.LINING_CREATE)
    public ResponseEntity<?> createLOplot(@RequestBody LopLotRequest lopLotRequest) {
        LopLot m = new LopLot();
        m.setMa(lopLotRequest.getMa());
        m.setTen(lopLotRequest.getTen());
        m.setTrangThai(lopLotRequest.getTrangThai());
        return ResponseEntity.ok(lopLotRepository.save(m));
    }

    @PutMapping(Admin.LINING_SET_UPDATE)
    public ResponseEntity<?>  updateLopLot(@PathVariable UUID id) {
        LopLot m = lopLotRepository.findById(id).orElse(null);
        if(m!=null){
            lopLotRepository.setdeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }
}
