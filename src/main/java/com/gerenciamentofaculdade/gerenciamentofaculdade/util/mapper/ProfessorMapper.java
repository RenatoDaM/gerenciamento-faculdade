package com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.ProfessorDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    ProfessorMapper INSTANCE = Mappers.getMapper( ProfessorMapper.class );

    ProfessorModel dtoToModel(ProfessorDTO professorDTO);
    ProfessorDTO modelToDTO(ProfessorModel professorModel);
}
