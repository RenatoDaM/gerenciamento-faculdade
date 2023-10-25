package com.gerenciamentofaculdade.gerenciamentofaculdade.services;

import com.gerenciamentofaculdade.gerenciamentofaculdade.config.mapper.AlunoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.models.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.AlunoRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.search.AlunoParams;
import com.gerenciamentofaculdade.gerenciamentofaculdade.utils.PaginationUtil;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final Logger log = LoggerFactory.getLogger(AlunoService.class);

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public AlunoDTO postAluno(AlunoDTO aluno) throws Exception {
        AlunoModel verificarAlunoRA = alunoRepository.findByRa(aluno.getRa());
        AlunoModel verificarAlunoEmail = alunoRepository.findByEmail(aluno.getEmail());

        if (verificarAlunoRA != null) {
            log.warn("Não foi possível persistir aluno, pois foi inserido um RA já existente");
            throw new IllegalArgumentException("Operação não concluída. Já existe um aluno com este RA");
        }

        if (verificarAlunoEmail != null) {
            log.warn("Não foi possível persistir aluno, pois foi inserido um email já existente no sistema");
            throw new IllegalArgumentException("Operação não concluída. Já existe um aluno com este email cadastrado");
        }

        if (aluno == null) {
            log.warn("Não foi possível persistir aluno, pois o body estava vazio");
            throw new Exception("Aluno não pode ser nulo");
        }

        AlunoModel alunoModelToPersist = AlunoMapper.INSTANCE.dtoToModel(aluno);
        AlunoDTO retornarAlunoComId = AlunoMapper.INSTANCE.modelToDTO(alunoRepository.save(alunoModelToPersist));
        log.info("Aluno com RA: {} foi persistido com sucesso", aluno.getRa());
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
        return PaginationUtil.paginarLista(resultList, pageable);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public AlunoDTO updateAluno(Long id, AlunoModel aluno) throws Exception {
        Optional<AlunoModel> alunoAntesDaAtualizacao = alunoRepository.findById(id);

        if (!Objects.equals(alunoRepository.findByRa(aluno.getRa()).getId(), aluno.getId())) {
            log.warn("Ouve tentativa mal sucedida de atualizar o RA do aluno: " + alunoAntesDaAtualizacao.get().getNome() + " que possui RA: " + alunoAntesDaAtualizacao.get().getRa() + ". Motivo do erro: Já existe outro aluno cadastrado com o RA inserido para atualização");
            throw new IllegalArgumentException("Não foi possível atualizar. Já existe outro aluno cadastrado com este RA");
        }

        if (alunoAntesDaAtualizacao.isPresent()) {
            aluno.setId(id);
            loggarModificacoes(alunoAntesDaAtualizacao.get(), aluno);
            return AlunoMapper.INSTANCE.modelToDTO(alunoRepository.save(aluno));
        } else {
            throw new EntityNotFoundException("Operação não concluida, não foi encontrado um aluno com este ID");
        }
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
        compare.put("ra", aluno.getRa());
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
