package com.cstradic.open_pos.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Product not found in database.");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
