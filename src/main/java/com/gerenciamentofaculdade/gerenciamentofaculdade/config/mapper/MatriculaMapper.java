package com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.MatriculaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.models.MatriculaModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatriculaMapper {
    MatriculaMapper INSTANCE = Mappers.getMapper( MatriculaMapper.class );

    MatriculaModel dtoToModel(MatriculaDTO matriculaDTO);
    MatriculaDTO modelToDTO(MatriculaModel alunoModel);
}
