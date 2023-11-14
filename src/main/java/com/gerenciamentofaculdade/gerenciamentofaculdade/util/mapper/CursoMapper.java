package com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.CursoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.CursoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CursoMapper {
    CursoMapper INSTANCE = Mappers.getMapper( CursoMapper.class );

    CursoModel dtoToModel(CursoDTO cursoDTO);
    CursoDTO modelToDTO(CursoModel cursoModel);
}
