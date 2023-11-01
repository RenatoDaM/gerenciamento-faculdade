package com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class DisciplinaDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "Nome da disciplina não pode ser nulo")
    @NotEmpty(message = "Nome da disciplina não estar vazio")
    @NotBlank(message = "Nome da disciplina não pode estar em branco")
    private String nome;

    @NotNull(message = "Carga horária da disciplina não pode ser nula")
    Integer cargaHoraria;

    @JsonProperty("curso")
    @NotNull
    CursoDTO cursoDTO;

    public DisciplinaDTO(String nome, Integer cargaHoraria, CursoDTO cursoDTO) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.cursoDTO = cursoDTO;
    }

    public DisciplinaDTO() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisciplinaDTO that = (DisciplinaDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(cargaHoraria, that.cargaHoraria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cargaHoraria);
    }

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

    public CursoDTO getCursoDTO() {
        return cursoDTO;
    }

    public void setCursoDTO(CursoDTO cursoDTO) {
        this.cursoDTO = cursoDTO;
    }
}
