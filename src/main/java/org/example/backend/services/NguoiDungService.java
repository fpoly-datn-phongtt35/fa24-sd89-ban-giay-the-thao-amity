package org.example.backend.services;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.nhanVien.NhanVienRequestAdd;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.mapper.NhanVienMapper;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.USER_CREATE;

@Service
public class NguoiDungService extends GenericServiceImpl<NguoiDung , UUID> {
    private final NhanVienMapper nhanVienMapper;

    public NguoiDungService(JpaRepository<NguoiDung, UUID> repository , NguoiDungRepository nhanVienRespository, NhanVienMapper nhanVienMapper) {
        super(repository);
        this.nhanVienRespository = nhanVienRespository;
        this.nhanVienMapper = nhanVienMapper;
    }
    private final NguoiDungRepository nhanVienRespository;

    public List<NhanVienRespon> getAllNhanVien(){
        return nhanVienRespository.getAllNhanVien();
    }
    public void setDeletedNhanVien(UUID id){
        nhanVienRespository.deleteNhanVienStatus(id);
    }
}
