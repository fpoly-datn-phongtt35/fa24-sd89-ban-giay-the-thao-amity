package org.example.backend.controllers.admin.dotGiamGia;

import org.example.backend.constants.PaginationConstants;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaCreate;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaUpdate;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.mapper.dotGiamGia.DotGiamGiaMapper;
import org.example.backend.models.DotGiamGia;
import org.example.backend.services.DotGiamGiaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.SALE_CREATE;
import static org.example.backend.constants.api.Admin.SALE_DELETE;
import static org.example.backend.constants.api.Admin.SALE_GET_ALL;
import static org.example.backend.constants.api.Admin.SALE_GET_BY_ID;
import static org.example.backend.constants.api.Admin.SALE_SET_DELETE;
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
    public ResponseEntity<?> getAllSale() {
        return ResponseEntity.ok().body(dotGiamGiaService.getDotGiamGiaGetAll());
    }

    @GetMapping(SALE_GET_ALL)
    public ResponseEntity<?> getAllSalePaginate(@RequestParam(value = "page", defaultValue = "0") int page) {
//        Page items = dotGiamGiaService.dotGiamGiaGetAllPaginate(PageRequest.of(page, PaginationConstants.DEFAULT_PAGE_SIZE, PaginationConstants.DEFAULT_SORT_DIRECTION));
        return ResponseEntity.ok().body("");
    }

    @PostMapping(SALE_CREATE)
    public ResponseEntity<?> createSale(@RequestBody DotGiamGiaCreate dotGiamGiaCreate) {
        DotGiamGia d = new DotGiamGia();

        dotGiamGiaMapper.createDotGiamGiaFromDto(dotGiamGiaCreate, d);

        return ResponseEntity.ok().body(dotGiamGiaService.save(d));
    }

    @PutMapping(SALE_UPDATE)
    public ResponseEntity<?> updateSale(
            @PathVariable UUID id,
            @RequestBody DotGiamGiaUpdate dotGiamGiaUpdate) {
        Optional<DotGiamGia> existingDotGiamGia = dotGiamGiaService.findById(id);
        if (existingDotGiamGia.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        DotGiamGia dotGiamGia = existingDotGiamGia.get();
        dotGiamGiaMapper.updateDotGiamGiaFromDto(dotGiamGiaUpdate, dotGiamGia);
        return ResponseEntity.ok(dotGiamGiaService.save(dotGiamGia));
    }

    @DeleteMapping(SALE_DELETE)
    public ResponseEntity<?> deleteSale(@PathVariable UUID id) {
        Optional<DotGiamGia> dotGiamGia = dotGiamGiaService.findById(id);
        if (dotGiamGia.isPresent()) {
            dotGiamGiaService.deleteById(id);
            return ResponseEntity.ok().body("Delete id: " + id);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(SALE_GET_BY_ID)
    public ResponseEntity<?> getSaleById(@PathVariable UUID id) {
        DotGiamGia dotGiamGia = dotGiamGiaService.findById(id).orElse(null);
        if (dotGiamGia != null) {
            DotGiamGiaResponse dotGiamGiaResponse = new DotGiamGiaResponse();
            dotGiamGiaMapper.getDtoFromDotGiamGia(dotGiamGiaResponse, dotGiamGia);
            return ResponseEntity.ok().body(dotGiamGiaResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(SALE_SET_DELETE)
    public ResponseEntity<?> setSaleDelete(@PathVariable UUID id) {
        DotGiamGia d = dotGiamGiaService.findById(id).orElse(null);
        if (d != null) {
            dotGiamGiaService.setDeletedDotGiamGia(!d.getDeleted(), id);
            return ResponseEntity.ok().body("Set deleted id: " + id);
        }
        System.out.println("hehe");
        return ResponseEntity.notFound().build();
    }

}
