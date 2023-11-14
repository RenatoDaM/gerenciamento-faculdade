package com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.HorarioAulaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.HorarioAulaModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HorarioAulaMapper {
    HorarioAulaMapper INSTANCE = Mappers.getMapper( HorarioAulaMapper.class );

    HorarioAulaModel dtoToModel(HorarioAulaDTO horarioAulaDTO);
    HorarioAulaDTO modelToDTO(HorarioAulaModel horarioAulaModel);
}
