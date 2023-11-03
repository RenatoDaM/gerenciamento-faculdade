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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorLecionaDisciplinaRepository professorLecionaDisciplinaRepository;

    public ProfessorService(ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository, ProfessorLecionaDisciplinaRepository professorLecionaDisciplinaRepository) {
        this.professorRepository = professorRepository;
        this.professorLecionaDisciplinaRepository = professorLecionaDisciplinaRepository;
    }

    public ProfessorDTO postProfessor(ProfessorDTO professorDTO) {
        ProfessorModel savedEntity = professorRepository.save(ProfessorMapper.INSTANCE.dtoToModel(professorDTO));
        return ProfessorMapper.INSTANCE.modelToDTO(savedEntity);
    }

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
