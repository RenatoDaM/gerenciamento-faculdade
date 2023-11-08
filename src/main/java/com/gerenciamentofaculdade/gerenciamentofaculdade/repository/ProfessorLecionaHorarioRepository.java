package com.gerenciamentofaculdade.gerenciamentofaculdade.repository;

import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.DiaDaSemanaEnum;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorLecionaHorarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalTime;

public interface ProfessorLecionaHorarioRepository extends JpaRepository<ProfessorLecionaHorarioModel, Long> {
    @Query("SELECT COUNT(ph) > 0 " +
            "FROM professor_leciona_horario ph " +
            "WHERE ph.professorLecionaDisciplinaModel.professorModel.id = :professorId " +
            "AND ph.horarioAulaModel.diaSemana = :diaSemana " +
            "AND ((ph.horarioAulaModel.horarioInicio <= :horarioFim AND ph.horarioAulaModel.horarioFim >= :horarioInicio) " +
            "OR (ph.horarioAulaModel.horarioInicio >= :horarioInicio AND ph.horarioAulaModel.horarioInicio <= :horarioFim))")
    boolean hasTimeConflict(@Param("professorId") Long professorId,
                            @Param("diaSemana") DiaDaSemanaEnum diaSemana,
                            @Param("horarioInicio") LocalTime horarioInicio,
                            @Param("horarioFim") LocalTime horarioFim);}
