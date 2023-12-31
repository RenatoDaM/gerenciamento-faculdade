package com.gerenciamentofaculdade.gerenciamentofaculdade.controller;

import com.gerenciamentofaculdade.gerenciamentofaculdade.controller.openapi.AlunoControllerOpenApi;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.MatriculaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.generic.Response;
import com.gerenciamentofaculdade.gerenciamentofaculdade.search.param.AlunoParametroFiltro;
import com.gerenciamentofaculdade.gerenciamentofaculdade.service.AlunoService;
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
                                         @RequestParam(required = false, value = "email") String email,
                                         @PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable) {
        var alunoParams = new AlunoParametroFiltro(ra, nome, email);
        return service.getAllAlunos(alunoParams, pageable);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AlunoDTO> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoDTO alunoDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAluno(id, alunoDTO));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> deleteAluno(@PathVariable Long id) {
        service.deleteAluno(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Aluno com ID: " + id + " deletado com sucesso."));
    }

    @GetMapping(value = "/{id}/matriculas",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<MatriculaDTO> getAllMatriculasByAlunoId(@PathVariable Long id, @PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable) {
        return service.getAllMatriculasByAlunoId(id, pageable);
    }
}
