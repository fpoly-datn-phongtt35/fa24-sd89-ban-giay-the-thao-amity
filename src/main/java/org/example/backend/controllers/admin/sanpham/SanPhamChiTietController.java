package org.example.backend.controllers.admin.sanpham;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.SanPhamChiTietRequest;
import org.example.backend.dto.request.sanPham.SanPhamChiTietSearchRequest;
import org.example.backend.dto.response.SanPham.SanPhamChiTietRespon;
import org.example.backend.dto.response.SanPham.SanPhamClientResponse;
import org.example.backend.dto.response.SanPham.SanPhamResponse;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.models.*;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.example.backend.repositories.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.*;

@RestController
public class SanPhamChiTietController {
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @GetMapping(Admin.PRODUCT_DETAIL_GET_ALL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(sanPhamChiTietRepository.getAll());
    }



    public String saveImageFile(MultipartFile file) throws IOException {

        String uploadDir = new File("src/main/resources/static/images").getAbsolutePath();

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        Path filePath = Paths.get(uploadDir, fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/images/" + fileName;
    }


    @PostMapping(value = Admin.PRODUCT_DETAIL_CREATE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(

            @RequestParam("idHang") Hang idHang,
            @RequestParam("idDanhMuc") DanhMuc idDanhMuc,
            @RequestParam("idDeGiay") DeGiay idDeGiay,
            @PathVariable("id") UUID idSanPham,
            @RequestParam("idMauSac") MauSac idMauSac,
            @RequestParam("idKichThuoc") KichThuoc idKichThuoc,
            @RequestParam("soLuong") int soLuong,
            @RequestParam("giaBan") BigDecimal giaBan,
            @RequestParam("giaNhap") BigDecimal giaNhap,
            @RequestParam("trangThai") String trangThai,
            @RequestParam("moTa") String moTa,

            @RequestParam("hinhAnh") MultipartFile hinhAnhFile) throws IOException {

        try {
            SanPhamChiTiet s = new SanPhamChiTiet();

            s.setIdHang(idHang);
            s.setIdDanhMuc(idDanhMuc);
            s.setIdDeGiay(idDeGiay);
            s.setIdSanPham(sanPhamRepository.findById(idSanPham).orElse(null));
            s.setIdMauSac(idMauSac);
            s.setIdKichThuoc(idKichThuoc);
            s.setSoLuong(soLuong);
            s.setGiaBan(giaBan);
            s.setGiaNhap(giaNhap);
            s.setTrangThai(trangThai);
            s.setMoTa(moTa);

            Map<String, Object> uploadResult = cloudinary.uploader().upload(hinhAnhFile.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");
            s.setHinhAnh(imageUrl);

            return ResponseEntity.ok(sanPhamChiTietRepository.save(s));
        }catch (IOException e){
            return ResponseEntity.status(500).body("Tải lên hình ảnh thất bại: " + e.getMessage());
        }
    }



    @PutMapping(value = Admin.PRODUCT_DETAIL_UPDATE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @PathVariable("id") UUID id,
            @RequestParam("idHang") Hang idHang,
            @RequestParam("idDanhMuc") DanhMuc idDanhMuc,
            @RequestParam("idDeGiay") DeGiay idDeGiay,
            @PathVariable("idSanPham") UUID idSanPham,
            @RequestParam("idMauSac") MauSac idMauSac,
            @RequestParam("idKichThuoc") KichThuoc idKichThuoc,
            @RequestParam("soLuong") int soLuong,
            @RequestParam("giaBan") BigDecimal giaBan,
            @RequestParam("giaNhap") BigDecimal giaNhap,
            @RequestParam("trangThai") String trangThai,
            @RequestParam("moTa") String moTa,
            @RequestPart(value = "hinhAnh", required = false) MultipartFile hinhAnhFile) {
        try {
            SanPhamChiTiet s = sanPhamChiTietRepository.findById(id).orElse(null);

            if (s != null) {


                s.setIdHang(idHang);
                s.setIdDanhMuc(idDanhMuc);
                s.setIdDeGiay(idDeGiay);
                s.setIdSanPham(sanPhamRepository.findById(idSanPham).orElse(null));
                s.setIdMauSac(idMauSac);
                s.setIdKichThuoc(idKichThuoc);
                s.setSoLuong(soLuong);
                s.setGiaBan(giaBan);
                s.setGiaNhap(giaNhap);
                s.setTrangThai(trangThai);
                s.setMoTa(moTa);

                // Tải lên hình ảnh lên Cloudinary nếu có tệp hình ảnh
                if (hinhAnhFile != null && !hinhAnhFile.isEmpty()) {
                    Map<String, Object> uploadResult = cloudinary.uploader().upload(hinhAnhFile.getBytes(), ObjectUtils.emptyMap());
                    String imageUrl = (String) uploadResult.get("secure_url");
                    s.setHinhAnh(imageUrl);
                }

                // Lưu đối tượng đã cập nhật vào cơ sở dữ liệu
                return ResponseEntity.ok(sanPhamChiTietRepository.save(s));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sản phẩm chi tiết không tồn tại");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Tải lên hình ảnh thất bại: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi xảy ra: " + e.getMessage());
        }
    }


    @PutMapping(Admin.PRODUCT_DETAIL_SET_DELETE)
    public ResponseEntity<?>  updateSanPham(@PathVariable UUID id) {
        SanPhamChiTiet m = sanPhamChiTietRepository.findById(id).orElse(null);
        if(m!=null){
            sanPhamChiTietRepository.setDeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping(Admin.PRODUCT_DETAIL_DETAIL)
    public ResponseEntity<?>  detail(@PathVariable UUID id) {
        return ResponseEntity.ok(sanPhamChiTietRepository.findById(id).orElse(null));
    }



    @GetMapping(Admin.PRODUCT_DETAIL_SEARCH)
    public ResponseEntity<?> search(
            @RequestParam(value = "ten", defaultValue = "") String ten,
            @RequestParam(value = "giaLonHon", required = false) Double giaLonHon,
            @RequestParam(value = "giaNhoHon", required = false) Double giaNhoHon,
            @RequestParam(value = "trangThai", required = false) String trangThai
    ) {
        return ResponseEntity.ok(sanPhamChiTietRepository.search("%" + ten + "%", giaLonHon, giaNhoHon, trangThai));
    }



    @GetMapping(Admin.PRODUCT_DETAIL_PAGE)
    public ResponseEntity<ResponseData<PageResponse<List<SanPhamChiTietRespon>>>> phanTrang(
            @RequestParam(value="itemsPerPage",defaultValue = "5") int itemsperPage,
            @RequestParam(value = "page",defaultValue = "0") int page
    ){
        Pageable phanTrang = PageRequest.of(page, itemsperPage);
        Page<SanPhamChiTietRespon> spctPage = sanPhamChiTietRepository.phanTrang(phanTrang);

        PageResponse<List<SanPhamChiTietRespon>> pageResponse = PageResponse.<List<SanPhamChiTietRespon>>builder()
                .page(spctPage.getNumber())
                .size(spctPage.getSize())
                .totalPage(spctPage.getTotalPages())
                .items(spctPage.getContent())
                .build();

        ResponseData<PageResponse<List<SanPhamChiTietRespon>>> responseData = ResponseData.<PageResponse<List<SanPhamChiTietRespon>>>builder()
                .message("get paginated done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }


    @GetMapping(Admin.PRODUCT_DETAIL_GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        List<SanPhamChiTietRespon> sanPhamChiTietList = sanPhamChiTietRepository.findByIdSpct(id);
        if (!sanPhamChiTietList.isEmpty()) {
            return ResponseEntity.ok(sanPhamChiTietList);
        }
        return null;
    }

    @GetMapping(Admin.PRODUCT_DETAIL_GET_BY_ID1)
    public ResponseEntity<?> getSpctById(@PathVariable UUID id,
                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "ngayTao") String sortBy,
                                         @RequestParam(defaultValue = "desc") String sortDir,
                                         @RequestParam(defaultValue = "") String hang,
                                         @RequestParam(defaultValue = "") String mauSac,
                                         @RequestParam(defaultValue = "") String kichThuoc) {
        SanPhamChiTietSearchRequest searchRequest = new SanPhamChiTietSearchRequest();
        searchRequest.setHang(hang);
        searchRequest.setMauSac(mauSac);
        searchRequest.setKichThuoc(kichThuoc);
        searchRequest.setIdSanPham(id);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<SanPhamChiTietRespon> searchSPCTPaginate = sanPhamChiTietRepository.findByIdSpct1(pageable, searchRequest);
        PageResponse<List<SanPhamChiTietRespon>> data = PageResponse.<List<SanPhamChiTietRespon>>builder()
                .page(searchSPCTPaginate.getNumber())
                .size(searchSPCTPaginate.getSize())
                .totalPage(searchSPCTPaginate.getTotalPages())
                .items(searchSPCTPaginate.getContent()).build();
        ResponseData<PageResponse<List<SanPhamChiTietRespon>>> responseData = ResponseData.<PageResponse<List<SanPhamChiTietRespon>>>builder()
                .message("Search Sale")
                .status(HttpStatus.OK.value())
                .data(data).build();
        return ResponseEntity.ok(responseData);
    }

//    qr
    @Autowired
    private QRCodeService qrCodeService;
    @GetMapping(Admin.PRODUCT_DETAIL_QR)
    public ResponseEntity<String> getQRCode(@PathVariable String id) throws Exception {
        String qrCode = qrCodeService.generateQRCode(id);
        return ResponseEntity.ok(qrCode);
    }

    @GetMapping(Admin.PRODUCT_DETAIL_QR_TO_CART)
    public ResponseEntity<?> spQRtoCart(@PathVariable UUID id) {
        Optional<SanPhamChiTietRespon> sanPhamChiTietList = sanPhamChiTietRepository.timspctQuetQR(id);
        if (!sanPhamChiTietList.isEmpty()) {
            return ResponseEntity.ok(sanPhamChiTietList);
        }
        return null;
    }



    @GetMapping(Admin.PRODUCT_DETAIL_CLIENT)
    public ResponseEntity<Map<String, Object>> getPaginatedProducts(
            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        try {
            // Tạo đối tượng Pageable
            Pageable pageable = PageRequest.of(page, itemsPerPage);

            // Lấy danh sách sản phẩm từ repository
//            Page<SanPhamChiTietRespon> productPage = sanPhamChiTietRepository.phanTrang(pageable);
            Page<SanPhamClientResponse> productPage = sanPhamChiTietRepository.getAllSpctAndDgg(pageable);

            // Chuẩn bị phản hồi dưới dạng map
            Map<String, Object> response = new HashMap<>();
            response.put("products", productPage.getContent());
            response.put("totalPages", productPage.getTotalPages());

            // Trả về phản hồi
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Trả về lỗi dưới dạng JSON
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to fetch paginated products");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
