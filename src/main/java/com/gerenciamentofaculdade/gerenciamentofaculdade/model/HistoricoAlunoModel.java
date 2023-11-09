package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.PeriodoEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "historico_aluno")
@Table(name = "historico_aluno")
public class HistoricoAlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float frequencia;
    private Float mediaFinal;
    private Integer faltas;
    private Integer presencas;
    private PeriodoEnum periodo;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    ProfessorModel professorModel;

    @ManyToOne
    @JoinColumn(name = "matricula_id", nullable = false)
    MatriculaModel matriculaModel;

    @ManyToMany
    @JoinTable(
            name = "historico_da_disciplina",
            joinColumns = @JoinColumn(name = "historico_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
    List<DisciplinaModel> disciplinaModelList;

    public HistoricoAlunoModel() {
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

    public List<DisciplinaModel> getDisciplinaModelList() {
        return disciplinaModelList;
    }

    public void setDisciplinaModelList(List<DisciplinaModel> disciplinaModelList) {
        this.disciplinaModelList = disciplinaModelList;
    }
}
