package com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.DisciplinaModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DisciplinaMapper {
    DisciplinaMapper INSTANCE = Mappers.getMapper( DisciplinaMapper.class );

    DisciplinaModel dtoToModel(DisciplinaDTO disciplinaDTO);
    DisciplinaDTO modelToDTO(DisciplinaModel disciplinaModel);
}
