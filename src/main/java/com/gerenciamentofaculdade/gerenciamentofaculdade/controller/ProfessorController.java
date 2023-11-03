package com.gerenciamentofaculdade.gerenciamentofaculdade.controller;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.ProfessorDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.request.ProfessorLecionaRequest;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.ProfessorLecionaResponse;
import com.gerenciamentofaculdade.gerenciamentofaculdade.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/faculdade/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProfessorDTO> postProfessor(@Valid @RequestBody ProfessorDTO professorDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.postProfessor(professorDTO));
    }

    @PostMapping(value = "/leciona", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProfessorLecionaResponse> vincularDisciplina(@Valid @RequestBody ProfessorLecionaRequest professorLecionaRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.vincularDisciplinaAoProfessor(professorLecionaRequest));
    }
}
