package org.example.backend.mapper;

import org.example.backend.dto.request.dotGiamGia.DotGiamGiaCreate;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaUpdate;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.models.DotGiamGia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DotGiamGiaMapper {
    DotGiamGiaCreate toDotGiamGiaRequest(DotGiamGia dotGiamGia);
    DotGiamGia createToDotGiamGia(DotGiamGiaCreate dtoreq);
    DotGiamGia updateToDotGiamGia(DotGiamGiaUpdate dtoreq);
    DotGiamGiaResponse toDotGiamGiaResponse(DotGiamGia dto);
}
