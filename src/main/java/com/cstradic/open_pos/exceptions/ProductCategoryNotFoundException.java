package com.cstradic.open_pos.exceptions;

public class ProductCategoryNotFoundException extends RuntimeException{
    public ProductCategoryNotFoundException() {
        super("Product category not found");
    }

    public ProductCategoryNotFoundException(String message) {
        super(message);
    }
}
