package com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration;

public enum EstadoMatriculaEnum {
    ATIVA("ATIVA"), TRANCADA("TRANCADA"), CONCLUIDA("CONCLUIDA");

    private String value;

    EstadoMatriculaEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
