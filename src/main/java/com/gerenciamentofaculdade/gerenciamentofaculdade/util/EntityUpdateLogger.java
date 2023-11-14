package com.gerenciamentofaculdade.gerenciamentofaculdade.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityUpdateLogger {
    private static final Logger log = LoggerFactory.getLogger(EntityUpdateLogger.class);
    private static Object id;

    public static <T> void loggarModificacoes(T antes, T depois) throws IllegalAccessException {
        Map<String, String> mapDepois = criarMapParaComparacao(depois);
        Map<String, String> mapAntes = criarMapParaComparacao(antes);

        for (Map.Entry<String, String> entry : mapDepois.entrySet()) {
            if (!entry.getValue().equals(mapAntes.get(entry.getKey())) && !"<Nulo>".equals(entry.getValue())) {
                log.info("Objeto de classe {} e id {} foi atualizado. Campo {} foi alterado. Valor {} foi alterado para: {}", antes.getClass(), id, entry.getKey(), mapAntes.get(entry.getKey()), entry.getValue());
            }
        }
    }

    private static <T> Map<String, String> criarMapParaComparacao(T objeto) throws IllegalAccessException {
        Map<String, String> compare = new HashMap<>();

        // peguei campos e valores usando reflexão
        Field[] campos = objeto.getClass().getDeclaredFields();
        for (Field campo : campos) {
            campo.setAccessible(true);
            if (campo.getName().equals("id")) id = campo.get(objeto);
            try {
                Object valor = campo.get(objeto);
                String valorString = (valor == null) ? "<Nulo>" : valor.toString();
                compare.put(campo.getName(), valorString);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return compare;
    }
}
