package org.example.backend.controllers.admin.khachHang;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.dto.request.khachHang.KhachHangCreate;
import org.example.backend.dto.request.khachHang.KhachHangUpdate;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.mapper.khachHang.KhachHangMapper;
import org.example.backend.models.Hang;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.services.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.*;

@RestController
public class KhachHangController {
    final KhachHangMapper khachHangMapper;
    final KhachHangService khachHangService;
    private final NguoiDungRepository nguoiDungRepository;
    private Cloudinary cloudinary;

public KhachHangController(KhachHangService khachHangService, NguoiDungRepository nguoiDungRepository, KhachHangMapper khachHangMapper, Cloudinary cloudinary){
    this.khachHangService = khachHangService;
    this.nguoiDungRepository = nguoiDungRepository;
    this.khachHangMapper = khachHangMapper;
    this.cloudinary = cloudinary;
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
    @GetMapping(CUSTOMER_GET_BY_ID)
    public ResponseEntity<?> getKhachHangById(
            @PathVariable UUID id
    ){
        List<KhachHangResponse> khachHangById = khachHangService.getKhachHangById(id);
        return ResponseEntity.ok(khachHangById);
    }
//@PostMapping(CUSTOMER_CREATE)
//    public ResponseData<KhachHangResponse> createCustomer(@RequestBody KhachHangCreate khachHangCreate){
//
//    NguoiDung nguoiDung = new NguoiDung();
//    khachHangMapper.createNguoiDungFromDto(khachHangCreate,nguoiDung);
//    khachHangService.save(nguoiDung);
//    return ResponseData.<KhachHangResponse>builder()
//            .status(HttpStatus.CREATED.value())
//            .message("Customer role created successfully")
//            .build();
//}

    @PostMapping(CUSTOMER_CREATE)
    public ResponseEntity<?> createCustomer(
            @RequestParam("ma") String ma,
            @RequestParam("ten") String ten,
            @RequestParam("email") String email,
            @RequestParam("sdt") String sdt,
            @RequestParam("ngaySinh") Instant ngaySinh,
            @RequestParam("gioiTinh") String gioiTinh,
            @RequestParam("diaChi") String diaChi,
            @RequestParam("hinhAnh") MultipartFile fileHinhAnh,
            @RequestParam("chucVu") String chucVu,
             @RequestParam("trangThai") String trangThai
    ) throws IOException {

        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setMa(ma);
        nguoiDung.setTen(ten);
        nguoiDung.setEmail(email);
        nguoiDung.setSdt(sdt);
        nguoiDung.setNgaySinh(ngaySinh);
        nguoiDung.setGioiTinh(gioiTinh);
        nguoiDung.setDiaChi(diaChi);

        Map<String, Object> uploadResult = cloudinary.uploader().upload(fileHinhAnh.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("secure_url");
        nguoiDung.setHinhAnh(imageUrl);
        nguoiDung.setChucVu(chucVu);
        nguoiDung.setTrangThai(trangThai);
        return ResponseEntity.ok(nguoiDungRepository.save(nguoiDung));


    }

//    ){
//
//
////        khachHangMapper.createNguoiDungFromDto(khachHangCreate,nguoiDung);
////        ;
//        return ResponseEntity.ok().body(khachHangService.save(nguoiDung));
//    }
@PutMapping(CUSTOMER_UPDATE)
    public ResponseEntity<?> updateCustomer(
        @PathVariable UUID id,
        @RequestParam(value = "ma",defaultValue = "") String ma,
        @RequestParam(value = "email",defaultValue = "") String email,
        @RequestParam(value = "sdt",defaultValue = "") String sdt,
        @RequestParam(value = "ten",defaultValue = "") String ten,
        @RequestParam(value = "diaChi",defaultValue = "") String diaChi,
        @RequestParam(value = "ngaySinh",defaultValue = "") Instant ngaySinh,
        @RequestParam(value = "gioiTinh",defaultValue = "") String gioiTinh,
        @RequestParam(value = "hinhAnh" ,defaultValue = "") MultipartFile hinhAnh,
        @RequestParam(value = "chucVu", defaultValue = "") String chucVu,
        @RequestParam(value = "trangThai" ,defaultValue = "") String trangThai
) throws IOException {
    Optional<NguoiDung> exitNguoiDung = khachHangService.findById(id);
    if (exitNguoiDung.isEmpty()) {
        return null;
    }
    NguoiDung nguoiDung = exitNguoiDung.get();
    nguoiDung.setId(id);
    nguoiDung.setMa(ma);
    nguoiDung.setEmail(email);
    nguoiDung.setSdt(sdt);
    nguoiDung.setTen(ten);
    nguoiDung.setDiaChi(diaChi);
    nguoiDung.setNgaySinh(ngaySinh);
    nguoiDung.setGioiTinh(gioiTinh);
    Map<String, Object> uploadResult = cloudinary.uploader().upload(hinhAnh.getBytes(), ObjectUtils.emptyMap());
    String imageUrl = (String) uploadResult.get("secure_url");
    nguoiDung.setHinhAnh(imageUrl);
    nguoiDung.setChucVu(chucVu);
    nguoiDung.setTrangThai(trangThai);

    khachHangService.save(nguoiDung);
    return ResponseEntity.ok(khachHangService.save(nguoiDung));
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
    public ResponseEntity<?> searchUserKhachHang(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "keyword" , defaultValue = "") String keyword,
            @RequestParam(value = "gioiTinh", defaultValue = "") String gioiTinh,
            @RequestParam(value = "trangThai", defaultValue = "") String trangThai
    ) {
        PageResponse<List<KhachHangResponse>> result = khachHangService.searchKhachHang(page, size,keyword, gioiTinh, trangThai);
        ResponseData<PageResponse<List<KhachHangResponse>>> responseData = ResponseData.<PageResponse<List<KhachHangResponse>>>builder()
                .message("Search Sale")
                .status(HttpStatus.OK.value())
                .data(result).build();
        return ResponseEntity.ok().body(responseData);
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


    @GetMapping(CUSTOMER_GET_BY_SDT)
    public ResponseEntity<?> getAllCustomerBySDT( @RequestParam(value = "sdt" , defaultValue = "") String sdt){
        return ResponseEntity.ok(nguoiDungRepository.timKiemSDT(sdt));
    }

}
