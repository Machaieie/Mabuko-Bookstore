package com.livrariamabuko.Livraria.Mabuko.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatedEntityException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String path;
    public DuplicatedEntityException(String message, String path){
        super(message);
        this.path = path;
    }
    public String getPath() {
        return path;
    }
    
    
}
