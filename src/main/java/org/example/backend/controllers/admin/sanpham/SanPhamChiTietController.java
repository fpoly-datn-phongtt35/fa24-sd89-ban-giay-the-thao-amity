package org.example.backend.controllers.admin.sanpham;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.SanPhamChiTietRequest;
import org.example.backend.dto.response.SanPham.DeGiayRepon;
import org.example.backend.dto.response.SanPham.SanPhamChiTietRespon;
import org.example.backend.models.SanPham;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
public class SanPhamChiTietController {
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @GetMapping(Admin.PRODUCT_DETAIL_GET_ALL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(sanPhamChiTietRepository.getAll());
    }

    @PostMapping(Admin.PRODUCT_DETAIL_CREATE)
    public ResponseEntity<?> create(@RequestBody SanPhamChiTietRequest request) {
        SanPhamChiTiet s = new SanPhamChiTiet();
        s.setTen(request.getTen());
        s.setIdHang(request.getIdHang());
        s.setIdDanhMuc(request.getIdDanhMuc());
        s.setIdDeGiay(request.getIdDeGiay());
        s.setIdSanPham(request.getIdSanPham());
        s.setIdMauSac(request.getIdMauSac());
        s.setIdKichThuoc(request.getIdKichThuoc());
        s.setSoLuong(request.getSoLuong());
        s.setGiaBan(request.getGiaBan());
        s.setGiaNhap(request.getGiaNhap());
        s.setTrangThai(request.getTrangThai());
        s.setHinhAnh(request.getHinhAnh());
        return ResponseEntity.ok(sanPhamChiTietRepository.save(s));
    }
    @PutMapping(Admin.PRODUCT_DETAIL_UPDATE)
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody SanPhamChiTietRequest request) {
        SanPhamChiTiet s = sanPhamChiTietRepository.findById(id).orElse(null);
        if(s != null){

            s.setTen(request.getTen());
            s.setIdHang(request.getIdHang());
            s.setIdDanhMuc(request.getIdDanhMuc());
            s.setIdDeGiay(request.getIdDeGiay());
            s.setIdSanPham(request.getIdSanPham());
            s.setIdMauSac(request.getIdMauSac());
            s.setIdKichThuoc(request.getIdKichThuoc());
            s.setSoLuong(request.getSoLuong());
            s.setGiaBan(request.getGiaBan());
            s.setGiaNhap(request.getGiaNhap());
            s.setTrangThai(request.getTrangThai());
            s.setHinhAnh(request.getHinhAnh());
            return ResponseEntity.ok(sanPhamChiTietRepository.save(s));
        }
       SanPhamChiTiet sp = new SanPhamChiTiet();
        sp.setTen(request.getTen());
        sp.setIdHang(request.getIdHang());
        sp.setIdDanhMuc(request.getIdDanhMuc());
        sp.setIdDeGiay(request.getIdDeGiay());
        sp.setIdSanPham(request.getIdSanPham());
        sp.setIdMauSac(request.getIdMauSac());
        sp.setIdKichThuoc(request.getIdKichThuoc());
        sp.setSoLuong(request.getSoLuong());
        sp.setGiaBan(request.getGiaBan());
        sp.setGiaNhap(request.getGiaNhap());
        sp.setTrangThai(request.getTrangThai());
        sp.setHinhAnh(request.getHinhAnh());
        return ResponseEntity.ok(sanPhamChiTietRepository.save(s));
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

//    public NhanVien detail(@PathVariable Integer id){
//
//        return NhanVienRepository.findById(id).get();
//
//    }
    @GetMapping(Admin.PRODUCT_DETAIL_DETAIL)
    public ResponseEntity<?>  detail(@PathVariable UUID id) {
        return ResponseEntity.ok(sanPhamChiTietRepository.findById(id).orElse(null));
    }


//    @GetMapping(Admin.PRODUCT_DETAIL_SEARCH)
//    public ResponseEntity<?>  search(@RequestParam(value="ten" ,defaultValue = "") String ten) {
//        return ResponseEntity.ok(sanPhamChiTietRepository.search("%"+ten+"%"));
//    }

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


}
