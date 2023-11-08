package com.gerenciamentofaculdade.gerenciamentofaculdade.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.ProfessorDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.DiaDaSemanaEnum;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.DisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorLecionaDisciplinaKey;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorModel;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ProfessorLecionaRequest {
    ProfessorLecionaDisciplinaKey relacao;

    @Enumerated(EnumType.STRING)
    private DiaDaSemanaEnum diaSemana;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioFim;


    public ProfessorLecionaDisciplinaKey getRelacao() {
        return relacao;
    }

    public void setRelacao(ProfessorLecionaDisciplinaKey relacao) {
        this.relacao = relacao;
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
