package org.example.backend.controllers.admin.khachHang;

import org.example.backend.dto.request.khachHang.KhachHangCreate;
import org.example.backend.dto.request.khachHang.KhachHangUpdate;
import org.example.backend.mapper.khachHang.KhachHangMapper;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.services.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.*;

@RestController
public class KhachHangController {
    final
    KhachHangMapper khachHangMapper;
final KhachHangService khachHangService;

private final NguoiDungRepository nguoiDungRepository;

public KhachHangController(KhachHangService khachHangService, NguoiDungRepository nguoiDungRepository, KhachHangMapper khachHangMapper){
    this.khachHangService = khachHangService;
    this.nguoiDungRepository = nguoiDungRepository;
    this.khachHangMapper = khachHangMapper;
}
@GetMapping(CUSTOMER_GET_ALL)
    public ResponseEntity<?> getAllCustomer(){
    return ResponseEntity.ok().body(khachHangService.getAllKhachHang());
}
@PostMapping(CUSTOMER_CREATE)
    public ResponseEntity<?> createCustomer(@RequestBody KhachHangCreate khachHangCreate){
    NguoiDung nguoiDung = new NguoiDung();
    khachHangMapper.createNguoiDungFromDto(khachHangCreate,nguoiDung);
    return ResponseEntity.ok().body(khachHangService.save(nguoiDung));
}
@PutMapping(CUSTOMER_UPDATE)
    public ResponseEntity<?> updateCustomer(
        @PathVariable UUID id,
        @RequestBody KhachHangUpdate khachHangUpdate){
    Optional<NguoiDung> exitNguoiDung = khachHangService.findById(id);
    if(exitNguoiDung.isEmpty()){
        return ResponseEntity.notFound().build();
    }
    NguoiDung nd = exitNguoiDung.get();
    khachHangMapper.updateNguoiDungFromDto(khachHangUpdate,nd);
    return ResponseEntity.ok().body(khachHangService.save(nd));

}
@DeleteMapping(CUSTOMER_DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id){
    NguoiDung nd = khachHangService.findById(id).orElse(null);

    if(nd != null){
        khachHangService.setDeletedKhachHang(id);
        return ResponseEntity.ok().body("Deleted id: " + id);
    }
    return ResponseEntity.notFound().build();

}

}
