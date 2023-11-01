package com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.DisciplinaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DisciplinaMapper {
    DisciplinaMapper INSTANCE = Mappers.getMapper( DisciplinaMapper.class );

    @Mapping(target = "cursoModel", source = "cursoDTO")
    DisciplinaModel dtoToModel(DisciplinaDTO disciplinaDTO);
    @Mapping(target = "cursoDTO", source = "cursoModel")
    DisciplinaDTO modelToDTO(DisciplinaModel disciplinaModel);
}
