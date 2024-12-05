package org.example.backend.services;

import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;
import org.example.backend.dto.response.traHang.TraHangResponse;
import org.example.backend.models.TraHang;
import org.example.backend.repositories.HoaDonChiTietRepository;
import org.example.backend.repositories.HoaDonRepository;
import org.example.backend.repositories.TraHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class TraHangService extends GenericServiceImpl<TraHang, UUID> {
   private final TraHangRepository traHangRepository;
    public TraHangService(JpaRepository<TraHang, UUID> repository, TraHangRepository traHangRepository) {
        super(repository);
        this.traHangRepository = traHangRepository;
    }


    public List<hoaDonChiTietReponse> getHoaDonByIdNguoiDung(UUID idNguoiDung) {
        return traHangRepository.getHoaDonByIdKh(idNguoiDung);
    }
    public List<hoaDonChiTietReponse> getHoaDonCtById(UUID id) {
        return traHangRepository.getHoaDonCtByID(id);
    }

}
