package com.gerenciamentofaculdade.gerenciamentofaculdade.models;

import jakarta.persistence.*;

import java.util.List;

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
}
