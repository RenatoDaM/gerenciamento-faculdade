package com.gerenciamentofaculdade.gerenciamentofaculdade.search;

public class AlunoParams {
    String ra;
    String nome;

    public AlunoParams(String ra, String nome) {
        this.ra = ra;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
