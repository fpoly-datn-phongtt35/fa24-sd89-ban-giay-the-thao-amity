package org.example.backend.controllers.admin.sanpham;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.SanPhamChiTietRequest;
import org.example.backend.models.SanPham;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(Admin.PRODUCT_DETAIL_UPDATE)
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody SanPhamChiTietRequest request) {
        SanPhamChiTiet s = sanPhamChiTietRepository.findById(id).orElse(null);
        if(s != null){
            s.setId(request.getId());
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



}
