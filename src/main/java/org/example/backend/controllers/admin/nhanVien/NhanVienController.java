package org.example.backend.controllers.admin.nhanVien;


import org.example.backend.dto.request.nhanVien.NhanVienRequestAdd;
import org.example.backend.dto.request.nhanVien.NhanVienRequestUpdate;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.mapper.NhanVienMapper;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.services.NguoiDungService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.*;

@RestController
public class NhanVienController {
    final NguoiDungService nhanVienService;
    final NhanVienMapper nhanVienMapper;
    private final NguoiDungRepository nguoiDungRepository;

    public NhanVienController(NguoiDungService nhanVienService, NhanVienMapper nhanVienMapper, NguoiDungRepository nguoiDungRepository) {
        this.nhanVienService = nhanVienService;
        this.nhanVienMapper = nhanVienMapper;
        this.nguoiDungRepository = nguoiDungRepository;
    }

    @GetMapping(USER_GET_ALL)
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok().body(nhanVienService.getAllNhanVien());
    }

    @PostMapping(USER_CREATE)
    public ResponseEntity<?> createNhanVien(@RequestBody NhanVienRequestAdd nhanVienRequestAdd) {
        NguoiDung nd = new NguoiDung();

        nhanVienMapper.createToNhanVien(nhanVienRequestAdd,nd);

        return ResponseEntity.ok().body(nhanVienService.save(nd));
    }
    @PutMapping(USER_UPDATE)
    public ResponseEntity<?> updateNhanVien(
            @PathVariable UUID id,
            @RequestBody NhanVienRequestUpdate nhanVienRequestUpdate){
        Optional<NguoiDung> exitNguoiDung = nhanVienService.findById(id);
        if (exitNguoiDung.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        NguoiDung nd = exitNguoiDung.get();
        nhanVienMapper.updateToNhanVien(nhanVienRequestUpdate,nd);
        return ResponseEntity.ok().body(nhanVienService.save(nd));
    }
    @GetMapping(USER_DELETE)
    public ResponseEntity<?> deleteNhanVien(@PathVariable UUID id) {
//        NguoiDung nd = nguoiDungRepository.getOne(id);
        nhanVienService.setDeletedNhanVien(id);
        return ResponseEntity.ok().body(nhanVienService.getAllNhanVien());
    }

    @GetMapping(PAGE_USER)
    public ResponseEntity<?> getAllUserPage(
            @RequestParam(value = "itemsPerPage" , defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable phanTrang = PageRequest.of(page,1);
        List<NhanVienRespon> users = nhanVienService.getAllNhanVienPage(phanTrang);
        return ResponseEntity.ok().body(users);
    }
}