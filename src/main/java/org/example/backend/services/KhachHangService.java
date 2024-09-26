package org.example.backend.services;

import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.mapper.KhachHangMapper;
import org.example.backend.mapper.NhanVienMapper;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class KhachHangService extends GenericServiceImpl<NguoiDung, UUID>{
    private final KhachHangMapper khachHangMapper;
public KhachHangService(JpaRepository<NguoiDung, UUID> repository, NguoiDungRepository khachHangRespository, KhachHangMapper  khachHangMapper){
    super(repository);
    this.khachHangRespository = khachHangRespository;
    this.khachHangMapper = khachHangMapper;
}
private final NguoiDungRepository khachHangRespository;
public List<KhachHangResponse> getAllKhachHang(){
    return  khachHangRespository.getAllKhachHang();
}
}
