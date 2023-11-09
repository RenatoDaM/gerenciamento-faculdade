package com.gerenciamentofaculdade.gerenciamentofaculdade.controller;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.ProfessorDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.request.ProfessorLecionaRequest;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.ProfessorLecionaResponse;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.generic.Response;
import com.gerenciamentofaculdade.gerenciamentofaculdade.service.ProfessorService;
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

import java.util.List;

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

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProfessorDTO> getProfessor(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getProfessor(id));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<ProfessorDTO> getAllProfessores(@RequestParam(required = false, value = "ra") String nome,
                                       @RequestParam(required = false, value = "nome") String registroProfissional,
                                       @PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable) {
        return professorService.getAllProfessores(pageable);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable Long id, @Valid @RequestBody ProfessorDTO professorDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.putProfessor(id, professorDTO));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Professor com ID: " + id + " deletado com sucesso."));
    }


    // vinculo professor-disciplina
    @PostMapping(value = "/leciona", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProfessorLecionaResponse> vincularDisciplina(@Valid @RequestBody ProfessorLecionaRequest professorLecionaRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.vincularDisciplinaAoProfessor(professorLecionaRequest));
    }

    @GetMapping(value = "/leciona/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProfessorLecionaResponse>> getVinculoDisciplina(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getDisciplinasVinculadasPorProfessorId(id));
    }

    @GetMapping(value = "/leciona", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<ProfessorLecionaResponse> getAllVinculoDisciplina(@PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable) {
        return professorService.getAllProfessorLecionaDisciplina(pageable);
    }

    // Acredito que não precise de update até o momento.
/*
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable Long id, @Valid @RequestBody ProfessorDTO professorDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.putProfessor(id, professorDTO));
    }
*/

    @DeleteMapping(value = "/leciona/{professorId}/{disciplinaId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> deleteVinculoDisciplina(@PathVariable Long professorId, @PathVariable Long disciplinaId) {
        professorService.deleteProfessorLeciona(professorId, disciplinaId);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Relação deletada com sucesso."));
    }
}
