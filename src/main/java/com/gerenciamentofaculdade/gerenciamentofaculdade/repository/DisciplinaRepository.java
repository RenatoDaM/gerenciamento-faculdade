package com.gerenciamentofaculdade.gerenciamentofaculdade.repository;

import com.gerenciamentofaculdade.gerenciamentofaculdade.model.CursoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.DisciplinaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<DisciplinaModel, Long> {
    boolean existsByNomeAndCursoModel(String nome, CursoModel cursoModel);
}
