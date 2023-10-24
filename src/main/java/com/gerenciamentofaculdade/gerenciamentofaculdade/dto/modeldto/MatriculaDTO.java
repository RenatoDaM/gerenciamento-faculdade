package com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MatriculaDTO {
    private Long id;

    @NotNull(message = "Estado da matrícula não pode ser nulo")
    @NotBlank(message = "Estado da matrícula não pode estar em branco")
    @NotEmpty(message = "Estado da matrícula não pode ter espaços em branco")
    private String estadoMatricula;

    // semestre atual
    @NotNull
    private Integer ciclo;

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
        this.estadoMatricula = estadoMatricula;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }
}
