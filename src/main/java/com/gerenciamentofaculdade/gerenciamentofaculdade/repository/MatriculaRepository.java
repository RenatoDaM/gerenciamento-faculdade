package com.gerenciamentofaculdade.gerenciamentofaculdade.repository;

import com.gerenciamentofaculdade.gerenciamentofaculdade.model.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaModel, Long> {
    MatriculaModel findByAlunoModel_Id(Long id);
    List<MatriculaModel> findAllByAlunoModel_Id(Long id);
}
