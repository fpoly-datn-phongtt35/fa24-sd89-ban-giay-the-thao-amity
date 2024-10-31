package org.example.backend.services;

import org.example.backend.common.PageResponse;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.nhanVien.NhanVienRequestAdd;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.mapper.NhanVienMapper;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.USER_CREATE;

@Service
public class NguoiDungService extends GenericServiceImpl<NguoiDung , UUID> {
    private final NhanVienMapper nhanVienMapper;
    private final NguoiDungRepository nguoiDungRepository;

    public NguoiDungService(JpaRepository<NguoiDung, UUID> repository , NhanVienMapper nhanVienMapper, NguoiDungRepository nguoiDungRepository) {
        super(repository);

        this.nhanVienMapper = nhanVienMapper;
        this.nguoiDungRepository = nguoiDungRepository;
    }


    public PageResponse<List<NhanVienRespon>> getAllNhanVien(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhanVienRespon> nhanVienPage = nguoiDungRepository.getAllNhanVien(pageable);

        return PageResponse.<List<NhanVienRespon>>builder()
                .page(nhanVienPage.getNumber())
                .size(nhanVienPage.getSize())
                .totalPage(nhanVienPage.getTotalPages())
                .items(nhanVienPage.getContent())
                .build();
    }

    public List<NhanVienRespon> getNhanVienById(UUID id) {
        return nguoiDungRepository.getNhanVienById(id);
    }

    public Page<NhanVienRespon> getAllNhanVienPage(Pageable pageable){
        return nguoiDungRepository.getAllNhanVienPage(pageable);
    }


    public void setDeletedNhanVien(UUID id){
        nguoiDungRepository.deleteNhanVienStatus(id);
    }
                                            
    public PageResponse<List<NhanVienRespon>> searchNhanVien(int page, int size,String keyword, String gioiTinh, String trangThai){
        Pageable pageable = PageRequest.of(page, size);
        Page<NhanVienRespon> seacrchNhanVienPaginate = nguoiDungRepository.searchUserNhanVien(pageable,keyword,gioiTinh,trangThai);
        return PageResponse.<List<NhanVienRespon>>builder()
                .page(seacrchNhanVienPaginate.getNumber())
                .size(seacrchNhanVienPaginate.getSize())
                .totalPage(seacrchNhanVienPaginate.getTotalPages())
                .items(seacrchNhanVienPaginate.getContent()).build();
    }

    public List<NhanVienRespon> sortNhanVien(){
        return nguoiDungRepository.sortNhanVien();
    }
}