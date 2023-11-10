package com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.HistoricoDisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.request.HistoricoDisciplinaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HistoricoDisciplinaMapper {
    HistoricoDisciplinaMapper INSTANCE = Mappers.getMapper( HistoricoDisciplinaMapper.class );

    @Mapping(target = "professorModel.id", source = "professorId")
    @Mapping(target = "disciplinaModel.id", source = "disciplinaId")
    @Mapping(target = "matriculaModel.id", source = "matriculaId")
    HistoricoDisciplinaModel requestToModel(HistoricoDisciplinaRequest request);

    @Mapping(target = "professorId", source = "professorModel.id")
    @Mapping(target = "disciplinaId", source = "disciplinaModel.id")
    @Mapping(target = "matriculaId", source = "matriculaModel.id")
    HistoricoDisciplinaRequest modelToRequest(HistoricoDisciplinaModel model);
}
