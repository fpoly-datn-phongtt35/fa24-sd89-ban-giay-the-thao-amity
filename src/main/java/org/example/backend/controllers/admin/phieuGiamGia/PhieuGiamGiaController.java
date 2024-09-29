package org.example.backend.controllers.admin.phieuGiamGia;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.phieuGiamGia.phieuGiamGiaRequestAdd;
import org.example.backend.dto.request.phieuGiamGia.phieuGiamGiaRequestUpdate;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.mapper.phieuGiamGia.phieuGiamGiaMapper;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.services.PhieuGiamGiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.VOUCHER_CREATE;
import static org.example.backend.constants.api.Admin.VOUCHER_DELETE;
import static org.example.backend.constants.api.Admin.VOUCHER_GET_BY_ID;
import static org.example.backend.constants.api.Admin.VOUCHER_UPDATE;

@RestController
public class PhieuGiamGiaController {

//    @Autowired
    final PhieuGiamGiaService PGGService;

    final phieuGiamGiaMapper PGGMapper;

    public PhieuGiamGiaController(PhieuGiamGiaService PGGService,phieuGiamGiaMapper PGGMapper){
        this.PGGService = PGGService;
        this.PGGMapper = PGGMapper;
    }

    @GetMapping(Admin.VOUCHER_GET_ALL)
    public ResponseEntity<?> getALlVoucher(){
        return ResponseEntity.ok(PGGService.getPGGGetAll());
    }

    @PostMapping(VOUCHER_CREATE)
    public ResponseEntity<?> createVoucher(@RequestBody phieuGiamGiaRequestAdd PGGadd) {
        PhieuGiamGia pgg = new PhieuGiamGia();
        PGGMapper.createPhieuGiamGiaFromDto(PGGadd, pgg);
        return ResponseEntity.ok().body(PGGService.save(pgg));
    }

    @PutMapping(VOUCHER_UPDATE)
    public ResponseEntity<?> updateVoucher(
            @PathVariable UUID id,
            @RequestBody phieuGiamGiaRequestUpdate PGGupdate) {
        Optional<PhieuGiamGia> existingPGG = PGGService.findById(id);
        if (existingPGG.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PhieuGiamGia pgg = existingPGG.get();
        PGGMapper.updatePhieuGiamGiaFromDto(PGGupdate, pgg);
        return ResponseEntity.ok(PGGService.save(pgg));
    }

//    @DeleteMapping(VOUCHER_DELETE)
//    public ResponseEntity<?> deleteVoucher(@PathVariable UUID id) {
//        Optional<PhieuGiamGia> pgg = PGGService.findById(id);
//        if (pgg.isPresent()) {
//            PGGService.deleteById(id);
//            return ResponseEntity.ok().body("Delete id: " + id);
//        }
//        return ResponseEntity.notFound().build();
//    }

    @GetMapping(VOUCHER_GET_BY_ID)
    public ResponseEntity<?> getVoucherById(@PathVariable UUID id) {
        PhieuGiamGia pgg = PGGService.findById(id).orElse(null);
        if (pgg != null) {
            phieuGiamGiaReponse pggReponse = new phieuGiamGiaReponse();
            PGGMapper.getDtoFromPhieuGiamGia(pggReponse, pgg);
            return ResponseEntity.ok().body(pggReponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(VOUCHER_DELETE)
    public ResponseEntity<?> setVoucherDelete(@PathVariable UUID id) {
        PhieuGiamGia pgg = PGGService.findById(id).orElse(null);
        if (pgg != null) {
            PGGService.setDeletedPhieuGiamGia(!pgg.getDeleted(), id);
            return ResponseEntity.ok().body("Set deleted id: " + id);
        }
        return ResponseEntity.notFound().build();
    }
}
