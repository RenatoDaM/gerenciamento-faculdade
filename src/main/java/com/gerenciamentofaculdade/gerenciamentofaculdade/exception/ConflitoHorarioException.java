package com.gerenciamentofaculdade.gerenciamentofaculdade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConflitoHorarioException extends Exception {
    public ConflitoHorarioException() {
        super();
    }
    public ConflitoHorarioException(String msg){
        super(msg);
    }

    public ConflitoHorarioException(String msg, Throwable cause){
        super(msg, cause);
    }
}
