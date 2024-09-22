package org.example.backend.repositories;

import org.example.backend.models.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
}