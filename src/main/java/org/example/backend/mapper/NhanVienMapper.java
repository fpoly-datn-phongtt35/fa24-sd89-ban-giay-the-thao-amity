package org.example.backend.mapper;


import org.example.backend.dto.request.dotGiamGia.DotGiamGiaUpdate;
import org.example.backend.dto.request.nhanVien.NhanVienRequestAdd;
import org.example.backend.dto.request.nhanVien.NhanVienRequestUpdate;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.models.DotGiamGia;
import org.example.backend.models.NguoiDung;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NhanVienMapper {
    NhanVienRequestAdd toNhanVienRequest(NguoiDung nguoiDung);
    NguoiDung createToNhanVien(NhanVienRequestAdd dtoreq);
    NguoiDung updateToNhanVien(NhanVienRequestUpdate dtoreq);
    NhanVienRequestUpdate toDotGiamGiaResponse(NguoiDung dto);
    NguoiDung getAllNhanVien(NhanVienRespon dtoreq);
    NhanVienRespon getAllNhanVienRespon(NguoiDung dto);
}
