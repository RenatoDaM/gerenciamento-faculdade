package com.gerenciamentofaculdade.gerenciamentofaculdade.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity(name = "aluno")
@Table(name = "aluno")
public class AlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "RA não pode ser nulo")
    @Column(unique = true)
    private Long ra;

    @NotBlank(message = "RA não pode estar em branco")
    @NotNull(message = "Nome não pode ser nulo")
    @Column(length = 60, nullable = false)
    private String nome;

    @NotBlank(message = "O primeiro telefone não pode ser nulo")
    @NotNull(message = "O primeiro telefone não estar em branco")
    @Column(length = 17, nullable = false)
    private String telefone1;

    @Column(length = 17)
    private String telefone2;

    @OneToMany(mappedBy = "alunoModel")
    List<MatriculaModel> matriculasModel;

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

    public List<MatriculaModel> getMatriculasModel() {
        return matriculasModel;
    }

    public void setMatriculasModel(List<MatriculaModel> matriculasModel) {
        this.matriculasModel = matriculasModel;
    }
}
