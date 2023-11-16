package com.gerenciamentofaculdade.gerenciamentofaculdade.service;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.HistoricoDisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.*;
import com.gerenciamentofaculdade.gerenciamentofaculdade.request.MatriculaUpdateRequest;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper.HistoricoDisciplinaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper.MatriculaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.MatriculaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.MatriculaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.EntityUpdateLogger;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.PaginationUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.stream.Collectors;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    private final HistoricoDisciplinaRepository historicoDisciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, CursoRepository cursoRepository, HistoricoDisciplinaRepository historicoDisciplinaRepository, ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
        this.historicoDisciplinaRepository = historicoDisciplinaRepository;
        this.professorRepository = professorRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    private final Logger log = LoggerFactory.getLogger(MatriculaService.class);

    @Transactional(rollbackFor = {SQLException.class})
    public MatriculaDTO matricularAluno(MatriculaDTO matriculaDTO) {
        if (cursoRepository.findById(matriculaDTO.getCursoId()).isEmpty()) throw new EntityNotFoundException("Não foi encontrado um curso com o ID desta solicitação de matrícula");
        if (alunoRepository.findById(matriculaDTO.getAlunoId()).isEmpty()) throw new EntityNotFoundException("Não foi encontrado um aluno com o ID desta solicitação de matrícula");

        // possívelmente criar no sistema automaticamente que cadastra o aluno com historicoDisciplina do primeiro semestre,
        // podendo alterar depois. Próximos semestres o aluno que escolhe.

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
    public MatriculaDTO updateMatricula(MatriculaUpdateRequest request, Long id) throws IllegalAccessException {
        MatriculaModel matriculaModel = matriculaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi encontrada uma matrícula com este ID"));
        if (alunoRepository.findById(matriculaModel.getAlunoModel().getId()).isEmpty()) throw new EntityNotFoundException("Não foi encontrado um aluno com o ID desta solicitação de matrícula");
        if (cursoRepository.findById(matriculaModel.getCursoModel().getId()).isEmpty()) throw new EntityNotFoundException("Não foi encontrado um curso com o ID desta solicitação de matrícula");
        matriculaModel.setEstadoMatricula(request.getEstadoMatricula());
        matriculaModel.setCiclo(request.getCiclo());
        matriculaRepository.save(matriculaModel);
        log.info("Atualizada matrícula com ID: {}", id);
        EntityUpdateLogger.loggarModificacoes(matriculaModel, matriculaModel);
        return MatriculaMapper.INSTANCE.modelToDto(matriculaModel);
    }

    @Transactional
    public void deleteMatricula(Long id) {
        matriculaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Operação não concluída, não foi encontrada uma matrícula com este ID para poder deletar"));
        matriculaRepository.deleteById(id);
        log.warn("Deletada matrícula com o ID: {}", id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public HistoricoDisciplinaDTO adicionarDisciplinaAoHistorico(HistoricoDisciplinaDTO request) {
        if (!professorRepository.existsById(request.getProfessorId())) {
            throw new EntityNotFoundException("Professor não encontrado");
        }

        if (!matriculaRepository.existsById(request.getMatriculaId())) {
            throw new EntityNotFoundException("Aluno não encontrado");
        }

        if (!disciplinaRepository.existsById(request.getDisciplinaId())) {
            throw new EntityNotFoundException("Disciplina não encontrada");
        }

        if (historicoDisciplinaRepository.existsByMatriculaModelIdAndDisciplinaModelIdAndData(request.getMatriculaId(), request.getDisciplinaId(), request.getData())) {
            throw new EntityExistsException("Já foi criado um histórico para esta matrícula e curso nesta data");
        }

        var result = historicoDisciplinaRepository.save(HistoricoDisciplinaMapper.INSTANCE.requestToModel(request));
        return HistoricoDisciplinaMapper.INSTANCE.modelToRequest(result);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Page<HistoricoDisciplinaDTO> getAllHistoricosByMatriculaId(Long id, Pageable pageable) {
        var test = historicoDisciplinaRepository.findByMatriculaModelId(id).stream()
                .map(HistoricoDisciplinaMapper.INSTANCE::modelToRequest).collect(Collectors.toList());
        return PaginationUtils.paginarLista(test, pageable);
    }
}
