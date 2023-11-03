package com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.ProfessorDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorLecionaDisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.request.ProfessorLecionaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfessorLecionaMapper {
    ProfessorLecionaMapper INSTANCE = Mappers.getMapper( ProfessorLecionaMapper.class );

    ProfessorLecionaDisciplinaModel dtoToModel(ProfessorLecionaRequest professorLecionaRequest);
    ProfessorLecionaRequest modelToDTO(ProfessorLecionaDisciplinaModel professorLecionaDisciplinaModel);
}
