package com.gerenciamentofaculdade.gerenciamentofaculdade.services;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.AlunoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.CursoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.MatriculaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.MatriculaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.models.MatriculaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.MatriculaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public MatriculaDTO matricularAluno(MatriculaDTO matriculaDTO) {
        MatriculaModel matriculaModel = MatriculaMapper.INSTANCE.dtoToModel(matriculaDTO);
        matriculaDTO.setId(matriculaRepository.save(matriculaModel).getId());
        return matriculaDTO;
    }
}
