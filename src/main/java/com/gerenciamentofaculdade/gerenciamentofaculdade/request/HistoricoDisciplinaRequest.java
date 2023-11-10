package com.gerenciamentofaculdade.gerenciamentofaculdade.request;

import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.EstadoDisciplinaEnum;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.PeriodoEnum;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;
import java.util.Date;

public class HistoricoDisciplinaRequest {
    private Long id;
    private Float frequencia;
    private Float mediaFinal;
    private Integer faltas;
    private Integer presencas;
    private PeriodoEnum periodo;
    @Temporal(TemporalType.DATE)
    private YearMonth data;
    private EstadoDisciplinaEnum estadoDaDisciplina;
    private Long professorId;
    private Long matriculaId;
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
