package com.gerenciamentofaculdade.gerenciamentofaculdade.request;

import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.EstadoDisciplinaEnum;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.PeriodoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;

public class HistoricoDisciplinaPutRequest {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private Float frequencia;
    private Float mediaFinal;
    private Integer faltas;
    private Integer presencas;
    private PeriodoEnum periodo;
    private EstadoDisciplinaEnum estadoDaDisciplina;

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

    public EstadoDisciplinaEnum getEstadoDaDisciplina() {
        return estadoDaDisciplina;
    }

    public void setEstadoDaDisciplina(EstadoDisciplinaEnum estadoDaDisciplina) {
        this.estadoDaDisciplina = estadoDaDisciplina;
    }
}
