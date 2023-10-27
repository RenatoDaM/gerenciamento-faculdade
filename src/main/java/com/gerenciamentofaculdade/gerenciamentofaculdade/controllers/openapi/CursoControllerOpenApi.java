package com.gerenciamentofaculdade.gerenciamentofaculdade.controllers.openapi;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.CursoDTO;
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

public interface CursoControllerOpenApi {
    @Operation(summary = "Persiste um curso no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi possível persistir o curso", responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi possível persistir", externalValue = "static/api-response-examples/aluno/post/erro-ao-persistir.json"))),
            @ApiResponse(description = "Curso persistido com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Curso criado com sucesso", externalValue = "static/api-response-examples/aluno/post/persistido-com-sucesso.json"), schema = @Schema(implementation = CursoDTO.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Tipo de mídia não suportado", responseCode = "415", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Tipo de mídia não suportado ainda", externalValue = "static/api-response-examples/geral/tipo-midia-nao-suportado.json")))
    })
    public ResponseEntity<CursoDTO> postCurso(@Valid @RequestBody CursoDTO cursoDTO) throws Exception;

    @Operation(summary = "Busca um curso pelo ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi encontrado um Curso com este ID", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi encontrado curso com este ID", externalValue = "static/api-response-examples/aluno/get/aluno-por-id-nao-encontrado.json"))),
            @ApiResponse(description = "Curso encontrado", responseCode = "200", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Retorna curso encontrado", externalValue = "static/api-response-examples/aluno/get/aluno-encontrado.json"), schema = @Schema(implementation = CursoDTO.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<CursoDTO> getCurso(@PathVariable Long id);
    public Page<CursoDTO> getAllCursos(@PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Atualiza um Curso já existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi possível atualizar o curso", responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi possível persistir", externalValue = "static/api-response-examples/aluno/put/erro-ao-atualizar.json"))),
            @ApiResponse(description = "Curso atualizado com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Curso atualizado com sucesso", externalValue = "static/api-response-examples/aluno/put/atualizado-com-sucesso.json"), schema = @Schema(implementation = CursoDTO.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Tipo de mídia não suportado", responseCode = "415", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Tipo de mídia não suportado ainda", externalValue = "static/api-response-examples/geral/tipo-midia-nao-suportado.json")))
    })
    public ResponseEntity<CursoDTO> updateCurso(@Valid @RequestBody CursoDTO cursoDTO, @PathVariable Long id);

    @Operation(summary = "Deleta um Curso já existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi possível deletar o Curso", responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi possível deletar", externalValue = "static/api-response-examples/aluno/delete/erro-ao-deletar.json"))),
            @ApiResponse(description = "Curso deletado com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Curso deletado com sucesso", externalValue = "static/api-response-examples/aluno/delete/deletado-com-sucesso.json"), schema = @Schema(implementation = Response.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Response> deleteCurso(@PathVariable Long id);
    }
