    package org.example.backend.controllers.admin.nhanVien;



    //import jakarta.validation.Valid;

    import com.cloudinary.Cloudinary;
    import com.cloudinary.utils.ObjectUtils;
    import org.example.backend.common.PageResponse;
    import org.example.backend.common.ResponseData;
    import org.example.backend.dto.request.nhanVien.NhanVienRequestAdd;
    import org.example.backend.dto.request.nhanVien.NhanVienRequestUpdate;
    import org.example.backend.dto.response.NhanVien.NhanVienRespon;
    import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
    import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
    import org.example.backend.mapper.NhanVienMapper;
    import org.example.backend.models.NguoiDung;
    import org.example.backend.repositories.NguoiDungRepository;
    import org.example.backend.services.NguoiDungService;
    import org.springframework.core.io.InputStreamResource;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.ByteArrayInputStream;
    import java.io.ByteArrayOutputStream;
    import java.io.IOException;
    import java.time.Instant;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;
    import java.util.UUID;

    import static org.example.backend.constants.api.Admin.*;

    @RestController
    public class NhanVienController {
        final NguoiDungService nhanVienService;
        final NhanVienMapper nhanVienMapper;
        private final NguoiDungRepository nguoiDungRepository;
        private final Cloudinary cloudinary;
        private final PasswordEncoder passwordEncoder;
        private final NguoiDungService nguoiDungService;

        public NhanVienController(NguoiDungService nhanVienService, NhanVienMapper nhanVienMapper, NguoiDungRepository nguoiDungRepository, Cloudinary cloudinary, PasswordEncoder passwordEncoder, NguoiDungService nguoiDungService) {
            this.nhanVienService = nhanVienService;
            this.nhanVienMapper = nhanVienMapper;
            this.nguoiDungRepository = nguoiDungRepository;
            this.cloudinary = cloudinary;
            this.passwordEncoder=passwordEncoder;
            this.nguoiDungService = nguoiDungService;
        }

        @GetMapping(USER_GET_ALL)
        public ResponseEntity<ResponseData<PageResponse<List<NhanVienRespon>>>> getAllUser(
                @RequestParam(value = "page", defaultValue = "0") int page,
                @RequestParam(value = "size", defaultValue = "5") int size) {

            PageResponse<List<NhanVienRespon>> nhanVienPage = nhanVienService.getAllNhanVien(page, size);

            ResponseData<PageResponse<List<NhanVienRespon>>> responseData = ResponseData.<PageResponse<List<NhanVienRespon>>>builder()
                    .message("Get all users done")
                    .status(HttpStatus.OK.value())
                    .data(nhanVienPage)
                    .build();

            return ResponseEntity.ok(responseData);
        }

        @GetMapping(USER_GET_BY_ID)
        public ResponseEntity<?> getNhanVienById(
                @PathVariable UUID id
        ){
            List<NhanVienRespon> nhanVienById = nhanVienService.getNhanVienById(id);
            return ResponseEntity.ok(nhanVienById);
        }


        @PostMapping(USER_CREATE)

        public ResponseEntity<?> createNhanVien(
                @RequestParam("ma") String ma,
                @RequestParam("email") String email,
                @RequestParam("sdt") String sdt,
                @RequestParam("matKhau") String matKhau,
                @RequestParam("ten") String ten,
                @RequestParam("diaChi") String diaChi,
                @RequestParam("ngaySinh") Instant ngaySinh,
                @RequestParam("gioiTinh") String gioiTinh,
                @RequestParam("hinhAnh") MultipartFile hinhAnh,
                @RequestParam("cccd") String cccd,
                @RequestParam("chucVu") String chucVu,
                @RequestParam("trangThai") String trangThai
        ) throws IOException {
            NguoiDung nd = new NguoiDung();
            nd.setMa(ma);
            nd.setEmail(email);
            nd.setSdt(sdt);
            nd.setMatKhau(passwordEncoder.encode(matKhau));
            nd.setTen(ten);
            nd.setDiaChi(diaChi);
            nd.setNgaySinh(ngaySinh);
            nd.setGioiTinh(gioiTinh);
            Map<String, Object> uploadResult = cloudinary.uploader().upload(hinhAnh.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");
            nd.setHinhAnh(imageUrl);
            nd.setCccd(cccd);
            nd.setChucVu(chucVu);
            nd.setTrangThai(trangThai);
            if (nguoiDungRepository.findByEmail(email).isPresent()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Email đã tồn tại vui lòng điền email khac");
            }
            NguoiDung nguoiDung = nhanVienService.save(nd);
            return ResponseEntity.ok(nguoiDung);

        }

        @PutMapping(USER_UPDATE)
        public ResponseEntity<?> updateNhanVien(
                @PathVariable UUID id,
                @RequestParam(value = "ma",defaultValue = "") String ma,
                @RequestParam(value = "email",defaultValue = "") String email,
                @RequestParam(value = "sdt",defaultValue = "") String sdt,
                @RequestParam(value = "matKhau",defaultValue = "") String matKhau,
                @RequestParam(value = "ten",defaultValue = "") String ten,
                @RequestParam(value = "diaChi",defaultValue = "") String diaChi,
                @RequestParam(value = "ngaySinh",defaultValue = "") Instant ngaySinh,
                @RequestParam(value = "gioiTinh",defaultValue = "") String gioiTinh,
                @RequestParam(value = "hinhAnh" , required = false) MultipartFile hinhAnh,
                @RequestParam(value = "cccd" , defaultValue = "") String cccd,
                @RequestParam(value = "chucVu",defaultValue = "") String chucVu,
                @RequestParam(value = "trangThai" ,defaultValue = "") String trangThai
        ) throws IOException {
            Optional<NguoiDung> exitNguoiDung = nhanVienService.findById(id);
            if (exitNguoiDung.isEmpty()){
                return null;
            }
            NguoiDung nd = exitNguoiDung.get();
            nd.setId(id);
            nd.setMa(ma);
            nd.setEmail(email);
            nd.setSdt(sdt);
            nd.setMatKhau(passwordEncoder.encode(matKhau));
            nd.setTen(ten);
            nd.setDiaChi(diaChi);
            nd.setNgaySinh(ngaySinh);
            nd.setGioiTinh(gioiTinh);
            if (hinhAnh != null && !hinhAnh.isEmpty()) {
                // Nếu có hình ảnh mới, upload và cập nhật
                Map<String, Object> uploadResult = cloudinary.uploader().upload(hinhAnh.getBytes(), ObjectUtils.emptyMap());
                String imageUrl = (String) uploadResult.get("secure_url");
                nd.setHinhAnh(imageUrl);
            } else {
                // Nếu không có hình ảnh mới, giữ nguyên hình ảnh cũ
                nd.setHinhAnh(nd.getHinhAnh()); // Giữ nguyên giá trị hiện tại
            }
            nd.setCccd(cccd);
            nd.setChucVu(chucVu);
            nd.setTrangThai(trangThai);

            nhanVienService.save(nd);
            return ResponseEntity.ok(nhanVienService.save(nd));
        }
        @GetMapping(USER_DELETE)
        public ResponseEntity<?> deleteNhanVien(@PathVariable UUID id) {
    //        NguoiDung nd = nguoiDungRepository.getOne(id);
            nhanVienService.setDeletedNhanVien(id);
            return ResponseEntity.ok().body("Oke");
        }

        @GetMapping(PAGE_USER)
        public ResponseEntity<ResponseData<PageResponse<List<NhanVienRespon>>>> getAllUserPage(
                @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
                @RequestParam(value = "page", defaultValue = "0") int page
        ) {
            Pageable phanTrang = PageRequest.of(page, itemsPerPage);
            Page<NhanVienRespon> nhanVienPage = nhanVienService.getAllNhanVienPage(phanTrang);

            PageResponse<List<NhanVienRespon>> pageResponse = PageResponse.<List<NhanVienRespon>>builder()
                    .page(nhanVienPage.getNumber())
                    .size(nhanVienPage.getSize())
                    .totalPage(nhanVienPage.getTotalPages())
                    .items(nhanVienPage.getContent())
                    .build();

            ResponseData<PageResponse<List<NhanVienRespon>>> responseData = ResponseData.<PageResponse<List<NhanVienRespon>>>builder()
                    .message("Get paginated users done")
                    .status(HttpStatus.OK.value())
                    .data(pageResponse)
                    .build();

            return ResponseEntity.ok(responseData);
        }


        @GetMapping(USER_GET_BY_NV)
        public ResponseEntity<?> searchUserNhanVien(
                @RequestParam(value = "page", defaultValue = "0") int page,
                @RequestParam(value = "size", defaultValue = "5") int size,
                @RequestParam(value = "keyword",defaultValue = "") String keyword,
                @RequestParam(value = "gioiTinh",defaultValue = "") String gioiTinh,
                @RequestParam(value = "trangThai",defaultValue = "") String trangThai
             ) {
            PageResponse<List<NhanVienRespon>> searchNhanVienValue = nhanVienService.searchNhanVien(page,size,keyword, gioiTinh, trangThai);
            ResponseData<PageResponse<List<NhanVienRespon>>> responseData = ResponseData.<PageResponse<List<NhanVienRespon>>>builder()
                    .message("Search Sale")
                    .status(HttpStatus.OK.value())
                    .data(searchNhanVienValue).build();
            return ResponseEntity.ok().body(responseData);
        }

        @GetMapping(USER_SORT)
        public ResponseEntity<List<NhanVienRespon>> sortUserNhanVien(

        ) {
            List<NhanVienRespon> result = nhanVienService.sortNhanVien();
            if (result.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        }
        @PostMapping(USER_LOGIN)
        public ResponseEntity<?> login(
                @RequestParam(value = "email",defaultValue = "") String email,
                @RequestParam(value = "password" , defaultValue = "") String password

        ) {
            return ResponseEntity.ok(nhanVienService.login(email, password));
        }
        @PostMapping(USER_REGISTER)
        public ResponseEntity<?>   createRegister (
//                @RequestParam(value = "ma",defaultValue = "") String ma,
                @RequestParam(value = "ten",defaultValue = "") String ten,
                @RequestParam(value = "email" ,defaultValue = "") String email,
                @RequestParam(value = "matKhau",defaultValue = "") String matKhau,
                @RequestParam(value = "matKhauNhapLai",defaultValue = "") String matKhauNhapLai,
                @RequestParam(value = "chucVu" ,defaultValue = "") String chucVu,
                @RequestParam(value = "trangThai",defaultValue = "") String trangThai
        ){
            String ma = "KH" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            NguoiDung nd = new NguoiDung();
            nd.setMa(ma);
            nd.setEmail(email);
            nd.setMatKhau(passwordEncoder.encode(matKhau));
            nd.setTen(ten);
            nd.setChucVu(chucVu);
            nd.setTrangThai(trangThai);
            if (!matKhauNhapLai.equals(matKhau)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("mật khẩu và mật khẩu nhập lại chưa trung khớp");
            }
            if (nguoiDungRepository.findByEmail(email).isPresent()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Email đã tồn tại vui lòng điền email khac");
            }
            return ResponseEntity.ok(nhanVienService.save(nd));
        }
        @PutMapping(USER_UPDATE_PASSWORD)
        public ResponseEntity<?> updatePasswordUser(
                @PathVariable UUID id,
                @RequestParam(value = "matKhauCu", defaultValue = "") String matKhauCu,
                @RequestParam(value = "matKhauMoi", defaultValue = "") String matKhauMoi,
                @RequestParam(value = "matKhauNhapLai", defaultValue = "") String matKhauNhapLai
        ) {
            Optional<NguoiDung> nguoiDungById = nguoiDungRepository.findById(id);
            if (nguoiDungById.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Người dùng không tồn tại.");
            }
            NguoiDung nguoiDung = nguoiDungById.get();

            if (!passwordEncoder.matches(matKhauCu, nguoiDung.getMatKhau())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Mật khẩu cũ không đúng.");
            }
            if (!matKhauMoi.equals(matKhauNhapLai)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Mật khẩu mới và mật khẩu nhập lại chưa khớp.");
            }
            String matKhauMoiEncoded = passwordEncoder.encode(matKhauMoi);
            nguoiDung.setMatKhau(matKhauMoiEncoded);
            nguoiDungRepository.save(nguoiDung);

            return ResponseEntity.ok("Cập nhật mật khẩu thành công.");
        }

        @GetMapping(USER_SEARCH_EMAIL)
        public ResponseEntity<?> searchEmail(@RequestParam String email) {
            Optional<NguoiDung> nguoiDung = nguoiDungService.searchByEmail(email);

            if (nguoiDung.isPresent()) {
                return ResponseEntity.ok(nguoiDung.get());  // Trả về người dùng tìm được
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with the email: " + email);
            }
        }

        @PutMapping(USER_UPDATE_PASSWORD_BY_EMAIL)
        public ResponseEntity<?> updatePasswordUserByEmail(
                @PathVariable String email,
                @RequestParam(value = "matKhauMoi", defaultValue = "") String matKhauMoi,
                @RequestParam(value = "matKhauNhapLai", defaultValue = "") String matKhauNhapLai
        ) {
            // Check if new password and confirm password are provided
            if (matKhauMoi.isEmpty() || matKhauNhapLai.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Mật khẩu mới và mật khẩu nhập lại không được để trống.");
            }
            Optional<NguoiDung> nguoiDungByEmail = nguoiDungService.searchByEmail(email);
            NguoiDung nguoiDung = nguoiDungByEmail.get();
            if (!matKhauMoi.equals(matKhauNhapLai)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Mật khẩu mới và mật khẩu nhập lại chưa khớp.");
            }
            if (matKhauMoi.length() < 8) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Mật khẩu mới phải có ít nhất 8 ký tự.");
            }
            String matKhauMoiEncoded = passwordEncoder.encode(matKhauMoi);
            nguoiDung.setMatKhau(matKhauMoiEncoded);
            nguoiDungRepository.save(nguoiDung);

            return ResponseEntity.ok("Cập nhật mật khẩu thành công.");
        }



//        @GetMapping(USER_SAVE_EXCEL)
//        public ResponseEntity<byte[]> exportExcel() {
//            try {
//                List<NhanVienRespon> voucherList = nhanVienService.getAllNhanVien(); // Lấy dữ liệu phiếu giảm giá
//                byte[] excelData = nhanVienService.exportToExcel(voucherList);
//                return ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=phieuGiamGia.xlsx")
//                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                        .body(excelData);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return ResponseEntity.status(500).build();
//            }
//        }

    }