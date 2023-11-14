/*
package com.gerenciamentofaculdade.gerenciamentofaculdade.integrationtests;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.*;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.EstadoDisciplinaEnum;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.PeriodoEnum;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.HistoricoDisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.service.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.YearMonth;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(profiles = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistoricoDisciplinaTest {
    @Autowired
    AlunoService alunoService;
    @Autowired
    ProfessorService professorService;
    @Autowired
    MatriculaService matriculaService;
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    CursoService cursoService;

    @Test
    @Order(1)
    public void postAluno() {
        alunoService.postAluno(new AlunoDTO("0000007890123456789012345", "Aluno Um", "aluno1@gmail.com", "21994558174", null));
    }

    @Test
    @Order(2)
    public void postProfessor() {
        professorService.postProfessor(new ProfessorDTO(null, "Professor Um", "RASDLGK123"));
    }

    @Test
    @Order(3)
    public void postCurso() {
        cursoService.postCurso(new CursoDTO(null, "ADS", 6));
    }

    @Test
    @Order(4)
    public void postDisciplina() {
        disciplinaService.postDisciplina(new DisciplinaDTO("ESI", 6, new CursoDTO(1L, "ADS", 6)));
    }

    @Test
    @Order(5)
    public void postMatricula() {
        var matricula = new MatriculaDTO();
        matricula.setDataMatricula(LocalDateTime.now());
        matricula.setEstadoMatricula("ATIVA");
        matricula.setCursoDTO(new CursoDTO(1L, "ADS", 6));
        matricula.setCiclo(4);
        var aluno = new AlunoDTO();
        aluno.setId(1L);
        matricula.setAlunoDTO(aluno);
        matriculaService.matricularAluno(matricula);
    }

    @Test
    @Order(6)
    public void postarHistorico() {
        var historicoDisciplina = new HistoricoDisciplinaDTO();
        historicoDisciplina.setEstadoDaDisciplina(EstadoDisciplinaEnum.APROVADO);
        historicoDisciplina.setDisciplinaId(1L);
        historicoDisciplina.setMatriculaId(1L);
        historicoDisciplina.setProfessorId(1L);
        historicoDisciplina.setPresencas(0);
        historicoDisciplina.setFaltas(0);
        historicoDisciplina.setData(YearMonth.now());
        historicoDisciplina.setMediaFinal(0F);
        historicoDisciplina.setFrequencia(0F);
        historicoDisciplina.setPeriodo(PeriodoEnum.MANHA);
        alunoService.adicionarDisciplinaAoHistorico(historicoDisciplina);
    }
}
*/
