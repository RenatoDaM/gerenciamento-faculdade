package com.gerenciamentofaculdade.gerenciamentofaculdade.response;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.Objects;

public class ProfessorLecionaResponse {
    @Positive(message = "Valor de ID deve ser numérico e positivo")
    private Long id;

    @NotBlank(message = "Nome não pode estar em branco")
    @NotNull(message = "Nome não pode ser nulo")
    @NotEmpty(message = "Nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "Registro de conselho não pode estar em branco")
    @NotNull(message = "Registro de conselho não pode ser nulo")
    @NotEmpty(message = "Registro de conselho não pode estar vazio")
    private String registroConselho;

    List<DisciplinaDTO> disciplinasList;

    public ProfessorLecionaResponse() {
    }

    public ProfessorLecionaResponse(Long id, String nome, String registroConselho) {
        this.id = id;
        this.nome = nome;
        this.registroConselho = registroConselho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorLecionaResponse that = (ProfessorLecionaResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(registroConselho, that.registroConselho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, registroConselho);
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

    public String getRegistroConselho() {
        return registroConselho;
    }

    public void setRegistroConselho(String registroConselho) {
        this.registroConselho = registroConselho;
    }

    public List<DisciplinaDTO> getDisciplinasList() {
        return disciplinasList;
    }

    public void setDisciplinasList(List<DisciplinaDTO> disciplinasList) {
        this.disciplinasList = disciplinasList;
    }
}
