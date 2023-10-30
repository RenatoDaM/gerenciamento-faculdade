package com.gerenciamentofaculdade.gerenciamentofaculdade.service;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.CursoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.CursoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.CursoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.CursoRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.PaginationUtils;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {
    private final Logger log = LoggerFactory.getLogger(CursoService.class);

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CursoDTO postCurso(CursoDTO cursoDTO) {
        if (cursoRepository.existsCursoModelByNome(cursoDTO.getNome())) throw new IllegalArgumentException("Operação não concluída. Já existe um curso com este nome");
        CursoModel cursoToPersist = CursoMapper.INSTANCE.dtoToModel(cursoDTO);
        cursoDTO.setId(cursoRepository.save(cursoToPersist).getId());
        log.info("Persistido curso com id: {} e nome {}", cursoDTO.getId(), cursoDTO.getNome());
        return cursoDTO;
    }

    public CursoDTO getCurso(Long id) {
        return CursoMapper.INSTANCE.modelToDTO(cursoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Não foi encontrado um Curso com o ID: {}", id);
                    return new EntityNotFoundException("Operação não concluída, não foi encontrado um curso com este ID");
                }));
    }

    public Page<CursoDTO> getAllCursos(Pageable pageable) {
        List<CursoDTO> cursoDTOList = new ArrayList<>();
        cursoRepository.findAll().forEach(cursoModel -> cursoDTOList.add(CursoMapper.INSTANCE.modelToDTO(cursoModel)));
        return PaginationUtils.paginarLista(cursoDTOList, pageable);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CursoDTO updateCurso(Long id, CursoDTO cursoDTO) {
        cursoDTO.setId(id);

        if (cursoRepository.existsCursoModelByNome(cursoDTO.getNome()))
            throw new IllegalArgumentException("Operação não concluída. Já existe um curso com este nome");

        if (cursoRepository.existsById(id)) {
            cursoRepository.save(CursoMapper.INSTANCE.dtoToModel(cursoDTO));
            log.info("Atualizado curso com o ID: {}", id);
        } else {
            throw new EntityNotFoundException("Operação não concluída. Não foi possível atualizar pois não foi encontrado um curso com este ID no banco de dados");
        }

        return cursoDTO;
    }

    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
        log.warn("Deletado curso com ID: {}", id);
    }
}
