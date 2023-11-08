package com.gerenciamentofaculdade.gerenciamentofaculdade.service;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.DisciplinaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.ProfessorLecionaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.ProfessorMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.HorarioAulaDTO;
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
    public ProfessorLecionaResponse vincularDisciplinaAoProfessor(ProfessorLecionaRequest professorLecionaRequest) throws Exception {
        ProfessorModel professorModel = professorRepository.findById(professorLecionaRequest.getRelacao().getProfessorId())
                .orElseThrow(() -> new EntityNotFoundException("Operação FALHOU. Não foi encontrado um professor com o respectivo ID"));

        DisciplinaModel disciplinaModel = disciplinaRepository.findById(professorLecionaRequest.getRelacao().getDisciplinaId())
                .orElseThrow(() -> new EntityNotFoundException("Operação FALHOU. Não foi encontrada uma disciplina com o respectivo ID"));

        // Checa se o professor possui conflito de tempo para lecionar a disciplina, ou seja, se já leciona outra disciplina neste horário
        if (professorLecionaHorarioRepository.hasTimeConflict(professorLecionaRequest.getRelacao().getProfessorId(), professorLecionaRequest.getDiaSemana(), professorLecionaRequest.getHorarioInicio(), professorLecionaRequest.getHorarioFim()))
            throw new EntityNotFoundException("Conflito de tempo");

        // Crio vínculo professor-leciona
        var professorLecionaDisciplinaModel = professorLecionaDisciplinaRepository.save(new ProfessorLecionaDisciplinaModel(professorLecionaRequest.getRelacao(), professorModel,
                disciplinaModel));

        // Produro no banco de dados um horário condizente com o da request
        var horarioAulaPersisted = horarioAulaRepository.findByDiaSemanaAndHorarioInicioAndHorarioFim(professorLecionaRequest
                .getDiaSemana(), professorLecionaRequest.getHorarioInicio(), professorLecionaRequest.getHorarioFim());

        var horarioProfessor = new ProfessorLecionaHorarioModel();
        horarioProfessor.setProfessorLecionaDisciplinaModel(professorLecionaDisciplinaModel);

        // Caso tenha encontrado um horario e dia condizente
        if (horarioAulaPersisted.isPresent()) {
            horarioProfessor.setHorarioAulaModel(horarioAulaPersisted.get());
        } else {
            // Caso não, eu crio no banco de dados
            horarioProfessor.setHorarioAulaModel(horarioAulaRepository.save(new HorarioAulaModel(professorLecionaRequest.getDiaSemana(),
                    professorLecionaRequest.getHorarioInicio(), professorLecionaRequest.getHorarioFim())));
        }

        // Persisto na tabela muitos pra muitos, que relaciona ProfessorLecionaDisciplina com HorarioAula
        professorLecionaHorarioRepository.save(horarioProfessor);
        return new ProfessorLecionaResponse(professorLecionaRequest.getRelacao(), disciplinaModel, horarioProfessor.getHorarioAulaModel());

    }

    public List<ProfessorLecionaResponse> getDisciplinasVinculadas(Long id) {
        return professorLecionaDisciplinaRepository.returnHorariosAndDisciplinas(id);
    }

    /*public Page<ProfessorLecionaResponse> getAllProfessorLecionaDisciplina(Pageable pageable) {
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
    }*/

    @Transactional
    public void deleteProfessorLeciona(Long professorId, Long disciplinaId) {
        if (professorLecionaDisciplinaRepository.findProfessorLecionaDisciplinaModelsByKey(new ProfessorLecionaDisciplinaKey(professorId, disciplinaId)).isPresent()) {
            professorLecionaDisciplinaRepository.deleteRelation(professorId, disciplinaId);
        } else {
            throw new EntityNotFoundException("Não foi encontrado uma relação 'professor leciona' respectiva a estes ids");
        }
    }
}
