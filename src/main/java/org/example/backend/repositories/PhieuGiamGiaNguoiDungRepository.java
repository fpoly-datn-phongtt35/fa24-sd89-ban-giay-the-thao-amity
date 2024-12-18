package org.example.backend.repositories;

import org.example.backend.models.PhieuGiamGiaNguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhieuGiamGiaNguoiDungRepository extends JpaRepository<PhieuGiamGiaNguoiDung, UUID> {
    // Các phương thức tùy chỉnh có thể được thêm vào đây nếu cần
} 