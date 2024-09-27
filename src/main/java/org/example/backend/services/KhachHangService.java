package org.example.backend.services;

import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class KhachHangService extends GenericServiceImpl<NguoiDung, UUID>{

public KhachHangService(JpaRepository<NguoiDung, UUID> repository, NguoiDungRepository khachHangRespository){
    super(repository);
    this.khachHangRespository = khachHangRespository;

}
private final NguoiDungRepository khachHangRespository;
public List<KhachHangResponse> getAllKhachHang(){
    return  khachHangRespository.getAllKhachHang();
}
    public void setDeletedKhachHang(UUID id){
khachHangRespository.deletedKhachHangStatus(id);
    }
}
