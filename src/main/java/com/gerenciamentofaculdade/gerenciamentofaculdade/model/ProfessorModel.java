package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "professor")
@Table(name = "professor")
public class ProfessorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(unique = true, nullable = false, length = 30)
    private String registroConselho;

    public Long getId() {
        return id;
    }

    public ProfessorModel() {
    }

    public ProfessorModel(Long id, String nome, String registroConselho) {
        this.id = id;
        this.nome = nome;
        this.registroConselho = registroConselho;
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
}
