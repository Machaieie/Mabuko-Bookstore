package com.livrariamabuko.Livraria.Mabuko.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyDatabaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public EmptyDatabaseException(String message){
        super(message);
    }
    
}
