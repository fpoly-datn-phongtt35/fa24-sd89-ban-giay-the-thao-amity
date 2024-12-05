package org.example.backend.controllers.client;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.phieuGiamGia.phieuGiamGiaRequestAdd;
import org.example.backend.dto.response.banHang.banHangClientResponse;
import org.example.backend.models.GioHang;
import org.example.backend.models.GioHangChiTiet;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.repositories.GioHangChiTietRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.example.backend.services.GioHangChiTietService;
import org.example.backend.services.GioHangService;
import org.example.backend.services.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.*;

@RestController
public class banHangClientController {

    @Autowired
    SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;
    @Autowired
    GioHangChiTietService gioHangChiTietService;
    @Autowired
    GioHangService gioHangService;
//    @Autowired
//    GioHangChiTietService gioHangChiTietService;

    @GetMapping(Admin.SELL_CLIENT_GET_ALL)
    public ResponseEntity<?> getbanHangClient(@RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
                                              @RequestParam(value = "page", defaultValue = "0") int page
    ){
        PageResponse<List<banHangClientResponse>> bhPage = sanPhamChiTietService.getbanHangClient(page, itemsPerPage);
        ResponseData<PageResponse<List<banHangClientResponse>>> responseData = ResponseData.<PageResponse<List<banHangClientResponse>>>builder()
                .message("Get all banHangCient done")
                .status(HttpStatus.OK.value())
                .data(bhPage)
                .build();
        return ResponseEntity.ok(responseData);
    }

    @GetMapping(Admin.SELL_CLIENT_GET_BY_ID_DGG)
    public ResponseEntity<?> getbanHangClientbyIDDGG(@RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @PathVariable UUID id
    ){
        PageResponse<List<banHangClientResponse>> bhPage = sanPhamChiTietService.getbanHangClientbyIDDGG(page,itemsPerPage,id);
        ResponseData<PageResponse<List<banHangClientResponse>>> responseData = ResponseData.<PageResponse<List<banHangClientResponse>>>builder()
                .message("Get all banHangCient done")
                .status(HttpStatus.OK.value())
                .data(bhPage)
                .build();
        return ResponseEntity.ok(responseData);
    }

    @GetMapping(Admin.CART_GET_BY_ID)
    public ResponseEntity<?> finbyIDKH(@PathVariable UUID id
    ){
        return ResponseEntity.ok().body(gioHangService.finbyIDGioHang(id));
    }

    @GetMapping(Admin.CART_DETAIL_GET_ALL)
    public ResponseEntity<?> getALLCart(){
        return ResponseEntity.ok().body(gioHangChiTietService.findAll());
    }

    @PostMapping(CART_CREATE)
    public ResponseEntity<?> createCart(@RequestBody GioHangChiTiet ghAdd) {
//        GioHangChiTiet gh = new GioHangChiTiet();
        return ResponseEntity.ok().body(gioHangChiTietService.save(ghAdd));
    }

    @PostMapping(CART_UPDATE)
    public ResponseEntity<?> updateCart(@PathVariable UUID id,@RequestBody GioHangChiTiet ghUpdate) {
        ghUpdate.setId(id);
        return ResponseEntity.ok().body(gioHangChiTietService.save(ghUpdate));
    }

    @DeleteMapping(CART_DELETE)
    public ResponseEntity<?> deleteCart(@RequestParam List<UUID> listId) {
        for (UUID id : listId) {
            gioHangChiTietService.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

    //tim kiem spct client

    @GetMapping(SELL_CLIENT_SEARCH_1)
    public ResponseEntity<?> getBanHangClient(
            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "tenSp", required = false) String tenSp,
            @RequestParam(value = "tenKichThuoc", required = false) String tenKichThuoc,
            @RequestParam(value = "tenMauSac", required = false) String tenMauSac,
            @RequestParam(value = "tenDanhMuc", required = false) String tenDanhMuc,
            @RequestParam(value = "tenHang", required = false) String tenHang,
            @RequestParam(value = "giaMin", required = false) BigDecimal giaMin,
            @RequestParam(value = "giaMax", required = false) BigDecimal giaMax
    ) {
        // Gọi service để lấy dữ liệu
        PageResponse<List<banHangClientResponse>> bhPage = sanPhamChiTietService.searchBanHangClient(
                page, itemsPerPage, tenSp, tenKichThuoc, tenMauSac,tenDanhMuc,tenHang,giaMin, giaMax
        );

        // Tạo ResponseData
        ResponseData<PageResponse<List<banHangClientResponse>>> responseData = ResponseData.<PageResponse<List<banHangClientResponse>>>builder()
                .message("Get all banHangClient done")
                .status(HttpStatus.OK.value())
                .data(bhPage)
                .build();

        return ResponseEntity.ok(responseData);
    }

    //lay 5 san pham moi nhat

    @GetMapping(Admin.SELL_CLIENT_TOP)
    public ResponseEntity<List<banHangClientResponse>> getTop5SanPhamMoiNhat() {
        List<banHangClientResponse> top5SanPham = sanPhamChiTietService.getTop5SanPhamMoiNhat();
        return ResponseEntity.ok(top5SanPham); // Trả về danh sách dưới dạng JSON
    }

    // hien cac sp da ap dung dot giam gia
    @GetMapping(Admin.SELL_CLIENT_SALE_SP)
    public ResponseEntity<List<banHangClientResponse>> getSanPhamGiamGia(
            @RequestParam List<String> trangThais,
            @RequestParam UUID id
    ) {
        List<banHangClientResponse> sanPhamGiamGia = sanPhamChiTietService.getSanPhamGiamGia(trangThais, id);
        return ResponseEntity.ok(sanPhamGiamGia);
    }

}
