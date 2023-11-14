package com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.MatriculaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.MatriculaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AlunoMapper.class, CursoMapper.class})
public interface MatriculaMapper {
    MatriculaMapper INSTANCE = Mappers.getMapper( MatriculaMapper.class );

    @Mapping(target = "alunoModel", source = "alunoDTO")
    @Mapping(target = "cursoModel", source = "cursoDTO")
    MatriculaModel dtoToModel(MatriculaDTO matriculaDTO);

    @Mapping(target = "alunoDTO", source = "alunoModel")
    @Mapping(target = "cursoDTO", source = "cursoModel")
    MatriculaDTO modelToDto(MatriculaModel matriculaModel);
/*    default AlunoModel alunoDtoToAlunoModel(AlunoDTO alunoDTO) {
        return Mappers.getMapper(AlunoMapper.class).dtoToModel(alunoDTO);
    }

    default CursoModel cursoDtoToCursoModel(CursoDTO cursoDTO) {
        return Mappers.getMapper(CursoMapper.class).dtoToModel(cursoDTO);
    }*/
}
