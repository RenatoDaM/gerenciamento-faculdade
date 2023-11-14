package com.gerenciamentofaculdade.gerenciamentofaculdade.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
/***
    @author RenatoDaM
    Por enquanto é colocado um RA de forma sequencial, primeiro aluno vai ter RA 1, o segundo 2 e por ai vai.
    A ideia não é essa, o RA no futuro irá ser composto por ID da unidade educacional, e possivelmente outros IDs como curso.
 */


public class RaGenerator {
    private static final Logger log = LoggerFactory.getLogger(RaGenerator.class);
    private static final String FILE_PATH = "proximo_ra.txt";
    private static final AtomicInteger contadorRA = new AtomicInteger(1);

    static {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            log.error("NÃO FOI ENCONTRADO UM ARQUIVO CONTENDO O PRÓXIMO RA NECESSÁRIO, SERÁ CRIADO NA PASTA ROOT UM NOVO ARQUIVO COMEÇANDO COM RA 00000000000000000000001, COM O NOME proximo_ra.txt");
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha = reader.readLine();
            if (linha != null) {
                contadorRA.set(Integer.parseInt(linha));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                writer.write(String.format("%025d", contadorRA.get()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    public static String gerarRA() {
        int raAtual = contadorRA.getAndIncrement();
        return String.format("%025d", raAtual);
    }

}
