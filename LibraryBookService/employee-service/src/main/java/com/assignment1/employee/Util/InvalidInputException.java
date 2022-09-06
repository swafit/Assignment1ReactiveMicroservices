package com.assignment1.employee.Util;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException() {}
    public InvalidInputException(String message) { super(message); }
    public InvalidInputException(Throwable cause) { super(cause); }
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause); }
}
