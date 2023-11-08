package com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.DiaDaSemanaEnum;
import jakarta.persistence.*;

import java.time.LocalTime;

public class HorarioAulaDTO {
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaDaSemanaEnum diaSemana;

    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    LocalTime horarioInicio;

    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    LocalTime horarioFim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiaDaSemanaEnum getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaDaSemanaEnum diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }
}
