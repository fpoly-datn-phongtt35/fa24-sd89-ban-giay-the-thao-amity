package org.example.backend.controllers.admin.sanpham;

import org.example.backend.repositories.HangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HangController {
    final
    HangRepository HangRepository;

    public HangController(HangRepository HangRepository) {
        this.HangRepository = HangRepository;
    }

}
