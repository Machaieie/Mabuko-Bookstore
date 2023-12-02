package com.livrariamabuko.Livraria.Mabuko.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyDatabaseException extends RuntimeException{

    public EmptyDatabaseException(String message){
        super(message);
    }
    
}
