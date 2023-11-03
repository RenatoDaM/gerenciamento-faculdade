package com.gerenciamentofaculdade.gerenciamentofaculdade.controller;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.generic.Response;
import com.gerenciamentofaculdade.gerenciamentofaculdade.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/faculdade/disciplina")
public class DisciplinaController {
    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DisciplinaDTO> postDisciplina(@RequestBody @Valid DisciplinaDTO disciplinaDTO) {
        return ResponseEntity.ok().body(disciplinaService.postDisciplina(disciplinaDTO));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DisciplinaDTO> getDisciplina(@PathVariable Long id) {
        return ResponseEntity.ok().body(disciplinaService.getDisciplina(id));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<DisciplinaDTO> getAllDisciplinas(@PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable) {
        return disciplinaService.getAllDisciplinas(pageable);
    }

    @PutMapping(value = "{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DisciplinaDTO> putDisciplina(@PathVariable Long id, @RequestBody @Valid DisciplinaDTO disciplinaDTO) throws IllegalAccessException {
        return ResponseEntity.ok().body(disciplinaService.putDisciplina(disciplinaDTO, id));
    }

    @DeleteMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> deleteDisciplina(@PathVariable Long id) {
        disciplinaService.deleteDisciplina(id);
        return ResponseEntity.ok().body(new Response(200, "Disciplina com ID: " + id + " deletada com sucesso"));
    }
}
