package com.gerenciamentofaculdade.gerenciamentofaculdade.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AlunoDTO {
    private Long id;

    @NotNull(message = "RA não pode estar em branco")
    @Positive
    private Long ra;

    @NotBlank(message = "Nome não pode estar em branco")
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "O primeiro telefone não pode estar em branco")
    @NotNull(message = "O primeiro telefone não pode ser nulo")
    private String telefone1;

    private String telefone2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }
}

