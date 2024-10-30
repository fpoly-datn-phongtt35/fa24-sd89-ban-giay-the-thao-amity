package org.example.backend.controllers.admin.banHang;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.banHang.BanHangRequest;
import org.example.backend.dto.response.banHang.TrangThaiRespon;
import org.example.backend.models.HoaDon;
import org.example.backend.repositories.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.example.backend.constants.Status.CHO_XAC_NHAN_HOA_DON;

@RestController
public class BanHangController {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @GetMapping(Admin.SELL_GET_ALL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(hoaDonRepository.getAllBanHang());
    }


    @PostMapping(Admin.SELL_CREATE)
    public ResponseEntity<?> create() {

        TrangThaiRespon trangThaiRespon = hoaDonRepository.getAllTrangThai(CHO_XAC_NHAN_HOA_DON).orElse(null);
        System.out.println("lol"+ trangThaiRespon);
        if(trangThaiRespon == null || trangThaiRespon.getCount() <5) {
            System.out.println("lol thuong"+ trangThaiRespon);
            HoaDon hoaDon = new HoaDon();
            return ResponseEntity.ok(hoaDonRepository.save(hoaDon));
        }
        return null;

    }

    @PutMapping(Admin.SELL_SET_DELETE)

    public ResponseEntity<?> delete(@PathVariable UUID id) {
        HoaDon hd = hoaDonRepository.findById(id).orElse(null);
        if(hd != null) {
            hoaDonRepository.setDeleted(!hd.getDeleted(),id);
            return ResponseEntity.ok("ok"+id);
        }
        return ResponseEntity.notFound().build();
    }
}
