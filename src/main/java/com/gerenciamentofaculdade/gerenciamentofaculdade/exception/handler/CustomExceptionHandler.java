package com.gerenciamentofaculdade.gerenciamentofaculdade.exception.handler;

import com.gerenciamentofaculdade.gerenciamentofaculdade.response.generic.ErrorResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.*;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(500,"Erro Servidor", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Object[] details = (ex.getDetailMessageArguments());
        List<String> teste = new ArrayList<>(List.of(details[1].toString().replace("[", "").replace("]", "").split(",")));

        for (int i = 0; i < teste.size(); i++) {
            teste.set(i, teste.get(i).trim());
        }

        ErrorResponse error = new ErrorResponse(400,"Violação de restrição", teste);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> argumentoInvalido(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(400,"Bad Request", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add("Tipagem do path não compatível. Provavelmente você colocou valor string num campo que deveria ser numérico. Detalhe a seguir:");
        details.add(ex.getLocalizedMessage());
        if (details.get(1).equals("Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"a\"")) {
            details.set(1, "É necessário que a variável do path referente ao ID do aluno seja um valor numérico");
        }
        ErrorResponse error = new ErrorResponse(400,"Bad Request", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> entidadeNaoEncontrada(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(404,"Not Found", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EntityExistsException.class})
    protected ResponseEntity<Object> entidadeJaExistente(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(400,"Bad Request", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add("Não foi possível ler a requisição. Verifique se o Body é válido e correspondente ao objeto do endpoint, tanto o nome dos atributos quanto sua tipagem");
        details.add("Erro: " + ex.getMessage());
        logger.error("Não foi possível ler uma requisição: " + ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(400,"Bad Request", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        logger.error("Mídia desse tipo não é aceita para requisição neste endpoint: " + ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(415,"Unsupported Media Type", details);
        return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add("O tipo de mídia " + ex.getContentType() + " não é suportado no momento");
        logger.error("Mídia desse tipo não suportada para requisição neste endpoint: " + ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(415,"Unsupported Media Type", details);
        return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
