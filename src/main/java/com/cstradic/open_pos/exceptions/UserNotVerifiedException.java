package com.cstradic.open_pos.exceptions;

public class UserNotVerifiedException extends RuntimeException{
    public UserNotVerifiedException() {
        super("Current user not verified, please verify it first.");
    }

    public UserNotVerifiedException(String message) {
        super(message);
    }
}
