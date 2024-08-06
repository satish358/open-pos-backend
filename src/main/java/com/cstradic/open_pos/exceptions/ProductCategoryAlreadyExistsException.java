package com.cstradic.open_pos.exceptions;

public class ProductCategoryAlreadyExistsException extends RuntimeException{
    public ProductCategoryAlreadyExistsException() {
        super("Entered product category already exists in database.");
    }

    public ProductCategoryAlreadyExistsException(String message) {
        super(message);
    }
}
