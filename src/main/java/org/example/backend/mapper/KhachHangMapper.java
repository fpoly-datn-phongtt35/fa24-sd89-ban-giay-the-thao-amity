package org.example.backend.mapper;

import org.example.backend.dto.request.khachHang.KhachHangCreate;
import org.example.backend.dto.request.khachHang.KhachHangUpdate;
import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.models.NguoiDung;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KhachHangMapper {
    KhachHangCreate toKhachHangCreate(NguoiDung nguoiDung);
    NguoiDung createToKhachHang(KhachHangCreate dtoreq);
    NguoiDung updateToKhachHang(KhachHangUpdate dtoreq, NguoiDung nd);
    KhachHangUpdate toKhachHangResponse(NguoiDung dto);
    NguoiDung getAllKhachHang(KhachHangResponse dtoreq);
    KhachHangResponse getAllKhachHangRespon(NguoiDung dto);
}
