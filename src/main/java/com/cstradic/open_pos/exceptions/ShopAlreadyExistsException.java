package com.cstradic.open_pos.exceptions;

public class ShopAlreadyExistsException extends RuntimeException{
    public ShopAlreadyExistsException() {
        super("Shop already exists in database.");
    }

    public ShopAlreadyExistsException(String message) {
        super(message);
    }
}
