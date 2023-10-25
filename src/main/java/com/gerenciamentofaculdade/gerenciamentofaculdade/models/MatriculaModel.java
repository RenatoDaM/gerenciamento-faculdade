package com.gerenciamentofaculdade.gerenciamentofaculdade.models;

import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.EstadoMatriculaEnum;
import jakarta.persistence.*;
import java.time.LocalDateTime;

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
}
