package org.example.backend.controllers.admin.sanpham;

import org.example.backend.constants.api.Admin;
import org.example.backend.models.Hang;
import org.example.backend.repositories.HangRepository;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
//@RestController
public class HangController {
    final
    HangRepository HangRepository;

    public HangController(HangRepository HangRepository) {
        this.HangRepository = HangRepository;
    }
    @GetMapping(Admin.COMPANY_GET_ALL)
    public String getAll(Model model) {
        model.addAttribute("list", HangRepository.getAll());
        model.addAttribute("hang", new Hang());
        return "sanpham/hang";
    }
    @PostMapping(Admin.COMPANY_CREATE)
    public String create(@ModelAttribute("hang") Hang hang) {
        HangRepository.save(hang);
        return "redirect:/api/v1/admin/company/all";
    }
    @PutMapping(Admin.COMPANY_UPDATE)
    public String update(@PathVariable UUID id, @ModelAttribute("hang") Hang hang) {
        Hang newHang = HangRepository.findById(id).orElse(null);
        if(newHang != null) {
            HangRepository.deletedHang(!newHang.getDeleted(), newHang.getId());
        }
        return "redirect:/api/v1/admin/company/all";
    }
}
