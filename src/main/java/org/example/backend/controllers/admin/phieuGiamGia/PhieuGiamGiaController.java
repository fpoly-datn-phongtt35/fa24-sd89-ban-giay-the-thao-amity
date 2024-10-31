package org.example.backend.controllers.admin.phieuGiamGia;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.phieuGiamGia.phieuGiamGiaRequestAdd;
import org.example.backend.dto.request.phieuGiamGia.phieuGiamGiaRequestUpdate;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.mapper.phieuGiamGia.phieuGiamGiaMapper;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.services.PhieuGiamGiaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.VOUCHER_CREATE;
import static org.example.backend.constants.api.Admin.VOUCHER_DELETE;
import static org.example.backend.constants.api.Admin.VOUCHER_EXCEL;
import static org.example.backend.constants.api.Admin.VOUCHER_GET_BY_ID;
import static org.example.backend.constants.api.Admin.VOUCHER_SEARCH;
import static org.example.backend.constants.api.Admin.VOUCHER_UPDATE;

@RestController
public class PhieuGiamGiaController {

//    @Autowired
    final PhieuGiamGiaService PGGService;

    final phieuGiamGiaMapper PGGMapper;

    public PhieuGiamGiaController(PhieuGiamGiaService PGGService,phieuGiamGiaMapper PGGMapper){
        this.PGGService = PGGService;
        this.PGGMapper = PGGMapper;
    }

    @GetMapping(Admin.VOUCHER_GET_ALL)
    public ResponseEntity<?> getALlVoucher(@RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "") String keyFind,
                                           @RequestParam(required = false, defaultValue = "") String trangThai,
                                           @RequestParam(required = false) Instant minNgay,
                                           @RequestParam(required = false) Instant maxNgay,
                                           @RequestParam(required = false) BigDecimal minGia,
                                           @RequestParam(required = false) BigDecimal maxGia,
//                                           @RequestParam(value = "loai",required = false, defaultValue = "0") Integer loai,
                                           @RequestParam(required = false, defaultValue = "1") Integer sapXep
    ){
        PageResponse<List<phieuGiamGiaReponse>> PGGPage = PGGService.searchPGG(page, itemsPerPage,keyFind,trangThai,sapXep,minNgay,maxNgay,minGia,maxGia);
        ResponseData<PageResponse<List<phieuGiamGiaReponse>>> responseData = ResponseData.<PageResponse<List<phieuGiamGiaReponse>>>builder()
                .message("Get all voucher done")
                .status(HttpStatus.OK.value())
                .data(PGGPage)
                .build();

        return ResponseEntity.ok(responseData);
    }

    @PostMapping(VOUCHER_CREATE)
    public ResponseEntity<?> createVoucher(@RequestBody phieuGiamGiaRequestAdd PGGadd) {
        PhieuGiamGia pgg = new PhieuGiamGia();
        PGGMapper.createPhieuGiamGiaFromDto(PGGadd, pgg);
        return ResponseEntity.ok().body(PGGService.save(pgg));
    }

    @PutMapping(VOUCHER_UPDATE)
    public ResponseEntity<?> updateVoucher(
            @PathVariable UUID id,
            @RequestBody phieuGiamGiaRequestUpdate PGGupdate) {
        Optional<PhieuGiamGia> existingPGG = PGGService.findById(id);
        if (existingPGG.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PhieuGiamGia pgg = existingPGG.get();
        PGGMapper.updatePhieuGiamGiaFromDto(PGGupdate, pgg);
        return ResponseEntity.ok(PGGService.save(pgg));
    }

//    @DeleteMapping(VOUCHER_DELETE)
//    public ResponseEntity<?> deleteVoucher(@PathVariable UUID id) {
//        Optional<PhieuGiamGia> pgg = PGGService.findById(id);
//        if (pgg.isPresent()) {
//            PGGService.deleteById(id);
//            return ResponseEntity.ok().body("Delete id: " + id);
//        }
//        return ResponseEntity.notFound().build();
//    }

    @GetMapping(VOUCHER_GET_BY_ID)
    public ResponseEntity<?> getVoucherById(@PathVariable UUID id) {
        PhieuGiamGia pgg = PGGService.findById(id).orElse(null);
        if (pgg != null) {
            phieuGiamGiaReponse pggReponse = new phieuGiamGiaReponse();
            PGGMapper.getDtoFromPhieuGiamGia(pggReponse, pgg);
            return ResponseEntity.ok().body(pggReponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(VOUCHER_DELETE)
    public ResponseEntity<?> setVoucherDelete(@PathVariable UUID id) {
        PhieuGiamGia pgg = PGGService.findById(id).orElse(null);
        if (pgg != null) {
            PGGService.setDeletedPhieuGiamGia(!pgg.getDeleted(), id);
            return ResponseEntity.ok().body("Set deleted id: " + id);
        }
        return ResponseEntity.notFound().build();
    }

//    @GetMapping(VOUCHER_SEARCH)
//    public ResponseEntity<?> SearchPGG(@PathVariable String find,
//                                       @PathVariable String filterType
//    ){
//        String filter = "";
//        if(filterType.equals("tienMat")) filter = "and p.loai = true ";
//        if(filterType.equals("phanTram")) filter = "and p.loai = false";
//        if(filterType.equals("dangHoatDong")) filter = "and p.trangThai like \"%Hoạt Động%\"";
//        if(filterType.equals("ngungHoatDong")) filter = "and p.trangThai like \"%ngừng hoạt động%\"";
//        List<phieuGiamGiaReponse> result = PGGService.searchPGG("%"+find+"%" , filterType);
//        if(result.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(result);
//    }

    @GetMapping(VOUCHER_EXCEL)
    public ResponseEntity<byte[]> exportExcel() {
        try {
            List<phieuGiamGiaReponse> voucherList = PGGService.getPGGGetAll(); // Lấy dữ liệu phiếu giảm giá
            byte[] excelData = PGGService.exportToExcel(voucherList);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=phieuGiamGia.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}

