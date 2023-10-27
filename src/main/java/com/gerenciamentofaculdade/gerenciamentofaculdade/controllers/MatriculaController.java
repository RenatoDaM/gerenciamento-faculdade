package com.gerenciamentofaculdade.gerenciamentofaculdade.controllers;

import com.gerenciamentofaculdade.gerenciamentofaculdade.controllers.openapi.MatriculaControllerOpenApi;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.MatriculaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.Response;
import com.gerenciamentofaculdade.gerenciamentofaculdade.services.MatriculaService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/faculdade/matricula")
public class MatriculaController implements MatriculaControllerOpenApi {
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MatriculaDTO> matricularAluno(@Valid @RequestBody MatriculaDTO matriculaDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.matricularAluno(matriculaDTO));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MatriculaDTO> getCurso(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.getMatricula(id));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<MatriculaDTO> getAllCursos(@PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable) {
        return matriculaService.getAllMatriculas(pageable);
    }

    @PutMapping(value = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MatriculaDTO> updateCurso(@Valid @RequestBody MatriculaDTO matriculaDTO, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.updateMatricula(matriculaDTO, id));
    }

    @DeleteMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> deleteCurso(@PathVariable Long id) {
        matriculaService.deleteMatricula(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Matrícula com ID: " + id + " deletado com sucesso."));
    }
}
