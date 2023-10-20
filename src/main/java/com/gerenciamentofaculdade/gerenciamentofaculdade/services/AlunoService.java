package com.gerenciamentofaculdade.gerenciamentofaculdade.services;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.AlunoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.models.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.AlunoRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.search.AlunoParams;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final Logger log = LoggerFactory.getLogger(AlunoService.class);

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoDTO postAluno(AlunoDTO aluno) throws Exception {
        AlunoModel verificarAluno = alunoRepository.findByRa(aluno.getRa());
        if (verificarAluno != null) {
            log.warn("Não foi possível persistir aluno, pois foi inserido um RA já existente");
            throw new Exception("Operação não concluída. Já existe um aluno com este RA");
        }

        if (aluno == null) {
            log.warn("Não foi possível persistir aluno, pois o body estava vazio");
            throw new Exception("Aluno não pode ser nulo");
        }

        AlunoModel alunoModelToPersist = AlunoMapper.INSTANCE.dtoToModel(aluno);;
        AlunoDTO retornarAlunoComId = AlunoMapper.INSTANCE.modelToDTO(alunoRepository.save(alunoModelToPersist));
        log.info("Aluno com RA: {} foi persistido com sucesso", aluno.getRa());
        return retornarAlunoComId;
    }

    public AlunoModel getAluno(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Não foi encontrado um Aluno com o ID: {}", id);
                    return new EntityNotFoundException("Operação não concluída, não foi encontrado um aluno com este ID");
                });
    }

    public Page<AlunoDTO> getAllAlunos(AlunoParams params, Pageable pageable) {
        List<AlunoDTO> resultList = new ArrayList<>();
        alunoRepository.findAll().forEach(alunoModel -> resultList.add(AlunoMapper.INSTANCE.modelToDTO(alunoModel)));
        return paginarLista(resultList, pageable);
    }

    public AlunoDTO updateAluno(Long id, AlunoModel aluno) {
        Optional<AlunoModel> alunoAntesDaAtualizacao = alunoRepository.findById(id);
        Optional<AlunoModel> alunoModel = Optional.ofNullable(aluno);
        if (alunoAntesDaAtualizacao.isPresent() && alunoModel.isPresent()) {
            aluno.setId(id);
            loggarModificacoes(alunoAntesDaAtualizacao.get(), aluno);
            AlunoDTO alunoDTO = AlunoMapper.INSTANCE.modelToDTO(alunoRepository.save(aluno));
            return alunoDTO;
        } else {
            throw new EntityNotFoundException("Operação não concluida, não foi encontrado um aluno com este ID");
        }
    }

    public void deleteAluno(Long id) {
        alunoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Não foi encontrado um Aluno com o ID: {} para deletar", id);
                    return new EntityNotFoundException("Operação não concluída, não foi encontrado um aluno com este ID para poder deletar");
                });
        alunoRepository.deleteById(id);
    }

    private Page<AlunoDTO> paginarLista(List<AlunoDTO> lista, Pageable pageable){
        int inicio, fim;
        inicio = (int) pageable.getOffset();
        fim = (inicio + pageable.getPageSize()) > lista.size() ? lista.size() : (inicio + pageable.getPageSize());
        Page<AlunoDTO> paginacao = new PageImpl<>(lista.stream().collect(Collectors.toList()).subList(inicio, fim), pageable, lista.size());
        return paginacao;
    }

    private void loggarModificacoes(AlunoModel antes, AlunoModel depois) {
        Map<String, String> mapAntes = criarMapParaComparacao(antes);
        Map<String, String> mapDepois= criarMapParaComparacao(depois);

        for (Map.Entry<String, String> entry : mapDepois.entrySet()) {
            if (!entry.getValue().equals(mapAntes.get(entry.getKey()))) log.info("Usuário com RA: {} foi atualizado. Campo {} foi alterado. Valor {} foi alterado para: {}", antes.getRa(), entry.getKey(), mapAntes.get(entry.getKey()), entry.getValue());
        }
    }

    private Map<String, String> criarMapParaComparacao(AlunoModel aluno) {
        Map<String, String> compare = new HashMap<>();
        compare.put("nome", aluno.getNome());
        compare.put("ra", String.valueOf(aluno.getRa()));
        compare.put("telefone1", aluno.getTelefone1());
        compare.put("telefone2", aluno.getTelefone2());

        for (Map.Entry<String, String> entry : compare.entrySet()) {
            if (entry.getValue() == null) {
                entry.setValue("<Nulo>");
            } else if (entry.getValue().equals("")) {
                entry.setValue("<Vazio>");
            }
        }

        return compare;
    }
}
