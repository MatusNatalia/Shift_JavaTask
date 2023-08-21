package org.shift.exceptions;

public class WrongArgumentException extends RuntimeException{
    private final String message;

    public WrongArgumentException(String message) {
        this.message = "Wrong arguments: " + message;
    }

    public String getMessage()
    {
        return message;
    }
}
