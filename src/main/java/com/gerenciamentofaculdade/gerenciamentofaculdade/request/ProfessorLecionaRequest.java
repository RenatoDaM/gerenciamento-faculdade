package com.gerenciamentofaculdade.gerenciamentofaculdade.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.ProfessorDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.DisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorLecionaDisciplinaKey;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorModel;
import jakarta.persistence.*;

import java.time.LocalTime;

public class ProfessorLecionaRequest {
    ProfessorLecionaDisciplinaKey relacao;

    Integer diaLecionado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    LocalTime horario;

    public ProfessorLecionaDisciplinaKey getRelacao() {
        return relacao;
    }

    public void setRelacao(ProfessorLecionaDisciplinaKey relacao) {
        this.relacao = relacao;
    }

    public Integer getDiaLecionado() {
        return diaLecionado;
    }

    public void setDiaLecionado(Integer diaLecionado) {
        this.diaLecionado = diaLecionado;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
