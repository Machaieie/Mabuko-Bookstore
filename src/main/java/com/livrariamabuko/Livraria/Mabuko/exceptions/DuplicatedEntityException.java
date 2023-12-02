package com.livrariamabuko.Livraria.Mabuko.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatedEntityException extends RuntimeException {

    public DuplicatedEntityException(String message){
        super(message);
    }
    
}
