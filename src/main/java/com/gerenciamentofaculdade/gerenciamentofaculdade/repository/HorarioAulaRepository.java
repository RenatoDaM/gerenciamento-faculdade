package com.gerenciamentofaculdade.gerenciamentofaculdade.repository;

import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.DiaDaSemanaEnum;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.HorarioAulaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface HorarioAulaRepository extends JpaRepository<HorarioAulaModel, Long> {
    Optional<HorarioAulaModel> findByDiaSemanaAndHorarioInicioAndHorarioFim(DiaDaSemanaEnum diaSemana, LocalTime inicio, LocalTime fim);
}
