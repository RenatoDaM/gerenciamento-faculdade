package com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration;

public enum PeriodoEnum {
    MANHA("Manhã"),
    TARDE("Tarde"),
    NOITE("Noite"),
    INTEGRAL("Integral");

    private String periodo;

    PeriodoEnum(String periodo) {
        this.periodo = periodo;
    }

    public String getNomeDoDia() {
        return periodo;
    }
}
