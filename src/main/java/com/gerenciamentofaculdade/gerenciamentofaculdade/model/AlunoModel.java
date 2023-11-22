package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "alunoModel", cascade = CascadeType.REMOVE)
    private List<MatriculaModel> matriculasModel;

    public AlunoModel(Long id, String ra, String nome, String email, String telefone1, String telefone2, List<MatriculaModel> matriculasModel) {
        this.id = id;
        this.ra = ra;
        this.nome = nome;
        this.email = email;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.matriculasModel = matriculasModel;
    }

    public AlunoModel(){

    }

    public AlunoModel(Long id, String ra, String nome, String email, String telefone1, String telefone2) {
        this.id = id;
        this.ra = ra;
        this.nome = nome;
        this.email = email;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoModel that = (AlunoModel) o;
        return Objects.equals(id, that.id) && Objects.equals(ra, that.ra) && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(telefone1, that.telefone1) && Objects.equals(telefone2, that.telefone2) && Objects.equals(matriculasModel, that.matriculasModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ra, nome, email, telefone1, telefone2, matriculasModel);
    }
}
