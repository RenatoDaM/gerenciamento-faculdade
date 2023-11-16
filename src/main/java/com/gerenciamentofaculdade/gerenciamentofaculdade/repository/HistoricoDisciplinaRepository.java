package com.gerenciamentofaculdade.gerenciamentofaculdade.repository;

import com.gerenciamentofaculdade.gerenciamentofaculdade.model.HistoricoDisciplinaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface HistoricoDisciplinaRepository extends JpaRepository<HistoricoDisciplinaModel, Long> {
    boolean existsByMatriculaModelIdAndDisciplinaModelIdAndData(Long matriculaId, Long disciplinaId, YearMonth data);
    List<HistoricoDisciplinaModel> findByMatriculaModelId(Long id);
}
