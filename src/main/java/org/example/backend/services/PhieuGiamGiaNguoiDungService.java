package org.example.backend.services;

import org.example.backend.models.PhieuGiamGiaNguoiDung;
import org.example.backend.repositories.PhieuGiamGiaNguoiDungRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhieuGiamGiaNguoiDungService extends GenericServiceImpl<PhieuGiamGiaNguoiDung, UUID> {
    private final PhieuGiamGiaNguoiDungRepository phieuGiamGiaNguoiDungRepository;

    public PhieuGiamGiaNguoiDungService(PhieuGiamGiaNguoiDungRepository repository) {
        super(repository);
        this.phieuGiamGiaNguoiDungRepository = repository;
    }
}
