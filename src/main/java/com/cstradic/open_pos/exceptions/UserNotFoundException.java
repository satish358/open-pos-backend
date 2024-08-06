package com.cstradic.open_pos.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User not found in database.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
