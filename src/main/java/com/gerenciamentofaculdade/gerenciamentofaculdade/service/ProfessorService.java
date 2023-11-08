package com.gerenciamentofaculdade.gerenciamentofaculdade.service;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.DisciplinaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.ProfessorLecionaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.ProfessorMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.ProfessorDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.*;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.*;
import com.gerenciamentofaculdade.gerenciamentofaculdade.request.ProfessorLecionaRequest;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.ProfessorLecionaResponse;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.PaginationUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorLecionaDisciplinaRepository professorLecionaDisciplinaRepository;
    private final ProfessorMapper professorMapper;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorLecionaHorarioRepository professorLecionaHorarioRepository;
    private final HorarioAulaRepository horarioAulaRepository;

    public ProfessorService(ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository, ProfessorLecionaDisciplinaRepository professorLecionaDisciplinaRepository,
                            ProfessorMapper professorMapper,
                            ProfessorLecionaMapper professorLecionaMapper, DisciplinaRepository disciplinaRepository1, ProfessorLecionaHorarioRepository professorLecionaHorarioRepository, HorarioAulaRepository horarioAulaRepository) {
        this.professorRepository = professorRepository;
        this.professorLecionaDisciplinaRepository = professorLecionaDisciplinaRepository;
        this.professorMapper = professorMapper;
        this.disciplinaRepository = disciplinaRepository1;
        this.professorLecionaHorarioRepository = professorLecionaHorarioRepository;
        this.horarioAulaRepository = horarioAulaRepository;
    }

    @Transactional
    public ProfessorDTO postProfessor(ProfessorDTO professorDTO) {
        ProfessorModel savedEntity = professorRepository.save(ProfessorMapper.INSTANCE.dtoToModel(professorDTO));
        return ProfessorMapper.INSTANCE.modelToDTO(savedEntity);
    }

    public ProfessorDTO getProfessor(Long id) {
        return ProfessorMapper.INSTANCE.modelToDTO(professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado um professor com o ID " + id)));
    }

    public Page<ProfessorDTO> getAllProfessores(Pageable pageable) {
        return PaginationUtils.paginarLista(professorRepository.findAll().stream()
                .map(professorMapper::modelToDTO).collect(Collectors.toList()), pageable);
    }


    @Transactional
    public ProfessorDTO putProfessor(Long id, ProfessorDTO professorDTO) {
        professorDTO.setId(id);
        var persistedProfessor = professorRepository.save(professorMapper.dtoToModel(professorDTO));
        return professorMapper.modelToDTO(persistedProfessor);
    }

    public void deleteProfessor(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Não foi encontrado um professor com este ID para poder deletar.");
        }
    }

    // vinculo disciplina-professor
    @Transactional
    public ProfessorLecionaResponse vincularDisciplinaAoProfessor(ProfessorLecionaRequest professorLecionaRequest) {
        var professorModel = professorRepository.findById(professorLecionaRequest.getRelacao().getProfessorId()).orElse(null);
        var disciplinaModel = disciplinaRepository.findById(professorLecionaRequest.getRelacao().getDisciplinaId()).orElse(null);

        if (professorModel != null && disciplinaModel != null) {
            var model = new ProfessorLecionaDisciplinaModel(professorLecionaRequest.getRelacao(), professorModel,
                    disciplinaModel);

            var modelResult1 = professorLecionaDisciplinaRepository.save(model);
            var horarioAula = new HorarioAulaModel();
            horarioAula.setHorarioFim(professorLecionaRequest.getHorarioFim());
            horarioAula.setHorarioInicio(professorLecionaRequest.getHorarioInicio());
            horarioAula.setDiaSemana(professorLecionaRequest.getDiaSemana());
            horarioAulaRepository.save(horarioAula);

            var horarioProfessor = new ProfessorLecionaHorarioModel();
            horarioProfessor.setHorarioAulaModel(horarioAula);
            horarioProfessor.setProfessorLecionaDisciplinaModel(modelResult1);
            professorLecionaHorarioRepository.save(horarioProfessor);
            return new ProfessorLecionaResponse(model.getProfessorModel().getId(), model.getProfessorModel().getNome(),
                    model.getProfessorModel().getRegistroConselho(), List.of(DisciplinaMapper.INSTANCE.modelToDTO(disciplinaModel)));
        } else {
            throw new EntityNotFoundException("Operação FALHOU. Não foi encontrado um professor ou aluno com o respectivo ID");
        }
    }

    public ProfessorLecionaResponse getDisciplinasVinculadas(Long id) {
        var response = new ProfessorLecionaResponse();
        var professor = professorRepository.findById(id);
        if (professor.isPresent()) {
            response.setDisciplinasList(professorLecionaDisciplinaRepository.findDisciplinasByProfessorId(id)
                    .stream().map(DisciplinaMapper.INSTANCE::modelToDTO).collect(Collectors.toList()));

            response.setNome(professor.get().getNome());
            response.setRegistroConselho(professor.get().getRegistroConselho());
            response.setId(professor.get().getId());
        }

        return response;
    }

    public Page<ProfessorLecionaResponse> getAllProfessorLecionaDisciplina(Pageable pageable) {
        var professores = professorRepository.findAll();
        List<ProfessorLecionaResponse> response = new ArrayList<>();

        professores.forEach(professor -> {
            var professorLeciona = new ProfessorLecionaResponse();
            professorLeciona.setNome(professor.getNome());
            professorLeciona.setId(professor.getId());
            professorLeciona.setRegistroConselho(professor.getRegistroConselho());
            professorLeciona.setDisciplinasList(professorLecionaDisciplinaRepository.findDisciplinasByProfessorId(professor.getId()).stream().map(DisciplinaMapper.INSTANCE::modelToDTO).collect(Collectors.toList()));
            response.add(professorLeciona);
        });

        return PaginationUtils.paginarLista(response, pageable);
    }

    @Transactional
    public void deleteProfessorLeciona(Long professorId, Long disciplinaId) {
        if (professorLecionaDisciplinaRepository.findProfessorLecionaDisciplinaModelsByKey(new ProfessorLecionaDisciplinaKey(professorId, disciplinaId)).isPresent()) {
            professorLecionaDisciplinaRepository.deleteRelation(professorId, disciplinaId);
        } else {
            throw new EntityNotFoundException("Não foi encontrado uma relação 'professor leciona' respectiva a estes ids");
        }
    }
}
