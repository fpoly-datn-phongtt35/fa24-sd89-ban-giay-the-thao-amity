package org.example.backend.controllers.admin.khachHang;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.dto.request.khachHang.KhachHangCreate;
import org.example.backend.dto.request.khachHang.KhachHangUpdate;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.mapper.khachHang.KhachHangMapper;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.services.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.*;

@RestController
public class KhachHangController {
    final
    KhachHangMapper khachHangMapper;
final KhachHangService khachHangService;

private final NguoiDungRepository nguoiDungRepository;

public KhachHangController(KhachHangService khachHangService, NguoiDungRepository nguoiDungRepository, KhachHangMapper khachHangMapper){
    this.khachHangService = khachHangService;
    this.nguoiDungRepository = nguoiDungRepository;
    this.khachHangMapper = khachHangMapper;
}
@GetMapping(CUSTOMER_GET_ALL)
    public ResponseEntity<ResponseData<PageResponse<List<KhachHangResponse>>>> getAllCustomer(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "5") int size)
{
    PageResponse<List<KhachHangResponse>> khachHangPage = khachHangService.getAllKhachHang(page, size);
    ResponseData<PageResponse<List<KhachHangResponse>>> responseData = ResponseData.<PageResponse<List<KhachHangResponse>>>builder()
            .message("Get all users done")
            .status(HttpStatus.OK.value())
            .data(khachHangPage)
            .build();
    return ResponseEntity.ok(responseData);
}
@PostMapping(CUSTOMER_CREATE)
    public ResponseData<KhachHangResponse> createCustomer(@RequestBody KhachHangCreate khachHangCreate){

    NguoiDung nguoiDung = new NguoiDung();
    khachHangMapper.createNguoiDungFromDto(khachHangCreate,nguoiDung);
    khachHangService.save(nguoiDung);
    return ResponseData.<KhachHangResponse>builder()
            .status(HttpStatus.CREATED.value())
            .message("Customer role created successfully")
            .build();
}
@PutMapping(CUSTOMER_UPDATE)
    public ResponseData<KhachHangResponse> updateCustomer(
        @PathVariable UUID id,
        @RequestBody KhachHangUpdate khachHangUpdate){
    Optional<NguoiDung> exitNguoiDung = khachHangService.findById(id);
    if(exitNguoiDung.isEmpty()){
        return null;
    }
    NguoiDung nd = exitNguoiDung.get();
    khachHangMapper.updateNguoiDungFromDto(khachHangUpdate,nd);
    khachHangService.save(nd);
    return ResponseData.<KhachHangResponse>builder()
            .status(HttpStatus.CREATED.value())
            .message("Customer role update successfully")
            .build();

}
@DeleteMapping(CUSTOMER_DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id){
    NguoiDung nd = khachHangService.findById(id).orElse(null);

    if(nd != null){
        khachHangService.setDeletedKhachHang(id);
        return ResponseEntity.ok().body("Deleted id: " + id);
    }
    return ResponseEntity.notFound().build();

}

    @GetMapping(CUSTOMER_GET_BY_KH)
    public ResponseEntity<List<KhachHangResponse>> searchUserKhachHang(
            @RequestParam(value = "name") String name
    ) {
        List<KhachHangResponse> result = khachHangService.searchKhachHang("%"+name+"%");
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(PAGE_CUSTOMER)
    public ResponseEntity<ResponseData<PageResponse<List<KhachHangResponse>>>> getAllCustomerPage(
            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable phanTrang = PageRequest.of(page, itemsPerPage);
        Page<KhachHangResponse> khachHangPage = khachHangService.getAllKhachHangPage(phanTrang);

        PageResponse<List<KhachHangResponse>> pageResponse = PageResponse.<List<KhachHangResponse>>builder()
                .page(khachHangPage.getNumber())
                .size(khachHangPage.getSize())
                .totalPage(khachHangPage.getTotalPages())
                .items(khachHangPage.getContent())
                .build();

        ResponseData<PageResponse<List<KhachHangResponse>>> responseData = ResponseData.<PageResponse<List<KhachHangResponse>>>builder()
                .message("Get paginated users done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }

}
