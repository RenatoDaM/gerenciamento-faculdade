package com.gerenciamentofaculdade.gerenciamentofaculdade.controller;

import com.gerenciamentofaculdade.gerenciamentofaculdade.controller.openapi.CursoControllerOpenApi;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.CursoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.Response;
import com.gerenciamentofaculdade.gerenciamentofaculdade.service.CursoService;
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
@RequestMapping("/faculdade/curso")
public class CursoController implements CursoControllerOpenApi {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CursoDTO> postCurso(@Valid @RequestBody CursoDTO cursoDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.postCurso(cursoDTO));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CursoDTO> getCurso(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.getCurso(id));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<CursoDTO> getAllCursos(@PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable) {
        return cursoService.getAllCursos(pageable);
    }

    @PutMapping(value = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CursoDTO> updateCurso(@Valid @RequestBody CursoDTO cursoDTO, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.updateCurso(id, cursoDTO));
    }

    @DeleteMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Curso com ID: " + id + " deletado com sucesso."));
    }
}
