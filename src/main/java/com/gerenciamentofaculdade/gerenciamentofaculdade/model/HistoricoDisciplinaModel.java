package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.EstadoDisciplinaEnum;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.PeriodoEnum;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;
import java.util.Date;

@Entity(name = "historico_da_disciplina")
@Table(name = "historico_da_disciplina")
public class HistoricoDisciplinaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float frequencia;
    private Float mediaFinal;
    private Integer faltas;
    private Integer presencas;
    private PeriodoEnum periodo;
    @Temporal(TemporalType.DATE)
    private YearMonth data;
    private EstadoDisciplinaEnum estadoDaDisciplina;

    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false)
    ProfessorModel professorModel;

    @ManyToOne
    @JoinColumn(name = "matricula_id", nullable = false)
    MatriculaModel matriculaModel;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    DisciplinaModel disciplinaModel;

    public HistoricoDisciplinaModel() {
    }

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

    public ProfessorModel getProfessorModel() {
        return professorModel;
    }

    public void setProfessorModel(ProfessorModel professorModel) {
        this.professorModel = professorModel;
    }

    public MatriculaModel getMatriculaModel() {
        return matriculaModel;
    }

    public void setMatriculaModel(MatriculaModel matriculaModel) {
        this.matriculaModel = matriculaModel;
    }

    public DisciplinaModel getDisciplinaModel() {
        return disciplinaModel;
    }

    public void setDisciplinaModel(DisciplinaModel disciplinaModel) {
        this.disciplinaModel = disciplinaModel;
    }

    public EstadoDisciplinaEnum getEstadoDaDisciplina() {
        return estadoDaDisciplina;
    }

    public void setEstadoDaDisciplina(EstadoDisciplinaEnum estadoDaDisciplina) {
        this.estadoDaDisciplina = estadoDaDisciplina;
    }

    public YearMonth getData() {
        return data;
    }

    public void setData(YearMonth data) {
        this.data = data;
    }
}
