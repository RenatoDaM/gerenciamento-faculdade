package com.gerenciamentofaculdade.gerenciamentofaculdade.services;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.CursoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.CursoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.models.CursoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CursoDTO postCurso(CursoDTO cursoDTO) {
        CursoModel cursoToPersist = CursoMapper.INSTANCE.dtoToModel(cursoDTO);
        cursoDTO.setId(cursoRepository.save(cursoToPersist).getId());
        return cursoDTO;
    }

}
