package com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class MatriculaDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Positive(message = "Valor de ID deve ser numérico e positivo")
    private Long id;

    @NotNull(message = "Estado da matrícula não pode ser nulo")
    @NotBlank(message = "Estado da matrícula não pode estar em branco")
    @NotEmpty(message = "Estado da matrícula não pode ter espaços em branco")
    @Pattern(regexp = "^(ATIVA|TRANCADA|CONCLUIDA)$", message = "Estado da matricula deve possuir um dos seguintes valores: ATIVA; TRANCADA; ou CONCLUIDA")
    private String estadoMatricula;

    // semestre atual
    @NotNull(message = "Semestre atual do aluno (ciclo) não pode ser nulo")
    @Positive(message = "Semestre atual do aluno (ciclo) tem que ser maior que zero")
    private Integer ciclo;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataMatricula;

    @NotNull
    private Long alunoId;

    @NotNull
    private Long cursoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(String estadoMatricula) {
        this.estadoMatricula = estadoMatricula.toUpperCase();
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

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}
