package com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration;

public enum DiaDaSemanaEnum {
    SEGUNDA_FEIRA("Segunda-feira"),
    TERCA_FEIRA("Terça-feira"),
    QUARTA_FEIRA("Quarta-feira"),
    QUINTA_FEIRA("Quinta-feira"),
    SEXTA_FEIRA("Sexta-feira"),
    SABADO("Sábado"),
    DOMINGO("Domingo");

    private String nomeDoDia;

    DiaDaSemanaEnum(String nomeDoDia) {
        this.nomeDoDia = nomeDoDia;
    }

    public String getNomeDoDia() {
        return nomeDoDia;
    }
}
