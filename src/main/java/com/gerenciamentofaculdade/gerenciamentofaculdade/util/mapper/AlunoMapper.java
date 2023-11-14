package com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.model.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlunoMapper {
    AlunoMapper INSTANCE = Mappers.getMapper( AlunoMapper.class );

    AlunoModel dtoToModel(AlunoDTO alunoDto);
    AlunoDTO modelToDTO(AlunoModel alunoModel);
}
