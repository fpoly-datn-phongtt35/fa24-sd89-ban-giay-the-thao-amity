package org.example.backend.controllers.admin;


import org.example.backend.mapper.NhanVienMapper;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.services.NguoiDungService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import static org.example.backend.constants.api.Admin.USER_GET_ALL;

@RestController
public class NhanVienController {
    final NguoiDungService nhanVienService;
    final NhanVienMapper nhanVienMapper;
    public NhanVienController(NguoiDungService nhanVienService, NhanVienMapper nhanVienMapper){
        this.nhanVienService = nhanVienService;
        this.nhanVienMapper = nhanVienMapper;
    }

    @GetMapping(USER_GET_ALL)
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok().body(nhanVienService.getAllNhanVien());
    }
}
