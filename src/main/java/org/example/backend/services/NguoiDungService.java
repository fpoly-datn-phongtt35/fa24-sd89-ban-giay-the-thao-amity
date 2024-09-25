package org.example.backend.services;

import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NguoiDungService extends GenericServiceImpl<NguoiDung , UUID> {
    public NguoiDungService(JpaRepository<NguoiDung, UUID> repository , NguoiDungRepository nhanVienRespository) {
        super(repository);
        this.nhanVienRespository = nhanVienRespository;
    }
    private final NguoiDungRepository nhanVienRespository;

    public List<NhanVienRespon> getAllNhanVien(){
        return nhanVienRespository.getAllNhanVien();
    }
}
