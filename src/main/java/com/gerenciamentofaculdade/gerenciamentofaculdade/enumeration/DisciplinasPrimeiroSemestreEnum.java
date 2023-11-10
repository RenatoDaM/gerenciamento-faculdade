package com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration;

import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.interfaces.IDisciplinasPrimeiroSemestreEnum;

public enum DisciplinasPrimeiroSemestreEnum implements IDisciplinasPrimeiroSemestreEnum {
    ADS("ADS") {
        public void aplicarHistoricoPrimeiroSemestre() {
            System.out.println("Executando ação da Opção 1");
            // criar histórico matematica discreta
            // criar histórico laboratório de hardware
            // etc...
        }
    };
    private final String nomeCurso;

    DisciplinasPrimeiroSemestreEnum(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public abstract void aplicarHistoricoPrimeiroSemestre();

    public String getNomeCurso() {
        return nomeCurso;
    }

    public static DisciplinasPrimeiroSemestreEnum obterEnumPorString(String str) {
        for (DisciplinasPrimeiroSemestreEnum valor : values()) {
            if (valor.nomeCurso.equals(str)) {
                return valor;
            }
        }
        throw new IllegalArgumentException("Opção inválida: " + str);
    }
}
