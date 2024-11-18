package org.example.backend.controllers.admin.banHang;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.banHang.*;
import org.example.backend.dto.response.banHang.TrangThaiRespon;
import org.example.backend.models.HoaDon;
import org.example.backend.models.HoaDonChiTiet;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.repositories.HoaDonChiTietRepository;
import org.example.backend.repositories.HoaDonRepository;
import org.example.backend.repositories.PhieuGiamGiaRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
//import net.glxn.qrgen.javase.QRCode;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import static org.example.backend.constants.Status.CHO_XAC_NHAN_HOA_DON;

@RestController
public class BanHangController {
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;
    @Autowired
    PhieuGiamGiaRepository phieuGiamGiaRepository;
    @Autowired
    VietQrService vietQrService;

    @GetMapping(Admin.SELL_GET_ALL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(hoaDonRepository.getAllBanHang(CHO_XAC_NHAN_HOA_DON));
    }


    @PostMapping(Admin.SELL_CREATE)
    public ResponseEntity<?> create() {

        TrangThaiRespon trangThaiRespon = hoaDonRepository.getAllTrangThai(CHO_XAC_NHAN_HOA_DON).orElse(null);
        System.out.println("lol"+ trangThaiRespon);
        if(trangThaiRespon == null || trangThaiRespon.getCount() <5) {
            System.out.println("lol thuong"+ trangThaiRespon);
            HoaDon hoaDon = new HoaDon();
            return ResponseEntity.ok(hoaDonRepository.save(hoaDon));
        }
        return null;

    }


    @PostMapping(Admin.SELL_DETAIL_CREATE)

    public ResponseEntity<?> detailCreate(@RequestBody List<HoaDonChiTietRequest> list ) {
        try{

            if(list.size()>0){

                for (HoaDonChiTietRequest hdct : list) {
                    HoaDon hd = hoaDonRepository.findById(hdct.getIdHoaDon()).orElse(null);

                    SanPhamChiTiet spct = sanPhamChiTietRepository.findById(hdct.getIdSpct()).orElse(null);
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();

                    hoaDonChiTiet.setIdHoaDon(hd);
                    hoaDonChiTiet.setIdSpct(spct);
//                    hoaDonChiTiet.setNguoiTao(hdct.getIdNguoiDung());
                    hoaDonChiTiet.setSoLuong(hdct.getSoLuong());
                    hoaDonChiTiet.setGia(hdct.getGia());
                    hoaDonChiTiet.setTrangThai(hdct.getTrangThai());
                    hoaDonChiTiet.setGiaGiam(hdct.getGiaGiam());

                    hoaDonChiTietRepository.save(hoaDonChiTiet);
                }

            }

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    @PutMapping(Admin.SELL_UPDATE)
    public ResponseEntity<?> update(@RequestBody BanHangRequest request ) {
        HoaDon hoaDon = hoaDonRepository.findById(request.getId()).orElse(null);
        if(hoaDon == null) {
            return ResponseEntity.notFound().build();
        }
        hoaDon.setIdPhieuGiamGia(request.getIdPhieuGiamGia());
        hoaDon.setIdNguoiDung(request.getIdNguoiDung());
        hoaDon.setIdDotGiamGia(request.getIdDotGiamGia());
        hoaDon.setSoDienThoai(request.getSoDienThoai());
        hoaDon.setDiaChi(request.getDiaChi());
        hoaDon.setGiaGoc(request.getGiaGoc());
        hoaDon.setGiaGiam(request.getGiaGiam());
        hoaDon.setTongTien(request.getTongTien());
        hoaDon.setLoaiHoaDon(request.getLoaiHoaDon());
        hoaDon.setGhiChu(request.getGhiChu());
        hoaDon.setQrCode(request.getQrCode());
        hoaDon.setTienVanChuyen(request.getTienVanChuyen());
        hoaDon.setTrangThai(request.getTrangThai());

        return ResponseEntity.ok(hoaDonRepository.save(hoaDon));


    }


    @PutMapping(Admin.SELL_SET_DELETE)

    public ResponseEntity<?> delete(@PathVariable UUID id) {
        HoaDon hd = hoaDonRepository.findById(id).orElse(null);
        if(hd != null) {
            hoaDonRepository.setDeleted(!hd.getDeleted(),id);
            return ResponseEntity.ok("ok"+id);
        }
        return ResponseEntity.notFound().build();
    }



    @PutMapping(Admin.SELL_ORDER_PRODUCT_COMPLETE)
    public ResponseEntity<?> updateProductQuantity(@PathVariable UUID id, @RequestBody QuantitySoldRequest quantitySoldRequest) {
        SanPhamChiTiet spct = sanPhamChiTietRepository.findById(id).orElse(null);
        if (spct == null) {
            throw new RuntimeException("Sản phẩm không tồn tại");
        }
        int quantitySold = quantitySoldRequest.getQuantitySold();
        if (spct.getSoLuong() < quantitySold) {
            throw new RuntimeException("Không đủ số lượng sản phẩm trong kho");
        }
        spct.setSoLuong(spct.getSoLuong() - quantitySold);
        sanPhamChiTietRepository.save(spct);
        return ResponseEntity.ok(spct);
    }

    @PutMapping(Admin.SELL_ORDER_VOUCHER_COMPLETE)
    public ResponseEntity<?> updateVoucherQuantity(@PathVariable UUID id, @RequestBody QuantitySoldVoucherRequest request) {
        PhieuGiamGia pgg = phieuGiamGiaRepository.findById(id).orElse(null);
        if (pgg == null) {
            throw new RuntimeException("pgg không tồn tại");
        }
        int SLpgg = request.getQuantitySoldVoucher();
        if(pgg.getSoLuong() < SLpgg) {
            throw new RuntimeException("het phieu");
        }
        pgg.setSoLuong(pgg.getSoLuong()-SLpgg);
        phieuGiamGiaRepository.save(pgg);
        return ResponseEntity.ok(pgg);
    }


    @PostMapping(Admin.SELL_QR)
    public ResponseEntity<String> generateQrCode(@RequestBody QrRequest qrRequest) {
        // Gọi service để tạo QR code
        String qrResponse = vietQrService.generateQrCode(
                qrRequest.getAccountNo(),
                qrRequest.getAccountName(),
                qrRequest.getAcqId(),
                qrRequest.getAddInfo(),
                qrRequest.getAmount()
        );
        return ResponseEntity.ok(qrResponse);
    }





}
