package org.example.backend.controllers.admin;

import org.example.backend.dto.request.DotGiamGiaRequest;
import org.example.backend.mapper.DotGiamGiaMapper;
import org.example.backend.models.DotGiamGia;
import org.example.backend.services.DotGiamGiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.SALE_CREATE;
import static org.example.backend.constants.api.Admin.SALE_DELETE;
import static org.example.backend.constants.api.Admin.SALE_GET_ALL;
import static org.example.backend.constants.api.Admin.SALE_UPDATE;

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
    public ResponseEntity<?> createSale(@RequestBody DotGiamGiaRequest dotGiamGiaRequest){
        DotGiamGia d = new DotGiamGia();
        System.out.println("dd >>>>>>>>>>>>>>>>>"+d);
        System.out.println(dotGiamGiaRequest);
        DotGiamGia dotGiamGia = dotGiamGiaMapper.toDotGiamGia(dotGiamGiaRequest);
        System.out.println(dotGiamGia);
        return ResponseEntity.ok().body(dotGiamGiaService.save(dotGiamGia));
    }

    @PutMapping(SALE_UPDATE)
    public ResponseEntity<?> updateSale(@RequestBody DotGiamGiaRequest dto, @PathVariable UUID id){
//        Optional<DotGiamGia> dotGiamGia = dotGiamGiaService.findById(id);
//        if (dotGiamGia.isPresent()) {
//            return ResponseEntity.ok().body(dotGiamGiaService.save(dotGiamGiaMapper.toDotGiamGia(dto)));
//        }
        return ResponseEntity.notFound().build();
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
