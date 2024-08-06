package com.cstradic.open_pos.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("User already exists in database. try different email address.");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
