package org.example.backend.services;

import org.example.backend.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class NguoiDungService extends GenericServiceImpl<NguoiDung , UUID> {
    public NguoiDungService(JpaRepository<NguoiDung, UUID> repository) {
        super(repository);
    }
}
