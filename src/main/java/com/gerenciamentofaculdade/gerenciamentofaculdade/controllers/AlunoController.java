package com.gerenciamentofaculdade.gerenciamentofaculdade.controllers;

import com.gerenciamentofaculdade.gerenciamentofaculdade.controllers.openapi.AlunoControllerOpenApi;
import com.gerenciamentofaculdade.gerenciamentofaculdade.models.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.Response;
import com.gerenciamentofaculdade.gerenciamentofaculdade.search.AlunoParams;
import com.gerenciamentofaculdade.gerenciamentofaculdade.services.AlunoService;
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
@RequestMapping("/faculdade/aluno")
public class AlunoController implements AlunoControllerOpenApi {
    private final AlunoService service;

    AlunoController(AlunoService alunoService) {
        this.service = alunoService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AlunoDTO> postAluno(@Valid @RequestBody AlunoDTO alunoDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.postAluno(alunoDto));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AlunoDTO> getAluno(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAluno(id));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<AlunoDTO> getAllAlunos(@RequestParam(required = false, value = "ra") String ra,
                                         @RequestParam(required = false, value = "nome") String nome,
                                         @PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable) {
        var alunoParams = new AlunoParams(ra, nome);
        return service.getAllAlunos(alunoParams, pageable);
    }

    @PutMapping(value = "{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AlunoDTO> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoModel alunoModel) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAluno(id, alunoModel));
    }

    @DeleteMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> deleteAluno(@PathVariable Long id) {
        service.deleteAluno(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Aluno com ID: " + id + " deletado com sucesso."));
    }
}
