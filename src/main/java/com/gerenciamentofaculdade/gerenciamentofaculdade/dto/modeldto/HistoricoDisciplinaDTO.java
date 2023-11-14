package com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto;

import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.EstadoDisciplinaEnum;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.PeriodoEnum;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.YearMonth;

public class HistoricoDisciplinaDTO {
    @Positive(message = "Valor de ID deve ser numérico e positivo")
    private Long id;
    @PositiveOrZero(message = "Frequencia deve ser positiva")
    private Float frequencia;
    @PositiveOrZero(message = "Média final deve ser positiva")
    @Max(10)
    private Float mediaFinal;
    @PositiveOrZero(message = "Quantidade de faltas deve ser positiva")
    private Integer faltas;
    @PositiveOrZero(message = "Quantidade de presenças deve ser positiva")
    private Integer presencas;
    private PeriodoEnum periodo;
    @Temporal(TemporalType.DATE)
    private YearMonth data;
    private EstadoDisciplinaEnum estadoDaDisciplina;
    @Positive(message = "Valor de professorId deve ser numérico e positivo")
    private Long professorId;
    @Positive(message = "Valor de matriculaId deve ser numérico e positivo")
    private Long matriculaId;
    @Positive(message = "Valor de disciplinaId deve ser numérico e positivo")
    private Long disciplinaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Float frequencia) {
        this.frequencia = frequencia;
    }

    public Float getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(Float mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    public Integer getPresencas() {
        return presencas;
    }

    public void setPresencas(Integer presencas) {
        this.presencas = presencas;
    }

    public PeriodoEnum getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEnum periodo) {
        this.periodo = periodo;
    }

    public YearMonth getData() {
        return data;
    }

    public void setData(YearMonth data) {
        this.data = data;
    }

    public EstadoDisciplinaEnum getEstadoDaDisciplina() {
        return estadoDaDisciplina;
    }

    public void setEstadoDaDisciplina(EstadoDisciplinaEnum estadoDaDisciplina) {
        this.estadoDaDisciplina = estadoDaDisciplina;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
}
