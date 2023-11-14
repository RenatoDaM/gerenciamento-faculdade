package com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.model.HistoricoDisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.HistoricoDisciplinaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HistoricoDisciplinaMapper {
    HistoricoDisciplinaMapper INSTANCE = Mappers.getMapper( HistoricoDisciplinaMapper.class );

    @Mapping(target = "professorModel.id", source = "professorId")
    @Mapping(target = "disciplinaModel.id", source = "disciplinaId")
    @Mapping(target = "matriculaModel.id", source = "matriculaId")
    HistoricoDisciplinaModel requestToModel(HistoricoDisciplinaDTO request);

    @Mapping(target = "professorId", source = "professorModel.id")
    @Mapping(target = "disciplinaId", source = "disciplinaModel.id")
    @Mapping(target = "matriculaId", source = "matriculaModel.id")
    HistoricoDisciplinaDTO modelToRequest(HistoricoDisciplinaModel model);
}
