package com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CursoDTO {
    @Schema(accessMode = Schema.AccessMode.AUTO)
    @Positive(message = "Valor de ID deve ser numérico e positivo")
    private Long id;

    @NotNull(message = "Nome do curso não pode ser nulo")
    @NotEmpty(message = "Nome do curso não estar vazio")
    @NotBlank(message = "Nome do curso não pode estar em branco")
    private String nome;

    @NotNull(message = "Quantidade de semestres do curso não pode ser nulo")
    @Positive(message = "Quantidade de semestres do curso precisa ser maior que zero")
    private Integer qtdSemestre;

    public CursoDTO() {
    }

    public CursoDTO(Long id, String nome, Integer qtdSemestre) {
        this.id = id;
        this.nome = nome;
        this.qtdSemestre = qtdSemestre;
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

    public Integer getQtdSemestre() {
        return qtdSemestre;
    }

    public void setQtdSemestre(Integer qtdSemestre) {
        this.qtdSemestre = qtdSemestre;
    }
}
