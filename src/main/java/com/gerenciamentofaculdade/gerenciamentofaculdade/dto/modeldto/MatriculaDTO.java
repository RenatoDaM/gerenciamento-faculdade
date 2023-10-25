package com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto;

import com.gerenciamentofaculdade.gerenciamentofaculdade.models.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.models.CursoModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public class MatriculaDTO {
    private Long id;

    @NotNull(message = "Estado da matrícula não pode ser nulo")
    @NotBlank(message = "Estado da matrícula não pode estar em branco")
    @NotEmpty(message = "Estado da matrícula não pode ter espaços em branco")
    private String estadoMatricula;

    // semestre atual
    @NotNull
    private Integer ciclo;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataMatricula;

    private AlunoDTO alunoDTO;

    private CursoDTO cursoDTO;

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

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDateTime dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public AlunoDTO getAlunoDTO() {
        return alunoDTO;
    }

    public void setAlunoDTO(AlunoDTO alunoDTO) {
        this.alunoDTO = alunoDTO;
    }

    public CursoDTO getCursoDTO() {
        return cursoDTO;
    }

    public void setCursoDTO(CursoDTO cursoDTO) {
        this.cursoDTO = cursoDTO;
    }
}
