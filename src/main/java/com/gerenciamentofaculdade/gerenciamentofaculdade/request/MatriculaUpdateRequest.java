package com.gerenciamentofaculdade.gerenciamentofaculdade.request;

import jakarta.validation.constraints.*;

public class MatriculaUpdateRequest {
    @NotNull(message = "Estado da matrícula não pode ser nulo")
    @NotBlank(message = "Estado da matrícula não pode estar em branco")
    @NotEmpty(message = "Estado da matrícula não pode ter espaços em branco")
    @Pattern(regexp = "^(ATIVA|TRANCADA|CONCLUIDA)$", message = "Estado da matricula deve possuir um dos seguintes valores: ATIVA; TRANCADA; ou CONCLUIDA")
    private String estadoMatricula;

    // semestre atual
    @NotNull(message = "Semestre atual do aluno (ciclo) não pode ser nulo")
    @Positive(message = "Semestre atual do aluno (ciclo) tem que ser maior que zero")
    private Integer ciclo;

    public String getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(String estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }
}
