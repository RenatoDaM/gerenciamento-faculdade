package com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.ReadOnlyProperty;

public class CursoDTO {
    @ReadOnlyProperty
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String nome;

    @NotNull
    private Integer qtdSemestre;

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
}
