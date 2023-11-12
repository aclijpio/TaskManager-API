package ru.pio.aclij.taskmanagerapi.security.exceptions;

import javax.security.sasl.AuthenticationException;

public class AuthenticationFailedException extends AuthenticationException {
    public AuthenticationFailedException() {
    }

    public AuthenticationFailedException(String detail) {
        super(detail);
    }

    public AuthenticationFailedException(String detail, Throwable ex) {
        super(detail, ex);
    }
}
