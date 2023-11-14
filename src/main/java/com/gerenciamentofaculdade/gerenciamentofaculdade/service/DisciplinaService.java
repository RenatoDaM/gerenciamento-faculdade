package com.gerenciamentofaculdade.gerenciamentofaculdade.service;

import com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper.CursoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper.DisciplinaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.DisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.CursoRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.DisciplinaRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.EntityUpdateLogger;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.PaginationUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;
    private final CursoRepository cursoRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository, CursoRepository cursoRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.cursoRepository = cursoRepository;
    }

    public DisciplinaDTO postDisciplina(DisciplinaDTO disciplinaDTO) {
        // colocar a tratativa: não pode ter uma disciplina com o nome igual ligada ao mesmo curso
        if (disciplinaRepository.existsByNomeAndCursoModel(disciplinaDTO.getNome(),
                CursoMapper.INSTANCE.dtoToModel(disciplinaDTO.getCursoDTO()))) throw new EntityExistsException("Já existe uma disciplina com o mesmo nome para este curso");
        if (!cursoRepository.existsById(disciplinaDTO.getCursoDTO().getId())) throw new EntityNotFoundException("Não foi encontrado o curso ao qual a disciplina pertence. ID não existente");
        DisciplinaModel disciplinaToPersist = DisciplinaMapper.INSTANCE.dtoToModel(disciplinaDTO);
        disciplinaDTO.setId(disciplinaRepository.save(disciplinaToPersist).getId());
        return disciplinaDTO;
    }

    public DisciplinaDTO getDisciplina(Long id) {
        return DisciplinaMapper.INSTANCE.modelToDTO(disciplinaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
        );
    }

    public Page<DisciplinaDTO> getAllDisciplinas(Pageable pageable) {
        return PaginationUtils.paginarLista(disciplinaRepository.findAll()
                .stream().map(DisciplinaMapper.INSTANCE::modelToDTO).toList(), pageable);
    }

    public DisciplinaDTO putDisciplina(DisciplinaDTO disciplinaDTO, Long id) throws IllegalAccessException {
        Optional<DisciplinaModel> disciplinaAntesDaAtualizacao = disciplinaRepository.findById(id);
        if (disciplinaAntesDaAtualizacao.isEmpty()) throw new EntityNotFoundException("Não foi encontrado o curso ao qual a disciplina pertence. ID não existente");
        if (disciplinaRepository.existsByNomeAndCursoModel(disciplinaDTO.getNome(),
                CursoMapper.INSTANCE.dtoToModel(disciplinaDTO.getCursoDTO()))) throw new EntityExistsException("Já existe uma disciplina com o mesmo nome para este curso");
        disciplinaDTO.setId(id);
        DisciplinaModel savedDisciplina = disciplinaRepository.save(DisciplinaMapper.INSTANCE.dtoToModel(disciplinaDTO));
        EntityUpdateLogger.loggarModificacoes(disciplinaAntesDaAtualizacao, savedDisciplina);
        return DisciplinaMapper.INSTANCE.modelToDTO(savedDisciplina);
    }

    public void deleteDisciplina(Long id) {
        disciplinaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Operação não concluída, não foi encontrada uma disciplina com este ID para poder deletar"));
        disciplinaRepository.deleteById(id);
    }
}
