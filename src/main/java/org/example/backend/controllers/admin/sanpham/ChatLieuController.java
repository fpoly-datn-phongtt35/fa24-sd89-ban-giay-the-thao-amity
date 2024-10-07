package org.example.backend.controllers.admin.sanpham;

import org.example.backend.constants.api.Admin;
import org.example.backend.models.ChatLieu;
import org.example.backend.models.MauSac;
import org.example.backend.repositories.ChatLieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ChatLieuController {
    @Autowired
    ChatLieuRepository chatLieuRepository;
    @GetMapping(Admin.MATERIAL_GET_ALL)
    public ResponseEntity<?> getAllChatLieu() {
        return ResponseEntity.ok(chatLieuRepository.getAll());
    }
    @PostMapping(Admin.MATERIAL_CREATE)
    public ResponseEntity<?> createChatLieu(@RequestBody ChatLieu chatLieu) {
        ChatLieu m = new ChatLieu();
        m.setMa(chatLieu.getMa());
        m.setTen(chatLieu.getTen());
        m.setTrangThai(chatLieu.getTrangThai());
        return ResponseEntity.ok(chatLieuRepository.save(m));
    }

    @PutMapping(Admin.MATERIAL_SET_UPDATE)
    public ResponseEntity<?>  updateChatLieu(@PathVariable UUID id) {
        ChatLieu m = chatLieuRepository.findById(id).orElse(null);
        if(m!=null){
            chatLieuRepository.setdeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }
}
