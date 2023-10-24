package com.gerenciamentofaculdade.gerenciamentofaculdade.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity(name = "aluno")
@Table(name = "aluno")
public class AlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 25)
    private String ra;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 320, nullable = false, unique = true)
    private String email;

    @Column(length = 17, nullable = false)
    private String telefone1;

    @Column(length = 17)
    private String telefone2;

    @OneToMany(mappedBy = "alunoModel")
    private List<MatriculaModel> matriculasModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
