package org.example.backend.services;

import org.example.backend.models.GioHang;
import org.example.backend.repositories.GioHangRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GioHangService extends GenericServiceImpl<GioHang, UUID> {
    public GioHangService(JpaRepository<GioHang, UUID> repository, GioHangRepository gioHangRepository) {
        super(repository);
        this.gioHangRepository = gioHangRepository;
    }

    private final GioHangRepository gioHangRepository;

    public GioHang finbyIDGioHang(UUID id){
        return gioHangRepository.finbyIDKH(id);
    }
}
