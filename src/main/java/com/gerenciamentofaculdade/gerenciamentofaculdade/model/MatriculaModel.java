package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.EstadoMatriculaEnum;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity(name = "matricula")
@Table(name = "matricula")
public class MatriculaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoMatriculaEnum estadoMatricula;

    // semestre atual
    @Column(columnDefinition = "integer", nullable = false)
    private Integer ciclo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, columnDefinition = "date")
    private LocalDateTime dataMatricula;

    @ManyToOne
    @JoinColumn(name="aluno_id", nullable = false)
    private AlunoModel alunoModel;

    @ManyToOne
    @JoinColumn(name="curso_id", nullable = false)
    private CursoModel cursoModel;

    @OneToMany(mappedBy = "matriculaModel")
    private List<HistoricoAlunoModel> historicoAlunoModelList;

    @Override
    public String toString() {
        return "MatriculaModel{" +
                "id=" + id +
                ", estadoMatricula=" + estadoMatricula +
                ", ciclo=" + ciclo +
                ", dataMatricula=" + dataMatricula +
                ", alunoModel=" + alunoModel +
                ", cursoModel=" + cursoModel +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoMatriculaEnum getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(String estadoMatricula) {
        this.estadoMatricula = EstadoMatriculaEnum.valueOf(estadoMatricula);
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDateTime dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public AlunoModel getAlunoModel() {
        return alunoModel;
    }

    public void setAlunoModel(AlunoModel alunoModel) {
        this.alunoModel = alunoModel;
    }

    public CursoModel getCursoModel() {
        return cursoModel;
    }

    public void setCursoModel(CursoModel cursoModel) {
        this.cursoModel = cursoModel;
    }

    public void setEstadoMatricula(EstadoMatriculaEnum estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public List<HistoricoAlunoModel> getHistoricoAlunoModelList() {
        return historicoAlunoModelList;
    }

    public void setHistoricoAlunoModelList(List<HistoricoAlunoModel> historicoAlunoModelList) {
        this.historicoAlunoModelList = historicoAlunoModelList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatriculaModel that = (MatriculaModel) o;
        return Objects.equals(id, that.id) && estadoMatricula == that.estadoMatricula && Objects.equals(ciclo, that.ciclo) && Objects.equals(dataMatricula, that.dataMatricula) && Objects.equals(alunoModel, that.alunoModel) && Objects.equals(cursoModel, that.cursoModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estadoMatricula, ciclo, dataMatricula, alunoModel, cursoModel);
    }
}
