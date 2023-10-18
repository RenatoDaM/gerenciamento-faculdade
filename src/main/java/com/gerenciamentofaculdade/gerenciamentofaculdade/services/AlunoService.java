package com.gerenciamentofaculdade.gerenciamentofaculdade.services;

import com.gerenciamentofaculdade.gerenciamentofaculdade.models.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.AlunoRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.search.AlunoParams;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final Logger log = LoggerFactory.getLogger(AlunoService.class);

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoModel postAluno(AlunoModel aluno) throws Exception {
        if (aluno == null) {
            log.error("Não foi possível persistir aluno, pois o body estava vazio");
            throw new Exception("Aluno não pode ser nulo");
        }

        log.info("Aluno com RA: {} foi persistido com sucesso", aluno.getRa());
        return alunoRepository.save(aluno);
    }

    public AlunoModel getAluno(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Não foi encontrado um Aluno com o ID: {}", id);
                    return new EntityNotFoundException("Operação não concluída, não foi encontrado um aluno com este ID");
                });
    }

    public Page<AlunoModel> getAllAlunos(AlunoParams params, Pageable pageable) {
        List<AlunoModel> resultList = new ArrayList<>();
        alunoRepository.findAll().forEach(resultList::add);
        return paginarLista(resultList, pageable);
    }

    public AlunoModel updateAluno(Long id, AlunoModel aluno) {
        if (alunoRepository.findById(id).isPresent()) {
            return alunoRepository.save(aluno);
        } else {
            throw new EntityNotFoundException("Operação não concluida, não foi encontrado um aluno com este ID");
        }
    }

    private Page<AlunoModel> paginarLista(List<AlunoModel> lista, Pageable pageable){
        int inicio, fim;
        inicio = (int) pageable.getOffset();
        fim = (inicio + pageable.getPageSize()) > lista.size() ? lista.size() : (inicio + pageable.getPageSize());
        Page<AlunoModel> paginacao = new PageImpl<AlunoModel>(lista.stream().collect(Collectors.toList()).subList(inicio, fim), pageable, lista.size());
        return paginacao;
    }
}
