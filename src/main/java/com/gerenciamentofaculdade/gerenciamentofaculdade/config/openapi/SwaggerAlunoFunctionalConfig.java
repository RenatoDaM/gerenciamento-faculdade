
package com.gerenciamentofaculdade.gerenciamentofaculdade.config.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.ErrorResponse;
import com.gerenciamentofaculdade.gerenciamentofaculdade.repository.AlunoRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.webmvc.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.http.ResponseEntity.ok;

@Configuration
@OpenAPIDefinition
public class SwaggerAlunoFunctionalConfig {
    private final Logger log = LoggerFactory.getLogger(SwaggerAlunoFunctionalConfig.class);

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    ObjectMapper objectMapper;

    ErrorResponse exemploErroServidor = new ErrorResponse(500, "Erro servidor", new ArrayList<>(List.of("erro1", "erro2", "etc...")));

    @Bean
    RouterFunction<ServerResponse> getAllAlunos() {
        return route()
                .GET("/faculdade/aluno",
                        findAllAlunos(), ops -> {
                            try {
                                ops
                                        .operationId("getAllAlunos")
                                        .summary("Lista alunos cadastrados")
                                        .parameter(parameterBuilder().name("size").description("Quantidade de itens que serão retornados na página").example("10"))
                                        .parameter(parameterBuilder().name("page").description("Número da página retornada").example("0"))
                                        .response(responseBuilder().responseCode("200").description("Resposta esperada, retornado uma página Pageable contendo uma lista de alunos"))
                                        .response(responseBuilder().responseCode("500").description("Erro interno no servidor. Vai acontecer normalmente no caso de um erro não esperado").content(createExample(objectMapper.writeValueAsString(exemploErroServidor))));
                            } catch (JsonProcessingException e) {
                                log.error("Erro de conversão objeto Java para JSON ao tentar colocar exemplo de possível resposta no Swagger.");
                                throw new RuntimeException("Erro ao serializar error response para JSON na configuração Swagger");
                            }
                        }).build();
    }

/*
    @Bean
    RouterFunction<ServerResponse> getAluno() {
        return route().GET("/faculdade/aluno/{id}", findAlunoById(), ops -> ops
                .operationId("aluno-por-id")
                .description("teste")
                .response(responseBuilder().description("teste").responseCode("200").implementation(AlunoModel.class))
        ).build();

    }

    @Bean
    RouterFunction<ServerResponse> postAluno() {
        return route().POST("/faculdade/aluno", post(new AlunoDTO()), ops -> ops.description("sdijasoida").beanClass(AlunoService.class).beanMethod("postAluno")).build();
    }


    private HandlerFunction<ServerResponse> post(AlunoDTO alunoDTO) {
        return req -> (ServerResponse) ok().body(alunoRepository.save(AlunoMapperImpl.INSTANCE.dtoToModel(alunoDTO)));
    }

    private HandlerFunction<ServerResponse> findAlunoById() {
        return req -> (ServerResponse) ok().body(
                alunoRepository.findById(Long.valueOf(req.pathVariable("id"))));
    }

    private Consumer<Builder> getOpenAPI(String method) {
        return ops -> ops.beanClass(AlunoService.class).beanMethod(method);
    }
*/

    private HandlerFunction<ServerResponse> findAllAlunos() {
        return req -> (ServerResponse) ok().body(
                alunoRepository.findAll());
    }


    private org.springdoc.core.fn.builders.content.Builder createExample(String string) {
        return org.springdoc.core.fn.builders.content.Builder.contentBuilder().example(org.springdoc.core.fn.builders.exampleobject.Builder.exampleOjectBuilder().value(string));
    }
}

