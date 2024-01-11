package com.livrariamabuko.Livraria.Mabuko.exceptions;

public class UnavailableQuantityException extends Exception {

    public UnavailableQuantityException() {
        super("Quantidade indisponível para a venda.");
    }

    public UnavailableQuantityException(String message) {
        super(message);
    }

    public UnavailableQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
