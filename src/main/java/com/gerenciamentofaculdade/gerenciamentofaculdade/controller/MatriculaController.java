package com.gerenciamentofaculdade.gerenciamentofaculdade.controller;

import com.gerenciamentofaculdade.gerenciamentofaculdade.controller.openapi.MatriculaControllerOpenApi;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.HistoricoDisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.MatriculaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.request.HistoricoDisciplinaPutRequest;
import com.gerenciamentofaculdade.gerenciamentofaculdade.request.MatriculaUpdateRequest;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.generic.Response;
import com.gerenciamentofaculdade.gerenciamentofaculdade.service.MatriculaService;
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

    @PutMapping(value = "/{matriculaId}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MatriculaDTO> updateCurso(@Valid @RequestBody MatriculaUpdateRequest request, @PathVariable Long matriculaId) throws IllegalAccessException {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.updateMatricula(request, matriculaId));
    }

    @DeleteMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> deleteCurso(@PathVariable Long id) {
        matriculaService.deleteMatricula(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Matrícula com ID: " + id + " deletado com sucesso."));
    }

    @PostMapping(value = "/historico", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HistoricoDisciplinaDTO> postHistoricoDisciplina(@RequestBody @Valid HistoricoDisciplinaDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.adicionarDisciplinaAoHistorico(request));
    }

    @GetMapping(value = "/{id}/historico", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<HistoricoDisciplinaDTO> getAllHistoricosByMatriculaId(@PathVariable Long id,
                                                     @PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable) {
        return matriculaService.getAllHistoricosByMatriculaId(id, pageable);
    }

    @PutMapping(value = "/historico/{historicoDisciplinaId}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HistoricoDisciplinaDTO> updateHistoricoDisciplina(@Valid @RequestBody HistoricoDisciplinaPutRequest historicoDisciplinaPutRequest, @PathVariable Long historicoDisciplinaId) throws IllegalAccessException {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.updateHistoricoDisciplina(historicoDisciplinaId, historicoDisciplinaPutRequest));
    }

    @DeleteMapping(value = "/historico/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> deleteHistoricoDisciplina(@PathVariable Long id) {
        matriculaService.deleteHistoricoDisciplina(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Matrícula com ID: " + id + " deletado com sucesso."));
    }
}
