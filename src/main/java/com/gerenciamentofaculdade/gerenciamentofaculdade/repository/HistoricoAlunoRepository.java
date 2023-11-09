package com.gerenciamentofaculdade.gerenciamentofaculdade.repository;

import com.gerenciamentofaculdade.gerenciamentofaculdade.model.HistoricoAlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoAlunoRepository extends JpaRepository<HistoricoAlunoModel, Long> {
}
