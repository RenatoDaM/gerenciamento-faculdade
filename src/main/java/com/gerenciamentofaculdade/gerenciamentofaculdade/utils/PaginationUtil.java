package com.gerenciamentofaculdade.gerenciamentofaculdade.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PaginationUtil {
    public static <T> Page<T> paginarLista(List<T> lista, Pageable pageable) {
        int inicio, fim;
        inicio = (int) pageable.getOffset();
        fim = (inicio + pageable.getPageSize()) > lista.size() ? lista.size() : (inicio + pageable.getPageSize());
        List<T> elementosPaginados = lista.subList(inicio, fim);
        return new PageImpl<>(elementosPaginados, pageable, lista.size());
    }
}
