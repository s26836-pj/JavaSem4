package org.example.apijazbookorder.exception;

public class ResourceOutOfStockException extends RuntimeException {
    public ResourceOutOfStockException(String message) {
        super(message);
    }
}
