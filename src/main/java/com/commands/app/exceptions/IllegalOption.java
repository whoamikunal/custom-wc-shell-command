package com.commands.app.exceptions;

public class IllegalOption extends RuntimeException {
    public IllegalOption(String message) {
        super(message);
    }
}
