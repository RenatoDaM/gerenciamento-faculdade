package com.gerenciamentofaculdade.gerenciamentofaculdade.controller.openapi;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.MatriculaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.ErrorResponse;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface MatriculaControllerOpenApi {
    @Operation(summary = "Persiste um curso no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi possível persistir a matrícula", responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi possível persistir", externalValue = "static/api-response-examples/aluno/post/erro-ao-persistir.json"))),
            @ApiResponse(description = "Matrícula persistida com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Matrícula criada com sucesso", externalValue = "static/api-response-examples/aluno/post/persistido-com-sucesso.json"), schema = @Schema(implementation = MatriculaDTO.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Tipo de mídia não suportado", responseCode = "415", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Tipo de mídia não suportado ainda", externalValue = "static/api-response-examples/geral/tipo-midia-nao-suportado.json")))
    })
    public ResponseEntity<MatriculaDTO> matricularAluno(@Valid @RequestBody MatriculaDTO matriculaDTO) throws Exception;
    @Operation(summary = "Busca um curso pelo ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi encontrado uma Matrícula com este ID", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi encontrado uma matrícula com este ID", externalValue = "static/api-response-examples/aluno/get/aluno-por-id-nao-encontrado.json"))),
            @ApiResponse(description = "Matrícula encontrada", responseCode = "200", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Retorna aluno encontrado", externalValue = "static/api-response-examples/aluno/get/aluno-encontrado.json"), schema = @Schema(implementation = MatriculaDTO.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<MatriculaDTO> getCurso(@PathVariable Long id);
    public Page<MatriculaDTO> getAllCursos(@PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable);
    @Operation(summary = "Atualiza uma Matrícula já existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi possível atualizar matrícula", responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi possível persistir", externalValue = "static/api-response-examples/aluno/put/erro-ao-atualizar.json"))),
            @ApiResponse(description = "Matrícula atualizada com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Matrícula atualizada com sucesso", externalValue = "static/api-response-examples/aluno/put/atualizado-com-sucesso.json"), schema = @Schema(implementation = MatriculaDTO.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Tipo de mídia não suportado", responseCode = "415", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Tipo de mídia não suportado ainda", externalValue = "static/api-response-examples/geral/tipo-midia-nao-suportado.json")))
    })
    public ResponseEntity<MatriculaDTO> updateCurso(@Valid @RequestBody MatriculaDTO matriculaDTO, @PathVariable Long id);
    @Operation(summary = "Deleta uma Matrícula já existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi possível deletar a Matrícula", responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi possível deletar", externalValue = "static/api-response-examples/aluno/delete/erro-ao-deletar.json"))),
            @ApiResponse(description = "Matrícula deletada com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Matrícula deletada com sucesso", externalValue = "static/api-response-examples/aluno/delete/deletado-com-sucesso.json"), schema = @Schema(implementation = Response.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Response> deleteCurso(@PathVariable Long id);

    }