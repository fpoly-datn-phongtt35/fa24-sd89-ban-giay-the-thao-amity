package org.example.backend.controllers.admin.sanpham;

import org.example.backend.constants.api.Admin;
import org.example.backend.models.KichThuoc;
import org.example.backend.repositories.KichThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class KichThuocController {
    @Autowired
    KichThuocRepository kichThuocRepository;
    @GetMapping(Admin.SIZE_GET_ALL)
    public String getAllKichThuoc(Model model) {
        model.addAttribute("list", kichThuocRepository.getAllKichThuoc());
        model.addAttribute("kichthuoc", new KichThuoc());
        return "sanpham/KichThuoc";
    }
    @PostMapping(Admin.SIZE_CREATE)
    public String create(@ModelAttribute("kichthuoc") KichThuoc kichThuoc) {
        kichThuocRepository.save(kichThuoc);
        return "redirect:/api/v1/admin/size/all";
    }
    @GetMapping(Admin.SIZE_UPDATE)
    public String update(@PathVariable UUID id, @ModelAttribute("kichthuoc") KichThuoc kichThuoc) {
        KichThuoc kt1 = kichThuocRepository.findById(id).orElse(null);
        if (kt1 != null) {
            kichThuocRepository.deletedKichThuoc(!kt1.getDeleted(), kichThuoc.getId());
        }
        return "redirect:/api/v1/admin/size/all";
    }
}
