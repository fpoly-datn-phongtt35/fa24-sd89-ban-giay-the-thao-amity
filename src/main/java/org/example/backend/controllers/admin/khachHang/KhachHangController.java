package org.example.backend.controllers.admin.khachHang;

import org.example.backend.dto.request.khachHang.KhachHangCreate;
import org.example.backend.dto.request.khachHang.KhachHangUpdate;
import org.example.backend.mapper.KhachHangMapper;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.services.KhachHangService;
import org.example.backend.services.NguoiDungService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.*;

@RestController
public class KhachHangController {
final KhachHangService khachHangService;
final KhachHangMapper khachHangMapper;
private final NguoiDungRepository nguoiDungRepository;

public KhachHangController(KhachHangService khachHangService, KhachHangMapper khachHangMapper, NguoiDungRepository nguoiDungRepository){
    this.khachHangService = khachHangService;
    this.khachHangMapper = khachHangMapper;
    this.nguoiDungRepository = nguoiDungRepository;
}
@GetMapping(CUSTOMER_GET_ALL)
    public ResponseEntity<?> getAllCustomer(){
    return ResponseEntity.ok().body(khachHangService.getAllKhachHang());
}
@PostMapping(CUSTOMER_CREATE)
    public ResponseEntity<?> createCustomer(@RequestBody KhachHangCreate khachHangCreate){
    NguoiDung nguoiDung = new NguoiDung();
    khachHangMapper.createToKhachHang(khachHangCreate);
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
    khachHangMapper.updateToKhachHang(khachHangUpdate,nd);
    return ResponseEntity.ok().body(khachHangService.save(nd));

}
@GetMapping(CUSTOMER_DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id){
    khachHangService.setDeletedKhachHang(id);
    return ResponseEntity.ok().body(khachHangService.getAllKhachHang());
}
}
