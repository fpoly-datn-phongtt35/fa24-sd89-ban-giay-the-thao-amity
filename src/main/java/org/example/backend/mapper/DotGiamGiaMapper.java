package org.example.backend.mapper;

import org.example.backend.dto.DotGiamGiaDTO;
import org.example.backend.models.DotGiamGia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DotGiamGiaMapper {
    DotGiamGiaDTO toDotGiamGiaDTO(DotGiamGia dotGiamGia);
    DotGiamGia toDotGiamGia(DotGiamGiaDTO dto);
}
