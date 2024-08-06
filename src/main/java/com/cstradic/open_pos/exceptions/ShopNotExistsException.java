package com.cstradic.open_pos.exceptions;

public class ShopNotExistsException extends RuntimeException{
    public ShopNotExistsException() {
        super("Shop not exits in database.");
    }

    public ShopNotExistsException(String message) {
        super(message);
    }
}
