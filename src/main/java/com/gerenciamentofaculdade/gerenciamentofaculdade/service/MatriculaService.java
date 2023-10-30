package com.gerenciamentofaculdade.gerenciamentofaculdade.service;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.MatriculaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.MatriculaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.MatriculaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.MatriculaRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.PaginationUtils;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        log.info("Persistida matrícula com ID: {}", matriculaDTO.getId());
        return matriculaDTO;
    }

    public MatriculaDTO getMatricula(Long id) {
        return MatriculaMapper.INSTANCE.modelToDto(matriculaRepository.findById(id).orElseThrow(() -> {
            log.warn("Não foi encontrado uma Matrícula com o ID: {}", id);
            return new EntityNotFoundException("Operação não concluída, não foi encontrada uma matrícula com este ID");
        }));
    }

    public Page<MatriculaDTO> getAllMatriculas(Pageable pageable) {
        return PaginationUtils.paginarLista(matriculaRepository.findAll()
                .stream().map(MatriculaMapper.INSTANCE::modelToDto).toList(), pageable);
    }

    @Transactional
    public MatriculaDTO updateMatricula(MatriculaDTO matriculaDTO, Long id) {
        MatriculaModel matriculaModel = MatriculaMapper.INSTANCE.dtoToModel(matriculaDTO);
        matriculaModel.setId(id);
        matriculaRepository.save(matriculaModel);
        log.info("Atualizada matrícula com ID: {}", id);
        matriculaDTO.setId(id);
        return matriculaDTO;
    }

    @Transactional
    public void deleteMatricula(Long id) {
        matriculaRepository.deleteById(id);
        log.warn("Deletada matrícula com o ID: {}", id);
    }
}
