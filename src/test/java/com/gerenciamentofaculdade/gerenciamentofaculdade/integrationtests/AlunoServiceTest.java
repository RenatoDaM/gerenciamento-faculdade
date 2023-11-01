package com.gerenciamentofaculdade.gerenciamentofaculdade.integrationtests;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.AlunoRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.service.AlunoService;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(profiles = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private Validator validator;

    @Autowired
    private AlunoRepository alunoRepository;

/*
    @Spy
    private AlunoMapper alunoMapper = new AlunoMapperImpl();
*/

    private static List<AlunoDTO> alunosList = new ArrayList<>();
    private static List<AlunoDTO> alunosComIdList = new ArrayList<>();

    @Test
    @Order(1)
    public void setup() {
        // setar varios alunos e ir nos testes lidando, dps no teardown limpar...
        alunosList.add(new AlunoDTO("0000007890123456789012345", "Aluno Um", "aluno1@gmail.com", "21994558174", null));
        alunosList.add(new AlunoDTO("1111117890123456789012345", "Aluno Dois", "aluno2@gmail.com", "21994558175", null));
        alunosList.add(new AlunoDTO("2222227890123456789012345", "Aluno Tres", "aluno3@gmail.com", "21994558176", null));
        alunosList.add(new AlunoDTO("3333337890123456789012345", "Aluno Quatro", "aluno4@gmail.com", "21994558177", null));
        alunosList.add(new AlunoDTO("4444447890123456789012345", "Aluno Cinco", "aluno5@gmail.com", "21994558178", null));
        alunosList.add(new AlunoDTO("5555557890123456789012345", "Aluno Seis", "aluno6@gmail.com", "21994558179", null));

        alunosList.forEach(aluno -> {
            try {
                alunosComIdList.add(alunoService.postAluno(aluno));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    @Order(2)
    public void postAlunoValido() throws Exception {
        AlunoDTO alunoToTest = new AlunoDTO("1234567890123456789012300", "Testador", "asokorkfwre@gmail.com", "19378334829", null);
        AlunoDTO resultado = alunoService.postAluno(new AlunoDTO("1234567890123456789012300", "Testador", "asokorkfwre@gmail.com", "19378334829", null));
        alunoToTest.setId(resultado.getId());
        assertEquals(resultado, alunoToTest);
        alunosComIdList.add(resultado);
    }

    @Test
    @Order(3)
    public void postEmailJaExistente() throws Exception {
        AlunoDTO alunoToTest = alunosList.get(0);
        alunoToTest.setRa("0000001760123456789012345");
        assertThrows(IllegalArgumentException.class, () -> alunoService.postAluno(alunoToTest));
    }

    @Test
    @Order(4)
    public void postRaJaExistente() {
        AlunoDTO alunoToTest = alunosList.get(1);
        alunoToTest.setEmail("outroemail@gmail.com");
        assertThrows(IllegalArgumentException.class, () -> alunoService.postAluno(alunoToTest));
    }

    @Test
    @Order(5)
    public void tearDown() {
        alunosComIdList.forEach(aluno -> alunoService.deleteAluno(aluno.getId()));
    }
}
