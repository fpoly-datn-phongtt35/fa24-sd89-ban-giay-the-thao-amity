package org.example.backend.mapper;

import org.example.backend.dto.request.dotGiamGia.DotGiamGiaRequest;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.models.DotGiamGia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DotGiamGiaMapper {
    DotGiamGiaRequest toDotGiamGiaRequest(DotGiamGia dotGiamGia);
    DotGiamGia toDotGiamGia(DotGiamGiaRequest dtoreq);
    DotGiamGiaResponse toDotGiamGiaResponse(DotGiamGia dto);
}
