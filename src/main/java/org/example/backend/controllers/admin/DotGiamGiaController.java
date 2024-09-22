package org.example.backend.controllers.admin;

import org.example.backend.dto.DotGiamGiaDTO;
import org.example.backend.mapper.DotGiamGiaMapper;
import org.example.backend.models.DotGiamGia;
import org.example.backend.services.DotGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.SALE_CREATE;
import static org.example.backend.constants.api.Admin.SALE_DELETE;
import static org.example.backend.constants.api.Admin.SALE_GET_ALL;

@RestController
public class DotGiamGiaController {
     final DotGiamGiaService dotGiamGiaService;

    public DotGiamGiaController(DotGiamGiaService dotGiamGiaService, DotGiamGiaMapper dotGiamGiaMapper) {
        this.dotGiamGiaService = dotGiamGiaService;
        this.dotGiamGiaMapper = dotGiamGiaMapper;
    }

    final DotGiamGiaMapper dotGiamGiaMapper;


    @GetMapping(SALE_GET_ALL)
    public ResponseEntity<?> getAllSale(){
        return ResponseEntity.ok().body(dotGiamGiaService.findAll());
    }

    @PostMapping(SALE_CREATE)
    public ResponseEntity<?> createSale(@RequestBody DotGiamGiaDTO dotGiamGiaDTO){
        dotGiamGiaService.save(dotGiamGiaMapper.toDotGiamGia(dotGiamGiaDTO));
        return ResponseEntity.ok().body(dotGiamGiaMapper.toDotGiamGia(dotGiamGiaDTO));
    }

    @DeleteMapping(SALE_DELETE)
    public ResponseEntity<?> deleteSale(@PathVariable UUID id){
        Optional<DotGiamGia> dotGiamGia = dotGiamGiaService.findById(id);
        if (dotGiamGia.isPresent()) {
            dotGiamGiaService.deleteById(id);
            return ResponseEntity.ok().body(dotGiamGiaService.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

}
