package com.gerenciamentofaculdade.gerenciamentofaculdade.controllers;

import com.gerenciamentofaculdade.gerenciamentofaculdade.models.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.search.AlunoParams;
import com.gerenciamentofaculdade.gerenciamentofaculdade.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/faculdade/aluno")
public class AlunoController {
    @Autowired
    AlunoService service;

    @PostMapping
    public ResponseEntity<AlunoModel> postAluno(@Valid @RequestBody AlunoModel alunoModel) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.postAluno(alunoModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoModel> getAluno(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAluno(id));
    }

    @GetMapping
    public Page<AlunoModel> getAllAlunos(@RequestParam(required = false, value = "ra") String ra,
                                         @RequestParam(required = false, value = "nome") String nome,
                                         @PageableDefault(size = 10) Pageable pageable) {
        var alunoParams = new AlunoParams(ra, nome);
        return service.getAllAlunos(alunoParams, pageable);
    }

    @PutMapping
    public ResponseEntity<AlunoModel> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoModel alunoModel) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAluno(id, alunoModel));
    }


}
