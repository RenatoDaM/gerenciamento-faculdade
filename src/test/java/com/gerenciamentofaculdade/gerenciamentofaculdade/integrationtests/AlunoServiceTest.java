package com.gerenciamentofaculdade.gerenciamentofaculdade.integrationtests;

import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.AlunoRepository;
import com.gerenciamentofaculdade.gerenciamentofaculdade.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {
    @InjectMocks
    private AlunoService itemServiceMock;

    @Mock
    private AlunoRepository itemRepositoryMock;

    List<Integer> idsToDeleteList = new ArrayList<>();

    @Test
    public void postAlunoValido() {

    }

    @Test
    public void postAlunoEmailEmBranco() {

    }

    @Test
    public void postAlunoEmailNulo() {

    }



}
