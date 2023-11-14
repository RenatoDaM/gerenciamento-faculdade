package com.gerenciamentofaculdade.gerenciamentofaculdade.service;

import com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper.AlunoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper.HistoricoDisciplinaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.*;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.HistoricoDisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.search.AlunoParams;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.EntityUpdateLogger;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.PaginationUtils;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.RaGenerator;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.*;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final HistoricoDisciplinaRepository historicoDisciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final MatriculaRepository matriculaRepository;
    private final Logger log = LoggerFactory.getLogger(AlunoService.class);

    public AlunoService(AlunoRepository alunoRepository, HistoricoDisciplinaRepository historicoDisciplinaRepository, MatriculaRepository matriculaRepository, ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository, MatriculaRepository matriculaRepository1) {
        this.alunoRepository = alunoRepository;
        this.historicoDisciplinaRepository = historicoDisciplinaRepository;
        this.professorRepository = professorRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.matriculaRepository = matriculaRepository1;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public AlunoDTO postAluno(AlunoDTO aluno) {
        if (alunoRepository.findByEmail(aluno.getEmail()).isPresent()) {
            log.warn("Não foi possível persistir aluno, pois foi inserido um email já existente no sistema");
            throw new IllegalArgumentException("Operação não concluída. Já existe um aluno com este email cadastrado");
        }

        AlunoModel alunoModelToPersist = AlunoMapper.INSTANCE.dtoToModel(aluno);
        alunoModelToPersist.setRa(RaGenerator.gerarRA());

        if (alunoRepository.findByRa(alunoModelToPersist.getRa()).isPresent()) {
            log.warn("Não foi possível persistir aluno, pois foi inserido um RA já existente");
            throw new IllegalArgumentException("Operação não concluída. Já existe um aluno com este RA");
        }

        AlunoDTO retornarAlunoComId = AlunoMapper.INSTANCE.modelToDTO(alunoRepository.save(alunoModelToPersist));
        log.info("Aluno com RA: {} foi persistido com sucesso", alunoModelToPersist.getRa());
        return retornarAlunoComId;
    }

    public AlunoDTO getAluno(Long id) {
        return AlunoMapper.INSTANCE.modelToDTO(alunoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Não foi encontrado um Aluno com o ID: {}", id);
                    return new EntityNotFoundException("Operação não concluída, não foi encontrado um aluno com este ID");
                }));
    }

    public Page<AlunoDTO> getAllAlunos(AlunoParams params, Pageable pageable) {
        List<AlunoDTO> resultList = new ArrayList<>();
        alunoRepository.findAll().forEach(alunoModel -> resultList.add(AlunoMapper.INSTANCE.modelToDTO(alunoModel)));
        return PaginationUtils.paginarLista(resultList, pageable);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public AlunoDTO updateAluno(Long id, AlunoDTO aluno) throws Exception {
        AlunoModel alunoAntesDaAtualizacao = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Operação não concluída, não foi encontrado um aluno com este ID"));

        EntityUpdateLogger.loggarModificacoes(alunoAntesDaAtualizacao, aluno);

        var modelToSave = AlunoMapper.INSTANCE.dtoToModel(aluno);
        modelToSave.setRa(alunoAntesDaAtualizacao.getRa());
        modelToSave.setId(alunoAntesDaAtualizacao.getId());

        var response = alunoRepository.save(modelToSave);
        return AlunoMapper.INSTANCE.modelToDTO(response);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void deleteAluno(Long id) {
        AlunoModel alunoModel = alunoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Não foi encontrado um Aluno com o ID: {} para deletar", id);
                    return new EntityNotFoundException("Operação não concluída, não foi encontrado um aluno com este ID para poder deletar");
                });

        alunoRepository.deleteById(id);
        log.info("Aluno com ID: {} e RA: {} foi deletado do banco de dados", id, alunoModel.getRa());
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
}
