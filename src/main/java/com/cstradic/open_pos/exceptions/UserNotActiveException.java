package com.cstradic.open_pos.exceptions;

public class UserNotActiveException extends RuntimeException{
    public UserNotActiveException() {
        super("User not active.");
    }

    public UserNotActiveException(String message) {
        super(message);
    }
}
