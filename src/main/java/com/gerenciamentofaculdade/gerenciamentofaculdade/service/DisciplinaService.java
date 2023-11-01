package com.gerenciamentofaculdade.gerenciamentofaculdade.service;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.DisciplinaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.MatriculaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.DisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.DisciplinaRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.PaginationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public DisciplinaDTO postDisciplina(DisciplinaDTO disciplinaDTO) {
        DisciplinaModel disciplinaToPersist = DisciplinaMapper.INSTANCE.dtoToModel(disciplinaDTO);
        disciplinaDTO.setId(disciplinaRepository.save(disciplinaToPersist).getId());
        return disciplinaDTO;
    }

    public DisciplinaDTO getDisciplina(Long id) {
        return DisciplinaMapper.INSTANCE.modelToDTO(disciplinaRepository.findById(id)
                .orElseThrow()
        );
    }

    public Page<DisciplinaDTO> getAllDisciplinas(Pageable pageable) {
        return PaginationUtils.paginarLista(disciplinaRepository.findAll()
                .stream().map(DisciplinaMapper.INSTANCE::modelToDTO).toList(), pageable);
    }

    public DisciplinaDTO putDisciplina(DisciplinaDTO disciplinaDTO, Long id) {
        disciplinaDTO.setId(id);
        DisciplinaModel savedDisciplina = disciplinaRepository.save(DisciplinaMapper.INSTANCE.dtoToModel(disciplinaDTO));
        return DisciplinaMapper.INSTANCE.modelToDTO(savedDisciplina);
    }

    public void deleteDisciplina(Long id) {
        disciplinaRepository.deleteById(id);
    }
}
