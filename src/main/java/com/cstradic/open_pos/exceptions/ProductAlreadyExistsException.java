package com.cstradic.open_pos.exceptions;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    public ProductAlreadyExistsException() {
        super("Product already exits in database.");
    }
}
