package com.gerenciamentofaculdade.gerenciamentofaculdade.services;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.AlunoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.CursoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.MatriculaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.MatriculaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.models.MatriculaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.MatriculaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    private final Logger log = LoggerFactory.getLogger(MatriculaService.class);

    @Transactional(rollbackFor = {SQLException.class})
    public MatriculaDTO matricularAluno(MatriculaDTO matriculaDTO) {
        MatriculaModel matriculaModel = MatriculaMapper.INSTANCE.dtoToModel(matriculaDTO);
        matriculaDTO.setId(matriculaRepository.save(matriculaModel).getId());
        return matriculaDTO;
    }

    public MatriculaDTO getMatricula(Long id) {
        return MatriculaMapper.INSTANCE.modelToDto(matriculaRepository.findById(id).orElseThrow(() -> {
            log.warn("Não foi encontrado uma Matrícula com o ID: {}", id);
            return new EntityNotFoundException("Operação não concluída, não foi encontrada uma matrícula com este ID");
        }));


    }
}
