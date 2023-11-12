package ru.pio.aclij.taskmanagerapi.security.exceptions;

public class DefaultRoleNotFoundException extends RuntimeException{
    private final String message;
    public DefaultRoleNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public DefaultRoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
