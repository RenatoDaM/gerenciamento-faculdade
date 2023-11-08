package com.gerenciamentofaculdade.gerenciamentofaculdade.repository;

import com.gerenciamentofaculdade.gerenciamentofaculdade.model.DisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorLecionaHorarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorLecionaHorarioRepository extends JpaRepository<ProfessorLecionaHorarioModel, Long> {
}
