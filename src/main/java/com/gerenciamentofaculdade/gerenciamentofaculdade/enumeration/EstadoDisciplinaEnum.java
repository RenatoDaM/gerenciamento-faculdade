package com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration;

public enum EstadoDisciplinaEnum {
    EM_CURSO("Em curso"),
    REPROVADO_NOTA("Reprovado por nota"),
    REPROVADO_FREQUENCIA("Reprovado por frequência"),
    REPROVADO_NOTA_FREQUENCIA("Reprovado por nota e frequência"),
    APROVADO("Aprovado");

    private final String estado;

    EstadoDisciplinaEnum(String estado) {
        this.estado = estado;
    }

    public String getValue() {
        return estado;
    }
}
