package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "disciplina")
@Table(name = "disciplina")
public class DisciplinaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    String nome;

    @Column(nullable = false)
    Integer cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoModel cursoModel;

    @OneToMany(mappedBy = "disciplinaModel")
    private List<HistoricoDisciplinaModel> historicoDisciplinaModelList;

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

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public CursoModel getCursoModel() {
        return cursoModel;
    }

    public void setCursoModel(CursoModel cursoModel) {
        this.cursoModel = cursoModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisciplinaModel that = (DisciplinaModel) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(cargaHoraria, that.cargaHoraria) && Objects.equals(cursoModel, that.cursoModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cargaHoraria, cursoModel);
    }

    public List<HistoricoDisciplinaModel> getHistoricoDisciplinaList() {
        return historicoDisciplinaModelList;
    }

    public void setHistoricoDisciplinaList(List<HistoricoDisciplinaModel> historicoDisciplinaModelList) {
        this.historicoDisciplinaModelList = historicoDisciplinaModelList;
    }
}
