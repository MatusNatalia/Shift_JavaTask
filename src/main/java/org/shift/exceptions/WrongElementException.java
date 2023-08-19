package org.shift.exceptions;

public class WrongElementException extends RuntimeException{
    private String message;
    private String fileName;

    public WrongElementException(String message, String fileName) {
        this.message = "Wrong element in " + fileName + " " + message;
    }

    public String getMessage()
    {
        return message;
    }
}
