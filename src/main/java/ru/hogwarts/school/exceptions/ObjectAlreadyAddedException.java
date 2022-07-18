package ru.hogwarts.school.exceptions;

public class ObjectAlreadyAddedException extends RuntimeException{
    public ObjectAlreadyAddedException() {
    }

    public ObjectAlreadyAddedException(String message) {
        super(message);
    }

    public ObjectAlreadyAddedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectAlreadyAddedException(Throwable cause) {
        super(cause);
    }

    public ObjectAlreadyAddedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
