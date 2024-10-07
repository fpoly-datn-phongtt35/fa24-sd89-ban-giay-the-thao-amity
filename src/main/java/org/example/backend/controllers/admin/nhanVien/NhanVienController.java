package org.example.backend.controllers.admin.nhanVien;


//import jakarta.validation.Valid;
import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.dto.request.nhanVien.NhanVienRequestAdd;
import org.example.backend.dto.request.nhanVien.NhanVienRequestUpdate;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.mapper.NhanVienMapper;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.services.NguoiDungService;
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
public class NhanVienController {
    final NguoiDungService nhanVienService;
    final NhanVienMapper nhanVienMapper;
    private final NguoiDungRepository nguoiDungRepository;

    public NhanVienController(NguoiDungService nhanVienService, NhanVienMapper nhanVienMapper, NguoiDungRepository nguoiDungRepository) {
        this.nhanVienService = nhanVienService;
        this.nhanVienMapper = nhanVienMapper;
        this.nguoiDungRepository = nguoiDungRepository;
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


    @PostMapping(USER_CREATE)

    public ResponseData<NhanVienRespon> createNhanVien(

            @RequestBody NhanVienRequestAdd nhanVienRequestAdd
    ) {
        NguoiDung nd = new NguoiDung();
        nhanVienMapper.createToNhanVien(nhanVienRequestAdd,nd);
        nhanVienService.save(nd);
        return ResponseData.<NhanVienRespon>builder()
                .status(HttpStatus.CREATED.value())
                .message("Customer role created successfully")
                .build();
    }

    //    public ResponseEntity<?> createNhanVien(@RequestBody NhanVienRequestAdd nhanVienRequestAdd) {
//        NguoiDung nd = new NguoiDung();
//
//        nhanVienMapper.createToNhanVien(nhanVienRequestAdd,nd);
//        System.out.println(nd);
//        return ResponseEntity.ok().body);
//    }
    @PutMapping(USER_UPDATE)
    public ResponseData<NhanVienRespon> updateNhanVien(
            @PathVariable UUID id,
            @RequestBody NhanVienRequestUpdate nhanVienRequestUpdate){
        Optional<NguoiDung> exitNguoiDung = nhanVienService.findById(id);
        if (exitNguoiDung.isEmpty()){
            return null;
        }
        NguoiDung nd = exitNguoiDung.get();
        nhanVienMapper.updateToNhanVien(nhanVienRequestUpdate,nd);
        nhanVienService.save(nd);
        return ResponseData.<NhanVienRespon>builder()
                .status(HttpStatus.CREATED.value())
                .message("Customer role update successfully")
                .build();
    }
    @GetMapping(USER_DELETE)
    public ResponseEntity<?> deleteNhanVien(@PathVariable UUID id) {
//        NguoiDung nd = nguoiDungRepository.getOne(id);
        nhanVienService.setDeletedNhanVien(id);
        return ResponseEntity.ok().body("Oke");
    }

    @GetMapping(PAGE_USER)
    public ResponseEntity<?> getAllUserPage(
            @RequestParam(value = "itemsPerPage" , defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable phanTrang = PageRequest.of(page,5);
        List<NhanVienRespon> users = nhanVienService.getAllNhanVienPage(phanTrang);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(USER_GET_BY_NV)
    public ResponseEntity<List<NhanVienRespon>> searchUserNhanVien(
            @RequestParam(value = "name") String name
         ) {
        List<NhanVienRespon> result = nhanVienService.searchNhanVien("%"+name+"%");
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
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
}