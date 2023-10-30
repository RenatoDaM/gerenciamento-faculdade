package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "curso")
@Table(name = "curso")
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    String nome;
    @Column(nullable = false)
    Integer qtdSemestre;

    @OneToMany(mappedBy = "cursoModel")
    List<MatriculaModel> matriculaList;

    @OneToMany(mappedBy = "cursoModel")
    List<DisciplinaModel> disciplinaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdSemestre() {
        return qtdSemestre;
    }

    public void setQtdSemestre(Integer qtdSemestre) {
        this.qtdSemestre = qtdSemestre;
    }

    public List<MatriculaModel> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<MatriculaModel> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public List<DisciplinaModel> getDisciplinaList() {
        return disciplinaList;
    }

    public void setDisciplinaList(List<DisciplinaModel> disciplinaList) {
        this.disciplinaList = disciplinaList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoModel that = (CursoModel) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(qtdSemestre, that.qtdSemestre) && Objects.equals(matriculaList, that.matriculaList) && Objects.equals(disciplinaList, that.disciplinaList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, qtdSemestre, matriculaList, disciplinaList);
    }
}
