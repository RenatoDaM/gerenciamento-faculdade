package com.gerenciamentofaculdade.gerenciamentofaculdade.service;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.DisciplinaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.ProfessorLecionaMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.ProfessorMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.ProfessorDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.DisciplinaRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.request.ProfessorLecionaRequest;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.ProfessorLecionaDisciplinaRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.ProfessorRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.ProfessorLecionaResponse;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.PaginationUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorLecionaDisciplinaRepository professorLecionaDisciplinaRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository, ProfessorLecionaDisciplinaRepository professorLecionaDisciplinaRepository,
                            ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorLecionaDisciplinaRepository = professorLecionaDisciplinaRepository;
        this.professorMapper = professorMapper;
    }

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
    public ProfessorLecionaResponse vincularDisciplinaAoProfessor(ProfessorLecionaRequest professorLecionaRequest) {
        professorLecionaDisciplinaRepository.save(ProfessorLecionaMapper.INSTANCE.dtoToModel(professorLecionaRequest));
        ProfessorLecionaResponse result = new ProfessorLecionaResponse();
        var professor = professorRepository.findById(professorLecionaRequest.getRelacao().getProfessorId());
        result.setNome(professor.get().getNome());
        result.setId(professor.get().getId());
        result.setRegistroConselho(professor.get().getRegistroConselho());
        var discplinaModelList = professorLecionaDisciplinaRepository.findDisciplinasByProfessorId(professorLecionaRequest.getRelacao().getProfessorId());
        List<DisciplinaDTO> dtoList = new ArrayList<>();
        discplinaModelList.forEach(disciplina -> dtoList.add(DisciplinaMapper.INSTANCE.modelToDTO(disciplina)));
        result.setDisciplinasList(dtoList);
        return result;
    }

    public ProfessorLecionaResponse getDisciplinasVinculadas(Long id) {
        var professorModel = professorRepository.findById(id);
        var professorLecionaResponse = new ProfessorLecionaResponse();
        professorLecionaResponse.setId(professorModel.get().getId());
        professorLecionaResponse.setRegistroConselho(professorModel.get().getRegistroConselho());
        professorLecionaResponse.setDisciplinasList(professorLecionaDisciplinaRepository.findDisciplinasByProfessorId(id).stream()
                .map(DisciplinaMapper.INSTANCE::modelToDTO).collect(Collectors.toList()));
        return professorLecionaResponse;
    }
}
