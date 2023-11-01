package com.gerenciamentofaculdade.gerenciamentofaculdade.integrationtests;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.CursoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.DisciplinaRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.service.DisciplinaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
public class DisciplinaServiceTest {
    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private Validator validator;

    @Autowired
    private DisciplinaRepository alunoRepository;

    private static List<DisciplinaDTO> disciplinas = new ArrayList<>();
    private static List<Long> idsToDelete = new ArrayList<>();

    @Test
    @Order(1)
    public void setup() {
        disciplinas.add(new DisciplinaDTO("Estatística", 60, new CursoDTO(1L, "ADS", 6)));
        disciplinas.add(new DisciplinaDTO("IHC", 30, new CursoDTO(1L, "ADS", 6)));
        disciplinas.add(new DisciplinaDTO("ESII", 90, new CursoDTO(1L, "ADS", 6)));
    }

    @Test
    @Order(2)
    public void postTest() {
        DisciplinaDTO disciplinaDTO = disciplinas.get(0);
        DisciplinaDTO result = disciplinaService.postDisciplina(disciplinas.get(0));
        disciplinaDTO.setId(result.getId());
        idsToDelete.add(result.getId());
        assertEquals(disciplinaDTO, result);
    }

    @Test
    @Order(3)
    public void getTest() {
        var disciplinaJaPersistida = disciplinas.get(0);
        disciplinaJaPersistida.setId(idsToDelete.get(0));
        var resultadoDoGet = disciplinaService.getDisciplina(idsToDelete.get(0));

        assertEquals(resultadoDoGet, disciplinaJaPersistida);
    }

    @Test
    @Order(4)
    public void putTest() {
        DisciplinaDTO disciplinaDTO = new DisciplinaDTO("Teste", 11, new CursoDTO(1L, "ADS", 6));
        disciplinaService.putDisciplina(disciplinaDTO, idsToDelete.get(0));
        disciplinaDTO.setId(idsToDelete.get(0));
        assertEquals(disciplinaDTO, disciplinaService.getDisciplina(idsToDelete.get(0)));
    }

    @Test
    @Order(5)
    public void deleteTest() {
        idsToDelete.forEach(id -> disciplinaService.deleteDisciplina(id));
        idsToDelete.forEach(id -> assertThrows(EntityNotFoundException.class, () -> disciplinaService.getDisciplina(id)));
    }
}
