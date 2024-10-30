package org.example.backend.controllers.admin.traHang;

import org.example.backend.models.TraHang;
import org.example.backend.repositories.TraHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.backend.constants.api.Admin.PRODUCT_RETURN_GET_ALL;
@RestController
public class traHangController {
    @Autowired
    TraHangRepository traHangRepository;
    @GetMapping(PRODUCT_RETURN_GET_ALL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(traHangRepository.getAllTraHang());
    }

}
