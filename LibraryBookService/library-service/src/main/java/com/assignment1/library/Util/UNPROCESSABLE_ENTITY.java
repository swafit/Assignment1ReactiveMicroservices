package com.assignment1.library.Util;

public class UNPROCESSABLE_ENTITY extends RuntimeException {
    public UNPROCESSABLE_ENTITY() {}
    public UNPROCESSABLE_ENTITY(String message) { super(message); }
    public UNPROCESSABLE_ENTITY(Throwable cause) { super(cause); }
    public UNPROCESSABLE_ENTITY(String message, Throwable cause) { super(message, cause); }
}
