package org.example.backend.controllers.admin.dotGiamGia;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaSpctCreate;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaUpdate;
import org.example.backend.mapper.dotGiamGia.DotGiamGiaMapper;
import org.example.backend.models.DotGiamGia;
import org.example.backend.models.DotGiamGiaSpct;
import org.example.backend.services.DotGiamGiaService;
import org.example.backend.services.DotGiamGiaSpctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class DotGiamGiaSpctController {
    @Autowired
    DotGiamGiaService dotGiamGiaService;

    @Autowired
    DotGiamGiaMapper dotGiamGiaMapper;
    final
    DotGiamGiaSpctService dotGiamGiaSpctService;

    public DotGiamGiaSpctController(DotGiamGiaSpctService dotGiamGiaSpctService) {
        this.dotGiamGiaSpctService = dotGiamGiaSpctService;
    }

    @PostMapping(Admin.SALE_DETAIL_CREATE)
    public ResponseEntity<?> createSaleDetail(@RequestBody DotGiamGiaSpctCreate dotGiamGiaSpctCreate){
        DotGiamGia dotGiamGia = dotGiamGiaService.findById(dotGiamGiaSpctCreate.getDotGiamGiaUpdate().getId()).orElse(null);
        if (dotGiamGia != null) {
            dotGiamGiaMapper.updateDotGiamGiaFromDto(dotGiamGiaSpctCreate.getDotGiamGiaUpdate(), dotGiamGia);
            dotGiamGiaSpctService.createDotGiamGiaWithSpct(dotGiamGia, dotGiamGiaSpctCreate.getIdSpcts());
        }
        // chua co
        DotGiamGia newDotGiamGia = new DotGiamGia();
        dotGiamGiaMapper.updateDotGiamGiaFromDto(dotGiamGiaSpctCreate.getDotGiamGiaUpdate(), newDotGiamGia);
        dotGiamGiaSpctService.createDotGiamGiaWithSpct(newDotGiamGia, dotGiamGiaSpctCreate.getIdSpcts());
        return ResponseEntity.ok().build();
    }

    @PutMapping(Admin.SALE_DETAIL_SET_DELETE)
    public ResponseEntity<?> setDeleteSaleDetail(@PathVariable UUID id){
        DotGiamGiaSpct dotGiamGiaSpct = dotGiamGiaSpctService.findById(id).orElse(null);
        if (dotGiamGiaSpct != null) {
            dotGiamGiaSpctService.setDeletedDotGiamGiaSpctById(!dotGiamGiaSpct.getDeleted(), id);
            return ResponseEntity.ok().body("Set deleted id: "+id);
        }
        return ResponseEntity.notFound().build();
    }

//    @GetMapping(Admin.SALE_DETAIL_GET_BY_ID_DGG)
//    public ResponseEntity<?> getSaleDetailByIDGG(@PathVariable UUID idDgg){
//
//    }

}
