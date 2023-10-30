package com.gerenciamentofaculdade.gerenciamentofaculdade.controller.openapi;

import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.AlunoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.ErrorResponse;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.Response;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@OpenAPIDefinition
public interface AlunoControllerOpenApi {

    @Operation(summary = "Persiste um aluno no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi possível persistir aluno", responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi possível persistir", externalValue = "static/api-response-examples/aluno/post/erro-ao-persistir.json"))),
            @ApiResponse(description = "Aluno persistido com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Aluno criado com sucesso", externalValue = "static/api-response-examples/aluno/post/persistido-com-sucesso.json"), schema = @Schema(implementation = AlunoDTO.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Tipo de mídia não suportado", responseCode = "415", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Tipo de mídia não suportado ainda", externalValue = "static/api-response-examples/geral/tipo-midia-nao-suportado.json")))
    })
    public ResponseEntity<AlunoDTO> postAluno(@Valid @RequestBody AlunoDTO alunoDto) throws Exception;

    @Operation(summary = "Busca um aluno pelo ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi encontrado um Aluno com este ID", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi encontrado aluno com este ID", externalValue = "static/api-response-examples/aluno/get/aluno-por-id-nao-encontrado.json"))),
            @ApiResponse(description = "Aluno encontrado", responseCode = "200", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Retorna aluno encontrado", externalValue = "static/api-response-examples/aluno/get/aluno-encontrado.json"), schema = @Schema(implementation = AlunoDTO.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AlunoDTO> getAluno(@PathVariable Long id);

    // Enquanto não for atualizado pra Open API 3.1.0 vou deixar assim
   /* @Operation(summary = "Lista todos os alunos. É possível filtrar por parâmetro(s).")
    @ApiResponses(value = {
            @ApiResponse(description = "Lista de alunos", responseCode = "200", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Page.class), examples = @ExampleObject(name = "Exemplo de página retornada ao listar", externalValue = "static/api-response-examples/aluno/get/listagem.json"))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class)))
    })
            public Page<AlunoDTO> getAllAlunos(@RequestParam(required = false, value = "ra") String ra,
                                       @RequestParam(required = false, value = "nome") String nome,
                                       @PageableDefault(size = 10) @Parameter(hidden = true) Pageable pageable);
*/
    @Operation(summary = "Atualiza um aluno já existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi possível atualizar aluno", responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi possível persistir", externalValue = "static/api-response-examples/aluno/put/erro-ao-atualizar.json"))),
            @ApiResponse(description = "Aluno atualizado com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Aluno atualizado", externalValue = "static/api-response-examples/aluno/put/atualizado-com-sucesso.json"), schema = @Schema(implementation = AlunoDTO.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Tipo de mídia não suportado", responseCode = "415", content = @Content(mediaType = "application/json",  schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Tipo de mídia não suportado ainda", externalValue = "static/api-response-examples/geral/tipo-midia-nao-suportado.json")))
    })
    public ResponseEntity<AlunoDTO> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoModel alunoModel) throws Exception;

    @Operation(summary = "Deleta um aluno já existente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(description = "Operação FALHOU. Não foi possível deletar aluno", responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "Não foi possível deletar", externalValue = "static/api-response-examples/aluno/delete/erro-ao-deletar.json"))),
            @ApiResponse(description = "Aluno deletado com sucesso", responseCode = "201", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Aluno deletado com sucesso", externalValue = "static/api-response-examples/aluno/delete/deletado-com-sucesso.json"), schema = @Schema(implementation = Response.class))),
            @ApiResponse(description = "Erro servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Response> deleteAluno(@PathVariable Long id);


    }