package org.jfm.domain.exceptions;

public class ErrorSqlException extends RuntimeException {
    public ErrorSqlException(String message) {
        super(message);
    }
}
