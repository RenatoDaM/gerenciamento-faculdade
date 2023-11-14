package com.gerenciamentofaculdade.gerenciamentofaculdade.repository;

import com.gerenciamentofaculdade.gerenciamentofaculdade.model.HistoricoDisciplinaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;

@Repository
public interface HistoricoDisciplinaRepository extends JpaRepository<HistoricoDisciplinaModel, Long> {
    boolean existsByMatriculaModelIdAndDisciplinaModelIdAndData(Long matriculaId, Long disciplinaId, YearMonth data);
}
